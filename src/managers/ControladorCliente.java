/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.FileManage;
import managers.queries.KeyValidate;
import classes.Usuario;
import managers.queries.ConsultasLogin;
import components.UVFoodDialogs;
import managers.queries.ConsultasCliente;
import validations.FormValidations;
import views.ConfirmMessage;
import views.VistaCliente;

/**
 *
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorCliente {

    private VistaCliente interfazPrincipalCliente;
    private FormValidations validaciones;
    private final KeyValidate keyvalidate;
    private UVFoodDialogs modal;
    private ConsultasCliente consultasCliente;
    public Usuario user;

    public ControladorCliente(VistaCliente vistaCliente) {
        this.interfazPrincipalCliente = vistaCliente;
        this.modal = new UVFoodDialogs();
        this.validaciones = new FormValidations();
        this.keyvalidate = new KeyValidate(modal);
    }
    
        public void requestFillTableShopping() {
        String namekey = "reports.generate.user.sessions";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        interfazPrincipalCliente.jLabelNoticeNotPermissions2.setVisible(!validate);
        interfazPrincipalCliente.jTextFieldBuscarUserSessions2.setEnabled(validate);
        if (validate) {
            if (!consultasCliente.llenarTablaSales(interfazPrincipalCliente)) {
                modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            }
        }
    }
    

}
