/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import components.UVFoodDialogs;
import java.awt.Color;
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

    public String validarInsert(VistaAdmin vista) {
        String result = "";
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        String usuario  = vista.jTextFieldUser.getText();
        String nombre   = vista.jTextFieldName.getText();
        String apellido = vista.jTextFieldApellido.getText();
        String email    = vista.jTextFieldEmail.getText();
        String fecha    = vista.jTextFieldFecNa.getText();
        String clave    = new String(vista.jPasswordField.getPassword());
        Matcher mather  = pattern.matcher(email);

        if (mather.find() == true) {
            
        }else{
            result = "email.error";
        }
        
        return result;
    }

}
