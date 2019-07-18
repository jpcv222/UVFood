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
public class UVFood {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // index view call
        // conexion DB call
        
                index miIndex = new index();
        miIndex.setVisible(true);
        
          
           Connection miconexion;
        miconexion=DBcontrol.GetConnection();
        
        
        if(miconexion !=null){
            
            JOptionPane.showMessageDialog(null,"Succes");
    } else {
            
            JOptionPane.showMessageDialog(null,"Server error");
        }
    
}
    
}
