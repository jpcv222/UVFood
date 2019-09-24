/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import managers.queries.ConsultasAdmin;
import classes.FileManage;
import views.VistaAdmin;
import managers.queries.KeyValidate;
import classes.Logs;
import classes.Usuario;
import components.UVFoodDialogs;
import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import views.GestionPermisos;
import validations.FormValidations;
import views.ConfirmMessage;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorAdmin {

    private VistaAdmin interfazPrincipalAdmin;
    private FormValidations validaciones;
    private final FileManage file;
    private final KeyValidate keyvalidate;
    private UVFoodDialogs modal;
    private ConsultasAdmin consultasAdmin;
    private ConfirmMessage confirmation_message;
    public Usuario user;

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    //private Cliente modeloCliente;
    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin, ConfirmMessage confirmation_message) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
        this.modal = new UVFoodDialogs();
        this.keyvalidate = new KeyValidate(modal);
        this.file = new FileManage();
        this.confirmation_message = confirmation_message;
        this.consultasAdmin = new ConsultasAdmin();
        this.validaciones = new FormValidations();

    }

    public void set_init_conf() {
        String current_text = interfazPrincipalAdmin.jLabelBienvenida.getText();
        this.interfazPrincipalAdmin.jLabelBienvenida.setText(current_text + user.getFirstname());
        createIndexView();
    }

    public void showPermissionsView(int row) {
        String namekey = "permissions.show.view.asign";

        try {

            String username = interfazPrincipalAdmin.jTableUsers.getValueAt(row, 1).toString();
            String firstname = interfazPrincipalAdmin.jTableUsers.getValueAt(row, 2).toString();
            String surname = interfazPrincipalAdmin.jTableUsers.getValueAt(row, 3).toString();

            String result = keyvalidate.haveKey(namekey, user.getIdUser());
            boolean validate = keyvalidate.resultHaveKey(result);
            if (validate) {
                show_permissions_view(username, firstname, surname);
            } else {
                modal.error_message("Error de validación.", "Permisos denegados.", "El rol actual no tiene accesos a esta opción.", null, null);
            }
        } catch (Exception ex) {
            modal.error_message("Error fatal.", "Error en servidor.", "Se ha generado un error inesperado.", null, null);
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
        }
    }

    public void show_permissions_view(String username, String firstname, String surname) {
        GestionPermisos view_permissions = new GestionPermisos();
        view_permissions.manager.setUser(user);
        view_permissions.jLabelUserNamePerm1.setText(firstname + " " + surname);
        view_permissions.jLabelUserName.setText(username);
        view_permissions.manager.set_init_conf();
        view_permissions.setVisible(true);

    }

    public void selectFile() {
        String namekey = "users.select.csv";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            String response = FileManage.selectFile(file.calcularRutaArchivo());
            interfazPrincipalAdmin.jLabelRutaArchivo.setText(response);
            validateBtCargar();
        }

    }

    public void createIndexView() {
        createJFreeChartCount("index.show.count.users", "user", "iduser");
        createJFreeChartCount("index.show.count.sessions", "sessions", "idsession");
    }

    public void createJFreeChartCount(String namekey, String table, String atrib) {
        try {
            String result = keyvalidate.haveKey(namekey, user.getIdUser());
            boolean validate = keyvalidate.resultHaveKey(result);
            if (validate) {
                int data_response;
                data_response = consultasAdmin.get_count_record(table, atrib);
                switch (data_response) {
                    case -99:
                        modal.error_message("Warning base de datos.", "Algo anda mal.", "La consulta no arrojó resultados.", null, null);
                        logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
                        break;
                    case -999:
                        modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", null);
                        logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// El servidor está presentado problemas.");
                        break;
                    default:
                        logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Se genera gráfica con " + data_response + " registros.");
                        generateGraph(namekey, data_response);
                        break;
                }
            }

        } catch (Exception ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", null);
        }

    }

    public void jFreeChartUsers(int users) {
        try {
            interfazPrincipalAdmin.jlUsersGraph.setText(String.valueOf(users));

            DefaultCategoryDataset data = new DefaultCategoryDataset();
            data.addValue(users, "Todos los roles", "");

            JFreeChart grafica = ChartFactory.createBarChart3D("Usuarios", "", "Total", data, PlotOrientation.VERTICAL, true, true, false);

            ChartPanel contenedor = new ChartPanel(grafica);
            interfazPrincipalAdmin.P_GraficaSessions.removeAll();
            interfazPrincipalAdmin.P_GraficaUsers.add(contenedor, BorderLayout.CENTER);
            interfazPrincipalAdmin.P_GraficaUsers.validate();
        } catch (Exception ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", null);
        }
    }

    public void jFreeChartSessions(int sessions) {
        try {
            interfazPrincipalAdmin.jlSessionsGraph.setText(String.valueOf(sessions));

            DefaultCategoryDataset data = new DefaultCategoryDataset();
            data.addValue(sessions, "Todas las sesiones", "");

            JFreeChart grafica = ChartFactory.createBarChart3D("Sesiones totales", "", "Total", data, PlotOrientation.VERTICAL, true, true, false);

            ChartPanel contenedor = new ChartPanel(grafica);
            interfazPrincipalAdmin.P_GraficaSessions.removeAll();
            interfazPrincipalAdmin.P_GraficaSessions.add(contenedor, BorderLayout.CENTER);
            interfazPrincipalAdmin.P_GraficaSessions.validate();
        } catch (Exception ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            modal.error_message("Error fatal.", "Algo anda mal.", "El servidor está presentado problemas.", "Por Favor intenta mas tarde.", null);
        }
    }

    public void generateGraph(String namekey, int records) {

        switch (namekey) {
            case "index.show.count.users":
                jFreeChartUsers(records);
                break;
            case "index.show.count.sessions":
                jFreeChartSessions(records);
                break;
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
        String namekey = "users.upload.csv";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            String response = FileManage.readCSVFile(file.getRuta());
            resultUpload(response, "usuarios");
        }

    }

    public void resultUpload(String response, String type) {
        try {
            switch (response) {
                case "success":
                    modal.success_message("Carga masiva de " + type + ".", "Éxito al cargar archivo.", "Los " + type + " fueron cargados con éxito.", null, null);
                    logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Los " + type + " fueron cargados con éxito.");
                    break;
                case "error.noclassdeffounderror":
                    modal.error_message("Carga masiva de " + type + ".", "Error fatal.", "Librería de lectura de archivo extraviada.", "Comuníquese con el área de sistemas.", null);
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Librería para carga CSV no encontrada.");
                    break;
                case "error.nullpointerexception":
                    modal.error_message("Carga masiva de " + type + ".", "Lectura archivo CSV errónea.", "El archivo CSV es null.", null, null);
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Archivo CSV erróneo.");
                    break;
                case "error.unknow":
                    modal.error_message("Carga masiva de " + type + ".", "Lectura archivo CSV errónea.", "Error desconocido.", "Comuníquese con el área de sistemas.", null);
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Error desconocidoa.");
                    break;
                default:
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                    break;
            }

        } catch (NullPointerException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
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
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Carga de imagen a /src/ImgSlider errónea");
                modal.error_message("Error fatal.", "Carga de imagen erronea.", "Intente con otra imagen o", "Comuníquese con el área de sistemas.", null);

            }

        } else {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(false);
        }

    }

    public void createPopupmenu() {
        try {

            Point punto = MouseInfo.getPointerInfo().getLocation();
            int x = punto.x;
            int y = punto.y;

            interfazPrincipalAdmin.popup.removeAll();
            // New project menu item
            JMenuItem menuItem = new JMenuItem("Permisos...",
                    new ImageIcon("src/images/house-key.png"));
            menuItem.setMnemonic(KeyEvent.VK_P);
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Asignar permisos a este usuario.");

            interfazPrincipalAdmin.popup.add(menuItem);
            interfazPrincipalAdmin.popup.setVisible(true);
            interfazPrincipalAdmin.popup.setLocation(x, y);
        } catch (Exception ex) {
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Error creando popupmenu.");
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
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

    public void requestSearchUser() {
        if (!consultasAdmin.buscarUser(interfazPrincipalAdmin)) {
            modal.error_message("Error", "Algo anda mal", "No fue exitosa la busqueda", "Por Favor intenta mas tarde", "O reportanos que ocurre");
        }
    }

    public void requestFillTable() {
        if (!consultasAdmin.llenarTabla(interfazPrincipalAdmin)) {
            modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
        }
    }

    public void requestFillTableSessions() {
        String namekey = "reports.generate.user.sessions";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        interfazPrincipalAdmin.jLabelNoticeNotPermissions.setVisible(!validate);
        interfazPrincipalAdmin.jTextFieldBuscarUserSessions.setEnabled(validate);
        if (validate) {
            if (!consultasAdmin.llenarTablaSessions(interfazPrincipalAdmin)) {
                modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            }
        }
    }

    public void requestFillTableUsersToTickets() {
        String namekey = "sales.generate.user.graph";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        interfazPrincipalAdmin.jTextFieldBuscarUserToTicket.setEnabled(validate);
        if (validate) {
            if (!consultasAdmin.llenarTablaUsersToTickets(interfazPrincipalAdmin)) {
                modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            }
        }
    }

    public void requestFillFields() {
        if (!consultasAdmin.llenarAcciones(interfazPrincipalAdmin)) {
            modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
        }
    }

    public void requestFillFieldsSales() {
        String namekey = "sales.generate.user.sale";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            try {
                int fila = interfazPrincipalAdmin.jTableUsersToTickets.getSelectedRow();
                String username = interfazPrincipalAdmin.jTableUsersToTickets.getValueAt(fila, 1).toString();
                interfazPrincipalAdmin.jLabelUsernameSales.setText(username);
            } catch (Exception ai) {
                logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
                modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            }
        }
    }

    public void vender() {
        String namekey = "sales.generate.user.sale";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            if (consultasAdmin.insertSale(interfazPrincipalAdmin)) {
                modal.success_message("Exito.", "Venta realizada.", "La venta se ha realizado con exito", "", "");
            } else {
                modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");

            }
        }
    }
    
    public void consumptionTicket(int row) {
        String namekey = "sales.generate.user.sale";
        String result = keyvalidate.haveKey(namekey, user.getIdUser());
        boolean validate = keyvalidate.resultHaveKey(result);
        if (validate) {
            if (consultasAdmin.insertConsumption(interfazPrincipalAdmin, row)) {
                modal.success_message("Exito.", "Consumo de ticket realizado.", "El ticket se descontará.", "", "");
            } else {
                modal.error_message("Error", "Viola restricción.", "No se realizó consumo de ticket.", "Por Favor intenta más tarde.", "Ticket diario consumido.");

            }
        }
    }

    public void requestFillCombo() {
        if (!consultasAdmin.fillCombo(interfazPrincipalAdmin)) {
            modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
        }
    }

    public void requestInsertUser() {

        String result = consultasAdmin.crearUsuario(interfazPrincipalAdmin);

        switch (result) {
            case "error.usuario.existe":
                modal.error_message("Error", "Algo anda mal", "El usuario ya esta registrado", "Por Favor intenta con otro", "");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error.usuario ya existe/ ");
                break;
            case "error.email.existe":
                modal.error_message("Error", "Algo anda mal", "El email ya esta registrado", "Por Favor intenta con otro", "");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error.email ya existe/ ");
                break;
            case "error.dato.no.insertado":
                modal.error_message("Error", "Algo anda mal", "Ocurrio un error al registrar", "Por Favor verifica los datos", "O reportanos que ocurre");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error./ ");
                break;
            case "success.dato.insertado":
                modal.success_message("Exito", "", "El usuario se registro con exito", "", "");
                limpiarCampos();
                break;
            case "error.NP.error":
                modal.error_message("Error", "Algo anda mal", "Ocurrio un error al registrar", "Es posible que los campos", "tengan formato incorrecto.");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error./ validando campos ");
                break;
            case "error.sql.error":
                modal.error_message("Error", "Algo anda mal", "Ocurrio un error al registrar", "Error en el servidor.", "Consulte al área de sistemas.");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error./ validando campos ");
                break;
            default:
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                break;

        }

    }

    public void requestValidationsInsertUser() {
        String validation = validaciones.validarInsert(interfazPrincipalAdmin);

        switch (validation) {
            case "error.emptyField":
                modal.error_message("Error.", "Todos los campos son obligatorios.", "", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//Error de campos obligatorios");
                break;
            case "error.email":
                modal.error_message("Error.", "El email ingresado no es valido", "Example@Example.com", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//Error de email incorrecto");
                break;
            case "error.date":
                modal.error_message("Error.", "la fecha ingresada no es valida", "yyyy-MM-dd", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//fecha ingresada no es valida");
                break;
            case "success":
                requestInsertUser();
                break;
            default:
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                break;

        }

    }

    public void requestUpdateUser() {

        String result = consultasAdmin.updateUser(interfazPrincipalAdmin);

        switch (result) {
            case "error.usuario.existe":
                modal.error_message("Error", "Algo anda mal", "El usuario ya esta registrado", "Por Favor intenta con otro", "");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error.usuario ya existe/ ");
                break;
            case "error.email.existe":
                modal.error_message("Error", "Algo anda mal", "El email ya esta registrado", "Por Favor intenta con otro", "");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error.email ya existe/ ");
                break;
            case "error.dato.no.actualizado":
                modal.error_message("Error", "Algo anda mal", "Ocurrio un error al actualizar", "Por Favor verifica los datos", "O reportanos que ocurre");
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error./ ");
                break;
            case "success.dato.actualizado":
                modal.success_message("Exito", "", "El usuario se actualizo con exito", "", "");
                logs.escribirAccessLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + " Se realiza actualización de usuario con éxito.");
                limpiarCampos();
                break;
            default:
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                break;

        }

    }

    public void requestValidationsUpdateUser() {
        String validation = validaciones.validarUpdate(interfazPrincipalAdmin);

        switch (validation) {
            case "error.emptyField":
                modal.error_message("Error.", "Todos los campos son obligatorios.", "", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//Error de campos obligatorios");
                break;
            case "error.email":
                modal.error_message("Error.", "El email ingresado no es valido", "Example@Example.com", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//Error de email incorrecto");
                break;
            case "error.date":
                modal.error_message("Error.", "la fecha ingresada no es valida", "yyyy-MM-dd", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//fecha ingresada no es valida");
                break;
            case "success":
                requestUpdateUser();
                break;
            default:
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                break;

        }

    }

    public void hablitarEdicionTotal() {
        switch (VistaAdmin.sePuede) {
            case "soloEliminar":
                interfazPrincipalAdmin.btnCrearUser.setEnabled(false);
                interfazPrincipalAdmin.btnModificarUser.setEnabled(false);
                interfazPrincipalAdmin.btnEliminarUser.setEnabled(true);

                interfazPrincipalAdmin.btnHabilitarEdicion.setEnabled(true);
                interfazPrincipalAdmin.btnCrearUser.setEnabled(false);
                desHablitarEdicion();

                break;
            case "eliminar_modificar":
                interfazPrincipalAdmin.btnCrearUser.setEnabled(false);
                interfazPrincipalAdmin.btnModificarUser.setEnabled(true);
                interfazPrincipalAdmin.btnEliminarUser.setEnabled(true);

                interfazPrincipalAdmin.btnHabilitarEdicion.setEnabled(true);
                HablitarEdicion();
                break;
            case "solo_crear":
                //interfazPrincipalAdmin.jComboBoxRoles.removeAllItems();
                requestFillCombo();
                interfazPrincipalAdmin.btnCrearUser.setEnabled(true);
                interfazPrincipalAdmin.btnModificarUser.setEnabled(false);
                interfazPrincipalAdmin.btnEliminarUser.setEnabled(false);

                interfazPrincipalAdmin.jTextFieldRol.removeAll();
                interfazPrincipalAdmin.jTextFieldRol.repaint();
                interfazPrincipalAdmin.jTextFieldRol.revalidate();

                interfazPrincipalAdmin.btnHabilitarEdicion.setEnabled(false);
                interfazPrincipalAdmin.jTextFieldRol.setEditable(false);

                break;
            case "solo_crear_limpiar":
                interfazPrincipalAdmin.btnCrearUser.setEnabled(true);
                interfazPrincipalAdmin.btnModificarUser.setEnabled(false);
                interfazPrincipalAdmin.btnEliminarUser.setEnabled(false);

                interfazPrincipalAdmin.jTextFieldRol.removeAll();
                interfazPrincipalAdmin.jTextFieldRol.repaint();
                interfazPrincipalAdmin.jTextFieldRol.revalidate();

                interfazPrincipalAdmin.btnHabilitarEdicion.setEnabled(false);
                interfazPrincipalAdmin.jTextFieldRol.setEditable(false);

                HablitarEdicion();

                break;
            case "soloHabilitar":
                interfazPrincipalAdmin.btnHabilitarEdicion.setEnabled(true);
                interfazPrincipalAdmin.btnCrearUser.setEnabled(false);
                interfazPrincipalAdmin.btnEliminarUser.setEnabled(false);
                break;
            default:
                break;
        }

    }

    public void desHablitarEdicion() {
        interfazPrincipalAdmin.jTextFieldApellido.setEditable(false);
        interfazPrincipalAdmin.jTextFieldEmail.setEditable(false);
        interfazPrincipalAdmin.jTextFieldFecNa.setEditable(false);
        interfazPrincipalAdmin.jTextFieldName.setEditable(false);
        interfazPrincipalAdmin.jTextFieldRol.setEditable(false);
        interfazPrincipalAdmin.jTextFieldUser.setEditable(false);
        interfazPrincipalAdmin.jPasswordField.setEditable(false);
        interfazPrincipalAdmin.jComboBoxRoles.setEnabled(false);

    }

    public void desHablitarEdicionBtn() {
        interfazPrincipalAdmin.btnCrearUser.setEnabled(true);
        interfazPrincipalAdmin.btnModificarUser.setEnabled(false);
        interfazPrincipalAdmin.btnEliminarUser.setEnabled(false);
        interfazPrincipalAdmin.jTextFieldRol.setEditable(false);
        interfazPrincipalAdmin.btnHabilitarEdicion.setEnabled(false);

    }

    public void HablitarEdicion() {
        interfazPrincipalAdmin.jTextFieldApellido.setEditable(true);
        interfazPrincipalAdmin.jTextFieldEmail.setEditable(true);
        interfazPrincipalAdmin.jTextFieldFecNa.setEditable(true);
        interfazPrincipalAdmin.jTextFieldName.setEditable(true);
        interfazPrincipalAdmin.jTextFieldUser.setEditable(true);
        interfazPrincipalAdmin.jPasswordField.setEditable(true);
        interfazPrincipalAdmin.jComboBoxRoles.setEnabled(true);

    }

    public void limpiarCampos() {
        interfazPrincipalAdmin.jTextFieldApellido.setText("");
        interfazPrincipalAdmin.jTextFieldEmail.setText("");
        interfazPrincipalAdmin.jTextFieldFecNa.setText("");
        interfazPrincipalAdmin.jTextFieldName.setText("");
        interfazPrincipalAdmin.jTextFieldRol.setText("");
        interfazPrincipalAdmin.jTextFieldUser.setText("");
        interfazPrincipalAdmin.jTextFieldIdRol.setText("");
        interfazPrincipalAdmin.jTextFieldIdUser.setText("");
    }

    public void limpiarCamposSales() {
        interfazPrincipalAdmin.jTextFieldCantidadTickets.setText("0");
        interfazPrincipalAdmin.jTextFieldEfectivo.setText("0");
        interfazPrincipalAdmin.jTextFieldCambio.setText("0");
        interfazPrincipalAdmin.jTextFieldTotalVenta.setText("0");
    }

    public void requestDisableUser() {
        String res = consultasAdmin.disableUser(interfazPrincipalAdmin);

        switch (res) {
            case "success":
                modal.success_message("Éxito", "", "El usuario fue deshabilitado con exito", "", "");
                break;
            case "error":
                modal.error_message("Error", "", "Intentalo de nuevo por favor", "", "");
                break;
            default:
                modal.error_message("Error", "Servidor no envía respuesta", "Intentalo de nuevo por favor", "", "");
                break;

        }

    }

    public void calculatePrice() {
        String value = interfazPrincipalAdmin.jTextFieldCantidadTickets.getText();
        if (validaciones.isNumeric(value)) {
            if (validaciones.isNegativeOrZero(value)) {
                int price_ticket = calculatePriceTicket();
                int price = Integer.parseInt(value) * price_ticket;
                interfazPrincipalAdmin.jTextFieldTotalVenta.setText(String.valueOf(price));
            }
        }

    }

    public void calculateCashChange() {
        String value = interfazPrincipalAdmin.jTextFieldEfectivo.getText();
        if (validaciones.isNumeric(value)) {

            if (validaciones.isNegativeOrZero(value)) {
                int venta = Integer.parseInt(interfazPrincipalAdmin.jTextFieldTotalVenta.getText());
                int cash = Integer.parseInt(value);
                if (cash >= venta) {
                    int cash_change = cash - venta;
                    interfazPrincipalAdmin.jTextFieldCambio.setText(String.valueOf(cash_change));
                }
            }
        }

    }

    public void validateBtFacturar() {
        String cash_change = interfazPrincipalAdmin.jTextFieldCambio.getText();
        String tickets = interfazPrincipalAdmin.jTextFieldCantidadTickets.getText();
        String price = interfazPrincipalAdmin.jTextFieldTotalVenta.getText();
        String efectivo = interfazPrincipalAdmin.jTextFieldEfectivo.getText();
        if (validaciones.isNumeric(cash_change) && validaciones.isNumeric(tickets) && validaciones.isNumeric(price) && validaciones.isNumeric(efectivo)) {
            int cash_change_aux = Integer.parseInt(cash_change);
            int tickets_aux = Integer.parseInt(tickets);
            int price_aux = Integer.parseInt(price);
            int efectivo_aux = Integer.parseInt(efectivo);

            interfazPrincipalAdmin.btnFacturar.setEnabled(cash_change_aux >= 0 && tickets_aux >= 0 && efectivo_aux >= price_aux);

        }
    }

    public void validateCantTickets() {
        String value = interfazPrincipalAdmin.jTextFieldCantidadTickets.getText();
        interfazPrincipalAdmin.jTextFieldEfectivo.setEnabled(validaciones.isNumeric(value) && validaciones.isNegativeOrZero(value));
    }

    public int calculatePriceTicket() {

        int result = 2100;

        try {
            int fila = interfazPrincipalAdmin.jTableUsersToTickets.getSelectedRow();
            int discount = (int) interfazPrincipalAdmin.jTableUsersToTickets.getValueAt(fila, 5);
            result = result - discount;
        } catch (Exception ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
            //modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");

        }
        return result;
    }

    public void validateDisableUser() {
        modal.confirmation_message("Confirmacion", "¿Desea deshabilitar este usuario?");
        if (modal.confirmation_message.confirm_action) {
            requestDisableUser();
            interfazPrincipalAdmin.btnhabilitarUser.setEnabled(true);
            interfazPrincipalAdmin.btnEliminarUser.setEnabled(false);
        }
    }

    public void requestEnableUser() {
        consultasAdmin.enableUser(interfazPrincipalAdmin);
        interfazPrincipalAdmin.btnhabilitarUser.setEnabled(false);
        interfazPrincipalAdmin.btnEliminarUser.setEnabled(true);
    }

}
