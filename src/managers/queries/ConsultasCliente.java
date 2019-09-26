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
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import views.VistaCliente;

/**
 *
 * @author STH_1F_MCB_PC09
 */
public class ConsultasCliente extends ConexionBD {

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    private DBCore db_core = new DBCore();

    public String getFecha() {
        java.util.Date myDate = new java.util.Date();

        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(myDate);

        return fecha;
    }

    public boolean buscarUserSales(VistaCliente vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        try {
            Statement ps = null;
            Connection conn = Conexion();
            ResultSet rs = null;
            String filtro = "'%" + vista.jTextFieldBuscarUserSales.getText() + "%'";

            //$query = "SELECT * FROM imagenesproductos WHERE nombre LIKE '%$q%' OR descripcion LIKE '%$q%' OR precio LIKE '%$q%' OR categoria LIKE '%$q%'";
            String sql = "SELECT * FROM (SELECT uvfood_user.username, uvfood_user.firstname, \n"
                    + "uvfood_user.surname, uvfood_sales.tickets, uvfood_sales.total_price, uvfood_sales.sale_date\n"
                    + "FROM uvfood_sales \n"
                    + "INNER JOIN uvfood_user ON uvfood_sales.created_to = uvfood_user.iduser\n"
                    + "WHERE uvfood_sales.created_to = '" + vista.manager.user.getIdUser() + "') AS result_sales \n"
                    + "	 WHERE result_sales.username::text  LIKE " + filtro + "\n"
                    + "	 OR result_sales.firstname::text  LIKE " + filtro + " OR result_sales.surname::text  LIKE " + filtro + "\n"
                    + "	 OR result_sales.tickets::text  LIKE " + filtro + " OR result_sales.total_price::text  LIKE " + filtro + " \n"
                    + "	 OR result_sales.sale_date::text  LIKE " + filtro + ";";

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();

            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Tickets");
            modelo.addColumn("Total");
            modelo.addColumn("Fecha");

            while (rs.next()) {

                Object[] filas = new Object[cantidadCol];

                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                filas[4] = rs.getObject(5);
                filas[5] = rs.getObject(6);

                modelo.addRow(filas);
            }
            vista.jTableUsersSales.setModel(modelo);

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

    public boolean llenarTablaSales(VistaCliente vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        try {
            Statement ps = null;
            Connection conn = null;
            ResultSet rs = null;

            String sql = "SELECT uvfood_user.username, uvfood_user.firstname, \n"
                    + "uvfood_user.surname, uvfood_sales.tickets, uvfood_sales.total_price, uvfood_sales.sale_date\n"
                    + "FROM uvfood_sales \n"
                    + "INNER JOIN uvfood_user ON uvfood_sales.created_to = uvfood_user.iduser\n"
                    + "WHERE uvfood_sales.created_to = '" + vista.manager.user.getIdUser() + "';";

            conn = Conexion();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();

            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Tickets");
            modelo.addColumn("Total");
            modelo.addColumn("Fecha");

            while (rs.next()) {

                Object[] filas = new Object[cantidadCol];

                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                filas[4] = rs.getObject(5);
                filas[5] = rs.getObject(6);

                modelo.addRow(filas);
            }
            vista.jTableUsersSales.setModel(modelo);
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

    public String traerMenu() {
        String result = "";
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;

            Connection conn = Conexion();

            String inserImg = "SELECT file_image FROM uvfood_images WHERE publication_date = '" + getFecha() + "' AND type_image IN\n"
                    + "(SELECT idtype FROM uvfood_type_image WHERE name_type = 'Menu');";

            ps = conn.prepareStatement(inserImg);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);

            } else {
                result = "error.img.no.encontrada";
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = "error.sql.error";
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "error.NP.error";
        }
        return result;
    }

    public String countTicekt(VistaCliente vista) {
        String result = "";
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;

            Connection conn = Conexion();
            //System.out.println(vista.manager.user.getIdUser());
            String ticket = "SELECT uvfood_sales.tickets FROM uvfood_sales INNER JOIN uvfood_user ON uvfood_sales.created_to = uvfood_user.iduser WHERE uvfood_sales.created_to = '" + vista.manager.user.getIdUser() + "';";

            ps = conn.prepareStatement(ticket);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);

            } else {
                result = "error.consulta";
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = "error.sql.error";
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "error.NP.error";
        }
        return result;
    }

}
