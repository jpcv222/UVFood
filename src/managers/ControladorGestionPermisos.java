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

    public void asignUserPermissions() {
        validateCheckSelected();

        String namekey = "permissions.asign";

        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            ArrayList<String> data_response;
            ArrayList<String> keys = user_keys_to_insert(new_user_keys_options, current_user_keys);
            if (!keys.isEmpty()) {

                data_response = consultasPermissions.update_user_keys(interfazGestionPermisos.jLabelUserName.getText(), keys);
                if (validateAsignUserPermissions(data_response)) {
                    modal.success_message("Éxito.", "Permisos actualizados.", "Se han actualizado permisos", "de manera exitosa.", null);
                    logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se actualizan permisos de usuario de manera exitosa.");
                } else {
                    modal.error_message("Error.", "Permisos no actualizados.", "No se han actualizado permisos", "de manera exitosa.", null);
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Actualización de permisos de usuario no completada.");
                }
            } else {
                modal.error_message("Error.", "Permisos no actualizados.", "No se han actualizado permisos", "de manera exitosa.", "No hay nada qué actualizar.");
            }

        }
    }

    public static boolean validateAsignUserPermissions(ArrayList<String> data_response) {
        boolean result = true;
        try{
        for (int i = 0; i < data_response.size(); i++) {
            if (data_response.get(i).equals("error.dato.no.insertado") || data_response.get(i).equals("server.error")) {
                return false;
            }
        }
        }catch(Exception np){
             result = true;
        }

        return result;
    }

    public ArrayList<String> user_keys_to_insert(ArrayList<String> new_user_keys, ArrayList<String> current_user_keys) {
        ArrayList<String> result = new_user_keys;

        for (int i = 0; i < new_user_keys.size(); i++) {

            for (int j = 0; j < current_user_keys.size(); j++) {
                if (new_user_keys.get(i).equals(current_user_keys.get(j))) {
                    result.remove(i);
                }
            }
        }
        return result;
    }

    public void validateCheckSelected() {

        for (int x = 0; x < interfazGestionPermisos.jPanelActions.getComponentCount(); x++) {
            if (interfazGestionPermisos.jPanelActions.getComponent(x) instanceof JCheckBox) {
                JCheckBox check = (JCheckBox) interfazGestionPermisos.jPanelActions.getComponent(x);
                if (check.isSelected()) {
                    new_user_keys_options.add(check.getText());
                }
            }
        }

    }

    public void addModule() {

        String namekey = "permissions.create.modules";

        //String result = keyvalidate.haveKey(namekey, user.getIdUser());
        //boolean validate = keyvalidate.resultHaveKey(result);
        if (true) {
            String new_module = interfazGestionPermisos.jTextFieldModuloNuevo.getText();
            boolean exist_module = existInputModule(new_module);
            if (!exist_module && !new_module.trim().equals("")) {
                insertModule(new_module);
            } else {
                modal.error_message("Error de validación.", "Registro duplicado o campo vacío.", "El módulo ingresado no se puede registrar.", "Intente con otro módulo.", null);
            }
        }
    }

    public void insertModule(String new_module) {

        String data_response;
        data_response = consultasPermissions.insertModule(new_module);
        switch (data_response) {
            case "error.dato.no.insertado":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados: error.dato.no.insertado");
                modal.error_message("Error.", "Módulos no actualizados.", "El módulo ingresado no se puede registrar.", "Error en el servidor.", null);
                break;
            case "server.error":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                modal.error_message("Error.", "Módulos no actualizados.", "El módulo ingresado no se puede registrar.", "Error en el servidor.", null);
                break;
            case "success.dato.insertado":
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se muestran  user keys con " + data_response + " registros.");
                modal.success_message("Éxito.", "Módulos actualizados.", "Se ha registrado módulo", "de manera exitosa.", null);
                break;
        }
    }

    public boolean existInputModule(String new_module) {

        boolean response;

        ArrayList<String> data_response;
        data_response = consultasPermissions.get_modules("idmodule", "WHERE namemodule = '" + new_module + "'");
        switch (data_response.get(0)) {
            case "error.empty":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
                response = false;
                break;
            case "server.error":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                response = true;
                break;
            case "server.success":
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se muestran  user modules con " + data_response + " registros.");
                response = true;
                break;
            default:
                response = true;
                break;
        }

        return response;

    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
