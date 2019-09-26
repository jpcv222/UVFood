/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;


import views.ConfirmMessage;
import views.ErrorMessage;
import views.SuccesMessage;

/**
 *
 * @author jpcv2
 */
public class UVFoodDialogs {

    public ErrorMessage error_message;
    public SuccesMessage succes_message;
    public ConfirmMessage confirmation_message;

    public UVFoodDialogs() {
        this.error_message = new ErrorMessage();
        this.succes_message = new SuccesMessage();
        this.confirmation_message = new ConfirmMessage();
    }

    public void error_message(String title, String subtitle, String body1, String body2, String body3) {

        if (validate_message(title, subtitle, body1)) {
            error_message.jLabelTitleModal.setText(title);
            error_message.jLabelSubtitleModal.setText(subtitle);
            error_message.jLabelBodyModal1.setText(body1);
            error_message.jLabelBodyModal2.setVisible(true);
            error_message.jLabelBodyModal3.setVisible(true);
            error_message.jLabelBodyModal2.setText(body2);
            error_message.jLabelBodyModal3.setText(body3);
            if (body2 == null) {
                error_message.jLabelBodyModal2.setVisible(false);
            }
            if (body3 == null) {
                error_message.jLabelBodyModal3.setVisible(false);
            }
            error_message.setVisible(true);
            error_message.setAlwaysOnTop(true);
        }
    }

    public void success_message(String title, String subtitle, String body1, String body2, String body3) {

        if (validate_message(title, subtitle, body1)) {
            succes_message.jLabelTitleModal.setText(title);
            succes_message.jLabelSubtitleModal.setText(subtitle);
            succes_message.jLabelBodyModal1.setText(body1);
            succes_message.jLabelBodyModal2.setVisible(true);
            succes_message.jLabelBodyModal3.setVisible(true);
            succes_message.jLabelBodyModal2.setText(body2);
            succes_message.jLabelBodyModal3.setText(body3);
            if (body2 == null || body2.isEmpty()) {
                succes_message.jLabelBodyModal2.setVisible(false);
            }
            if (body3 == null || body3.isEmpty()) {
                succes_message.jLabelBodyModal3.setVisible(false);
            }
            succes_message.setVisible(true);
            succes_message.setAlwaysOnTop(true);
        }
    }

    public void confirmation_message(String title, String subtitle) {

        if (validate_message(title, subtitle)) {
            confirmation_message.jLabelTitleModal.setText(title);
            confirmation_message.jLabelSubtitleModal.setText(subtitle);

            confirmation_message.setVisible(true);
            confirmation_message.setAlwaysOnTop(true);
        }
    }


    public boolean validate_message(String title, String subtitle, String body1) {
        boolean result = true;

        if (title == null || subtitle == null || body1 == null) {
            this.error_message("Error del programador.", "Parámetros de modal no válidos", "El título, subtítulo y primer mensaje son obligatorios.", null, null);
            result = false;
        }
        return result;
    }
        public boolean validate_message(String title, String subtitle) {
        boolean result = true;

        if (title == null || subtitle == null) {
            this.confirmation_message("Error del programador.", "El título y subtítul son obligatorios.");
            result = false;
        }
        return result;
    }

}
