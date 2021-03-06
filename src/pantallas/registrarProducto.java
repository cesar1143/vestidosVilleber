/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import NuevasPantallas.principal;
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
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author famsa
 */
public class registrarProducto extends javax.swing.JFrame {
    //para el frame

    int x = 0, y = 0;

    Image foto;
    File file;
    //ESTOS  VALORES defaultTabla-->SON LLENADOS DESDE LA PANTALLA PRINCIPAL
    public static DefaultTableModel defaultTabla = new DefaultTableModel();
    public static JTable tabla = new JTable();
//=========================== INSTANCIAMOS LA CLASES QUE UTILIZAREMOS ======================================
    validarCampos validar = new validarCampos();

    controlProductos controlPro = new controlProductos();

    /**
     * Creates new form registrarProducto
     */
    public registrarProducto() {
        initComponents();

        principal.frameregistrarProducto = this;
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
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
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

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jPanel11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel11KeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Codigo:");

        txtClaveRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtClaveRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        txtClaveRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClaveRegistrarProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Nombre:");

        txtNombreRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        txtNombreRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreRegistrarProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRegistrarProductoKeyTyped(evt);
            }
        });

        txtPrecioRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrecioRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        txtPrecioRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioRegistrarProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Precio:");

        txtColorRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtColorRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        txtColorRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtColorRegistrarProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColorRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Color:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Cantidad:");

        txtCantidadRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCantidadRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        txtCantidadRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadRegistrarProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadRegistrarProductoKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Descripción:");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Tipo:");

        spinnerRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spinnerRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        spinnerRegistrarProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boda", "Bautizo", "XV años", "XVIII años", "Plata", "Presentacion", "Trajes", "Ninguno" }));
        spinnerRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spinnerRegistrarProductoKeyPressed(evt);
            }
        });

        txtDescripcionRegistrarProducto.setColumns(20);
        txtDescripcionRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescripcionRegistrarProducto.setForeground(new java.awt.Color(255, 0, 204));
        txtDescripcionRegistrarProducto.setRows(5);
        txtDescripcionRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionRegistrarProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionRegistrarProductoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtDescripcionRegistrarProducto);

        btnAceptarRegistrarProducto.setBackground(new java.awt.Color(255, 0, 204));
        btnAceptarRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAceptarRegistrarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarRegistrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar.png"))); // NOI18N
        btnAceptarRegistrarProducto.setText("Registrar");
        btnAceptarRegistrarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptarRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarRegistrarProductoActionPerformed(evt);
            }
        });
        btnAceptarRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAceptarRegistrarProductoKeyPressed(evt);
            }
        });

        btnCancelarRegistrarProducto.setBackground(new java.awt.Color(255, 0, 204));
        btnCancelarRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarRegistrarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarRegistrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        btnCancelarRegistrarProducto.setText("F1- Cancelar");
        btnCancelarRegistrarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegistrarProductoActionPerformed(evt);
            }
        });
        btnCancelarRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarRegistrarProductoKeyPressed(evt);
            }
        });

        btnBuscarFotoRegistrarProducto.setBackground(new java.awt.Color(255, 0, 204));
        btnBuscarFotoRegistrarProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuscarFotoRegistrarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarFotoRegistrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarFotoRegistrarProducto.setText("Buscar foto");
        btnBuscarFotoRegistrarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarFotoRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFotoRegistrarProductoActionPerformed(evt);
            }
        });
        btnBuscarFotoRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarFotoRegistrarProductoKeyPressed(evt);
            }
        });

        labelFotoRegistrarProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelFotoRegistrarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labelFotoRegistrarProductoKeyPressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 0, 204));
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Registrar producto");
        jLabel18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel18KeyPressed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/minimizar.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jLabel19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel19KeyPressed(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jLabel20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel20KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtClaveRegistrarProducto)
                                    .addComponent(txtNombreRegistrarProducto)
                                    .addComponent(txtPrecioRegistrarProducto)
                                    .addComponent(txtColorRegistrarProducto)
                                    .addComponent(spinnerRegistrarProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCantidadRegistrarProducto)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFotoRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarFotoRegistrarProducto))))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnAceptarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 434, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        String tipo = spinnerRegistrarProducto.getSelectedItem().toString();

        controlPro.registrar(txtClaveRegistrarProducto, txtNombreRegistrarProducto, txtPrecioRegistrarProducto, txtColorRegistrarProducto, tipo, txtCantidadRegistrarProducto, txtDescripcionRegistrarProducto, labelFotoRegistrarProducto, file, tabla, defaultTabla);
    }//GEN-LAST:event_btnAceptarRegistrarProductoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        principal.controlregistrarProducto = false;

    }//GEN-LAST:event_formWindowClosing

    private void txtNombreRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRegistrarProductoKeyTyped
        if (txtNombreRegistrarProducto.getText().length() == 19) {
            evt.consume();

        }
    }//GEN-LAST:event_txtNombreRegistrarProductoKeyTyped

    private void txtClaveRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveRegistrarProductoKeyTyped
        if (txtClaveRegistrarProducto.getText().length() == 39) {
            evt.consume();

        }
    }//GEN-LAST:event_txtClaveRegistrarProductoKeyTyped

    private void txtPrecioRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioRegistrarProductoKeyTyped
        if (txtPrecioRegistrarProducto.getText().length() == 10) {
            evt.consume();

        }
    }//GEN-LAST:event_txtPrecioRegistrarProductoKeyTyped

    private void txtColorRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorRegistrarProductoKeyTyped
        if (txtColorRegistrarProducto.getText().length() == 19) {
            evt.consume();

        }
    }//GEN-LAST:event_txtColorRegistrarProductoKeyTyped

    private void txtCantidadRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadRegistrarProductoKeyTyped
        if (txtCantidadRegistrarProducto.getText().length() == 10) {
            evt.consume();

        }
    }//GEN-LAST:event_txtCantidadRegistrarProductoKeyTyped

    private void txtDescripcionRegistrarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionRegistrarProductoKeyTyped
        if (txtDescripcionRegistrarProducto.getText().length() == 99) {
            evt.consume();

        }
    }//GEN-LAST:event_txtDescripcionRegistrarProductoKeyTyped

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        principal.controlregistrarProducto = false;
        dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void jLabel18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel18KeyPressed
        metodosBotones(evt);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel18KeyPressed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4KeyPressed

    private void jPanel11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel11KeyPressed
       metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_jPanel11KeyPressed

    private void txtClaveRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveRegistrarProductoKeyPressed
       metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveRegistrarProductoKeyPressed

    private void txtNombreRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRegistrarProductoKeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreRegistrarProductoKeyPressed

    private void txtPrecioRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioRegistrarProductoKeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioRegistrarProductoKeyPressed

    private void txtColorRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorRegistrarProductoKeyPressed
        metodosBotones(evt);// TODO add your handling code here:
    }//GEN-LAST:event_txtColorRegistrarProductoKeyPressed

    private void spinnerRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spinnerRegistrarProductoKeyPressed
       metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_spinnerRegistrarProductoKeyPressed

    private void txtCantidadRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadRegistrarProductoKeyPressed
       metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadRegistrarProductoKeyPressed

    private void txtDescripcionRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionRegistrarProductoKeyPressed
       metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionRegistrarProductoKeyPressed

    private void btnCancelarRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarRegistrarProductoKeyPressed
     metodosBotones(evt);   // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarRegistrarProductoKeyPressed

    private void btnAceptarRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarRegistrarProductoKeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarRegistrarProductoKeyPressed

    private void labelFotoRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labelFotoRegistrarProductoKeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_labelFotoRegistrarProductoKeyPressed

    private void btnBuscarFotoRegistrarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarFotoRegistrarProductoKeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarFotoRegistrarProductoKeyPressed

    private void jLabel19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel19KeyPressed
      metodosBotones(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19KeyPressed

    private void jLabel20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel20KeyPressed
     metodosBotones(evt);   // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20KeyPressed

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
            java.util.logging.Logger.getLogger(registrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registrarProducto().setVisible(true);
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
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

    public void metodosBotones(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            principal.controlregistrarProducto = false;
            dispose();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tipo = spinnerRegistrarProducto.getSelectedItem().toString();

            controlPro.registrar(txtClaveRegistrarProducto, txtNombreRegistrarProducto, txtPrecioRegistrarProducto, txtColorRegistrarProducto, tipo, txtCantidadRegistrarProducto, txtDescripcionRegistrarProducto, labelFotoRegistrarProducto, file, tabla, defaultTabla);
        } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
            controlPro.btnCancelar(txtClaveRegistrarProducto, txtNombreRegistrarProducto, txtPrecioRegistrarProducto, txtColorRegistrarProducto, txtCantidadRegistrarProducto, txtDescripcionRegistrarProducto, labelFotoRegistrarProducto);

        }
    }
}
