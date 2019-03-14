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
import pantallas.cambiarEstadoProductosApartados2019;
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

    public void mostrarPagosyabonos(String idCliente, JTable tablaPagos, DefaultTableModel modelPagos,
            JTextField txtDeuda, JTextField txtRestan, JTextField txtTotalAbonos) {
        List<Clientes> lista = daoP.mostrarPagosyabonos(idCliente);
        if (lista.size() > 0) {
            String deuda = "";
            int restan = 0;
            for (int i = 0; i < lista.size(); i++) {
                modelPagos.addRow(new Object[]{lista.get(i).getPagos().getIdpagos(),
                    lista.get(i).getPagos().getAbono(),
                    lista.get(i).getPagos().getFecharegistro(),
                    lista.get(i).getUsuarios().getNombre()});
                deuda = lista.get(i).getDeudatotal().getDeudatotal() + "";
                restan = restan + lista.get(i).getPagos().getAbono();
                
            }
            verPagos.idDeuda = lista.get(0).getDeudatotal().getIddeudatotal() + "";
            txtTotalAbonos.setText(restan + "");
            restan = Integer.parseInt(deuda) - restan;
            txtDeuda.setText(deuda);
            txtRestan.setText(restan + "");
            
        } else {
            
        }
        
    }
//*********************************** nuevos metodo cesar 2019  ***************************************
    //al registrar  el abono debemos de ver si ya es el ultimo pago   

    public void registrarAbono(JTextField txtAbono, JTextField txtEfectivoRecibido, JTextField txtCambio, JTextField txtDeuda, JTextField txtResta,
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
    
    public void registrarAbono2(JTextField txtAbono, JTextField txtEfectivoRecibido, JTextField txtCambio,
            JTextField txtDeuda, JTextField txtResta, String nombreCliente, JTable tablaPagos,
            DefaultTableModel defaultTablaPagos, String idUsuario, 
            JFrame frame, String idCliente,JTextField txtTotalAbonos) {
        if (validar.validarCampos(txtAbono)) {
            if (validar.validarCampos(txtEfectivoRecibido)) {
                int abono = Integer.parseInt(txtAbono.getText());
                int efectivoRecibido = Integer.parseInt(txtEfectivoRecibido.getText());
                int deudaRestante = Integer.parseInt(txtResta.getText());

                //vemos si el abono ingresado cubre la deuda
                if (abono == deudaRestante) {

                    //si  es  igual se registra el abono,se cambia el estatus de de la deuda a pagado y cambiamos el
                    //estatus de los productos y se actualizan los campos de  restan y deuda total
                    if (registrarSoloAbonoYcambiarEstatusProductos(idCliente, abono, defaultTablaPagos)) {
                        //recalculamos el resto
                        deudaRestante = deudaRestante - abono;
                        txtResta.setText(deudaRestante + "");
                        txtAbono.setText("");
                        txtEfectivoRecibido.setText("");
                        txtCambio.setText("");
                        txtTotalAbonos.setText(txtDeuda.getText());
                        frame.dispose();
                        principal.controlverPagos = false;
                    }
                } else if (abono > deudaRestante) {
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("Ingresa un abono menor");
                    menAdvertencia.setVisible(true);
                    menAdvertencia.setAlwaysOnTop(true);
                } else if (abono < deudaRestante) {
                    //si el abono el menor a lo que se debe solo registrmos el abono y actualizamos los campos de resta 0
                    if (registrarSoloAbono(idCliente, abono, defaultTablaPagos)) {
                        //recalculamos el resto
                        deudaRestante = deudaRestante - abono;
                        txtResta.setText(deudaRestante + "");
                        txtAbono.setText("");
                        txtEfectivoRecibido.setText("");
                        txtCambio.setText("");
                        int sum=sumaPagos(defaultTablaPagos);
                        txtTotalAbonos.setText(sum+"");
                    } else {
                        
                    }
                    
                }
                
            }
            
        }
        
    }
    
    public boolean registrarSoloAbono(String idCliente, int abono, DefaultTableModel defaultTablaPagos) {
        boolean ban = false;
        System.out.println("idClientes " + idCliente);
        if (daoP.registrarSoloAbono2(idCliente, abono, defaultTablaPagos)) {
            mensajeExito exito = new mensajeExito();
            mensajeExito.labelMensaje.setText("Registro Exitoso!");
            exito.setVisible(true);
            exito.setAlwaysOnTop(true);
            ban = true;
        } else {
            
        }
        
        return ban;
    }
    
    public boolean registrarSoloAbonoYcambiarEstatusProductos(String idCliente, int abono, DefaultTableModel defaultTablaPagos) {
        boolean ban = false;
        
        if (daoP.registrarSoloAbonoYcambiarEstatusProductos(idCliente, abono, defaultTablaPagos)) {
            
            mensajeExito exito = new mensajeExito();
            mensajeExito.labelMensaje.setText("Ultimo abono registrado correctamente!");
            exito.setVisible(true);
            exito.setAlwaysOnTop(true);
            //para cambiar el estatus de  los productos apartados y cambiar el  estatus de deuda total
            
            cambiarEstadoProductosApartados2019 c = new cambiarEstadoProductosApartados2019();
            cambiarEstadoProductosApartados2019.idCliente = idCliente;
            
            c.setVisible(true);
            ban = true;
        } else {
            
        }
        
        return ban;
    }
    
    public void editar2019(JTable tablaPagos, DefaultTableModel defaultTablaPagos,
            String nombreCliente, JTextField txtDeuda, JTextField txtResta, JFrame frame, String idCliente,
            String idDeuda, JTextField txtTotalAbono) {
        int fila = tablaPagos.getSelectedRow();
        int valorId = Integer.parseInt(tablaPagos.getValueAt(fila, 0) + "");
        int deuda = Integer.parseInt(txtDeuda.getText());
        int resta = Integer.parseInt(txtResta.getText());
        int valorAbono = Integer.parseInt(tablaPagos.getValueAt(fila, 1) + "");
        
        try {
            int abono = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el nuevo abono"));
            resta = resta + valorAbono;
            if (resta < abono) {
                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                mensajeAdvertencia.labelMensaje.setText("Ingresa un abono menor");
                menAdvertencia.setVisible(true);
                menAdvertencia.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "El abono es mayor a la deuda ", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (resta == abono) {
                System.out.println("idDeuda " + idDeuda);
                //se edita el  pago se cambia el  estatus de la deuda y cambiamos el estado de los productos
                if (daoP.editar2019(valorId + "", abono + "")) {
                    daoP.editarStatusDeuda2019(idDeuda);
                    tablaPagos.setValueAt(abono, fila, 1);
                    int  sum=sumaPagos(defaultTablaPagos);
                    int nuevaResta = deuda -sum ;
                    txtTotalAbono.setText(sum + "");
                    txtResta.setText(nuevaResta + "");
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("El ultimo abono se modifico correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);
                    frame.dispose();
                    principal.controlverPagos = false;
                    
                    cambiarEstadoProductosApartados2019.idCliente = idCliente;
                    cambiarEstadoProductosApartados2019 c = new cambiarEstadoProductosApartados2019();
                    
                    c.setVisible(true);
                    
                } else {
                    
                }
                
            } else if (abono < resta) {
                
                if (daoP.editar2019(valorId + "", abono + "")) {
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("El abono se modifico correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "El abono se modifico correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    //cambiamos el abono en la tabla  de apagos y recalcular cuanto resta
                    //pintamos  el  nuevo abono
                    tablaPagos.setValueAt(abono, fila, 1);
                    int sum=sumaPagos(defaultTablaPagos);
                    int nuevaResta = deuda - sum;
                    txtTotalAbono.setText(sum+"");
                    txtResta.setText(nuevaResta + "");
                }
            } else {
                
            }
            
        } catch (Exception e) {
            System.out.println("Error al modificar el abono " + e.getMessage());
        }
        
    }
    
    public void eliminar2019(JTable tablaPagos, DefaultTableModel defaultTablaPagos,
            String nombreCliente, JTextField txtDeuda, 
            JTextField txtResta, JFrame frame, String idCliente,JTextField txtTotalAbonos) {
        int fila = tablaPagos.getSelectedRow();
        int valorId = Integer.parseInt(tablaPagos.getValueAt(fila, 0) + "");
        int deuda = Integer.parseInt(txtDeuda.getText());
        int resta = Integer.parseInt(txtResta.getText());
        try {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta  seguro de eliminar  el pago ?");
            if (respuesta == 0) {
                if (daoP.eliminar2109(valorId + "")) {
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("Eliminado corretamente correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);
                    //JOptionPane.showMessageDialog(null, "El abono se modifico correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    //cambiamos el abono en la tabla  de apagos y recalcular cuanto resta
                    //pintamos  el  nuevo abono
                    defaultTablaPagos.removeRow(fila);
                    int sum=sumaPagos(defaultTablaPagos);
                    int nuevaResta = deuda - sum;
                    txtTotalAbonos.setText(sum+"");
                    txtResta.setText(nuevaResta + "");
                }
            } else {
                
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar el abono " + e.getMessage());
        }
        
    }
    
    public int sumaPagos(DefaultTableModel tabla) {
        int sumPagos = 0;
        if (tabla.getRowCount() > 0) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                int abono = Integer.parseInt(tabla.getValueAt(i, 1) + "");
                sumPagos += abono;
            }
        } else {
            
        }
        
        return sumPagos;
    }
}
