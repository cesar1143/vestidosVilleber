/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NuevasPantallas;
//color rosa FF00BC para iconos

import static NuevasPantallas.principal.usuario;
import control.controlPagos;
import control.controlProductoPendientes;
import control.controlProductos;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mensajes.mensajeAdvertencia;
import pantallas.cambiarEstadoProductosApartados;
import pantallas.cambiarEstadoProductosApartados2019;
import pantallas.cambiarEstadoProductosEntregadosNoPagados2019;
import pantallas.cancelarProductosApartados;
import pantallas.cancelarProductosApartados2019;
import pantallas.editarProducto;
import pantallas.editarProductoPendientes2019;
import pantallas.verPagos;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class productosPendientes extends javax.swing.JFrame {
    //para el frame

    int x = 0, y = 0;
    controlProductoPendientes controlPendientes = new controlProductoPendientes();
    controlProductos controlPro = new controlProductos();
    validarCampos validar = new validarCampos();
    /**
     * Creates new form productosPendientes
     */
    public static DefaultTableModel tablaPendientes, tablaMedidas, tablaClientes;
    //recibimos el frame  de ver pagos
    public static JFrame frameverpagos;

    public productosPendientes() {
        tablaPendientes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaPendientes.setColumnIdentifiers(new Object[]{"Id", "Clave","Nombre","Precio", "Estado", "Cantidad", "Fecha prueba", "Fecha Evento"});
        tablaMedidas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaMedidas.setColumnIdentifiers(new Object[]{"Id", "Talle", "Sise", "Hombros", "Busto", "Largo falda", "Ancho puño", "Cintura", "Cadera"});

        tablaClientes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaClientes.setColumnIdentifiers(new Object[]{"id", "Nombre", "Teléfono"});

        initComponents();
        this.setState(MAXIMIZED_BOTH);
        tbClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tbClientes.getColumnModel().getColumn(0).setMinWidth(0);
        tbClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbClientes.getColumnModel().getColumn(0).setWidth(0);
        tbClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
//tbClientes.getColumnModel().getColumn(2).setMaxWidth(150);
        tbClientes.getColumnModel().getColumn(1).setMinWidth(200);

//ocultar columna de tbpendientes
        jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable4.getColumnModel().getColumn(0).setMinWidth(0);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable4.getColumnModel().getColumn(0).setWidth(0);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable4.getColumnModel().getColumn(1).setMinWidth(80);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(130);
        jTable4.getColumnModel().getColumn(2).setMinWidth(130);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(160);
        jTable4.getColumnModel().getColumn(4).setMinWidth(160);

//ocultar columna de tbmedidas
        jTable5.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable5.getColumnModel().getColumn(0).setMinWidth(0);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable5.getColumnModel().getColumn(0).setWidth(0);

        controlPendientes.llenarTablaClientes(tbClientes, tablaClientes, jTable4, tablaPendientes,
                 jTable5, tablaMedidas, txtAreaDetalleProductoPendiente, labelFotoPendientes);
        //validar quien esta iniciando sesion 
        if (usuario.equalsIgnoreCase("Mayra")) {

        } else {
            btnCancelarPedidoPendientes.setVisible(false);
        }
        //validar  campos
        validar.soloNumerosYLetras(txtBuscarProductosPendientes);
        //mandamos el frame a pantalla  principal para controlarlo al abrirlo
        principal.frameproductosPendientes = this;

        //====================
        //mandar tabla pendietes aa cambiarEstadoProductosApartados
        cambiarEstadoProductosApartados.tablaPendientes = jTable4;
        //mandar tabla pendietes aa cambiarEstadoProductosApartados
        cambiarEstadoProductosApartados.defaultTablaPendientes = tablaPendientes;
        //mandar tabla pendientes a cancelar productos apartados2019
        cancelarProductosApartados2019.tablaPendientes = jTable4;

        cancelarProductosApartados2019.defaultTablaPendientes = tablaPendientes;

        //LLenamos la tabla pendientes
        // controlPendientes.llenarTablaPendientes(jTable4, tablaPendientes);
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
        labelFotoPendientes = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        txtBuscarProductosPendientes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        btnEditarProProductos = new javax.swing.JButton();
        btnCancelarPedidoPendientes = new javax.swing.JButton();
        btnEntregarProductoPendientes = new javax.swing.JButton();
        btnAbonarPagoPendientes = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaDetalleProductoPendiente = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos pendientes");
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

        labelFotoPendientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFotoPendientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        tbClientes.setModel(tablaClientes);
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbClientesMousePressed(evt);
            }
        });
        tbClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbClientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbClientes);

        txtBuscarProductosPendientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBuscarProductosPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosPendientesKeyReleased(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 0, 204));
        jLabel4.setForeground(new java.awt.Color(255, 0, 204));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizarTabla.png"))); // NOI18N
        jLabel4.setToolTipText("Actualiza la  tabla Clientes");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 204));
        jLabel32.setText("Ingresa el nombre del cliente que deseas buscar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtBuscarProductosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4))
            .addComponent(jLabel32)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProductosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos  pendientes", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        jTable4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable4.setModel(tablaPendientes);
        jTable4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable4.getTableHeader().setResizingAllowed(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTable4.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jTable4ComponentRemoved(evt);
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable4MousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        btnEditarProProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnEditarProProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarProProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarProProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editar.png"))); // NOI18N
        btnEditarProProductos.setText("Editar ");
        btnEditarProProductos.setToolTipText("Para editar la foto y la descripcion del producto apartado");
        btnEditarProProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProProductosActionPerformed(evt);
            }
        });

        btnCancelarPedidoPendientes.setBackground(new java.awt.Color(255, 0, 204));
        btnCancelarPedidoPendientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarPedidoPendientes.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarPedidoPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        btnCancelarPedidoPendientes.setText("Cancelar");
        btnCancelarPedidoPendientes.setToolTipText("Para cancelar  un producto que esta apartado");
        btnCancelarPedidoPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarPedidoPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoPendientesActionPerformed(evt);
            }
        });

        btnEntregarProductoPendientes.setBackground(new java.awt.Color(255, 0, 204));
        btnEntregarProductoPendientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEntregarProductoPendientes.setForeground(new java.awt.Color(255, 255, 255));
        btnEntregarProductoPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/entregar.png"))); // NOI18N
        btnEntregarProductoPendientes.setText("Entregar ");
        btnEntregarProductoPendientes.setToolTipText("Para entregar un producto que ya esta pagado");
        btnEntregarProductoPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntregarProductoPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregarProductoPendientesActionPerformed(evt);
            }
        });

        btnAbonarPagoPendientes.setBackground(new java.awt.Color(255, 0, 204));
        btnAbonarPagoPendientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAbonarPagoPendientes.setForeground(new java.awt.Color(255, 255, 255));
        btnAbonarPagoPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/abonar.png"))); // NOI18N
        btnAbonarPagoPendientes.setText("Abonar");
        btnAbonarPagoPendientes.setToolTipText("Para registrar un abono de un producto apartado");
        btnAbonarPagoPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAbonarPagoPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarPagoPendientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btnAbonarPagoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEntregarProductoPendientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarPedidoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarProProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbonarPagoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntregarProductoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarPedidoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarProProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medidas y detalles del producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        jTable5.setBackground(new java.awt.Color(229, 222, 222));
        jTable5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable5.setModel(tablaMedidas);
        jTable5.getTableHeader().setResizingAllowed(false);
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable5);

        txtAreaDetalleProductoPendiente.setEditable(false);
        txtAreaDetalleProductoPendiente.setBackground(new java.awt.Color(229, 222, 222));
        txtAreaDetalleProductoPendiente.setColumns(20);
        txtAreaDetalleProductoPendiente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAreaDetalleProductoPendiente.setRows(5);
        jScrollPane7.setViewportView(txtAreaDetalleProductoPendiente);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFotoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1099, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelFotoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap(2470, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarProductosPendientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosPendientesKeyReleased
        //limpiamos la tabla pendientes,medidas,txtDescripcion  y label foto

        if (txtBuscarProductosPendientes.getText().isEmpty()) {

            controlPendientes.llenarTablaClientes(tbClientes, tablaClientes, jTable4, tablaPendientes,
                     jTable5, tablaMedidas, txtAreaDetalleProductoPendiente, labelFotoPendientes);
        } else {
            controlPendientes.buscarClienteFiltrado(tbClientes, tablaClientes, txtBuscarProductosPendientes, jTable4, tablaPendientes,
                     jTable5, tablaMedidas, txtAreaDetalleProductoPendiente, labelFotoPendientes);
        }
    }//GEN-LAST:event_txtBuscarProductosPendientesKeyReleased

    private void jTable4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MousePressed
        int fila = jTable4.getSelectedRow();

        int valorId = Integer.parseInt(jTable4.getValueAt(fila, 0) + "");
        String valorClave = jTable4.getValueAt(fila, 1) + "";
        byte[] foto;
        controlPro.consultarImgYmedidas(valorId + "", labelFotoPendientes, jTable5, tablaMedidas, txtAreaDetalleProductoPendiente);
        //controlPro.mostrarImagen2(valorClave, labelFotoPendientes);
        //controlPendientes.mostrarMedidasProductos(valorId, jTable5, tablaMedidas, txtAreaDetalleProductoPendiente);
    }//GEN-LAST:event_jTable4MousePressed

    private void btnAbonarPagoPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarPagoPendientesActionPerformed
        //mostraremos una pantalla con los pagos,deduda y lo que resta
        if (jTable4.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (principal.controlverPagos == false) {
                int fila = jTable4.getSelectedRow();
                String estado = jTable4.getValueAt(fila, 4) + "";

                if (estado.equalsIgnoreCase("Apartado")) {
                    int filaCliente = tbClientes.getSelectedRow();
                    String idCliente = tbClientes.getValueAt(filaCliente, 0) + "";
                    String nombreCliente = tbClientes.getValueAt(filaCliente, 1) + "";
                    // new controlPagos().mostrarInformacionDeuda(nombre);
                    //enviamos la tabla

                    verPagos.idCliente = idCliente;
                    verPagos verP = new verPagos();
                    verPagos.txtNombreDelCiente.setText(nombreCliente);
                    verP.setVisible(true);

                    principal.controlverPagos = true;
                } else {
                    mensajeAdvertencia men = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("El cliente no tiene deduda");
                    men.setVisible(true);
                    men.setAlwaysOnTop(true);
                    // JOptionPane.showMessageDialog(null, "El cliente no tiene deduda", "Advertencia", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.frameverPagos.setAlwaysOnTop(true);
                principal.frameverPagos.setAlwaysOnTop(false);
            }

        }
    }//GEN-LAST:event_btnAbonarPagoPendientesActionPerformed
//para  obtener la deudaTotal 

    private void btnEntregarProductoPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregarProductoPendientesActionPerformed
        if (jTable4.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //  JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (principal.controlcambiarEstadoProductosEntregadosNoPagados2019 == false) {
                int fila = jTable4.getSelectedRow();
                String estado = jTable4.getValueAt(fila, 4) + "";

                if (estado.equalsIgnoreCase("Pagado no entregado")) {

                    //solo mandamos idCliente
                    int filaCliente = tbClientes.getSelectedRow();
                    String idCliente = tablaClientes.getValueAt(filaCliente, 0) + "";

                    cambiarEstadoProductosEntregadosNoPagados2019 c = new cambiarEstadoProductosEntregadosNoPagados2019();
                    cambiarEstadoProductosEntregadosNoPagados2019.idCliente = idCliente;
                    c.setVisible(true);
                    principal.controlcambiarEstadoProductosEntregadosNoPagados2019 = true;
                } else {
                    mensajeAdvertencia men = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("El cliente no tiene productos pendientes");
                    men.setVisible(true);
                    men.setAlwaysOnTop(true);
                    //  JOptionPane.showMessageDialog(null, "El cliente no tiene productos pendientes", "Advertencia", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);

                //  JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.framecambiarEstadoProductosEntregadosNoPagados2019.setAlwaysOnTop(true);
                principal.framecambiarEstadoProductosEntregadosNoPagados2019.setAlwaysOnTop(false);
            }

        }

    }//GEN-LAST:event_btnEntregarProductoPendientesActionPerformed

    private void btnCancelarPedidoPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoPendientesActionPerformed
        if (jTable4.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {

            if (principal.controlcancelarProductosApartados == false) {
                int fila = jTable4.getSelectedRow();
                String estado = jTable4.getValueAt(fila, 2) + "";

                if (estado.equalsIgnoreCase("Apartado")) {
                    String idCliente = tbClientes.getValueAt(fila, 0) + "";

                    cancelarProductosApartados2019 c = new cancelarProductosApartados2019();
                    cancelarProductosApartados2019.idCliente = idCliente;
                    c.setVisible(true);
                    principal.controlcancelarProductosApartados = true;
                } else {
                    mensajeAdvertencia men = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("El cliente no tiene productos pendientes");
                    men.setVisible(true);
                    men.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "El cliente no tiene productos pendientes", "Advertencia", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                // JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.framecancelarProductosApartados.setAlwaysOnTop(true);
                principal.framecancelarProductosApartados.setAlwaysOnTop(false);
            }

        }
    }//GEN-LAST:event_btnCancelarPedidoPendientesActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void jTable4ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable4ComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4ComponentRemoved

    private void btnEditarProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProProductosActionPerformed
        if (jTable4.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona un producto de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            if (principal.controleditarProductoPendientes2019 == false) {
                int fila = jTable4.getSelectedRow();
                String valorIdProApa = jTable4.getValueAt(fila, 0) + "";
             
              
                 editarProductoPendientes2019.idproApa = valorIdProApa ;
                editarProductoPendientes2019 ep = new editarProductoPendientes2019();
               
                ep.setVisible(true);
                principal.controleditarProductoPendientes2019 = true;
            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.frameeditarProductoPendientes2019.setAlwaysOnTop(true);
                principal.frameeditarProductoPendientes2019.setAlwaysOnTop(false);
            }

        }
    }//GEN-LAST:event_btnEditarProProductosActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        controlPendientes.llenarTablaClientes(tbClientes, tablaClientes, jTable4, tablaPendientes,
                 jTable5, tablaMedidas, txtAreaDetalleProductoPendiente, labelFotoPendientes);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void tbClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbClientesKeyPressed

    }//GEN-LAST:event_tbClientesKeyPressed

    private void tbClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMousePressed
        controlPendientes.llenarTablaPendientesNew(jTable4, tablaPendientes, tbClientes, tablaClientes, jTable5, tablaMedidas, txtAreaDetalleProductoPendiente, labelFotoPendientes);

    }//GEN-LAST:event_tbClientesMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        principal.controlproductosPendientes = false;
        controlPendientes.cerrarVentana();
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
            java.util.logging.Logger.getLogger(productosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new productosPendientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonarPagoPendientes;
    private javax.swing.JButton btnCancelarPedidoPendientes;
    private javax.swing.JButton btnEditarProProductos;
    private javax.swing.JButton btnEntregarProductoPendientes;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    public static javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    public static javax.swing.JLabel labelFotoPendientes;
    public static javax.swing.JTable tbClientes;
    public static javax.swing.JTextArea txtAreaDetalleProductoPendiente;
    private javax.swing.JTextField txtBuscarProductosPendientes;
    // End of variables declaration//GEN-END:variables
}
