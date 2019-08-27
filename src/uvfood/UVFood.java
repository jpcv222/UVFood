/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uvfood;

import java.sql.Connection;
import classes.ConexionBD;
import classes.ConsultasAdmin;
import classes.ConsultasCliente;
import classes.Usuario;
import components.UVFoodDialogs;
import managers.ControladorAdmin;
import managers.ControladorCliente;
import views.Index;
import views.VistaAdmin;
import views.VistaCliente;
import views.VistaLogin;

/**
 *
 * @author invitado
 */
public class UVFood extends ConexionBD {

    Connection miconexion = Conexion();
    UVFoodDialogs modal = new UVFoodDialogs();

    public void CheckServer() {
        if (miconexion != null) {
            System.out.println("Conexion exitosa");
        } else {

            modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
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
        VistaAdmin vistaAdmin = new VistaAdmin();
        VistaLogin vistaLogin = new VistaLogin();
        Index index = new Index();
        
        ConsultasCliente consultasCliente = new ConsultasCliente();
        ConsultasAdmin consultasAdmin = new ConsultasAdmin();
        
        ControladorCliente controladorCliente = new ControladorCliente(vistaCliente, vistaLogin, consultasCliente, usuario);
        index.setVisible(true);
        
    }

}
