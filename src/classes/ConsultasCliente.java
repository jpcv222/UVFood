/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import validations.Validations;
import views.VistaLogin;

//libreria para encriptar
//import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author sp
 */
public class ConsultasCliente extends ConexionBD {

    public boolean login(Usuario modeloCliente, VistaLogin vista) {
        PreparedStatement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM uvfood_usuario WHERE username = ?";

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

                    return true;
                } else {
                    Validations validation = new Validations();
                    validation.alert("La contraseña no coincide con el usuario", "danger", vista.jPanelError, vista.jLabelError);
                    return false;
                }
            } else {
                Validations validation = new Validations();
                validation.alert("El usuario no existe", "danger", vista.jPanelError, vista.jLabelError);
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }

    }

}
