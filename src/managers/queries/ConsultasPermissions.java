/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import static classes.ConexionBD.Conexion;
import classes.Logs;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public ArrayList<String> update_user_keys(String username, ArrayList<String> user_keys) {

        ArrayList<String> result = new ArrayList();

        String iduser = (String) db_core.get_record("user", "iduser", "username = '" + username + "'");

        try {
            PreparedStatement ps = null;
            Connection conn = Conexion();

            for (int i = 0; i < user_keys.size(); i++) {
                ArrayList<String> result_key = get_keys("idkey", "WHERE namekey = '" + user_keys.get(i) + "'");
                if (result_key.size() > 1) {
                    int idkey = Integer.parseInt(result_key.get(1));

                    String sql = "INSERT INTO uvfood_user_key VALUES (" + iduser + "," + idkey + ");";

                    ps = conn.prepareStatement(sql);
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        result.add("success.dato.insertado");
                    } else {
                        result.add("error.dato.no.insertado");
                    }

                    ps.close();
                }

            }

        } catch (SQLException | NullPointerException | IndexOutOfBoundsException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result.add("server.error");
        }

        return result;
    }

    public String insertModule(String namemodule) {

        String result;

        try {
            PreparedStatement ps = null;
            Connection conn = Conexion();

            String sql = "INSERT INTO uvfood_modules (namemodule) VALUES ('" + namemodule + "');";

            ps = conn.prepareStatement(sql);
            int res = ps.executeUpdate();
            if (res > 0) {
                result = "success.dato.insertado";
            } else {
                result = "error.dato.no.insertado";
            }

            ps.close();

        } catch (SQLException | NullPointerException | IndexOutOfBoundsException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "server.error";
        }

        return result;
    }

    public String insertKey(String namemodule, String namekey) {

        String result;

        try {
            String id_module = get_modules("idmodule", "WHERE namemodule = '" + namemodule + "'").get(1);

            PreparedStatement ps = null;
            Connection conn = Conexion();

            String sql = "INSERT INTO uvfood_keys (idmodule,namekey) VALUES ('" + id_module + "','" + namekey + "');";

            ps = conn.prepareStatement(sql);
            int res = ps.executeUpdate();
            if (res > 0) {
                result = "success.dato.insertado";
            } else {
                result = "error.dato.no.insertado";
            }

            ps.close();

        } catch (SQLException | NullPointerException | IndexOutOfBoundsException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "server.error";
        }

        return result;
    }

    public ArrayList<String> get_user_keys(String username) {

        ArrayList<String> result = new ArrayList();

        try {
            Statement ps = null;
            Connection conn = Conexion();
            ResultSet rs = null;
            ResultSet aux_rs = null;
            String sql = "SELECT namekey FROM uvfood_keys WHERE idkey IN "
                    + "(SELECT idkey FROM uvfood_user_key WHERE iduser  IN\n"
                    + "					  (SELECT iduser FROM uvfood_user WHERE username=  '" + username + "'));";

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
            String id_module = get_modules("idmodule", "WHERE namemodule = '" + module + "'").get(1);

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

    public ArrayList<String> get_keys(String atrib, String condition) {

        ArrayList<String> result = new ArrayList();

        try {
            Statement ps = null;
            Connection conn = Conexion();
            ResultSet rs = null;
            ResultSet aux_rs = null;
            String sql = "SELECT " + atrib + " FROM uvfood_keys " + condition + ";";

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

}
