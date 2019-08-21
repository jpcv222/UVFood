/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import views.VistaAdmin;

/**
 *
 * @author STH_1F_MCB_PC09
 */
public class ConsultasAdmin extends ConexionBD {

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public boolean llenarTabla(VistaAdmin vista) {

        DefaultTableModel modelo = new DefaultTableModel();
        vista.jTableUsers.setModel(modelo);

        PreparedStatement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;

        String sql = " select * from uvfood_user";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Fecha de Nacimiento");
            modelo.addColumn("Fecha de creacion");

            while (rs.next()) {

                Object[] filas = new Object[cantidadCol];

                for (int i = 0; i < cantidadCol; i++) {
                    filas[i] = rs.getObject(i + 1);

                }
                modelo.addRow(filas);
            }
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            return false;
        }

    }
    
    public boolean buscarUser(String dato, VistaAdmin vista){

        DefaultTableModel modelo = new DefaultTableModel();
        vista.jTableUsers.setModel(modelo);

        PreparedStatement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        String filtro = "%"+dato+"%";

        String sql = " select * from uvfood_user where idUser, username, firstname, surname, birth_date, email,creation_date LIKE " +"'"+filtro+"'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Fecha de Nacimiento");
            modelo.addColumn("Fecha de creacion");

            while (rs.next()) {

                Object[] filas = new Object[cantidadCol];

                for (int i = 0; i < cantidadCol; i++) {
                    filas[i] = rs.getObject(i + 1);

                }
                modelo.addRow(filas);
            }
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            return false;
        }
    }

}