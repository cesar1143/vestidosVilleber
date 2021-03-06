/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import pantallas.*;
import ModeloProductos.DaoProductos;
import ModeloProductos.DetallesProductos;
import ModeloProductos.productos;
import beans.Productos;
import control.controlInicioSesion;
import control.controlPagos;
import control.controlProductoPendientes;
import control.controlProductos;
import control.controlProductosAgotados;
import control.controlReportes;
import control.controlVentas;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class principalUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form principal
     */
//=============================  PARA  EL CATALOGO  PRODUCTOS =========================================
    int canlis;
    int contCatalogoImg = 0;

//para validar las pantallas   que se abran solo una vez
    public static boolean controlDetallesProducto = false;
    public static boolean controlcambiarEstadoProductosApartados = false;
    public static boolean controlcancelarProductosApartados = false;
    public static boolean controldetallesDelProducto = false;
    public static boolean controldetallesVenderProducto = false;
    public static boolean controleditarProducto = false;
    public static boolean controleditarProducto2 = false;
    public static boolean controleditarUsuario = false;
    public static boolean controlnuevoRegistro2 = false;
    public static boolean controlregistrarProducto2 = false;
    public static boolean controlverPagos = false;
    
    public static JFrame frameDetallesProducto;
    public static JFrame framecambiarEstadoProductosApartados;
    public static JFrame framecancelarProductosApartados;
    public static JFrame framedetallesDelProducto;
                            
    public static JFrame framedetallesVenderProducto;
    public static JFrame frameeditarProducto ;
    public static JFrame frameeditarProducto2;
    public static JFrame frameeditarUsuario ;
    public static JFrame framenuevoRegistro2;
    public static JFrame frameregistrarProducto2;
     public static JFrame frameverPagos ;
    
    

//================================= HERE TERMINA ======================================================================================    
//============================= DECLARACION DE VARIABLES ===============================================
    public static String idUSuario, nombreUSuario;

//=========================== INSTANCIAMOS LA CLASES QUE UTILIZAREMOS ======================================
    validarCampos validar = new validarCampos();

    controlInicioSesion controlInicioSesion = new controlInicioSesion();
    controlProductos controlPro = new controlProductos();
    controlVentas controlVen = new controlVentas();
    controlProductoPendientes controlPendientes = new controlProductoPendientes();
    controlProductosAgotados controlProAgotados = new controlProductosAgotados();
    controlInicioSesion controlUsuario = new controlInicioSesion();
    //los daos
    DaoProductos daoProductosCatalogo = new DaoProductos();
//============================ INSTANCIA DE LA TABLAS =========================================================
    DefaultTableModel tablaProductos, tablaVentas, tablaPendientes, tablaMedidas, tablaProAgotados, tablaUsuarios, tablaReportes;

    public principalUsuarios() {

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

        tablaPendientes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaPendientes.setColumnIdentifiers(new Object[]{"Id", "Clave producto", "Nombre cliente", "Estado", "Cantidad", "Fecha prueba", "Fecha Evento"});

        tablaMedidas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaMedidas.setColumnIdentifiers(new Object[]{"Id", "Talle", "Sise", "Hombros", "Busto", "Largo falda", "Ancho puño", "Cintura", "Cadera"});

        tablaProAgotados = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaProAgotados.setColumnIdentifiers(new Object[]{"Id", "Clave", "Nombre", "Descripcion", "Existencias", "Precio"});

        tablaUsuarios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaUsuarios.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Amaterno", "Apaterno", "Usuario", "Contraseña"});

        tablaReportes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tablaReportes.setColumnIdentifiers(new Object[]{"Id", "Clave", "Nombre", "Cantidad", "Precio", "Subtotal"});
//================================== INITIcOMPONENTS(); ===========================================================       
        initComponents();

//================================ MANDAMOS LAS TABLAS PARA PODER ACTUALIZARLAS AL DISTINTAS PANTALLAS ==========   
        editarProducto3.tabla = jTable1;
        editarProducto3.defaultTabla = tablaProductos;
        //mandamos la tabla pro agotados a pantalla editar2

        registrarProducto2.tabla = jTable1;
        registrarProducto2.defaultTabla = tablaProductos;

        detallesDelProducto.tablaVentas = jTable2;
        detallesDelProducto.defaultTablaVentas = tablaVentas;
        detallesDelProducto.txtTotalAPagar = txtTotalPagarProductos;
        detallesDelProducto.tablaProductos = jTable1;
        detallesDelProducto.defaultTablaProductos = tablaProductos;

        detallesVenderProducto.tablaProductos = jTable1;
        detallesVenderProducto.defaultTablaProductos = tablaProductos;
        detallesVenderProducto.tablaVentas = jTable2;
        detallesVenderProducto.defaultTablaVentas = tablaVentas;
        detallesVenderProducto.txtTotalAPagar = txtTotalPagarProductos;

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
        nuevoRegistro2.tablaVentas = jTable2;
        nuevoRegistro2.defaultTablaVentas = tablaVentas;
        nuevoRegistro2.txtTotalApagar = txtTotalPagarProductos;
        //mandar tabla pendietes aa cambiarEstadoProductosApartados
        cambiarEstadoProductosApartados.tablaPendientes = jTable4;
        //mandar tabla pendietes aa cambiarEstadoProductosApartados
        cambiarEstadoProductosApartados.defaultTablaPendientes = tablaPendientes;

        //mandar tabla pendientes a cancelar productos apartados
        cancelarProductosApartados.tablaPendientes = jTable4;

        cancelarProductosApartados.defaultTablaPendientes = tablaPendientes;

        // jPanel13.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        //=============================== VALIDACIONES DE CAMPOS ======================================================       
        //HACEMOS QUE LA PANTALLA SE AMPLIE A LO MAXIMO
        setExtendedState(MAXIMIZED_BOTH);
        //VALIDAMOS LOS CAMPOS DE LA VENTANA PRODUCTOS
        validar.soloNumerosYLetras(txtBuscarProductos);
        validar.soloNumeros(txtEfectivoRecibidoProductos);
        //VALIDAMOS LOS CAMPOS DE LA VENTANA NUEVOS REGISTROS
        //DATOS DEL CLIENTE
        //validamos el campo de pro pendientes
        validar.soloLetras(txtBuscarProductosPendientes);
        //validamos los campos de la pantalla usuarios

//================ LLENADO  DE CAMPOS TABLAS ESPINER TODO LO QUE DEBE CARGAR LA VENTANA DESDE UN PRINCIPIO =======        
        //COLOCAMOS EL NOMBRE DEL USUARIO Y LA FECHA ACTUAL EN LA VISTA 
        txtPersonaQueAtiendeProductos.setText(nombreUSuario);

        // txtFechaActualProductos.setText(validar.obtenerFechaActual());
        //LLENAMOS LA TABLA DE PRODUCTOS
        controlPro.llenarTabla(jTable1, tablaProductos);
        controlPendientes.llenarTablaPendientes(jTable4, tablaPendientes);

//================================  PARA EL CATALGO PRODUCTOS =============================
//Todo esto usamos para el catalogo
        btnAtras.setEnabled(false);
        //this.setExtendedState(MAXIMIZED_BOTH);
        labelImg11.setIcon(null);
        //para el catalogo por default vamos a dejar 8 fotos de manera estatica se podria decir
        //cuando se presion el boton siguiente el contador ira a avanzado y cambiara las 8 imagenes y pondra otras 8 nueva
        //validaremos si aun la lista de imagenes aun tiene y si ya no tiene los cuadros que quedan vacios les pondremos una foto estatica

        //este contador nos va servir para ir moviendo el boton next y atras conforme avanse
        //declaramos nuesta calse dao
        //creamos una lista para recibir los valoles del dao aqui recibimos byte[]
        //aqui mero mero maromero
        new controlProductos().llenarListaImg(labelImg11, labelImg12, labelImg13, labelImg14, labelImg15, labelImg16, labelImg17, labelImg29,
                btnAtras, btnSiguiente, labelIdProducto16, labelIdProducto17, labelIdProducto18, labelIdProducto19, labelIdProducto20,
                labelIdProducto21, labelIdProducto22, labelIdProducto23, contCatalogoImg, canlis);
//OCULTAMOS LOS LABEL DONDE PONEMOS LOS ID DE PRODUCTOS
        labelIdProducto16.setVisible(false);
        labelIdProducto17.setVisible(false);
        labelIdProducto18.setVisible(false);
        labelIdProducto19.setVisible(false);
        labelIdProducto20.setVisible(false);
        labelIdProducto21.setVisible(false);
        labelIdProducto22.setVisible(false);
        labelIdProducto23.setVisible(false);

//============================================== HERE TERMINA ==============================================================================================
    }

//====================================  PARA EL CATALOGO DE PRODUCTOS  ==================================
    public void consultarDetallesProducto(int idProducto) {
        DetallesProducto beanDetallesPro = new DetallesProducto();
        productos bean = daoProductosCatalogo.consultarImage(idProducto);
        Image imagen;
//        List<Image> listaImg = new ArrayList<Image>();
        try {
            if (controlDetallesProducto == false) {
                  
                imagen = daoProductosCatalogo.getImage(bean, false);
//            listaImg.add(imagen);
//            Icon i=new ImageIcon(listaImg.get(1).getScaledInstance(2, 3, listaImg.get(1).SCALE_DEFAULT));
                //este icon se supone que ya es la imagen literalmente entonces aremos un arrayList<Image>
                Icon icon = new ImageIcon(imagen.getScaledInstance(360, 670, Image.SCALE_DEFAULT));
                DetallesProducto.labelFoto.setIcon(icon);
                DetallesProducto.txtCodigo.setText(bean.getClave());
                DetallesProducto.txtPrecio.setText(String.valueOf(bean.getPrecio()));

                beanDetallesPro.setVisible(true);
                controlDetallesProducto = true;
                       
            } else {
                JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana proooo", "MENSAJE DE ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                   frameDetallesProducto.setAlwaysOnTop(true);
                    frameDetallesProducto.setAlwaysOnTop(false);
            }
        } catch (Exception ex) {
            System.out.println("error al cargar al imagen " + ex);
        }
    }
//==================================== HERE AQUIIIIIIIIIIIIIIIIIIIIII ===================================    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBuscarProductos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarProductos = new javax.swing.JTextField();
        btnRegistrarProProductos = new javax.swing.JButton();
        btnActualizarTablaProductos = new javax.swing.JButton();
        btnVenderProProductos = new javax.swing.JButton();
        labelFotoVerProductosProductos = new javax.swing.JLabel();
        btnVenderProProductos3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtNombreProductoProductos = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtDescripcionProductos = new javax.swing.JTextField();
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
        txtFechaActualProductos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPersonaQueAtiendeProductos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtBuscarProductosPendientes = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnBuscarProductos1 = new javax.swing.JButton();
        btnActualizarTablaProductos1 = new javax.swing.JButton();
        labelFotoPendientes = new javax.swing.JLabel();
        btnAbonarPagoPendientes = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaDetalleProductoPendiente = new javax.swing.JTextArea();
        btnEntregarProductoPendientes = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        labelBuscar = new javax.swing.JLabel();
        comboxTipo = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        labelImg11 = new javax.swing.JLabel();
        labelImg12 = new javax.swing.JLabel();
        labelImg13 = new javax.swing.JLabel();
        labelImg14 = new javax.swing.JLabel();
        labelImg15 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        labelImg16 = new javax.swing.JLabel();
        labelImg17 = new javax.swing.JLabel();
        labelImg29 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        labelIdProducto23 = new javax.swing.JLabel();
        labelIdProducto22 = new javax.swing.JLabel();
        labelIdProducto21 = new javax.swing.JLabel();
        labelIdProducto20 = new javax.swing.JLabel();
        labelIdProducto19 = new javax.swing.JLabel();
        labelIdProducto18 = new javax.swing.JLabel();
        labelIdProducto17 = new javax.swing.JLabel();
        labelIdProducto16 = new javax.swing.JLabel();
        btnActualizarCatalogo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 51, 255));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 18))); // NOI18N

        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(tablaProductos);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnBuscarProductos.setText("Buscar Producto");
        btnBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Ingresa Nombre, Codigo o Descripcion del producto que desea buscar:");

        txtBuscarProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnRegistrarProProductos.setText("Registrar Producto");
        btnRegistrarProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProProductosActionPerformed(evt);
            }
        });

        btnActualizarTablaProductos.setText("Actualizar Tabla");
        btnActualizarTablaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaProductosActionPerformed(evt);
            }
        });

        btnVenderProProductos.setText("Vender Producto");
        btnVenderProProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderProProductosActionPerformed(evt);
            }
        });

        labelFotoVerProductosProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnVenderProProductos3.setText("Nuevo Registro");
        btnVenderProProductos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderProProductos3ActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre y descripcion del producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setText("Nombre:");

        txtNombreProductoProductos.setEditable(false);
        txtNombreProductoProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setText("Descripción:");

        txtDescripcionProductos.setEditable(false);
        txtDescripcionProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionProductos)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreProductoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(btnRegistrarProProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelFotoVerProductosProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btnBuscarProductos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizarTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
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
                        .addGap(159, 159, 159)
                        .addComponent(labelFotoVerProductosProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(401, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta de productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 18))); // NOI18N

        txtTotalPagarProductos.setEditable(false);
        txtTotalPagarProductos.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N

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
        txtCambioProductos.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        txtCambioProductos.setForeground(java.awt.Color.blue);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Cambio:");

        jTable2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable2.setModel(tablaVentas);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        btnRegistrarVentaProductos.setText("Registrar Venta");
        btnRegistrarVentaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaProductosActionPerformed(evt);
            }
        });

        btnCancelarVentaProductos.setText("Cancelar Venta");
        btnCancelarVentaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaProductosActionPerformed(evt);
            }
        });

        btnQuitarProductoProductos.setText("Quitar Producto de la tabla");
        btnQuitarProductoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoProductosActionPerformed(evt);
            }
        });

        labelFotoVentaProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtFechaActualProductos.setEditable(false);
        txtFechaActualProductos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Fecha Actual:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Persona que atiende:");

        txtPersonaQueAtiendeProductos.setEditable(false);
        txtPersonaQueAtiendeProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnRegistrarVentaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnCancelarVentaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
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
                        .addComponent(txtFechaActualProductos)))
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
                            .addComponent(txtFechaActualProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(413, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cerrar");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sesión");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/btnApagado22.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos", jPanel1);

        txtBuscarProductosPendientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBuscarProductosPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosPendientesKeyReleased(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("Ingresa el nombre del cliente que deseas buscar:");

        btnBuscarProductos1.setText("Buscar Cliente");
        btnBuscarProductos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductos1ActionPerformed(evt);
            }
        });

        btnActualizarTablaProductos1.setText("Actualizar Tabla");
        btnActualizarTablaProductos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaProductos1ActionPerformed(evt);
            }
        });

        labelFotoPendientes.setText("jLabel1");
        labelFotoPendientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAbonarPagoPendientes.setText("Abonar Pago");
        btnAbonarPagoPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarPagoPendientesActionPerformed(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos  pendientes", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 18))); // NOI18N

        jTable4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable4.setModel(tablaPendientes);
        jTable4.getTableHeader().setResizingAllowed(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable4MousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medidas y detalles del producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 18))); // NOI18N

        jTable5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable5.setModel(tablaMedidas);
        jTable5.getTableHeader().setResizingAllowed(false);
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable5);

        txtAreaDetalleProductoPendiente.setEditable(false);
        txtAreaDetalleProductoPendiente.setColumns(20);
        txtAreaDetalleProductoPendiente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAreaDetalleProductoPendiente.setRows(5);
        jScrollPane7.setViewportView(txtAreaDetalleProductoPendiente);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
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

        btnEntregarProductoPendientes.setText("Entregar Producto");
        btnEntregarProductoPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregarProductoPendientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAbonarPagoPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEntregarProductoPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addComponent(labelFotoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtBuscarProductosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btnBuscarProductos1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizarTablaProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel32)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProductosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarTablaProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(btnAbonarPagoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEntregarProductoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelFotoPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(408, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pendientes", jPanel3);

        labelBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelBuscar.setForeground(new java.awt.Color(255, 0, 204));
        labelBuscar.setText("Buscar Por:");

        comboxTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comboxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Novia", "IV años", "Presentacion", "Trajes" }));

        btnBuscar.setBackground(new java.awt.Color(255, 51, 255));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        labelImg11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg11MousePressed(evt);
            }
        });

        labelImg12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg12MousePressed(evt);
            }
        });

        labelImg13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg13MousePressed(evt);
            }
        });

        labelImg14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg14MousePressed(evt);
            }
        });

        labelImg15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg15MousePressed(evt);
            }
        });

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/atras.png"))); // NOI18N
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        labelImg16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg16MousePressed(evt);
            }
        });

        labelImg17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg17MousePressed(evt);
            }
        });

        labelImg29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelImg29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImg29MousePressed(evt);
            }
        });

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nextt.png"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        labelIdProducto23.setText("jLabel23");

        labelIdProducto22.setText("jLabel22");

        labelIdProducto21.setText("jLabel21");

        labelIdProducto20.setText("jLabel20");

        labelIdProducto19.setText("jLabel19");

        labelIdProducto18.setText("jLabel18");

        labelIdProducto17.setText("jLabel17");

        labelIdProducto16.setText("jLabel16");

        btnActualizarCatalogo.setBackground(new java.awt.Color(255, 51, 255));
        btnActualizarCatalogo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActualizarCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarCatalogo.setText("Actulizar  Catalogo");
        btnActualizarCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(785, Short.MAX_VALUE)
                .addComponent(btnActualizarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(401, 401, 401))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelIdProducto16)
                                .addComponent(labelIdProducto17)
                                .addComponent(labelIdProducto18)
                                .addComponent(labelIdProducto19)
                                .addComponent(labelIdProducto20)
                                .addComponent(labelIdProducto21)
                                .addComponent(labelIdProducto22)
                                .addComponent(labelIdProducto23))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(61, 61, 61)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(60, 60, 60)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelImg14, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(377, 377, 377)
                            .addComponent(labelBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(comboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBuscar)
                            .addGap(552, 552, 552)))
                    .addContainerGap()))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizarCatalogo)
                .addContainerGap(623, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelBuscar)
                        .addComponent(comboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(labelImg13, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelImg17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(labelImg12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelImg16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelImg11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(labelIdProducto16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto18)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelIdProducto23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAtras)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelImg15, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelImg14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSiguiente))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelImg29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 418, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Catalogo", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProProductosActionPerformed
        if (controlregistrarProducto2==false) {
            registrarProducto2 resgistrar2 = new registrarProducto2();
        resgistrar2.setVisible(true); 
        controlregistrarProducto2=true;
        }else{
           JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            frameregistrarProducto2.setAlwaysOnTop(true);  
             frameregistrarProducto2.setAlwaysOnTop(false);
        }
       
    }//GEN-LAST:event_btnRegistrarProProductosActionPerformed

    private void btnBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductosActionPerformed
        controlPro.buscarProductoPorNombreORClaveORDescripcio(txtBuscarProductos, jTable1, tablaProductos);
    }//GEN-LAST:event_btnBuscarProductosActionPerformed

    private void btnActualizarTablaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaProductosActionPerformed
        controlPro.llenarTabla(jTable1, tablaProductos);
    }//GEN-LAST:event_btnActualizarTablaProductosActionPerformed

    private void btnVenderProProductos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderProProductos3ActionPerformed
        // TODO add your handling code here:
        if (controlnuevoRegistro2==false) {
            nuevoRegistro2 newRegistro2 = new nuevoRegistro2();
        newRegistro2.setVisible(true);
        controlnuevoRegistro2=true;
        }else{
           JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            framenuevoRegistro2.setAlwaysOnTop(true); 
             framenuevoRegistro2.setAlwaysOnTop(false); 
        }
        

    }//GEN-LAST:event_btnVenderProProductos3ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        //cuando seleccionemostraremos la foto del producto si es que tiene
       /* byte[] foto;
        int fila = jTable1.getSelectedRow();
        Object valorId = jTable1.getValueAt(fila, 0);
        Productos bean = controlPro.mostrarImagen(Integer.parseInt(valorId + ""), labelFotoVerProductosProductos);
        if (bean != null) {
            txtNombreProductoProductos.setText(bean.getNombre());
            txtDescripcionProductos.setText(bean.getDescripcion());
        } else {

        }*/

    }//GEN-LAST:event_jTable1MousePressed

    private void btnVenderProProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderProProductosActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            if (controldetallesVenderProducto == false) {
                int fila = jTable1.getSelectedRow();
                Object valorId = jTable1.getValueAt(fila, 0);
                Object valorExistencias = jTable1.getValueAt(fila, 4);

                //tenemos que enviar un bean productos a detalles vender producto para mostrar los datos delproducto seleccionado
                controlPro.consultaEspecificaParaEnviarAPantllas(Integer.parseInt(valorId + ""), Integer.parseInt(valorExistencias + ""), "detallesVenderProducto");
                controldetallesVenderProducto = true;
            } else {
                JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            framedetallesVenderProducto.setAlwaysOnTop(true);
            framedetallesVenderProducto.setAlwaysOnTop(false);
            }

        }


    }//GEN-LAST:event_btnVenderProProductosActionPerformed

    private void btnRegistrarVentaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaProductosActionPerformed
        if (jTable2.getRowCount() == 0) {

            JOptionPane.showMessageDialog(null, "La tabla ventas esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {

            controlVen.registrarVenta(jTable2, tablaVentas, txtTotalPagarProductos, txtEfectivoRecibidoProductos, txtCambioProductos, idUSuario, this, jTable1, tablaProductos,jTable4, tablaPendientes);
        }

    }//GEN-LAST:event_btnRegistrarVentaProductosActionPerformed

    private void txtEfectivoRecibidoProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoRecibidoProductosKeyReleased

        if (txtTotalPagarProductos.getText().isEmpty()) {

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


    }//GEN-LAST:event_txtEfectivoRecibidoProductosKeyReleased

    private void btnCancelarVentaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaProductosActionPerformed
        if (jTable2.getRowCount() == 0) {

            JOptionPane.showMessageDialog(null, "La tabla ventas esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {

            controlVen.btnCanelarVenta(jTable2, tablaVentas, txtTotalPagarProductos, txtEfectivoRecibidoProductos, txtCambioProductos, jTable1, tablaProductos);
        }

    }//GEN-LAST:event_btnCancelarVentaProductosActionPerformed

    private void btnQuitarProductoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoProductosActionPerformed
        if (jTable2.getRowCount() == 0) {

            JOptionPane.showMessageDialog(null, "La tabla ventas esta vacia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            if (jTable2.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla ventas", "Advertencia", JOptionPane.WARNING_MESSAGE);

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

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
      /*  byte[] foto;
        int fila = jTable2.getSelectedRow();
        Object valorId = jTable1.getValueAt(fila, 0);
        controlPro.mostrarImagen(Integer.parseInt(valorId + ""), labelFotoVentaProductos);
*/

    }//GEN-LAST:event_jTable2MousePressed

    private void jTable4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MousePressed
      /*  int fila = jTable4.getSelectedRow();

        int valorId = Integer.parseInt(jTable4.getValueAt(fila, 0) + "");
        int valorClave = Integer.parseInt(jTable4.getValueAt(fila, 1) + "");
        byte[] foto;

        controlPro.mostrarImagen2(Integer.parseInt(valorClave + ""), labelFotoPendientes);
        controlPendientes.mostrarMedidasProductos(valorId, jTable5, tablaMedidas, txtAreaDetalleProductoPendiente);
        */
    }//GEN-LAST:event_jTable4MousePressed

    private void btnBuscarProductos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductos1ActionPerformed
        controlPendientes.buscarCliente(txtBuscarProductosPendientes, jTable4, tablaPendientes);
    }//GEN-LAST:event_btnBuscarProductos1ActionPerformed

    private void btnActualizarTablaProductos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaProductos1ActionPerformed

        controlPendientes.llenarTablaPendientes(jTable4, tablaPendientes);
    }//GEN-LAST:event_btnActualizarTablaProductos1ActionPerformed

    private void txtBuscarProductosPendientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosPendientesKeyReleased

        if (txtBuscarProductosPendientes.getText().isEmpty()) {
            controlPendientes.llenarTablaPendientes(jTable4, tablaPendientes);
        } else {
            controlPendientes.buscarCliente(txtBuscarProductosPendientes, jTable4, tablaPendientes);
        }

    }//GEN-LAST:event_txtBuscarProductosPendientesKeyReleased

    private void btnAbonarPagoPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarPagoPendientesActionPerformed
        //mostraremos una pantalla con los pagos,deduda y lo que resta
        if (jTable4.getSelectedRow() == -1) {

            JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controlverPagos==false) {
                int fila = jTable4.getSelectedRow();
            String estado = jTable4.getValueAt(fila, 3) + "";
            if (estado.equals("Apartado")) {
                String nombre = jTable4.getValueAt(fila, 2) + "";
                new controlPagos().mostrarInformacionDeuda(nombre);
                verPagos2 verP2 = new verPagos2();
                verP2.setVisible(true);
                controlverPagos=true;
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no tiene deduda", "Advertencia", JOptionPane.WARNING_MESSAGE);

            }
            }else{
               JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            frameverPagos.setAlwaysOnTop(true); 
             frameverPagos.setAlwaysOnTop(false); 
            }
            

        }

    }//GEN-LAST:event_btnAbonarPagoPendientesActionPerformed

    private void btnEntregarProductoPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregarProductoPendientesActionPerformed
        if (jTable4.getSelectedRow() == -1) {

            JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controlcambiarEstadoProductosApartados == false) {
                int fila = jTable4.getSelectedRow();
                String estado = jTable4.getValueAt(fila, 3) + "";

                if (estado.equalsIgnoreCase("Pagado no entregado")) {
                                   
                    String nombre = jTable4.getValueAt(fila, 2) + "";
                    controlPendientes.btnEntregarProdcutos(nombre);
                    controlcambiarEstadoProductosApartados = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El cliente no tiene productos pendientes", "Advertencia", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            framecambiarEstadoProductosApartados.setAlwaysOnTop(true);
             framecambiarEstadoProductosApartados.setAlwaysOnTop(false);
            }

        }


    }//GEN-LAST:event_btnEntregarProductoPendientesActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String tipo = comboxTipo.getSelectedItem().toString();
        String estado = "";

        //            System.out.println("entro al check vestiso seleccionado");
        estado = "Vendido";
        //Mandamos a traer un metodo donde el status sea vendido mas el tipo de opcion bod,IV años etc
        //creamos una lista para recibir los valoles del dao aqui recibimos byte[]
        contCatalogoImg = 0;
        List<productos> listaProCatalgo = daoProductosCatalogo.consultarImageParaCatalogoTipoyEstado(tipo, estado);
        if (listaProCatalgo.size() != 0) {
            List<DetallesProductos> listaImg = new ArrayList<>();
            Image imagen = null;
            for (int i = 0; i < listaProCatalgo.size(); i++) {
                DetallesProductos beanDetallesPro = new DetallesProductos();
                try {
                    imagen = daoProductosCatalogo.getImage(listaProCatalgo.get(i), false);
                    beanDetallesPro.setImagen(imagen);
                    beanDetallesPro.setIdProducto(listaProCatalgo.get(i).getIdProductos());
                    listaImg.add(beanDetallesPro);
                } catch (IOException ex) {
                    System.out.println("error en la imagenes");
                }
            }
            if (listaImg.size() < 9) {
                btnSiguiente.setEnabled(false);
            }
            //here mero llenarCatalago(listaImg);
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos", "MENSAJE DE ALERTA", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void labelImg11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg11MousePressed
        // TODO add your handling code here:
        if (labelIdProducto16.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto16.getText().toString()));
        }
    }//GEN-LAST:event_labelImg11MousePressed

    private void labelImg12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg12MousePressed
        // TODO add your handling code here:
        if (labelIdProducto17.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto17.getText().toString()));
        }
    }//GEN-LAST:event_labelImg12MousePressed

    private void labelImg13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg13MousePressed
        // TODO add your handling code here:
        if (labelIdProducto18.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto18.getText().toString()));
        }
    }//GEN-LAST:event_labelImg13MousePressed

    private void labelImg14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg14MousePressed
        // TODO add your handling code here:
        if (labelIdProducto19.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto19.getText().toString()));
        }
    }//GEN-LAST:event_labelImg14MousePressed

    private void labelImg15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg15MousePressed
        // TODO add your handling code here:
        if (labelIdProducto20.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto20.getText().toString()));
        }
    }//GEN-LAST:event_labelImg15MousePressed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (contCatalogoImg <= 15) {
            //            System.out.println("entro en es igual a 15");
            contCatalogoImg = 0;
        } else {
            contCatalogoImg = contCatalogoImg - 15;
            //            System.out.println("entro en no es igual a 15");
        }
        //este contador nos va servir para ir moviendo el boton next y atras conforme avanse
        //declaramos nuesta calse dao

        //creamos una lista para recibir los valoles del dao aqui recibimos byte[]
        List<productos> listaProCatalgo = daoProductosCatalogo.consultarImageParaCatalogo();
        if (listaProCatalgo.size() != 0) {
            List<DetallesProductos> listaImg = new ArrayList<>();
            Image imagen = null;
            for (int i = 0; i < listaProCatalgo.size(); i++) {
                DetallesProductos beanDetallesPro = new DetallesProductos();
                try {
                    imagen = daoProductosCatalogo.getImage(listaProCatalgo.get(i), false);
                    beanDetallesPro.setImagen(imagen);
                    beanDetallesPro.setIdProducto(listaProCatalgo.get(i).getIdProductos());
                    listaImg.add(beanDetallesPro);
                } catch (IOException ex) {
                    System.out.println("error en la imagenes");
                }
            }
            //            System.out.println("en boton Siguiente" + listaImg.size());
            if (listaImg.size() < 9) {
                btnSiguiente.setEnabled(false);
            }

            //here mero llenarCatalago(listaImg);
            //            System.out.println("cont btn " + contCatalogoImg);
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos", "MENSAJE DE ALERTA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void labelImg16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg16MousePressed
        // TODO add your handling code here:
        if (labelIdProducto21.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto21.getText().toString()));
        }
    }//GEN-LAST:event_labelImg16MousePressed

    private void labelImg17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg17MousePressed
        // TODO add your handling code here:
        if (labelIdProducto22.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto22.getText().toString()));
        }
    }//GEN-LAST:event_labelImg17MousePressed

    private void labelImg29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImg29MousePressed
        // TODO add your handling code here:
        if (labelIdProducto23.getText().toString().equals("")) {

        } else {
            consultarDetallesProducto(Integer.parseInt(labelIdProducto23.getText().toString()));
        }
    }//GEN-LAST:event_labelImg29MousePressed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        contCatalogoImg++;

        //este contador nos va servir para ir moviendo el boton next y atras conforme avanse
        //declaramos nuesta calse dao
        //creamos una lista para recibir los valoles del dao aqui recibimos byte[]
        List<productos> listaProCatalgo = daoProductosCatalogo.consultarImageParaCatalogo();

        List<DetallesProductos> listaImg = new ArrayList<>();
        Image imagen = null;
        for (int i = 0; i < listaProCatalgo.size(); i++) {
            DetallesProductos beanDetallesPro = new DetallesProductos();
            try {
                imagen = daoProductosCatalogo.getImage(listaProCatalgo.get(i), false);
                beanDetallesPro.setImagen(imagen);
                beanDetallesPro.setIdProducto(listaProCatalgo.get(i).getIdProductos());
                listaImg.add(beanDetallesPro);
            } catch (IOException ex) {
                System.out.println("error en la imagenes");
            }
        }
        //        System.out.println("en boton Siguiente" + listaImg.size());
        if (listaImg.size() < 9) {
            btnSiguiente.setEnabled(false);
        }

        //here mero llenarCatalago(listaImg);
        //        System.out.println("cont btn " + contCatalogoImg);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnActualizarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCatalogoActionPerformed
        controlPro.llenarListaImg(labelImg11, labelImg12, labelImg13, labelImg14, labelImg15, labelImg16, labelImg17, labelImg29, btnAtras, btnSiguiente, labelIdProducto16, labelIdProducto17, labelIdProducto18, labelIdProducto19, labelIdProducto20, labelIdProducto21, labelIdProducto22, labelIdProducto23, contCatalogoImg, canlis);
    }//GEN-LAST:event_btnActualizarCatalogoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controlUsuario.cerrarSesionPrincipalUsuario(this);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(principalUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principalUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principalUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principalUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principalUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonarPagoPendientes;
    private javax.swing.JButton btnActualizarCatalogo;
    private javax.swing.JButton btnActualizarTablaProductos;
    private javax.swing.JButton btnActualizarTablaProductos1;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarProductos;
    private javax.swing.JButton btnBuscarProductos1;
    private javax.swing.JButton btnCancelarVentaProductos;
    private javax.swing.JButton btnEntregarProductoPendientes;
    private javax.swing.JButton btnQuitarProductoProductos;
    private javax.swing.JButton btnRegistrarProProductos;
    private javax.swing.JButton btnRegistrarVentaProductos;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnVenderProProductos;
    private javax.swing.JButton btnVenderProProductos3;
    private javax.swing.JComboBox comboxTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel labelBuscar;
    private javax.swing.JLabel labelFotoPendientes;
    private javax.swing.JLabel labelFotoVentaProductos;
    private javax.swing.JLabel labelFotoVerProductosProductos;
    private javax.swing.JLabel labelIdProducto16;
    private javax.swing.JLabel labelIdProducto17;
    private javax.swing.JLabel labelIdProducto18;
    private javax.swing.JLabel labelIdProducto19;
    private javax.swing.JLabel labelIdProducto20;
    private javax.swing.JLabel labelIdProducto21;
    private javax.swing.JLabel labelIdProducto22;
    private javax.swing.JLabel labelIdProducto23;
    private javax.swing.JLabel labelImg11;
    private javax.swing.JLabel labelImg12;
    private javax.swing.JLabel labelImg13;
    private javax.swing.JLabel labelImg14;
    private javax.swing.JLabel labelImg15;
    private javax.swing.JLabel labelImg16;
    private javax.swing.JLabel labelImg17;
    private javax.swing.JLabel labelImg29;
    private javax.swing.JTextArea txtAreaDetalleProductoPendiente;
    private javax.swing.JTextField txtBuscarProductos;
    private javax.swing.JTextField txtBuscarProductosPendientes;
    private javax.swing.JTextField txtCambioProductos;
    private javax.swing.JTextField txtDescripcionProductos;
    private javax.swing.JTextField txtEfectivoRecibidoProductos;
    private javax.swing.JTextField txtFechaActualProductos;
    private javax.swing.JTextField txtNombreProductoProductos;
    private javax.swing.JTextField txtPersonaQueAtiendeProductos;
    private javax.swing.JTextField txtTotalPagarProductos;
    // End of variables declaration//GEN-END:variables
}
