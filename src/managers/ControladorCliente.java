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

}
