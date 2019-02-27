/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ModeloProductos.DaoProductos;
import ModeloProductos.DetallesProductos;
import ModeloProductos.productos;
import NuevasPantallas.principal;
import beans.Fechaspruebas;
import beans.Medidas;
import beans.Productos;
import beans.Productosapartados;

import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import mensajes.mensajeAdvertencia;
import mensajes.mensajeError;
import mensajes.mensajeExito;
import modelo.daoFechas;
import modelo.daoMedidas;
import modelo.daoProductos;
import modelo.daoProductosApartados;
import modelo.daocontrolProductosApartados;
import pantallas.detallesVenderProducto;
import pantallas.editarProducto;
import pantallas.editarProducto3;

import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlProductos {

    daoProductos dao = new daoProductos();
    validarCampos validar = new validarCampos();
    Productos beanGlobal = new Productos();
    daocontrolProductosApartados daoApartados = new daocontrolProductosApartados();

    public void registrar(JTextField clave, JTextField nombre, JTextField precio, JTextField color, String tipo, JTextField cantidad, JTextArea descripcion, JLabel foto, File file, JTable tabla, DefaultTableModel defaultTabla) {
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
                            //VALIDAMOS LOS CAMPOS QUE SON NECESARIOS INGRESAR Y LOS QUE NO PUES LES ASIGNAMOS UN VALOR PARA ENVIAR ALA BD
                            //SI TODOS LOS CAMPOS FUERON COMPLETADOS ENTONCES HACEMOS EL REGISTRO
                            Productos bean = new Productos();
                            bean.setClave(clave.getText());
                            //System.out.println("clave bean " + bean.getClave());
                            bean.setNombre(nombre.getText());
                            bean.setPrecio(Integer.parseInt(precio.getText()));
                            bean.setColor(color.getText());
                            bean.setTipo(tipo);
                            bean.setCantidad(Integer.parseInt(cantidad.getText()));
                            bean.setDescripcion(desc);

                            bean.setFotoString(String.valueOf(file));
                            //VALIDAMOS SI EL PRODUCTO YA ESTA REGISTRADO
                            //SI ES TRUE MOSTRAMOS MENSAJE DE QUE YA ESTA REGISTRADO
                            if (validarProductoExisteten(clave.getText())) {
                                validar.limpiarCampos(clave);
                                validar.limpiarCampos(nombre);
                                validar.limpiarCampos(precio);
                                validar.limpiarCampos(color);
                                validar.limpiarCampos(cantidad);
                                descripcion.setText("");
                                foto.setIcon(null);
                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                mensajeAdvertencia.labelMensaje.setText("El producto ya esta registrado");
                                menAdvertencia.setVisible(true);
                                menAdvertencia.setAlwaysOnTop(true);
                                //JOptionPane.showMessageDialog(null, "El producto ya esta registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                editarProducto.bean = beanGlobal;
                                editarProducto editarPro = new editarProducto();

                                editarPro.setVisible(true);
                            } else {
                                //EN CASO DE SER FALSE  ENTONCES REGISTRAMOS EL PRODUCTO
                                if (dao.registrarSQL(bean)) {
                                    //Actualizamos la tabla prductos para mostrar los nuevos registros

                                    validar.limpiarCampos(clave);
                                    validar.limpiarCampos(nombre);
                                    validar.limpiarCampos(precio);
                                    validar.limpiarCampos(color);
                                    validar.limpiarCampos(cantidad);
                                    descripcion.setText("");
                                    foto.setIcon(null);
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Registro exitoso");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);

                                    //JOptionPane.showMessageDialog(null, "Registro exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    llenarTabla(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al registrar");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);
                                    //JOptionPane.showMessageDialog(null, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void registrar2(JTextField clave, JTextField nombre, JTextField precio, JTextField color, String tipo, JTextField cantidad, JTextArea descripcion, JLabel foto, File file, JTable tabla, DefaultTableModel defaultTabla) {
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
                            //VALIDAMOS LOS CAMPOS QUE SON NECESARIOS INGRESAR Y LOS QUE NO PUES LES ASIGNAMOS UN VALOR PARA ENVIAR ALA BD
                            //SI TODOS LOS CAMPOS FUERON COMPLETADOS ENTONCES HACEMOS EL REGISTRO
                            Productos bean = new Productos();
                            bean.setClave(clave.getText());
                            System.out.println("clave bean " + bean.getClave());
                            bean.setNombre(nombre.getText());
                            bean.setPrecio(Integer.parseInt(precio.getText()));
                            bean.setColor(color.getText());
                            bean.setTipo(tipo);
                            bean.setCantidad(Integer.parseInt(cantidad.getText()));
                            bean.setDescripcion(desc);

                            bean.setFotoString(String.valueOf(file));
                            //VALIDAMOS SI EL PRODUCTO YA ESTA REGISTRADO
                            //SI ES TRUE MOSTRAMOS MENSAJE DE QUE YA ESTA REGISTRADO
                            if (validarProductoExisteten(clave.getText())) {
                                validar.limpiarCampos(clave);
                                validar.limpiarCampos(nombre);
                                validar.limpiarCampos(precio);
                                validar.limpiarCampos(color);
                                validar.limpiarCampos(cantidad);
                                descripcion.setText("");
                                foto.setIcon(null);
                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                mensajeAdvertencia.labelMensaje.setText("El producto ya esta registrado");
                                menAdvertencia.setVisible(true);
                                menAdvertencia.setAlwaysOnTop(true);
                                // JOptionPane.showMessageDialog(null, "El producto ya esta registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);

                                editarProducto3.bean = beanGlobal;
                                //nos sirve para el usuario que no se mayra no podra editar solo mostraremos quesi existe
                                editarProducto3 editarPro = new editarProducto3();

                                editarPro.setVisible(true);
                            } else {
                                //EN CASO DE SER FALSE  ENTONCES REGISTRAMOS EL PRODUCTO
                                if (dao.registrarSQL2(bean)) {
                                    //Actualizamos la tabla prductos para mostrar los nuevos registros

                                    validar.limpiarCampos(clave);
                                    validar.limpiarCampos(nombre);
                                    validar.limpiarCampos(precio);
                                    validar.limpiarCampos(color);
                                    validar.limpiarCampos(cantidad);
                                    descripcion.setText("");
                                    foto.setIcon(null);
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Registro exitoso");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);
                                    //JOptionPane.showMessageDialog(null, "Registro exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    llenarTabla(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al registrar");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);

                                    // JOptionPane.showMessageDialog(null, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
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

    public List<Productos> consultarTodos() {
        List<Productos> lista = new ArrayList<Productos>();
        for (int i = 0; i < dao.consultarTodos().size(); i++) {
            Productos bean = (Productos) dao.consultarTodos().get(i);
            lista.add(bean);

        }
        return lista;
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

                                    principal.controleditarProducto = false;
                                    frame.dispose();
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Se edito correctamente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);

                                    // JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    llenarTabla(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al editar ");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);
                                    //  JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                if (dao.modificarProductoConFoto(bean)) {
                                    //SI SE MODIFICA EL PRODUCTO ACTUALIZAMOS LA TABLA Y CERRAMOS LA PANTALLA
                                    principal.controleditarProducto = false;
                                    frame.dispose();
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("Se edito correctamente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);
                                    //JOptionPane.showMessageDialog(null, "Se edito correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    llenarTabla(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al editar ");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);
                                    //  JOptionPane.showMessageDialog(null, "Error al editar ", "Error", JOptionPane.ERROR_MESSAGE);

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

    public void eliminar(String id, JTable tabla, DefaultTableModel defaultTabla) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro de eliminar el producto seleccionado?");

        try {
            if (respuesta == 0) {
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
                            // System.out.println("entro aqui")11111111;
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
                                    // JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    llenarTabla(tabla, defaultTabla);

                                } else {
                                    mensajeError menError = new mensajeError();
                                    mensajeError.labelMensaje.setText("Error al eliminar");
                                    menError.setVisible(true);
                                    menError.setAlwaysOnTop(true);
                                    //  JOptionPane.showMessageDialog(null, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);

                                }
                            }

                        } else {
                            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                            mensajeAdvertencia.labelMensaje.setText("No se puede eliminar," + "\n algún cliente lo tiene apartado");
                            menAdvertencia.setVisible(true);
                            menAdvertencia.setAlwaysOnTop(true);
                            // JOptionPane.showMessageDialog(null, "No se puede eliminar," + "\n algún cliente lo tiene apartado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }

                } else {
                    if (dao.eliminar(bean)) {
                        mensajeExito menExito = new mensajeExito();
                        mensajeExito.labelMensaje.setText("Se elimino correctamente");
                        menExito.setVisible(true);
                        menExito.setAlwaysOnTop(true);

                        //  JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        llenarTabla(tabla, defaultTabla);
                    } else {
                        mensajeError menError = new mensajeError();
                        mensajeError.labelMensaje.setText("Error al eliminar");
                        menError.setVisible(true);
                        menError.setAlwaysOnTop(true);
                        // JOptionPane.showMessageDialog(null, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("No se puede eliminar," + "\n algún cliente lo tiene apartado");
                    menAdvertencia.setVisible(true);
                    menAdvertencia.setAlwaysOnTop(true);
                    // JOptionPane.showMessageDialog(null, "No se puede eliminar," + "\n algún cliente lo tiene apartado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {

            }

        } catch (Exception e) {
        }

    }

    public void buscarProductoPorNombreORClaveORDescripcio(JTextField dato, JTable tabla, DefaultTableModel defaultTabla) {
        if (validar.validarCampos(dato)) {
            List<Productos> lista = consultarTodos();
            vaciarTabla(tabla, defaultTabla);

            for (int i = 0; i < lista.size(); i++) {
                if (dato.getText().toString().equalsIgnoreCase(lista.get(i).getNombre()) || dato.getText().toString().equalsIgnoreCase(lista.get(i).getClave()) || dato.getText().toString().equalsIgnoreCase(lista.get(i).getDescripcion())) {

                    defaultTabla.addRow(new Object[]{lista.get(i).getIdproductos(), lista.get(i).getClave(), lista.get(i).getNombre(),
                        lista.get(i).getDescripcion(), lista.get(i).getCantidad(), lista.get(i).getPrecio()});
                }

            }
        } else {

        }

    }

    public void consultaEspecificaParaModificar(int id) {
        boolean ban = false;
        Productos bean = (Productos) dao.consultaEspecifica(id + "");
        //encontramos nuestro id en lalista y obtenmos el bean 
        //y lo enviamos a la pantalla editar pro para llenar los campos
        editarProducto.bean = bean;
    }

    public void consultaEspecificaParaModificarPendientes(String clave) {
        boolean ban = false;
        Productos bean = (Productos) dao.consultaEspecificaPorClave(clave + "");
        //encontramos nuestro id en lalista y obtenmos el bean 
        //y lo enviamos a la pantalla editar pro para llenar los campos
        editarProducto.bean = bean;
    }

    public void consultaEspecificaParaEnviarAPantllas(int id, int existencias, String deDondeViene) {
        //hacemos una consulta especifica 
        //y lo enviamos a la pantalla que se requiere pro para llenar los campos
        if (deDondeViene.equalsIgnoreCase("detallesVenderProducto")) {
            Productos bean = (Productos) dao.consultaEspecifica(id + "");
            bean.setCantidad(existencias);

            detallesVenderProducto.bean = bean;

            detallesVenderProducto detalles = new detallesVenderProducto();
            detalles.setVisible(true);

        } else {

        }
        // editarProducto.bean = lista.get(i);

    }

    public Productos mostrarImagen(String id, JLabel labelFoto) {
        boolean ban = false;
        //HACEMOS LACONSUTLA ESPECIFICA PARA OBTEBENER LOS DATOS DEL PRODUCTO

        Productos bean = (Productos) dao.consultaEspecifica12(id);

        Image imagen = null;

        try {

            if (bean.getFoto() == null) {

                labelFoto.setIcon(null);
                labelFoto.setText("Sin foto");
            } else {

                imagen = dao.getImage(bean, false);

                Icon icon = new ImageIcon(imagen.getScaledInstance(130, 176, Image.SCALE_DEFAULT));
                labelFoto.setIcon(icon);
            }

        } catch (Exception ex) {

            System.out.println("error al cargar al imagen " + ex.getMessage());

        }
        return bean;

    }

    public Productos mostrarImagen2(String clave, JLabel labelFoto) {
        boolean ban = false;
        //HACEMOS LACONSUTLA ESPECIFICA PARA OBTEBENER LOS DATOS DEL PRODUCTO

        Productos bean = (Productos) dao.consultaEspecifica12PorClave(clave);
        //System.out.println("bean " + bean);
        // System.out.println("bean foto " + bean.getFoto());
        Image imagen = null;

        try {

            if (bean.getFoto() == null) {

                labelFoto.setIcon(null);
                labelFoto.setText("Sin foto");
            } else {

                imagen = dao.getImage(bean, false);

                Icon icon = new ImageIcon(imagen.getScaledInstance(250, 400, Image.SCALE_DEFAULT));
                labelFoto.setIcon(icon);
            }

        } catch (Exception ex) {

            System.out.println("error al cargar al imagen " + ex.getMessage());

        }
        return bean;

    }

    public Productos mostrarImagen3(int id, JLabel labelFoto) {
        boolean ban = false;
        //HACEMOS LACONSUTLA ESPECIFICA PARA OBTEBENER LOS DATOS DEL PRODUCTO

        Productos bean = (Productos) dao.consultaEspecifica(id + "");

        Image imagen = null;

        try {

            if (bean.getFoto() == null) {

                labelFoto.setIcon(null);
                labelFoto.setText("Sin foto");
            } else {

                imagen = dao.getImage(bean, false);

                Icon icon = new ImageIcon(imagen.getScaledInstance(250, 400, Image.SCALE_DEFAULT));
                labelFoto.setIcon(icon);
            }

        } catch (Exception ex) {

            System.out.println("error al cargar al imagen " + ex.getMessage());

        }
        return bean;

    }
//ESTE METODO ES LLAMADO CUANDO EL USUARIO QUIERE  REGISTRAR UN PRODUCTO, PERO ESE PRODUCTO YA EXISTE
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

    public void btnCancelar(JTextField clave, JTextField nombre, JTextField precio, JTextField color, JTextField cantidad, JTextArea descripcion, JLabel foto) {
        validar.limpiarCampos(clave);
        validar.limpiarCampos(nombre);
        validar.limpiarCampos(precio);
        validar.limpiarCampos(color);
        validar.limpiarCampos(cantidad);
        descripcion.setText("");
        foto.setIcon(null);
    }

    public void llenarTabla(JTable tabla, DefaultTableModel defaultTabla) {
        List<productos> lista = dao.consultarTodosPricipal();
        if (lista.size() > 0) {
            vaciarTabla(tabla, defaultTabla);

            for (int i = 0; i < lista.size(); i++) {
                defaultTabla.addRow(new Object[]{lista.get(i).getIdProductos(), lista.get(i).getClave(), lista.get(i).getNombre(),
                    lista.get(i).getDescripcion(), lista.get(i).getCantidad(), lista.get(i).getPrecio()});

            }
        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("No hay productos registrados");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            // JOptionPane.showMessageDialog(null, "No hay productos registrados", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

//======================================== METODOS DE LA PANTALLA PRO AGOTADOS ============================================
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
            menAdvertencia.setAlwaysOnTop(true);

            // JOptionPane.showMessageDialog(null, "No hay ningun registro", "Inforamción", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void buscarProductoPorNombreORClaveORDescripcioProAgotados(JTextField dato, JTable tablaProAgotados, DefaultTableModel defaultTablaProAgotados) {
        if (validar.validarCampos(dato)) {
            //nos trae una lista de objetos

            //creamo  una lista de tipo productos
            List<productos> lista = dao.consultarPorNombreClaveDescripcionLikeAgotados(dato.getText());
            //vaciamos la tabla para meter el producto que buscamos
            if (lista.size() > 0) {
                vaciarTabla(tablaProAgotados, defaultTablaProAgotados);
                //recorremos la lista de objetos  y vamos llenando   la lista d e tipo productos
                for (int i = 0; i < lista.size(); i++) {
                    defaultTablaProAgotados.addRow(new Object[]{lista.get(i).getIdProductos(), lista.get(i).getClave(), lista.get(i).getNombre(),
                        lista.get(i).getDescripcion(), lista.get(i).getCantidad(), lista.get(i).getPrecio()});
                }

            } else {
                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("El producto no esta registrado");
                menAdvertencia.setVisible(true);
                menAdvertencia.setAlwaysOnTop(true);
                //  JOptionPane.showMessageDialog(null, "El producto no esta registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } else {

        }

    }

    //metodo queu buscar el producto por medio de clave,nombre o descripcion con like
    public void buscarProductoPorNombreORClaveORDescripcioProAgotadosLike(JTextField dato, JTable tablaProAgotados, DefaultTableModel defaultTablaProAgotados) {
        //nos trae una lista de objetos

        //creamo  una lista de tipo productos
        List<productos> lista = dao.consultarPorNombreClaveDescripcionLikeAgotados(dato.getText());
        if (lista.size() > 0) {
            vaciarTabla(tablaProAgotados, defaultTablaProAgotados);
            //recorremos la lista de objetos  y vamos llenando   la lista d e tipo productos

            for (int i = 0; i < lista.size(); i++) {

                defaultTablaProAgotados.addRow(new Object[]{lista.get(i).getIdProductos(), lista.get(i).getClave(), lista.get(i).getNombre(),
                    lista.get(i).getDescripcion(), lista.get(i).getCantidad(), lista.get(i).getPrecio()});

            }
        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("El producto  no esta registrado");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);
            //  JOptionPane.showMessageDialog(null, "El producto  no esta registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
//vaciamos la tabla para meter el producto que buscamos

    }

    //=================================== PARA EL CATALOGO PRODUTOS ==================================================
    //los daos
    DaoProductos daoProductosCatalogo = new DaoProductos();

    public void llenarListaImg(JLabel labelImg11, JLabel labelImg12, JLabel labelImg13, JLabel labelImg14, JLabel labelImg15,
            JLabel labelImg16, JLabel labelImg17, JLabel labelImg29,
            JButton btnAtras, JButton btnSiguiente, JLabel labelIdProducto16, JLabel labelIdProducto17, JLabel labelIdProducto18, JLabel labelIdProducto19,
            JLabel labelIdProducto20, JLabel labelIdProducto21, JLabel labelIdProducto22, JLabel labelIdProducto23,
            int contCatalogoImg, int canlis) {
        List<productos> listaProCatalgo = daoProductosCatalogo.consultarImageParaCatalogo();
        List<DetallesProductos> listaImg = new ArrayList<>();
        Image imagen = null;
        for (int i = 0; i < listaProCatalgo.size(); i++) {
            //DECLARAMOS EL BEAN DETALLES PRODCUTOS
            //INSTANCIAMOS EL BEAN DETALLES PRODUCTOS
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
        if (listaProCatalgo.size() != 0) {
            llenarCatalago(listaImg, labelImg11, labelImg12, labelImg13, labelImg14, labelImg15, labelImg16, labelImg17, labelImg29, btnAtras, btnSiguiente, labelIdProducto16, labelIdProducto17, labelIdProducto18, labelIdProducto19, labelIdProducto20, labelIdProducto21, labelIdProducto22, labelIdProducto23, contCatalogoImg, canlis);

            //llenarCatalago(listaImg);
        } else {
            System.out.println("No hay imagenes para mostrar");
//PONEMOS VACIOS EN LOS LABEL DONDE PONEMOS LOS ID DE PRODUCTOS
            labelIdProducto16.setText("");
            labelIdProducto17.setText("");
            labelIdProducto18.setText("");
            labelIdProducto19.setText("");
            labelIdProducto20.setText("");
            labelIdProducto21.setText("");
            labelIdProducto22.setText("");
            labelIdProducto23.setText("");
        }
    }

    //====================================  PARA EL CATALOGO DE PRODUCTOS  ==================================
    public void llenarCatalago(List<DetallesProductos> listaImg, JLabel labelImg11, JLabel labelImg12, JLabel labelImg13, JLabel labelImg14, JLabel labelImg15,
            JLabel labelImg16, JLabel labelImg17, JLabel labelImg29,
            JButton btnAtras, JButton btnSiguiente, JLabel labelIdProducto16, JLabel labelIdProducto17, JLabel labelIdProducto18, JLabel labelIdProducto19,
            JLabel labelIdProducto20, JLabel labelIdProducto21, JLabel labelIdProducto22, JLabel labelIdProducto23,
            int contCatalogoImg, int canlis) {
        //  System.out.println("entro a actualizar catalogo");
//    ------------------------- CONTADOR CUANDO LLEGUE A 7 DEBE IMPRIMIR 8 IMG
        canlis = listaImg.size();
//        canlis = 7;

        int desc = 0;
        int conListaLabel = 0;
        boolean ban = false;
        Icon icon1 = null;
        List<JLabel> listaLabel = new ArrayList<>();

        listaLabel.add(labelImg11);
        listaLabel.add(labelImg12);
        listaLabel.add(labelImg13);
        listaLabel.add(labelImg14);
        listaLabel.add(labelImg15);
        listaLabel.add(labelImg16);
        listaLabel.add(labelImg17);
        listaLabel.add(labelImg29);

//HACEMOS UNA LISTA DE JLABEL DONDE GUARDAREMOS NUESTRO IDPRODUCTO
        List<JLabel> listaLabelIdPro = new ArrayList<>();
        listaLabelIdPro.add(labelIdProducto16);
        listaLabelIdPro.add(labelIdProducto17);
        listaLabelIdPro.add(labelIdProducto18);
        listaLabelIdPro.add(labelIdProducto19);
        listaLabelIdPro.add(labelIdProducto20);
        listaLabelIdPro.add(labelIdProducto21);
        listaLabelIdPro.add(labelIdProducto22);
        listaLabelIdPro.add(labelIdProducto23);

//        System.out.println("listaImg " + listaImg.size());
//        System.out.println("listaLabel + " + listaLabel.size());
//        System.out.println("canList " + canlis);
        for (int i = 0; i < listaLabel.size(); i++) {//solo sacaremos 8 imagenes del 
//            System.out.println("iiii " + i);
//            System.out.println("esto vale canlis " + canlis);
//            System.out.println("conCatalogo " + contCatalogoImg);
            if (contCatalogoImg == listaImg.size()) {
//                System.out.println("entro en el if de canlis!= contCatalogoImg");
//                listaLabel.get(contCatalogoImg).setText("Ya no hay img");
//                contCatalogoImg++;
                btnSiguiente.setEnabled(true);
            } else {

                for (int j = 0; j < canlis; j++) {
//                    System.out.println("jjj " + j);
//                    System.out.println("para ver al presionasr next " + contCatalogoImg);
                    if (listaImg.get(contCatalogoImg).getImagen() == null) {
                        icon1 = null;
                        //   System.out.println("soy null ala verga " + listaImg.get(contCatalogoImg).getImagen());
                    } else {

                        icon1 = new ImageIcon(listaImg.get(contCatalogoImg).getImagen().getScaledInstance(230, 300, listaImg.get(contCatalogoImg).getImagen().SCALE_DEFAULT));
                        //System.out.println("entro en else ala verga " + icon1);
                    }

                    listaLabel.get(conListaLabel).setIcon(icon1);
                    listaLabelIdPro.get(conListaLabel).setText(String.valueOf(listaImg.get(contCatalogoImg).getIdProducto()));
                    conListaLabel++;
                    contCatalogoImg++;
                    j = listaImg.size();
                    canlis = canlis - 1;

//                    System.out.println("canlis " + canlis);
//                    System.out.println("jjjj2222" + j);
//                    System.out.println("contimg en for " + contCatalogoImg);
                }

            }

            if (listaImg.size() > 8) {

                btnSiguiente.setEnabled(true);

            } else if (canlis < 8) {

                if (canlis == 0) {

//                    System.out.println("cont en if 1111" + contCatalogoImg);
//                    System.out.println(" iiff canlis = 0 " + i);
                    if (i != 7) {

                        listaLabel.get(contCatalogoImg).setIcon(null);

                        listaLabelIdPro.get(contCatalogoImg).setText("");
//                        listaLabel.get(contCatalogoImg).setEnabled(false);
//                        listaLabel.get(contCatalogoImg).setVisible(false);
                        contCatalogoImg++;
//                        i++;

//                        System.out.println("if iiiiii " + i);
//                        System.out.println("cont en if " + contCatalogoImg);
                        btnAtras.setEnabled(false);
                        btnSiguiente.setEnabled(false);
                    } else {
//                        System.out.println("Ya no entro por que iii ya es igual a 77 " + contCatalogoImg);
                    }

                } else {
//                    System.err.println("entro en else de canliss == 0");
                }

            } else {
                btnAtras.setEnabled(false);
                btnSiguiente.setEnabled(false);
//                System.err.println("entro al else de listaImg.siza < 8");
            }

        }

        if (contCatalogoImg == listaImg.size()) {
            btnSiguiente.setEnabled(false);
        }

//        System.out.println("esto termino valiendo calis " + canlis);
        contCatalogoImg--;
        if (contCatalogoImg > 7) {
            btnAtras.setEnabled(true);
        }
        if (contCatalogoImg < 8) {
            btnAtras.setEnabled(false);
        }
//        System.out.println("=====================================");
//        System.out.println("esto termina valiendo mi contadorCat " + contCatalogoImg);
//        System.out.println("=====================================");
    }

    public void consultarPorClaveNombreDescripcionLike(String dato, JTable tabla, DefaultTableModel defaultTabla) {

        List<productos> lista = dao.consultarPorNombreClaveDescripcionLike(dato);
        if (lista.size() > 0) {
            vaciarTabla(tabla, defaultTabla);

            for (int i = 0; i < lista.size(); i++) {
                defaultTabla.addRow(new Object[]{lista.get(i).getIdProductos(), lista.get(i).getClave(), lista.get(i).getNombre(),
                    lista.get(i).getDescripcion(), lista.get(i).getCantidad(), lista.get(i).getPrecio()});

            }
        } else {
            // JOptionPane.showMessageDialog(null, "El producto no esta registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
//************************************ metodos nuevos cesar 2019 *************************************************

    public Productosapartados consultarImgYmedidas(String idproApartados,
            JLabel labelFoto, JTable tablaMedidas, DefaultTableModel modelMedidas, JTextArea txtdescripcion) {
        boolean ban = false;
        //HACEMOS LACONSUTLA ESPECIFICA PARA OBTEBENER LOS DATOS DEL PRODUCTO

        List<Productosapartados> lista = dao.consultarImgYmedidas(idproApartados);
        //System.out.println("bean " + bean);
        // System.out.println("bean foto " + bean.getFoto());
        Image imagen = null;

        try {

            if (lista.get(0).getFoto() == null) {

                labelFoto.setIcon(null);
                labelFoto.setText("Sin foto");
            } else {
                labelFoto.setText("");
                imagen = dao.getImg(lista.get(0).getFoto(), false);

                Icon icon = new ImageIcon(imagen.getScaledInstance(200, 300, Image.SCALE_DEFAULT));
                labelFoto.setIcon(icon);
            }
            //llenamos la tabla medidas
            vaciarTabla(tablaMedidas, modelMedidas);
            modelMedidas.addRow(new Object[]{
                lista.get(0).getMedidas().getIdmedidas(),
                lista.get(0).getMedidas().getTalle(),
                lista.get(0).getMedidas().getSise(),
                lista.get(0).getMedidas().getHombros(),
                lista.get(0).getMedidas().getBusto(),
                lista.get(0).getMedidas().getLargoFalda(),
                lista.get(0).getMedidas().getAnchoPuno(),
                lista.get(0).getMedidas().getCintura(),
                lista.get(0).getMedidas().getCadera()});

            txtdescripcion.setText(lista.get(0).getDetallesproducto().toUpperCase());
        } catch (Exception ex) {

            System.out.println("error al cargar al imagen " + ex.getMessage());

        }
        return lista.get(0);

    }
}
