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

//libreria para encriptar
//import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author sp
 */
public class ConsultasCliente extends ConexionBD {

    //ejemplo consulta
    /*public boolean login(Cliente modeloCliente) {
        PreparedStatement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, modeloCliente.getUsuario());
            rs = ps.executeQuery();

            //verificamos primero si el usuario existe
            if (rs.next()) {
                if (modeloCliente.getClave().equals(rs.getString(/*numero de columna donde esta la clave*//*))) {

                    /*
                    aqui se le asigna lo que trae la consulta para el objeto cliente
                    ejemplo
                    modeloCliente.setId(rs.getInt(columna del id en la BD);
                     */
                   /* return true;

                }else{
                    return false;
                }
                
            }
            return false;          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }

    }*/

}
