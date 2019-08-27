/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

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
    
    public boolean campoVacio(JTextField campo){
        if (campo.getText().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

}
