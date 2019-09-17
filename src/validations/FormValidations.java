/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import classes.Logs;
import components.UVFoodDialogs;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import views.VistaAdmin;

/**
 *
 * @author jeffr
 */
public class FormValidations {

    private UVFoodDialogs modal = new UVFoodDialogs();
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());

    public void alert(String mensaje, String tipo, JPanel panel, JLabel label) {
        panel.setVisible(true);

        if (tipo.equalsIgnoreCase("success")) {
            panel.setBackground(new Color(212, 237, 218));
            label.setForeground(new Color(21, 87, 36));
            label.setText(mensaje);

        } else if (tipo.equalsIgnoreCase("danger")) {
            panel.setBackground(new Color(248, 215, 218));
            label.setForeground(new Color(114, 28, 36));
            label.setText(mensaje);
        }

        Timer timer = new Timer(5000, evt -> {
            panel.setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();

    }

    public boolean campoVacio(JTextField campo) {
        if (campo.getText().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public boolean noCampoVacio(String campo) {
        if (!campo.equalsIgnoreCase("")) {
            return true;
        } else {
            modal.error_message("Error.", "Todos los campos son obligatorios.", "", null, null);
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//Error de campos obligatorios");
            return false;
        }

    }

    public boolean isDate(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechax);
        } catch (Exception e) {
            modal.error_message("Error.", "la fecha ingresada no es valida", "yyyy-MM-dd", null, null);
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//fecha ingresada no es valida");
            return false;

        }
        return true;
    }

    public boolean validarInsert(VistaAdmin vista) {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        String usuario = vista.jTextFieldUser.getText();
        String nombre = vista.jTextFieldName.getText();
        String apellido = vista.jTextFieldApellido.getText();
        String email = vista.jTextFieldEmail.getText();
        String fecha = vista.jTextFieldFecNa.getText();
        String clave = new String(vista.jPasswordField.getPassword());
        Matcher mather = pattern.matcher(email);

        if (noCampoVacio(usuario) && noCampoVacio(nombre) && noCampoVacio(apellido) && noCampoVacio(email)
                && noCampoVacio(fecha) && noCampoVacio(clave)) {
            if (mather.find() == true) {
                if (isDate(fecha)) {
                    return true;
                }
            } else {
                modal.error_message("Error.", "El email ingresado no es valido", "Example@Example.com", null, null);
                logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "//Error de email incorrecto");
                return false;
            }
        }
        return false;
    }
}

