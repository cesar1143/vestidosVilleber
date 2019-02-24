/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ModeloProductos.productos;
import NuevasPantallas.principal;
import beans.Fechaspruebas;
import beans.Medidas;
import beans.Productos;
import beans.Productosapartados;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import modelo.daoFechas;
import modelo.daoMedidas;
import modelo.daoProductos;
import modelo.daoProductosApartados;
import modelo.daocontrolProductosApartados;
import pantallas.editarProducto;
import pantallas.editarProducto2;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlProductosAgotados {

    daoProductos dao = new daoProductos();
    validarCampos validar = new validarCampos();
    Productos beanGlobal = new Productos();
    daocontrolProductosApartados daoApartados = new daocontrolProductosApartados();

    public void eliminar(String id, JTable tabla, DefaultTableModel defaultTabla) {

        try {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro de eliminar el prodcuto ?");
            if (respuesta == 0) {
                List<Productosapartados> lista = new ArrayList<Productosapartados>();
                //HACEMOS CONSULTA ESPECIFICA EN PRODUCTOS BUSCANDO EL ID ENVIADO POR EL USUARIO
                Productos bean = (Productos) dao.consultaEspecifica(id);

                //LA CONSULTA ANTERIRO POR DEFAULT YA ME TRARE UNA LISTA DE MI TABLA PRO APARTADOS
                Set<Productosapartados> litsaProApartado = bean.getProductosapartadoses();

                if (litsaProApartado.size() > 0) {
                   // System.out.println("entro aqui")111111111111111111111111111111;
                    //SI ES IGUAL A 0 QUIERE DECIR QUE ESE PRODUCTO NO ESTA REGISTRADO EN APARTADOS
                    //Y SE ELIMINA SIN PROBLEMA ALGUNO
                    for (Productosapartados productosapartados : litsaProApartado) {
                       // System.out.println("entro tanatas veces " + productosapartados.getIdproductosapartados());
                        if (productosapartados.getStatus().equals("Pagado entregado")) {
                         //   System.out.println("entro aqui");
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
                                    llenarTabalProAgotados(tabla, defaultTabla);

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
                            // JOptionPane.showMessageDialog(null, "No se puede eliminar," + "\n algún cliente lo tiene apartado", "Advertencia", JOptionPane.WARNING_MESSAGE);

                        }
                    }

                } else {
                    if (dao.eliminar(bean)) {

                        mensajeExito menExito = new mensajeExito();
                        mensajeExito.labelMensaje.setText("Se elimino correctamente");
                        menExito.setVisible(true);
                        menExito.setAlwaysOnTop(true);
                        //JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        llenarTabalProAgotados(tabla, defaultTabla);
                    } else {
                        mensajeError menError = new mensajeError();
                        mensajeError.labelMensaje.setText("Error al eliminar");
                        menError.setVisible(true);
                        menError.setAlwaysOnTop(true);
                        //  JOptionPane.showMessageDialog(null, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                }
            } else {

            }
        } catch (Exception e) {
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
                            //VALIDAMOS SI EL PRODUCTO YA ESTA REGISTRADO
                            //SI ES TRUE MOSTRAMOS MENSAJE DE QUE YA ESTA REGISTRADO

                            if (validarProductoExisteten(clave.getText())) {
                                int fila = tabla.getSelectedRow();
                                String valorClave = tabla.getValueAt(fila, 1) + "";
                               
                                if (!beanGlobal.getClave().equals(valorClave)) {
                                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                    mensajeAdvertencia.labelMensaje.setText("El codigo ya esta registrado");
                                    menAdvertencia.setVisible(true);
                                    menAdvertencia.setAlwaysOnTop(true);
                                    // JOptionPane.showMessageDialog(null, "El codigo ya esta registrado");
                                } else {
                                    //VALIDAMOS SI ES UNA IMAGEN DE LA BD O UNA IMAGEN NUEVA

                                    if (bean.getFotoString().equals("null")) {
                                        //MANDAMOS AMODIFICAR EL PRODUCTO
                                        if (dao.modificarProductoFotoActual(bean)) {
                                            //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA

                                            frame.dispose();
                                            principal.controleditarProducto2 = false;
                                            mensajeExito menExito = new mensajeExito();
                                            mensajeExito.labelMensaje.setText("Se edito correctamente");
                                            menExito.setVisible(true);
                                            menExito.setAlwaysOnTop(true);
                                            //JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                            llenarTabalProAgotados(tabla, defaultTabla);

                                        } else {
                                            mensajeError menError = new mensajeError();
                                            mensajeError.labelMensaje.setText("Error al editar");
                                            menError.setVisible(true);
                                            menError.setAlwaysOnTop(true);

                                            //JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        if (dao.modificarProductoConFoto(bean)) {
                                            //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA
                                            frame.dispose();
                                            principal.controleditarProducto2 = false;
                                            mensajeExito menExito = new mensajeExito();
                                            mensajeExito.labelMensaje.setText("Se edito correctamente");
                                            menExito.setVisible(true);
                                            menExito.setAlwaysOnTop(true);
                                            //  JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                            llenarTabalProAgotados(tabla, defaultTabla);

                                        } else {
                                            mensajeError menError = new mensajeError();
                                            mensajeError.labelMensaje.setText("Error al editar");
                                            menError.setVisible(true);
                                            menError.setAlwaysOnTop(true);
                                            //JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);

                                        }
                                    }
                                }

                            } else {
                                //VALIDAMOS SI ES UNA IMAGEN DE LA BD O UNA IMAGEN NUEVA

                                if (bean.getFotoString().equals("null")) {
                                    //MANDAMOS AMODIFICAR EL PRODUCTO
                                    if (dao.modificarProductoFotoActual(bean)) {
                                        //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA

                                        frame.dispose();
                                        principal.controleditarProducto2 = false;
                                        mensajeExito menExito = new mensajeExito();
                                        mensajeExito.labelMensaje.setText("Se edito correctamente");
                                        menExito.setVisible(true);
                                        menExito.setAlwaysOnTop(true);
                                        //  JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                        llenarTabalProAgotados(tabla, defaultTabla);

                                    } else {
                                        mensajeError menError = new mensajeError();
                                        mensajeError.labelMensaje.setText("Error al editar");
                                        menError.setVisible(true);
                                        menError.setAlwaysOnTop(true);
                                        //JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    if (dao.modificarProductoConFoto(bean)) {
                                        //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA
                                        frame.dispose();
                                        principal.controleditarProducto2 = false;
                                        mensajeExito menExito = new mensajeExito();
                                        mensajeExito.labelMensaje.setText("Se edito correctamente");
                                        menExito.setVisible(true);
                                        menExito.setAlwaysOnTop(true);
                                        //JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                        llenarTabalProAgotados(tabla, defaultTabla);

                                    } else {
                                        mensajeError menError = new mensajeError();
                                        mensajeError.labelMensaje.setText("Error al editar");
                                        menError.setVisible(true);
                                        menError.setAlwaysOnTop(true);
                                        //JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);

                                    }
                                }
                                System.out.println("el producto no existe con esa clava");
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

    public void consultaEspecificaParaModificar(int id) {

        Productos beanPro = (Productos) dao.consultaEspecifica(id + "");

        //SI RETORNA QUIERE DECIR QUE LA CLAVE YA ESTA REGISTRADO EN LA BD
        //encontramos nuestro id en lalista y obtenmos el bean 
        //y lo enviamos a la pantalla editar pro para llenar los campos
        editarProducto2.bean = beanPro;

    }

    public void llenarTabalProAgotados(JTable tablaProAgotados, DefaultTableModel tablaAgostado) {
        //here
        List<productos> listaPro = new ArrayList< productos>();
        listaPro = dao.consultarTodosPricipalAgotados();
        if (listaPro.size() > 0) {
            vaciarTabla(tablaProAgotados, tablaAgostado);
            for (int i = 0; i < listaPro.size(); i++) {

                tablaAgostado.addRow(new Object[]{listaPro.get(i).getIdProductos(), listaPro.get(i).getClave(), listaPro.get(i).getNombre(),
                    listaPro.get(i).getDescripcion(), listaPro.get(i).getCantidad(), listaPro.get(i).getPrecio()});
            }
        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("No hay ningun registro");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
           // JOptionPane.showMessageDialog(null, "No hay ningun registro", "Inforamción", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }
    //ESTE METODO VALIDA SI LA CLAVE INGRESADA YA ESTA EN LA BD, SE MANDA A LLAMAR EN METODO REGISTRAR    

    public boolean validarProductoExisteten(String clave) {
        boolean ban = false;
        Productos bean = (Productos) dao.consultaEspecificaPorClave(clave);

        //SI RETORNA QUIERE DECIR QUE LA CLAVE YA ESTA REGISTRADO EN LA BD
        if (bean != null) {

            ban = true;
            beanGlobal = bean;

        } else {

        }
        return ban;

    }
}
