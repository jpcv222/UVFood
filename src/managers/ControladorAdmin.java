/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.Cliente;
import classes.FileChooser;
import com.opencsv.CSVReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import views.VistaAdmin;
import classes.Ficheros;
import classes.KeyValidate;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 */
public class ControladorAdmin implements ActionListener {

    private VistaAdmin interfazPrincipalAdmin;
    private FileChooser file = new FileChooser();
    private Ficheros logs = new Ficheros();
    private KeyValidate keyvalidate = new KeyValidate();
    //private Cliente modeloCliente;

    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
        //this.interfazPrincipalAdmin.jButtonCargar.addActionListener(this);
    }

    public void selectFile(String tipoCarga) {
        if(keyvalidate.haveKey("action-method-name","user-id")){
        file.calcularRutaArchivo();
        String ruta = null;
        ruta = file.getRuta();
        if (ruta != null) {
            interfazPrincipalAdmin.jLabelRutaArchivo.setText(ruta);
        } else {
            interfazPrincipalAdmin.jLabelRutaArchivo.setText("Archivo CSV");
        }
        validateBtCargar(); 
        }else{
        //Show error key message
        }
    }

    public void validateBtCargar() {
        String value = interfazPrincipalAdmin.jLabelRutaArchivo.getText();
        if ((!value.equals("Archivo CSV")) && value.contains(".csv")) {
            interfazPrincipalAdmin.jButtonCargar.setEnabled(true);
        } else {
            interfazPrincipalAdmin.jButtonCargar.setEnabled(false);
        }
    }

    public void readCSVFile(String url_file) {
        String archCSV = url_file;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(archCSV));
            String[] fila = null;
            while ((fila = csvReader.readNext()) != null) {
                System.out.println(fila[0]
                        + " | " + fila[1]
                        + " |  " + fila[2]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
            logs.escribirExceptionLogs( "//ControladorAdmin//readCSVFile// " + ex.getMessage());
        } catch (IOException   ioe){
            Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ioe);
            logs.escribirExceptionLogs( "//ControladorAdmin//readCSVFile// " + ioe.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == interfazPrincipalAdmin.jButtonCargar) {
            readCSVFile(file.getRuta());
        }
    }

}