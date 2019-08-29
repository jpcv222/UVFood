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
            modelo.addColumn("Email");
            modelo.addColumn("Fecha de creacion");
            modelo.addColumn("Estado");

            while (rs.next()) {

                Object[] filas = new Object[cantidadCol];

                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                filas[4] = rs.getObject(5);
                filas[5] = rs.getObject(6);
                filas[6] = rs.getObject(8);

                if (rs.getInt(9) == 1) {
                    filas[7] = "Activo";
                } else {
                    filas[7] = "No activo";
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
            String sql2 = "SELECT t2.type_user FROM uvfood_user_extended AS t1 INNER JOIN uvfood_typeuser "
                    + "AS t2 ON t2.id_typeuser = t1.id_typeuser WHERE t1.iduser ='" + codigo + "';";
            ps = conn.createStatement();
            rs = ps.executeQuery(sql2);

            while (rs.next()) {
                if (rs.getString(1) == "") {
                    vista.jTextFieldRol.setText("Sin rol asignado");
                } else {
                    vista.jTextFieldRol.setText(rs.getString(1));
                }

            }

            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());

            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        } catch (ArrayIndexOutOfBoundsException ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
            return false;
        }
    }

    public boolean fillCombo(VistaAdmin vista) {
        Statement ps = null;
        ResultSet rs = null;

        try {
            Connection conn = Conexion();

            String sql = "SELECT * FROM uvfood_typeuser;";
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                vista.jComboBoxRoles.addItem(rs.getString("type_user"));
            }
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        } catch (ArrayIndexOutOfBoundsException ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
            return false;
        }

    }

    public int get_count_users() {

        int result = 0;
        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        String sql = "";

        try {

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            if (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            } else {
                result = -99;
            }
            rs.close();
            ps.close();

        } catch (SQLException np) {
             logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
             result = -999;
        }
        
        return result;

    }

}
