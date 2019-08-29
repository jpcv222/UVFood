/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import managers.ControladorGestionPermisos;

/**
 *
 * @author jpcv2
 */
public class GestionPermisos extends javax.swing.JFrame {

    /**
     * Creates new form ErrorMessage
     */
    Color red_error;
    public ControladorGestionPermisos manager;

    public GestionPermisos() {
        initComponents();
        manager = new ControladorGestionPermisos(this);

        red_error = new Color(205, 31, 50);

        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabelTitleModal = new javax.swing.JLabel();
        jPanelAceptar = new javax.swing.JPanel();
        jLabelAceptar = new javax.swing.JLabel();
        jLabelUserNamePerm = new javax.swing.JLabel();
        jLabelAceptar2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxModulos = new javax.swing.JComboBox<>();
        jPanelActions = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(205, 31, 50));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit2.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 60, 50));

        jLabelTitleModal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitleModal.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitleModal.setText("Gestión de permisos por usuario.");
        jPanel2.add(jLabelTitleModal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 460, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 880, 50));

        jPanelAceptar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAceptar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(205, 31, 50), 2, true));
        jPanelAceptar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanelAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelAceptarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelAceptarMouseExited(evt);
            }
        });
        jPanelAceptar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAceptar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelAceptar.setForeground(new java.awt.Color(205, 31, 50));
        jLabelAceptar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAceptar.setText("Aceptar");
        jPanelAceptar.add(jLabelAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 6, 140, 40));

        jPanel1.add(jPanelAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 210, 50));

        jLabelUserNamePerm.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelUserNamePerm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUserNamePerm.setText("Nombre usuario");
        jPanel1.add(jLabelUserNamePerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 530, 40));

        jLabelAceptar2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelAceptar2.setForeground(new java.awt.Color(205, 31, 50));
        jLabelAceptar2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAceptar2.setText("Asignar permisos a: ");
        jPanel1.add(jLabelAceptar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 240, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 850, 10));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Acciones del módulo seleccionado");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 390, 20));

        jComboBoxModulos.setBorder(null);
        jPanel1.add(jComboBoxModulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 390, 30));

        jPanelActions.setBackground(new java.awt.Color(255, 255, 255));
        jPanelActions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jPanelActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 390, 420));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Módulo");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 390, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanelAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAceptarMouseClicked
        // TODO add your handling code here:
        //this.dispose();
    }//GEN-LAST:event_jPanelAceptarMouseClicked

    private void jPanelAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAceptarMouseEntered
        // TODO add your handling code here:
        jPanelAceptar.setBackground(red_error);
        jLabelAceptar.setForeground(Color.white);
    }//GEN-LAST:event_jPanelAceptarMouseEntered

    private void jPanelAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAceptarMouseExited
        // TODO add your handling code here:
        jPanelAceptar.setBackground(Color.white);
        jLabelAceptar.setForeground(red_error);
    }//GEN-LAST:event_jPanelAceptarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox<String> jComboBoxModulos;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabelAceptar;
    private javax.swing.JLabel jLabelAceptar2;
    public javax.swing.JLabel jLabelTitleModal;
    public javax.swing.JLabel jLabelUserNamePerm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAceptar;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
