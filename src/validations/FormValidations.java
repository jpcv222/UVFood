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
 * @author Jeffrey Rios 2019 GitHub: jeffrey2423
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
            return false;
        }

    }

    public boolean isDate(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechax);
        } catch (Exception e) {
            return false;

        }
        return true;
    }

    public boolean isEmail(String email) {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find() == true;
    }

    public String validarInsert(VistaAdmin vista) {
        String result = "";

        String usuario = vista.jTextFieldUser.getText();
        String nombre = vista.jTextFieldName.getText();
        String apellido = vista.jTextFieldApellido.getText();
        String email = vista.jTextFieldEmail.getText();
        String fecha = vista.jTextFieldFecNa.getText();
        String clave = new String(vista.jPasswordField.getPassword());

        if (noCampoVacio(usuario) && noCampoVacio(nombre) && noCampoVacio(apellido) && noCampoVacio(email)
                && noCampoVacio(fecha) && noCampoVacio(clave)) {
            if (isEmail(email)) {
                if (isDate(fecha)) {
                    result = "success";
                } else {
                    result = "error.date";
                }
            } else {

                result = "error.email";
            }
        } else {
            result = "error.emptyField";
        }
        return result;
    }
    public String validarUpdate(VistaAdmin vista) {
        String result = "";

        String usuario = vista.jTextFieldUser.getText();
        String nombre = vista.jTextFieldName.getText();
        String apellido = vista.jTextFieldApellido.getText();
        String email = vista.jTextFieldEmail.getText();
        String fecha = vista.jTextFieldFecNa.getText();
        String clave = new String(vista.jPasswordField.getPassword());

        if (noCampoVacio(usuario) && noCampoVacio(nombre) && noCampoVacio(apellido) && noCampoVacio(email)
                && noCampoVacio(fecha)) {
            if (isEmail(email)) {
                if (isDate(fecha)) {
                    result = "success";
                } else {
                    result = "error.date";
                }
            } else {

                result = "error.email";
            }
        } else {
            result = "error.emptyField";
        }
        return result;
    }
}
