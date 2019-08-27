/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.KeyValidate;
import classes.Usuario;
import managers.queries.ConsultasLogin;
import components.UVFoodDialogs;
import validations.FormValidations;
import views.VistaCliente;
/**
 *
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorCliente {

    private final VistaCliente vistaCliente;
    private final ConsultasLogin consultasCliente;
    private final Usuario modeloCliente;
    private final KeyValidate keyvalidate;
    private final FormValidations validations;
    private final UVFoodDialogs modal;

    public ControladorCliente(VistaCliente vistaCliente, ConsultasLogin consultasCliente, Usuario modeloCliente) {
        this.vistaCliente = vistaCliente;
        this.consultasCliente = consultasCliente;
        this.modeloCliente = modeloCliente;
        this.modal = new UVFoodDialogs();
        this.validations = new FormValidations();
        this.keyvalidate = new KeyValidate();
    }

}
