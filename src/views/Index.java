/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

/**
 *
 * @author sp
 */
public class Index extends javax.swing.JFrame {

    /**
     * Creates new form Index
     */
    Color item_menu_exited,

    /**
     * Creates new form Index
     */
    item_bottom_exited;
    Color item_menu_entered, item_bottom_entered;
    Color item_menu_clicked;
    BevelBorder border_clicked;
    int xMouse;
    int yMouse;
    boolean slid1, slid2;
    AnimationClass AC = new AnimationClass();
    SobreNosotros nosotros;
    
    public static VistaLogin login;
    
    public Index() {
        initComponents();
        item_menu_exited = new Color(205, 31, 50);
        item_bottom_exited = new Color(240, 240, 240);
        item_menu_entered = new Color(157, 0, 0);
        item_bottom_entered = new Color(153, 153, 153);
        item_menu_clicked = new Color(157, 0, 0);
        border_clicked = new BevelBorder(BevelBorder.RAISED);
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
        jLabel5.setForeground(Color.WHITE);
        jLabel6.setForeground(Color.WHITE);
        jLabel7.setForeground(Color.WHITE);
        jLabel8.setForeground(Color.WHITE);
        jLabel9.setForeground(Color.WHITE);
        jLabel10.setForeground(Color.WHITE);
        jLabel11.setForeground(Color.WHITE);
        jLabel14.setForeground(Color.WHITE);
        jLabel15.setForeground(Color.WHITE);
        jLabel16.setForeground(Color.WHITE);
        this.setLocationRelativeTo(null);
        
        ImageIcon imagen1 = new ImageIcon("src/images/logo-footer.png");
        Icon icono1 = new ImageIcon(imagen1.getImage().getScaledInstance(lblfootuv.getWidth(), lblfootuv.getHeight(), Image.SCALE_DEFAULT));
        lblfootuv.setIcon(icono1);
        
       
        this.repaint();
        
        slid1 = true;
        slid2 = true;
        
        
       
        
        SlideShow();
    }
    
    public void SlideShow(){
        new Thread(){
            int count;
            @Override
            public void run(){
                try{
                    while(true){
                        switch(count){
                            case 0:
                                ImageIcon img1 = new ImageIcon(getClass().getResource("/images/imgIndex1.jpg"));
                                Slider1.setIcon(img1);
                                Thread.sleep(5000);   
                                AC.jLabelXLeft(0, -1240, 20, 10, Slider1);
                                AC.jLabelXLeft(1240, 0, 20, 10, Slider2);
                                count = 1;
                                break;
                            case 1:
                                ImageIcon img2 = new ImageIcon(getClass().getResource("/images/imgIndex2.jpg"));
                                Slider2.setIcon(img2);
                                Thread.sleep(5000);
                                AC.jLabelXRight(-1240, 0, 20, 10, Slider1);
                                AC.jLabelXRight(0, 1240, 20, 10, Slider2);
                                count = 3;
                                break;
                                
                            case 3:
                                ImageIcon img3 = new ImageIcon(getClass().getResource("/images/imgIndex3.jpg"));
                                Slider1.setIcon(img3);
                                Thread.sleep(5000);   
                                AC.jLabelXLeft(0, -1240, 20, 10, Slider1);
                                AC.jLabelXLeft(1240, 0, 20, 10, Slider2);
                                count = 4;
                                break;
                            
                             case 4:
                                ImageIcon img4 = new ImageIcon(getClass().getResource("/images/imgIndex4.jpg"));
                                Slider2.setIcon(img4);
                                Thread.sleep(5000);   
                                AC.jLabelXRight(-1240, 0, 20, 10, Slider1);
                                AC.jLabelXRight(0, 1240, 20, 10, Slider2);
                                count = 5;
                                break;
                                 
                            case 5:
                                ImageIcon img5 = new ImageIcon(getClass().getResource("/images/imgIndex1.jpg"));
                                Slider1.setIcon(img5);
                                Thread.sleep(5000);   
                                AC.jLabelXLeft(0, -1240, 20, 10, Slider1);
                                AC.jLabelXLeft(1240, 0, 20, 10, Slider2);
                                count = 0;
                                break;
                           
                         
                              
                        }
                    }
                }catch(Exception e){
                    
                }
            }
        }.start();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelbtnIndex = new javax.swing.JPanel();
        btnMiminize = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JButton();
        btnSobreNosotros = new javax.swing.JButton();
        jPanelfooter = new javax.swing.JPanel();
        lblfootuv = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Slider1 = new javax.swing.JLabel();
        Slider2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1240, 802));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanelbtnIndex.setBackground(new java.awt.Color(255, 255, 255));
        jPanelbtnIndex.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanelbtnIndex.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanelbtnIndex.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelbtnIndexMouseDragged(evt);
            }
        });
        jPanelbtnIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelbtnIndexMousePressed(evt);
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
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelbtnIndexLayout = new javax.swing.GroupLayout(jPanelbtnIndex);
        jPanelbtnIndex.setLayout(jPanelbtnIndexLayout);
        jPanelbtnIndexLayout.setHorizontalGroup(
            jPanelbtnIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelbtnIndexLayout.createSequentialGroup()
                .addGap(0, 1174, Short.MAX_VALUE)
                .addComponent(btnMiminize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanelbtnIndexLayout.setVerticalGroup(
            jPanelbtnIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelbtnIndexLayout.createSequentialGroup()
                .addGroup(jPanelbtnIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMiminize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelbtnIndex);
        jPanelbtnIndex.setBounds(0, 0, 1240, 25);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Sobre Nosotros");
        btnIniciarSesion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnIniciarSesion.setContentAreaFilled(false);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.setOpaque(true);
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseExited(evt);
            }
        });

        btnSobreNosotros.setBackground(new java.awt.Color(255, 255, 255));
        btnSobreNosotros.setText("Iniciar Sesion");
        btnSobreNosotros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSobreNosotros.setContentAreaFilled(false);
        btnSobreNosotros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSobreNosotros.setOpaque(true);
        btnSobreNosotros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSobreNosotrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSobreNosotrosMouseExited(evt);
            }
        });
        btnSobreNosotros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobreNosotrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(btnSobreNosotros, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSobreNosotros, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 432, 1240, 180);

        jPanelfooter.setBackground(new java.awt.Color(255, 0, 51));

        lblfootuv.setText("jLabel1");

        jLabel1.setText("Universidad del Valle:");

        jLabel2.setText("Cali - Colombia");

        jLabel3.setText("Dirección:");

        jLabel4.setText("Ciudad Universitaria Meléndez");

        jLabel5.setText("Calle 13 # 100-00");

        jLabel6.setText("Sede San Fernando ");

        jLabel7.setText("PBX:");

        jLabel8.setText("+57 2 3212100");

        jLabel10.setText("Línea gratuita:");

        jLabel11.setText("018000 22 00 21");

        jLabel9.setText("Calle 4B # 36-00");

        jLabel14.setText("Apartado Aéreo:");

        jLabel15.setText("25360");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText("Institución de educación superior sujeta a inspección y vigilancia por el Ministerio de Educación Nacional");

        javax.swing.GroupLayout jPanelfooterLayout = new javax.swing.GroupLayout(jPanelfooter);
        jPanelfooter.setLayout(jPanelfooterLayout);
        jPanelfooterLayout.setHorizontalGroup(
            jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelfooterLayout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(lblfootuv, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelfooterLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelfooterLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel3))
                            .addGroup(jPanelfooterLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(127, 127, 127)
                                .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))))
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelfooterLayout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanelfooterLayout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jLabel7))))
                    .addGroup(jPanelfooterLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1007, 1007, 1007))
        );
        jPanelfooterLayout.setVerticalGroup(
            jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelfooterLayout.createSequentialGroup()
                .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelfooterLayout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(lblfootuv, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelfooterLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelfooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)))
                .addContainerGap())
        );

        getContentPane().add(jPanelfooter);
        jPanelfooter.setBounds(0, 598, 1240, 204);

        Slider1.setText("jLabel12");
        getContentPane().add(Slider1);
        Slider1.setBounds(0, -6, 1240, 480);

        Slider2.setText("jLabel12");
        getContentPane().add(Slider2);
        Slider2.setBounds(1240, -6, 1240, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelbtnIndexMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelbtnIndexMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xMouse , y-yMouse);
    }//GEN-LAST:event_jPanelbtnIndexMouseDragged

    private void jPanelbtnIndexMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelbtnIndexMousePressed
        xMouse  = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelbtnIndexMousePressed

    private void btnMiminizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiminizeMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMiminizeMouseClicked

    private void btnMiminizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiminizeMouseEntered
        btnMiminize.setBackground(new Color(229, 229, 229));
    }//GEN-LAST:event_btnMiminizeMouseEntered

    private void btnMiminizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiminizeMouseExited
       btnMiminize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMiminizeMouseExited

    private void btnIniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseEntered
        // TODO add your handling code here:
        btnIniciarSesion.setBackground(new Color(250,250,250));
    }//GEN-LAST:event_btnIniciarSesionMouseEntered

    private void btnIniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseExited
        // TODO add your handling code here:
        btnIniciarSesion.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnIniciarSesionMouseExited

    private void btnSobreNosotrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSobreNosotrosMouseEntered
        // TODO add your handling code here:
        btnSobreNosotros.setBackground(new Color(250,250,250));
    }//GEN-LAST:event_btnSobreNosotrosMouseEntered

    private void btnSobreNosotrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSobreNosotrosMouseExited
        // TODO add your handling code here:
        btnSobreNosotros.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnSobreNosotrosMouseExited

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(232, 17, 35));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnSobreNosotrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreNosotrosActionPerformed
        // TODO add your handling code here:
        if (login == null) {
            
            login = new VistaLogin();
            login.setVisible(true);
        }
    }//GEN-LAST:event_btnSobreNosotrosActionPerformed

    private void btnIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseClicked
        // TODO add your handling code here:
         nosotros = new SobreNosotros(this, true);
         nosotros.setVisible(true);
    }//GEN-LAST:event_btnIniciarSesionMouseClicked

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
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Slider1;
    private javax.swing.JLabel Slider2;
    private javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnMiminize;
    public javax.swing.JButton btnSobreNosotros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelbtnIndex;
    private javax.swing.JPanel jPanelfooter;
    private javax.swing.JLabel lblfootuv;
    // End of variables declaration//GEN-END:variables
}
