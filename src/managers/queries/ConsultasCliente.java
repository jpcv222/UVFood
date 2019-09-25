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
    
    
    
}
