/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Logs;
import classes.Usuario;
import components.UVFoodDialogs;
import java.util.ArrayList;
import managers.queries.ConsultasPermissions;
import managers.queries.KeyValidate;
import views.GestionPermisos;

/**
 *
 * @author jpcv2
 */
public class ControladorGestionPermisos {

    private GestionPermisos interfazGestionPermisos;
    private UVFoodDialogs modal;
    public Usuario user;
    private KeyValidate keyvalidate;
    private ConsultasPermissions consultasPermissions;

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ControladorGestionPermisos(GestionPermisos interfazPrincipalAdmin) {
        try {
            this.interfazGestionPermisos = interfazPrincipalAdmin;
            this.modal = new UVFoodDialogs();
            this.keyvalidate = new KeyValidate(modal);
            this.consultasPermissions = new ConsultasPermissions();
            set_init_conf();
        } catch (Exception ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString() + " ERROR DEL PROGRAMADOR.");
            UVFoodDialogs modal_alt = new UVFoodDialogs();
            modal_alt.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Error del programador.", null);
        }
    }

    public void set_init_conf() {
        String current_text = interfazGestionPermisos.jLabelUserNamePerm.getText();
        this.interfazGestionPermisos.jLabelUserNamePerm.setText(current_text + user.getFirstname() + " " + user.getSurname());
        createSelectModules("permissions.asign");
    }

    public void createSelectModules(String namekey) {

        try {
            String result = keyvalidate.haveKey(namekey, user.getIdUser());
            boolean validate = keyvalidate.resultHaveKey(result);
            if (validate) {
                ArrayList<String> data_response;
                data_response = consultasPermissions.get_modules();
                switch (data_response.get(0)) {
                    case "error.empty":
                        modal.error_message("Warning base de datos.", "Algo anda mal.", "La consulta no arrojó resultados.", null, null);
                        logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
                        break;
                    case "server.error":
                        modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", null);
                        logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                        break;
                    default:
                        logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se muestran  modulos con " + data_response + " registros.");
                        interfazGestionPermisos.jComboBoxModulos.removeAllItems();
                        for (int x = 0; x < data_response.size(); x++) {
                            interfazGestionPermisos.jComboBoxModulos.addItem(data_response.get(x));
                        }
                        break;
                }
            }

        } catch (Exception ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", null);
        }

    }

}
