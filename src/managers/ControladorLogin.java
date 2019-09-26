/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Logs;
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

    private  VistaLogin vistaLogin;
    private final FormValidations validations;
    private Usuario user;
    private ConsultasLogin loginQueries;
    private final UVFoodDialogs modal;
    
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ControladorLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
        this.validations = new FormValidations();
        this.modal = new UVFoodDialogs();
        this.user = new Usuario();
        this.loginQueries = new ConsultasLogin();
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
                    define_view();
                    insert_session_record();
                    logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Usuario existente.");
                    break;
                case "error.incorrect_user":
                    modal.error_message("Error.", "Algo anda mal.", "El usuario no existe.", "Por favor intenta con otros datos.", null);
                     logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El usuario no existe.");
                    break;
                case "error.incorrect_password":
                    modal.error_message("Error.", "Algo anda mal.", "La contraseña no coincide con el usuario.", "Por Favor intenta con otros datos.", null);
                     logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// La contraseña no coincide con el usuario.");
                    break;
                case "error.server":
                    modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", "O reportanos que ocurre.");
                     logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                    break;
                case "error.unknow":
                    modal.error_message("Error.", "Algo anda mal.", "Error desconocido, ", "contacte al área de sistemas.", null);
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Error desconocido.");
                    break;
            }

        } else {
            modal.error_message("Error", "Campos obligatorios", "Debes llenar todos los campos", null, null);
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Campos obligatorios vacíos.");
        }
    }
    
    public void insert_session_record(){
         String result = loginQueries.insert_session_record(user.getIdUser());
             switch (result) {
            case "success.insert":
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Acceso a vista admin.");
                break;
            case "error.insert":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El registro de sesión no se realizó.");
                break;
            case "error.unknow":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Error desconocido.");
                break;
            case "error.server":
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                break;

        }
    }

    public void define_view() {

        String result = loginQueries.get_view(user);

        switch (result) {
            case "success.admin":
                vistaLogin.dispose();
                VistaAdmin home_admin = new VistaAdmin();
                home_admin.manager.user = this.user;
                home_admin.manager.set_init_conf();
                home_admin.setVisible(true);
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Acceso a vista admin.");
                break;
            case "success.cliente":
                vistaLogin.dispose();
                VistaCliente home_cliente = new VistaCliente();
                home_cliente.manager.user = this.user;
                home_cliente.jLabelNombreUser.setText("Bienvenido "+home_cliente.manager.user.getFirstname());
                home_cliente.setVisible(true);
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Acceso a vista cliente.");
                break;
            case "success.vendedor":
                modal.error_message("Error.", "Algo anda mal.", "Vista vendedor no disponible.", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Vista vendedor no disponible.");
                break;
            case "success.notfound":
                modal.error_message("Error.", "Algo anda mal.", "El rol no está definido.", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El rol no está definido.");
                break;
            case "error.notfound_typeuser":
                modal.error_message("Error.", "Algo anda mal.", "El registro no existe o está inactivo.", "Por favor intenta con otros datos.", null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El registro no existe o está inactivo.");
                break;
            case "error.unknow":
                modal.error_message("Error.", "Algo anda mal.", "Error desconocido, ", "contacte al área de sistemas.", null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Error desconocido.");
                break;
            case "error.server":
                modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", "No se puede cargar vista.");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                break;

        }

    }

}
