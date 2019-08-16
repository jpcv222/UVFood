/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author Juan Pablo Castro    2019
 * GitHub: jpcv222
 */
public class FileChooser {
    
    String ruta;
    private Logs logs = new Logs();
    
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
            logs.escribirExceptionLogs( "//FileChooser//calcularRutaArchivo// " + e.getMessage());
        } catch (Exception ex) {
            logs.escribirExceptionLogs( "//FileChooser//calcularRutaArchivo// " + ex.getMessage());
        } 
        
        setRuta(ruta);
    }

}
