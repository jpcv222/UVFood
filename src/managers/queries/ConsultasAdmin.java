/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.queries;

import classes.ConexionBD;
import classes.Logs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import views.ConfirmMessage;
import views.VistaAdmin;

/**
 *
 * @author STH_1F_MCB_PC09
 */
public class ConsultasAdmin extends ConexionBD {
    
    private Logs logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    private DBCore db_core = new DBCore();
    
    String usuarioTem = "";
    String emailTem = "";
    
    public boolean llenarTabla(VistaAdmin vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        Statement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM uvfood_user";
        try {
            conn = Conexion();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Fecha de Nacimiento");
            modelo.addColumn("Email");
            modelo.addColumn("Fecha de creacion");
            modelo.addColumn("Estado");
            
            while (rs.next()) {
                
                Object[] filas = new Object[cantidadCol];
                
                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                filas[4] = rs.getObject(5);
                filas[5] = rs.getObject(6);
                filas[6] = rs.getObject(8);
                
                if (rs.getInt(9) == 1) {
                    filas[7] = "Activo";
                } else {
                    filas[7] = "No activo";
                }
                
                modelo.addRow(filas);
            }
            vista.jTableUsers.setModel(modelo);
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        }
        
    }
    
    public boolean llenarTablaUsersToTickets(VistaAdmin vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        try {
            Statement ps = null, psaux = null;
            Connection conn = null;
            ResultSet rs = null, rsaux = null;
            
            String sqlUsersToTickets = "SELECT uvfood_user.iduser,  uvfood_user.username, uvfood_user.firstname, uvfood_user.surname, uvfood_user_tickets.count_tickets\n"
                    + "FROM uvfood_user \n"
                    + "INNER JOIN uvfood_user_tickets ON uvfood_user.iduser = uvfood_user_tickets.iduser\n"
                    + "WHERE uvfood_user.is_active = 1 AND uvfood_user.iduser IN \n"
                    + "	(SELECT iduser FROM uvfood_user_extended WHERE uvfood_user_extended.iduser = uvfood_user.iduser AND id_typeuser IN\n"
                    + "	(SELECT id_typeuser FROM uvfood_typeuser WHERE type_user = 'Cliente'));";
            
            conn = Conexion();
            ps = conn.createStatement();
            rs = ps.executeQuery(sqlUsersToTickets);
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Tickets acumulados");
            modelo.addColumn("Descuento");
            
            while (rs.next()) {
                
                Object[] filas = new Object[cantidadCol + 1];
                
                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                filas[4] = rs.getObject(5);
                filas[5] = 0;
                String sqlUserWithDiscount = "SELECT  uvfood_discount.price_discount\n"
                        + "FROM uvfood_user_discount \n"
                        + "INNER JOIN uvfood_discount ON uvfood_user_discount.iddiscount = uvfood_discount.iddiscount\n"
                        + "WHERE uvfood_user_discount.iduser = '" + rs.getObject(1) + "';";
                
                psaux = conn.createStatement();
                rsaux = psaux.executeQuery(sqlUserWithDiscount);
                
                while (rsaux.next()) {
                    filas[5] = rsaux.getObject(1);
                }
                
                modelo.addRow(filas);
            }
            vista.jTableUsersToTickets.setModel(modelo);
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        }
        
    }
    
    public boolean llenarTablaSessions(VistaAdmin vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        try {
            Statement ps = null;
            Connection conn = null;
            ResultSet rs = null;
            
            String sql = "SELECT uvfood_user.username, uvfood_user.firstname, uvfood_user.surname, count(\"idsession\") AS cant FROM uvfood_sessions \n"
                    + "INNER JOIN uvfood_user ON uvfood_sessions.iduser = uvfood_user.iduser\n"
                    + "GROUP BY uvfood_user.iduser;";
            
            conn = Conexion();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();
            
            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Sesiones");
            
            while (rs.next()) {
                
                Object[] filas = new Object[cantidadCol];
                
                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                
                modelo.addRow(filas);
            }
            vista.jTableUsersSessions.setModel(modelo);
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        }
        
    }
    
    public boolean buscarUser(VistaAdmin vista) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        Statement ps = null;
        Connection conn = Conexion();
        ResultSet rs = null;
        String filtro = "'%" + vista.jTextFieldBuscarUser.getText() + "%'";

        //$query = "SELECT * FROM imagenesproductos WHERE nombre LIKE '%$q%' OR descripcion LIKE '%$q%' OR precio LIKE '%$q%' OR categoria LIKE '%$q%'";
        String sql = "SELECT * FROM uvfood_user where idUser::text LIKE" + filtro + " OR username::text LIKE" + filtro + " OR firstname::text LIKE" + filtro + " OR surname::text LIKE" + filtro;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadCol = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Fecha de Nacimiento");
            modelo.addColumn("Email");
            modelo.addColumn("Fecha de creacion");
            modelo.addColumn("Estado");
            
            while (rs.next()) {
                
                Object[] filas = new Object[cantidadCol];
                
                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(3);
                filas[3] = rs.getObject(4);
                filas[4] = rs.getObject(5);
                filas[5] = rs.getObject(6);
                filas[6] = rs.getObject(8);
                
                if (rs.getInt(9) == 1) {
                    filas[7] = "Activo";
                } else {
                    filas[7] = "No activo";
                }
                
                modelo.addRow(filas);
            }
            vista.jTableUsers.setModel(modelo);
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            //modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");

            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            //modal.error_message("Error", "Algo anda mal", "El servidor esta presentado problemas", "Por Favor intenta mas tarde", "O reportanos que ocurre");
            return false;
        }
    }
    
    public static String sqlDateToString(java.sql.Date date) {
        if (date != null) {
            java.util.Date utilDate = new java.util.Date(date.getTime());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return dateFormat.format(utilDate);
        }
        return null;
    }
    
    public boolean llenarAcciones(VistaAdmin vista) {
        
        Statement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conn = Conexion();
            
            int fila = vista.jTableUsers.getSelectedRow();
            
            String codigo = vista.jTableUsers.getValueAt(fila, 0).toString();
            String activo = vista.jTableUsers.getValueAt(fila, 7).toString();
            
            String sql = "SELECT * FROM uvfood_user WHERE iduser= '" + codigo + "';";
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            
            while (rs.next()) {
                usuarioTem = rs.getString(2);
                emailTem = rs.getString(6);
                vista.jTextFieldIdUser.setText("" + rs.getInt(1));
                vista.jTextFieldUser.setText(rs.getString(2));
                vista.jTextFieldName.setText(rs.getString(3));
                vista.jTextFieldApellido.setText(rs.getString(4));
                vista.jTextFieldEmail.setText(rs.getString(6));
                vista.jTextFieldFecNa.setText(sqlDateToString(rs.getDate(5)));
                vista.jTextFieldNumActi.setText("" + rs.getInt("is_active"));
                vista.jTextFieldActivo.setText(activo);
                
                if (vista.jTextFieldActivo.getText().equals("No activo")) {
                    vista.btnhabilitarUser.setEnabled(true);
                } else {
                    vista.btnhabilitarUser.setEnabled(false);
                }
            }
            String sql2 = "SELECT t2.type_user, t2.id_typeuser FROM uvfood_user_extended AS t1 INNER JOIN uvfood_typeuser "
                    + "AS t2 ON t2.id_typeuser = t1.id_typeuser WHERE t1.iduser ='" + codigo + "';";
            ps = conn.createStatement();
            rs = ps.executeQuery(sql2);
            
            while (rs.next()) {
                if (rs.getString(1).equals("")) {
                    vista.jTextFieldRol.setText("Sin rol asignado");
                } else {
                    vista.jTextFieldRol.setText(rs.getString(1));
                    vista.jTextFieldIdRol.setText("" + rs.getInt(2));
                }
                
            }
            
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        } catch (ArrayIndexOutOfBoundsException ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
            return false;
        }
    }
    
    public boolean fillCombo(VistaAdmin vista) {
        vista.jComboBoxRoles.removeAllItems();
        Statement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conn = Conexion();
            
            String sql = "SELECT * FROM uvfood_typeuser;";
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            
            while (rs.next()) {
                
                vista.jComboBoxRoles.addItem(rs.getString("type_user"));
            }
            rs.close();
            ps.close();
            return true;
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            return false;
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            return false;
        } catch (ArrayIndexOutOfBoundsException ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
            return false;
        }
        
    }
    
    public int get_count_record(String table, String atrib) {
        
        int result = db_core.get_count_record(table, atrib);
        return result;
        
    }
    
    public boolean insertSale(VistaAdmin vista) {
        boolean result = false;
        try {
            
            PreparedStatement ps = null;
            
            int created_by = vista.manager.user.getIdUser();
            int fila = vista.jTableUsersToTickets.getSelectedRow();
            int created_to = (int) vista.jTableUsersToTickets.getValueAt(fila, 0);
            int tickets = Integer.parseInt(vista.jTextFieldCantidadTickets.getText());
            int total_price = Integer.parseInt(vista.jTextFieldTotalVenta.getText());
            int cash = Integer.parseInt(vista.jTextFieldEfectivo.getText());
            int cash_change = Integer.parseInt(vista.jTextFieldCambio.getText());
            
            Connection conn = Conexion();
            
            String insertQuery = "INSERT INTO uvfood_sales (created_by, created_to, tickets, total_price, cash, cash_change) VALUES "
                    + "('" + created_by + "', '" + created_to + "', '" + tickets + "', '" + total_price + "', '" + cash + "', '" + cash_change + "')";
            
            ps = conn.prepareStatement(insertQuery);
            int res = ps.executeUpdate();
            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
            
            ps.close();
            
        } catch (SQLException | NullPointerException | IllegalArgumentException | ClassCastException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = false;
        }
        return result;
    }
    
    public boolean insertConsumption(VistaAdmin vista, int row) {
        boolean result = false;
        try {
            
            PreparedStatement ps = null;
            
            int iduser = (int) vista.jTableUsersToTickets.getValueAt(row, 0);
            
            Connection conn = Conexion();
            
            String insertQuery = "INSERT INTO uvfood_consumption_user (iduser) VALUES "
                    + "('" + iduser + "';)";
            
            ps = conn.prepareStatement(insertQuery);
            int res = ps.executeUpdate();
            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
            
            ps.close();
            
        } catch (SQLException | NullPointerException | IllegalArgumentException | ClassCastException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = false;
        }
        return result;
    }
    
    public String crearUsuario(VistaAdmin vista) {
        String result = "";
        try {
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String usuario = vista.jTextFieldUser.getText();
            String nombre = vista.jTextFieldName.getText();
            String apellido = vista.jTextFieldApellido.getText();
            String email = vista.jTextFieldEmail.getText();
            Date fecha = Date.valueOf(vista.jTextFieldFecNa.getText());
            String clave = new String(vista.jPasswordField.getPassword());
            String rol = vista.jTextFieldRol.getText();
            
            Connection conn = Conexion();
            
            String verUserQuery = "SELECT username FROM uvfood_user WHERE username = '" + usuario + "';";
            String verEmailQuery = "SELECT email FROM uvfood_user WHERE email = '" + email + "';";
            
            String insertQuery = "INSERT INTO uvfood_user (username, firstname, surname, birth_date, email, password_user) VALUES ('" + usuario + "', '" + nombre + "', '" + apellido + "', '" + fecha + "', '" + email + "', '" + clave + "')";
            String getIdQuery = "SELECT iduser, is_active FROM uvfood_user WHERE username = '" + usuario + "';";
            
            ps = conn.prepareStatement(verUserQuery);
            rs = ps.executeQuery();

            //verificamos primero si el usuario existe
            if (rs.next()) {
                result = "error.usuario.existe";
            } else {
                ps = conn.prepareStatement(verEmailQuery);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = "error.email.existe";
                } else {
                    ps = conn.prepareStatement(insertQuery);
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        
                        ps = conn.prepareStatement(getIdQuery);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            int idUserTemp = rs.getInt(1);
                            int activeUserTemp = rs.getInt(2);
                            
                            String getIdRolQuery = "SELECT id_typeuser FROM uvfood_typeuser WHERE type_user = '" + rol + "';";
                            ps = conn.prepareStatement(getIdRolQuery);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                int idRolTemp = rs.getInt(1);
                                String insertRolUserQuery = "INSERT INTO uvfood_user_extended VALUES('" + idUserTemp + "', '" + idRolTemp + "', '" + activeUserTemp + "')";
                                ps = conn.prepareStatement(insertRolUserQuery);
                                int res2 = ps.executeUpdate();
                                if (res2 > 0) {
                                    llenarTabla(vista);
                                    result = "success.dato.insertado";
                                } else {
                                    result = "error.dato.no.insertado";
                                }
                            }
                        }
                    } else {
                        result = "error.dato.no.insertado";
                    }
                }
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = "error.sql.error";
        } catch (NullPointerException | IllegalArgumentException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "error.NP.error";
        }
        return result;
    }
    
    public String ManejadorUpdate(VistaAdmin vista, String usuario, String nombre, String apellido, Date fecha, String email, String clave, String idUser, String rol) {
        String result = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = Conexion();
            String updateQuery = "UPDATE uvfood_user SET username = '" + usuario + "', firstname = '" + nombre + "', surname = '" + apellido + "', birth_date = '" + fecha + "', email = '" + email + "', password_user ='" + clave + "' WHERE iduser = '" + idUser + "';";
            String updateQuery2 = "UPDATE uvfood_user SET username = '" + usuario + "', firstname = '" + nombre + "', surname = '" + apellido + "', birth_date = '" + fecha + "', email = '" + email + "' WHERE iduser = '" + idUser + "';";
            
            int res = 0;
            if (clave.equals("")) {
                ps = conn.prepareStatement(updateQuery2);
                res = ps.executeUpdate();
                
            } else {
                ps = conn.prepareStatement(updateQuery);
                res = ps.executeUpdate();
                
            }
            
            if (res > 0) {
                result = "success.dato.actualizado";
                
                String getIdRolQuery = "SELECT id_typeuser FROM uvfood_typeuser WHERE type_user = '" + rol + "';";
                ps = conn.prepareStatement(getIdRolQuery);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int idRolTemp = rs.getInt(1);
                    String updateRolUserQuery = "UPDATE uvfood_user_extended SET id_typeuser = '" + idRolTemp + "' WHERE iduser = '" + idUser + "';";
                    ps = conn.prepareStatement(updateRolUserQuery);
                    int res2 = ps.executeUpdate();
                    if (res2 > 0) {
                        llenarTabla(vista);
                        result = "success.dato.actualizado";
                    } else {
                        result = "error.dato.no.actualizado";
                    }
                }
            } else {
                result = "error.dato.no.actualizado";
            }
            
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = "error.sql.error";
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "error.NP.error";
        }
        return result;
        
    }
    
    public String updateUser(VistaAdmin vista) {
        String result = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            String usuario = vista.jTextFieldUser.getText();
            String nombre = vista.jTextFieldName.getText();
            String apellido = vista.jTextFieldApellido.getText();
            String email = vista.jTextFieldEmail.getText();
            Date fecha = Date.valueOf(vista.jTextFieldFecNa.getText());
            String clave = new String(vista.jPasswordField.getPassword());
            String rol = vista.jTextFieldRol.getText();
            String idUser = vista.jTextFieldIdUser.getText();
            
            Connection conn = Conexion();
            
            if (!usuario.equals(usuarioTem)) {
                String verUserQuery = "SELECT username FROM uvfood_user WHERE username = '" + usuario + "';";
                ps = conn.prepareStatement(verUserQuery);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = "error.usuario.existe";
                } else {
                    
                    if (!email.equals(emailTem)) {
                        String verEmailQuery = "SELECT email FROM uvfood_user WHERE email = '" + email + "';";
                        ps = conn.prepareStatement(verEmailQuery);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            result = "error.email.existe";
                        } else {
                            
                            result = ManejadorUpdate(vista, usuario, nombre, apellido, fecha, email, clave, idUser, rol);
                        }
                        
                    }
                }
                
            } else if (!email.equals(emailTem)) {
                
                String verEmailQuery = "SELECT email FROM uvfood_user WHERE email = '" + email + "';";
                ps = conn.prepareStatement(verEmailQuery);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    result = "error.email.existe";
                } else {
                    if (!usuario.equals(usuarioTem)) {
                        String verUserQuery = "SELECT username FROM uvfood_user WHERE username = '" + usuario + "';";
                        ps = conn.prepareStatement(verUserQuery);
                        rs = ps.executeQuery();
                        
                        if (rs.next()) {
                            result = "error.usuario.existe";
                        } else {
                            result = ManejadorUpdate(vista, usuario, nombre, apellido, fecha, email, clave, idUser, rol);
                        }
                    }
                    
                }
                
            } else {
                result = ManejadorUpdate(vista, usuario, nombre, apellido, fecha, email, clave, idUser, rol);
            }
            
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = "error.sql.error";
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "error.NP.error";
        }
        return result;
    }
    
    public String disableUser(VistaAdmin vista) {
        String result = "";
        String idTem = vista.jTextFieldIdUser.getText();
        int id = Integer.parseInt(idTem);
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conn = Conexion();
            
            String sql = "UPDATE uvfood_user SET is_active = 0 WHERE iduser = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            
            if (res > 0) {
                result = "success";
                llenarTabla(vista);
                llenarAcciones(vista);
                
            } else {
                result = "error";
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
        } catch (ArrayIndexOutOfBoundsException ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
        }
        
        return result;
    }
    
    public String enableUser(VistaAdmin vista) {
        String result = "";
        String idTem = vista.jTextFieldIdUser.getText();
        int id = Integer.parseInt(idTem);
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conn = Conexion();
            
            String sql = "UPDATE uvfood_user SET is_active = 1 WHERE iduser = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            
            if (res > 0) {
                result = "success";
                llenarTabla(vista);
                llenarAcciones(vista);
                
            } else {
                result = "error";
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            result = "ex";
        } catch (NullPointerException np) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + np.getMessage() + " " + np.toString());
            result = "np";
        } catch (ArrayIndexOutOfBoundsException ai) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ai.getMessage() + " " + ai.toString());
            result = "ai";
        }
        
        return result;
    }
    
}
