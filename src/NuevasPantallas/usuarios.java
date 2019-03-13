/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NuevasPantallas;

import control.controlInicioSesion;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mensajes.mensajeAdvertencia;
import static pantallas.editarUsuario.tablaUsuarios;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class usuarios extends javax.swing.JFrame {

    //para el frame
    int x = 0, y = 0;
    controlInicioSesion controlUsuario = new controlInicioSesion();
    validarCampos validar = new validarCampos();
    DefaultTableModel tablaUsuarios;

    /**
     * Creates new form usuarios
     */
    public usuarios() {
        tablaUsuarios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaUsuarios.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Amaterno", "Apaterno", "Usuario", "Contraseña"});

        initComponents();
        //OCULTAMOS EL ID

        //validamos los campos
        validar.soloLetras(txtNombreUsuarios);
        validar.soloLetras(txtApaternoUsuarios);
        validar.soloLetras(txtAmaternoUsuarios);
        validar.soloLetras(txtUsuarioUsuarios);
        validar.soloNumerosYLetras(txtContraseñaUsuarios);
        //mandamos el frame a pantalla  principal para controlarlo al abrirlo
        principal.frameusuarios = this;
        this.setState(MAXIMIZED_BOTH);
        //llenamos la tabla
        controlUsuario.llenarTablaUsuarios(jTable6, tablaUsuarios);
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
        jPanel9 = new javax.swing.JPanel();
        txtNombreUsuarios = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtApaternoUsuarios = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAmaternoUsuarios = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtUsuarioUsuarios = new javax.swing.JTextField();
        txtContraseñaUsuarios = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        btnRegistrarUsuarios = new javax.swing.JButton();
        btnCancelarUsuarios = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        btnEditarUsuarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pantalla usuarios");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Registro de  usuarios", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        txtNombreUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUsuariosKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 204));
        jLabel9.setText("Nombre:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 204));
        jLabel10.setText("Apellido  Paterno:");

        txtApaternoUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtApaternoUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApaternoUsuariosKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 204));
        jLabel11.setText("Apellido Materno");

        txtAmaternoUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAmaternoUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmaternoUsuariosKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 204));
        jLabel12.setText("Usuario:");

        txtUsuarioUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUsuarioUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioUsuariosKeyTyped(evt);
            }
        });

        txtContraseñaUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaUsuariosKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 204));
        jLabel13.setText("Contraseña:");

        btnRegistrarUsuarios.setBackground(new java.awt.Color(255, 0, 204));
        btnRegistrarUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrarUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar.png"))); // NOI18N
        btnRegistrarUsuarios.setText("Registrar ");
        btnRegistrarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuariosActionPerformed(evt);
            }
        });

        btnCancelarUsuarios.setBackground(new java.awt.Color(255, 0, 204));
        btnCancelarUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        btnCancelarUsuarios.setText("Cancelar");
        btnCancelarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrarUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarUsuarios)
                .addGap(56, 56, 56))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(txtNombreUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtApaternoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtAmaternoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtUsuarioUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtContraseñaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jLabel9)
                            .addGap(108, 108, 108)
                            .addComponent(jLabel10)
                            .addGap(41, 41, 41)
                            .addComponent(jLabel11)
                            .addGap(79, 79, 79)
                            .addComponent(jLabel12)
                            .addGap(98, 98, 98)
                            .addComponent(jLabel13)))
                    .addContainerGap(310, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(txtUsuarioUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel13))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtContraseñaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombreUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApaternoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAmaternoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(26, Short.MAX_VALUE)))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios Registrados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        jTable6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable6.setModel(tablaUsuarios);
        jTable6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable6.getTableHeader().setResizingAllowed(false);
        jTable6.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable6);

        btnEditarUsuarios.setBackground(new java.awt.Color(255, 0, 204));
        btnEditarUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editar.png"))); // NOI18N
        btnEditarUsuarios.setText("Editar");
        btnEditarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnEditarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(377, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreUsuariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuariosKeyTyped
        if (txtNombreUsuarios.getText().length() == 19) {
            evt.consume();

        }
    }//GEN-LAST:event_txtNombreUsuariosKeyTyped

    private void txtApaternoUsuariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApaternoUsuariosKeyTyped
        if (txtApaternoUsuarios.getText().length() == 19) {
            evt.consume();

        }
    }//GEN-LAST:event_txtApaternoUsuariosKeyTyped

    private void txtAmaternoUsuariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmaternoUsuariosKeyTyped
        if (txtAmaternoUsuarios.getText().length() == 19) {
            evt.consume();

        }
    }//GEN-LAST:event_txtAmaternoUsuariosKeyTyped

    private void txtUsuarioUsuariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioUsuariosKeyTyped
        if (txtUsuarioUsuarios.getText().length() == 19) {
            evt.consume();

        }
    }//GEN-LAST:event_txtUsuarioUsuariosKeyTyped

    private void txtContraseñaUsuariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaUsuariosKeyTyped
        if (txtContraseñaUsuarios.getText().length() == 9) {
            evt.consume();

        }
    }//GEN-LAST:event_txtContraseñaUsuariosKeyTyped

    private void btnRegistrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuariosActionPerformed
        controlUsuario.Regsitrar(txtNombreUsuarios, txtApaternoUsuarios, txtAmaternoUsuarios, txtUsuarioUsuarios, txtContraseñaUsuarios, jTable6, tablaUsuarios);
    }//GEN-LAST:event_btnRegistrarUsuariosActionPerformed

    private void btnCancelarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUsuariosActionPerformed
        controlUsuario.cancelar(txtNombreUsuarios, txtApaternoUsuarios, txtAmaternoUsuarios, txtUsuarioUsuarios, txtContraseñaUsuarios);
    }//GEN-LAST:event_btnCancelarUsuariosActionPerformed

    private void btnEditarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuariosActionPerformed
        if (jTable6.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Selecciona una fila  de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (principal.controleditarUsuario == false) {
                int fila = jTable6.getSelectedRow();
                Object valorId = jTable6.getValueAt(fila, 0);
                controlUsuario.btnEditar(valorId + "", jTable6, tablaUsuarios);
                principal.controleditarUsuario = true;
            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.frameeditarUsuario.setAlwaysOnTop(true);
                principal.frameeditarUsuario.setAlwaysOnTop(false);
            }

        }
    }//GEN-LAST:event_btnEditarUsuariosActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (principal.controleditarUsuario == false) {

        } else {
            principal.controleditarUsuario = false;
            principal.frameeditarUsuario.dispose();
        }
        principal.controlusuarios = false;
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarUsuarios;
    private javax.swing.JButton btnEditarUsuarios;
    private javax.swing.JButton btnRegistrarUsuarios;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField txtAmaternoUsuarios;
    private javax.swing.JTextField txtApaternoUsuarios;
    private javax.swing.JPasswordField txtContraseñaUsuarios;
    private javax.swing.JTextField txtNombreUsuarios;
    private javax.swing.JTextField txtUsuarioUsuarios;
    // End of variables declaration//GEN-END:variables
}
