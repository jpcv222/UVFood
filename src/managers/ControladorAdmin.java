/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.ConsultasAdmin;
import classes.Usuario;
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
import classes.Logs;
import classes.KeyValidate;
import components.UVFoodDialogs;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorAdmin implements ActionListener{

    private VistaAdmin interfazPrincipalAdmin;
    private FileChooser file = new FileChooser();
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    private KeyValidate keyvalidate = new KeyValidate();
    private UVFoodDialogs modal = new UVFoodDialogs();
    private ConsultasAdmin consultasAdmin;

    //private Cliente modeloCliente;
    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
        //this.interfazPrincipalAdmin.jButtonCargar.addActionListener(this);
    }

    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin, ConsultasAdmin consultasAdmin) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
        this.consultasAdmin = consultasAdmin;
        this.interfazPrincipalAdmin.btnConsultaUser.addActionListener(this);
    }

    public void selectFile(String tipoCarga) {
        if (keyvalidate.haveKey("action-method-name", "user-id")) {
            file.calcularRutaArchivo();
            String ruta = null;
            ruta = file.getRuta();
            if (ruta != null) {
                interfazPrincipalAdmin.jLabelRutaArchivo.setText(ruta);
            } else {
                interfazPrincipalAdmin.jLabelRutaArchivo.setText("Archivo CSV, ruta errónea");
            }
            validateBtCargar();
        } else {
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

    public void readCSVFile() {
        if (keyvalidate.haveKey("action-method-name", "user-id")) {
            String archCSV = null;
            archCSV = file.getRuta();
            try {
                CSVReader csvReader = new CSVReader(new FileReader(archCSV));
                String[] fila = null;
                while ((fila = csvReader.readNext()) != null) {
                    System.out.println(fila[0]
                            + " | " + fila[1]
                            + " |  " + fila[2]);
                }
                modal.succes_message("Carga masiva de usuarios.", "Éxito al cargar archivo.", "Los usuarios fueron cargados con éxito.", null, null);
            } catch (IOException ex) {
                logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            } catch (NoClassDefFoundError ex) {
                logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
                modal.error_message("Error fatal.", "Lectura archivo CSV errónea.", "Librería de lectura de archivo extraviada.", "Comuníquese con el área de sistemas.", null);
            } catch (NullPointerException ex) {
                logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
                modal.error_message("Carga masiva usuarios.", "Lectura archivo CSV errónea.", "El archivo CSV es null.", null, null);
            }
        } else {
            //Show error key message
        }
    }
    
    /*public boolean gestionBuscarUser(String gdato, VistaAdmin gvista){
        return consultasAdmin.buscarUser(gdato, gvista);        
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == interfazPrincipalAdmin.btnConsultaUser) {
            if (consultasAdmin.llenarTabla(interfazPrincipalAdmin)) {
                System.out.println("entro");
            }else{
                System.out.println("no entro");
            }    
            
        }
    }


    



}
