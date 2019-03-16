/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NuevasPantallas;

import control.controlReportes;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static pantallas.principal1.fechaAñoReportes;
import static pantallas.principal1.fechaFinalReportes;
import static pantallas.principal1.fechaInicialReportes;
import static pantallas.principal1.spinerReportes;
import static pantallas.principal1.txtMostrarVentaReportes;

/**
 *
 * @author famsa
 */
public class reportes extends javax.swing.JFrame {

    //para el frame
    int x = 0, y = 0;

    /**
     * Creates new form reportes
     */
    DefaultTableModel tablaReportes;

    public reportes() {
        tablaReportes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        //tablaReportes.setColumnIdentifiers(new Object[]{"Id", "Clave", "Nombre", "Cantidad", "Precio", "Subtotal"});
        tablaReportes.setColumnIdentifiers(new Object[]{"Cliente", "Pago", "Fecha"});
        initComponents();
        //mandamos el frame a pantalla  principal para controlarlo al abrirlo
        principal.framereportes = this;
        txtMostrarVentaReportes.setText("");
        labelVentaReportes.setText("Venta");
        labelFechaInicialReportes.setVisible(false);
        fechaInicialReportes.setVisible(false);
        labelFechaAñoReportes.setVisible(false);
        fechaAñoReportes.setVisible(false);
        labelFechaAñoReportes.setVisible(false);
        labelFechaFinalReportes.setVisible(false);
        fechaFinalReportes.setVisible(false);
        labelVentaReportes.setVisible(false);
        txtMostrarVentaReportes.setVisible(false);
        labelVentaReportes2.setVisible(false);
        txtDineroCaja.setVisible(false);

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
        jPanel20 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        spinerReportes = new javax.swing.JComboBox();
        fechaInicialReportes = new com.toedter.calendar.JDateChooser();
        labelFechaInicialReportes = new javax.swing.JLabel();
        labelFechaAñoReportes = new javax.swing.JLabel();
        fechaAñoReportes = new com.toedter.calendar.JYearChooser();
        fechaFinalReportes = new com.toedter.calendar.JDateChooser();
        labelFechaFinalReportes = new javax.swing.JLabel();
        btnGenerarReporteReportes = new javax.swing.JButton();
        txtMostrarVentaReportes = new javax.swing.JTextField();
        labelVentaReportes = new javax.swing.JLabel();
        labelDineroTotalCaja = new javax.swing.JLabel();
        labelVentaReportes2 = new javax.swing.JLabel();
        txtDineroTotalCaja = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDineroCaja = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes de ventas de productos");
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
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar reporte de ventas", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N
        jPanel20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel20KeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 204));
        jLabel15.setText("Seleccionar el tipo de reporte: ");

        spinerReportes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spinerReportes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona...", "Dia", "Semana", "Año" }));
        spinerReportes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spinerReportesItemStateChanged(evt);
            }
        });
        spinerReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spinerReportesActionPerformed(evt);
            }
        });
        spinerReportes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spinerReportesKeyPressed(evt);
            }
        });

        fechaInicialReportes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        fechaInicialReportes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaInicialReportesKeyPressed(evt);
            }
        });

        labelFechaInicialReportes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelFechaInicialReportes.setForeground(new java.awt.Color(255, 0, 204));
        labelFechaInicialReportes.setText("Ingresar fecha:");

        labelFechaAñoReportes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelFechaAñoReportes.setForeground(new java.awt.Color(255, 0, 204));
        labelFechaAñoReportes.setText("Ingresar año:");

        fechaAñoReportes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaAñoReportesKeyPressed(evt);
            }
        });

        fechaFinalReportes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        fechaFinalReportes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaFinalReportesKeyPressed(evt);
            }
        });

        labelFechaFinalReportes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelFechaFinalReportes.setForeground(new java.awt.Color(255, 0, 204));
        labelFechaFinalReportes.setText("Ingresar fecha final:");

        btnGenerarReporteReportes.setBackground(new java.awt.Color(255, 51, 255));
        btnGenerarReporteReportes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGenerarReporteReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReporteReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/generar.png"))); // NOI18N
        btnGenerarReporteReportes.setText("Generar reporte");
        btnGenerarReporteReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarReporteReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteReportesActionPerformed(evt);
            }
        });

        txtMostrarVentaReportes.setEditable(false);
        txtMostrarVentaReportes.setBackground(new java.awt.Color(229, 222, 222));
        txtMostrarVentaReportes.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        txtMostrarVentaReportes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMostrarVentaReportes.setText("0");

        labelVentaReportes.setBackground(new java.awt.Color(255, 255, 255));
        labelVentaReportes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelVentaReportes.setText("Venta");

        labelDineroTotalCaja.setBackground(new java.awt.Color(255, 255, 255));
        labelDineroTotalCaja.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelDineroTotalCaja.setText("Dinero total en caja");

        labelVentaReportes2.setBackground(new java.awt.Color(255, 255, 255));
        labelVentaReportes2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelVentaReportes2.setText("Dinero en caja");

        txtDineroTotalCaja.setEditable(false);
        txtDineroTotalCaja.setBackground(new java.awt.Color(229, 222, 222));
        txtDineroTotalCaja.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        txtDineroTotalCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDineroTotalCaja.setText("0");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDineroCaja.setEditable(false);
        txtDineroCaja.setBackground(new java.awt.Color(229, 222, 222));
        txtDineroCaja.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        txtDineroCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDineroCaja.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDineroTotalCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDineroTotalCaja)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addComponent(labelVentaReportes2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDineroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelFechaFinalReportes)
                                .addGroup(jPanel20Layout.createSequentialGroup()
                                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(labelFechaInicialReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                .addComponent(labelFechaAñoReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel15))
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(fechaInicialReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(spinerReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                        .addComponent(labelVentaReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMostrarVentaReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnGenerarReporteReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fechaFinalReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fechaAñoReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(spinerReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(labelFechaInicialReportes))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fechaInicialReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelFechaAñoReportes))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaAñoReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelFechaFinalReportes))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fechaFinalReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerarReporteReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMostrarVentaReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelVentaReportes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDineroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelVentaReportes2))
                .addGap(31, 31, 31)
                .addComponent(labelDineroTotalCaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDineroTotalCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles de Pagos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 0, 204))); // NOI18N
        jPanel19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel19KeyPressed(evt);
            }
        });

        jTable7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable7.setModel(tablaReportes);
        jTable7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable7KeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(164, Short.MAX_VALUE))
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

    private void spinerReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spinerReportesActionPerformed
        // TODO add your handling code here:
        String tipoReporte = spinerReportes.getSelectedItem().toString();
        if (tipoReporte.equals("Selecciona...")) {
            txtMostrarVentaReportes.setText("");
            labelVentaReportes.setText("Venta");
            labelFechaInicialReportes.setVisible(false);
            fechaInicialReportes.setVisible(false);
            labelFechaAñoReportes.setVisible(false);
            fechaAñoReportes.setVisible(false);
            labelFechaAñoReportes.setVisible(false);
            labelFechaFinalReportes.setVisible(false);
            fechaFinalReportes.setVisible(false);
            labelVentaReportes.setVisible(false);
            txtMostrarVentaReportes.setVisible(false);
            labelVentaReportes2.setVisible(false);
            txtDineroCaja.setVisible(false);
        }
        if (tipoReporte.equals("Dia")) {
            txtMostrarVentaReportes.setText("");
            labelVentaReportes.setText("Venta");
            labelFechaInicialReportes.setVisible(true);
            labelFechaInicialReportes.setText("Selecciona la fecha");
            fechaInicialReportes.setVisible(true);
            labelFechaAñoReportes.setVisible(false);
            fechaAñoReportes.setVisible(false);
            labelFechaAñoReportes.setVisible(false);
            fechaFinalReportes.setVisible(false);
            labelFechaFinalReportes.setVisible(false);
            labelVentaReportes.setVisible(true);
            txtMostrarVentaReportes.setVisible(true);
            labelVentaReportes2.setVisible(true);
            txtDineroCaja.setVisible(true);

        } else if (tipoReporte.equals("Semana")) {
            txtMostrarVentaReportes.setText("");
            labelVentaReportes.setText("Venta");
            labelFechaInicialReportes.setVisible(true);
            labelFechaInicialReportes.setText("Fecha inicial");

            fechaInicialReportes.setVisible(true);
            labelFechaAñoReportes.setVisible(false);
            fechaAñoReportes.setVisible(false);
            labelFechaAñoReportes.setVisible(false);

            fechaFinalReportes.setVisible(true);
            labelFechaFinalReportes.setText("Fecha final");
            labelFechaFinalReportes.setVisible(true);
            labelVentaReportes.setVisible(false);
            txtMostrarVentaReportes.setVisible(false);
            labelVentaReportes2.setVisible(false);
            txtDineroCaja.setVisible(false);

        } else if (tipoReporte.equals("Año")) {
            txtMostrarVentaReportes.setText("");
            labelVentaReportes.setText("Venta");
            labelFechaInicialReportes.setVisible(false);
            fechaInicialReportes.setVisible(false);
            labelFechaAñoReportes.setVisible(true);
            labelFechaAñoReportes.setText("Selecciona el año");
            fechaAñoReportes.setVisible(true);

            fechaFinalReportes.setVisible(false);
            labelFechaFinalReportes.setVisible(false);
            labelVentaReportes.setVisible(false);
            txtMostrarVentaReportes.setVisible(false);
            labelVentaReportes2.setVisible(false);
            txtDineroCaja.setVisible(false);

        } else if (tipoReporte.equals("Mes con mas ventas")) {
            txtMostrarVentaReportes.setText("");
            labelVentaReportes.setText("Venta");
            labelFechaInicialReportes.setVisible(false);
            fechaInicialReportes.setVisible(false);
            labelFechaAñoReportes.setVisible(true);
            labelFechaAñoReportes.setText("Selecciona el año");
            fechaAñoReportes.setVisible(true);

            fechaFinalReportes.setVisible(false);
            labelFechaFinalReportes.setVisible(false);
            labelVentaReportes.setVisible(false);
            txtMostrarVentaReportes.setVisible(false);
            labelVentaReportes2.setVisible(false);
            txtDineroCaja.setVisible(false);

        }
    }//GEN-LAST:event_spinerReportesActionPerformed

    private void btnGenerarReporteReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteReportesActionPerformed
        // TODO add your handling code here:

        String tipoReporte = spinerReportes.getSelectedItem().toString();
        //recibe  tiporeporte,fechai,fecha-f,fechaA,txtmostraVenta,tabla, default,label
        new controlReportes().reportes(tipoReporte, fechaInicialReportes, fechaFinalReportes, fechaAñoReportes, txtMostrarVentaReportes,
                jTable7, tablaReportes, labelVentaReportes, labelFechaInicialReportes,
                labelFechaAñoReportes, labelFechaFinalReportes, txtDineroCaja,
                txtDineroTotalCaja, labelDineroTotalCaja, txtDineroTotalCaja);
    }//GEN-LAST:event_btnGenerarReporteReportesActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_formMouseDragged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        principal.controlreportes = false;
    }//GEN-LAST:event_formWindowClosing

    private void spinerReportesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spinerReportesItemStateChanged
        if (tablaReportes.getRowCount() > 0) {
            vaciarTabla(jTable7, tablaReportes);
        }
        labelVentaReportes.setText("Venta total");
        labelDineroTotalCaja.setText("Venta total");

        txtMostrarVentaReportes.setText("0");

        txtDineroCaja.setText("0");
        txtDineroTotalCaja.setText("0");
    }//GEN-LAST:event_spinerReportesItemStateChanged

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        metodosBotones(evt);
    }//GEN-LAST:event_jPanel1KeyPressed

    private void jPanel20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel20KeyPressed
        metodosBotones(evt);// TODO add your handling code here:
    }//GEN-LAST:event_jPanel20KeyPressed

    private void fechaInicialReportesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaInicialReportesKeyPressed
        metodosBotones(evt);// TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialReportesKeyPressed

    private void fechaAñoReportesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaAñoReportesKeyPressed
        metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_fechaAñoReportesKeyPressed

    private void fechaFinalReportesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaFinalReportesKeyPressed
        metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinalReportesKeyPressed

    private void jTable7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable7KeyPressed
        metodosBotones(evt);// TODO add your handling code here:
    }//GEN-LAST:event_jTable7KeyPressed

    private void jPanel19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel19KeyPressed
        metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_jPanel19KeyPressed

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
     
    }//GEN-LAST:event_jPanel1KeyReleased

    private void spinerReportesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spinerReportesKeyPressed
       metodosBotones(evt); // TODO add your handling code here:
    }//GEN-LAST:event_spinerReportesKeyPressed
    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

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
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnGenerarReporteReportes;
    public static com.toedter.calendar.JYearChooser fechaAñoReportes;
    public static com.toedter.calendar.JDateChooser fechaFinalReportes;
    public static com.toedter.calendar.JDateChooser fechaInicialReportes;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable7;
    public static javax.swing.JLabel labelDineroTotalCaja;
    public static javax.swing.JLabel labelFechaAñoReportes;
    public static javax.swing.JLabel labelFechaFinalReportes;
    public static javax.swing.JLabel labelFechaInicialReportes;
    public static javax.swing.JLabel labelVentaReportes;
    public static javax.swing.JLabel labelVentaReportes2;
    public static javax.swing.JComboBox spinerReportes;
    public static javax.swing.JTextField txtDineroCaja;
    public static javax.swing.JTextField txtDineroTotalCaja;
    public static javax.swing.JTextField txtMostrarVentaReportes;
    // End of variables declaration//GEN-END:variables

    public void metodosBotones(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String tipoReporte = spinerReportes.getSelectedItem().toString();
            //recibe  tiporeporte,fechai,fecha-f,fechaA,txtmostraVenta,tabla, default,label
            new controlReportes().reportes(tipoReporte, fechaInicialReportes, fechaFinalReportes, fechaAñoReportes, txtMostrarVentaReportes,
                    jTable7, tablaReportes, labelVentaReportes, labelFechaInicialReportes,
                    labelFechaAñoReportes, labelFechaFinalReportes, txtDineroCaja,
                    txtDineroTotalCaja, labelDineroTotalCaja, txtDineroTotalCaja);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            principal.controlreportes = false;
            dispose();
        }
    }
}
