/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import static classes.ConexionBD.Conexion;
import classes.Logs;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jpcv2
 */
public class ConsultasPermissions {

    private DBCore db_core = new DBCore();
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ArrayList<String> get_modules() {
        ArrayList<String> result = new ArrayList();

        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        ResultSet aux_rs = null;
        String sql = "SELECT namemodule FROM uvfood_modules;";

        try {

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            aux_rs = rs;
            if (aux_rs.next()) {
                result.add("server.success");
                do {
                    result.add(rs.getString("namemodule"));
                } while (rs.next());
            } else {
                result.add("error.empty");
            }
            rs.close();
            ps.close();

        } catch (SQLException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result.add("server.error");
        }

        return result;
    }
    
       public ArrayList<String> get_keys_modules() {
        ArrayList<String> result = new ArrayList();

        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        ResultSet aux_rs = null;
        String sql = "SELECT namekey FROM uvfood_keys;";

        try {

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            aux_rs = rs;
            if (aux_rs.next()) {
                result.add("server.success");
                do {
                    result.add(rs.getString("namekey"));
                } while (rs.next());
            } else {
                result.add("error.empty");
            }
            rs.close();
            ps.close();

        } catch (SQLException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result.add("server.error");
        }

        return result;
    }

}
