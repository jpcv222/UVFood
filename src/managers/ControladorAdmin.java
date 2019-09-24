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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import validations.FormValidations;
import views.DisableUser;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
 */
public class ControladorAdmin implements ActionListener {

    private VistaAdmin interfazPrincipalAdmin;
    private FormValidations validaciones;
    private final FileManage file;
    private final KeyValidate keyvalidate;
    private UVFoodDialogs modal;
    private ConsultasAdmin consultasAdmin;
    private DisableUser confirmation_message;
    public Usuario user;

    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    //private Cliente modeloCliente;
    public ControladorAdmin(VistaAdmin interfazPrincipalAdmin, DisableUser confirmation_message) {
        this.interfazPrincipalAdmin = interfazPrincipalAdmin;
        this.modal = new UVFoodDialogs();
        this.keyvalidate = new KeyValidate(modal);
        this.file = new FileManage();
        this.confirmation_message = confirmation_message;
        this.consultasAdmin = new ConsultasAdmin();
        this.confirmation_message.btnAceptar.addActionListener(this);
        this.confirmation_message.btnCancelar.addActionListener(this);
        this.interfazPrincipalAdmin.btnEliminarUser.addActionListener(this);
        this.validaciones = new FormValidations();

    }

    public void set_init_conf() {
        String current_text = interfazPrincipalAdmin.jLabelBienvenida.getText();
        this.interfazPrincipalAdmin.jLabelBienvenida.setText(current_text + user.getFirstname());
        createIndexView();
    }

    public void showPermissionsView() {
        try {
            modal.show_permissions_view(user.getUsername(), user.getFirstname(), user.getSurname());
        } catch (Exception ex) {
            modal.error_message("Error fatal.", "Error en servidor.", "Se ha generado una excepción.", null, null);
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Consulta no arroja resultados.");
        }
    }

    public void selectFile(String namekey) {

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

            JFreeChart grafica = ChartFactory.createBarChart3D("Sesiones", "", "Total", data, PlotOrientation.VERTICAL, true, true, false);

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

    public void readCSVFile(String namekey) {

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

    public void requestFillComboImgType() {
        if (!consultasAdmin.fillComboImgTipo(interfazPrincipalAdmin)) {
            modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
        }
    }

    public void requestGuardarImg(String tipo) {
        try {
            switch (tipo) {
                case "Slider":
                    guardarImg();
                    break;
                case "Menu":
                    guardarImgMenu();
                    break;
                default:
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                    break;

            }

        } catch (NullPointerException e) {
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

    public String getFecha() {
        Date myDate = new Date();

        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(myDate);

        return fecha;
    }

    public void requestInsertImgBD(String nombreImg) {
        String result = consultasAdmin.guardarMenu(nombreImg, interfazPrincipalAdmin);

        try {
            switch (result) {
                case "success.dato.insertado":
                    interfazPrincipalAdmin.jLabelEscogerImagen.setText("Debes escoger una imagen");
                    break;
                case "error.dato.no.insertado":
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "/error.Imagen no insertada/ ");
                    break;
                default:
                    logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Respuesta a petición inválida.");
                    break;

            }

        } catch (NullPointerException e) {
        }
    }

    public void guardarImgMenu() {

        if (interfazPrincipalAdmin.nombreImg != null) {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(true);
            try {
                //definimos el destino de la imagen
                String nombreImg = interfazPrincipalAdmin.nombreImg.getName() + "menu_dia_" +getFecha();
                String dest = System.getProperty("user.dir") + "/src/ImgMenu/" + interfazPrincipalAdmin.nombreImg.getName() + "-" + getFecha();
                Path destino = Paths.get(dest);

                //defininimos el origen
                String orig = interfazPrincipalAdmin.nombreImg.getPath();
                Path origen = Paths.get(orig);

                //copiamos el archivo
                Files.copy(origen, destino, REPLACE_EXISTING);
                requestInsertImgBD(nombreImg);
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
            interfazPrincipalAdmin.jComboBoxTipoImg.setEnabled(true);
            return true;
        } else if (interfazPrincipalAdmin.nombreImg.getName().contains(".png")) {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(true);
            interfazPrincipalAdmin.jComboBoxTipoImg.setEnabled(true);
            return true;
        } else {
            interfazPrincipalAdmin.btnGuardarImg.setEnabled(false);
            interfazPrincipalAdmin.jComboBoxTipoImg.setEnabled(false);
            return false;
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

    public void requestFillFields() {
        if (!consultasAdmin.llenarAcciones(interfazPrincipalAdmin)) {
            modal.error_message("Error", "Algo anda mal", "No se pueden mostrar registros de la Base de datos", "Por Favor intenta mas tarde", "O reportanos que ocurre");
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

    public void requestDisableUser() {
        String res = consultasAdmin.disableUser(interfazPrincipalAdmin);

        switch (res) {
            case "success":
                modal.success_message("Exito", "", "El usuario fue deshabilitado con exito", "", "");
                break;
            case "error":
                modal.error_message("Error", "", "Intentalo de nuevo por favor", "", "");
                break;

        }

    }

    public void showConfirmationMessage() {
        modal.confirmation_message("Confirmacion", "¿Desea deshabilitar este usuario?");

    }

    public void requestEnableUser() {
        consultasAdmin.enableUser(interfazPrincipalAdmin);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == interfazPrincipalAdmin.btnEliminarUser) {
            confirmation_message.setVisible(true);
            interfazPrincipalAdmin.btnhabilitarUser.setEnabled(true);

            //modal.confirmation_message("Confirmacion", "¿Desea deshabilitar este usuario?");
        }
        if (ae.getSource() == confirmation_message.btnAceptar) {
            requestDisableUser();
            confirmation_message.dispose();

        }
        if (ae.getSource() == confirmation_message.btnCancelar) {
            confirmation_message.dispose();
        }

    }

}
