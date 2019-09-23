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
    
    public ArrayList<String> get_user_keys(String username){
        
         ArrayList<String> result = new ArrayList();
        
        try {
            Statement ps = null;
            Connection conn = Conexion();
            ResultSet rs = null;
            ResultSet aux_rs = null;
            String sql = "SELECT namekey FROM uvfood_keys WHERE idkey IN "
                    + "(SELECT idkey FROM uvfood_user_key WHERE iduser  IN\n" +
"					  (SELECT iduser FROM uvfood_user WHERE username=  '"+username+"'));";

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
    
    public ArrayList<String> get_modules(String atrib, String condition) {
        
        ArrayList<String> result = new ArrayList();
        
        try {
            Statement ps = null;
            Connection conn = Conexion();
            ResultSet rs = null;
            ResultSet aux_rs = null;
            String sql = "SELECT " + atrib + " FROM uvfood_modules " + condition + ";";

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            aux_rs = rs;
            if (aux_rs.next()) {
                result.add("server.success");
                do {
                    result.add(rs.getString(atrib));
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

    public ArrayList<String> get_keys_modules(String module) {
        
        ArrayList<String> result = new ArrayList();

        try {
            String id_module = get_modules("idmodule", "WHERE namemodule = '" + module+"'").get(1);

            Statement ps = null;
            Connection conn = Conexion();
            ResultSet rs = null;
            ResultSet aux_rs = null;
            String sql = "SELECT namekey FROM uvfood_keys WHERE idmodule = " + id_module + ";";

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

        } catch (SQLException | NullPointerException | IndexOutOfBoundsException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result.add("server.error");
        }

        return result;
    }

}
