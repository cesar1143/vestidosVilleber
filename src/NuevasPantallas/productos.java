/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NuevasPantallas;

import static NuevasPantallas.principal.usuario;
import beans.Productos;
import control.controlInicioSesion;
import control.controlProductos;
import control.controlVentas;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mensajes.cargando;
import mensajes.mensajeAdvertencia;
import static pantallas.cambiarEstadoProductosApartados.tablaPendientes;
import pantallas.detallesDelProducto;
import pantallas.detallesVenderProducto;
import pantallas.editarProducto;
import pantallas.editarProducto2;
import pantallas.nuevoRegistro;
import pantallas.registrarCliente;
import pantallas.registrarProducto;
import pantallas.ventaRapida;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class productos extends javax.swing.JFrame implements Runnable{

    //para el reloj
    String hora, minuto, segundo;

    Thread hilo;
    //para el frame

    int x = 0, y = 0;

    //============================= DECLARACION DE VARIABLES ===============================================
    public static String idUSuario, nombreUSuario;

//=========================== INSTANCIAMOS LA CLASES QUE UTILIZAREMOS ======================================
    validarCampos validar = new validarCampos();

    controlInicioSesion controlInicioSesion = new controlInicioSesion();
    controlProductos controlPro = new controlProductos();
    controlVentas controlVen = new controlVentas();

    //============================ INSTANCIA DE LA TABLAS =========================================================
    DefaultTableModel tablaProductos, tablaVentas;

    public productos() {
        //============================================= DECLARACION DE LA TABLAS ===============================       
        tablaProductos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaProductos.setColumnIdentifiers(new Object[]{"Id", "Clave", "Nombre", "Descripcion", "Existencias", "Precio"});
        tablaVentas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else {
                    return false;
                }
                //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaVentas.setColumnIdentifiers(new Object[]{"Id", "Clave", "Cantidad", "Precio", "Subtotal"});
        initComponents();
          hilo=new Thread(this);
       // txtReloj.setText(fechaActual);
        hilo.start();
        //validar quien esta iniciando sesion 
        if (usuario.equalsIgnoreCase("Mayra")) {

        } else {
            btnRegistrarProProductos.setVisible(false);
            btnEditarProProductos.setVisible(false);
            btnEliminarProProductos.setVisible(false);
        }
        //mandamos el frame a pantalla  principal para controlarlo al abrirlo
        principal.frameproductos = this;

        txtPersonaQueAtiendeProductos.setText(principal.nombreUsuario);
        this.setState(MAXIMIZED_BOTH);

        //LLENAMOS LA TABLA DE PRODUCTOS
        controlPro.llenarTabla(jTable1, tablaProductos);
//VALIDAMOS LOS CAMPOS DE LA VENTANA PRODUCTOS
        validar.soloNumerosYLetras(txtBuscarProductos);
        validar.soloNumeros(txtEfectivoRecibidoProductos);
        //================================ MANDAMOS LAS TABLAS PARA PODER ACTUALIZARLAS AL DISTINTAS PANTALLAS ==========   
        editarProducto.tabla = jTable1;
        editarProducto.defaultTabla = tablaProductos;

        registrarProducto.tabla = jTable1;
        registrarProducto.defaultTabla = tablaProductos;

        detallesDelProducto.tablaProductos = jTable1;
        detallesDelProducto.defaultTablaProductos = tablaProductos;

        detallesVenderProducto.tablaProductos = jTable1;
        detallesVenderProducto.defaultTablaProductos = tablaProductos;

        registrarCliente.tablaProductos = jTable1;
        registrarCliente.defaultTablaProductos = tablaProductos;

        detallesDelProducto.tablaVentas = jTable2;
        detallesDelProducto.defaultTablaVentas = tablaVentas;
        detallesDelProducto.txtTotalAPagar = txtTotalPagarProductos;

        detallesVenderProducto.tablaVentas = jTable2;
        detallesVenderProducto.defaultTablaVentas = tablaVentas;
        detallesVenderProducto.txtTotalAPagar = txtTotalPagarProductos;
        //tabla ventas
        //enviar datos a registrarCliente
        registrarCliente.txtTotalApagar = txtTotalPagarProductos;
        registrarCliente.txtEfectivoRecibido = txtEfectivoRecibidoProductos;
        registrarCliente.txtCambio = txtCambioProductos;
        registrarCliente.tablaVentas = jTable2;
        registrarCliente.defaultTablaVentas = tablaVentas;
        registrarCliente.tablaProductos = jTable1;
        registrarCliente.defaultTablaProductos = tablaProductos;
        //recibo  el frame  de cliente con deuda
        //enviasmos datos a nuevo registro
        nuevoRegistro.tablaVentas = jTable2;
        nuevoRegistro.defaultTablaVentas = tablaVentas;
        nuevoRegistro.txtTotalApagar = txtTotalPagarProductos;
        //los enviamos  a pantalla venta rapida
        ventaRapida.tablaVentas = jTable2;
        ventaRapida.defaultTablaVentas = tablaVentas;
        ventaRapida.txtTotalAPagar = txtTotalPagarProductos;
    }
     public void   hora(){
        Calendar calen= new GregorianCalendar();
        Date  horaActual=new Date();
        calen.setTime(horaActual);
        hora=calen.get(Calendar.HOUR_OF_DAY)>9?""+calen.get(Calendar.HOUR_OF_DAY):"0"+calen.get(Calendar.HOUR_OF_DAY);
        minuto=calen.get(Calendar.MINUTE)>9?""+calen.get(Calendar.MINUTE):"0"+calen.get(Calendar.MINUTE);
        segundo=calen.get(Calendar.SECOND)>9?""+calen.get(Calendar.SECOND):"0"+calen.get(Calendar.SECOND);
    }
    public String obtenerFecha(){
         //para el reloj
        Calendar fecha1= Calendar.getInstance();
         int dia=fecha1.get(Calendar.DATE);
         int mes=fecha1.get(Calendar.MONTH);
        int  año=fecha1.get(Calendar.YEAR);
        String fechaActual=dia+"-"+mes+"-"+año;
        
        return fechaActual;
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
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEliminarProProductos = new javax.swing.JButton();
        btnBuscarProductos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarProductos = new javax.swing.JTextField();
        btnRegistrarProProductos = new javax.swing.JButton();
        btnActualizarTablaProductos = new javax.swing.JButton();
        btnEditarProProductos = new javax.swing.JButton();
        btnVenderProProductos = new javax.swing.JButton();
        labelFotoVerProductosProductos = new javax.swing.JLabel();
        btnVenderProProductos3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtNombreProductoProductos = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtDescripcionProductos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtTotalPagarProductos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEfectivoRecibidoProductos = new javax.swing.JTextField();
        txtCambioProductos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnRegistrarVentaProductos = new javax.swing.JButton();
        btnCancelarVentaProductos = new javax.swing.JButton();
        btnQuitarProductoProductos = new javax.swing.JButton();
        labelFotoVentaProductos = new javax.swing.JLabel();
        txtReloj = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPersonaQueAtiendeProductos = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1575, 867));
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

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(tablaProductos);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnEliminarProProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnEliminarProProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEliminarProProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarProProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eliminar.png"))); // NOI18N
        btnEliminarProProductos.setText("Eliminar Producto");
        btnEliminarProProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProProductosActionPerformed(evt);
            }
        });

        btnBuscarProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnBuscarProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuscarProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btnBuscarProductos.setText("Buscar Producto");
        btnBuscarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 204));
        jLabel2.setText("Ingresa Nombre, Codigo o Descripcion del producto que desea buscar:");

        txtBuscarProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosKeyReleased(evt);
            }
        });

        btnRegistrarProProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnRegistrarProProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrarProProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarProProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar.png"))); // NOI18N
        btnRegistrarProProductos.setText("Registrar Producto");
        btnRegistrarProProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProProductosActionPerformed(evt);
            }
        });

        btnActualizarTablaProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnActualizarTablaProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizarTablaProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarTablaProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar.png"))); // NOI18N
        btnActualizarTablaProductos.setText("Actualizar Tabla");
        btnActualizarTablaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarTablaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaProductosActionPerformed(evt);
            }
        });

        btnEditarProProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnEditarProProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarProProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarProProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editar.png"))); // NOI18N
        btnEditarProProductos.setText("Editar Producto");
        btnEditarProProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProProductosActionPerformed(evt);
            }
        });

        btnVenderProProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnVenderProProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVenderProProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnVenderProProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/vender.png"))); // NOI18N
        btnVenderProProductos.setText("Vender Producto");
        btnVenderProProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVenderProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderProProductosActionPerformed(evt);
            }
        });

        labelFotoVerProductosProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnVenderProProductos3.setBackground(new java.awt.Color(255, 0, 204));
        btnVenderProProductos3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVenderProProductos3.setForeground(new java.awt.Color(255, 255, 255));
        btnVenderProProductos3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar.png"))); // NOI18N
        btnVenderProProductos3.setText("Nuevo Registro");
        btnVenderProProductos3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVenderProProductos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderProProductos3ActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre y descripcion del producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 0, 204))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 0, 204));
        jLabel33.setText("Nombre:");

        txtNombreProductoProductos.setEditable(false);
        txtNombreProductoProductos.setBackground(new java.awt.Color(229, 222, 222));
        txtNombreProductoProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 204));
        jLabel34.setText("Descripción:");

        txtDescripcionProductos.setEditable(false);
        txtDescripcionProductos.setBackground(new java.awt.Color(229, 222, 222));
        txtDescripcionProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcionProductos)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreProductoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtNombreProductoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionProductos))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ventarapida.png"))); // NOI18N
        jLabel1.setText("Venta rapida");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnVenderProProductos3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnVenderProProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditarProProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRegistrarProProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarProProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelFotoVerProductosProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnBuscarProductos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizarTablaProductos)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnVenderProProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVenderProProductos3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarProProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnEditarProProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelFotoVerProductosProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta de productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        txtTotalPagarProductos.setEditable(false);
        txtTotalPagarProductos.setBackground(new java.awt.Color(204, 204, 204));
        txtTotalPagarProductos.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        txtTotalPagarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPagarProductosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Total A pagar:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Efectivo recibido:");

        txtEfectivoRecibidoProductos.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        txtEfectivoRecibidoProductos.setForeground(new java.awt.Color(0, 0, 204));
        txtEfectivoRecibidoProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoRecibidoProductosKeyReleased(evt);
            }
        });

        txtCambioProductos.setEditable(false);
        txtCambioProductos.setBackground(new java.awt.Color(204, 204, 204));
        txtCambioProductos.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        txtCambioProductos.setForeground(java.awt.Color.blue);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Cambio:");

        jTable2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable2.setModel(tablaVentas);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        btnRegistrarVentaProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnRegistrarVentaProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrarVentaProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVentaProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar.png"))); // NOI18N
        btnRegistrarVentaProductos.setText("Registrar Venta");
        btnRegistrarVentaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarVentaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaProductosActionPerformed(evt);
            }
        });

        btnCancelarVentaProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnCancelarVentaProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarVentaProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarVentaProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        btnCancelarVentaProductos.setText("Cancelar Venta");
        btnCancelarVentaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVentaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaProductosActionPerformed(evt);
            }
        });

        btnQuitarProductoProductos.setBackground(new java.awt.Color(255, 0, 204));
        btnQuitarProductoProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnQuitarProductoProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitarProductoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/quitar.png"))); // NOI18N
        btnQuitarProductoProductos.setText("Quitar Producto ");
        btnQuitarProductoProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitarProductoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoProductosActionPerformed(evt);
            }
        });

        labelFotoVentaProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtReloj.setEditable(false);
        txtReloj.setBackground(new java.awt.Color(229, 222, 222));
        txtReloj.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 204));
        jLabel7.setText("Fecha Actual:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 204));
        jLabel8.setText("Persona que atiende:");

        txtPersonaQueAtiendeProductos.setEditable(false);
        txtPersonaQueAtiendeProductos.setBackground(new java.awt.Color(229, 222, 222));
        txtPersonaQueAtiendeProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnRegistrarVentaProductos)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarVentaProductos)
                        .addGap(20, 20, 20)
                        .addComponent(btnQuitarProductoProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalPagarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEfectivoRecibidoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCambioProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPersonaQueAtiendeProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtReloj)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelFotoVentaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPersonaQueAtiendeProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelFotoVentaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTotalPagarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtEfectivoRecibidoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCambioProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuitarProductoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarVentaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarVentaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 0, 204));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Registro y  ventas de productos");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/minimizar.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        //cuando seleccionemostraremos la foto del producto si es que tiene
        byte[] foto;
        int fila = jTable1.getSelectedRow();
        String valorId = jTable1.getValueAt(fila, 0) + "";
        Productos bean = controlPro.mostrarImagen(valorId, labelFotoVerProductosProductos);
        if (bean != null) {
            txtNombreProductoProductos.setText(bean.getNombre());
            txtDescripcionProductos.setText(bean.getDescripcion());
        } else {

        }
    }//GEN-LAST:event_jTable1MousePressed

    private void btnEliminarProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProProductosActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);

        } else {
            int fila = jTable1.getSelectedRow();
            Object valorId = jTable1.getValueAt(fila, 0);

            controlPro.eliminar(valorId + "", jTable1, tablaProductos);
        }
    }//GEN-LAST:event_btnEliminarProProductosActionPerformed

    private void btnBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductosActionPerformed
        String dato = txtBuscarProductos.getText();
        controlPro.consultarPorClaveNombreDescripcionLike(dato, jTable1, tablaProductos);
    }//GEN-LAST:event_btnBuscarProductosActionPerformed

    private void btnRegistrarProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProProductosActionPerformed
        if (principal.controlregistrarProducto == false) {
            registrarProducto resgistrar = new registrarProducto();
            resgistrar.setVisible(true);
            principal.controlregistrarProducto = true;
        } else {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            principal.frameregistrarProducto.setAlwaysOnTop(true);
            principal.frameregistrarProducto.setAlwaysOnTop(false);

        }
    }//GEN-LAST:event_btnRegistrarProProductosActionPerformed

    private void btnActualizarTablaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaProductosActionPerformed
        controlPro.llenarTabla(jTable1, tablaProductos);
    }//GEN-LAST:event_btnActualizarTablaProductosActionPerformed

    private void btnEditarProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProProductosActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona un producto de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            if (principal.controleditarProducto == false) {
                int fila = jTable1.getSelectedRow();
                Object valorId = jTable1.getValueAt(fila, 0);
                controlPro.consultaEspecificaParaModificar(Integer.parseInt(valorId + ""));
                editarProducto ep = new editarProducto();

                ep.setVisible(true);
                principal.controleditarProducto = true;
            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.frameeditarProducto.setAlwaysOnTop(true);
                principal.frameeditarProducto.setAlwaysOnTop(false);
            }

        }
    }//GEN-LAST:event_btnEditarProProductosActionPerformed

    private void btnVenderProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderProProductosActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona un producto de la tabla");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            if (principal.controldetallesVenderProducto == false) {
                int fila = jTable1.getSelectedRow();
                Object valorId = jTable1.getValueAt(fila, 0);
                Object valorExistencias = jTable1.getValueAt(fila, 4);

                //tenemos que enviar un bean productos a detalles vender producto para mostrar los datos delproducto seleccionado
                controlPro.consultaEspecificaParaEnviarAPantllas(Integer.parseInt(valorId + ""), Integer.parseInt(valorExistencias + ""), "detallesVenderProducto");
                principal.controldetallesVenderProducto = true;
            } else {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
                principal.framedetallesVenderProducto.setAlwaysOnTop(true);
                principal.framedetallesVenderProducto.setAlwaysOnTop(false);
            }

        }

    }//GEN-LAST:event_btnVenderProProductosActionPerformed

    private void btnVenderProProductos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderProProductos3ActionPerformed
        // TODO add your handling code here:
        if (principal.controlnuevoRegistro == false) {
            nuevoRegistro newRegistro = new nuevoRegistro();
            newRegistro.setVisible(true);
            principal.controlnuevoRegistro = true;
        } else {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            principal.framenuevoRegistro.setAlwaysOnTop(true);
            principal.framenuevoRegistro.setAlwaysOnTop(false);
        }

    }//GEN-LAST:event_btnVenderProProductos3ActionPerformed

    private void txtEfectivoRecibidoProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoRecibidoProductosKeyReleased

        if (txtTotalPagarProductos.getText().isEmpty()) {

        } else {
            if (jTable2.getRowCount() == 0) {

            } else {
                if (Integer.parseInt(txtEfectivoRecibidoProductos.getText()) >= Integer.parseInt(txtTotalPagarProductos.getText().toString())) {

                    jLabel5.setText("Cambio:");
                    jLabel5.setForeground(Color.BLUE);
                    int cambio = Integer.parseInt(txtEfectivoRecibidoProductos.getText()) - Integer.parseInt(txtTotalPagarProductos.getText().toString());
                    txtCambioProductos.setForeground(Color.BLUE);
                    txtCambioProductos.setText(cambio + "");

                } else {
                    jLabel5.setText("Restan:");
                    jLabel5.setForeground(Color.red);
                    int cambio = Integer.parseInt(txtTotalPagarProductos.getText().toString()) - Integer.parseInt(txtEfectivoRecibidoProductos.getText());
                    txtCambioProductos.setForeground(Color.red);

                    txtCambioProductos.setText(cambio + "");
                }
            }

        }

    }//GEN-LAST:event_txtEfectivoRecibidoProductosKeyReleased

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        byte[] foto;
        int fila = jTable2.getSelectedRow();
        String valorId = jTable1.getValueAt(fila, 0) + "";
        controlPro.mostrarImagen(valorId, labelFotoVentaProductos);

    }//GEN-LAST:event_jTable2MousePressed

    private void btnRegistrarVentaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaProductosActionPerformed
        if (jTable2.getRowCount() == 0) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("La tabla ventas esta vacia");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "La tabla ventas esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            JFrame t = this;

            controlVen.registrarVenta(jTable2, tablaVentas, txtTotalPagarProductos, txtEfectivoRecibidoProductos, txtCambioProductos, principal.idUsuario + "", t, jTable1, tablaProductos, null, null);

        }
    }//GEN-LAST:event_btnRegistrarVentaProductosActionPerformed

    private void btnCancelarVentaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaProductosActionPerformed
        if (jTable2.getRowCount() == 0) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("La tabla ventas esta vacia");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "La tabla ventas esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {

            controlVen.btnCanelarVenta(jTable2, tablaVentas, txtTotalPagarProductos, txtEfectivoRecibidoProductos, txtCambioProductos, jTable1, tablaProductos);
        }
    }//GEN-LAST:event_btnCancelarVentaProductosActionPerformed

    private void btnQuitarProductoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoProductosActionPerformed
        if (jTable2.getRowCount() == 0) {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("La tabla ventas esta vacia");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //  JOptionPane.showMessageDialog(null, "La tabla ventas esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            if (jTable2.getSelectedRow() == -1) {
                mensajeAdvertencia men = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla ventas");
                men.setVisible(true);
                men.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla ventas", "Advertencia", JOptionPane.WARNING_MESSAGE);

            } else {
                int fila = jTable2.getSelectedRow();
                int valorID = Integer.parseInt(jTable2.getValueAt(fila, 0) + "");
                int valorCantidad = Integer.parseInt(jTable2.getValueAt(fila, 2) + "");
                int valorSubtotal = Integer.parseInt(jTable2.getValueAt(fila, 4) + "");

                controlVen.btnQuitarProductosTablaVentas(valorID, valorCantidad, valorSubtotal,
                        txtTotalPagarProductos, txtEfectivoRecibidoProductos, txtCambioProductos,
                        jTable1, tablaProductos, jLabel5);

                tablaVentas.removeRow(fila);

            }

        }
    }//GEN-LAST:event_btnQuitarProductoProductosActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked

        principal.controlproductos = false;
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void txtTotalPagarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPagarProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPagarProductosActionPerformed

    private void txtBuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosKeyReleased
        //buscamos  por clave,nombres ,descripcion
        String dato = txtBuscarProductos.getText();
        controlPro.consultarPorClaveNombreDescripcionLike(dato, jTable1, tablaProductos);
    }//GEN-LAST:event_txtBuscarProductosKeyReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        principal.controlproductos = false;
    }//GEN-LAST:event_formWindowClosing

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (principal.controlventaRapida == false) {
            ventaRapida ventaRa = new ventaRapida();
            ventaRa.setVisible(true);
            principal.controlventaRapida = true;
        } else {
            mensajeAdvertencia men = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ya esta abierto esta ventana");
            men.setVisible(true);
            men.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            principal.frameventaRapida.setAlwaysOnTop(true);
            principal.frameventaRapida.setAlwaysOnTop(false);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new productos().setVisible(true);
            }
        });
    }
    @Override
    public void run() {
       Thread current= Thread.currentThread();
       
       while(current==hilo){
           hora();
           txtReloj.setText(obtenerFecha()+"   "+hora+":"+minuto+":"+segundo);
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarTablaProductos;
    private javax.swing.JButton btnBuscarProductos;
    private javax.swing.JButton btnCancelarVentaProductos;
    private javax.swing.JButton btnEditarProProductos;
    private javax.swing.JButton btnEliminarProProductos;
    private javax.swing.JButton btnQuitarProductoProductos;
    private javax.swing.JButton btnRegistrarProProductos;
    private javax.swing.JButton btnRegistrarVentaProductos;
    private javax.swing.JButton btnVenderProProductos;
    private javax.swing.JButton btnVenderProProductos3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelFotoVentaProductos;
    private javax.swing.JLabel labelFotoVerProductosProductos;
    private javax.swing.JTextField txtBuscarProductos;
    private javax.swing.JTextField txtCambioProductos;
    private javax.swing.JTextField txtDescripcionProductos;
    private javax.swing.JTextField txtEfectivoRecibidoProductos;
    private javax.swing.JTextField txtNombreProductoProductos;
    private javax.swing.JTextField txtPersonaQueAtiendeProductos;
    private javax.swing.JTextField txtReloj;
    private javax.swing.JTextField txtTotalPagarProductos;
    // End of variables declaration//GEN-END:variables
}
