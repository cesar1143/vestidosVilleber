/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NuevasPantallas;

import control.controlInicioSesion;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mensajes.mensajeQuestion;

/**
 *
 * @author famsa
 */
public class principal extends javax.swing.JFrame {
//para el frame

    int x = 0, y = 0;
    public static int idUsuario;
    public static String nombreUsuario;
    public static String usuario;

//para validar las pantallas   que se abran solo una vez
    public static boolean controlDetallesProducto = false;
    public static boolean controlcambiarEstadoProductosApartados = false;
    public static boolean controlcancelarProductosApartados = false;
    public static boolean controldetallesDelProducto = false;
    public static boolean controldetallesVenderProducto = false;
    public static boolean controleditarProducto = false;
    public static boolean controleditarProducto2 = false;
    public static boolean controleditarUsuario = false;
    public static boolean controlnuevoRegistro = false;
    public static boolean controlregistrarProducto = false;
    public static boolean controlverPagos = false;
    //para validar las pantallas principales
    public static boolean controlcatalogo = false;
    public static boolean controlproductos = false;
    public static boolean controlproductosAgotados = false;
    public static boolean controlproductosPendientes = false;
    public static boolean controlreportes = false;
    public static boolean controlusuarios = false;
    public static boolean controlventaRapida = false;
    public static  boolean controlcambiarEstadoProductosEntregadosNoPagados2019=false;
    public static boolean controleditarProductoPendientes2019=false;

    public static JFrame frameDetallesProducto;
    public static JFrame framecambiarEstadoProductosApartados;
    public static JFrame framecancelarProductosApartados;
    public static JFrame framedetallesDelProducto;
    public static JFrame framedetallesVenderProducto;
    public static JFrame frameeditarProducto;
    public static JFrame frameeditarProducto2;
    public static JFrame frameeditarUsuario;
    public static JFrame framenuevoRegistro;
    public static JFrame frameregistrarProducto;
    public static JFrame frameverPagos;
     public static JFrame framecambiarEstadoProductosEntregadosNoPagados2019;
     public static JFrame frameeditarProductoPendientes2019;

    //para validar las pantallas principales
    public static JFrame framecatalogo;
    public static JFrame frameproductos;
    public static JFrame frameproductosAgotados;
    public static JFrame frameproductosPendientes;
    public static JFrame framereportes;
    public static JFrame frameusuarios;
    public static JFrame frameventaRapida;

    /**
     * Creates new form principalMenu2
     */
    public principal() {
        initComponents();
        //frame pricipal
        mensajeQuestion.framePrincipal = this;
        this.setLocationRelativeTo(null);
        labelNombreUsuario.setText(nombreUsuario);
        //validar quien esta iniciando sesion 
        if (usuario.equalsIgnoreCase("Mayra")) {

        } else {
            panelAgotados3.setVisible(false);
            panelReportes.setVisible(false);
            panelUsuarios.setVisible(false);
        }

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
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelNombreUsuario = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelProductos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelPendientes = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelCatalogo = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelAgotados3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        panelReportes = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        panelUsuarios = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
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

        jPanel2.setBackground(new java.awt.Color(255, 0, 204));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/minimizar.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menú");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(194, 194, 194))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 204));
        jLabel4.setText("Bienvenida(o):");

        labelNombreUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelNombreUsuario.setForeground(new java.awt.Color(255, 0, 204));
        labelNombreUsuario.setText("jLabel5");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/btnApagado22.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 204));
        jLabel7.setText("Cerrar Sesión");

        panelProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelProductosMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Productos");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/vestir.png"))); // NOI18N

        javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(panelProductosLayout);
        panelProductosLayout.setHorizontalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelProductosLayout.setVerticalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPendientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelPendientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelPendientesMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pendientes");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pendientes.png"))); // NOI18N

        javax.swing.GroupLayout panelPendientesLayout = new javax.swing.GroupLayout(panelPendientes);
        panelPendientes.setLayout(panelPendientesLayout);
        panelPendientesLayout.setHorizontalGroup(
            panelPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPendientesLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(50, 50, 50))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPendientesLayout.setVerticalGroup(
            panelPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPendientesLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        panelCatalogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCatalogoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelCatalogoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelCatalogoMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Catalogo");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/catalogo.png"))); // NOI18N

        javax.swing.GroupLayout panelCatalogoLayout = new javax.swing.GroupLayout(panelCatalogo);
        panelCatalogo.setLayout(panelCatalogoLayout);
        panelCatalogoLayout.setHorizontalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel13)
                .addContainerGap(55, Short.MAX_VALUE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCatalogoLayout.setVerticalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(13, 13, 13)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        panelAgotados3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAgotados3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAgotados3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAgotados3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAgotados3MouseExited(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Agotados");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agotados.png"))); // NOI18N

        javax.swing.GroupLayout panelAgotados3Layout = new javax.swing.GroupLayout(panelAgotados3);
        panelAgotados3.setLayout(panelAgotados3Layout);
        panelAgotados3Layout.setHorizontalGroup(
            panelAgotados3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelAgotados3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel20)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panelAgotados3Layout.setVerticalGroup(
            panelAgotados3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgotados3Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        panelReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelReportesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelReportesMouseExited(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Reportes");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reportes.png"))); // NOI18N

        javax.swing.GroupLayout panelReportesLayout = new javax.swing.GroupLayout(panelReportes);
        panelReportes.setLayout(panelReportesLayout);
        panelReportesLayout.setHorizontalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelReportesLayout.setVerticalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        panelUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelUsuariosMouseExited(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Usuarios");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuarios.png"))); // NOI18N

        javax.swing.GroupLayout panelUsuariosLayout = new javax.swing.GroupLayout(panelUsuarios);
        panelUsuarios.setLayout(panelUsuariosLayout);
        panelUsuariosLayout.setHorizontalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsuariosLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(49, 49, 49))
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelUsuariosLayout.setVerticalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelAgotados3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(panelReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(panelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelAgotados3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(217, 217, 217))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addGap(188, 188, 188))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(panelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(panelPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(panelCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(labelNombreUsuario))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelPendientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCatalogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelProductosMouseEntered
        // panelProductos.setBorder(BorderFactory.createBevelBorder(1));
        panelProductos.setBorder(BorderFactory.createLineBorder(Color.PINK));
    }//GEN-LAST:event_panelProductosMouseEntered

    private void panelProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelProductosMouseExited
        panelProductos.setBorder(BorderFactory.createLineBorder(Color.white));
    }//GEN-LAST:event_panelProductosMouseExited

    private void panelPendientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPendientesMouseEntered
        panelPendientes.setBorder(BorderFactory.createLineBorder(Color.PINK));
    }//GEN-LAST:event_panelPendientesMouseEntered

    private void panelPendientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPendientesMouseExited
        panelPendientes.setBorder(BorderFactory.createLineBorder(Color.white));
    }//GEN-LAST:event_panelPendientesMouseExited

    private void panelCatalogoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCatalogoMouseEntered
        panelCatalogo.setBorder(BorderFactory.createLineBorder(Color.PINK));
    }//GEN-LAST:event_panelCatalogoMouseEntered

    private void panelCatalogoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCatalogoMouseExited
        panelCatalogo.setBorder(BorderFactory.createLineBorder(Color.white));
    }//GEN-LAST:event_panelCatalogoMouseExited

    private void panelProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelProductosMouseClicked
        if (controlproductos == false) {
            productos p = new productos();
            p.setVisible(true);
            controlproductos = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            frameproductos.setAlwaysOnTop(true);
            frameproductos.setAlwaysOnTop(false);
        }
    }//GEN-LAST:event_panelProductosMouseClicked

    private void panelPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPendientesMouseClicked

        if (controlproductosPendientes == false) {
            productosPendientes proPen = new productosPendientes();
            proPen.setVisible(true);
            controlproductosPendientes = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            frameproductosPendientes.setAlwaysOnTop(true);
            frameproductosPendientes.setAlwaysOnTop(false);
        }

    }//GEN-LAST:event_panelPendientesMouseClicked

    private void panelCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCatalogoMouseClicked
        JOptionPane.showMessageDialog(null, "Fuera de servicio, trabajando en el", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_panelCatalogoMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        mensajeQuestion men = new mensajeQuestion();
        mensajeQuestion.labelMensaje.setText("¿ Esta seguro de  cerrar sesión ?");
        men.setVisible(true);
        men.setAlwaysOnTop(true);

    }//GEN-LAST:event_jLabel6MouseClicked

    private void panelAgotados3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAgotados3MouseClicked
        if (controlproductosAgotados == false) {
            productosAgotados proAgotados = new productosAgotados();
            proAgotados.setVisible(true);
            controlproductosAgotados = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            frameproductosAgotados.setAlwaysOnTop(true);
            frameproductosAgotados.setAlwaysOnTop(false);
        }

    }//GEN-LAST:event_panelAgotados3MouseClicked

    private void panelAgotados3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAgotados3MouseEntered
        panelAgotados3.setBorder(BorderFactory.createLineBorder(Color.PINK));
    }//GEN-LAST:event_panelAgotados3MouseEntered

    private void panelAgotados3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAgotados3MouseExited
        panelAgotados3.setBorder(BorderFactory.createLineBorder(Color.white));
    }//GEN-LAST:event_panelAgotados3MouseExited

    private void panelReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelReportesMouseClicked
        if (controlreportes == false) {
            reportes reporte = new reportes();
            reporte.setVisible(true);
            controlreportes = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            framereportes.setAlwaysOnTop(true);
            framereportes.setAlwaysOnTop(false);
        }

    }//GEN-LAST:event_panelReportesMouseClicked

    private void panelReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelReportesMouseEntered
        panelReportes.setBorder(BorderFactory.createLineBorder(Color.PINK));
    }//GEN-LAST:event_panelReportesMouseEntered

    private void panelReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelReportesMouseExited
        panelReportes.setBorder(BorderFactory.createLineBorder(Color.white));
    }//GEN-LAST:event_panelReportesMouseExited

    private void panelUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelUsuariosMouseClicked
        if (controlusuarios == false) {
            usuarios usu = new usuarios();
            usu.setVisible(true);
            controlusuarios = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya esta abierto esta ventana", "Advertencia", JOptionPane.WARNING_MESSAGE);
            frameusuarios.setAlwaysOnTop(true);
            frameusuarios.setAlwaysOnTop(false);
        }

    }//GEN-LAST:event_panelUsuariosMouseClicked

    private void panelUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelUsuariosMouseEntered
        panelUsuarios.setBorder(BorderFactory.createLineBorder(Color.PINK));
    }//GEN-LAST:event_panelUsuariosMouseEntered

    private void panelUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelUsuariosMouseExited
        panelUsuarios.setBorder(BorderFactory.createLineBorder(Color.white));
    }//GEN-LAST:event_panelUsuariosMouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      JOptionPane.showMessageDialog(null, "Cierra la session","Advertencia!",JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel labelNombreUsuario;
    private javax.swing.JPanel panelAgotados3;
    private javax.swing.JPanel panelCatalogo;
    private javax.swing.JPanel panelPendientes;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelUsuarios;
    // End of variables declaration//GEN-END:variables
}
