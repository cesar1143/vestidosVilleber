/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import NuevasPantallas.principal;
import beans.Clientes;
import beans.Deudatotal;
import beans.Pagos;
import beans.Productosapartados;
import beans.Usuarios;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mensajes.mensajeAdvertencia;
import mensajes.mensajeExito;
import modelo.daoClientes;
import modelo.daoDeudaTotal;
import modelo.daoPagos;
import modelo.daoUsuarios;
import pantallas.cambiarEstadoProductosApartados;
import pantallas.verPagos;
import pantallas.verPagos2;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlPagos {

    validarCampos validar = new validarCampos();
    daoPagos daoP = new daoPagos();

    public void registrar(JTextField txtAbono, JTextField txtEfectivoRecibido, JTextField txtCambio, JTextField txtDeuda, JTextField txtResta,
            String nombreCliente, JTable tablaPagos, DefaultTableModel defaultTablaPagos, String idUsuario, JFrame frame) {
        if (validar.validarCampos(txtAbono)) {
            if (validar.validarCampos(txtEfectivoRecibido)) {
                if (Integer.parseInt(txtAbono.getText()) <= Integer.parseInt(txtResta.getText())) {
                    if (Integer.parseInt(txtAbono.getText()) <= Integer.parseInt(txtEfectivoRecibido.getText())) {
                        Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(nombreCliente);
                        Usuarios benaUsuario = (Usuarios) new daoUsuarios().consultaEspecifica(idUsuario);
                        if (beanCliente != null) {
                            //mandamos el bean a ver pagos
                            Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                            if (listaDeuda.size() > 0) {
                                for (Deudatotal deudatotal : listaDeuda) {
                                    if (deudatotal.getStatus().equals("No pagado")) {
                                        //aqui llenamos el bean pago ya que ya tenemos la deuda
                                        Pagos beanPagos = new Pagos();
                                        beanPagos.setDeudatotal(deudatotal);
                                        beanPagos.setUsuarios(benaUsuario);
                                        beanPagos.setAbono(Integer.parseInt(txtAbono.getText()));
                                        beanPagos.setFecharegistro(validar.obtenerFechaActual());
                                        if (daoP.registrar(beanPagos)) {
                                            mensajeExito menExito = new mensajeExito();
                                            mensajeExito.labelMensaje.setText("El abono se registro correctamente");
                                            menExito.setVisible(true);
                                            menExito.setAlwaysOnTop(true);

                                            //JOptionPane.showMessageDialog(null, "El abono se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                            //en cuanto se registre actualizamos la tabla pagos y el resto
                                            vaciarTabla(tablaPagos, defaultTablaPagos);
                                            validar.limpiarCampos(txtAbono);
                                            validar.limpiarCampos(txtEfectivoRecibido);
                                            validar.limpiarCampos(txtCambio);
                                            Deudatotal beanDeuda = (Deudatotal) new daoDeudaTotal().consultaEspecifica(deudatotal.getIddeudatotal() + "");
                                            System.out.println("id deuda " + beanDeuda.getIddeudatotal());
                                            Set<Pagos> listaPagos = beanDeuda.getPagoses();
                                            int sumaPagos = 0;
                                            for (Pagos listaPago : listaPagos) {

                                                sumaPagos = sumaPagos + listaPago.getAbono();

                                                defaultTablaPagos.addRow(new Object[]{listaPago.getIdpagos(), listaPago.getAbono(), listaPago.getFecharegistro(), listaPago.getUsuarios().getNombre()});
                                            }
                                            int restante = Integer.parseInt(txtDeuda.getText()) - sumaPagos;
                                            txtResta.setText(restante + "");

                                            //vamos a verificar si con el abono registrado ya se cubrio  la deuda
                                            //para cambiar el estado de los productos a pagado entregregado o pagado no entregado
                                            //y tambien cambiar el estado de la deuda a pagado y actualizamos la tabla pendientes
                                            //la  tabla pagos
                                            if (Integer.parseInt(txtDeuda.getText()) == sumaPagos) {
                                                //quiere decirque se acubierto la deuda

                                                mensajeExito.labelMensaje.setText("La deuda se ha cubierto completamente");
                                                menExito.setVisible(true);
                                                menExito.setAlwaysOnTop(true);

                                                //JOptionPane.showMessageDialog(null, "La deuda se ha cubierto completamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                                System.out.println("se cubrio la deuda");
                                                //hacemos el cambio de estado de los productos apartados

                                                //modificaremos el estado de la deuda total
                                                beanDeuda.setFecharegistro(validar.obtenerFechaActual());
                                                beanDeuda.setStatus("Pagado");
                                                if (new daoDeudaTotal().editar(beanDeuda)) {
                                                    cambiarEstadoProductosApartados.beanCliente = beanCliente;
                                                    principal.controlverPagos = false;
                                                    frame.dispose();

                                                    cambiarEstadoProductosApartados cambiar = new cambiarEstadoProductosApartados();
                                                    cambiar.setVisible(true);
                                                } else {
                                                    System.out.println("fallo al modificar el estado de la deuda");
                                                }

                                            } else {

                                            }

                                        }
                                    }
                                }
                            }

                        } else {

                        }
                    } else {
                        txtAbono.requestFocus();
                        mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                        mensajeAdvertencia.labelMensaje.setText("Ingresa un abono menor");
                        menAdvertencia.setVisible(true);
                        menAdvertencia.setAlwaysOnTop(true);
                        //  JOptionPane.showMessageDialog(null, "Ingresa un abono menor, al efectivo Recibido");
                    }

                } else {
                    txtAbono.requestFocus();
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("Ingresa un abono menor al restante de la deuda");
                    menAdvertencia.setVisible(true);
                    menAdvertencia.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "Ingresa un abono menor al restante de la deuda ");
                }

            }

        }

    }

    public void mostrarInformacionDeuda(String nombreCliente) {
        //buscamos el cliente por su nombre para obtner su deuda
        Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(nombreCliente);
        if (beanCliente != null) {
            //mandamos el bean a ver pagos
            verPagos.beanClientes = beanCliente;
            verPagos2.beanClientes = beanCliente;

        } else {
            verPagos.beanClientes = null;
            verPagos2.beanClientes = null;
        }
    }

    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

    public void llenarTablaPagos(JTable tablaPagos, DefaultTableModel defaultTablaPagos, List<Pagos> listaPago) {
        for (int i = 0; i < listaPago.size(); i++) {
            //defaultTablaPagos.addRow(new Object[]{listaPago.get(i).getIdpagos(), listaPago.getAbono(), listaPago.getFecharegistro(), listaPago.getUsuarios().getNombre()});
        }

    }

    public void editar(JTable tablaPagos, DefaultTableModel defaultTablaPagos, String nombreCliente, JTextField txtDeuda, JTextField txtResta) {
        int fila = tablaPagos.getSelectedRow();
        int valorId = Integer.parseInt(tablaPagos.getValueAt(fila, 0) + "");

        try {
            int abono = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el nuevo abono"));
            if (Integer.parseInt(txtDeuda.getText()) < abono) {
                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("El abono es mayor a la deuda");
                menAdvertencia.setVisible(true);
                menAdvertencia.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "El abono es mayor a la deuda ", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                Pagos beanP = (Pagos) daoP.consultaEspecifica(valorId + "");
                beanP.setAbono(abono);
                if (daoP.editar(beanP)) {
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("El abono se modifico correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "El abono se modifico correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    vaciarTabla(tablaPagos, defaultTablaPagos);
                    //consultamos el cliente para obtener su deuda y sus pagos
                    System.out.println("nombre cliente " + nombreCliente);
                    Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(nombreCliente);
                    Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                    //verifcamos su deuda
                    //  System.out.println("lista deuda " + listaDeuda.size());
                    if (listaDeuda.size() > 0) {
                        for (Deudatotal deudatotal : listaDeuda) {

                            if (deudatotal.getStatus().equals("No pagado")) {

                                //obtnemos su pagos
                                Set<Pagos> listaPagos = deudatotal.getPagoses();
                                int sumaPagos = 0;

                                if (listaPagos.size() > 0) {

                                    for (Pagos listaPago : listaPagos) {

                                        sumaPagos = sumaPagos + listaPago.getAbono();

                                        defaultTablaPagos.addRow(new Object[]{listaPago.getIdpagos(), listaPago.getAbono(), listaPago.getFecharegistro(), listaPago.getUsuarios().getNombre()});
                                    }
                                    int restante = Integer.parseInt(txtDeuda.getText()) - sumaPagos;
                                    txtResta.setText(restante + "");

                                }

                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
        }

    }

    public void eliminar(JTable tablaPagos, DefaultTableModel defaultTablaPagos, String nombreCliente, JTextField txtDeuda, JTextField txtResta) {

        try {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta  seguro de eliminar  el pago ?");
            if (respuesta == 0) {
                int fila = tablaPagos.getSelectedRow();
                int valorId = Integer.parseInt(tablaPagos.getValueAt(fila, 0) + "");

                Pagos beanP = (Pagos) daoP.consultaEspecifica(valorId + "");
                if (daoP.eliminar(beanP)) {
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("El abono se elimino correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "El abono se elimino correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    vaciarTabla(tablaPagos, defaultTablaPagos);
                    //consultamos el cliente para obtener su deuda y sus pagos
                    Clientes beanCliente = (Clientes) new daoClientes().consultaEspecificaPorNombreBean(nombreCliente);
                    Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
                    //verifcamos su deuda
                    if (listaDeuda.size() > 0) {
                        for (Deudatotal deudatotal : listaDeuda) {
                            if (deudatotal.getStatus().equals("No pagado")) {
                                //obtnemos su pagos
                                Set<Pagos> listaPagos = deudatotal.getPagoses();
                                int sumaPagos = 0;
                                if (listaPagos.size() > 0) {
                                    for (Pagos listaPago : listaPagos) {
                                        //System.out.println("abono " + listaPago.getAbono());
                                        sumaPagos = sumaPagos + listaPago.getAbono();

                                        defaultTablaPagos.addRow(new Object[]{listaPago.getIdpagos(), listaPago.getAbono(), listaPago.getFecharegistro(), listaPago.getUsuarios().getNombre()});
                                    }
                                    int restante = Integer.parseInt(txtDeuda.getText()) - sumaPagos;
                                    txtResta.setText(restante + "");
                                }

                            }
                        }
                    }
                }
            } else {

            }
        } catch (Exception e) {
        }

    }
//*********************** metodos nuevos cesar 2019 ********************************************

    public void mostrarPagosyabonos(String idCliente,JTable tablaPagos,DefaultTableModel modelPagos) {
        

    }

}
