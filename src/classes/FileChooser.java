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
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

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
        String result_ruta = null;
        try {
             result_ruta = fileChooser.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {
            logs.escribirExceptionLogs( logs.getClassName() + "//"+ Thread.currentThread().getStackTrace()[1].getMethodName() +"// "+  e.getMessage()+" "+e.toString());
        }
        
        setRuta(result_ruta);
    }

}
