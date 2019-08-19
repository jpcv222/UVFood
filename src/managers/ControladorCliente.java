/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Usuario;
import classes.ConsultasCliente;
import components.UVFoodDialogs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.Objects.hash;
import org.apache.commons.codec.digest.DigestUtils;
import validations.Validations;
import views.VistaCliente;
import views.VistaLogin;

/**
 *
 * @author Jeffrey Rios
 */
public class ControladorCliente implements ActionListener {

    private VistaCliente vistaCliente;
    private VistaLogin vistaLogin;
    private ConsultasCliente consultasCliente;
    private Usuario modeloCliente;
    Validations validations = new Validations();
    UVFoodDialogs modal = new UVFoodDialogs();

    public ControladorCliente(VistaCliente vistaCliente, VistaLogin vistaLogin, ConsultasCliente consultasCliente, Usuario modeloCliente) {
        this.vistaCliente = vistaCliente;
        this.vistaLogin = vistaLogin;
        this.consultasCliente = consultasCliente;
        this.modeloCliente = modeloCliente;
        this.vistaLogin.jButtonIniciarSesion.addActionListener(this);
    }

    public void iniciar() {
        vistaLogin.setTitle("Login");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.jButtonIniciarSesion) {
            String clave = new String(vistaLogin.jPasswordField.getPassword());
            String claveEn = DigestUtils.sha1Hex(clave);

            if (validations.campoVacio(vistaLogin.jPasswordField) && validations.campoVacio(vistaLogin.jTextField1)) {
                modeloCliente.setUsername(vistaLogin.jTextField1.getText());
                modeloCliente.setPassword_user(clave);

                if (consultasCliente.login(modeloCliente, vistaLogin)) {
                    vistaLogin.dispose();

                    VistaCliente home = new VistaCliente();
                    home.setVisible(true);
                }

            }else{
                modal.error_message("Error", "Campos obligatorios", "Debes llenar todos los campos", null, null);
            }

        }
    }

}
