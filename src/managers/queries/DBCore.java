/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.Logs;
import static classes.ConexionBD.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sp
 */
public class DBCore {

    private final Logs logs;

    public DBCore() {
        logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    }

    public int get_count_record(String table, String atrib) {

        int result = 0;
        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        String sql = "SELECT count(" + atrib + ") FROM uvfood_" + table + ";";

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
