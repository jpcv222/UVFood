/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Logs;
import classes.Usuario;
import components.UVFoodDialogs;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import managers.queries.ConsultasPermissions;
import managers.queries.KeyValidate;
import views.GestionPermisos;

/**
 *
 * @author jpcv2
 */
public class ControladorGestionPermisos {

    private GestionPermisos interfazGestionPermisos;
    public Usuario user;
    private KeyValidate keyvalidate;
    private final UVFoodDialogs modal;
    private ConsultasPermissions consultasPermissions;
    public ArrayList<String> new_user_keys_options;
    public ArrayList<String> current_user_keys;

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ControladorGestionPermisos(GestionPermisos interfazPrincipalAdmin) {
        this.interfazGestionPermisos = interfazPrincipalAdmin;
        this.consultasPermissions = new ConsultasPermissions();
        this.modal = new UVFoodDialogs();
        this.new_user_keys_options = new ArrayList<>();
        this.current_user_keys = new ArrayList<>();
    }

    public void set_init_conf() {
        createSelectModules();
        getCurrentUserKeys();
    }

    public void createSelectModules() {
        String namekey = "permissions.asign";
        try {
            String result = keyvalidate.haveKey(namekey, user.getIdUser());
            boolean validate = keyvalidate.resultHaveKey(result);
            if (validate) {
                ArrayList<String> data_response;
                data_response = consultasPermissions.get_modules("namemodule", "");
                switch (data_response.get(0)) {
                    case "error.empty":
                        logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
                        break;
                    case "server.error":
                        logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                        break;
                    case "server.success":
                        logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se muestran  modulos con " + data_response + " registros.");
                        interfazGestionPermisos.jComboBoxModulos.removeAllItems();
                        for (int x = 1; x < data_response.size(); x++) {
                            interfazGestionPermisos.jComboBoxModulos.addItem(data_response.get(x));
                        }
                        break;
                }
            } else {
                modal.error_message("Error de validación.", "Permisos denegados.", "El rol actual no tiene accesos a esta opción:", "Asignación de permisos.", null);
            }

        } catch (Exception ex) {
            modal.error_message("Error fatal.", "Error en servidor.", "Se ha generado un error inesperado.", null, null);
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
        }

    }

    public void getCurrentUserKeys() {
        String namekey = "permissions.asign";

        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);

        if (validate) {
            ArrayList<String> data_response;
            data_response = consultasPermissions.get_user_keys(interfazGestionPermisos.jLabelUserName.getText());
            switch (data_response.get(0)) {
                case "error.empty":
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
                    break;
                case "server.error":
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                    break;
                case "server.success":
                    logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se muestran  user keys con " + data_response + " registros.");
                    current_user_keys.clear();
                    for (int x = 1; x < data_response.size(); x++) {
                        current_user_keys.add(data_response.get(x));
                    }
                    break;
            }
        } else {
            modal.error_message("Error de validación.", "Permisos denegados.", "El rol actual no tiene accesos a esta opción:", "Asignación de permisos.", null);
        }

    }

    public void createKeysModule() {

        String module = (String) interfazGestionPermisos.jComboBoxModulos.getSelectedItem();
        String namekey = "permissions.asign";

        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            ArrayList<String> data_response;
            data_response = consultasPermissions.get_keys_modules(module);
            switch (data_response.get(0)) {
                case "error.empty":
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
                    break;
                case "server.error":
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                    break;
                case "server.success":
                    logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se muestran  keys por modulos con " + data_response + " registros.");
                    addCheckBox(data_response);
                    break;
            }
        } else {
            modal.error_message("Error de validación.", "Permisos denegados.", "El rol actual no tiene accesos a esta opción:", "Asignación de permisos.", null);
        }
    }

    public void addCheckBox(ArrayList<String> options) {

        interfazGestionPermisos.jPanelActions.removeAll();
        interfazGestionPermisos.jPanelActions.repaint();

        for (int x = 1; x < options.size(); x++) {
            JCheckBox key_option = new JCheckBox(options.get(x));
            interfazGestionPermisos.jPanelActions.add(key_option);
            key_option.setSelected(validateSelected(key_option));
            interfazGestionPermisos.jPanelActions.validate();
            interfazGestionPermisos.jPanelActions.repaint();

        }
    }

    public boolean validateSelected(JCheckBox item) {
        boolean result = false;
        try {
            for (int i = 0; i < current_user_keys.size(); i++) {
                if (item.getText().equals(current_user_keys.get(i))) {
                    result = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
        }

        return result;
    }

    public void validateCheckSelected() {
        new_user_keys_options.clear();
        java.awt.Container check[] = (java.awt.Container[]) interfazGestionPermisos.jPanelActions.getComponents();
        JCheckBox num[] = (JCheckBox[]) check;
        for (int i = 0; i < interfazGestionPermisos.jPanelActions.getComponentCount(); i++) {
            if (num[i].isSelected()) {
                new_user_keys_options.add(num[i].getText());
            }
        }
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
