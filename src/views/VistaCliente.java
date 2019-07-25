/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JLabel;
import rojerusan.RSPanelsSlider;

/**
 *
 * @author STH_1F_GCB_PC16
 */
public class VistaCliente extends javax.swing.JFrame {

    /**
     * Creates new form VistaCliente
     */
    int xMouse;
    int yMouse;
    public VistaCliente() {
        initComponents();
        this.setLocationRelativeTo(null);
        jLabel1.setOpaque(true);
        jLabel1.setBackground(new Color(255,51,51));
    }
    
    
    public void resetColor(JLabel item) {
        item.setOpaque(false);
        item.setBackground(new Color(205,31,50));
    }

    public void setColor(JLabel item) {
        item.setOpaque(true);
        item.setBackground(new Color(255,51,51));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        jPanelIndexCliente = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanelPerfilCliente = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanelbtn = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        btnMiminize = new javax.swing.JButton();
        jPanelHeader = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanelMenu.setBackground(new java.awt.Color(205, 31, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("   Opcion 1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("   Opcion 1");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Opcion 1");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("   Opcion 1");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelIndexCliente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIndexCliente.setName("jPanelIndexCliente"); // NOI18N

        jLabel7.setText("Index");

        javax.swing.GroupLayout jPanelIndexClienteLayout = new javax.swing.GroupLayout(jPanelIndexCliente);
        jPanelIndexCliente.setLayout(jPanelIndexClienteLayout);
        jPanelIndexClienteLayout.setHorizontalGroup(
            jPanelIndexClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIndexClienteLayout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(317, Short.MAX_VALUE))
        );
        jPanelIndexClienteLayout.setVerticalGroup(
            jPanelIndexClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIndexClienteLayout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(jPanelIndexCliente, "card2");

        jPanelPerfilCliente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPerfilCliente.setName("jPanelPerfilCliente"); // NOI18N

        jLabel8.setText("Perfil");

        javax.swing.GroupLayout jPanelPerfilClienteLayout = new javax.swing.GroupLayout(jPanelPerfilCliente);
        jPanelPerfilCliente.setLayout(jPanelPerfilClienteLayout);
        jPanelPerfilClienteLayout.setHorizontalGroup(
            jPanelPerfilClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerfilClienteLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );
        jPanelPerfilClienteLayout.setVerticalGroup(
            jPanelPerfilClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerfilClienteLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(jPanelPerfilCliente, "card3");

        jPanelbtn.setBackground(new java.awt.Color(255, 255, 255));
        jPanelbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelbtnMouseDragged(evt);
            }
        });
        jPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelbtnMousePressed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit.png"))); // NOI18N
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit2.png"))); // NOI18N
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });

        btnMiminize.setBackground(new java.awt.Color(255, 255, 255));
        btnMiminize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize.png"))); // NOI18N
        btnMiminize.setContentAreaFilled(false);
        btnMiminize.setOpaque(true);
        btnMiminize.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize (2).png"))); // NOI18N
        btnMiminize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMiminizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiminizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiminizeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelbtnLayout = new javax.swing.GroupLayout(jPanelbtn);
        jPanelbtn.setLayout(jPanelbtnLayout);
        jPanelbtnLayout.setHorizontalGroup(
            jPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelbtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMiminize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelbtnLayout.setVerticalGroup(
            jPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMiminize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelHeader.setBackground(new java.awt.Color(250, 250, 250));

        jLabel5.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(205, 31, 50));
        jLabel5.setText("UV Food");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/univalle.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 38, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(232, 17, 35));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnMiminizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiminizeMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMiminizeMouseClicked

    private void btnMiminizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiminizeMouseEntered
        btnMiminize.setBackground(new Color(229, 229, 229));
    }//GEN-LAST:event_btnMiminizeMouseEntered

    private void btnMiminizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiminizeMouseExited
        btnMiminize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMiminizeMouseExited

    private void jPanelbtnMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelbtnMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xMouse , y-yMouse);
    }//GEN-LAST:event_jPanelbtnMouseDragged

    private void jPanelbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelbtnMousePressed
        xMouse  = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelbtnMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        resetColor(jLabel2);
        setColor(jLabel1);
        resetColor(jLabel3);
        resetColor(jLabel4);
        rSPanelsSlider1.setPanelSlider(15, jPanelIndexCliente, RSPanelsSlider.DIRECT.RIGHT);
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        resetColor(jLabel1);
        setColor(jLabel2);
        resetColor(jLabel3);
        resetColor(jLabel4);
        rSPanelsSlider1.setPanelSlider(15, jPanelPerfilCliente, RSPanelsSlider.DIRECT.RIGHT);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMiminize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelIndexCliente;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelPerfilCliente;
    private javax.swing.JPanel jPanelbtn;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    // End of variables declaration//GEN-END:variables
}
