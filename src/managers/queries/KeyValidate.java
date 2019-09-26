/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.ConexionBD;
import classes.Logs;
import components.UVFoodDialogs;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jpcv2
 */
public class KeyValidate extends ConexionBD {

    private static Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    private static  UVFoodDialogs modal;

    public KeyValidate(UVFoodDialogs modal) {
        KeyValidate.modal = modal;
    }

    public static String haveKey(String namekey, int iduser) {
        String result = "error.unknow";
        try {
        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM uvfood_user_key WHERE iduser = '" + iduser + "' AND idkey IN "
                + "	(SELECT idkey FROM uvfood_keys WHERE namekey = '" + namekey + "');";



            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            if (rs.next()) {
                result = "success.key_access";
            } else {
                result = "error.notfound_key";
            }

        } catch (SQLException | NullPointerException e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + e.getMessage() + " " + e.toString());
            result = "error.server";
        }

        return result;
    }

    public static boolean resultHaveKey(String result) {
        boolean have_key = false;
        
        try{

        switch (result) {
            case "success.key_access":
                have_key = true;
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Permiso concedido.");
                break;
            case "error.notfound_key":
                have_key = false;
                modal.error_message("Error de acceso.", "Permisos denegados.", "Comuníquese con el área de sistemas.", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Permisos denegados.");
                break;
            case "error.server":
                have_key = false;
                modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", "No se puede leer archivo.");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                break;
            default:
                have_key = false;
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                break;
        }
        
        }catch(NullPointerException ex){
            have_key = false;
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
        }

        return have_key;
    }

}
