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
import views.VistaAdmin;

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

    public String traerMenu() {
        String result = "asas";
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;

            Connection conn = Conexion();

            String inserImg = "SELECT file_image FROM uvfood_images WHERE publication_date = '" + getFecha() + "' AND type_image IN\n" +
                                "(SELECT idtype FROM uvfood_type_image WHERE name_type = 'Menu');";

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

}
