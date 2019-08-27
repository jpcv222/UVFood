/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Usuario;
import components.UVFoodDialogs;
import managers.queries.ConsultasLogin;
import org.apache.commons.codec.digest.DigestUtils;
import validations.FormValidations;
import views.VistaAdmin;
import views.VistaCliente;
import views.VistaLogin;

/**
 *
 * @author jpcv2
 */
public class ControladorLogin {

    private final VistaLogin vistaLogin;
    private final FormValidations validations;
    private Usuario user;
    private ConsultasLogin loginQueries;
    private final UVFoodDialogs modal;

    public ControladorLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
        this.validations = new FormValidations();
        this.modal = new UVFoodDialogs();
    }

    public void request_login() {

        String clave = new String(vistaLogin.jPasswordField.getPassword());
        String claveEn = DigestUtils.sha1Hex(clave);

        if (validations.campoVacio(vistaLogin.jPasswordField) || validations.campoVacio(vistaLogin.jTextField1)) {
            user.setUsername(vistaLogin.jTextField1.getText());
            user.setPassword_user(clave);

            String result = loginQueries.login(user, vistaLogin);
            switch (result) {
                case "success":
                    vistaLogin.dispose();

                    VistaCliente home = new VistaCliente();
                    VistaAdmin home2 = new VistaAdmin();
                    home2.setVisible(true);
                    break;
                case "error.incorrect_user":
                    modal.error_message("Error.", "Algo anda mal.", "El usuario no existe.", "Por favor intenta con otros datos.", null);
                    break;
                case "error.incorrect_password":
                    modal.error_message("Error.", "Algo anda mal.", "La contraseña no coincide con el usuario.", "Por Favor intenta con otros datos.", null);
                    break;
                case "error.server":
                    modal.error_message("Error.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", "O reportanos que ocurre.");
                    break;
            }

        } else {
            modal.error_message("Error", "Campos obligatorios", "Debes llenar todos los campos", null, null);
        }

    }

}
