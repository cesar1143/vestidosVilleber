/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import control.ImagePreview;
import control.Utils;
import control.controlInicioSesion;
import control.controlProductos;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class registrarProducto2 extends javax.swing.JFrame {

    Image foto;
    File file;
    //ESTOS  VALORES defaultTabla-->SON LLENADOS DESDE LA PANTALLA PRINCIPAL
    public static DefaultTableModel defaultTabla= new DefaultTableModel();
    public static JTable tabla = new JTable();
//=========================== INSTANCIAMOS LA CLASES QUE UTILIZAREMOS ======================================
     validarCampos validar = new validarCampos();
    
     controlProductos controlPro= new controlProductos();
    /**
     * Creates new form registrarProducto
     */
    public registrarProducto2() {
        initComponents();
        principalUsuarios.frameregistrarProducto2=this;
         
        this.setTitle("Registrar Producto");
        this.setLocationRelativeTo(null);
        //VALIDAMOS LOS CAMPOS
        validar.soloNumeros(txtClaveRegistrarProducto);
        validar.soloLetras(txtNombreRegistrarProducto);
        validar.soloNumeros(txtPrecioRegistrarProducto);
        validar.soloLetras(txtColorRegistrarProducto);
        validar.soloNumeros(txtCantidadRegistrarProducto);
        validar.soloNumerosYLetras(txtDescripcionRegistrarProducto);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtClaveRegistrarProducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNombreRegistrarProducto = new javax.swing.JTextField();
        txtPrecioRegistrarProducto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtColorRegistrarProducto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCantidadRegistrarProducto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        spinnerRegistrarProducto = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcionRegistrarProducto = new javax.swing.JTextArea();
        btnAceptarRegistrarProducto = new javax.swing.JButton();
        btnCancelarRegistrarProducto = new javax.swing.JButton();
        btnBuscarFotoRegistrarProducto = new javax.swing.JButton();
        labelFotoRegistrarProducto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Clave:");

        txtClaveRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtClaveRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Nombre:");

        txtNombreRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRegistrarProductoKeyTyped(evt);
            }
        });

        txtPrecioRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrecioRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Precio:");

        txtColorRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtColorRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColorRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Color:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Cantidad:");

        txtCantidadRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCantidadRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Descripción:");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Tipo:");

        spinnerRegistrarProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        spinnerRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spinnerRegistrarProductoActionPerformed(evt);
            }
        });

        txtDescripcionRegistrarProducto.setColumns(20);
        txtDescripcionRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescripcionRegistrarProducto.setRows(5);
        txtDescripcionRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionRegistrarProductoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtDescripcionRegistrarProducto);

        btnAceptarRegistrarProducto.setText("Registrar");
        btnAceptarRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarRegistrarProductoActionPerformed(evt);
            }
        });

        btnCancelarRegistrarProducto.setText("Cancelar");
        btnCancelarRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegistrarProductoActionPerformed(evt);
            }
        });

        btnBuscarFotoRegistrarProducto.setText("Buscar foto");
        btnBuscarFotoRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFotoRegistrarProductoActionPerformed(evt);
            }
        });

        labelFotoRegistrarProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCantidadRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtClaveRegistrarProducto)
                                    .addComponent(txtNombreRegistrarProducto)
                                    .addComponent(txtPrecioRegistrarProducto)
                                    .addComponent(txtColorRegistrarProducto)
                                    .addComponent(spinnerRegistrarProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarFotoRegistrarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(labelFotoRegistrarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)))
                .addGap(166, 166, 166))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnAceptarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCancelarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtClaveRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtPrecioRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtColorRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(spinnerRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelFotoRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtCantidadRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFotoRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel15))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistrarProductoActionPerformed
controlPro.btnCancelar(txtClaveRegistrarProducto, txtNombreRegistrarProducto, txtPrecioRegistrarProducto, txtColorRegistrarProducto, txtCantidadRegistrarProducto, txtDescripcionRegistrarProducto, labelFotoRegistrarProducto);
    }//GEN-LAST:event_btnCancelarRegistrarProductoActionPerformed

    private void btnBuscarFotoRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFotoRegistrarProductoActionPerformed
        //tutorial   https://codigo--java.blogspot.mx/2014/06/uso-basico-de-elementos-swing-con_5.html
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de archivos JPEG(*.JPG ;*.JEPG;*.PNG)", "jpg", "jpeg", "png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
//=============== pruebass ============
//Creamos un filtro de imágenes utilizando nuestra clase Utils
        archivo.addChoosableFileFilter(new FileFilter() {
            //Los métos siguientes son implementaciones
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String exts = Utils.getExtension(f);
                if (exts != null) {
                    if (exts.equals(Utils.tiff)
                            || exts.equals(Utils.tif)
                            || exts.equals(Utils.gif)
                            || exts.equals(Utils.jpeg)
                            || exts.equals(Utils.jpg)
                            || exts.equals(Utils.png)) {
                        return true;
                    } else {
                        return false;
                    }
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "Imágenes";
            }
        });
        //Establecemos un accesorio que será nuestra vista previa.
        archivo.setAccessory(new ImagePreview(archivo));
        //showOpenDialog para JFileChooser de apertura.
        //Guardamos el valor en un entero, pues es lo que retorna.
//        int rs = archivo.showOpenDialog(this);
        //Comprabamos el retorno como vemos más abajo.

//================ hasta aqui=============
        archivo.setDialogTitle("Abrir archivo");

        int ventana = archivo.showOpenDialog(null);

        if (ventana == JFileChooser.APPROVE_OPTION) {
            file = archivo.getSelectedFile();

            foto = getToolkit().getImage(String.valueOf(file));
            // getImage(String.valueOf(file));
            foto = foto.getScaledInstance(127, 190, Image.SCALE_DEFAULT);
            labelFotoRegistrarProducto.setIcon(new ImageIcon(foto));

        }
    }//GEN-LAST:event_btnBuscarFotoRegistrarProductoActionPerformed

    private void btnAceptarRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarRegistrarProductoActionPerformed
      String tipo= spinnerRegistrarProducto.getSelectedItem().toString();
     
        controlPro.registrar2(txtClaveRegistrarProducto, txtNombreRegistrarProducto, txtPrecioRegistrarProducto, txtColorRegistrarProducto, tipo, txtCantidadRegistrarProducto, txtDescripcionRegistrarProducto, labelFotoRegistrarProducto, file,tabla,defaultTabla);
    }//GEN-LAST:event_btnAceptarRegistrarProductoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
 
        principalUsuarios.controlregistrarProducto2=false;
    }//GEN-LAST:event_formWindowClosing

    private void txtNombreRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRegistrarProductoKeyTyped
       if(txtNombreRegistrarProducto.getText().length()== 19)
        {
            evt.consume();

        }
    }//GEN-LAST:event_txtNombreRegistrarProductoKeyTyped

    private void txtClaveRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveRegistrarProductoKeyTyped
        if(txtClaveRegistrarProducto.getText().length()== 39)
        {
            evt.consume();

        }
    }//GEN-LAST:event_txtClaveRegistrarProductoKeyTyped

    private void txtPrecioRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioRegistrarProductoKeyTyped
        if(txtPrecioRegistrarProducto.getText().length()== 10)
        {
            evt.consume();

        }
    }//GEN-LAST:event_txtPrecioRegistrarProductoKeyTyped

    private void txtColorRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorRegistrarProductoKeyTyped
       if(txtColorRegistrarProducto.getText().length()== 19)
        {
            evt.consume();

        }
    }//GEN-LAST:event_txtColorRegistrarProductoKeyTyped

    private void txtCantidadRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadRegistrarProductoKeyTyped
        if(txtCantidadRegistrarProducto.getText().length()== 10)
        {
            evt.consume();

        }
    }//GEN-LAST:event_txtCantidadRegistrarProductoKeyTyped

    private void txtDescripcionRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionRegistrarProductoKeyTyped
        if(txtDescripcionRegistrarProducto.getText().length()== 99)
        {
            evt.consume();

        }
    }//GEN-LAST:event_txtDescripcionRegistrarProductoKeyTyped

    private void spinnerRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spinnerRegistrarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spinnerRegistrarProductoActionPerformed

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
            java.util.logging.Logger.getLogger(registrarProducto2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registrarProducto2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registrarProducto2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registrarProducto2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registrarProducto2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarRegistrarProducto;
    private javax.swing.JButton btnBuscarFotoRegistrarProducto;
    private javax.swing.JButton btnCancelarRegistrarProducto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelFotoRegistrarProducto;
    private javax.swing.JComboBox<String> spinnerRegistrarProducto;
    private javax.swing.JTextField txtCantidadRegistrarProducto;
    private javax.swing.JTextField txtClaveRegistrarProducto;
    private javax.swing.JTextField txtColorRegistrarProducto;
    private javax.swing.JTextArea txtDescripcionRegistrarProducto;
    private javax.swing.JTextField txtNombreRegistrarProducto;
    private javax.swing.JTextField txtPrecioRegistrarProducto;
    // End of variables declaration//GEN-END:variables
}
