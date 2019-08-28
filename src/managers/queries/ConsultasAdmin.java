/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.ConexionBD;
import classes.Logs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
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
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        Statement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM uvfood_user";
        try {
            conn = Conexion();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

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
            vista.jTableUsers.setModel(modelo);
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        }

    }

    public boolean buscarUser(VistaAdmin vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        String filtro = "'%" + vista.jTextFieldBuscarUser.getText() + "%'";

        //$query = "SELECT * FROM imagenesproductos WHERE nombre LIKE '%$q%' OR descripcion LIKE '%$q%' OR precio LIKE '%$q%' OR categoria LIKE '%$q%'";
        String sql = "SELECT * FROM uvfood_user where idUser::text LIKE" + filtro + " OR username::text LIKE" + filtro + " OR firstname::text LIKE" + filtro + " OR surname::text LIKE" + filtro;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

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
            vista.jTableUsers.setModel(modelo);
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            //modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");

            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            //modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            return false;
        }
    }

    public static String sqlDateToString(java.sql.Date date) {
        if (date != null) {
            java.util.Date utilDate = new java.util.Date(date.getTime());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return dateFormat.format(utilDate);
        }
        return null;
    }

    public boolean llenarAcciones(VistaAdmin vista) {

        Statement ps = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexion();

            int fila = vista.jTableUsers.getSelectedRow();
            String codigo = vista.jTableUsers.getValueAt(fila, 0).toString();

            String sql = "SELECT * FROM uvfood_user WHERE iduser= '" + codigo + "';";
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                vista.jTextFieldUser.setText(rs.getString(2));
                vista.jTextFieldName.setText(rs.getString(3));
                vista.jTextFieldApellido.setText(rs.getString(4));
                vista.jTextFieldEmail.setText(rs.getString(6));
                vista.jTextFieldFecNa.setText(sqlDateToString(rs.getDate(5)));
            }
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            //modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());

            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            //modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            return false;
        }
    }

}
