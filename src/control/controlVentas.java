/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import NuevasPantallas.principal;
import almacenamientoDeListas.almacenamientoDeListas;
import static almacenamientoDeListas.almacenamientoDeListas.listaClientes;
import beans.Clientes;
import beans.Deudatotal;
import beans.Fechaspruebas;
import beans.Medidas;
import beans.Pagos;
import beans.Productos;
import beans.Productosapartados;
import beans.Usuarios;
import com.sun.imageio.plugins.common.I18N;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mensajes.cargando;
import mensajes.mensajeAdvertencia;
import mensajes.mensajeError;
import mensajes.mensajeExito;
import modelo.daoClientes;
import modelo.daoDeudaTotal;
import modelo.daoFechas;
import modelo.daoMedidas;
import modelo.daoPagos;
import modelo.daoProductos;
import modelo.daoProductosApartados;
import modelo.daoUsuarios;
import pantallas.clienteConDeuda;
import static pantallas.clienteConDeuda.bean;
import pantallas.detallesDelProducto;
import static pantallas.detallesDelProducto.beanProductos;
import pantallas.nuevoRegistro;

import pantallas.registrarCliente;
import pantallas.registrarCliente2;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlVentas {

    //=========================== INSTANCIAMOS LA CLASES QUE UTILIZAREMOS ======================================
    validarCampos validar = new validarCampos();
    daoUsuarios daoUsuario = new daoUsuarios();
    daoClientes daoCliente = new daoClientes();
    Clientes beanCliente = new Clientes();
    daoProductos daoProductos = new daoProductos();
    daoProductosApartados daoProAP = new daoProductosApartados();
    daoMedidas daoMedi = new daoMedidas();
    daoFechas daoFech = new daoFechas();
    daoDeudaTotal daoDeuda = new daoDeudaTotal();
    daoPagos daoPag = new daoPagos();

    public void descontarExistenciasTablaProductos(JTable tablaProductos, DefaultTableModel defaultTablaPro, int cantidadParaComprar) {
        //System.out.println("entro aqui sin pedos");111111111111111111111111111
        int fila = tablaProductos.getSelectedRow();
        //System.out.println("fial " + fila);
        int valorExistencias = Integer.parseInt(tablaProductos.getValueAt(fila, 4) + "");
        valorExistencias = valorExistencias - cantidadParaComprar;
        tablaProductos.setValueAt(valorExistencias, fila, 4);

    }

    //ESTE METODO SE  UTILIZA SOLO PARA LOS PRODUCTOS YA ESTAN REGISTRADOS EN LA BD
    public void aceptarDetallesVenta(JTextField cantidad, Productos bean, int cantidadParaComprar,
            JFrame frame, JTable tablaProductos, DefaultTableModel defaultTablaPro,
            JTable tablaVentas, DefaultTableModel defaultTablaVentas, JTextField txtTotalAPagar) {

        if (validar.validarCampos(cantidad)) {
            //SI  EL CAMPO SE COMPLETO ENTONCES ARROJAREMOS UN MENSAJE     
            if (cantidadParaComprar <= bean.getCantidad()) {
                //MOSTRMOS MENSAJE DE SI LLEVARA ALGUN DETALLESU PRODCUTO
                int opcion = JOptionPane.showConfirmDialog(null, "¿El producto tendra algun detalle?", "Detalles del producto", JOptionPane.YES_NO_OPTION);
                //0==SI, Y 1==NO   

                if (opcion == 0) {
                    //AQUI PRESIONO QUE SI
                    //PRIMERO OBTNEMOS EL ID DEL PRODUCTO PARA  HACER LA CONSULTA Y  PODER MOSTRAR LOS DATOS DEL PRODUCTO,POR QUE EL CLIENTE APENAS SE VA REGISTRAR
                    //SI EL PRODUCTO TENDRA ALGUN DETALLE,ENTONCES MOSTRAREMOS
                    //SI LA CANTIDAD DESEADA ES MENOR A NUESTRAS EXISTENCIAS HABRIMOS LA SIGUIENTE VENTANA
                    //detallesDelProductoPAra comprar
                    detallesDelProducto.beanProductos = bean;
                    detallesDelProducto.cantidadParaComprar = cantidadParaComprar;

                    //VALIDAMOS SI  LA LISTA CLIENTES ESTA VACIA MANDAMOS UN NULL
                    //osea si el cliente ya esta registrado en la lista para no volverlo a registrar
                    System.out.println("lista cliente " + almacenamientoDeListas.listaClientes.size());
                    if (almacenamientoDeListas.listaClientes.size() == 0) {
                        detallesDelProducto.beanCliente = null;
                        nuevoRegistro.beanCliente = null;
                    } else {
                        System.out.println("lista cliente presiono si " + listaClientes.size());
                        detallesDelProducto.beanCliente = listaClientes.get(0);
                        nuevoRegistro.beanCliente = listaClientes.get(0);

                    }

                    frame.dispose();
                    detallesDelProducto dt = new detallesDelProducto();
                    dt.setVisible(true);

                    principal.controldetallesVenderProducto = false;
                } else {//TERMINA SI LA RESPUESTA  ES SI O NO
                    //SI ENTRO AQUI QUIERE DECIR QUE EL PRODUCTO NO LLEVA  DETALLES ENTONCES LO  MANDAREMOS RAPIDO A LA TABLA VENTAS
                    llenarTablaVentas(tablaVentas, defaultTablaVentas, bean, cantidadParaComprar, txtTotalAPagar);

                    descontarExistenciasTablaProductos(tablaProductos, defaultTablaPro, cantidadParaComprar);
                    frame.dispose();

                    principal.controldetallesVenderProducto = false;

                }

            } else {//termina  la validacion de si la cantidad <= a las existencias
                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("No cuenta con suficientes productos");
                menAdvertencia.setVisible(true);
                menAdvertencia.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "No cuenta con suficientes productos", "Advertencia", JOptionPane.WARNING_MESSAGE);

            }

        } else {//termina la  validacion del  campo

        }
    }
// ===================== AQUI TERMINA METODO MANDADO A TRAER DESDE EL BOTON ACEPTAR DE detallesVenderProducto();===============

    //Metodo para mandar  los productos ala tabla, asi como guardar sus medidas o fechas de ser necesario
    public void aceptarDetallesDelProducto(JTextField nombreCliente, JTextField txtTelefono, JTextArea descripcion, JCheckBox medidas, JCheckBox fechas,
            JTextField talle, JTextField sise, JTextField hombros, JTextField busto, JTextField cintura, JTextField cadera, JTextField largoFalda,
            JTextField anchoPuño, JDateChooser fechaPrueba, JDateChooser fechaEvento, String idUsuario, Productos beanProductos,
            int cantidadParaComprar, JTable tablaVentas, DefaultTableModel defaultTablaVentas, JFrame frame, JTextField totalApagar,
            JTable tablaProductos, DefaultTableModel defaultTablaPro) {

        if (validar.validarCampos(nombreCliente)) {
            //SI LA VALIDACION DEL CAMPO NOMBRE ES TRUE ENTONCES OBTENEMOS LOS VALORES DE TELEFONO Y DESCRIPCION
            //validamos si el campo telefono y la descripcion viene como vacios
            //les asiganmos sin telefon y sin descripcion

            String telefono = txtTelefono.getText().toString();

            String des = descripcion.getText().toString();

            if (telefono.trim().isEmpty()) {

                telefono = "sin teléfono";

            } else {

            }

            if (des.isEmpty()) {

                des = "Sin descripción";

            } else {

            }

            //DESDE AQUI GUARDAMOS EL CLIENTE  EN LA LISTA CLIENTES
            //llenamos el beanCliente
            beanCliente.setNombrecompleto(nombreCliente.getText());
            beanCliente.setTelefono(telefono);

            beanCliente.setFecharegistro(validar.obtenerFechaActual());
            //consultamos el usuario(administrador que esta registrando a este cliente)
            //y obtenemos elbean para asi poder registrar al cliente
            Usuarios beanUsu = (Usuarios) daoUsuario.consultaEspecifica(idUsuario);
            beanCliente.setUsuarios(beanUsu);
            //LLENAMOS UNA LISTA DE TIPO CLIENTE PARA MANTENER AL CLIENTE VIVO DURANTE EL PROCESO
            //AQUI EN ESTE METODO NO SE MANDARA A REGISTRAR NADA ALA BD
            //ANTE DE AGREGAR EL USUARIO A ALA LISTA CHECAREMOS SI LA LISTA ESTA VACIA O NO
            //SI NO ESTA VACIA QUIERE DECIR QUE EL MISMO USUARIO SEGUIRA COMPRANDO Y MOSTRAMOS LOS DATOS DEL USUARIO
            //Y BLOQUEAMOS EL INPUT PARA QUE NO SE PUEDA EDITAR SUS DATOS Y ASI MISMO HAREMOS CON LAS DEMAS LISTAS,
            //EJEMPLO SI AGREGA UN PRODUCTO Y MAS TARDE DESEA AGREGAR  EL MISMO PRODUCTO SOLO ACTUALIZAREMOS LA CANTIDAD EN LA TABLA VENTAS
            //llenamos la lista clientes

            llenarListaClientes(beanCliente);

            //llenamos la listaDescripcion
            Productosapartados beanProductosApartados = new Productosapartados();
            beanProductosApartados.setIdProducto(beanProductos.getIdproductos());
            beanProductosApartados.setDetallesproducto(des);

            //true=ya esta registrado 
            //REVISAMOS SI  LOS DOS CHECK ESTAN SELECCIONADOS ENTONCES TENDREMOS QUE GUARDAR FEHCAS Y MEDIDAS
            if (medidas.isSelected() && fechas.isSelected()) {
                //SI LOS DOS  ESTAN SELECCIONADOS  VALIDAMOS QUE LOS CAMPOS ESTEN COMPLETOS Y OBTENDREMOS LAS MEDIDAS Y LAS FECHAS
                if (validarCamposMedidasYfechas(talle, sise, hombros, busto, cintura, cadera, largoFalda, anchoPuño, beanProductos, fechaPrueba, fechaEvento)) {
                    if (llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, cantidadParaComprar, totalApagar)) {
//Cerramos la ventana detallesProductos y cambiamos la existenciade productos solo en la tabla productos 
                        //no en la bda
                        //buscamos si el cliente tiene  una deuda, si tiene  mostramos un una pantalla con su deuda
                        //el cual solo se cerrar cuando se presione el boton registrar venta de la pantalla productos  de la principal1
                        // y le sumamos  el costo del producto y lo mostramos en como nueva deuda
                        //enviamos el precio para poder sumarlo a la dedua que el ya tiene
                        buscarClienteSiTieneDeuda(beanProductos.getPrecio());

                        descontarExistenciasTablaProductos(tablaProductos, defaultTablaPro, cantidadParaComprar);
                        llenarListaDescripcion(beanProductosApartados, beanProductos.getIdproductos());
                        principal.controldetallesDelProducto = false;
                        frame.dispose();

                    } else {
                        mensajeError menError = new mensajeError();
                        mensajeError.labelMensaje.setText("Error al agregar el producto en la tabla ventas");
                        menError.setVisible(true);
                        menError.setAlwaysOnTop(true);
                        // JOptionPane.showMessageDialog(null, "Error al agregar el producto en la tabla ventas", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                }

            } else if (medidas.isSelected()) {
                if (validarCamposMedidas(talle, sise, hombros, busto, cintura, cadera, largoFalda, anchoPuño, beanProductos)) {

                    if (llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, cantidadParaComprar, totalApagar)) {
                        //Cerramos la ventana detallesProductos y cambiamos la existenciade productos solo en la tabla productos 
                        //no en la bd
                        //buscamos si el cliente tiene  una deuda, si tiene  mostramos un una pantalla con su deuda
                        //el cual solo se cerrar cuando se presione el boton registrar venta de la pantalla productos  de la principal1
                        // y le sumamos  el costo del producto y lo mostramos en como nueva deuda
                        //enviamos el precio para poder sumarlo a la dedua que el ya tiene
                        buscarClienteSiTieneDeuda(beanProductos.getPrecio());

                        descontarExistenciasTablaProductos(tablaProductos, defaultTablaPro, cantidadParaComprar);
                        llenarListaDescripcion(beanProductosApartados, beanProductos.getIdproductos());
                        principal.controldetallesDelProducto = false;
                        frame.dispose();

                    } else {
                        mensajeError menError = new mensajeError();
                        mensajeError.labelMensaje.setText("Error al agregar el producto en la tabla ventas");
                        menError.setVisible(true);
                        menError.setAlwaysOnTop(true);
                        //JOptionPane.showMessageDialog(null, "Error al agregar el producto en la tabla ventas", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                }
            } else if (fechas.isSelected()) {
                if (validarCamposFechas(fechaPrueba, fechaEvento, beanProductos)) {
                    if (llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, cantidadParaComprar, totalApagar)) {
                        //Cerramos la ventana detallesProductos y cambiamos la existenciade productos solo en la tabla productos 
                        //no en la bd
                        //buscamos si el cliente tiene  una deuda, si tiene  mostramos un una pantalla con su deuda
                        //el cual solo se cerrar cuando se presione el boton registrar venta de la pantalla productos  de la principal1
                        // y le sumamos  el costo del producto y lo mostramos en como nueva deuda
                        //enviamos el precio para poder sumarlo a la dedua que el ya tiene
                        buscarClienteSiTieneDeuda(beanProductos.getPrecio());

                        descontarExistenciasTablaProductos(tablaProductos, defaultTablaPro, cantidadParaComprar);
                        llenarListaDescripcion(beanProductosApartados, beanProductos.getIdproductos());
                        principal.controldetallesDelProducto = false;
                        frame.dispose();

                    } else {
                        mensajeError menError = new mensajeError();
                        mensajeError.labelMensaje.setText("Error al agregar el producto en la tabla ventas");
                        menError.setVisible(true);
                        menError.setAlwaysOnTop(true);
                        //JOptionPane.showMessageDialog(null, "Error al agregar el producto en la tabla ventas", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                }
            } else {
                if (llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, cantidadParaComprar, totalApagar)) {
                    //Cerramos la ventana detallesProductos y cambiamos la existenciade productos solo en la tabla productos 
                    //no en la bd
                    //buscamos si el cliente tiene  una deuda, si tiene  mostramos un una pantalla con su deuda
                    //el cual solo se cerrar cuando se presione el boton registrar venta de la pantalla productos  de la principal1
                    // y le sumamos  el costo del producto y lo mostramos en como nueva deuda
                    //enviamos el precio para poder sumarlo a la dedua que el ya tiene
                    buscarClienteSiTieneDeuda(beanProductos.getPrecio());
                    descontarExistenciasTablaProductos(tablaProductos, defaultTablaPro, cantidadParaComprar);
                    llenarListaDescripcion(beanProductosApartados, beanProductos.getIdproductos());
                    principal.controldetallesDelProducto = false;
                    frame.dispose();

                } else {
                    mensajeError menError = new mensajeError();
                    mensajeError.labelMensaje.setText("Error al agregar el producto en la tabla ventas");
                    menError.setVisible(true);
                    menError.setAlwaysOnTop(true);
                    // JOptionPane.showMessageDialog(null, "Error al agregar el producto en la tabla ventas", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        } else {
            //de la validacio nombreusuario
        }

    }

    public boolean llenarTablaVentas(JTable tablaVentas, DefaultTableModel defaultTabla, Productos bean, int cantidadParaComprar, JTextField txtTotalApagar) {
        boolean ban = false;

        //vamos a buscar si un producto ya esta registrado solo le modificaremos la cantidad
        for (int i = 0; i < tablaVentas.getRowCount(); i++) {

            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
            if (valorId == bean.getIdproductos()) {

                //si es igual quiere decir que si encontro este producto en la tabla entonces obtendremos la cantidad de la tabla y
                //y le sumaremos la nueva cantidad 
                int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                cantidadParaComprar = cantidadParaComprar + valorCantidad;
                tablaVentas.setValueAt(cantidadParaComprar, i, 2);
                int subtotal = bean.getPrecio() * cantidadParaComprar;
                tablaVentas.setValueAt(subtotal, i, 4);
                ban = true;
                i = tablaVentas.getRowCount();

            } else {

            }

        }

        if (ban) {
            //si es true quiere decir que ya fue modificado
        } else {
            //si el producto no esta en la tabla ventas entonces lo registramos

            int subtotal = bean.getPrecio() * cantidadParaComprar;
            defaultTabla.addRow(new Object[]{bean.getIdproductos(), bean.getClave(), cantidadParaComprar, bean.getPrecio(), subtotal});
            ban = true;
        }

        //calculamos el total a pagar
        int totalApagar = 0;
        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
            int valorSubTotal = Integer.parseInt(tablaVentas.getValueAt(i, 4) + "");

            totalApagar = totalApagar + valorSubTotal;

            txtTotalApagar.setText(totalApagar + "");

        }

        return ban;
    }

    public void llenarListaDescripcion(Productosapartados bean, int idProducto) {
        boolean ban = false;

        if (almacenamientoDeListas.listaDescripcion.size() != 0) {
            for (int i = 0; i < almacenamientoDeListas.listaDescripcion.size(); i++) {
                //si el producto ya esta la lista solo le cambiamos la descripcion por la nueva y  ya no se agrega
                if (almacenamientoDeListas.listaDescripcion.get(i).getIdProducto() == idProducto) {
                    almacenamientoDeListas.listaDescripcion.get(i).setDetallesproducto(bean.getDetallesproducto());
                    ban = true;
                } else {

                }

            }
        } else {

        }
        if (ban) {

        } else {
            almacenamientoDeListas.listaDescripcion.add(bean);

        }

    }

    public void llenarListaClientes(Clientes bean) {
        boolean ban = false;

        if (almacenamientoDeListas.listaClientes.size() == 0) {

            almacenamientoDeListas.listaClientes.add(bean);
            ban = true;
            //si se agrega el usuario a la lista mandamos true;
        } else {
            System.out.println("no se agrego el cliente por que ya existe");
        }

    }

    public void llenarListaMedidas(Medidas bean, int idProducto) {
        boolean ban = false;

        if (almacenamientoDeListas.listaMedidas.size() != 0) {
            for (int i = 0; i < almacenamientoDeListas.listaMedidas.size(); i++) {
                if (almacenamientoDeListas.listaMedidas.get(i).getIdProducto() == idProducto) {

                    Medidas beanM = almacenamientoDeListas.listaMedidas.get(i);
                    System.out.println("ya tengo este producto en lista medidas " + beanM.getTalle());

                    beanM.setTalle(Float.parseFloat(bean.getTalle() + ""));
                    beanM.setSise(Float.parseFloat(bean.getSise() + ""));
                    beanM.setHombros(Float.parseFloat(bean.getHombros() + ""));
                    beanM.setBusto(Float.parseFloat(bean.getBusto() + ""));
                    beanM.setLargoFalda(Float.parseFloat(bean.getLargoFalda() + ""));
                    beanM.setAnchoPuno(Float.parseFloat(bean.getAnchoPuno() + ""));
                    beanM.setCintura(Float.parseFloat(bean.getCintura() + ""));
                    beanM.setCadera(Float.parseFloat(bean.getCadera() + ""));
                    ban = true;

                }
            }

        }
        if (ban) {

        } else {
            almacenamientoDeListas.listaMedidas.add(bean);
            System.out.println("tamaño lista medidas " + almacenamientoDeListas.listaMedidas.size());
        }

    }

    public void llenarListaFechas(Fechaspruebas bean, int idProducto) {
        boolean ban = false;
        if (almacenamientoDeListas.listaFechas.size() != 0) {
            for (int i = 0; i < almacenamientoDeListas.listaFechas.size(); i++) {
                Fechaspruebas beanFechas = almacenamientoDeListas.listaFechas.get(i);

                if (beanFechas.getIdProducto() == idProducto) {
                    System.out.println("ya tengo el producto en lista fechas");
                    beanFechas.setFechaprueba(bean.getFechaprueba());
                    beanFechas.setFechaevento(bean.getFechaevento());
                    ban = true;

                } else {

                }

            }
        } else {

        }

        if (ban) {

        } else {
            almacenamientoDeListas.listaFechas.add(bean);
            System.out.println("tamaño lista fechas " + almacenamientoDeListas.listaFechas.size());
        }

    }
//=================== METODO PARA REGISTRAR VENTA BTNREGISTRARVENTA DE LA PANTALLA PRINCIPAL ==================    

    public void registrarVenta(JTable tablaVentas, DefaultTableModel defaultTableVentas, JTextField txtTotalApagar,
            JTextField txtEfectivoRecibido, JTextField txtCambio, String idUsuario, JFrame frame,
            JTable tablaProductos, DefaultTableModel defaultTablaProductos, JTable tablaPendientes, DefaultTableModel defaultTablaPendientes) {

//el cliente se registra en 2 ocasiones
//1.-  cuando la deuda es mas grande que el efectivo recibido y se pone como apartado
//2.- cunado un producto tiene medidas o fechas 
//buscamos si el cliente tiene una deuda existen de serasi solo se modificara su deudaTotal sin registra al cliente
//
        if (validar.validarCampos(txtEfectivoRecibido)) {
            Thread hilo = new Thread() {
                @Override
                public void run() {
                    cargando car = new cargando();

                    car.setVisible(true);
                    car.setAlwaysOnTop(true);
                    String estadoProductoApartado = "";
                    String estadoDeudadTotal = "";
                    int abonoPago = 0;
                    int ultimoProRegistrado = 0;
                    //Recorremos la tabla para sabeque productos vamos a registrar y obtener su id y ponerlo en la tabla
                    for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                        int valorIDoClave = Integer.parseInt(tablaVentas.getValueAt(i, 1) + "");
                        System.out.println("cbusqued  por clave " + valorIDoClave);
                        Productos beanConsutlaPro = daoProductos.consultaEspecificaPorClave2019(valorIDoClave + "");
                        System.out.println("beanConsutlaPro " + beanConsutlaPro);
                        if (beanConsutlaPro == null) {
                            System.out.println("no eetsa registrado  este  9 " + valorIDoClave);
                            System.out.println("tamaño lista 9 " + almacenamientoDeListas.listaProductos.size());
                            for (int j = 0; j < almacenamientoDeListas.listaProductos.size(); j++) {

                                if (valorIDoClave == Integer.parseInt(almacenamientoDeListas.listaProductos.get(j).getClave())) {
                                    Productos beanRegistrarPro = almacenamientoDeListas.listaProductos.get(j);
                                    System.out.println("bean registrar pro img " + beanRegistrarPro.getFotoString());
                                    if (daoProductos.registrarSQL(beanRegistrarPro)) {
                                        System.out.println("registrado el pro");
                                        //System.out.println("se registro el producto machin y cambiamos la clave  por  id en la tabla");

                                        ultimoProRegistrado = daoProductos.obtener_id();
                                        System.out.println("ultimo " + ultimoProRegistrado);
                                        tablaVentas.setValueAt(ultimoProRegistrado, i, 0);
                                        // almacenamientoDeListas.listaProductos.set(i, almacenamientoDeListas.listaProductos.get(i).setIdproductos(ultimoProRegistrado));
                                        almacenamientoDeListas.listaProductos.get(j).setIdproductos(ultimoProRegistrado);
                                        j = almacenamientoDeListas.listaProductos.size();
                                    }
                                }
                            }
                            //========================= lista descripcion
                            for (int j = 0; j < almacenamientoDeListas.listaDescripcion.size(); j++) {
                                System.out.println("for des");
                                System.out.println("valor id  clave " + valorIDoClave);
                                System.out.println("valor de la  lista  descripcion " + almacenamientoDeListas.listaDescripcion.get(j).getIdProducto());
                                if (valorIDoClave == almacenamientoDeListas.listaDescripcion.get(j).getIdProducto()) {
                                    System.out.println("entro des");
                                    // int ultimoProRegistrado = daoProductos.obtener_id();

                                    almacenamientoDeListas.listaDescripcion.get(j).setIdProducto(ultimoProRegistrado);

                                }
                            }

                            //=============================== hasta aqui
                            //========================= lista medidas
                            for (int j = 0; j < almacenamientoDeListas.listaMedidas.size(); j++) {
                                System.out.println("entro  fro lismed");
                                if (valorIDoClave == almacenamientoDeListas.listaMedidas.get(j).getIdProducto()) {

                                    System.out.println("if  medi");
                                    //int ultimoProRegistrado = daoProductos.obtener_id();

                                    almacenamientoDeListas.listaMedidas.get(j).setIdProducto(ultimoProRegistrado);

                                }
                            }

                            //=============================== hasta aqui
                            //========================= lista Fechas
                            for (int j = 0; j < almacenamientoDeListas.listaFechas.size(); j++) {
                                System.out.println("forfehcas");
                                if (valorIDoClave == almacenamientoDeListas.listaFechas.get(j).getIdProducto()) {

                                    System.out.println("if  fechas");
                                    //int ultimoProRegistrado = daoProductos.obtener_id();
                                    System.out.println("cambio listafechas " + almacenamientoDeListas.listaFechas.get(j).getIdProducto());
                                    almacenamientoDeListas.listaFechas.get(j).setIdProducto(ultimoProRegistrado);
                                    System.out.println("cambio listafechas 222" + almacenamientoDeListas.listaFechas.get(j).getIdProducto());
                                    //se hace el cambio en la tabla
                                    tablaVentas.setValueAt(ultimoProRegistrado, i, 0);

                                }
                            }

                            //=============================== hasta aqui
                        } else {
                            System.out.println("este producto esta registrado");
                        }

                    }

                    if (almacenamientoDeListas.listaClientes.size() == 0) {
                        System.out.println("entro en registrar venta 1");
                        //si la lista  clientes ==0 no hay  ningun clienteregistrado  y el efectivo recibido es menor a totalapagar
                        //mostraremos una pantalla para que registre el cliente, y asi porder guardarlo en la bd y porner asignarle una deuda
                        //ya que sus producto sera con estado apartado
                        clienteConDeuda ccd = new clienteConDeuda();
                        ccd.setVisible(false);
                        //validamos si el total a pagar si esmayor a l efetivo recibido para obtener los diferentes estados 
                        if (Integer.parseInt(txtEfectivoRecibido.getText()) < Integer.parseInt(txtTotalApagar.getText())) {
                            //si efectivo recibido es menora totalApagar entonces los estados son como aparatdos
                            //registramos el cliente aqui mero 1
                            System.out.println("efectivo < total a pagar");
                            almacenamientoDeListas.estadoProductoAPartadoGlobal = "Apartado";
                            almacenamientoDeListas.estadoDeudaGlobal = "No pagado";
                            almacenamientoDeListas.abonoPagoGlobal = Integer.parseInt(txtEfectivoRecibido.getText());
                            System.out.println("abono global 11 " + almacenamientoDeListas.abonoPagoGlobal);
                            car.dispose();
                            registrarCliente registrarClien = new registrarCliente();
                            registrarClien.setVisible(true);

                        } else {
                            //si txtRecibiodo >= txtTotalApagar
                            System.out.println("entro en registrar venta 3");
                            //si efectivo recibo es mayor  a totalApagar entonces mostramos mensaje de los posibles  estados
                            //Pagado  entregado-->
                            //pagado  entregado se registra en la tabla productos vendidos
                            car.dispose();
                            String status[] = {"Pagado entregado", "Pagado no entregado"};
                            //********** aqui  yaaa-----------------
                            Object estado = JOptionPane.showInputDialog(frame, "Estado del producto", "Seleccionar el estado de los productos", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
                            if (String.valueOf(estado).equalsIgnoreCase("Pagado no entregado")) {

                                //pagado no entrgado tenemos queregistrar al cliente
                                almacenamientoDeListas.estadoProductoAPartadoGlobal = "Pagado no entregado";
                                almacenamientoDeListas.estadoDeudaGlobal = "Pagado";
                                almacenamientoDeListas.abonoPagoGlobal = Integer.parseInt(txtTotalApagar.getText());
                                car.dispose();
                                registrarCliente registrarClien = new registrarCliente();
                                registrarClien.setVisible(true);

                            } else if (String.valueOf(estado).equalsIgnoreCase("Pagado entregado")) {
                                car.setVisible(true);
                                System.out.println("entro en registrar venta 4");
                                almacenamientoDeListas.estadoProductoAPartadoGlobal = "Pagado entregado";
                                almacenamientoDeListas.estadoDeudaGlobal = "Pagado";
                                almacenamientoDeListas.abonoPagoGlobal = Integer.parseInt(txtTotalApagar.getText());
                                Clientes beanCliente = daoCliente.consultaEspecificaPorNombreBean2019("cliente general");

                                System.out.println("cliente general " + beanCliente.getIdclientes());
                                boolean banRegistroVenta = false;
                                //Nota=aqui debemos asignarle esa venta al cliente general
                                //no se registra el usuario, solo se registra el producto en apartados,deudaTotal  y pagos 
                                System.out.println("El  cliente esta registrado en la bd pero no tiene deuda");
                                //si es true quiered decir que el cliente si esta registrado en la bd pero no tiene deuda
                                //registramos la deduatotal del cliente
                                //registramos los pagos
                                //registramos los  produtos apartados

                                System.out.println("vamos a registrar la deuda total");

                                Deudatotal beanDeudaT = new Deudatotal();

                                beanDeudaT.setClientes(beanCliente);
                                beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                                beanDeudaT.setStatus(almacenamientoDeListas.estadoDeudaGlobal);
                                beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                                if (daoDeuda.registrar(beanDeudaT)) {
                                    System.out.println("se  registro la ideuda");
                                    System.out.println("registramos el pago");

                                    // Deudatotal beanDeuda = (Deudatotal) daoDeuda.consultaEspecifica(almacenamientoDeListas.ultimoRegistroDeuda + "");
                                    Deudatotal beanDeuda = daoDeuda.consultaEspecifica2019(almacenamientoDeListas.ultimoRegistroDeuda + "");
                                    System.out.println("lista de impresio " + beanDeuda.getIddeudatotal());

                                    //buscamos su deuda y obtenemos el id deuda para guardarlo en la tabla pagos
                                    Pagos beanPagos = new Pagos();
                                    System.out.println("tomo eeste idDeudaTotal " + beanDeuda.getIddeudatotal());
                                    beanPagos.setDeudatotal(beanDeuda);
                                    Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);
                                    beanPagos.setUsuarios(beanUsuario);
                                    beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                    beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                    beanPagos.setFecharegistro2(obtenerFechaDate());

                                    if (daoPag.registrar(beanPagos)) {
                                        banRegistroVenta = true;
                                        System.out.println("registro el pago");
                                        System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                            int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                            System.out.println("valor enviado id pro " + valorId);
                                            Productos beanProductos = (Productos) daoProductos.consultaEspecifica2019(valorId + "");
                                            Productosapartados beanProApartados = new Productosapartados();
                                            //-------  aqui comienzo ---------------
                                            beanProApartados.setClientes(beanCliente);
                                            beanProApartados.setProductos(beanProductos);
                                            beanProApartados.setUsuarios(beanUsuario);
                                            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                            beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                            beanProApartados.setCantidadVenta(valorCantidad);
                                            beanProApartados.setDetallesproducto("Sin detalles");

                                            if (daoProAP.registrar(beanProApartados)) {
                                                banRegistroVenta = true;
                                                System.out.println("bean cant " + beanProductos.getCantidad());
                                                System.out.println("valor cant  " + valorCantidad);
                                                int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                beanProductos.setCantidad(nuevaExistencia);
                                                System.out.println("actualizamos las existencias de los productos ");
                                                if (daoProductos.editarExistencias2019(beanProductos)) {
                                                    System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                }

                                                System.out.println("se registraron los productos en apartados " + i);
                                            } else {
                                                banRegistroVenta = false;
                                                System.out.println("error al registrar los pro apartados " + i);
                                            }

                                        }

                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("eroro al  registrar el pago");
                                    }

                                } else {
                                    System.out.println("error al registrar deuda total");
                                }

                                //here
                                if (banRegistroVenta) {

                                    validar.limpiarCampos(txtTotalApagar);
                                    validar.limpiarCampos(txtEfectivoRecibido);
                                    validar.limpiarCampos(txtCambio);
                                    vaciarTabla(tablaVentas, defaultTableVentas);
                                    car.dispose();
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);
                                    //JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);
                                    // new controlProductoPendientes().llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);

                                } else {

                                }

                            } else {
                                System.out.println("se cancelo el status");
                            }//aquiva un else

                        }

                    } else if (almacenamientoDeListas.listaClientes.size() > 0 && Integer.parseInt(txtEfectivoRecibido.getText()) < Integer.parseInt(txtTotalApagar.getText())
                            || almacenamientoDeListas.listaClientes.size() > 0 && Integer.parseInt(txtEfectivoRecibido.getText()) >= Integer.parseInt(txtTotalApagar.getText())) {
//el cliente esta en la listaClientes y los productos se iran como apartados, y tambien buscaremos si tiene medidas y fechas
//en caso de no tener  medidas y fechas  1.-registramos el cliente  2.- registramos  en apartados  3.- registramos deudaTotal 4.-registramos en pagos
//en caso de tener  medidas y fechas  1.-registramos el cliente  2.- registramos  en apartados 3.- registramos medidas 4.- registramos fechas 5.- registramos deudaTotal 6.-registramos en pagos
                        //consultamos el id usuarios para enviarlo al bean cliente
                        System.out.println("entro en btn registerar venta");
                        if (Integer.parseInt(txtEfectivoRecibido.getText()) < Integer.parseInt(txtTotalApagar.getText())) {
                            estadoProductoApartado = "Apartado";
                            estadoDeudadTotal = "No pagado";
                            abonoPago = Integer.parseInt(txtEfectivoRecibido.getText());
                        } else if (Integer.parseInt(txtEfectivoRecibido.getText()) >= Integer.parseInt(txtTotalApagar.getText())) {
                            estadoProductoApartado = "Pagado no entregado";
                            estadoDeudadTotal = "pagado";
                            abonoPago = Integer.parseInt(txtTotalApagar.getText());
                        } else {

                        }
                        boolean banRegistroVenta = false;
                        boolean banClienteRegistradoSinDeuda = false;

                        //here
                        Clientes beanClienteLista = almacenamientoDeListas.listaClientes.get(0);
                        Clientes beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean2019(beanClienteLista.getNombrecompleto());
                        if (beanCliente != null) {
                            System.out.println("cliente registrado en la base de datos 5");
                            //Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                            Deudatotal listaDeuda2 = daoCliente.obtnerDeudaXIdCliente(beanCliente.getIdclientes() + "");
                            if (listaDeuda2 != null) {
                                System.out.println("esta registrado en la tabla deudatotal 5");

                                System.out.println("el cliente tiene deuda   5");
                                System.out.println("no registramos el cliente pero modificamos la deuda");
                                int nuevaDeuda = listaDeuda2.getDeudatotal() + Integer.parseInt(txtTotalApagar.getText());
                                int abonoActual = abonoPago;
                                //mandamos a la otra pantalla para mostrar informacion de la deuda
                                beanCliente.setNuevaDeuda(nuevaDeuda);
                                beanCliente.setAbonoActual(abonoActual);
                                //enviamos el bean a pantalla clientecondeuda
                                clienteConDeuda.bean = beanCliente;
                                clienteConDeuda ccd = new clienteConDeuda();
                                ccd.setVisible(true);
                                listaDeuda2.setDeudatotal(nuevaDeuda);
                                if (daoDeuda.editarDeuda2019(listaDeuda2)) {
                                    banRegistroVenta = true;
                                    System.out.println("se modfio la deuda del cliente 5");
                                    System.out.println("vamos a registrar pagos  5");
                                    Pagos beanPagos = new Pagos();
                                    beanPagos.setDeudatotal(listaDeuda2);
                                    Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);
                                    beanPagos.setUsuarios(beanUsuario);

                                    beanPagos.setAbono(abonoPago);
                                    beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                    beanPagos.setFecharegistro2(obtenerFechaDate());
                                    //ya  pase

                                    if (daoPag.registrar(beanPagos)) {
                                        banRegistroVenta = true;
                                        System.out.println("registro el pago 5");
                                        System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas 5");

                                        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                            System.out.println("valor id de la tabla " + valorId);
                                            int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                            Productos beanProductos = (Productos) daoProductos.consultaEspecifica2019(valorId + "");

                                            Productosapartados beanProApartados = new Productosapartados();
                                            beanProApartados.setClientes(beanCliente);
                                            beanProApartados.setProductos(beanProductos);
                                            beanProApartados.setUsuarios(beanUsuario);
                                            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                            beanProApartados.setStatus(estadoProductoApartado);
                                            beanProApartados.setCantidadVenta(valorCantidad);
                                            //aqui  vamos a sacar la descripcion de cada producto 
                                            for (int j = 0; j < almacenamientoDeListas.listaDescripcion.size(); j++) {
                                                if (almacenamientoDeListas.listaDescripcion.get(j).getIdProducto() == valorId) {
                                                    System.out.println("este pro lleva detalle  " + almacenamientoDeListas.listaDescripcion.get(j).getIdProducto());
                                                    beanProApartados.setDetallesproducto(almacenamientoDeListas.listaDescripcion.get(j).getDetallesproducto());
                                                    j = almacenamientoDeListas.listaDescripcion.size();
                                                }
                                            }

                                            if (daoProAP.registrar(beanProApartados)) {
                                                banRegistroVenta = true;

                                                System.out.println("se registro aparatados 5");
                                                int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                beanProductos.setCantidad(nuevaExistencia);
                                                System.out.println("actualizamos las existencias de los productos 5");
                                                if (daoProductos.editarExistencias2019(beanProductos)) {
                                                    System.out.println("se modifico  la existencia del producto 5 " + beanProductos.getIdproductos());
                                                }
                                                //Vamos a registrar las medida y  fechas
                                                //consultamos el ultimo registro de apartados
                                                Productosapartados benaProApar2 = (Productosapartados) daoProAP.consultaEspecifica2019(almacenamientoDeListas.idProductoApartadoRegistrado + "");
                                                System.out.println("ultimo  id de pro apartados " + benaProApar2.getIdproductosapartados());
                                                if (buscarMedidas(valorId) != null) {
                                                    Medidas beanMedidas2 = buscarMedidas(valorId);
                                                    //conusltamos el ultimo producto aparatado

                                                    beanMedidas2.setProductosapartados(benaProApar2);
                                                    //el bean trae las medidas
                                                    if (daoMedi.registrar(beanMedidas2)) {
                                                        banRegistroVenta = true;
                                                        System.out.println("se registraron las medidas 5");

                                                        // si se registran la medidas buscamos las fechas
                                                        if (buscarFechas(valorId) != null) {
                                                            Fechaspruebas beanFechas2 = buscarFechas(valorId);
                                                            beanFechas2.setProductosapartados(benaProApar2);

                                                            if (daoFech.registrar(beanFechas2)) {
                                                                banRegistroVenta = true;
                                                                System.out.println("se registraron las fechas 5");
                                                                //si se  registra  en fechas registramos la deudaTotal

                                                            } else {
                                                                banRegistroVenta = false;
                                                                System.out.println("error  al registrar fechas 5");
                                                            }
                                                        } else {
                                                            //tiene fechas pero no tiene medidas

                                                        }

                                                    } else {
                                                        //no tiene medidas
                                                        banRegistroVenta = false;
                                                        System.out.println("Error al registrar medidas 5");
                                                    }

                                                    System.out.println("si tengo medidas homie  5");

                                                } else {
                                                    //el producto no tiene medidas
                                                    //buscamos si tiene fechas 
                                                    System.out.println("no tiene medidas 5");

                                                    if (buscarFechas(valorId) != null) {
                                                        Fechaspruebas beanFechas2 = buscarFechas(valorId);
                                                        beanFechas2.setProductosapartados(benaProApar2);

                                                        if (daoFech.registrar(beanFechas2)) {
                                                            banRegistroVenta = true;
                                                            //si se  registra  en fechas registramos la deudaTotal
                                                        } else {

                                                            System.out.println("error al registrar fechas sin medidas 5");
                                                        }
                                                    } else {
                                                        //no tiene medidas y tampoco fechas
                                                    }

                                                }

                                                //go a add the measurements and dates
                                                System.out.println("se registraron los productos en apartados 5  " + i);
                                            } else {
                                                banRegistroVenta = false;
                                                System.out.println("fallo al  registrar pro apartados 5");
                                            }

                                        }//for  para recorrer la tabal ventas

                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("eroro al  registrar el pago");
                                    }
                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("error al editar la deuda");
                                }

                                //termina el for de  recorer la lista de deudaTotal
                                //aqui el cliente existe en la bd pero no tiene deuda
                                if (banClienteRegistradoSinDeuda) {
                                    System.out.println("El  cliente esta registrado en la bd pero no tiene deuda 5");
                                    //si es true quiered decir que el cliente si esta registrado en la bd pero no tiene deuda
                                    //registramos la deduatotal del cliente
                                    //registramos los pagos
                                    //registramos los  produtos apartados

                                    System.out.println("vamos a registrar la deuda total 5");

                                    Deudatotal beanDeudaT = new Deudatotal();

                                    beanDeudaT.setClientes(beanCliente);

                                    beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                                    beanDeudaT.setStatus(estadoDeudadTotal);
                                    beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                                    if (daoDeuda.registrar(beanDeudaT)) {
                                        banRegistroVenta = true;
                                        System.out.println("se  registro la deuda 5");
                                        System.out.println("registramos el pago 5");

                                        //obtenemos el ultimo registro de la deuda total
                                        Deudatotal beanDeuda = (Deudatotal) daoDeuda.consultaEspecifica2019(almacenamientoDeListas.ultimoRegistroDeuda + "");
                                        int contador = 0;

                                        System.out.println("soy el id deuda total " + beanDeuda.getIddeudatotal());
                                        //buscamos su deuda y obtenemos el id deuda para guardarlo en la tabla pagos
                                        Pagos beanPagos = new Pagos();

                                        beanPagos.setDeudatotal(beanDeuda);
                                        Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);
                                        beanPagos.setUsuarios(beanUsuario);
                                        beanPagos.setAbono(abonoPago);
                                        beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                        beanPagos.setFecharegistro2(obtenerFechaDate());

                                        if (daoPag.registrar(beanPagos)) {
                                            banRegistroVenta = true;
                                            System.out.println("registro el pagoc 5");
                                            System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas 5");

                                            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                                int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                                int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                                Productos beanProductos = (Productos) daoProductos.consultaEspecifica2019(valorId + "");
                                                Productosapartados beanProApartados = new Productosapartados();
                                                beanProApartados.setClientes(beanCliente);
                                                beanProApartados.setProductos(beanProductos);
                                                beanProApartados.setUsuarios(beanUsuario);
                                                beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                                beanProApartados.setStatus(estadoProductoApartado);
                                                beanProApartados.setCantidadVenta(valorCantidad);
                                                //aqui  vamos a sacar la descripcion de cada producto 
                                                for (int j = 0; j < almacenamientoDeListas.listaDescripcion.size(); j++) {
                                                    if (almacenamientoDeListas.listaDescripcion.get(j).getIdProducto() == valorId) {
                                                        System.out.println("este pro lleva detalle  " + almacenamientoDeListas.listaDescripcion.get(j).getIdProducto());
                                                        beanProApartados.setDetallesproducto(almacenamientoDeListas.listaDescripcion.get(j).getDetallesproducto());
                                                        j = almacenamientoDeListas.listaDescripcion.size();
                                                    }
                                                }

                                                if (daoProAP.registrar(beanProApartados)) {
                                                    banRegistroVenta = true;

                                                    System.out.println("vamos a registrar medidas 5");

                                                    Productosapartados benaProApar2 = (Productosapartados) daoProAP.consultaEspecifica2019(almacenamientoDeListas.idProductoApartadoRegistrado + "");

                                                    System.out.println("ultimo  id de pro apartados " + benaProApar2.getIdproductosapartados());
                                                    if (buscarMedidas(valorId) != null) {
                                                        Medidas beanMedidas2 = buscarMedidas(valorId);
                                                        //conusltamos el ultimo producto aparatado

                                                        beanMedidas2.setProductosapartados(benaProApar2);
                                                        //el bean trae las medidas
                                                        if (daoMedi.registrar(beanMedidas2)) {
                                                            banRegistroVenta = true;
                                                            System.out.println("se registraron las medidas 5");

                                                            // si se registran la medidas buscamos las fechas
                                                            if (buscarFechas(valorId) != null) {
                                                                Fechaspruebas beanFechas2 = buscarFechas(valorId);
                                                                beanFechas2.setProductosapartados(benaProApar2);
                                                                if (daoFech.registrar(beanFechas2)) {
                                                                    banRegistroVenta = true;
                                                                    System.out.println("se registraron las fechas 5");
                                                                    //si se  registra  en fechas registramos la deudaTotal

                                                                } else {
                                                                    banRegistroVenta = false;
                                                                    System.out.println("error  al registrar fechas 5");
                                                                }
                                                            } else {
                                                                //tiene fechas pero no tiene medidas

                                                            }

                                                        } else {
                                                            //no tiene medidas
                                                            banRegistroVenta = false;

                                                            System.out.println("Error al registrar medidas 5");
                                                        }

                                                        System.out.println("si tengo medidas homie  5");

                                                    } else {
                                                        //el producto no tiene medidas
                                                        //buscamos si tiene fechas 
                                                        System.out.println("no tiene medidas 5");

                                                        if (buscarFechas(valorId) != null) {
                                                            Fechaspruebas beanFechas2 = buscarFechas(valorId);
                                                            beanFechas2.setProductosapartados(benaProApar2);

                                                            if (daoFech.registrar(beanFechas2)) {
                                                                banRegistroVenta = true;
                                                                //si se  registra  en fechas registramos la deudaTotal
                                                            } else {
                                                                banRegistroVenta = false;
                                                                System.out.println("error al registrar fechas sin medidas 5");
                                                            }
                                                        } else {
                                                            //no tiene medidas y tampoco fechas
                                                        }

                                                    }

                                                    //go a add the measurements and dates
                                                    System.out.println("se registraron los productos en apartados 5  " + i);

                                                    //  //hasta  aqui  ya estaba
                                                    int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                    beanProductos.setCantidad(nuevaExistencia);
                                                    System.out.println("actualizamos las existencias de los productos ");
                                                    if (daoProductos.editarExistencias2019(beanProductos)) {
                                                        System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                    }
                                                    //banRegistroVenta = ;//true; este tiene que ir  en los ultimos registrso que sehace
                                                    System.out.println("se registraron los productos en apartados " + i);
                                                    //hasta  aqui  ya estaba
                                                } else {
                                                    banRegistroVenta = false;
                                                    System.out.println("error al registrar los pro apartados " + i);
                                                }

                                            }

                                        } else {
                                            banRegistroVenta = false;
                                            System.out.println("eroro al  registrar el pago");
                                        }

                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("error al registrar deuda total");
                                    }

                                } else {
                                    //else del if banClienteRegistradoSinDeuda
                                }
                            } else {
                                System.out.println("No  trajo su deuda");
                            }

                            if (banRegistroVenta) {

                                //frame.dispose();
                                validar.limpiarCampos(txtTotalApagar);
                                validar.limpiarCampos(txtEfectivoRecibido);
                                validar.limpiarCampos(txtCambio);
                                vaciarTabla(tablaVentas, defaultTableVentas);
                                limpiarListas();
                                car.dispose();
                                mensajeExito menExito = new mensajeExito();
                                mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                                menExito.setVisible(true);
                                menExito.setAlwaysOnTop(true);
                                //JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);
                                //new controlProductoPendientes().llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);

                            } else {
                                System.out.println("erro al registrar venta 2222000000");
                            }

                        } else {//else de que el cliente no esta registradon en la bd

                            System.out.println("cliente no registrado en bd y lo registramos 5");
                            //registramos el cliente
                            Clientes beanLista = almacenamientoDeListas.listaClientes.get(0);
                            Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);

                            beanLista.setUsuarios(beanUsuario);
                            if (daoCliente.registrar(beanLista)) {
                                banRegistroVenta = true;

                                System.out.println("se registro el cliente y por ley  no tiene deuda, ni medidas y tampoco fechas");
                                System.out.println("vamos a registrar la deuda total 5");

                                Deudatotal beanDeudaT = new Deudatotal();
                                beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean2019(beanLista.getNombrecompleto());
                                beanDeudaT.setClientes(beanCliente);
                                beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                                beanDeudaT.setStatus(estadoDeudadTotal);

                                beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                                if (daoDeuda.registrar(beanDeudaT)) {
                                    banRegistroVenta = true;
                                    System.out.println("se  registro la deuda 56");
                                    System.out.println("registramos el pago 56");
                                    Deudatotal deudatotal1 = daoDeuda.consultaEspecifica2019(almacenamientoDeListas.ultimoRegistroDeuda + "");

                                    //como el cliente noexiste en la bd entonces no puede tener mas que un soloregistro en la tabla  deuda 
                                    //el cual sera su primera deuda
                                    Pagos beanPagos = new Pagos();
                                    beanPagos.setDeudatotal(deudatotal1);
                                    //  Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);
                                    beanPagos.setUsuarios(beanUsuario);

                                    System.out.println("abonoPagoGlobal 5 " + abonoPago);
                                    beanPagos.setAbono(abonoPago);
                                    beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                    beanPagos.setFecharegistro2(obtenerFechaDate());

                                    if (daoPag.registrar(beanPagos)) {
                                        banRegistroVenta = true;
                                        System.out.println("registro el pago 5");
                                        System.out.println("ahora si registraremos los productos apartados con fechas y con medidas 5");

                                        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                            int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                            Productos beanProductos = daoProductos.consultaEspecifica2019(valorId + "");
                                            Productosapartados beanProApartados = new Productosapartados();
                                            beanProApartados.setClientes(beanCliente);
                                            beanProApartados.setProductos(beanProductos);
                                            beanProApartados.setUsuarios(beanUsuario);
                                            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                            beanProApartados.setStatus(estadoProductoApartado);
                                            beanProApartados.setCantidadVenta(valorCantidad);
                                            //aqui  vamos a sacar la descripcion de cada producto 
                                            for (int j = 0; j < almacenamientoDeListas.listaDescripcion.size(); j++) {
                                                if (almacenamientoDeListas.listaDescripcion.get(j).getIdProducto() == valorId) {
                                                    System.out.println("este pro lleva detalle  " + almacenamientoDeListas.listaDescripcion.get(j).getIdProducto());
                                                    beanProApartados.setDetallesproducto(almacenamientoDeListas.listaDescripcion.get(j).getDetallesproducto());
                                                    j = almacenamientoDeListas.listaDescripcion.size();
                                                }
                                            }
                                            if (daoProAP.registrar(beanProApartados)) {
                                                banRegistroVenta = true;

                                                //ya estaba
                                                int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                beanProductos.setCantidad(nuevaExistencia);
                                                System.out.println("actualizamos las existencias de los productos ");
                                                if (daoProductos.editarExistencias2019(beanProductos)) {
                                                    System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                }

                                                System.out.println("se registraron los productos en apartados " + i);
                                                //hasta aqui

                                                Productosapartados benaProApar2 = daoProAP.consultaEspecifica2019(almacenamientoDeListas.idProductoApartadoRegistrado + "");
                                                System.out.println("ultimo  id de pro apartados " + benaProApar2.getIdproductosapartados());
                                                //aqui mero
                                                if (buscarMedidas(valorId) != null) {
                                                    System.out.println("si traigo medidas");
                                                    Medidas beanMedidas2 = buscarMedidas(valorId);
                                                    //conusltamos el ultimo producto aparatado

                                                    beanMedidas2.setProductosapartados(benaProApar2);
                                                    //el bean trae las medidas
                                                    if (daoMedi.registrar(beanMedidas2)) {
                                                        banRegistroVenta = true;
                                                        System.out.println("se registraron las medidas 5");

                                                        // si se registran la medidas buscamos las fechas
                                                        if (buscarFechas(valorId) != null) {

                                                            Fechaspruebas beanFechas2 = buscarFechas(valorId);
                                                            System.out.println("si trigo  fechas homie " + beanFechas2.getFechaevento());
                                                            beanFechas2.setProductosapartados(benaProApar2);
                                                            if (daoFech.registrar(beanFechas2)) {
                                                                banRegistroVenta = true;
                                                                System.out.println("se registraron las fechas 5");
                                                                //si se  registra  en fechas registramos la deudaTotal

                                                            } else {
                                                                banRegistroVenta = false;

                                                                System.out.println("error  al registrar fechas 5");
                                                            }
                                                        } else {
                                                            System.out.println("trae medidas pero no fechas");
                                                            //tiene fechas pero no tiene medidas

                                                        }

                                                    } else {
                                                        //no tiene medidas
                                                        banRegistroVenta = false;
                                                        System.out.println("Error al registrar medidas 5");
                                                    }

                                                    System.out.println("si tengo medidas homie  5");

                                                } else {
                                                    //el producto no tiene medidas
                                                    //buscamos si tiene fechas 
                                                    System.out.println("no tiene medidas 5");

                                                    if (buscarFechas(valorId) != null) {
                                                        Fechaspruebas beanFechas2 = buscarFechas(valorId);
                                                        beanFechas2.setProductosapartados(benaProApar2);

                                                        if (daoFech.registrar(beanFechas2)) {
                                                            banRegistroVenta = true;
                                                            //si se  registra  en fechas registramos la deudaTotal
                                                        } else {
                                                            banRegistroVenta = false;
                                                            System.out.println("error al registrar fechas sin medidas 5");
                                                        }
                                                    } else {
                                                        //no tiene medidas y tampoco fechas
                                                    }

                                                }

                                                //go a add the measurements and dates
                                                System.out.println("se registraron los productos en apartados 5  " + i);

                                            } else {
                                                banRegistroVenta = false;
                                                System.out.println("error al registrar los pro apartados " + i);
                                            }

                                        }

                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("eroro al  registrar el pago");
                                    }

                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("error al registrar deuda total");
                                }

                            } else {
                                banRegistroVenta = false;
                                System.out.println("Error al registrar el cliente");
                            }

                            if (banRegistroVenta) {

                                //frame.dispose();
                                validar.limpiarCampos(txtTotalApagar);

                                validar.limpiarCampos(txtEfectivoRecibido);

                                validar.limpiarCampos(txtCambio);

                                vaciarTabla(tablaVentas, defaultTableVentas);

                                limpiarListas();
                                car.dispose();
                                mensajeExito menExito = new mensajeExito();
                                mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                                menExito.setVisible(true);
                                menExito.setAlwaysOnTop(true);
                                // JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);
                                // new controlProductoPendientes().llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);

                            } else {
                                System.out.println("erro al actulizar todo 10000");
                            }

                        }

                        //up to this point  (hata aqui)
                    } else {

                    }
                }

            };
            hilo.start();

        } else {

        }
    }

    public Date obtenerFechaDate(JDateChooser fecha) {
        Date d = fecha.getDate();
        return d;
    }

    public Date obtenerFechaDate() {
        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 

        return objDate;
    }

    public Clientes buscarClienteSiTieneDeuda(int precioProducto) {
        Clientes bean = null;
        // System.out.println("llamado por otra  ventana");
        if (almacenamientoDeListas.listaClientes.size() != 0) {

            bean = (Clientes) daoCliente.consultaEspecificaPorNombreBean(almacenamientoDeListas.listaClientes.get(0).getNombrecompleto());

            if (bean != null) {

                Set<Deudatotal> listaDeuda = bean.getDeudatotals();
                if (listaDeuda.size() != 0) {
                    for (Deudatotal deudatotal : listaDeuda) {
                        if (deudatotal.getStatus().equalsIgnoreCase("No pagado")) {
                            int nuevaDeuda = deudatotal.getDeudatotal() + precioProducto;
                            bean.setNuevaDeuda(nuevaDeuda);
                            //enviamos el bean a pantalla clientecondeuda
                            clienteConDeuda.bean = bean;
                            clienteConDeuda ccd = new clienteConDeuda();
                            ccd.setVisible(true);
                        }
                    }
                } else {

                }
            } else {
                System.out.println("el cliente no esta registrado en la bd");
            }

        } else {
        }
        return bean;
    }

    public void limpiarListas() {
        // System.out.println("tamañao lista  al limpiar " + almacenamientoDeListas.listaClientes.size());
        almacenamientoDeListas.listaClientes.clear();
        //  System.out.println("lista cliente limpiada " + almacenamientoDeListas.listaClientes.size());
        almacenamientoDeListas.listaFechas.clear();
        almacenamientoDeListas.listaMedidas.clear();
        almacenamientoDeListas.listaDescripcion.clear();
        almacenamientoDeListas.listaProductos.clear();
    }

    public Medidas buscarMedidas(int idProducto) {

        Medidas bean = new Medidas();
        if (almacenamientoDeListas.listaMedidas.size() == 0) {
            bean = null;
        } else {
            for (int i = 0; i < almacenamientoDeListas.listaMedidas.size(); i++) {
                System.out.println("al buscar medidas 11" + almacenamientoDeListas.listaMedidas.get(i).getIdProducto());
                System.out.println("idPro albuscar medidas " + idProducto);
                if (almacenamientoDeListas.listaMedidas.get(i).getIdProducto() == idProducto) {
                    bean = almacenamientoDeListas.listaMedidas.get(i);
                    i = almacenamientoDeListas.listaMedidas.size();
                    System.out.println("esto me envia mi bean medidad en buscarMedidas " + bean.getTalle());
                } else {
                    bean = null;
                    System.out.println("esto me envia mi bean medidad en buscarMedidas 222" + bean);
                }

            }

        }

        return bean;
    }

    public Fechaspruebas buscarFechas(int idProducto) {
        Fechaspruebas bean = new Fechaspruebas();
        if (almacenamientoDeListas.listaFechas.size() == 0) {
            bean = null;
        } else {

            for (int i = 0; i < almacenamientoDeListas.listaFechas.size(); i++) {
                if (almacenamientoDeListas.listaFechas.get(i).getIdProducto() == idProducto) {
                    bean = almacenamientoDeListas.listaFechas.get(i);
                    i = almacenamientoDeListas.listaFechas.size();
                } else {
                    bean = null;
                }
            }
        }
        System.out.println("esto me envia mi bean fechas en buscarFechas " + bean);
        return bean;
    }

    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

    public boolean validarCamposMedidasYfechas(JTextField talle, JTextField sise, JTextField hombros, JTextField busto, JTextField cintura, JTextField cadera, JTextField largoFalda,
            JTextField anchoPuño, Productos beanProductos, JDateChooser fechaPrueba, JDateChooser fechaEvento) {
        boolean ban = false;
        if (validar.validarCampos(talle)) {
            if (validar.validarCampos(sise)) {
                if (validar.validarCampos(hombros)) {
                    if (validar.validarCampos(busto)) {
                        if (validar.validarCampos(cintura)) {
                            if (validar.validarCampos(cadera)) {
                                if (validar.validarCampos(largoFalda)) {
                                    if (validar.validarCampos(anchoPuño)) {
                                        if (fechaPrueba.getDate() != null) {
                                            if (fechaEvento.getDate() != null) {

                                                /* if (Integer.parseInt(talle.getText()) >= 0) {
                                                    if (Integer.parseInt(sise.getText()) >= 0) {
                                                        if (Integer.parseInt(hombros.getText()) >= 0) {
                                                            if (Integer.parseInt(busto.getText()) >= 0) {
                                                                if (Integer.parseInt(cintura.getText()) >= 0) {
                                                                    if (Integer.parseInt(cadera.getText()) >= 0) {
                                                                        if (Integer.parseInt(largoFalda.getText()) >= 0) {
                                                                            if (Integer.parseInt(anchoPuño.getText()) >= 0) {*/
                                                //SI YA  TODOS LOS CAMPOS REQUERIDOS FUERON COMPLETADOS ENTONCES GUARDAREMOS TODA LA INFORMACION
                                                //MEDIDAS,FECHAS Y PRODUCTO , Y LO MOSTRAREMOS EN LA TABLA VENTA
                                                //OBTENEMOS LOS DATOS DE LAS MEDIDAS
                                                Medidas beanM = new Medidas();
                                                beanM.setIdProducto(beanProductos.getIdproductos());
                                                // System.out.println("en medidas bean  " + beanProductos.getIdproductos());
                                                beanM.setTalle(Float.parseFloat(talle.getText()));
                                                beanM.setSise(Float.parseFloat(sise.getText()));
                                                //System.out.println("bean m sise puto " + beanM.getSise());
                                                beanM.setHombros(Float.parseFloat(hombros.getText()));
                                                beanM.setBusto(Float.parseFloat(busto.getText()));
                                                beanM.setLargoFalda(Float.parseFloat(largoFalda.getText()));
                                                beanM.setAnchoPuno(Float.parseFloat(anchoPuño.getText()));
                                                beanM.setCintura(Float.parseFloat(cintura.getText()));
                                                beanM.setCadera(Float.parseFloat(cadera.getText()));
                                                beanM.setFecharegistro(validar.obtenerFechaActual());

                                                //falta productos idProductosApartados por que ese se lo enviaremos cuando 
                                                //se presion el boton registrar venta
                                                //ahora mandamos a llenar nuestra listaMedidas
                                                llenarListaMedidas(beanM, beanProductos.getIdproductos());
                                                //ahora llenamos el bean de fechas y mandamos a traer el metodo llenar listaFechas
                                                int dia = fechaPrueba.getCalendar().get(Calendar.DAY_OF_MONTH);
                                                int mes = fechaPrueba.getCalendar().get(Calendar.MARCH) + 1;
                                                int año = fechaPrueba.getCalendar().get(Calendar.YEAR);
                                                String mes1 = mes + "";
                                                String dia1 = dia + "";
                                                if (mes <= 9) {
                                                    mes1 = "0" + String.valueOf(mes);
                                                    System.out.println("entro a cambiar fecha ");
                                                }

                                                if (dia <= 9) {
                                                    dia1 = "0" + String.valueOf(dia);
                                                    //System.out.println("entro a cambiar fecha ");
                                                }

                                                String fechaPrue = dia1 + "-" + mes1 + "-" + año;
                                                //  System.out.println("fecha preuba " + fechaPrue);
                                                int diaE = fechaEvento.getCalendar().get(Calendar.DAY_OF_MONTH);
                                                int mesE = fechaEvento.getCalendar().get(Calendar.MARCH) + 1;
                                                int añoE = fechaEvento.getCalendar().get(Calendar.YEAR);
                                                String mes2 = mesE + "";
                                                String dia2 = diaE + "";

                                                if (mesE <= 9) {
                                                    mes2 = "0" + String.valueOf(mes);
                                                    //  System.out.println("entro a cambiar fecha ");
                                                }

                                                if (diaE <= 9) {
                                                    dia2 = "0" + String.valueOf(diaE);
                                                    //System.out.println("entro a cambiar fecha ");
                                                }

                                                String fechaEven = dia2 + "-" + mes2 + "-" + añoE;
                                                // System.out.println("fechas  " + fechaEven);

                                                Fechaspruebas beanFechas = new Fechaspruebas();
                                                beanFechas.setIdProducto(beanProductos.getIdproductos());
                                                beanFechas.setFechaprueba(fechaPrue);
                                                beanFechas.setFechaevento(fechaEven);
                                                beanFechas.setFechaevento2(obtenerFechaDate(fechaEvento));
                                                beanFechas.setFechaprueba2(obtenerFechaDate(fechaPrueba));
                                                //********* zqui ya hermano -----
                                                //aqui pa la fecha 
                                                beanFechas.setFecharegistro(validar.obtenerFechaActual());
                                                //System.out.println("fecha evento " + beanFechas.getFechaevento());
                                                // System.out.println("fechaPrueba " + beanFechas.getFechaprueba());
                                                llenarListaFechas(beanFechas, beanProductos.getIdproductos());
                                                ban = true;

                                                /*   } else {
                                                                                anchoPuño.requestFocus();
                                                                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                                mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                                menAdvertencia.setVisible(true);
                                                                                menAdvertencia.setAlwaysOnTop(true);

                                                                                // JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                                            }
                                                                        } else {
                                                                            largoFalda.requestFocus();
                                                                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                            mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                            menAdvertencia.setVisible(true);
                                                                            menAdvertencia.setAlwaysOnTop(true);
                                                                            // JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                                        }

                                                                    } else {
                                                                        cadera.requestFocus();
                                                                        mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                        mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                        menAdvertencia.setVisible(true);
                                                                        menAdvertencia.setAlwaysOnTop(true);
                                                                        //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                                    }

                                                                } else {
                                                                    cintura.requestFocus();
                                                                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                    mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                    menAdvertencia.setVisible(true);
                                                                    menAdvertencia.setAlwaysOnTop(true);
                                                                    //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                                }

                                                            } else {
                                                                busto.requestFocus();
                                                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                menAdvertencia.setVisible(true);
                                                                menAdvertencia.setAlwaysOnTop(true);
                                                                //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                            }

                                                        } else {
                                                            hombros.requestFocus();
                                                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                            mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                            menAdvertencia.setVisible(true);
                                                            menAdvertencia.setAlwaysOnTop(true);
                                                            //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                        }

                                                    } else {
                                                        sise.requestFocus();
                                                        mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                        mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                        menAdvertencia.setVisible(true);
                                                        menAdvertencia.setAlwaysOnTop(true);
                                                        //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                    }
                                                } else {
                                                    talle.requestFocus();
                                                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                    mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                    menAdvertencia.setVisible(true);
                                                    menAdvertencia.setAlwaysOnTop(true);
                                                    // JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                }*/
                                            } else {
                                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                mensajeAdvertencia.labelMensaje.setText("selecciona una fecha evento");
                                                menAdvertencia.setVisible(true);
                                                menAdvertencia.setAlwaysOnTop(true);
                                                //JOptionPane.showMessageDialog(null, "Selecciona la fecha del evento", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                fechaEvento.requestFocus();

                                            }
                                        } else {
                                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                            mensajeAdvertencia.labelMensaje.setText("selecciona una fecha prueba");
                                            menAdvertencia.setVisible(true);
                                            menAdvertencia.setAlwaysOnTop(true);
                                            //JOptionPane.showMessageDialog(null, "Selecciona la fecha de la prueba", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                            fechaPrueba.requestFocus();

                                        }

                                    }

                                }

                            }

                        }

                    }

                }
            }
        }
        return ban;
    }

    public boolean validarCamposMedidas(JTextField talle, JTextField sise, JTextField hombros,
            JTextField busto, JTextField cintura, JTextField cadera, JTextField largoFalda,
            JTextField anchoPuño, Productos beanProductos) {
        boolean ban = false;
        if (validar.validarCampos(talle)) {
            if (validar.validarCampos(sise)) {
                if (validar.validarCampos(hombros)) {
                    if (validar.validarCampos(busto)) {
                        if (validar.validarCampos(cintura)) {
                            if (validar.validarCampos(cadera)) {
                                if (validar.validarCampos(largoFalda)) {
                                    if (validar.validarCampos(anchoPuño)) {

                                        if (Integer.parseInt(talle.getText().toString()) >= 0) {
                                            if (Integer.parseInt(sise.getText()) >= 0) {
                                                if (Integer.parseInt(hombros.getText()) >= 0) {
                                                    if (Integer.parseInt(busto.getText()) >= 0) {
                                                        if (Integer.parseInt(cintura.getText()) >= 0) {
                                                            if (Integer.parseInt(cadera.getText()) >= 0) {
                                                                if (Integer.parseInt(largoFalda.getText()) >= 0) {
                                                                    if (Integer.parseInt(anchoPuño.getText()) >= 0) {
                                                                        //SI YA  TODOS LOS CAMPOS REQUERIDOS FUERON COMPLETADOS ENTONCES GUARDAREMOS TODA LA INFORMACION
                                                                        //MEDIDAS,FECHAS Y PRODUCTO , Y LO MOSTRAREMOS EN LA TABLA VENTA
                                                                        //OBTENEMOS LOS DATOS DE LAS MEDIDAS
                                                                        Medidas beanM = new Medidas();
                                                                        beanM.setIdProducto(beanProductos.getIdproductos());

                                                                        beanM.setTalle(Integer.parseInt(talle.getText()));
                                                                        beanM.setSise(Float.parseFloat(sise.getText()));
                                                                        System.out.println("bean m sise puto " + beanM.getSise());
                                                                        beanM.setHombros(Float.parseFloat(hombros.getText()));
                                                                        beanM.setBusto(Float.parseFloat(busto.getText()));
                                                                        beanM.setLargoFalda(Float.parseFloat(largoFalda.getText()));
                                                                        beanM.setAnchoPuno(Float.parseFloat(anchoPuño.getText()));
                                                                        beanM.setCintura(Float.parseFloat(cintura.getText()));
                                                                        beanM.setCadera(Float.parseFloat(cadera.getText()));
                                                                        beanM.setFecharegistro(validar.obtenerFechaActual());
                                                                        //falta productos idProductosApartados por que ese se lo enviaremos cuando 
                                                                        //se presion el boton registrar venta
                                                                        //ahora mandamos a llenar nuestra listaMedidas

                                                                        llenarListaMedidas(beanM, beanProductos.getIdproductos());
                                                                        ban = true;

                                                                    } else {
                                                                        anchoPuño.requestFocus();
                                                                        mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                        mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                        menAdvertencia.setVisible(true);
                                                                        menAdvertencia.setAlwaysOnTop(true);
                                                                        //  JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                                    }
                                                                } else {
                                                                    largoFalda.requestFocus();
                                                                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                    mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                    menAdvertencia.setVisible(true);
                                                                    menAdvertencia.setAlwaysOnTop(true);
                                                                    //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                                }

                                                            } else {
                                                                cadera.requestFocus();
                                                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                                mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                                menAdvertencia.setVisible(true);
                                                                menAdvertencia.setAlwaysOnTop(true);
                                                                //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                            }

                                                        } else {
                                                            cintura.requestFocus();
                                                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                            mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                            menAdvertencia.setVisible(true);
                                                            menAdvertencia.setAlwaysOnTop(true);
                                                            // JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                        }

                                                    } else {
                                                        busto.requestFocus();
                                                        mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                        mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                        menAdvertencia.setVisible(true);
                                                        menAdvertencia.setAlwaysOnTop(true);
                                                        // JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                    }

                                                } else {
                                                    hombros.requestFocus();
                                                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                    mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                    menAdvertencia.setVisible(true);
                                                    menAdvertencia.setAlwaysOnTop(true);
                                                    //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                                }

                                            } else {
                                                sise.requestFocus();
                                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                                menAdvertencia.setVisible(true);
                                                menAdvertencia.setAlwaysOnTop(true);
                                                //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                            }
                                        } else {
                                            talle.requestFocus();
                                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                            mensajeAdvertencia.labelMensaje.setText("Ingresa un valor mayor a 0");
                                            menAdvertencia.setVisible(true);
                                            menAdvertencia.setAlwaysOnTop(true);
                                            //JOptionPane.showMessageDialog(null, "Ingresa un valor mayor a 0 ", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                        }

                                    }

                                }

                            }

                        }

                    }

                }
            }
        }
        return ban;
    }

    public boolean validarCamposFechas(JDateChooser fechaPrueba, JDateChooser fechaEvento, Productos beanProductos) {
        boolean ban = false;

        if (fechaPrueba.getDate() != null) {
            if (fechaEvento.getDate() != null) {

                //ahora llenamos el bean de fechas y mandamos a traer el metodo llenar listaFechas
                //ahora llenamos el bean de fechas y mandamos a traer el metodo llenar listaFechas
                int dia = fechaPrueba.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes = fechaPrueba.getCalendar().get(Calendar.MARCH) + 1;
                int año = fechaPrueba.getCalendar().get(Calendar.YEAR);
                String mes1 = mes + "";
                String dia1 = dia + "";
                if (mes <= 9) {
                    mes1 = "0" + String.valueOf(mes);

                }

                if (dia <= 9) {
                    dia1 = "0" + String.valueOf(dia);

                }

                String fechaPrue = dia1 + "-" + mes1 + "-" + año;

                int diaE = fechaEvento.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mesE = fechaEvento.getCalendar().get(Calendar.MARCH) + 1;
                int añoE = fechaEvento.getCalendar().get(Calendar.YEAR);
                String mes2 = mesE + "";
                String dia2 = diaE + "";

                if (mesE <= 9) {
                    mes2 = "0" + String.valueOf(mes);

                }

                if (diaE <= 9) {
                    dia2 = "0" + String.valueOf(diaE);

                }

                String fechaEven = dia2 + "-" + mes2 + "-" + añoE;

                Fechaspruebas beanFechas = new Fechaspruebas();
                beanFechas.setIdProducto(beanProductos.getIdproductos());
                beanFechas.setFechaprueba(fechaPrue);
                beanFechas.setFechaevento(fechaEven);
                //aqui pa la fecha
                beanFechas.setFecharegistro(validar.obtenerFechaActual());
                beanFechas.setFechaevento2(obtenerFechaDate(fechaEvento));
                beanFechas.setFechaprueba2(obtenerFechaDate(fechaPrueba));

                // System.out.println("fecha evento " + beanFechas.getFechaevento());
                // System.out.println("fechaPrueba " + beanFechas.getFechaprueba());
                llenarListaFechas(beanFechas, beanProductos.getIdproductos());
                ban = true;
            } else {
                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Selecciona la fecha del evento");
                menAdvertencia.setVisible(true);
                menAdvertencia.setAlwaysOnTop(true);

                //JOptionPane.showMessageDialog(null, "Selecciona la fecha del evento", "Advertencia", JOptionPane.WARNING_MESSAGE);
                fechaEvento.requestFocus();

            }
        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona la fecha del prueba");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Selecciona la fecha de la prueba", "Advertencia", JOptionPane.WARNING_MESSAGE);
            fechaPrueba.requestFocus();

        }
        return ban;
    }

    public Clientes registrarCliente(JTextField txtNombre, JTextField txtTelefono, JFrame frame,
            String idUsuario, JTextField txtTotalApagar, JTextField txtEfectivoRecibido, JTextField txtCambio, JTable tablaVentas, DefaultTableModel defaultTablaVentas,
            JTable tablaProductos, DefaultTableModel defaultTablaProductos) {
        // checar este  pedaso de codigo
        if (validar.validarCampos(txtNombre)) {

            Thread hilo = new Thread() {
                @Override
                public void run() {
                    String telefono = "Sin telefono";
                    boolean banRegistroVenta = false;
                    boolean banClienteRegistradoSinDeuda = false;
                    if (txtTelefono.getText().isEmpty()) {

                    } else {
                        telefono = txtTelefono.getText();

                    }
                    cargando car = new cargando();

                    car.setVisible(true);
                    car.setAlwaysOnTop(true);
                    Clientes beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean2019(txtNombre.getText());
                    if (beanCliente != null) {
                        System.out.println("cliente registrado en la base de datos");
                        Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                        if (listaDeuda.size() != 0) {
                            System.out.println("esta registrado en la tabla deudatotal");
                            for (Deudatotal deudatotal : listaDeuda) {
                                if (deudatotal.getStatus().equals("No pagado")) {
                                    System.out.println("el cliente tiene deuda");
                                    System.out.println("no registramos el cliente pero modificamos la deuda");
                                    int nuevaDeuda = deudatotal.getDeudatotal() + Integer.parseInt(txtTotalApagar.getText());
                                    int abonoActual = almacenamientoDeListas.abonoPagoGlobal;
                                    //mandamos a la otra pantalla para mostrar informacion de la deuda
                                    beanCliente.setNuevaDeuda(nuevaDeuda);
                                    beanCliente.setAbonoActual(abonoActual);
                                    //enviamos el bean a pantalla clientecondeuda
                                    clienteConDeuda.bean = beanCliente;
                                    clienteConDeuda ccd = new clienteConDeuda();
                                    ccd.setVisible(true);
                                    deudatotal.setDeudatotal(nuevaDeuda);
                                    if (daoDeuda.editar(deudatotal)) {
                                        banRegistroVenta = true;
                                        System.out.println("se modfio la deuda del cliente");
                                        System.out.println("vamos a registrar pagos");
                                        Pagos beanPagos = new Pagos();
                                        beanPagos.setDeudatotal(deudatotal);
                                        Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica(idUsuario);
                                        beanPagos.setUsuarios(beanUsuario);

                                        beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                        beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                        beanPagos.setFecharegistro2(obtenerFechaDate());

                                        if (daoPag.registrar(beanPagos)) {
                                            banRegistroVenta = true;
                                            System.out.println("registro el pago");
                                            System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                                int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                                int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                                Productos beanProductos = (Productos) daoProductos.consultaEspecifica(valorId + "");

                                                Productosapartados beanProApartados = new Productosapartados();
                                                beanProApartados.setClientes(beanCliente);
                                                beanProApartados.setProductos(beanProductos);
                                                beanProApartados.setUsuarios(beanUsuario);
                                                beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                                beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                                beanProApartados.setCantidadVenta(valorCantidad);
                                                beanProApartados.setDetallesproducto("Sin detalles");
                                                if (daoProAP.registrar(beanProApartados)) {
                                                    banRegistroVenta = true;
                                                    int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                    beanProductos.setCantidad(nuevaExistencia);
                                                    System.out.println("actualizamos las existencias de los productos ");
                                                    if (daoProductos.editar(beanProductos)) {
                                                        System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                    }

                                                    System.out.println("se registraron los productos en apartados " + i);
                                                } else {
                                                    banRegistroVenta = false;
                                                }

                                            }

                                        } else {
                                            banRegistroVenta = false;
                                            System.out.println("eroro al  registrar el pago");
                                        }
                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("error al editar la deuda");
                                    }
                                } else {
                                    //aqui que pasa si el cliente esta  registrado en la bd pero  no tiene deuda
                                    //segun yo deberiamos de registrar todo pero sin registrarlo a el
                                    System.out.println("el  cliente esta registrado  en la bd pero  no tiene deudad " + deudatotal);
                                    banClienteRegistradoSinDeuda = true;
                                }

                            }
                            //aqui el cliente existe en la bd pero no tiene deuda
                            if (banClienteRegistradoSinDeuda) {
                                System.out.println("El  cliente esta registrado en la bd pero no tiene deuda");
                                //si es true quiered decir que el cliente si esta registrado en la bd pero no tiene deuda
                                //registramos la deduatotal del cliente
                                //registramos los pagos
                                //registramos los  produtos apartados

                                System.out.println("vamos a registrar la deuda total");

                                Deudatotal beanDeudaT = new Deudatotal();

                                beanDeudaT.setClientes(beanCliente);
                                beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                                beanDeudaT.setStatus(almacenamientoDeListas.estadoDeudaGlobal);
                                beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                                if (daoDeuda.registrar(beanDeudaT)) {
                                    banRegistroVenta = true;
                                    System.out.println("se  registro la deuda");
                                    System.out.println("registramos el pago");
//consultamos la deuda   total por medio del ultimo registro
                                    Deudatotal deudatotal1 = (Deudatotal) daoDeuda.consultaEspecifica(almacenamientoDeListas.ultimoRegistroDeuda + "");

                                    System.out.println("soy el id deuda total " + deudatotal1);
                                    //buscamos su deuda y obtenemos el id deuda para guardarlo en la tabla pagos
                                    Pagos beanPagos = new Pagos();

                                    beanPagos.setDeudatotal(deudatotal1);
                                    Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica(idUsuario);
                                    beanPagos.setUsuarios(beanUsuario);
                                    beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                    beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                    beanPagos.setFecharegistro2(obtenerFechaDate());

                                    if (daoPag.registrar(beanPagos)) {
                                        banRegistroVenta = true;
                                        System.out.println("registro el pago");
                                        System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                            int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                            Productos beanProductos = (Productos) daoProductos.consultaEspecifica(valorId + "");
                                            Productosapartados beanProApartados = new Productosapartados();
                                            beanProApartados.setClientes(beanCliente);
                                            beanProApartados.setProductos(beanProductos);
                                            beanProApartados.setUsuarios(beanUsuario);
                                            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                            beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                            beanProApartados.setCantidadVenta(valorCantidad);
                                            beanProApartados.setDetallesproducto("Sin detalles");
                                            if (daoProAP.registrar(beanProApartados)) {
                                                banRegistroVenta = true;
                                                int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                beanProductos.setCantidad(nuevaExistencia);
                                                System.out.println("actualizamos las existencias de los productos ");
                                                if (daoProductos.editar(beanProductos)) {
                                                    System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                }

                                                System.out.println("se registraron los productos en apartados " + i);
                                            } else {
                                                banRegistroVenta = false;
                                                System.out.println("error al registrar los pro apartados " + i);
                                            }

                                        }

                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("eroro al  registrar el pago");
                                    }

                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("error al registrar deuda total");
                                }

                            } else {
                                //else del if banClienteRegistradoSinDeuda
                            }
                        } else {
                            System.out.println("no esta registrado en la tabla deudatotal");
                        }

                        if (banRegistroVenta) {

                            frame.dispose();
                            validar.limpiarCampos(txtTotalApagar);
                            validar.limpiarCampos(txtEfectivoRecibido);
                            validar.limpiarCampos(txtCambio);
                            vaciarTabla(tablaVentas, defaultTablaVentas);
                            car.dispose();
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);

                        } else {

                        }

                    } else {//else de que el cliente no esta registradon en la bd
                        Clientes beanCliente2 = new Clientes();
                        System.out.println("cliente no registrado en bd y lo registramos");
                        //registramos el cliente
                        System.out.println("telefon " + txtNombre.getText());
                        beanCliente2.setNombrecompleto(txtNombre.getText().toString());
                        System.out.println("telefon " + telefono);
                        beanCliente2.setTelefono(telefono);
                        beanCliente2.setFecharegistro(validar.obtenerFechaActual());
                        Usuarios beanUsuario = daoUsuario.consultaEspecifica2019(idUsuario);
                        beanCliente2.setUsuarios(beanUsuario);
                        //************** YA PASE AQUII *******************
                        if (daoCliente.registrar(beanCliente2)) {
                            banRegistroVenta = true;
                            System.out.println("se registro el cliente y por ley  no tiene deuda, ni medidas y tampoco fechas");
                            System.out.println("vamos a registrar la deuda total");

                            Deudatotal beanDeudaT = new Deudatotal();
                            beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean(txtNombre.getText());
                            beanDeudaT.setClientes(beanCliente);
                            beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                            beanDeudaT.setStatus(almacenamientoDeListas.estadoDeudaGlobal);
                            beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                            if (daoDeuda.registrar(beanDeudaT)) {
                                banRegistroVenta = true;
                                System.out.println("se  registro la deuda");
                                System.out.println("registramos el pago");
                                Deudatotal deudatotal1 = (Deudatotal) daoDeuda.consultaEspecifica(almacenamientoDeListas.ultimoRegistroDeuda + "");

                                Pagos beanPagos = new Pagos();
                                beanPagos.setDeudatotal(deudatotal1);

                                beanPagos.setUsuarios(beanUsuario);
                                System.out.println("abonoPagoGlobal " + almacenamientoDeListas.abonoPagoGlobal);
                                beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                beanPagos.setFecharegistro2(obtenerFechaDate());

                                if (daoPag.registrar(beanPagos)) {
                                    banRegistroVenta = true;
                                    System.out.println("registro el pago");
                                    System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                    for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                        int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                        int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                        Productos beanProductos = (Productos) daoProductos.consultaEspecifica(valorId + "");
                                        Productosapartados beanProApartados = new Productosapartados();
                                        beanProApartados.setClientes(beanCliente);
                                        beanProApartados.setProductos(beanProductos);
                                        beanProApartados.setUsuarios(beanUsuario);
                                        beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                        beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                        beanProApartados.setCantidadVenta(valorCantidad);
                                        beanProApartados.setDetallesproducto("Sin detalles");
                                        if (daoProAP.registrar(beanProApartados)) {
                                            banRegistroVenta = true;
                                            int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                            beanProductos.setCantidad(nuevaExistencia);
                                            System.out.println("actualizamos las existencias de los productos ");
                                            if (daoProductos.editar(beanProductos)) {
                                                System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                            }

                                            System.out.println("se registraron los productos en apartados " + i);
                                        } else {
                                            banRegistroVenta = false;
                                            System.out.println("error al registrar los pro apartados " + i);
                                        }

                                    }

                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("eroro al  registrar el pago");
                                }

                            } else {
                                banRegistroVenta = false;
                                System.out.println("error al registrar deuda total");
                            }

                        } else {
                            banRegistroVenta = false;
                            System.out.println("Error al registrar el cliente");
                        }

                        if (banRegistroVenta) {

                            frame.dispose();

                            validar.limpiarCampos(txtTotalApagar);

                            validar.limpiarCampos(txtEfectivoRecibido);

                            validar.limpiarCampos(txtCambio);

                            vaciarTabla(tablaVentas, defaultTablaVentas);

                            car.dispose();
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            // JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                            new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);

                        } else {
                            car.dispose();
                            mensajeError menError = new mensajeError();
                            mensajeError.labelMensaje.setText("Error al registrar venta correctamente");
                            menError.setVisible(true);
                            menError.setAlwaysOnTop(true);
                        }

                    }

                }
            };
            hilo.start();

        } else {

        }

        return beanCliente;

    }

    public Clientes registrarCliente2(JTextField txtNombre, JTextField txtTelefono, JFrame frame,
            String idUsuario, JTextField txtTotalApagar, JTextField txtEfectivoRecibido, JTextField txtCambio, JTable tablaVentas, DefaultTableModel defaultTablaVentas,
            JTable tablaProductos, DefaultTableModel defaultTablaProductos) {

        if (validar.validarCampos(txtNombre)) {

            Thread hilo = new Thread() {
                @Override
                public void run() {
                    String telefono = "Sin telefono";
                    if (txtTelefono.getText().isEmpty()) {

                    } else {
                        telefono = txtTelefono.getText();

                    }
                    boolean banRegistroVenta = false;
                    cargando car = new cargando();
                    car.setVisible(true);
                    car.setAlwaysOnTop(true);
                    Clientes beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean(txtNombre.getText());
                    if (beanCliente != null) {
                        System.out.println("cliente registrado en la base de datos 2");
                        Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                        if (listaDeuda.size() != 0) {
                            System.out.println("esta registrado en la tabla deudatotal 2");
                            for (Deudatotal deudatotal : listaDeuda) {
                                if (deudatotal.getStatus().equalsIgnoreCase("No pagado")) {
                                    System.out.println("el cliente tiene deuda 2");
                                    System.out.println("no registramos el cliente pero modificamos la deuda 2");
                                    int nuevaDeuda = deudatotal.getDeudatotal() + Integer.parseInt(txtTotalApagar.getText());
                                    int abonoActual = Integer.parseInt(txtEfectivoRecibido.getText());

                                    //mandamos a la otra pantalla para mostrar informacion de la deuda
                                    beanCliente.setNuevaDeuda(nuevaDeuda);
                                    beanCliente.setAbonoActual(abonoActual);
                                    //enviamos el bean a pantalla clientecondeuda
                                    clienteConDeuda.bean = beanCliente;
                                    clienteConDeuda ccd = new clienteConDeuda();
                                    ccd.setVisible(true);
                                    deudatotal.setDeudatotal(nuevaDeuda);
                                    if (daoDeuda.editar(deudatotal)) {
                                        System.out.println("se modfio la deuda del cliente 2");
                                        System.out.println("vamos a registrar pagos 2");
                                        Pagos beanPagos = new Pagos();
                                        beanPagos.setDeudatotal(deudatotal);
                                        Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica(idUsuario);
                                        beanPagos.setUsuarios(beanUsuario);
                                        beanPagos.setAbono(Integer.parseInt(txtEfectivoRecibido.getText()));
                                        beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                        beanPagos.setFecharegistro2(obtenerFechaDate());

                                        if (daoPag.registrar(beanPagos)) {
                                            System.out.println("registro el pago 2");
                                            System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas 2 ");

                                            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                                int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                                int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                                Productos beanProductos = (Productos) daoProductos.consultaEspecifica(valorId + "");

                                                Productosapartados beanProApartados = new Productosapartados();
                                                beanProApartados.setClientes(beanCliente);
                                                beanProApartados.setProductos(beanProductos);
                                                beanProApartados.setUsuarios(beanUsuario);
                                                beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                                beanProApartados.setStatus("Pagado no entregado");
                                                beanProApartados.setCantidadVenta(valorCantidad);
                                                beanProApartados.setDetallesproducto("Sin detalles");
                                                if (daoProAP.registrar(beanProApartados)) {
                                                    int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                    beanProductos.setCantidad(nuevaExistencia);
                                                    System.out.println("actualizamos las existencias de los productos ");
                                                    if (daoProductos.editar(beanProductos)) {
                                                        System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                    }
                                                    banRegistroVenta = true;
                                                    System.out.println("se registraron los productos en apartados " + i);
                                                }

                                            }

                                        } else {
                                            System.out.println("eroro al  registrar el pago");
                                        }
                                    } else {
                                        System.out.println("error al editar la deuda");
                                    }
                                }
                            }
                        } else {
                            System.out.println("no esta registrado en la tabla deudatotal");
                        }

                        if (banRegistroVenta) {

                            frame.dispose();
                            validar.limpiarCampos(txtTotalApagar);
                            validar.limpiarCampos(txtEfectivoRecibido);
                            validar.limpiarCampos(txtCambio);
                            vaciarTabla(tablaVentas, defaultTablaVentas);
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);
                            car.dispose();
                        } else {
                            car.dispose();
                        }
                    } else {
                        Clientes beanCliente2 = new Clientes();
                        System.out.println("cliente no registrado en bd y lo registramos 2");
                        //registramos el cliente
                        System.out.println("telefon 2" + txtNombre.getText());
                        beanCliente2.setNombrecompleto(txtNombre.getText().toString());
                        System.out.println("telefon 2 " + telefono);
                        beanCliente2.setTelefono(telefono);
                        beanCliente2.setFecharegistro(validar.obtenerFechaActual());

                        beanCliente2.setUsuarios((Usuarios) daoUsuario.consultaEspecifica(idUsuario));
                        if (daoCliente.registrar(beanCliente2)) {

                            System.out.println("se registro el cliente y por ley  no tiene deuda, ni medidas y tampoco fechas 2");
                            System.out.println("vamos a registrar la deuda total 2");

                            Deudatotal beanDeudaT = new Deudatotal();
                            beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean(txtNombre.getText());
                            beanDeudaT.setClientes(beanCliente);
                            beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                            beanDeudaT.setStatus(almacenamientoDeListas.estadoDeudaGlobal);
                            beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                            if (daoDeuda.registrar(beanDeudaT)) {
                                System.out.println("se  registro la deuda");
                                System.out.println("registramos el pago");
                                beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean(txtNombre.getText());
                                Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                                Deudatotal beanDeuda = null;
                                for (Deudatotal deudatotal : listaDeuda) {
                                    Pagos beanPagos = new Pagos();
                                    beanPagos.setDeudatotal(deudatotal);
                                    Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica(idUsuario);
                                    beanPagos.setUsuarios(beanUsuario);
                                    beanPagos.setAbono(Integer.parseInt(txtEfectivoRecibido.getText()));
                                    beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                    beanPagos.setFecharegistro2(obtenerFechaDate());

                                    if (daoPag.registrar(beanPagos)) {
                                        System.out.println("registro el pago");
                                        System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                            int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                            Productos beanProductos = (Productos) daoProductos.consultaEspecifica(valorId + "");
                                            Productosapartados beanProApartados = new Productosapartados();
                                            beanProApartados.setClientes(beanCliente);
                                            beanProApartados.setProductos(beanProductos);
                                            beanProApartados.setUsuarios(beanUsuario);
                                            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                            beanProApartados.setStatus("Apartado");
                                            beanProApartados.setCantidadVenta(valorCantidad);
                                            beanProApartados.setDetallesproducto("Sin detalles");
                                            if (daoProAP.registrar(beanProApartados)) {
                                                int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                beanProductos.setCantidad(nuevaExistencia);
                                                System.out.println("actualizamos las existencias de los productos ");
                                                if (daoProductos.editar(beanProductos)) {
                                                    System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                }
                                                banRegistroVenta = true;
                                                System.out.println("se registraron los productos en apartados " + i);
                                            } else {
                                                System.out.println("error al registrar los pro apartados " + i);
                                            }

                                        }

                                    } else {
                                        System.out.println("eroro al  registrar el pago");
                                    }

                                }//termina el for

                            } else {
                                System.out.println("error al registrar deuda total");
                            }

                        } else {
                            System.out.println("Error al registrar el cliente");
                        }

                        if (banRegistroVenta) {

                            frame.dispose();
                            System.out.println("1");
                            validar.limpiarCampos(txtTotalApagar);
                            System.out.println("122");
                            validar.limpiarCampos(txtEfectivoRecibido);
                            System.out.println("1333");
                            validar.limpiarCampos(txtCambio);
                            System.out.println("3");
                            vaciarTabla(tablaVentas, defaultTablaVentas);
                            System.out.println("144444");
                            new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);
                            car.dispose();
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("5555");

                            System.out.println("1666666666");

                        } else {
                            car.dispose();
                        }

                    }
                }
            };
            hilo.start();

        } else {

        }

        return beanCliente;
    }

//=================================== para pantalla nuevoregistro  btn Aceptar =======================================
    public void btnAceptarNuevoRegistro(JTextField txtNombre, JTextField txtTelefono, JTextField txtClavePro,
            JTextField txtNombrePro, JTextField txtPrecioPro, JTextField txtColorPro, String tipoPro, JTextField txtCantidadPro,
            JTextField talle, JTextField sise, JTextField hombros, JTextField busto, JTextField cintura, JTextField cadera,
            JTextField largoFalda,
            JTextField anchoPuño, JDateChooser fechaPrueba, JDateChooser fechaEvento, JTextArea txtDescripcion,
            JCheckBox checkFoto, JLabel labelFoto, String idUsuario, final File file2, JFrame frame,
            JTable tablaVentas, DefaultTableModel defaultTablaVentas, int cantidadParaComprar, JTextField txtTotalApagar
    ) {

        //validar datos del cliente
        if (validar.validarCampos(txtNombre)) {

            String telefono = "Sin teléfono";
            if (txtTelefono.getText().isEmpty()) {

            } else {
                telefono = txtTelefono.getText();
            }

            if (validar.validarCampos(txtClavePro)) {
                if (validar.validarCampos(txtNombrePro)) {
                    if (validar.validarCampos(txtPrecioPro)) {
                        if (validar.validarCampos(txtColorPro)) {
                            if (validar.validarCampos(txtCantidadPro)) {
                                Thread hilo = new Thread() {
                                    File file = file2;

                                    @Override
                                    public void run() {
                                        cargando c = new cargando();
                                        c.setVisible(true);
                                        c.setAlwaysOnTop(true);

                                        //validar  datos del producto
                                        Productos beanProductos = new Productos();
                                        //consultamos para ver si la clave ya  existe en la bd
                                        Productos beanProductos2 = daoProductos.consultaEspecificaPorClave2019(txtClavePro.getText());
                                        Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica(idUsuario);
                                        System.out.println("bean   usuario 9" + beanUsuario.getNombre());
                                        if (beanProductos2 == null) {
                                            //quiere decir que esa clave no esta registrada en la bd
                                            //llenasmos el bean PRo

                                            System.out.println("clave generada " + txtClavePro.getText());
                                            beanProductos.setIdproductos(Integer.parseInt(txtClavePro.getText()));
                                            beanProductos.setUsuarios(beanUsuario);

                                            beanProductos.setClave(txtClavePro.getText());
                                            beanProductos.setNombre(txtNombrePro.getText());
                                            beanProductos.setPrecio(Integer.parseInt(txtPrecioPro.getText()));
                                            beanProductos.setColor(txtColorPro.getText());
                                            beanProductos.setCantidad(Integer.parseInt(txtCantidadPro.getText()));
                                            beanProductos.setDescripcion(txtDescripcion.getText());
                                            beanProductos.setTipo(tipoPro);

                                            //beanProductos.setFecharegistro(fecharegistro);
                                            //mas abajo llenamos el campo de imagen para enviarlo a registrar el bean
                                            //validamos los cmapos fechas y medidas
                                            System.out.println("del bean que  envio aa llenar lista medidas y fechas " + beanProductos.getIdproductos());
                                            if (validarCamposMedidasYfechas(talle, sise, hombros, busto, cintura, cadera, largoFalda, anchoPuño, beanProductos, fechaPrueba, fechaEvento)) {
                                                //validamos si el check foto esta  activado o no

                                                if (checkFoto.isSelected()) {
                                                    System.out.println("check seleccionado 9");
                                                    if (labelFoto.getIcon() != null) {

                                                        System.out.println("tiene que meter imagens");
                                                        //llenamos la tabla cuando ya todas las validacionesfueroan aceptadoas
                                                        System.out.println("del bean que  envio aa llenar tabla ventas " + beanProductos.getIdproductos());
                                                        if (llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, Integer.parseInt(txtCantidadPro.getText()), txtTotalApagar)) {
                                                            Productosapartados beanProApartado = new Productosapartados();

                                                            beanProApartado.setIdProducto(Integer.parseInt(txtClavePro.getText()));
                                                            beanProApartado.setDetallesproducto(txtDescripcion.getText());
                                                            llenarListaDescripcion(beanProApartado, beanProductos.getIdproductos());
//Cerramos la ventana detallesProductos y cambiamos la existenciade productos solo en la tabla productos 
                                                            //no en la bda
                                                            //buscamos si el cliente tiene  una deuda, si tiene  mostramos un una pantalla con su deuda
                                                            //el cual solo se cerrar cuando se presione el boton registrar venta de la pantalla productos  de la principal1
                                                            // y le sumamos  el costo del producto y lo mostramos en como nueva deuda
                                                            //enviamos el precio para poder sumarlo a la dedua que el ya tiene
                                                            buscarClienteSiTieneDeuda2019(beanProductos.getPrecio());

//frem de la pantalla nuevo registro
                                                            principal.controlnuevoRegistro = false;
                                                            frame.dispose();
                                                            c.dispose();
                                                        } else {
                                                            c.dispose();
                                                            mensajeError menError = new mensajeError();
                                                            mensajeError.labelMensaje.setText("Error al agregar el producto en la tabla ventas");
                                                            menError.setVisible(true);
                                                            menError.setAlwaysOnTop(true);
                                                            // JOptionPane.showMessageDialog(null, "Error al agregar el producto en la tabla ventas", "Error", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    } else {
                                                        c.dispose();
                                                        mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                                        mensajeAdvertencia.labelMensaje.setText("Selecciona la foto del prodcuto");
                                                        menAdvertencia.setVisible(true);
                                                        menAdvertencia.setAlwaysOnTop(true);

                                                        //JOptionPane.showMessageDialog(null, "Selecciona la foto del prodcuto", "Advertecia", JOptionPane.WARNING_MESSAGE);
                                                    }
                                                } else {
                                                    file = null;
                                                    //llenamos la tabla cuando ya todas las validacionesfueroan aceptadoas
                                                    System.out.println("check foto no esta  activado");
                                                    //llenamos la tabla cuando ya todas las validacionesfueroan aceptadoas
                                                    if (llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, Integer.parseInt(txtCantidadPro.getText()), txtTotalApagar)) {

//Cerramos la ventana detallesProductos y cambiamos la existenciade productos solo en la tabla productos 
                                                        //no en la bda
                                                        //buscamos si el cliente tiene  una deuda, si tiene  mostramos un una pantalla con su deuda
                                                        //el cual solo se cerrar cuando se presione el boton registrar venta de la pantalla productos  de la principal1
                                                        // y le sumamos  el costo del producto y lo mostramos en como nueva deuda
                                                        //enviamos el precio para poder sumarlo a la dedua que el ya tiene
                                                        buscarClienteSiTieneDeuda2019(beanProductos.getPrecio());

//frem de la pantalla nuevo registro
                                                        principal.controlnuevoRegistro = false;
                                                        frame.dispose();

                                                    } else {
                                                        mensajeError menError = new mensajeError();
                                                        mensajeError.labelMensaje.setText("Error al agregar el producto en la tabla ventas");
                                                        menError.setVisible(true);
                                                        menError.setAlwaysOnTop(true);
                                                        // JOptionPane.showMessageDialog(null, "Error al agregar el producto en la tabla ventas", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }

                                                }
                                                System.out.println("estamos  dentron del true del validar campos medidas y fechas");
                                                System.out.println("foto para la lista " + file);
                                                beanProductos.setFotoString(String.valueOf(file));

                                                //el producto se va a guardar con consulta sql
                                                //solo lo guardaremos en la lista productos sin registrar en bd
                                                //llenamos la lista producto
                                                almacenamientoDeListas.listaProductos.add(beanProductos);

                                                //llenamos la lista Cliente
                                                Clientes beanCliente = new Clientes();
                                                beanCliente.setNombrecompleto(txtNombre.getText());
                                                beanCliente.setTelefono(txtTelefono.getText());
                                                beanCliente.setFecharegistro(validar.obtenerFechaActual());
                                                //consultamos el usuario para obtener sus datos

                                                beanCliente.setUsuarios(beanUsuario);
                                                llenarListaClientes(beanCliente);
                                                //llenamos la lista de descripcion 
                                                Productosapartados beanProApartados = new Productosapartados();
                                                beanProApartados.setIdProducto(Integer.parseInt(txtClavePro.getText()));
                                                beanProApartados.setDetallesproducto(txtDescripcion.getText());
                                                llenarListaDescripcion(beanProApartados, beanProductos.getCantidad());

                                                System.out.println("las lista y fehcas se llenan desde la validacion");
                                                c.dispose();
                                            } else {
                                                c.dispose();
                                            }

                                        } else {
                                            c.dispose();
                                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                            mensajeAdvertencia.labelMensaje.setText("Esta clave ya existe " + "\n GENERa OTRA POR FAVOR");
                                            menAdvertencia.setVisible(true);
                                            menAdvertencia.setAlwaysOnTop(true);
                                            //JOptionPane.showMessageDialog(null, "Esta clave ya existe en la base de datos" + "\n GENERE OTRA POR FAVOR", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                        }

                                    }

                                };
                                hilo.start();

                            }

                        }
                    }

                }

            } else {

            }

        } else {

        }

    }

    public void btnCanelarVenta(JTable tablaVentas, DefaultTableModel defaulTableVentas, JTextField txtTotalApagar,
            JTextField txtRecibido, JTextField txtCambio, JTable tablaProductos, DefaultTableModel defaulTablaProductos) {
        limpiarListas();
        vaciarTabla(tablaVentas, defaulTableVentas);
        validar.limpiarCampos(txtTotalApagar);
        validar.limpiarCampos(txtRecibido);
        validar.limpiarCampos(txtCambio);
        new controlProductos().llenarTabla(tablaProductos, defaulTablaProductos);

    }

    public void btnQuitarProductosTablaVentas(int idTablaVentas, int cantidadTableVentas, int subtotalProducto, JTextField txtTotalApagar,
            JTextField txtRecibido, JTextField txtCambio,
            JTable tablaProductos, DefaultTableModel defaulTablaProductos, JLabel labelCambio) {
        //descontamos el subtotal de los productos  del totalApagar
        int restarElTotalApagar = Integer.parseInt(txtTotalApagar.getText()) - subtotalProducto;
        txtTotalApagar.setText(restarElTotalApagar + "");
        //reasignamos de  nuevo los valores aa los textField
        if (txtRecibido.getText().isEmpty()) {
//no hacemos nada

        } else {
            //verificamos si el totalApagar es mayor a efectivo recibido
            //para hacer las operaciones
            if (Integer.parseInt(txtRecibido.getText()) >= Integer.parseInt(txtTotalApagar.getText().toString())) {

                labelCambio.setText("Cambio:");
                labelCambio.setForeground(Color.BLUE);
                int cambio = Integer.parseInt(txtRecibido.getText()) - Integer.parseInt(txtTotalApagar.getText().toString());
                txtCambio.setForeground(Color.BLUE);
                txtCambio.setText(cambio + "");
            } else {

                labelCambio.setText("Restan:");
                labelCambio.setForeground(Color.red);
                int cambio = Integer.parseInt(txtTotalApagar.getText().toString()) - Integer.parseInt(txtRecibido.getText());
                txtCambio.setForeground(Color.red);

                txtCambio.setText(cambio + "");
            }

        }
        //buscamos el productos en latabla producto para asignarle el produicto que se esta quitando

        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            int valorId = Integer.parseInt(tablaProductos.getValueAt(i, 0) + "");
            int valorCantidad = Integer.parseInt(tablaProductos.getValueAt(i, 4) + "");
            System.out.println("valor table pro " + valorCantidad);
            System.out.println("valor table ventas " + cantidadTableVentas);
            if (idTablaVentas == valorId) {
                int cantidadExistencia = cantidadTableVentas + valorCantidad;
                tablaProductos.setValueAt(cantidadExistencia, i, 4);
                quitarProductoDLista(valorId);

                i = tablaProductos.getRowCount();

            } else {

            }
        }

        if (tablaProductos.getRowCount() == 0) {
            limpiarListas();
        }

    }

    public void quitarProductoDLista(int idTablaVenta) {
        if (almacenamientoDeListas.listaClientes.size() > 0) {
            for (int i = 0; i < almacenamientoDeListas.listaDescripcion.size(); i++) {
                if (almacenamientoDeListas.listaDescripcion.get(i).getIdProducto() == idTablaVenta) {
                    almacenamientoDeListas.listaDescripcion.remove(i);
                }
            }
            for (int i = 0; i < almacenamientoDeListas.listaFechas.size(); i++) {
                if (almacenamientoDeListas.listaFechas.get(i).getIdProducto() == idTablaVenta) {
                    almacenamientoDeListas.listaFechas.remove(i);
                }
            }

            for (int i = 0; i < almacenamientoDeListas.listaMedidas.size(); i++) {
                if (almacenamientoDeListas.listaMedidas.get(i).getIdProducto() == idTablaVenta) {
                    almacenamientoDeListas.listaMedidas.remove(i);
                }
            }
            for (int i = 0; i < almacenamientoDeListas.listaProductos.size(); i++) {
                if (almacenamientoDeListas.listaProductos.get(i).getIdproductos() == idTablaVenta) {
                    almacenamientoDeListas.listaProductos.remove(i);
                }
            }

        } else {

        }
    }

    public void aceptarVentaRapida(JTextField txtNombre, JTextField txtCantidad, JTextField txtPrecio,
            JFrame frame, JTable tablaVentas, DefaultTableModel defaultTablaVentas, JTextField txtTotalAPagar,
            String idUsuario) {

        if (validar.validarCampos(txtNombre)) {
            if (validar.validarCampos(txtCantidad)) {
                if (validar.validarCampos(txtPrecio)) {
                    //SI  EL CAMPO SE COMPLETO ENTONCES ARROJAREMOS UN MENSAJE     
//guardamos los valores ingresados por el usuario en variables
                    String nombre = txtNombre.getText().toString();
                    int cantidad = Integer.parseInt(txtCantidad.getText());
                    int precio = Integer.parseInt(txtPrecio.getText());

                    //como es  venta rapida vamos a llenar  en bean  y guardalo en la lista productos
                    //para cuando se presione elbtn  registrare venta  se registre este pro
                    Usuarios beanUsuarios = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);
                    //creamos el bean producto y lo llenamos
                    Productos beanProductos = new Productos();
                    int clave = 0;
                    int numSecuencia = 104927583;
                    //hacemos un random para generar una clave para nuestro producto
                    Random numAletorio = new Random();
                    for (int i = 0; i < 3; i++) {

                        clave = numAletorio.nextInt(numSecuencia);
                        //si la clave existe tienes 2 hcanses para  generar la clave ala 3 generamos otra secuencia de numeros
                        if (new controlProductos().validarProductoExisteten(clave + "")) {
                            //si es true quiere decir que clave ya existe
                            if (i == 1) {
                                numSecuencia = 948576123;

                            } else {

                            }
                        } else {
                            break;
                        }

                    }
                    beanProductos.setIdproductos(clave);
                    beanProductos.setClave(clave + "");
                    beanProductos.setPrecio(precio);
                    beanProductos.setColor("NA");
                    beanProductos.setTipo("Plata");
                    beanProductos.setFotoString(null);
                    beanProductos.setDescripcion("NA");
                    beanProductos.setCantidad(cantidad);
                    beanProductos.setNombre(nombre);
                    //despues de llenar  el bean productos ahora lo guardamremos en nuestra lista static
                    almacenamientoDeListas.listaProductos.add(beanProductos);

                    llenarTablaVentas(tablaVentas, defaultTablaVentas, beanProductos, cantidad, txtTotalAPagar);

                    // descontarExistenciasTablaProductos(tablaProductos, defaultTablaPro, cantidadParaComprar);
                    frame.dispose();

                    principal.controlventaRapida = false;

                }
            }

        } else {//termina la  validacion del  campo

        }
    }

    public Clientes buscarClienteSiTieneDeuda2019(int precioProducto) {
        Clientes bean = null;
        // System.out.println("llamado por otra  ventana");
        if (almacenamientoDeListas.listaClientes.size() != 0) {

            bean = daoCliente.consultaEspecificaPorNombreBean2019(almacenamientoDeListas.listaClientes.get(0).getNombrecompleto());

            if (bean != null) {

                //enviamos el bean a pantalla clientecondeuda
                clienteConDeuda.precioPro = precioProducto + "";
                clienteConDeuda.bean = bean;
                clienteConDeuda ccd = new clienteConDeuda();
                ccd.setVisible(true);

            } else {
            }

        }
        return bean;
    }

    public Clientes registrarCliente2019(JTextField txtNombre, JTextField txtTelefono, JFrame frame,
            String idUsuario, JTextField txtTotalApagar, JTextField txtEfectivoRecibido, JTextField txtCambio, JTable tablaVentas, DefaultTableModel defaultTablaVentas,
            JTable tablaProductos, DefaultTableModel defaultTablaProductos) {
        System.out.println("aqui mero homi");
        if (validar.validarCampos(txtNombre)) {

            Thread hilo = new Thread() {
                @Override
                public void run() {
                    String telefono = "Sin telefono";
                    boolean banRegistroVenta = false;
                    boolean banClienteRegistradoSinDeuda = false;
                    if (txtTelefono.getText().isEmpty()) {

                    } else {
                        telefono = txtTelefono.getText();

                    }
                    cargando car = new cargando();

                    car.setVisible(true);
                    car.setAlwaysOnTop(true);
                    Clientes beanCliente = daoCliente.consultaEspecificaPorNombreBean2019(txtNombre.getText());
                    //checamo si el cliente esta registrado  en  la bd  y buscamos si tiene deuda
                    if (beanCliente != null) {
                        System.out.println("el cliente esta registrado en la  bd");
                        Usuarios beanUsuario = (Usuarios) daoUsuario.consultaEspecifica2019(idUsuario);

                        System.out.println("cliente registrado en la base de datos");
                        Deudatotal beanDeuda = daoCliente.obtnerDeudaXIdCliente(beanCliente.getIdclientes() + "");
                        if (beanDeuda != null) {
                            System.out.println("esta registrado en la tabla deudatotal");

                            System.out.println("el cliente tiene deuda");
                            System.out.println("no registramos el cliente pero modificamos la deuda");
                            int nuevaDeuda = beanDeuda.getDeudatotal() + Integer.parseInt(txtTotalApagar.getText());
                            int abonoActual = almacenamientoDeListas.abonoPagoGlobal;
                            //mandamos a la otra pantalla para mostrar informacion de la deuda
                            beanCliente.setNuevaDeuda(nuevaDeuda);
                            beanCliente.setAbonoActual(abonoActual);
                            //enviamos el bean a pantalla clientecondeuda
                            /*clienteConDeuda.bean = beanCliente;
                            clienteConDeuda ccd = new clienteConDeuda();
                            ccd.setVisible(true);*/
                            beanDeuda.setDeudatotal(nuevaDeuda);
                            if (daoDeuda.editarDeuda2019(beanDeuda)) {
                                banRegistroVenta = true;
                                System.out.println("se modfio la deuda del cliente");
                                System.out.println("vamos a registrar pagos");
                                Pagos beanPagos = new Pagos();
                                beanPagos.setDeudatotal(beanDeuda);
                                beanPagos.setUsuarios(beanUsuario);

                                beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                beanPagos.setFecharegistro2(obtenerFechaDate());

                                if (daoPag.registrar(beanPagos)) {
                                    banRegistroVenta = true;
                                    System.out.println("registro el pago");
                                    System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                    for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                        int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                        int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");

                                        Productos beanProductos = (Productos) daoProductos.consultaEspecifica2019(valorId + "");

                                        Productosapartados beanProApartados = new Productosapartados();
                                        beanProApartados.setClientes(beanCliente);
                                        beanProApartados.setProductos(beanProductos);
                                        beanProApartados.setUsuarios(beanUsuario);
                                        beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                        beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                        beanProApartados.setCantidadVenta(valorCantidad);
                                        beanProApartados.setDetallesproducto("SIN DETALLES");
                                        if (daoProAP.registrar(beanProApartados)) {
                                            banRegistroVenta = true;
                                            int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                            beanProductos.setCantidad(nuevaExistencia);
                                            System.out.println("actualizamos las existencias de los productos ");
                                            if (daoProductos.editar(beanProductos)) {
                                                System.out.println("REVISAR se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                            }

                                            System.out.println("se registraron los productos en apartados " + i);
                                        } else {
                                            banRegistroVenta = false;
                                        }

                                    }

                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("eroro al  registrar el pago");
                                }
                            } else {
                                banRegistroVenta = false;
                                System.out.println("error al editar la deuda");
                            }

                            //aqui el cliente existe en la bd pero no tiene deuda
                            if (banClienteRegistradoSinDeuda) {
                                System.out.println("El  cliente esta registrado en la bd pero no tiene deuda");
                                //si es true quiered decir que el cliente si esta registrado en la bd pero no tiene deuda
                                //registramos la deduatotal del cliente
                                //registramos los pagos
                                //registramos los  produtos apartados

                                System.out.println("vamos a registrar la deuda total");

                                Deudatotal beanDeudaT = new Deudatotal();

                                beanDeudaT.setClientes(beanCliente);
                                beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                                beanDeudaT.setStatus(almacenamientoDeListas.estadoDeudaGlobal);
                                beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                                if (daoDeuda.registrar(beanDeudaT)) {
                                    banRegistroVenta = true;
                                    System.out.println("se  registro la deuda");
                                    System.out.println("registramos el pago");
//consultamos la deuda   total por medio del ultimo registro
                                    //Deudatotal deudatotal1 = (Deudatotal) daoDeuda.consultaEspecifica(almacenamientoDeListas.ultimoRegistroDeuda + "");
                                    //le metemos al bean su id de deudatotal
                                    beanDeudaT.setIddeudatotal(almacenamientoDeListas.ultimoRegistroDeuda);
                                    System.out.println("soy el id deuda total " + beanDeudaT.getStatus());
                                    //buscamos su deuda y obtenemos el id deuda para guardarlo en la tabla pagos
                                    Pagos beanPagos = new Pagos();

                                    beanPagos.setDeudatotal(beanDeudaT);

                                    beanPagos.setUsuarios(beanUsuario);
                                    beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                    beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                    beanPagos.setFecharegistro2(obtenerFechaDate());

                                    if (daoPag.registrar(beanPagos)) {
                                        banRegistroVenta = true;
                                        System.out.println("registro el pago");
                                        System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                            int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                            int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                            Productos beanProductos = (Productos) daoProductos.consultaEspecifica2019(valorId + "");
                                            Productosapartados beanProApartados = new Productosapartados();
                                            beanProApartados.setClientes(beanCliente);
                                            beanProApartados.setProductos(beanProductos);
                                            beanProApartados.setUsuarios(beanUsuario);
                                            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                            beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                            beanProApartados.setCantidadVenta(valorCantidad);
                                            beanProApartados.setDetallesproducto("SIN DETALLES");
                                            if (daoProAP.registrar(beanProApartados)) {
                                                banRegistroVenta = true;
                                                int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                                beanProductos.setCantidad(nuevaExistencia);
                                                System.out.println("actualizamos las existencias de los productos ");
                                                if (daoProductos.editar(beanProductos)) {
                                                    System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                                }

                                                System.out.println("se registraron los productos en apartados " + i);
                                            } else {
                                                banRegistroVenta = false;
                                                System.out.println("error al registrar los pro apartados " + i);
                                            }

                                        }

                                    } else {
                                        banRegistroVenta = false;
                                        System.out.println("eroro al  registrar el pago");
                                    }

                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("error al registrar deuda total");
                                }

                            } else {
                                //else del if banClienteRegistradoSinDeuda
                            }
                        } else {
                            System.out.println("no esta registrado en la tabla deudatotal");
                        }

                        if (banRegistroVenta) {

                            frame.dispose();
                            validar.limpiarCampos(txtTotalApagar);
                            validar.limpiarCampos(txtEfectivoRecibido);
                            validar.limpiarCampos(txtCambio);
                            vaciarTabla(tablaVentas, defaultTablaVentas);
                            car.dispose();
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);

                        } else {

                        }

                    } else {//else de que el cliente no esta registradon en la bd
                        Clientes beanCliente2 = new Clientes();
                        System.out.println("cliente no registrado en bd y lo registramos");
                        //registramos el cliente
                        System.out.println("telefon " + txtNombre.getText());
                        beanCliente2.setNombrecompleto(txtNombre.getText().toString());
                        System.out.println("telefon " + telefono);
                        beanCliente2.setTelefono(telefono);
                        beanCliente2.setFecharegistro(validar.obtenerFechaActual());
                        Usuarios beanUsuario = daoUsuario.consultaEspecifica2019(idUsuario);

                        beanCliente2.setUsuarios(beanUsuario);
                        if (daoCliente.registrar(beanCliente2)) {
                            banRegistroVenta = true;
                            System.out.println("se registro el cliente y por ley  no tiene deuda, ni medidas y tampoco fechas");
                            System.out.println("vamos a registrar la deuda total");

                            Deudatotal beanDeudaT = new Deudatotal();
                            beanCliente = (Clientes) daoCliente.consultaEspecificaPorNombreBean2019(txtNombre.getText());
                            beanDeudaT.setClientes(beanCliente);
                            beanDeudaT.setDeudatotal(Integer.parseInt(txtTotalApagar.getText()));
                            beanDeudaT.setStatus(almacenamientoDeListas.estadoDeudaGlobal);
                            beanDeudaT.setFecharegistro(validar.obtenerFechaActual());
                            if (daoDeuda.registrar(beanDeudaT)) {
                                banRegistroVenta = true;
                                System.out.println("se  registro la deuda");
                                System.out.println("registramos el pago");
                                Deudatotal deudatotal1 =  daoDeuda.consultaEspecifica2019(almacenamientoDeListas.ultimoRegistroDeuda + "");

                                Pagos beanPagos = new Pagos();
                                beanPagos.setDeudatotal(deudatotal1);
                                beanPagos.setUsuarios(beanUsuario);
                                System.out.println("abonoPagoGlobal " + almacenamientoDeListas.abonoPagoGlobal);
                                beanPagos.setAbono(almacenamientoDeListas.abonoPagoGlobal);
                                beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                beanPagos.setFecharegistro2(obtenerFechaDate());

                                if (daoPag.registrar(beanPagos)) {
                                    banRegistroVenta = true;
                                    System.out.println("registro el pago");
                                    System.out.println("ahora si registraremos los productos apartados sin fechas y sin medidas ");

                                    for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                                        int valorId = Integer.parseInt(tablaVentas.getValueAt(i, 0) + "");
                                        int valorCantidad = Integer.parseInt(tablaVentas.getValueAt(i, 2) + "");
                                        Productos beanProductos =  daoProductos.consultaEspecifica2019(valorId + "");
                                        Productosapartados beanProApartados = new Productosapartados();
                                        beanProApartados.setClientes(beanCliente);
                                        beanProApartados.setProductos(beanProductos);
                                        beanProApartados.setUsuarios(beanUsuario);
                                        beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                                        beanProApartados.setStatus(almacenamientoDeListas.estadoProductoAPartadoGlobal);
                                        beanProApartados.setCantidadVenta(valorCantidad);
                                        beanProApartados.setDetallesproducto("Sin detalles");
                                        if (daoProAP.registrar(beanProApartados)) {
                                            banRegistroVenta = true;
                                            int nuevaExistencia = beanProductos.getCantidad() - valorCantidad;
                                            beanProductos.setCantidad(nuevaExistencia);
                                            System.out.println("actualizamos las existencias de los productos ");
                                            if (daoProductos.editarExistencias2019(beanProductos)) {
                                                System.out.println("se modifico  la existencia del producto " + beanProductos.getIdproductos());
                                            }

                                            System.out.println("se registraron los productos en apartados " + i);
                                        } else {
                                            banRegistroVenta = false;
                                            System.out.println("error al registrar los pro apartados " + i);
                                        }

                                    }

                                } else {
                                    banRegistroVenta = false;
                                    System.out.println("eroro al  registrar el pago");
                                }

                            } else {
                                banRegistroVenta = false;
                                System.out.println("error al registrar deuda total");
                            }

                        } else {
                            banRegistroVenta = false;
                            System.out.println("Error al registrar el cliente");
                        }

                        if (banRegistroVenta) {

                            frame.dispose();

                            validar.limpiarCampos(txtTotalApagar);

                            validar.limpiarCampos(txtEfectivoRecibido);

                            validar.limpiarCampos(txtCambio);

                            vaciarTabla(tablaVentas, defaultTablaVentas);

                            car.dispose();
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("La venta se registro correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            // JOptionPane.showMessageDialog(null, "La venta se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                            new controlProductos().llenarTabla(tablaProductos, defaultTablaProductos);

                        } else {
                            car.dispose();
                            mensajeError menError = new mensajeError();
                            mensajeError.labelMensaje.setText("Error al registrar venta correctamente");
                            menError.setVisible(true);
                            menError.setAlwaysOnTop(true);
                        }

                    }

                }
            };
            hilo.start();

        } else {

        }

        return beanCliente;

    }
}
