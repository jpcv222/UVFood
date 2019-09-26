/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.ConexionBD;
import classes.Logs;
import classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import views.VistaLogin;

//libreria para encriptar
//import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author sp
 */
public class ConsultasLogin extends ConexionBD {

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public String login(Usuario modeloCliente, VistaLogin vista) {

        String result = "error.unknow";
        PreparedStatement ps = null;
        Connection conn = null;

        ResultSet rs = null;

        String sql = "SELECT * FROM uvfood_user WHERE is_active = 1 AND username = ?";

        try {
            conn = Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, modeloCliente.getUsername());
            rs = ps.executeQuery();

            //verificamos primero si el usuario existe
            if (rs.next()) {
                //verificamos si la contraseña que escribe es igual a la de la bd
                if (modeloCliente.getPassword_user().equals(rs.getString(7))) {

                    /*
                    ahora los datos que trae la consulta se los pasamos al modelo
                    para luego acceder a ellos
                     */
                    vista.login_manager.user.setIdUser(rs.getInt(1));
                    vista.login_manager.user.setUsername(rs.getString(2));
                    vista.login_manager.user.setFirstname(rs.getString(3));
                    vista.login_manager.user.setSurname(rs.getString(4));
                    vista.login_manager.user.setBirth_date(rs.getDate(5));
                    vista.login_manager.user.setEmail(rs.getString(6));

                    result = "success";
                } else {
                    result = "error.incorrect_password";
                }
            } else {
                result = "error.incorrect_user";
            }

        } catch (SQLException e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + e.getMessage() + " " + e.toString());
            result = "error.server";

        }

        return result;
    }

    public String insert_session_record(int id_user) {
        String result =  "error.unknow";
        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;


        String sql = "INSERT INTO uvfood_sessions (iduser) VALUES ("+id_user+");";

        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            if (rs.rowInserted()) {
                result = "success.insert";
            }else{
                 result = "error.insert";
            }

        } catch (SQLException e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + e.getMessage() + " " + e.toString());
            result = "error.unknow";
        }
        
        return result;
    }

    public String get_view(Usuario modeloCliente) {
        String result = "error.unknow";

        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;

        String username = modeloCliente.getUsername();

        String sql = "SELECT type_user FROM uvfood_typeuser WHERE id_typeuser IN "
                + "(SELECT id_typeuser FROM uvfood_user_extended WHERE status = 1 AND iduser IN "
                + "	(SELECT iduser FROM uvfood_user WHERE username = '" + username + "'));";

        try {

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            if (rs.next()) {
                result = define_view(rs.getString(1));
            } else {
                result = "error.notfound_typeuser";
            }

        } catch (SQLException e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + e.getMessage() + " " + e.toString());
            result = "error.server";
        }

        return result;
    }

    public String define_view(String result_sql) {
        String view_result;

        switch (result_sql) {
            case "Administrador":
                view_result = "success.admin";
                break;
            case "Cliente":
                view_result = "success.cliente";
                break;
            case "Vendedor":
                view_result = "success.admin";
                break;
            default:
                view_result = "success.notfound";
                break;
        }

        return view_result;
    }
}
