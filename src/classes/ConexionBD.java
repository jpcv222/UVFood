/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import components.UVFoodDialogs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author sp
 */
public  class ConexionBD {
    static UVFoodDialogs modal = new UVFoodDialogs();

    private static final String url = "jdbc:postgresql://localhost:5432/uvfood";
    private static final String user = "postgres";
    private static final String password = "atila1994";
    private static Connection conn = null;
    private static Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public static Connection Conexion() {
        try {
            conn = DriverManager.getConnection(ConexionBD.url, ConexionBD.user, ConexionBD.password);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() +"// "+  e.getMessage() +" "+ e.toString());
        }

        return conn;
    }

    public void cerrarConexion() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() +"// "+  ex.getMessage() +" "+ ex.toString());
            System.exit(1);
        }
    }

}
