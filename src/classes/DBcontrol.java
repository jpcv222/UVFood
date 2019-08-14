/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 



/**
 *
 * @author sp
 */
public class DBcontrol {

        private String url = "jdbc:postgresql://localhost:5432/uvfood";
        private String user = "postgres";
        private String password = "pgsql";
        private Connection conn = null;

        public Connection Conexion() {           
            try {
                conn = DriverManager.getConnection(this.url, this.user, this.password);
                System.out.println("Conexion exitosa");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return conn;
        }
}

    

