/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uvfood;

import java.sql.Connection;
import classes.ConexionBD;
import components.UVFoodDialogs;
import views.Index;

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
        Index index = new Index();
        
        index.setVisible(true);
        
    }

}
