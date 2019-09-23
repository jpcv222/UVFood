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
    public ArrayList<String> keys_options;

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ControladorGestionPermisos(GestionPermisos interfazPrincipalAdmin) {
        this.interfazGestionPermisos = interfazPrincipalAdmin;
        this.consultasPermissions = new ConsultasPermissions();
        this.modal = new UVFoodDialogs();
        this.keys_options = new ArrayList<>();
    }

    public void set_init_conf() {
        createSelectModules();
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
                modal.error_message("Error de validación.", "Permisos denegados.", "El rol actual no tiene accesos a esta opción.", null, null);
            }

        } catch (Exception ex) {
            modal.error_message("Error fatal.", "Error en servidor.", "Se ha generado un error inesperado.", null, null);
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
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
            modal.error_message("Error de validación.", "Permisos denegados.", "El rol actual no tiene accesos a esta opción.", null, null);
        }
    }

    public void addCheckBox(ArrayList<String> options) {

        interfazGestionPermisos.jPanelActions.removeAll();
        interfazGestionPermisos.jPanelActions.repaint();

        for (int x = 1; x < options.size(); x++) {
            JCheckBox key_option = new JCheckBox(options.get(x));
            interfazGestionPermisos.jPanelActions.add(key_option);
            interfazGestionPermisos.jPanelActions.validate();
            interfazGestionPermisos.jPanelActions.repaint();

        }
    }

    public void validateCheckSelected(JCheckBox key_option) {
        keys_options.clear();
        java.awt.Container check[] = (java.awt.Container[]) interfazGestionPermisos.jPanelActions.getComponents();
        JCheckBox num[] = (JCheckBox[]) check;
        for (int i = 0; i < interfazGestionPermisos.jPanelActions.getComponentCount(); i++) {
            if (num[i].isSelected()) {
                keys_options.add(num[i].getText());
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
