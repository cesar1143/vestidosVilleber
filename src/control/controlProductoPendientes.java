/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ModeloProductos.DaoProductos;
import NuevasPantallas.principal;
import NuevasPantallas.productosPendientes;
import beans.Clientes;
import beans.Deudatotal;
import beans.Fechaspruebas;
import beans.Medidas;
import beans.Pagos;
import beans.Productos;
import beans.Productosapartados;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
import modelo.daocontrolProductosApartados;
import org.hibernate.engine.jdbc.BinaryStream;
import pantallas.cambiarEstadoProductosApartados;
import pantallas.cancelarProductosApartados;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlProductoPendientes {

    daoProductos dao = new daoProductos();
    validarCampos validar = new validarCampos();
    daoProductosApartados daoApartados = new daoProductosApartados();

    public void llenarTablaPendientes(JTable tabla, DefaultTableModel defaultTabla) {
        List<Productosapartados> lista = consultarTodos();
        //System.out.println("lista pendientes " + lista.size());
        if (lista.size() > 0) {
            vaciarTabla(tabla, defaultTabla);

            for (int i = 0; i < lista.size(); i++) {

                Set<Fechaspruebas> listaFechas = lista.get(i).getFechaspruebases();

                Fechaspruebas beanFechas = null;
                if (listaFechas.size() > 0) {

                    for (Fechaspruebas listaFecha : listaFechas) {
                        defaultTabla.addRow(new Object[]{lista.get(i).getIdproductosapartados(), lista.get(i).getProductos().getClave(),
                            lista.get(i).getClientes().getNombrecompleto(), lista.get(i).getStatus(), lista.get(i).getCantidadVenta(), listaFecha.getFechaprueba(),
                            listaFecha.getFechaevento()});
                    }

                } else {

                    defaultTabla.addRow(new Object[]{lista.get(i).getIdproductosapartados(), lista.get(i).getProductos().getClave(),
                        lista.get(i).getClientes().getNombrecompleto(), lista.get(i).getStatus(), lista.get(i).getCantidadVenta(), "Sin Fechas",
                        "Sin Fechas"});
                }
            }
        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("No hay ningun producto pendiente");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "No hay ningun producto pendiente");
        }

    }

    public void llenarTablaPendientesNew(JTable tablaPendientes, DefaultTableModel modelTablaPendientes,
            JTable tablaClientes, DefaultTableModel modelTablaClientes,
            JTable tablaMedidas, DefaultTableModel modelMedidas, JTextArea txtDescripcion, JLabel labelFoto) {
        if (tablaMedidas.getRowCount() > 0) {
            vaciarTabla(tablaMedidas, modelMedidas);
        } else {

        }
        //limpiamos el campo descripcion y foto
        txtDescripcion.setText("");
        labelFoto.setText("");
        labelFoto.setIcon(null);
        int fila = tablaClientes.getSelectedRow();
        String idCliente = modelTablaClientes.getValueAt(fila, 0) + "";

        List<Productosapartados> lista = daoApartados.llenarTablaPendientesNew(idCliente);

        if (lista.size() > 0) {
            vaciarTabla(tablaPendientes, modelTablaPendientes);

            for (int i = 0; i < lista.size(); i++) {
                String hacer = "PRUEBA";
                if (lista.get(i).getStatus().equalsIgnoreCase("apartado")) {

                } else if (lista.get(i).getStatus().equalsIgnoreCase("pagado no entregado")) {
                    hacer = "ENTREGAR";
                } else {

                }
                String arreDatos[]=lista.get(i).getClave().split("-");
                String clave=arreDatos[0];
                String precio=arreDatos[1];
                String nombre=arreDatos[2];
                modelTablaPendientes.addRow(new Object[]{
                    lista.get(i).getIdproductosapartados(),
                    clave,
                    nombre.toUpperCase(),
                    precio,
                    lista.get(i).getStatus().toUpperCase(),
                    lista.get(i).getCantidadVenta(),
                    lista.get(i).getFechaPrueba(),
                    lista.get(i).getFechaEvento(),
                    hacer});
            }

        } else {

        }
    }

    public List<Productosapartados> consultarTodos() {
        List<Productosapartados> lista = new ArrayList<Productosapartados>();
        List<Object> listaObjec = daoApartados.consultarTodosPendientes();

        for (int i = 0; i < listaObjec.size(); i++) {
            Productosapartados bean = (Productosapartados) listaObjec.get(i);
            lista.add(bean);

        }
        return lista;
    }

    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

    public void mostrarMedidasProductos(int idProApartado, JTable tablaMedidas, DefaultTableModel defaultTablaMedidas,
            JTextArea txtAreaDetallesProducto) {

        Productosapartados bean = (Productosapartados) daoApartados.consultaEspecifica(idProApartado + "");
        Set<Medidas> listaMedidas = bean.getMedidases();
        vaciarTabla(tablaMedidas, defaultTablaMedidas);
        txtAreaDetallesProducto.setText(bean.getDetallesproducto());
        if (listaMedidas.size() > 0) {
            for (Medidas listaMedida : listaMedidas) {
                defaultTablaMedidas.addRow(new Object[]{listaMedida.getIdmedidas(), listaMedida.getTalle(), listaMedida.getSise(), listaMedida.getHombros(),
                    listaMedida.getBusto(), listaMedida.getLargoFalda(), listaMedida.getAnchoPuno(), listaMedida.getCintura(), listaMedida.getCadera()});

            }

        } else {
            System.out.println("no  tiene medidas");
        }

    }

    public void buscarCliente(JTextField txtBuscarCliente, JTable tabla, DefaultTableModel defaultTabla) {

        if (validar.validarCampos(txtBuscarCliente)) {
            //vaciamos la tabla
            vaciarTabla(tabla, defaultTabla);
            //consultamos el cliente para obtener sus pro apartados
            List<Clientes> listaCliente = new daoClientes().consultaEspecificaPorNombre(txtBuscarCliente.getText());
            for (int i = 0; i < listaCliente.size(); i++) {
                Clientes beanCliente = listaCliente.get(i);
                //verificamos que no venga null
                if (beanCliente != null) {
                    //obtenemos la lista de pro apartados
                    Set<Productosapartados> listaProApartado = beanCliente.getProductosapartadoses();
                    System.out.println("lista apartados " + listaProApartado.size());
                    if (listaProApartado.size() != 0) {
                        //System.out.println("entro en lis apartados " + listaProApartado.size());
                        for (Productosapartados productosapartados : listaProApartado) {
                            if (!productosapartados.getStatus().equals("Pagado entregado")) {
                                Set<Fechaspruebas> listaFechas = productosapartados.getFechaspruebases();
                                Fechaspruebas beanFechas = null;

                                if (listaFechas.size() != 0) {
                                    for (Fechaspruebas listaFecha : listaFechas) {
                                        //System.out.println("entro en lis fechas " + listaFechas.size());

                                        defaultTabla.addRow(new Object[]{productosapartados.getIdproductosapartados(), productosapartados.getProductos().getClave(),
                                            productosapartados.getClientes().getNombrecompleto(), productosapartados.getStatus(), productosapartados.getCantidadVenta(), listaFecha.getFechaprueba(),
                                            listaFecha.getFechaevento()});
                                    }

                                } else {
                                    defaultTabla.addRow(new Object[]{productosapartados.getIdproductosapartados(), productosapartados.getProductos().getClave(),
                                        productosapartados.getClientes().getNombrecompleto(), productosapartados.getStatus(), productosapartados.getCantidadVenta(), "Sin Fechas",
                                        "Sin Fechas"});
                                }

                                //aqui  insertamos en la tabla
                            } else {

                            }

                        }

                    } else {

                    }

                } else {
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("El cliente no existe");
                    menAdvertencia.setVisible(true);
                    menAdvertencia.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "El cliente no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }

            }

        }
    }

    public void buscarClienteFiltrado(JTable tablaClientes, DefaultTableModel modelTablaClientes, JTextField txtNombre,
            JTable tablaPen, DefaultTableModel modelPendientes, JTable tablaMe, DefaultTableModel modelMe, JTextArea txtDesc,
            JLabel labelFoto) {
        //limpimaos campos
        if (tablaPen.getRowCount() > 0) {
            vaciarTabla(tablaPen, modelPendientes);
        }
        if (tablaMe.getRowCount() > 0) {
            vaciarTabla(tablaMe, modelMe);
        }
        txtDesc.setText("");
        labelFoto.setText("");
        labelFoto.setIcon(null);
        List<Clientes> lista = daoApartados.buscarClienteFiltrado(txtNombre.getText());
        if (lista.size() > 0) {
            vaciarTabla(tablaClientes, modelTablaClientes);
            for (int i = 0; i < lista.size(); i++) {
                modelTablaClientes.addRow(new Object[]{lista.get(i).getIdclientes(), lista.get(i).getNombrecompleto().toUpperCase(), lista.get(i).getTelefono()});

            }
        } else {

        }
    }

    public void eliminar(String id, JTable tabla, DefaultTableModel defaultTabla) {
        List<Productosapartados> lista = new ArrayList<Productosapartados>();
        //HACEMOS CONSULTA ESPECIFICA EN PRODUCTOS BUSCANDO EL ID ENVIADO POR EL USUARIO
        Productos bean = (Productos) dao.consultaEspecifica(id);

        //LA CONSULTA ANTERIRO POR DEFAULT YA ME TRARE UNA LISTA DE MI TABLA PRO APARTADOS
        Set<Productosapartados> litsaProApartado = bean.getProductosapartadoses();

        if (litsaProApartado.size() > 0) {
            //SI ES IGUAL A 0 QUIERE DECIR QUE ESE PRODUCTO NO ESTA REGISTRADO EN APARTADOS
            //Y SE ELIMINA SIN PROBLEMA ALGUNO
            for (Productosapartados productosapartados : litsaProApartado) {
                if (productosapartados.getStatus().equals("Pagado entregado")) {
                    System.out.println("entro aqui");
                    //buscaremos si tiene medidas y fechas este producto y los eliminaremos 
                    Set<Medidas> listaMedidas = productosapartados.getMedidases();
                    Set<Fechaspruebas> listaFechas = productosapartados.getFechaspruebases();

                    if (listaMedidas.size() > 0) {
                        for (Medidas listaMedida : listaMedidas) {
                            //eliminamos las medidas
                            if (new daoMedidas().eliminar(listaMedida)) {
                                System.out.println("se eliminiaron las medidas");
                            }

                        }
                    }
                    //eliminamos las fechas
                    if (listaMedidas.size() > 0) {
                        for (Fechaspruebas listaFecha : listaFechas) {
                            //eliminamos las medidas
                            if (new daoFechas().eliminar(listaFecha)) {
                                System.out.println("se eliminiaron las fechas");
                            }

                        }
                    }
                    //eliminamos el producto apartado ante de eliminar el producto
                    if (new daoProductosApartados().eliminar(productosapartados)) {
                        System.out.println("se elimino el pro apartado " + productosapartados.getIdProducto());
                        if (dao.eliminar(bean)) {
                            mensajeExito menExito = new mensajeExito();
                            mensajeExito.labelMensaje.setText("Se elimino correctamente");
                            menExito.setVisible(true);
                            menExito.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            llenarTablaPendientes(tabla, defaultTabla);

                        } else {
                            mensajeError menError = new mensajeError();
                            mensajeError.labelMensaje.setText("Error al eliminar");
                            menError.setVisible(true);
                            menError.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(null, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }

                } else {
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("No se puede eliminar," + "\n algún cliente lo tiene apartado");
                    menAdvertencia.setVisible(true);
                    menAdvertencia.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "No se puede eliminar," + "\n algún cliente lo tiene apartado", "Advertencia", JOptionPane.WARNING_MESSAGE);

                }
            }

        } else {
            if (dao.eliminar(bean)) {
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("Se elimino correctamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                llenarTablaPendientes(tabla, defaultTabla);
            } else {
                mensajeError menError = new mensajeError();
                mensajeError.labelMensaje.setText("Error al eliminar");
                menError.setVisible(true);
                menError.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);

            }
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("No se puede eliminar," + "\n algún cliente lo tiene apartado");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "No se puede eliminar," + "\n algún cliente lo tiene apartado", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }

    }

    public void editar(JTextField id, JTextField clave, JTextField nombre, JTextField precio, JTextField color, String tipo, JTextField cantidad, JTextArea descripcion, JLabel foto, File file, JFrame frame, JTable tabla, DefaultTableModel defaultTabla) {
        String desc = descripcion.getText().toString();
        String foto1 = foto.getText().toString();
        if (validar.validarCampos(clave)) {
            if (validar.validarCampos(nombre)) {
                if (validar.validarCampos(precio)) {
                    if (validar.validarCampos(color)) {
                        if (validar.validarCampos(cantidad)) {
                            if (descripcion.getText().toString().isEmpty()) {
                                desc = "null";

                                if (foto.getIcon() == null) {
                                    file = null;
                                } else {

                                }
                            } else {

                            }
                            //SI TODOS LOS CAMPOS FUERON COMPLETADOS ENTONCES HACEMOS EL REGISTRO
                            Productos bean = new Productos();

                            bean.setClave(clave.getText());
                            bean.setNombre(nombre.getText());
                            bean.setPrecio(Integer.parseInt(precio.getText()));
                            bean.setColor(color.getText());
                            bean.setTipo(tipo);
                            bean.setCantidad(Integer.parseInt(cantidad.getText()));
                            bean.setDescripcion(desc);
                            bean.setIdproductos(Integer.parseInt(id.getText()));

                            bean.setFotoString(String.valueOf(file));
                            //VALIDAMOS SI ES UNA IMAGEN DE LA BD O UNA IMAGEN NUEVA

                            if (bean.getFotoString().equals("null")) {
                                //MANDAMOS AMODIFICAR EL PRODUCTO
                                if (dao.modificarProductoFotoActual(bean)) {
                                    //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA

                                    frame.setVisible(false);
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Se edito correctamente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);
                                    // JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                    llenarTablaPendientes(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al editar");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);
                                    //  JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                if (dao.modificarProductoConFoto(bean)) {
                                    //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA
                                    frame.setVisible(false);
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Se edito correctamente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);
                                    //JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    llenarTablaPendientes(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al editar");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);
                                    // JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);

                                }
                            }

                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
    }
//=================================== para la pantalla  cambiar estado productos ============================

    public void btncambiarEstadoProductosTodos(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes) {
        String estadoProApartado = "";

        String status[] = {"Pagado entregado", "Pagado NO entregado"};
        Object estadoPro = JOptionPane.showInputDialog(frame, "Estado del producto", "Seleccionar el estado de los productos", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);

        for (int i = 0; i < tablaCambiarEstadoProdcutos.getRowCount(); i++) {
            int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(i, 0) + "");
            Productosapartados beanProApartados = (Productosapartados) new daoProductosApartados().consultaEspecifica(valorId + "");
            beanProApartados.setStatus(estadoPro + "");
            beanProApartados.setFecharegistro(validar.obtenerFechaActual());
            if (new daoProductosApartados().editar(beanProApartados)) {
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("Los estados se cambiaron corectamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);

                // JOptionPane.showMessageDialog(null, "Los estados se cambiaron corectamente");
                frame.dispose();
                principal.controlcambiarEstadoProductosApartados = false;
                //actualizamos la tabla pendietes
                vaciarTabla(tablaPendientes, defaultTablaPendientes);
                llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);
            }
        }

    }

    public void btncambiarEstadoProductosUnoPorUno(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes) {

        if (tablaCambiarEstadoProdcutos.getSelectedRow() == -1) {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String status[] = {"Pagado entregado", "Pagado NO entregado"};
                Object estadoPro = JOptionPane.showInputDialog(frame, "Estado del producto", "Seleccionar el estado de los productos", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);

                int fila = tablaCambiarEstadoProdcutos.getSelectedRow();
                int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 0) + "");
                Productosapartados beanProApartados = (Productosapartados) new daoProductosApartados().consultaEspecifica(valorId + "");
                beanProApartados.setStatus(estadoPro + "");
                beanProApartados.setFecharegistro(validar.obtenerFechaActual());
                if (new daoProductosApartados().editar(beanProApartados)) {
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("El estado del producto  se cambio correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);
                    // JOptionPane.showMessageDialog(null, "El estado del producot  se cambio correctamente");

                    defaulttablaCambiar.removeRow(fila);

                }

            } catch (Exception e) {
            }

        }
        if (tablaCambiarEstadoProdcutos.getRowCount() == 0) {
            frame.dispose();
            principal.controlcambiarEstadoProductosApartados = false;
            //actualizamos la tabla pendietes
            vaciarTabla(tablaPendientes, defaultTablaPendientes);
            llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);
        }

    }

    // metodo para hacer entrega deproductos con estado pagado no entregado
    public void btnEntregarProdcutos(String nombreCliente) {
        Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(nombreCliente);
        //  System.out.println("bean cliente control " + beanCliente.getNombrecompleto());
        if (beanCliente != null) {
            cambiarEstadoProductosApartados.beanCliente2 = beanCliente;
            cambiarEstadoProductosApartados cambiar = new cambiarEstadoProductosApartados();
            cambiar.setVisible(true);
        }
    }
    // metodo para hacer entrega deproductos con estado pagado no entregado

    //metodo para cancelar todos los productos apartados que tiene ese cliente
    /*1.-buscamos  si tiene medidas y fechas los eliminamos
     2.-eliminamos el producto apartado
     3.- eliminamos los pagos
     4.- cambiamos el estado a productos apartados*/
    public void btnCanclearProdcutos(String nombreCliente) {
        Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(nombreCliente);
        if (beanCliente != null) {
            cancelarProductosApartados.beanCliente = beanCliente;
            cancelarProductosApartados cancelar = new cancelarProductosApartados();
            cancelar.setVisible(true);
        }
    }

    //=================== metodo que se ejecutara desde la pantlla cancelar  productos =======================
    public void btnCancelarProdcutosUnoPorUno(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes) {

        if (tablaCambiarEstadoProdcutos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea cancelar el producto?");
                if (respuesta == 0) {
                    int fila = tablaCambiarEstadoProdcutos.getSelectedRow();
                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 0) + "");
                    int valorCantidad = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 4) + "");
                    Productosapartados beanProApar = (Productosapartados) new daoProductosApartados().consultaEspecifica(valorId + "");
                    Set<Medidas> listaMedidas = beanProApar.getMedidases();
                    Set<Fechaspruebas> listaFechas = beanProApar.getFechaspruebases();
                    if (listaMedidas.size() > 0) {
                        for (Medidas listaMedida : listaMedidas) {
                            System.out.println("como tiene medidas eliminaremos las medidas");
                            if (new daoMedidas().eliminar(listaMedida)) {

                            }
                        }

                    }
                    if (listaFechas.size() > 0) {
                        for (Fechaspruebas listaFecha : listaFechas) {
                            System.out.println("como tiene fechas eliminaremos las fechas");
                            if (new daoFechas().eliminar(listaFecha)) {

                            }
                        }

                    }

                    //ahora eliminaremos el   producto apartado
                    if (new daoProductosApartados().eliminar(beanProApar)) {
                        System.out.println("eliminamos el producto apartado");
                        //si  elimina el pro apartado actualizaremos la deuda , el estado y la fecharegistro
                        Set<Deudatotal> listaDeuda = beanProApar.getClientes().getDeudatotals();
                        System.out.println("esto trae la lista " + listaDeuda.size());
                        if (listaDeuda.size() > 0) {
                            for (Deudatotal deudatotal : listaDeuda) {
                                if (deudatotal.getStatus().equals("No pagado")) {
                                    //los pagos no se eliminan por  que  sirven para mostrar la  ganancia del dia
                                    //modificamremos la  deuda total descontandole el precio  del producto
                                    //y regresaremos el producto ala tabla productos con un  update dependiendo la  cantidad
                                    int precioProducto = beanProApar.getProductos().getPrecio();
                                    System.out.println("precio pro " + precioProducto);
                                    int nuevaDeuda = deudatotal.getDeudatotal() - precioProducto;
                                    //llenamos el bean de deudaTotal con la cantidad que el cliente realizo en los pagos
                                    //para que las cuentas cuadren

                                    deudatotal.setDeudatotal(nuevaDeuda);
                                    deudatotal.setFecharegistro(new validarCampos().obtenerFechaActual());
                                    if (new daoDeudaTotal().editar(deudatotal)) {
                                        System.out.println("se modifica la deuda");
                                        System.out.println("fila " + fila);

                                        defaulttablaCambiar.removeRow(fila);
                                        //agregamos el producto cancelado a la tabla producto con su cantidad de tabal
                                        if (new DaoProductos().modificarCantidadYEstadoProducto2(beanProApar.getProductos().getIdproductos(), valorCantidad)) {
                                            System.out.println("se modifico la cantidad");
                                        } else {
                                            System.out.println("errore al modificar cantidad");
                                        }
                                        mensajeExito menExito = new mensajeExito();
                                        mensajeExito.labelMensaje.setText("Se cancelo el producto del cliente");
                                        menExito.setVisible(true);
                                        menExito.setAlwaysOnTop(true);
                                        // JOptionPane.showMessageDialog(null, "Se cancelaron los productos del cliente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                    }
                                    //**********************************

                                    //llenamos el bean de deudaTotal con la cantidad que el cliente realizo en los pagos
                                    //para que las cuentas cuadren
                                    if (tablaCambiarEstadoProdcutos.getRowCount() == 0) {
                                        int sumaPagos = 0;
                                        Set<Pagos> listaPagos = deudatotal.getPagoses();
                                        if (listaPagos.size() > 0) {

                                            for (Pagos listaPago : listaPagos) {
                                                sumaPagos = sumaPagos + listaPago.getAbono();

                                            }
                                        } else {

                                        }
                                        deudatotal.setStatus("Pagado");
                                        deudatotal.setDeudatotal(sumaPagos);
                                        deudatotal.setFecharegistro(new validarCampos().obtenerFechaActual());
                                        if (new daoDeudaTotal().editar(deudatotal)) {
                                            System.out.println("se modifica la deuda000");
                                            System.out.println("fila " + fila);

                                            mensajeExito menExito = new mensajeExito();
                                            mensajeExito.labelMensaje.setText("Se cancelaron los productos del cliente");
                                            menExito.setVisible(true);
                                            menExito.setAlwaysOnTop(true);
                                            // JOptionPane.showMessageDialog(null, "Se cancelaron los productos del cliente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                        }
                                    } else {

                                    }

                                }
                            }

                        } else {

                        }

                    }
                    if (tablaCambiarEstadoProdcutos.getRowCount() == 0) {
                        //aqui actualizamos la tabla pendientes
                        /* para boton eliminar pedido 
                         Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(beanProApar.getClientes().getNombrecompleto());
                         Set<Deudatotal> listaDeuda2 = beanCliente.getDeudatotals();
                         if (listaDeuda2.size() > 0) {

                         if (new daoClientes().eliminar(beanCliente)) {
                         System.out.println("se elimino el cliente");
                         } else {

                         }
                         }
                         */
                        frame.dispose();
                        principal.controlcancelarProductosApartados = false;
                        //actualizamos la tabla pendietes
                        vaciarTabla(tablaPendientes, defaultTablaPendientes);
                        llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);
                    }
                } else {

                }
            } catch (Exception e) {

            }

        }

    }

    //=================== metodo que se ejecutara desde la pantlla cancelar  productos =======================
    public void btnCancelarTodosProdcutos(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes) {

        try {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea cancelar todos los pedidos?");
            if (respuesta == 0) {
                for (int i = 0; i < tablaCambiarEstadoProdcutos.getRowCount(); i++) {
                    int valorIdProApar = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(i, 0) + "");
                    Productosapartados beanProApar = (Productosapartados) new daoProductosApartados().consultaEspecifica(valorIdProApar + "");
                    Set<Medidas> listaMedidas = beanProApar.getMedidases();
                    Set<Fechaspruebas> listaFechas = beanProApar.getFechaspruebases();
                    if (listaMedidas.size() > 0) {
                        for (Medidas listaMedida : listaMedidas) {
                            System.out.println("como tiene medidas eliminaremos las medidas 2" + listaMedida.getIdmedidas());
                            if (new daoMedidas().eliminar(listaMedida)) {

                            }
                        }

                    }
                    if (listaFechas.size() > 0) {
                        for (Fechaspruebas listaFecha : listaFechas) {
                            System.out.println("como tiene fechas eliminaremos las fechas " + listaFecha.getIdfechaspruebas());
                            if (new daoFechas().eliminar(listaFecha)) {

                            }
                        }

                    }
                    //ahora eliminaremos el   producto apartado
                    System.out.println("id  pro eliminar 2 " + beanProApar.getIdproductosapartados());
                    if (new daoProductosApartados().eliminar(beanProApar)) {
                        System.out.println("eliminamos el producto apartado 2");
                        //si  elimina el pro apartado actualizaremos la deuda , el estado y la fecharegistro
                        Set<Deudatotal> listaDeuda = beanProApar.getClientes().getDeudatotals();
                        // System.out.println("esto trae la lista " + listaDeuda.size());
                        if (listaDeuda.size() > 0) {
                            for (Deudatotal deudatotal : listaDeuda) {
                                if (deudatotal.getStatus().equals("No pagado")) {
                                    int sumaPagos = 0;
                                    Set<Pagos> listaPagos = deudatotal.getPagoses();
                                    if (listaPagos.size() > 0) {

                                        for (Pagos listaPago : listaPagos) {
                                            sumaPagos = sumaPagos + listaPago.getAbono();
                                            if (new daoPagos().eliminar(listaPago)) {
                                                System.out.println("se elimino  el pago");
                                            }
                                        }
                                    } else {

                                    }
                                    //llenamos el bean de deudaTotal con la cantidad que el cliente realizo en los pagos
                                    //para que las cuentas cuadren
                                    deudatotal.setStatus("Pagado");
                                    deudatotal.setDeudatotal(sumaPagos);
                                    deudatotal.setFecharegistro(new validarCampos().obtenerFechaActual());
                                    if (new daoDeudaTotal().editar(deudatotal)) {
                                        System.out.println("se modifico la dueda");
                                        /* para boton eliminar pedido
                                         Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(beanProApar.getClientes().getNombrecompleto());
                                         Set<Deudatotal> listaDeuda2 = beanCliente.getDeudatotals();
                                         if (listaDeuda2.size() > 0) {

                                         if (new daoClientes().eliminar(beanCliente)) {
                                         System.out.println("se elimino el cliente");
                                         } else {

                                         }
                                         }
                                         */

                                        JOptionPane.showMessageDialog(null, "Se cancelaron todos los productos del cliente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                        //aqui actualizamos la tabla pendientes
                                        frame.dispose();
                                        principal.controlcancelarProductosApartados = false;
                                        //actualizamos la tabla pendietes
                                        vaciarTabla(tablaPendientes, defaultTablaPendientes);
                                        llenarTablaPendientes(tablaPendientes, defaultTablaPendientes);

                                    }
                                }
                            }

                        } else {

                        }

                    }

                }

            } else {

            }
        } catch (Exception e) {
        }

    }
    //estos clientes son los que tienen deuda  y pomner un estatus si esta persona ya paso un año su deuda

    public void llenarTablaClientes(JTable tbCliente, DefaultTableModel tablaClientesModel,
            JTable tablaPen, DefaultTableModel modelPendientes, JTable tablaMe, DefaultTableModel modelMe, JTextArea txtDesc,
            JLabel labelFoto) {
        //limpimaos campos
        if (tablaPen.getRowCount() > 0) {
            vaciarTabla(tablaPen, modelPendientes);
        }
        if (tablaMe.getRowCount() > 0) {
            vaciarTabla(tablaMe, modelMe);
        }
        txtDesc.setText("");
        labelFoto.setText("");
        labelFoto.setIcon(null);
        List<Clientes> lista = daoApartados.consultarClienteConDeuda();

        if (lista.size() > 0) {
            vaciarTabla(tbCliente, tablaClientesModel);
            for (int i = 0; i < lista.size(); i++) {
                tablaClientesModel.addRow(new Object[]{lista.get(i).getIdclientes(), lista.get(i).getNombrecompleto().toUpperCase(), lista.get(i).getTelefono()});

            }
        } else {

        }

    }
//metodos nuevos cesar 2019

    public void btncambiarEstadoProductosUnoPorUno2(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes, String idCliente) {

        if (tablaCambiarEstadoProdcutos.getSelectedRow() == -1) {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String status[] = {"Pagado entregado", "Pagado NO entregado"};
                Object estadoPro = JOptionPane.showInputDialog(frame, "Estado del producto", "Seleccionar el estado de los productos", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
                if (estadoPro != null) {
                    int fila = tablaCambiarEstadoProdcutos.getSelectedRow();
                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 0) + "");

                    if (new daoProductosApartados().editarSoloEstatusProApartadpos(estadoPro + "", valorId + "")) {
                        mensajeExito menExito = new mensajeExito();
                        mensajeExito.labelMensaje.setText("El estado del producto  se cambio correctamente");
                        menExito.setVisible(true);
                        menExito.setAlwaysOnTop(true);
                        // JOptionPane.showMessageDialog(null, "El estado del producot  se cambio correctamente");

                        defaulttablaCambiar.removeRow(fila);
                        //le cambiamos el estado a los de la tabla de propendientes
                        if (String.valueOf(estadoPro).equalsIgnoreCase("pagado no entregado")) {
                            //se cambiara el status por medio del id

                            for (int i = 0; i < productosPendientes.tablaPendientes.getRowCount(); i++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(i, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.setValueAt("PAGADO NO ENTREGADO", i, 2);
                                    break;
                                } else {

                                }
                            }

                        } else if (String.valueOf(estadoPro).equalsIgnoreCase("pagado entregado")) {
                            //se elminara el status por medio del id

                            for (int i = 0; i < productosPendientes.tablaPendientes.getRowCount(); i++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(i, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.removeRow(i);
                                    break;
                                } else {

                                }
                            }

                        } else {

                        }
                        if (productosPendientes.tablaPendientes.getRowCount() > 0) {

                        } else {
                            //vemos si todos se  entregaron el eliminamos a nuestro cliente de la tabla
                            for (int i = 0; i < productosPendientes.tablaClientes.getRowCount(); i++) {
                                String id = productosPendientes.tablaClientes.getValueAt(i, 0) + "";
                                if (id.equalsIgnoreCase(idCliente)) {
                                    productosPendientes.tablaClientes.removeRow(i);
                                    break;
                                }
                            }
                        }

                    }
                } else {
                    mensajeAdvertencia m = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("Selecciona un estado");
                    m.setVisible(true);
                    m.setAlwaysOnTop(true);
                }

            } catch (Exception e) {
                System.out.println("error elimianr de uno en uno");
            }

        }
        if (tablaCambiarEstadoProdcutos.getRowCount() == 0) {
            frame.dispose();
            principal.controlcambiarEstadoProductosApartados = false;

        }

    }

    public void btncambiarEstadoProductosTodo2(JFrame frame, JTable tablaCambiarEstadoProdcutos,
            DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes, String idCliente) {

        try {
            String status[] = {"Pagado entregado", "Pagado NO entregado"};
            Object estadoPro = JOptionPane.showInputDialog(frame, "Estado del producto", "Seleccionar el estado de los productos", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
            if (estadoPro != null) {
                int cantFilas = tablaCambiarEstadoProdcutos.getRowCount();
                for (int i = 0; i < cantFilas; i++) {

                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(i, 0) + "");

                    if (new daoProductosApartados().editarSoloEstatusProApartadpos(estadoPro + "", valorId + "")) {
                        System.out.println("entro  " + i);
                        // JOptionPane.showMessageDialog(null, "El estado del producot  se cambio correctamente");
                        // defaulttablaCambiar.removeRow(0);
                        //le cambiamos el estado a los de la tabla de propendientes
                        //le cambiamos el estado a los de la tabla de propendientes
                        if (String.valueOf(estadoPro).equalsIgnoreCase("pagado no entregado")) {
                            //se cambiara el status por medio del id

                            for (int k = 0; k < productosPendientes.tablaPendientes.getRowCount(); k++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(k, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.setValueAt("PAGADO NO ENTREGADO", k, 2);
                                    break;
                                } else {

                                }
                            }

                        } else if (String.valueOf(estadoPro).equalsIgnoreCase("pagado entregado")) {
                            //se elminara el status por medio del id

                            for (int l = 0; l < productosPendientes.tablaPendientes.getRowCount(); l++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(l, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.removeRow(l);
                                    break;
                                } else {

                                }
                            }

                        } else {

                        }
                        if (productosPendientes.tablaPendientes.getRowCount() > 0) {

                        } else {
                            //vemos si todos se  entregaron el eliminamos a nuestro cliente de la tabla
                            for (int j = 0; j < productosPendientes.tablaClientes.getRowCount(); j++) {
                                String id = productosPendientes.tablaClientes.getValueAt(j, 0) + "";
                                if (id.equalsIgnoreCase(idCliente)) {
                                    productosPendientes.tablaClientes.removeRow(j);
                                    break;
                                }
                            }
                        }

                    } else {

                    }

                }
                //termina el for muestra el mns
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("El estado del producto  se cambio correctamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                frame.dispose();
                principal.controlcambiarEstadoProductosApartados = false;

            } else {
                mensajeAdvertencia m = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Selecciona un estado");
                m.setVisible(true);
                m.setAlwaysOnTop(true);
            }

        } catch (Exception e) {
            System.out.println("error elimianr de todos  " + e.getMessage());
        }

    }

    public void btncambiarEstadoProductosPagadoNoEntregadoUnoPorUno2019(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes, String idCliente) {

        if (tablaCambiarEstadoProdcutos.getSelectedRow() == -1) {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona una fila de la tabla");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                Object estadoPro = "Pagado entregado";
                if (estadoPro != null) {
                    int fila = tablaCambiarEstadoProdcutos.getSelectedRow();
                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 0) + "");

                    if (new daoProductosApartados().editarSoloEstatusProApartadpos(estadoPro + "", valorId + "")) {
                        mensajeExito menExito = new mensajeExito();
                        mensajeExito.labelMensaje.setText("El estado del producto  se cambio correctamente");
                        menExito.setVisible(true);
                        menExito.setAlwaysOnTop(true);
                        // JOptionPane.showMessageDialog(null, "El estado del producot  se cambio correctamente");

                        defaulttablaCambiar.removeRow(fila);
                        //le cambiamos el estado a los de la tabla de propendientes
                        if (String.valueOf(estadoPro).equalsIgnoreCase("pagado no entregado")) {
                            //se cambiara el status por medio del id

                            for (int i = 0; i < productosPendientes.tablaPendientes.getRowCount(); i++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(i, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.setValueAt("PAGADO NO ENTREGADO", i, 2);
                                    break;
                                } else {

                                }
                            }

                        } else if (String.valueOf(estadoPro).equalsIgnoreCase("pagado entregado")) {
                            //se elminara el status por medio del id

                            for (int i = 0; i < productosPendientes.tablaPendientes.getRowCount(); i++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(i, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.removeRow(i);

                                    break;
                                } else {

                                }
                            }

                        } else {

                        }
                        if (productosPendientes.tablaPendientes.getRowCount() > 0) {

                        } else {
                            //vemos si todos se  entregaron el eliminamos a nuestro cliente de la tabla
                            for (int i = 0; i < productosPendientes.tablaClientes.getRowCount(); i++) {
                                String id = productosPendientes.tablaClientes.getValueAt(i, 0) + "";
                                if (id.equalsIgnoreCase(idCliente)) {
                                    productosPendientes.tablaClientes.removeRow(i);

                                    mensajeExito.labelMensaje.setText("Se entregaron los productos al cliente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);

                                    break;
                                }
                            }
                        }

                    }
                } else {
                    mensajeAdvertencia m = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("Selecciona un estado");
                    m.setVisible(true);
                    m.setAlwaysOnTop(true);
                }

            } catch (Exception e) {
                System.out.println("error btncambiarEstadoProductosPagadoNoEntregadoUnoPorUno  " + e.getMessage());
            }

        }
        if (tablaCambiarEstadoProdcutos.getRowCount() == 0) {
            frame.dispose();
            principal.controlcambiarEstadoProductosEntregadosNoPagados2019 = false;

        }

    }

    public void btncambiarEstadoProductosPagadoNoEntregadoTodos2019(JFrame frame, JTable tablaCambiarEstadoProdcutos,
            DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes, String idCliente) {

        try {

            Object estadoPro = "Pagado entregado";
            if (estadoPro != null) {
                int cantFilas = tablaCambiarEstadoProdcutos.getRowCount();
                for (int i = 0; i < cantFilas; i++) {

                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(i, 0) + "");

                    if (new daoProductosApartados().editarSoloEstatusProApartadpos(estadoPro + "", valorId + "")) {
                        System.out.println("entro  " + i);
                        // JOptionPane.showMessageDialog(null, "El estado del producot  se cambio correctamente");
                        // defaulttablaCambiar.removeRow(0);
                        //le cambiamos el estado a los de la tabla de propendientes
                        //le cambiamos el estado a los de la tabla de propendientes
                        if (String.valueOf(estadoPro).equalsIgnoreCase("pagado no entregado")) {
                            //se cambiara el status por medio del id

                            for (int k = 0; k < productosPendientes.tablaPendientes.getRowCount(); k++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(k, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.setValueAt("PAGADO NO ENTREGADO", k, 2);
                                    break;
                                } else {

                                }
                            }

                        } else if (String.valueOf(estadoPro).equalsIgnoreCase("pagado entregado")) {
                            //se elminara el status por medio del id

                            for (int l = 0; l < productosPendientes.tablaPendientes.getRowCount(); l++) {
                                int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(l, 0) + "");
                                if (id == valorId) {
                                    productosPendientes.tablaPendientes.removeRow(l);
                                    break;
                                } else {

                                }
                            }

                        } else {

                        }
                        if (productosPendientes.tablaPendientes.getRowCount() > 0) {

                        } else {
                            //vemos si todos se  entregaron el eliminamos a nuestro cliente de la tabla
                            for (int j = 0; j < productosPendientes.tablaClientes.getRowCount(); j++) {
                                String id = productosPendientes.tablaClientes.getValueAt(j, 0) + "";
                                if (id.equalsIgnoreCase(idCliente)) {
                                    productosPendientes.tablaClientes.removeRow(j);

                                    break;
                                }
                            }
                        }

                    } else {

                    }

                }

                //termina el for muestra el mns
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("Se entregaron los productos al cliente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                frame.dispose();
                principal.controlcambiarEstadoProductosEntregadosNoPagados2019 = false;

            } else {
                mensajeAdvertencia m = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Selecciona un estado");
                m.setVisible(true);
                m.setAlwaysOnTop(true);
            }

        } catch (Exception e) {
            System.out.println("error btncambiarEstadoProductosPagadoNoEntregadoTodos2019 de todos  " + e.getMessage());
        }

    }

    public void btnCancelarProdcutosUnoPorUno2019(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes, String idCliente) {

        if (tablaCambiarEstadoProdcutos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea cancelar el producto?");
                if (respuesta == 0) {
                    //eliminar de proApartados,eliminar si tiene medidas si tiene fechas,regresar el producto 
                    //a la tb productos restamos la deuda total el precio del producto
                    int fila = tablaCambiarEstadoProdcutos.getSelectedRow();
                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 0) + "");
                    int valorCantidad = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(fila, 3) + "");

                    if (tablaCambiarEstadoProdcutos.getRowCount() > 0) {

                        //metodo  que descuenta el precio del producto a la deudatotal y  regresa el  productos a producto
                        if (tablaCambiarEstadoProdcutos.getRowCount() == 1) {
                            if (daoApartados.editarDeudaTotaYEstatusl2019(valorId + "", valorCantidad)) {
                                System.out.println("bien hecho");
                                //editamos el precio del producto y lo restamos a la deuda total
                                //metodo  elimina medidas,fechas y  pro aparatado
                                if (daoApartados.btnCancelarProdcutosUnoPorUno2019(valorId + "")) {
                                    mensajeExito m = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Cancelado con exito");
                                    m.setVisible(true);
                                    m.setAlwaysOnTop(true);

                                    for (int i = 0; i < productosPendientes.tablaPendientes.getRowCount(); i++) {
                                        int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(i, 0) + "");
                                        if (id == valorId) {
                                            productosPendientes.tablaPendientes.removeRow(i);
                                            break;
                                        } else {

                                        }

                                        //eliminamos el id Cliente
                                        for (int k = 0; k < productosPendientes.tablaClientes.getRowCount(); k++) {
                                            String idc = productosPendientes.tablaClientes.getValueAt(k, 0) + "";
                                            if (idc.equalsIgnoreCase(idCliente)) {
                                                productosPendientes.tablaClientes.removeRow(k);
                                                break;
                                            }
                                        }
                                    }//termina el for
                                    defaulttablaCambiar.removeRow(fila);
                                    frame.dispose();
                                    principal.controlcancelarProductosApartados = false;

                                }

                            } else {
                                System.out.println("mal");
                            }
                        } else {
                            if (daoApartados.editarDeudaTotal2019(valorId + "", valorCantidad)) {
                                System.out.println("bien hecho");
                                //editamos el precio del producto y lo restamos a la deuda total
                                //metodo  que descuenta el precio del producto a la deudatotal y  regresa el  productos a producto
                                if (daoApartados.btnCancelarProdcutosUnoPorUno2019(valorId + "")) {
                                    mensajeExito m = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Cancelado con exito");
                                    m.setVisible(true);
                                    m.setAlwaysOnTop(true);
                                    for (int i = 0; i < productosPendientes.tablaPendientes.getRowCount(); i++) {
                                        int id = Integer.parseInt(productosPendientes.tablaPendientes.getValueAt(i, 0) + "");
                                        if (id == valorId) {
                                            productosPendientes.tablaPendientes.removeRow(i);
                                            break;
                                        } else {

                                        }
                                    }

                                    defaulttablaCambiar.removeRow(fila);
                                }

                            } else {
                                System.out.println("mal");
                            }
                        }

                    } else {

                    }

                } else {

                }
            } catch (Exception e) {
                System.out.println("error en  controlProductosPendientes btnCancelarProdcutosUnoPorUno2019 " + e.getMessage());
            }

        }

    }

    public void btnCancelarProdcutosTodos2019(JFrame frame, JTable tablaCambiarEstadoProdcutos, DefaultTableModel defaulttablaCambiar,
            JTable tablaPendientes, DefaultTableModel defaultTablaPendientes, String idCliente) {

        try {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea cancelar todos los productos?");
            if (respuesta == 0) {
                //eliminar de proApartados,eliminar si tiene medidas si tiene fechas,regresar el producto 
                //a la tb productos restamos la deuda total el precio del producto
                for (int i = 0; i < tablaCambiarEstadoProdcutos.getRowCount(); i++) {
                    int valorId = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(i, 0) + "");
                    int valorCantidad = Integer.parseInt(tablaCambiarEstadoProdcutos.getValueAt(i, 3) + "");

                    //metodo  que descuenta el precio del producto a la deudatotal y  regresa el  productos a producto
                    if (daoApartados.editarDeudaTotaYEstatusl2019(valorId + "", valorCantidad)) {

                        //editamos el precio del producto y lo restamos a la deuda total
                        //metodo  elimina medidas,fechas y  pro aparatado
                        if (daoApartados.btnCancelarProdcutosUnoPorUno2019(valorId + "")) {

                            //defaulttablaCambiar.removeRow(i);
                        }

                    } else {
                        System.out.println("mal");
                    }

                }
                mensajeExito m = new mensajeExito();
                mensajeExito.labelMensaje.setText("Cancelados con exito");
                m.setVisible(true);
                m.setAlwaysOnTop(true);

                frame.dispose();
                principal.controlcancelarProductosApartados = false;
                vaciarTabla(productosPendientes.jTable4, productosPendientes.tablaPendientes);
                //eliminamos el id Cliente
                for (int i = 0; i < productosPendientes.tablaClientes.getRowCount(); i++) {
                    String id = productosPendientes.tablaClientes.getValueAt(i, 0) + "";
                    if (id.equalsIgnoreCase(idCliente)) {
                        productosPendientes.tablaClientes.removeRow(i);
                        break;
                    }
                }
            } else {

            }
        } catch (Exception e) {
            System.out.println("error en  controlProductosPendientes btnCancelarProdcutosTodos2019 " + e.getMessage());
        }

    }

    public void editarProductos2019(JTextField id, JTextArea descripcion,
            JLabel foto, File file, JFrame frame, String idProA) {
        String desc = descripcion.getText().toString();
        String foto1 = foto.getText().toString();

        if (descripcion.getText().toString().isEmpty()) {
            desc = "SIN DESCRIPCIÓN";

            if (foto.getIcon() == null) {
                file = null;
            } else {

            }
        } else {

        }
        //SI TODOS LOS CAMPOS FUERON COMPLETADOS ENTONCES HACEMOS EL REGISTRO
        Productos bean = new Productos();

        bean.setDescripcion(desc);
        bean.setIdproductos(Integer.parseInt(id.getText()));

        bean.setFotoString(String.valueOf(file));
        //VALIDAMOS SI ES UNA IMAGEN DE LA BD O UNA IMAGEN NUEVA
        System.out.println("bean " + bean.getDescripcion() + bean.getFotoString());
        if (bean.getFotoString().equals("null")) {
            //MANDAMOS AMODIFICAR EL PRODUCTO
            if (daoApartados.modificarProductoFotoActual2019(bean, idProA)) {
                //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA

                principal.controleditarProductoPendientes2019 = false;
                frame.dispose();
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("Se edito correctamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                productosPendientes.txtAreaDetalleProductoPendiente.setText(descripcion.getText().toUpperCase());
                // JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

            } else {
                mensajeError menError = new mensajeError();
                mensajeError.labelMensaje.setText("Error al editar ");
                menError.setVisible(true);
                menError.setAlwaysOnTop(true);
                //  JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (daoApartados.modificarProductoFotoNueva2019(bean, idProA)) {
                //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA
                principal.controleditarProductoPendientes2019 = false;
                frame.dispose();
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("Se edito correctamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                productosPendientes.txtAreaDetalleProductoPendiente.setText(descripcion.getText().toUpperCase());

            } else {
                mensajeError menError = new mensajeError();
                mensajeError.labelMensaje.setText("Error al editar ");
                menError.setVisible(true);
                menError.setAlwaysOnTop(true);
                //  JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    public void cerrarVentana() {
        if (principal.controlverPagos == false) {

        } else {
            principal.controlverPagos = false;
            principal.frameverPagos.dispose();
        }

        if (principal.controlcambiarEstadoProductosEntregadosNoPagados2019 == false) {

        } else {
            principal.controlcambiarEstadoProductosEntregadosNoPagados2019 = false;
            principal.framecambiarEstadoProductosEntregadosNoPagados2019.dispose();
        }
    }
}
