/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author sp
 */
public class ConexionBD {

    private String url = "jdbc:postgresql://localhost:5432/prueba";
    private String user = "postgres";
    private String password = "root";
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

    public void cerrarConexion() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion " + ex.getMessage());
            System.exit(1);
        }
    }

}