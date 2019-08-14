/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Cliente;
import classes.ConsultasCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.Objects.hash;
import org.apache.commons.codec.digest.DigestUtils;
import views.VistaCliente;
import views.VistaLogin;

/**
 *
 * @author Jeffrey Rios
 */
/*public class ControladorCliente implements ActionListener{
    
    private VistaCliente vistaCliente;
    private VistaLogin vistaLogin;
    private ConsultasCliente consultasCliente;
    private Cliente modeloCliente;

    public ControladorCliente(VistaCliente vistaCliente, VistaLogin vistaLogin, ConsultasCliente consultasCliente, Cliente cliente) {
        this.vistaCliente = vistaCliente;
        this.vistaLogin = vistaLogin;
        this.consultasCliente = consultasCliente;
        this.cliente = cliente;
        this.vistaLogin.jButtonIniciarSesion.addActionListener(this);
    }
    
    public void iniciar(){
        vistaLogin.setTitle("Login");
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.jButtonIniciarSesion) {
            String clave = new String(vistaLogin.jPasswordField.getPassword());
            String claveEn = DigestUtils.sha1Hex(clave);
            
            modeloCliente.setUsuario(vistaLogin.jTextField1.getText());
            modeloCliente.setClave(claveEn);
            
            if(consultasCliente.login(modeloCliente)){
                this.dispose();
            }else{
                System.out.println("datos incorrectos");
            }
            
        }
    }
    
}*/
