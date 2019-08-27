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
import classes.Logs;
import classes.Usuario;
import components.UVFoodDialogs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorAdmin implements ActionListener {

    private final VistaAdmin interfazPrincipalAdmin;
    private final FileManage file = new FileManage();
    private final KeyValidate keyvalidate = new KeyValidate();
    private final UVFoodDialogs modal = new UVFoodDialogs();
    private ConsultasAdmin consultasAdmin;
    private Usuario user;

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    //private Cliente modeloCliente;
    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
        //this.interfazPrincipalAdmin.jButtonCargar.addActionListener(this);
    }

    public void selectFile(String tipoCarga) {
        if (keyvalidate.haveKey("action-method-name", "user-id")) {

            String response = FileManage.selectFile(file.calcularRutaArchivo());
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
            String response = FileManage.readCSVFile(file.getRuta());

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

    public void guardarImg() {

            if (interfazPrincipalAdmin.nombreImg != null) {
                interfazPrincipalAdmin.btnGuardarImg.setEnabled(true);
                try {
                    //definimos el destino de la imagen
                    String dest = System.getProperty("user.dir") + "/src/ImgSlider/" + interfazPrincipalAdmin.nombreImg.getName();
                    Path destino = Paths.get(dest);

                    //defininimos el origen
                    String orig = interfazPrincipalAdmin.nombreImg.getPath();
                    Path origen = Paths.get(orig);

                    //copiamos el archivo
                    Files.copy(origen, destino, REPLACE_EXISTING);

                    System.out.println("archivo copiado con exito en: " + dest);

                } catch (IOException ex) {
                    logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
                    modal.error_message("Error fatal.", "Carga de imagen erronea.", "Intente con otra imagen o", "Comuníquese con el área de sistemas.", null);

                }

            } else {
                interfazPrincipalAdmin.btnGuardarImg.setEnabled(false);
            }

    }

    public boolean validarImg() {

        if (interfazPrincipalAdmin.nombreImg.getName().contains(".jpg")) {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(true);
            return true;
        } else if (interfazPrincipalAdmin.nombreImg.getName().contains(".png")) {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(true);
            return true;
        } else {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(false);
            return false;
        }

    }

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
