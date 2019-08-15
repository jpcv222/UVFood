/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author jpcv2
 */
public class FileChooser {
    
    String ruta;
    
    public FileChooser() {
        ruta = null;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public void calcularRutaArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        String ruta = null;
        try {
             ruta = fileChooser.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
            
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        
        setRuta(ruta);
    }

}
