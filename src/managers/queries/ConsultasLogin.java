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
        Connection conn = Conexion();
        ResultSet rs = null;
       
        String sql = "SELECT * FROM uvfood_user WHERE username = ?";

        try {
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
                    modeloCliente.setIdUser(rs.getInt(1));
                    modeloCliente.setUsername(rs.getString(2));
                    modeloCliente.setFirstname(rs.getString(3));
                    modeloCliente.setSurname(rs.getString(4));
                    modeloCliente.setBirth_date(rs.getDate(5));
                    modeloCliente.setEmail(rs.getString(6));

                    result = "success";
                } else {
                    result = "error.incorrect_password";
                }
            } else {
                result = "error.incorrect_user";
            }

        } catch (SQLException e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() +"// "+  e.getMessage() +" "+ e.toString());
            result = "error.server";

        }
        
        return result;

    }

}
