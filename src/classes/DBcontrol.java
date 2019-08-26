/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sp
 */
public class DBcontrol {
    
        public static Connection GetConnection(){
        
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            
            
            String server = "jdbc:mysql://localhost/vetmajapipetsbd";
            
            
            String userBD= "root";
            String passwordBD="";
            
            conexion = (Connection) DriverManager.getConnection(server, userBD, passwordBD);
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBcontrol.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return conexion;
    }
    
}
