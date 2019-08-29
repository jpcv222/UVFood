/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.Logs;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jpcv2
 */
public class ConsultasPermissions {

    private DBCore db_core = new DBCore();
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ArrayList <String> get_modules() {
        ArrayList <String> result = new ArrayList();
        try {
        
        Object response = db_core.get_all_records("modules", "namemodule");
        result.clear();
        if ("error.empty".equals((String) response) || "server.error".equals((String) response)) {
            result.add((String) response);
        }else {
            ResultSet new_response = (ResultSet) response;
            while (new_response.next()) {
              result.add(new_response.getString("namemodule"));
            }
        }
        }
        catch(Exception ex){
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result.add("server.error");
        }
        return result;
    }

}
