/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.FileChooser;
import views.VistaAdmin;

/**
 *
 * @author jpcv2
 */
public class ControladorAdmin {
    
     views.VistaAdmin interfazPrincipalAdmin;
    
    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
    }
    
    public void selectFile(){
         FileChooser file = new FileChooser();
         file.calcularRutaArchivo();
         String ruta = file.getRuta();
         if(ruta != null){
         interfazPrincipalAdmin.jLabelRutaArchivo.setText(ruta);
         } else {
         interfazPrincipalAdmin.jLabelRutaArchivo.setText("Archivo CSV");
         }
    }
    
    public void validateBtCargar(){
        String value = interfazPrincipalAdmin.jLabelRutaArchivo.getText();
        if((!value.equals("Archivo CSV")) && value.contains(".csv")){
            interfazPrincipalAdmin.jButtonCargar.setEnabled(true);
        }else{
            interfazPrincipalAdmin.jButtonCargar.setEnabled(false);
        }
    }
}
