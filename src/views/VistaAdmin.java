/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author Juan Pablo Castro    2019
 * GitHub: jpcv222
 */
package views;

import components.UVFoodDialogs;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import rojerusan.RSPanelsSlider;
import managers.ControladorAdmin;

/**
 *
 * @author webMaster
 */
public class VistaAdmin extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form VistaAdmin
     */
    Color item_menu_exited, item_bottom_exited;
    Color item_menu_entered, item_bottom_entered;
    Color item_menu_clicked;
    BevelBorder border_clicked;
    int xMouse;
    int yMouse;
    public JPopupMenu popup = new JPopupMenu();
    public JFileChooser fileImg = new JFileChooser();
    public File nombreImg;

    public ControladorAdmin manager;
    private UVFoodDialogs modal;

    String hora, minutos, segundos;
    Thread hilo;

    public static String sePuede;

    private DisableUser confirmation_message = new DisableUser();

    ;

    public VistaAdmin() {
        initComponents();
        manager = new ControladorAdmin(this, confirmation_message);

        popup.setVisible(false);
        jlFecha.setText(fecha());
        hilo = new Thread(this);
        hilo.start();

        item_menu_exited = new Color(205, 31, 50);
        item_bottom_exited = new Color(240, 240, 240);
        item_menu_entered = new Color(157, 0, 0);
        item_bottom_entered = new Color(153, 153, 153);
        item_menu_clicked = new Color(157, 0, 0);
        border_clicked = new BevelBorder(BevelBorder.RAISED);
        // btnGuardarImg.setEnabled(false);
        this.setLocationRelativeTo(null);
        resetColor(jLabelHome, "inicio.jpg");

        /*ImageIcon imagen = new ImageIcon("src/images/buscar.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_DEFAULT));
        jLabel9.setIcon(icono);
        this.repaint();*/
        ImageIcon imagen1 = new ImageIcon("src/images/inicio.jpg");
        Icon icono1 = new ImageIcon(imagen1.getImage().getScaledInstance(jLabelHome.getWidth(), jLabelHome.getHeight(), Image.SCALE_DEFAULT));
        jLabelHome.setIcon(icono1);
        this.repaint();

        ImageIcon imagen2 = new ImageIcon("src/images/Perfil.jpg");
        Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(jLabelPerfil.getWidth(), jLabelPerfil.getHeight(), Image.SCALE_DEFAULT));
        jLabelPerfil.setIcon(icono2);
        this.repaint();

        ImageIcon imagen3 = new ImageIcon("src/images/GestionInterfaz.jpg");
        Icon icono3 = new ImageIcon(imagen3.getImage().getScaledInstance(jLabelinterfaz.getWidth(), jLabelinterfaz.getHeight(), Image.SCALE_DEFAULT));
        jLabelinterfaz.setIcon(icono3);
        this.repaint();

        ImageIcon imagen4 = new ImageIcon("src/images/GestionUser.jpg");
        Icon icono4 = new ImageIcon(imagen4.getImage().getScaledInstance(jLabelUser.getWidth(), jLabelUser.getHeight(), Image.SCALE_DEFAULT));
        jLabelUser.setIcon(icono4);
        this.repaint();

        ImageIcon imagen5 = new ImageIcon("src/images/GestionTicket.jpg");
        Icon icono5 = new ImageIcon(imagen5.getImage().getScaledInstance(jLabelticket.getWidth(), jLabelticket.getHeight(), Image.SCALE_DEFAULT));
        jLabelticket.setIcon(icono5);
        this.repaint();

        jTextFieldIdRol.setVisible(false);
        jTextFieldIdUser.setVisible(false);
        jTextFieldActivo.setVisible(false);
    }

    public void hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaActual = new Date();
        calendario.setTime(horaActual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();

        for (int i = 1; 1 < 10; i++) {
            if (i > 0) {
                hora();
                jlHora.setText(hora + ":" + minutos + ":" + segundos);
            }
        }
    }

    public static String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    public void resetColor(JLabel item, String image) {
        ImageIcon imagen = new ImageIcon("src/images/" + image);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(item.getWidth(), item.getHeight(), Image.SCALE_DEFAULT));
        item.setIcon(icono);
        this.repaint();
    }

    public void setColor(JLabel item) {
        item.setOpaque(true);
        item.setBackground(new Color(205, 41, 55));
    }

    public void changeImage(String nombreNueva, JLabel label) {
        ImageIcon image = new ImageIcon("src/images/" + nombreNueva);
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
        this.repaint();

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
        jLabelHome = new javax.swing.JLabel();
        jLabelPerfil = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabelinterfaz = new javax.swing.JLabel();
        jLabelticket = new javax.swing.JLabel();
        jPanelbtn = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        btnMiminize = new javax.swing.JButton();
        jPanelHeader = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlFecha = new javax.swing.JLabel();
        jlHora = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        jPanelIndexAdmin = new javax.swing.JPanel();
        jPanelHeaderIndex = new javax.swing.JPanel();
        jLabelBienvenida = new javax.swing.JLabel();
        P_GraficaUsers_content = new javax.swing.JPanel();
        jLabelX = new javax.swing.JLabel();
        jlUsersGraph = new javax.swing.JLabel();
        P_GraficaUsers = new javax.swing.JPanel();
        P_GraficaSessions_content = new javax.swing.JPanel();
        jLabelX1 = new javax.swing.JLabel();
        jlSessionsGraph = new javax.swing.JLabel();
        P_GraficaSessions = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanelPerfilAdmin = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabelUserNamePerm = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanelModuleUserAdmin = new javax.swing.JPanel();
        jPanelMenuOptionsModuleUser = new javax.swing.JPanel();
        jPanelUserRegisterItem = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnConsultaUser = new javax.swing.JButton();
        jPanelModuleUserReports = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jTextFieldBuscarUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jTextFieldApellido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldFecNa = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        btnModificarUser = new javax.swing.JButton();
        btnCrearUser = new javax.swing.JButton();
        btnEliminarUser = new javax.swing.JButton();
        jComboBoxRoles = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btnHabilitarEdicion = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldRol = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jTextFieldIdUser = new javax.swing.JTextField();
        jTextFieldIdRol = new javax.swing.JTextField();
        jTextFieldActivo = new javax.swing.JTextField();
        btnhabilitarUser = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jPanelModuleUserRegister = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelSelectCSVUser = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelRutaArchivo = new javax.swing.JLabel();
        jButtonCargar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabelUserNamePerm2 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanelGestionInterfaz = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnEscogerImg = new javax.swing.JButton();
        btnGuardarImg = new javax.swing.JButton();
        jLabelEscogerImagen = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelUserNamePerm1 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanelMenu.setBackground(new java.awt.Color(205, 31, 50));

        jLabelHome.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHomeMouseClicked(evt);
            }
        });

        jLabelPerfil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPerfil.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPerfilMouseClicked(evt);
            }
        });

        jLabelUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelUserMouseClicked(evt);
            }
        });

        jLabelinterfaz.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelinterfaz.setForeground(new java.awt.Color(255, 255, 255));
        jLabelinterfaz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelinterfazMouseClicked(evt);
            }
        });

        jLabelticket.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelticket.setForeground(new java.awt.Color(255, 255, 255));
        jLabelticket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelticketMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelHome, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(jLabelPerfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelinterfaz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelinterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelticket, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
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

        jlFecha.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        jlFecha.setForeground(new java.awt.Color(205, 31, 50));
        jlFecha.setText("DD/MM/YYYY");

        jlHora.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        jlHora.setForeground(new java.awt.Color(205, 31, 50));
        jlHora.setText("00:00:00");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlHora)
                .addGap(30, 30, 30)
                .addComponent(jlFecha)
                .addGap(62, 62, 62)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jlFecha)
                    .addComponent(jlHora))
                .addGap(0, 38, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelIndexAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIndexAdmin.setName("jPanelIndexAdmin"); // NOI18N
        jPanelIndexAdmin.setPreferredSize(new java.awt.Dimension(961, 704));
        jPanelIndexAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeaderIndex.setBackground(new java.awt.Color(205, 31, 50));

        jLabelBienvenida.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelBienvenida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBienvenida.setText("Bienvenido al Servicio de Restaurante Universitario, ");
        jLabelBienvenida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBienvenidaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelHeaderIndexLayout = new javax.swing.GroupLayout(jPanelHeaderIndex);
        jPanelHeaderIndex.setLayout(jPanelHeaderIndexLayout);
        jPanelHeaderIndexLayout.setHorizontalGroup(
            jPanelHeaderIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderIndexLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanelHeaderIndexLayout.setVerticalGroup(
            jPanelHeaderIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderIndexLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelIndexAdmin.add(jPanelHeaderIndex, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1001, -1));

        P_GraficaUsers_content.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        P_GraficaUsers_content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelX.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        jLabelX.setForeground(new java.awt.Color(205, 31, 50));
        jLabelX.setText("Usuarios: ");
        P_GraficaUsers_content.add(jLabelX, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jlUsersGraph.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jlUsersGraph.setForeground(new java.awt.Color(205, 31, 50));
        jlUsersGraph.setText("0");
        P_GraficaUsers_content.add(jlUsersGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 13, 78, -1));

        P_GraficaUsers.setLayout(new java.awt.BorderLayout());
        P_GraficaUsers_content.add(P_GraficaUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 390, 230));

        jPanelIndexAdmin.add(P_GraficaUsers_content, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 420, 290));

        P_GraficaSessions_content.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        P_GraficaSessions_content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelX1.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        jLabelX1.setForeground(new java.awt.Color(205, 31, 50));
        jLabelX1.setText("Sesiones:");
        P_GraficaSessions_content.add(jLabelX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        jlSessionsGraph.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jlSessionsGraph.setForeground(new java.awt.Color(205, 31, 50));
        jlSessionsGraph.setText("0");
        P_GraficaSessions_content.add(jlSessionsGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 78, -1));

        P_GraficaSessions.setLayout(new java.awt.BorderLayout());
        P_GraficaSessions_content.add(P_GraficaSessions, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 350, 520));

        jPanelIndexAdmin.add(P_GraficaSessions_content, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 370, 600));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Mayor información.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Restaurante universitario.");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, 420, 26));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Calle 13 #100-00");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 420, 26));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("restaurante@correounivalle.edu.co ");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 87, 420, 26));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("321 21 99 - 330 84 22");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 420, 26));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Ciudad Universitaria Meléndez");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 420, 26));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Edificio D14 (antes 389)");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 420, 26));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Teléfonos: +57 2 339 22 20");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 420, 26));

        jPanelIndexAdmin.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 420, 280));

        rSPanelsSlider1.add(jPanelIndexAdmin, "card2");

        jPanelPerfilAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPerfilAdmin.setName("jPanelPerfilAdmin"); // NOI18N

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 80, 120));

        jLabelUserNamePerm.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelUserNamePerm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUserNamePerm.setText("Módulo en construcción.");
        jPanel7.add(jLabelUserNamePerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 80, 130));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/screw.png"))); // NOI18N
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 80, 130));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 80, 130));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/screw.png"))); // NOI18N
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 80, 130));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 120));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, 130));

        javax.swing.GroupLayout jPanelPerfilAdminLayout = new javax.swing.GroupLayout(jPanelPerfilAdmin);
        jPanelPerfilAdmin.setLayout(jPanelPerfilAdminLayout);
        jPanelPerfilAdminLayout.setHorizontalGroup(
            jPanelPerfilAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1003, Short.MAX_VALUE)
            .addGroup(jPanelPerfilAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPerfilAdminLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanelPerfilAdminLayout.setVerticalGroup(
            jPanelPerfilAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(jPanelPerfilAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPerfilAdminLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        rSPanelsSlider1.add(jPanelPerfilAdmin, "card3");

        jPanelModuleUserAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelModuleUserAdmin.setName("jPanelModuleUserAdmin"); // NOI18N
        jPanelModuleUserAdmin.setPreferredSize(new java.awt.Dimension(961, 704));
        jPanelModuleUserAdmin.setRequestFocusEnabled(false);
        jPanelModuleUserAdmin.setVerifyInputWhenFocusTarget(false);
        jPanelModuleUserAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelMenuOptionsModuleUser.setBackground(new java.awt.Color(205, 31, 50));

        jPanelUserRegisterItem.setBackground(new java.awt.Color(205, 31, 50));
        jPanelUserRegisterItem.setName("jPanelUserRegisterItem"); // NOI18N
        jPanelUserRegisterItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelUserRegisterItemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelUserRegisterItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelUserRegisterItemMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro de usuarios");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelUserRegisterItemLayout = new javax.swing.GroupLayout(jPanelUserRegisterItem);
        jPanelUserRegisterItem.setLayout(jPanelUserRegisterItemLayout);
        jPanelUserRegisterItemLayout.setHorizontalGroup(
            jPanelUserRegisterItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserRegisterItemLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanelUserRegisterItemLayout.setVerticalGroup(
            jPanelUserRegisterItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserRegisterItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnConsultaUser.setBackground(new java.awt.Color(205, 31, 50));
        btnConsultaUser.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnConsultaUser.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultaUser.setText("Consulta de usuarios");
        btnConsultaUser.setContentAreaFilled(false);
        btnConsultaUser.setOpaque(true);
        btnConsultaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultaUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultaUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultaUserMouseExited(evt);
            }
        });
        btnConsultaUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuOptionsModuleUserLayout = new javax.swing.GroupLayout(jPanelMenuOptionsModuleUser);
        jPanelMenuOptionsModuleUser.setLayout(jPanelMenuOptionsModuleUserLayout);
        jPanelMenuOptionsModuleUserLayout.setHorizontalGroup(
            jPanelMenuOptionsModuleUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuOptionsModuleUserLayout.createSequentialGroup()
                .addComponent(jPanelUserRegisterItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 399, Short.MAX_VALUE))
        );
        jPanelMenuOptionsModuleUserLayout.setVerticalGroup(
            jPanelMenuOptionsModuleUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUserRegisterItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConsultaUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelModuleUserAdmin.add(jPanelMenuOptionsModuleUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1001, -1));

        jPanelModuleUserReports.setBackground(new java.awt.Color(255, 255, 255));
        jPanelModuleUserReports.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jPanelModuleUserReports.setAlignmentY(1.0F);
        jPanelModuleUserReports.setPreferredSize(new java.awt.Dimension(977, 635));
        jPanelModuleUserReports.setRequestFocusEnabled(false);
        jPanelModuleUserReports.setVerifyInputWhenFocusTarget(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(205, 31, 50), 1, true));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Reporte de usuarios");

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuario", "Nombre", "Apellido", "Fecha de nacimiento", "Email", "Fecha de creacion", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsers.getTableHeader().setReorderingAllowed(false);
        jTableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsers);
        if (jTableUsers.getColumnModel().getColumnCount() > 0) {
            jTableUsers.getColumnModel().getColumn(0).setResizable(false);
            jTableUsers.getColumnModel().getColumn(1).setResizable(false);
            jTableUsers.getColumnModel().getColumn(2).setResizable(false);
            jTableUsers.getColumnModel().getColumn(3).setResizable(false);
            jTableUsers.getColumnModel().getColumn(4).setResizable(false);
            jTableUsers.getColumnModel().getColumn(5).setResizable(false);
            jTableUsers.getColumnModel().getColumn(6).setResizable(false);
            jTableUsers.getColumnModel().getColumn(7).setResizable(false);
        }

        jTextFieldBuscarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarUserActionPerformed(evt);
            }
        });
        jTextFieldBuscarUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarUserKeyReleased(evt);
            }
        });

        jLabel3.setText("Buscar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(205, 31, 50), 1, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Acciones");

        jTextFieldUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldUser.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Usuario");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Clave");

        jPasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField.setBorder(null);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nombre");

        jTextFieldName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldName.setBorder(null);

        jTextFieldApellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldApellido.setBorder(null);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Apellido");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Fecha nacimiento");

        jTextFieldFecNa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldFecNa.setBorder(null);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Email");

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldEmail.setBorder(null);

        btnModificarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnModificarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_user (1).png"))); // NOI18N
        btnModificarUser.setBorder(null);
        btnModificarUser.setContentAreaFilled(false);
        btnModificarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarUser.setOpaque(true);

        btnCrearUser.setBackground(new java.awt.Color(255, 255, 255));
        btnCrearUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_user (1).png"))); // NOI18N
        btnCrearUser.setBorder(null);
        btnCrearUser.setContentAreaFilled(false);
        btnCrearUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrearUser.setOpaque(true);
        btnCrearUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUserActionPerformed(evt);
            }
        });

        btnEliminarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_user (1).png"))); // NOI18N
        btnEliminarUser.setBorder(null);
        btnEliminarUser.setContentAreaFilled(false);
        btnEliminarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarUser.setOpaque(true);
        btnEliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserActionPerformed(evt);
            }
        });

        jComboBoxRoles.setBorder(null);
        jComboBoxRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRolesActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Escoger Rol");

        btnHabilitarEdicion.setBackground(new java.awt.Color(255, 255, 255));
        btnHabilitarEdicion.setText("Habilitar Edicion");
        btnHabilitarEdicion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnHabilitarEdicion.setContentAreaFilled(false);
        btnHabilitarEdicion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHabilitarEdicion.setOpaque(true);
        btnHabilitarEdicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHabilitarEdicionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHabilitarEdicionMouseExited(evt);
            }
        });
        btnHabilitarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarEdicionActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar Campos");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setOpaque(true);
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseExited(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Rol actual");

        jTextFieldRol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldRol.setBorder(null);

        btnhabilitarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnhabilitarUser.setText("Habilitar Usuario");
        btnhabilitarUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnhabilitarUser.setContentAreaFilled(false);
        btnhabilitarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnhabilitarUser.setEnabled(false);
        btnhabilitarUser.setOpaque(true);
        btnhabilitarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnhabilitarUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnhabilitarUserMouseExited(evt);
            }
        });
        btnhabilitarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhabilitarUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21)
                            .addComponent(jTextFieldRol)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(jSeparator2)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(jComboBoxRoles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnHabilitarEdicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addComponent(btnCrearUser)
                                .addGap(29, 29, 29)
                                .addComponent(btnModificarUser)
                                .addGap(27, 27, 27)
                                .addComponent(btnEliminarUser))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextFieldIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldIdRol, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnhabilitarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFecNa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFecNa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnModificarUser)
                                    .addComponent(btnEliminarUser)
                                    .addComponent(btnCrearUser))
                                .addGap(0, 18, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldIdRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnHabilitarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnhabilitarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRol, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Puee seleccionar un usuario: click izquierdo para llenar campos, derecho para asignar permisos.");

        javax.swing.GroupLayout jPanelModuleUserReportsLayout = new javax.swing.GroupLayout(jPanelModuleUserReports);
        jPanelModuleUserReports.setLayout(jPanelModuleUserReportsLayout);
        jPanelModuleUserReportsLayout.setHorizontalGroup(
            jPanelModuleUserReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModuleUserReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelModuleUserReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModuleUserReportsLayout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelModuleUserReportsLayout.setVerticalGroup(
            jPanelModuleUserReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModuleUserReportsLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelModuleUserAdmin.add(jPanelModuleUserReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 72, -1, 620));
        jPanelModuleUserReports.getAccessibleContext().setAccessibleName("jPanelModuleUserReports");

        jPanelModuleUserRegister.setBackground(new java.awt.Color(255, 255, 255));
        jPanelModuleUserRegister.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true));
        jPanelModuleUserRegister.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanelModuleUserRegister.setRequestFocusEnabled(false);
        jPanelModuleUserRegister.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setText("Registro de usuarios");

        jPanelSelectCSVUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSelectCSVUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSelectCSVUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSelectCSVUserMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Seleccionar archivo CSV");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/open-document.png"))); // NOI18N

        javax.swing.GroupLayout jPanelSelectCSVUserLayout = new javax.swing.GroupLayout(jPanelSelectCSVUser);
        jPanelSelectCSVUser.setLayout(jPanelSelectCSVUserLayout);
        jPanelSelectCSVUserLayout.setHorizontalGroup(
            jPanelSelectCSVUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelectCSVUserLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelSelectCSVUserLayout.setVerticalGroup(
            jPanelSelectCSVUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelectCSVUserLayout.createSequentialGroup()
                .addGroup(jPanelSelectCSVUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelRutaArchivo.setText("Archivo CSV...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelRutaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelRutaArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jButtonCargar.setBackground(new java.awt.Color(205, 31, 50));
        jButtonCargar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonCargar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCargar.setText("Cargar");
        jButtonCargar.setBorder(null);
        jButtonCargar.setEnabled(false);
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel9.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 80, 120));

        jLabelUserNamePerm2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelUserNamePerm2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUserNamePerm2.setText("Módulo en construcción.");
        jPanel9.add(jLabelUserNamePerm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel9.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 80, 130));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/screw.png"))); // NOI18N
        jPanel9.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 80, 130));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel9.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 80, 130));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/screw.png"))); // NOI18N
        jPanel9.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 80, 130));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel9.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 120));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel9.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, 130));

        javax.swing.GroupLayout jPanelModuleUserRegisterLayout = new javax.swing.GroupLayout(jPanelModuleUserRegister);
        jPanelModuleUserRegister.setLayout(jPanelModuleUserRegisterLayout);
        jPanelModuleUserRegisterLayout.setHorizontalGroup(
            jPanelModuleUserRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModuleUserRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelModuleUserRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanelModuleUserRegisterLayout.createSequentialGroup()
                        .addGroup(jPanelModuleUserRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelModuleUserRegisterLayout.createSequentialGroup()
                                .addGroup(jPanelModuleUserRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 392, Short.MAX_VALUE)
                                    .addComponent(jPanelSelectCSVUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 399, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelModuleUserRegisterLayout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanelModuleUserRegisterLayout.setVerticalGroup(
            jPanelModuleUserRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModuleUserRegisterLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanelModuleUserRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSelectCSVUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );

        jPanelModuleUserAdmin.add(jPanelModuleUserRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 83, 940, 610));
        jPanelModuleUserRegister.getAccessibleContext().setAccessibleName("jPanelModuleUserRegister");

        rSPanelsSlider1.add(jPanelModuleUserAdmin, "card3");

        jPanelGestionInterfaz.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGestionInterfaz.setName("jPanelGestionInterfaz"); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(205, 31, 50), 1, true));

        btnEscogerImg.setBackground(new java.awt.Color(255, 255, 255));
        btnEscogerImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/img (1).png"))); // NOI18N
        btnEscogerImg.setText("Escoger Imagen");
        btnEscogerImg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEscogerImg.setContentAreaFilled(false);
        btnEscogerImg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEscogerImg.setOpaque(true);
        btnEscogerImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEscogerImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEscogerImgMouseExited(evt);
            }
        });
        btnEscogerImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscogerImgActionPerformed(evt);
            }
        });

        btnGuardarImg.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarImg.setText("Guardar imagen");
        btnGuardarImg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardarImg.setContentAreaFilled(false);
        btnGuardarImg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarImg.setEnabled(false);
        btnGuardarImg.setOpaque(true);
        btnGuardarImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarImgMouseExited(evt);
            }
        });
        btnGuardarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEscogerImg, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelEscogerImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelEscogerImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEscogerImg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Imagenes Slider Pagina Principal");

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 80, 120));

        jLabelUserNamePerm1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelUserNamePerm1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUserNamePerm1.setText("Módulo en construcción.");
        jPanel8.add(jLabelUserNamePerm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 80, 130));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/screw.png"))); // NOI18N
        jPanel8.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 80, 130));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel8.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 80, 130));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/screw.png"))); // NOI18N
        jPanel8.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 80, 130));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel8.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 120));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nut-icon.png"))); // NOI18N
        jPanel8.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, 130));

        javax.swing.GroupLayout jPanelGestionInterfazLayout = new javax.swing.GroupLayout(jPanelGestionInterfaz);
        jPanelGestionInterfaz.setLayout(jPanelGestionInterfazLayout);
        jPanelGestionInterfazLayout.setHorizontalGroup(
            jPanelGestionInterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionInterfazLayout.createSequentialGroup()
                .addGroup(jPanelGestionInterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGestionInterfazLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 693, Short.MAX_VALUE))
                    .addGroup(jPanelGestionInterfazLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGestionInterfazLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(277, 277, 277))
        );
        jPanelGestionInterfazLayout.setVerticalGroup(
            jPanelGestionInterfazLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionInterfazLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(jPanelGestionInterfaz, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHomeMouseClicked
        resetColor(jLabelPerfil, "Perfil.jpg");
        resetColor(jLabelinterfaz, "GestionInterfaz.jpg");
        resetColor(jLabelUser, "GestionUser.jpg");
        resetColor(jLabelticket, "GestionTicket.jpg");
        setColor(jLabelHome);
        /*resetColor(jLabelUser);
        resetColor(jLabelinterfaz);*/
        changeImage("inicio-clic.jpg", jLabelHome);
        rSPanelsSlider1.setPanelSlider(15, jPanelIndexAdmin, RSPanelsSlider.DIRECT.RIGHT);

        manager.createIndexView();

    }//GEN-LAST:event_jLabelHomeMouseClicked

    private void jLabelPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPerfilMouseClicked
        resetColor(jLabelHome, "inicio.jpg");
        resetColor(jLabelinterfaz, "GestionInterfaz.jpg");
        resetColor(jLabelUser, "GestionUser.jpg");
        resetColor(jLabelticket, "GestionTicket.jpg");
        /* setColor(jLabelPerfil);
        resetColor(jLabelUser);
        resetColor(jLabelinterfaz);*/
        changeImage("perfil-clic.jpg", jLabelPerfil);

        rSPanelsSlider1.setPanelSlider(15, jPanelPerfilAdmin, RSPanelsSlider.DIRECT.RIGHT);
    }//GEN-LAST:event_jLabelPerfilMouseClicked

    private void jLabelUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelUserMouseClicked
        resetColor(jLabelPerfil, "Perfil.jpg");
        resetColor(jLabelHome, "inicio.jpg");
        resetColor(jLabelinterfaz, "GestionInterfaz.jpg");
        resetColor(jLabelticket, "GestionTicket.jpg");
        changeImage("User-clic.jpg", jLabelUser);
        rSPanelsSlider1.setPanelSlider(15, jPanelModuleUserAdmin, RSPanelsSlider.DIRECT.RIGHT);
        jPanelModuleUserRegister.setVisible(true);
        jPanelModuleUserReports.setVisible(false);
    }//GEN-LAST:event_jLabelUserMouseClicked

    private void jLabelinterfazMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelinterfazMouseClicked
        resetColor(jLabelPerfil, "Perfil.jpg");
        resetColor(jLabelHome, "inicio.jpg");
        resetColor(jLabelUser, "GestionUser.jpg");
        resetColor(jLabelticket, "GestionTicket.jpg");
        changeImage("interfaz-clic.jpg", jLabelinterfaz);
        rSPanelsSlider1.setPanelSlider(15, jPanelGestionInterfaz, RSPanelsSlider.DIRECT.RIGHT);
    }//GEN-LAST:event_jLabelinterfazMouseClicked

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
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanelbtnMouseDragged

    private void jPanelbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelbtnMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanelbtnMousePressed

    private void jLabelticketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelticketMouseClicked
        resetColor(jLabelHome, "inicio.jpg");
        resetColor(jLabelPerfil, "Perfil.jpg");
        resetColor(jLabelinterfaz, "GestionInterfaz.jpg");
        resetColor(jLabelUser, "GestionUser.jpg");
        changeImage("Ticket-clic.jpg", jLabelticket);
    }//GEN-LAST:event_jLabelticketMouseClicked

    private void jPanelUserRegisterItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUserRegisterItemMouseEntered
        // TODO add your handling code here:
        jPanelUserRegisterItem.setBackground(item_menu_entered);
    }//GEN-LAST:event_jPanelUserRegisterItemMouseEntered

    private void jPanelUserRegisterItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUserRegisterItemMouseExited
        // TODO add your handling code here:
        jPanelUserRegisterItem.setBackground(item_menu_exited);
    }//GEN-LAST:event_jPanelUserRegisterItemMouseExited

    private void jPanelUserRegisterItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUserRegisterItemMouseClicked
        // TODO add your handling code here:
        jPanelUserRegisterItem.setBorder(border_clicked);
        btnConsultaUser.setBackground(item_menu_exited);
        btnConsultaUser.setBorder(null);
        jPanelModuleUserRegister.setVisible(true);
        jPanelModuleUserReports.setVisible(false);
    }//GEN-LAST:event_jPanelUserRegisterItemMouseClicked

    private void jPanelSelectCSVUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSelectCSVUserMouseEntered
        // TODO add your handling code here:
        jPanelSelectCSVUser.setBackground(item_bottom_entered);
    }//GEN-LAST:event_jPanelSelectCSVUserMouseEntered

    private void jPanelSelectCSVUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSelectCSVUserMouseExited
        // TODO add your handling code here:
        jPanelSelectCSVUser.setBackground(item_bottom_exited);
    }//GEN-LAST:event_jPanelSelectCSVUserMouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanelSelectCSVUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSelectCSVUserMouseClicked
        // TODO add your handling code here:
        manager.selectFile("users.select.csv");
    }//GEN-LAST:event_jPanelSelectCSVUserMouseClicked

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        // TODO add your handling code here:
        manager.readCSVFile("users.upload.csv");
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnConsultaUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaUserMouseClicked
        // TODO add your handling code here:
        btnConsultaUser.setBorder(border_clicked);
        btnConsultaUser.setBackground(item_menu_entered);
        jPanelUserRegisterItem.setBorder(null);
        jPanelModuleUserRegister.setVisible(false);
        jPanelModuleUserReports.setVisible(true);
    }//GEN-LAST:event_btnConsultaUserMouseClicked

    private void btnConsultaUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaUserMouseEntered
        // TODO add your handling code here:
        btnConsultaUser.setBackground(item_menu_entered);
    }//GEN-LAST:event_btnConsultaUserMouseEntered

    private void btnConsultaUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaUserMouseExited
        // TODO add your handling code here:
        btnConsultaUser.setBackground(item_menu_exited);
    }//GEN-LAST:event_btnConsultaUserMouseExited

    private void jTextFieldBuscarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarUserActionPerformed

    private void jTextFieldBuscarUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarUserKeyReleased
        manager.requestSearchUser();
    }//GEN-LAST:event_jTextFieldBuscarUserKeyReleased

    private void btnConsultaUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaUserActionPerformed

        sePuede = "solo_crear";
        manager.hablitarEdicionTotal();
        jTextFieldRol.removeAll();
        jTextFieldRol.repaint();
        jTextFieldRol.revalidate();
        //manager.requestFillCombo();
        //jComboBoxRoles.removeAllItems();
        manager.requestFillTable();


    }//GEN-LAST:event_btnConsultaUserActionPerformed

    private void btnGuardarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarImgActionPerformed
        // TODO add your handling code here:
        manager.guardarImg();

    }//GEN-LAST:event_btnGuardarImgActionPerformed

    private void btnEscogerImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerImgActionPerformed
        // TODO add your handling code here:
        fileImg.showOpenDialog(this);
        nombreImg = fileImg.getSelectedFile();
        if (manager.validarImg()) {
            String rutaImagen = fileImg.getSelectedFile().toString();
            //metodo de libreria importada RSScaleLabel para mostrar la imagen seleccionada
            rsscalelabel.RSScaleLabel.setScaleLabel(jLabelEscogerImagen, rutaImagen);
        } else {

        }


    }//GEN-LAST:event_btnEscogerImgActionPerformed

    private void btnEscogerImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEscogerImgMouseEntered
        // TODO add your handling code here:
        btnEscogerImg.setBackground(new Color(250, 250, 250));
    }//GEN-LAST:event_btnEscogerImgMouseEntered

    private void btnEscogerImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEscogerImgMouseExited
        // TODO add your handling code here:
        btnEscogerImg.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnEscogerImgMouseExited

    private void btnGuardarImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarImgMouseEntered
        // TODO add your handling code here:
        btnGuardarImg.setBackground(new Color(250, 250, 250));
    }//GEN-LAST:event_btnGuardarImgMouseEntered

    private void btnGuardarImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarImgMouseExited
        // TODO add your handling code here:
        btnGuardarImg.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnGuardarImgMouseExited

    private void jTableUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsersMouseClicked
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            manager.limpiarCampos();
            manager.requestFillFields();
            btnCrearUser.setEnabled(false);
            jComboBoxRoles.setEnabled(false);
            btnHabilitarEdicion.setEnabled(true);
            if (jTextFieldActivo.getText().equals("Activo")) {
                sePuede = "soloEliminar";
                btnEliminarUser.setEnabled(true);
                manager.hablitarEdicionTotal();
                btnHabilitarEdicion.setEnabled(true);
                jComboBoxRoles.setEnabled(false);

                btnhabilitarUser.setEnabled(false);
            } else if (jTextFieldActivo.getText().equals("No Activo")) {
                sePuede = "soloHabilitar";
                btnEliminarUser.setEnabled(false);
                manager.hablitarEdicionTotal();
                btnHabilitarEdicion.setEnabled(true);

                btnhabilitarUser.setEnabled(true);

            }

        }
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            //manager.createPopupmenu();

            manager.showPermissionsView();
        }
    }//GEN-LAST:event_jTableUsersMouseClicked


    private void btnCrearUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUserActionPerformed
        // TODO add your handling code here:
        manager.requestValidationsInsertUser();
        //manager.requestFillTable();
    }//GEN-LAST:event_btnCrearUserActionPerformed

    private void btnHabilitarEdicionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabilitarEdicionMouseEntered
        // TODO add your handling code here:
        /* btnHabilitarEdicion.setBackground(new Color(205, 31, 50));
        btnHabilitarEdicion.setForeground(Color.white);*/

    }//GEN-LAST:event_btnHabilitarEdicionMouseEntered

    private void btnHabilitarEdicionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabilitarEdicionMouseExited
        // TODO add your handling code here:
        /*btnHabilitarEdicion.setBackground(new Color(255, 255, 255));
        btnHabilitarEdicion.setForeground(Color.black);*/
    }//GEN-LAST:event_btnHabilitarEdicionMouseExited

    private void btnHabilitarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarEdicionActionPerformed
        // TODO add your handling code here:
        sePuede = "eliminar_modificar";
        manager.hablitarEdicionTotal();
    }//GEN-LAST:event_btnHabilitarEdicionActionPerformed

    private void btnLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseEntered
        // TODO add your handling code here:

        /*btnLimpiar.setBackground(new Color(205, 31, 50));
        btnLimpiar.setForeground(Color.white);*/

    }//GEN-LAST:event_btnLimpiarMouseEntered

    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited
        // TODO add your handling code here:
        /*btnLimpiar.setBackground(new Color(255, 255, 255));
        btnLimpiar.setForeground(Color.black);*/
    }//GEN-LAST:event_btnLimpiarMouseExited

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        //jComboBoxRoles.removeAllItems();
        sePuede = "solo_crear_limpiar";
        manager.hablitarEdicionTotal();
        manager.limpiarCampos();

        jComboBoxRoles.setEnabled(true);
        btnhabilitarUser.setEnabled(false);
        btnEliminarUser.setEnabled(false);

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jLabelBienvenidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBienvenidaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelBienvenidaMouseClicked

    private void jComboBoxRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRolesActionPerformed
        // TODO add your handling code here:
        sePuede = "solo_crear";
        jTextFieldRol.removeAll();
        jTextFieldRol.repaint();
        jTextFieldRol.revalidate();
        String rol = jComboBoxRoles.getSelectedItem().toString();
        jTextFieldRol.setText(rol);
    }//GEN-LAST:event_jComboBoxRolesActionPerformed

    private void btnEliminarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserActionPerformed
        // TODO add your handling code here:
        //manager.showConfirmationMessage();
    }//GEN-LAST:event_btnEliminarUserActionPerformed

    private void btnhabilitarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhabilitarUserMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnhabilitarUserMouseEntered

    private void btnhabilitarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhabilitarUserMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnhabilitarUserMouseExited

    private void btnhabilitarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhabilitarUserActionPerformed
        // TODO add your handling code here:
        manager.requestEnableUser();
        btnEliminarUser.setEnabled(true);
    }//GEN-LAST:event_btnhabilitarUserActionPerformed

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
            java.util.logging.Logger.getLogger(VistaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel P_GraficaSessions;
    public javax.swing.JPanel P_GraficaSessions_content;
    public javax.swing.JPanel P_GraficaUsers;
    public javax.swing.JPanel P_GraficaUsers_content;
    private javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnConsultaUser;
    public javax.swing.JButton btnCrearUser;
    public javax.swing.JButton btnEliminarUser;
    public javax.swing.JButton btnEscogerImg;
    public javax.swing.JButton btnGuardarImg;
    public javax.swing.JButton btnHabilitarEdicion;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMiminize;
    public javax.swing.JButton btnModificarUser;
    public javax.swing.JButton btnhabilitarUser;
    public javax.swing.JButton jButtonCargar;
    public javax.swing.JComboBox<String> jComboBoxRoles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelBienvenida;
    public javax.swing.JLabel jLabelEscogerImagen;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelPerfil;
    public javax.swing.JLabel jLabelRutaArchivo;
    private javax.swing.JLabel jLabelUser;
    public javax.swing.JLabel jLabelUserNamePerm;
    public javax.swing.JLabel jLabelUserNamePerm1;
    public javax.swing.JLabel jLabelUserNamePerm2;
    private javax.swing.JLabel jLabelX;
    private javax.swing.JLabel jLabelX1;
    private javax.swing.JLabel jLabelinterfaz;
    private javax.swing.JLabel jLabelticket;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGestionInterfaz;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelHeaderIndex;
    private javax.swing.JPanel jPanelIndexAdmin;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMenuOptionsModuleUser;
    private javax.swing.JPanel jPanelModuleUserAdmin;
    public javax.swing.JPanel jPanelModuleUserRegister;
    public javax.swing.JPanel jPanelModuleUserReports;
    private javax.swing.JPanel jPanelPerfilAdmin;
    public javax.swing.JPanel jPanelSelectCSVUser;
    private javax.swing.JPanel jPanelUserRegisterItem;
    private javax.swing.JPanel jPanelbtn;
    public javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    public javax.swing.JTable jTableUsers;
    public javax.swing.JTextField jTextFieldActivo;
    public javax.swing.JTextField jTextFieldApellido;
    public javax.swing.JTextField jTextFieldBuscarUser;
    public javax.swing.JTextField jTextFieldEmail;
    public javax.swing.JTextField jTextFieldFecNa;
    public javax.swing.JTextField jTextFieldIdRol;
    public javax.swing.JTextField jTextFieldIdUser;
    public javax.swing.JTextField jTextFieldName;
    public javax.swing.JTextField jTextFieldRol;
    public javax.swing.JTextField jTextFieldUser;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlHora;
    public javax.swing.JLabel jlSessionsGraph;
    public javax.swing.JLabel jlUsersGraph;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    // End of variables declaration//GEN-END:variables

}
