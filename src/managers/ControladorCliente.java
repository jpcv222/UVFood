/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.FileManage;
import classes.Logs;
import managers.queries.KeyValidate;
import classes.Usuario;
import managers.queries.ConsultasLogin;
import components.UVFoodDialogs;
import java.io.File;
import managers.queries.ConsultasCliente;
import validations.FormValidations;
import views.ConfirmMessage;
import views.VistaCliente;

/**
 *
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorCliente {

    private VistaCliente interfazPrincipalCliente;
    private FormValidations validaciones;
    private final KeyValidate keyvalidate;
    private UVFoodDialogs modal;
    private ConsultasCliente consultasCliente;
    public Usuario user;
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public ControladorCliente(VistaCliente vistaCliente) {
        this.consultasCliente = new ConsultasCliente();
        this.interfazPrincipalCliente = vistaCliente;
        this.modal = new UVFoodDialogs();
        this.validaciones = new FormValidations();
        this.keyvalidate = new KeyValidate(modal);

    }

    public void requestTraerMenu() {
        try {
            String image = consultasCliente.traerMenu();

            System.out.println(image);

            if (image.equals("error.img.no.encontrada")) {
                rsscalelabel.RSScaleLabel.setScaleLabel(interfazPrincipalCliente.jLabelMenuActual, System.getProperty("user.dir") + "/src/ImgMenu/menuNoDisponible.png");
            } else {
                File archivo = new File("image");
                if (!archivo.exists()) {
                    rsscalelabel.RSScaleLabel.setScaleLabel(interfazPrincipalCliente.jLabelMenuActual, System.getProperty("user.dir") + "/src/ImgMenu/menuNoDisponible.png");
                } else {
                    rsscalelabel.RSScaleLabel.setScaleLabel(interfazPrincipalCliente.jLabelMenuActual, System.getProperty("user.dir") + "/src/ImgMenu/" + image);
                }
            }

        } catch (NullPointerException e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + e.getMessage() + " " + e.toString());
        }

    }


    public void requestCountTicket() {
        String result = consultasCliente.countTicekt(interfazPrincipalCliente);
        System.out.println(result);

        if (result.equals("error.consulta")) {
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// error.consulta ");
        } else {
            interfazPrincipalCliente.jLabelTickets.setText("Su cantidad de tickets es: "+result);
        }
    }

}
