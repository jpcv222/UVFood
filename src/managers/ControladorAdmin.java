/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.ConsultasAdmin;
import classes.FileManage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.VistaAdmin;
import classes.KeyValidate;
import components.UVFoodDialogs;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorAdmin implements ActionListener {

    private VistaAdmin interfazPrincipalAdmin;
    private FileManage file = new FileManage();
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

            String response = file.selectFile(file.calcularRutaArchivo());
            interfazPrincipalAdmin.jLabelRutaArchivo.setText(response);

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
            String response = file.readCSVFile(file.getRuta());

            switch (response) {
                case "success":
                    modal.success_message("Carga masiva de usuarios.", "Éxito al cargar archivo.", "Los usuarios fueron cargados con éxito.", null, null);
                    break;
                case "error.noclassdeffounderror":
                    modal.error_message("Error fatal.", "Lectura archivo CSV errónea.", "Librería de lectura de archivo extraviada.", "Comuníquese con el área de sistemas.", null);
                    break;
                case "error.nullpointerexception":
                    modal.error_message("Carga masiva usuarios.", "Lectura archivo CSV errónea.", "El archivo CSV es null.", null, null);
                    break;
                case "error.unknow":
                    modal.error_message("Carga masiva usuarios.", "Lectura archivo CSV errónea.", "Error desconocido.", "Comuníquese con el área de sistemas.", null);
                    break;
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
            } else {
                System.out.println("no entro");
            }

        }
    }

}
