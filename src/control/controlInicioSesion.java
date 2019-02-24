/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import NuevasPantallas.iniciarSesion;
import NuevasPantallas.principal;
import NuevasPantallas.productos;
import modelo.daoUsuarios;
import beans.Usuarios;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mensajes.cargando;
import mensajes.mensajeAdvertencia;
import mensajes.mensajeError;
import mensajes.mensajeExito;
import mensajes.mensajeQuestion;
import pantallas.detallesVenderProducto;
import pantallas.editarUsuario;


import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlInicioSesion {

    validarCampos validar = new validarCampos();
    daoUsuarios dao = new daoUsuarios();
    Usuarios bean = new Usuarios();

    public void Regsitrar(JTextField nombre, JTextField apaterno, JTextField amaterno, JTextField usuario, JTextField contra,
            JTable tablaUsuario, DefaultTableModel defaultTablaUsuarios) {
        if (validar.validarCampos(nombre)) {
            if (validar.validarCampos(apaterno)) {
                if (validar.validarCampos(amaterno)) {
                    if (validar.validarCampos(usuario)) {
                        if (validar.validarCampos(contra)) {
                            // SI LOS CAMPOS FUERON LLENADOS CORRECTAMENTE ENTONCES PUEDE REGISTRAR
                            bean.setNombre(nombre.getText());
                            bean.setApaterno(apaterno.getText());
                            bean.setAmaterno(amaterno.getText());
                            bean.setUsuario(usuario.getText());
                            bean.setContra(contra.getText());
                            //if validar si elusuario ya existe
                            if (dao.consultaEspecificaPorUsuario(usuario.getText()) != null) {
                                if (dao.registrar(bean)) {
                                    mensajeExito menExito = new mensajeExito();
                                    mensajeExito.labelMensaje.setText("El usuario  se registro correctamente");
                                    menExito.setVisible(true);
                                    menExito.setAlwaysOnTop(true);
                                    //   JOptionPane.showMessageDialog(null, "El usuario  se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                    validar.limpiarCampos(nombre);
                                    validar.limpiarCampos(apaterno);
                                    validar.limpiarCampos(amaterno);
                                    validar.limpiarCampos(usuario);
                                    validar.limpiarCampos(contra);
                                    vaciarTabla(tablaUsuario, defaultTablaUsuarios);
                                    llenarTablaUsuarios(tablaUsuario, defaultTablaUsuarios);

                                }
                            } else {
                                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                                mensajeAdvertencia.labelMensaje.setText("Este usuario  ya esta registrado," + "\nintenta con otro  nombre de usuario");
                                menAdvertencia.setVisible(true);
                                menAdvertencia.setAlwaysOnTop(true);

                                //JOptionPane.showMessageDialog(null, "Este usuario  ya esta registrado, intente con otro  nombre de usuario");
                                usuario.requestFocus();
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

    public void Editar(JTextField nombre, JTextField apaterno, JTextField amaterno, JTextField usuario, JTextField contra,
            JTable tablaUsuario, DefaultTableModel defaultTablaUsuarios, String idUsuario, JFrame frame) {

        if (validar.validarCampos(nombre)) {
            if (validar.validarCampos(apaterno)) {
                if (validar.validarCampos(amaterno)) {
                    if (validar.validarCampos(usuario)) {
                        if (validar.validarCampos(contra)) {
                            // SI LOS CAMPOS FUERON LLENADOS CORRECTAMENTE ENTONCES PUEDE REGISTRAR
                            Usuarios bean = new Usuarios();
                            bean.setIdusuarios(Integer.parseInt(idUsuario));
                            bean.setNombre(nombre.getText());
                            bean.setApaterno(apaterno.getText());
                            bean.setAmaterno(amaterno.getText());
                            bean.setUsuario(usuario.getText());
                            bean.setContra(contra.getText());

                            if (dao.editar(bean)) {
                                mensajeExito menExito = new mensajeExito();
                                mensajeExito.labelMensaje.setText("El usuario  se modifico correctamente");
                                menExito.setVisible(true);
                                menExito.setAlwaysOnTop(true);
                                //JOptionPane.showMessageDialog(null, "El usuario  se modifico correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                validar.limpiarCampos(nombre);
                                validar.limpiarCampos(apaterno);
                                validar.limpiarCampos(amaterno);
                                validar.limpiarCampos(usuario);
                                validar.limpiarCampos(contra);
                                vaciarTabla(tablaUsuario, defaultTablaUsuarios);
                                frame.dispose();
                                llenarTablaUsuarios(tablaUsuario, defaultTablaUsuarios);

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

    public void btnEditar(String idUsuario, JTable tablaUsuario, DefaultTableModel defaultTablaUsuarios) {
        Usuarios beanUsu = (Usuarios) dao.consultaEspecifica(idUsuario);
        editarUsuario.beanUsuario = beanUsu;
        editarUsuario.tablaUsuarios = tablaUsuario;
        editarUsuario.defaultTablaUsuarios = defaultTablaUsuarios;
        editarUsuario editUsu = new editarUsuario();
        editUsu.setVisible(true);

    }

    public void llenarTablaUsuarios(JTable tablaUsuario, DefaultTableModel defaultTablaUsuarios) {
        List<Usuarios> listaUsu = new ArrayList<Usuarios>();
        listaUsu = consultarTodos();
        if (listaUsu.size() > 0) {
            for (int i = 0; i < listaUsu.size(); i++) {
                defaultTablaUsuarios.addRow(new Object[]{listaUsu.get(i).getIdusuarios(), listaUsu.get(i).getNombre(), listaUsu.get(i).getApaterno(),
                    listaUsu.get(i).getApaterno(), listaUsu.get(i).getUsuario(), listaUsu.get(i).getContra()});
            }
        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("No hay usuarios registrados");
            menAdvertencia.setVisible(true);
            menAdvertencia.setAlwaysOnTop(true);

            //JOptionPane.showMessageDialog(null, "No hay usuarios registrados", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

//METODO CONSULTAR TODOS   
    public List<Usuarios> consultarTodos() {
        List<Usuarios> listaUsu = new ArrayList<Usuarios>();
        List<Object> listaObjec = dao.consultarTodos();
        if (listaObjec.size() == 0) {
            //JOptionPane.showMessageDialog(null, "No hay usuarios registrados", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            //COMO OBTENEMOS UNA LISTA DE OBJETOS DEBEMOS CONVERTIRLOS A USUARIOS
            for (int i = 0; i < listaObjec.size(); i++) {
                bean = (Usuarios) listaObjec.get(i);
                listaUsu.add(bean);

            }

        }

        return listaUsu;
    }

    //METODO PARA INICIAR SESION  VALIDANDO LOS DATOS
    public void iniciarSesion(String usuario, JTextField contra, JFrame frame) {
        boolean ban = false;

        if (validar.validarCampos(contra)) {
            Usuarios bean = (Usuarios) dao.consultaEspecificaSession(usuario, contra.getText());
            //BUSCAMOS EN LA LISTA SI EXISTE SU USUARIO Y  CONTRASEÑA DEL USUARIO

            //VALIDAMOS SI ENCONTRO LOS DATOS DEL USUARIO EN LA BD
            if (bean != null) {
                if (bean.getContra().equals(contra.getText())) {

                    principal.usuario = bean.getUsuario();
                    principal.idUsuario = bean.getIdusuarios();

                    principal.nombreUsuario = bean.getNombre() + " " + bean.getApaterno();
                    //in.setVisible(false);

                    principal p = new principal();
                    p.setVisible(true);
                    frame.setVisible(false);

                } else {
                    mensajeError men = new mensajeError();
                    mensajeError.labelMensaje.setText("Datos incorrectos");
                    men.setVisible(true);
                    men.setAlwaysOnTop(true);
                    
                    // JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                mensajeError men = new mensajeError();
                mensajeError.labelMensaje.setText("Datos incorrectos");
                men.setVisible(true);
                men.setAlwaysOnTop(true);

                //JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {

        }

    }

    public void llenarSpinerIniciarSesion(JComboBox spinner) {
        if (consultarTodos().size() > 0) {
            for (int i = 0; i < consultarTodos().size(); i++) {
                spinner.addItem(consultarTodos().get(i).getUsuario());

            }

        } else {

        }

    }

    public void cancelar(JTextField nombre, JTextField apaterno, JTextField amaterno, JTextField usuario, JTextField contra) {
        validar.limpiarCampos(nombre);
        validar.limpiarCampos(apaterno);
        validar.limpiarCampos(amaterno);
        validar.limpiarCampos(usuario);
        validar.limpiarCampos(contra);
    }

    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

    public void cerrarSesionPrincipalUsuario(JFrame principalUsurio) {

        /* if (principalUsuarios.controlcambiarEstadoProductosApartados == true) {
            JOptionPane.showMessageDialog(null, "La ventana esta abierto cambiar estado de prodcutos " + "\n ESTA ABIERTO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            principalUsuarios.framecambiarEstadoProductosApartados.setAlwaysOnTop(true);
            principalUsuarios.framecambiarEstadoProductosApartados.setAlwaysOnTop(false);
        } else if (principalUsuarios.controldetallesDelProducto == true) {
            JOptionPane.showMessageDialog(null, "La ventana esta abierto detalles del producto " + "\n ESTA ABIERTO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            principalUsuarios.framedetallesDelProducto.setAlwaysOnTop(true);
            principalUsuarios.framedetallesDelProducto.setAlwaysOnTop(false);
        } else {
            if (principalUsuarios.controlDetallesProducto == true) {
                //1
                principalUsuarios.controlDetallesProducto = false;
                principalUsuarios.frameDetallesProducto.dispose();
            } else {

            }

            if (principalUsuarios.controldetallesVenderProducto == true) {
                //2
                principalUsuarios.controldetallesVenderProducto = false;
                principalUsuarios.framedetallesVenderProducto.dispose();
            } else {

            }

            if (principalUsuarios.controlnuevoRegistro2 == true) {
                //3
                principalUsuarios.controlnuevoRegistro2 = false;
                principalUsuarios.framenuevoRegistro2.dispose();
            } else {

            }

            if (principalUsuarios.controlregistrarProducto2 == true) {
                //4
                principalUsuarios.controlregistrarProducto2 = false;
                principalUsuarios.frameregistrarProducto2.dispose();

            } else {

            }

            if (principalUsuarios.controlverPagos == true) {
                //5
                principalUsuarios.controlverPagos = false;
                principalUsuarios.frameverPagos.dispose();

                //cerramos la principal y mostramos el login
            } else {
            }
            // ================================= nuevos ===========
            if (principal.controlcancelarProductosApartados == true) {
                //6
                principalUsuarios.controlverPagos = false;
                principalUsuarios.frameverPagos.dispose();

                //cerramos la principal y mostramos el login
            } else {
            }

            //cerramos la principal y mostramos el login
            principalUsurio.dispose();
            iniciarSesion in = new iniciarSesion();
            in.setVisible(true);
            System.out.println("entro cerrar sesion sin ventanas aviertas");
        }
         */
    }

    public void cerrarSesionPrincipal(JFrame framePrincipal) {

        try {

            Thread hilo = new Thread() {
                @Override
                public void run() {
                    cargando car = new cargando();

                    car.setVisible(true);
                    car.setAlwaysOnTop(true);
                   
                    //para validar las pantallas principales
                    if (principal.controlcatalogo == true) {
                       
                        principal.framecatalogo.dispose();
                        principal.controlcatalogo = false;
                    } else {

                    }
                    //=============================
                  
                    if (principal.controlproductos == true) {
                        

                        principal.controlproductos = false;
                        principal.frameproductos.dispose();
                    }
                    
                    if (principal.controlproductosAgotados == true) {
                        principal.controlproductosAgotados = false;
                        principal.frameproductosAgotados.dispose();
                    }
                   
                    if (principal.controlproductosPendientes == true) {
                        principal.controlproductosPendientes = false;
                        principal.frameproductosPendientes.dispose();
                    }
                   
                    if (principal.controlreportes == true) {
                        principal.controlreportes = false;
                        principal.framereportes.dispose();
                    }
                   
                    if (principal.controlusuarios == true) {
                        principal.controlusuarios = false;
                        principal.frameusuarios.dispose();
                    }
                    

                    framePrincipal.dispose();
                   
                    //validamos subventanas
                    if (principal.controlDetallesProducto == true) {
                        principal.controlDetallesProducto = false;
                        principal.frameDetallesProducto.dispose();
                    }
                    if (principal.controlcambiarEstadoProductosApartados == true) {
                        principal.controlcambiarEstadoProductosApartados = false;
                        principal.framecambiarEstadoProductosApartados.dispose();
                    }

                    if (principal.controlcancelarProductosApartados == true) {
                        principal.controlcancelarProductosApartados = false;
                        principal.framecancelarProductosApartados.dispose();
                    }
                    if (principal.controldetallesDelProducto == true) {
                        principal.controldetallesDelProducto = false;
                        principal.framedetallesDelProducto.dispose();
                    }
                    if (principal.controldetallesVenderProducto == true) {
                        principal.controldetallesVenderProducto = false;
                        principal.framedetallesVenderProducto.dispose();
                    }
                    if (principal.controleditarProducto == true) {
                        principal.controleditarProducto = false;
                        principal.frameeditarProducto.dispose();
                    }
                    if (principal.controleditarProducto2 == true) {
                        principal.controleditarProducto2 = false;
                        principal.frameeditarProducto2.dispose();

                    }

                    if (principal.controlnuevoRegistro == true) {
                        principal.controlnuevoRegistro = false;
                        principal.framenuevoRegistro.dispose();

                    }
                    if (principal.controlregistrarProducto == true) {
                        principal.controlregistrarProducto = false;
                        principal.frameregistrarProducto.dispose();
                    }
                    if (principal.controlverPagos == true) {
                        principal.controlverPagos = false;
                        principal.frameverPagos.dispose();
                    }
                    //cerrar subventanas                //cerrar subventanas

                    if (principal.controleditarUsuario == true) {
                        principal.controleditarUsuario = false;
                        principal.frameeditarUsuario.dispose();
                    }

                  
                    //todas las ventanas los ponemos en false
                    car.dispose();
                    iniciarSesion login = new iniciarSesion();
                    login.setVisible(true);
                }

            };
            hilo.start();

        } catch (Exception e) {
        }

    }
}
