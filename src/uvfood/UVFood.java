/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uvfood;

import java.sql.Connection;
import javax.swing.JOptionPane;
import classes.DBcontrol;
import views.index;

/**
 *
 * @author invitado
 */
public class UVFood extends DBcontrol {

    Connection miconexion = Conexion();

    public void CheckServer() {
        if (miconexion != null) {

            JOptionPane.showMessageDialog(null, "Succes");
        } else {

            JOptionPane.showMessageDialog(null, "Server error");
        }

    }

    public static void main(String[] args) {
        UVFood server = new UVFood();
        // TODO code application logic here

        // index view call
        // conexion DB call
        index miIndex = new index();
        miIndex.setVisible(true);
        
        server.CheckServer();
        

    }

}
