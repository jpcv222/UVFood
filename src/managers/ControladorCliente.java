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

    public void set_init_conf() {

        interfazPrincipalCliente.jLabelNombreUser.setText("Bienvenido al Servicio de Restaurante Universitario, " + user.getFirstname());
        requestTraerMenu();
        requestCountTicket();
        requestFillTableSales();
    }

    public void requestSearchUserSales() {
        if (!consultasCliente.buscarUserSales(interfazPrincipalCliente)) {
            modal.error_message("Error", "Algo anda mal", "No fue exitosa la busqueda", "Por Favor intenta mas tarde", "O reportanos que ocurre");
        }
    }

    public void requestFillTableSales() {
        String namekey = "reports.generate.user.sessions";
        System.out.println(user.getIdUser());
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        interfazPrincipalCliente.jLabelNoticeNotPermissions1.setVisible(!validate);
        interfazPrincipalCliente.jTextFieldBuscarUserSales.setEnabled(validate);
        if (validate) {
            if (!consultasCliente.llenarTablaSales(interfazPrincipalCliente)) {
                modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            }
        }
    }

    public void requestTraerMenu() {
        try {
            String image = consultasCliente.traerMenu();

            System.out.println(image);

            if (image.equals("error.img.no.encontrada")) {
                rsscalelabel.RSScaleLabel.setScaleLabel(interfazPrincipalCliente.jLabelMenuActual, System.getProperty("user.dir") + "/src/ImgMenu/menuNoDisponible.png");
            } else {
                File archivo = new File(image);
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
            interfazPrincipalCliente.jLabelTickets.setText("Su cantidad de tickets es: " + result);
        }
    }

}
