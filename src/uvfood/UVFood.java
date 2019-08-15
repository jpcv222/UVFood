/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uvfood;

import java.sql.Connection;
import javax.swing.JOptionPane;
import classes.ConexionBD;
import classes.ConsultasCliente;
import classes.Usuario;
import managers.ControladorCliente;
import views.VistaCliente;
import views.VistaLogin;
import views.index;

/**
 *
 * @author invitado
 */
public class UVFood extends ConexionBD {

    Connection miconexion = Conexion();

    public void CheckServer() {
        if (miconexion != null) {

            System.out.println("Conexion exitosa");
        } else {

            System.out.println("Server error");
        }

    }

    public static void main(String[] args) {
        UVFood server = new UVFood();
        server.CheckServer();
        
        /*
        llamadas a los constructores de los controladores pasandole los objetos previamente creados
        */
        Usuario usuario = new Usuario();
        VistaCliente vistaCliente = new VistaCliente();
        VistaLogin vistaLogin = new VistaLogin();
        ConsultasCliente consultasCliente = new ConsultasCliente();
        
        ControladorCliente controladorCliente = new ControladorCliente(vistaCliente, vistaLogin, consultasCliente, usuario);
        controladorCliente.iniciar();
        vistaLogin.setVisible(true);

    }

}
