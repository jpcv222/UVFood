/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.ConexionBD;
import classes.Logs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import views.ConfirmMessage;
import views.VistaCliente;

/**
 *
 * @author STH_1F_MCB_PC09
 */
public class ConsultasCliente extends ConexionBD {

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    private DBCore db_core = new DBCore();
    
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

            String sql = "SELECT uvfood_user.username, uvfood_user.firstname,  uvfood_user.surname, uvfood_sales.tickets, uvfood_sales.total_price, uvfood_sales.sale_date\n"
                    + "FROM uvfood_sales \n"
                    + "INNER JOIN uvfood_user ON uvfood_sales.created_to = uvfood_user.iduser;";

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
    
    
}
