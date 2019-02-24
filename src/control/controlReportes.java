/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import beans.Deudatotal;
import beans.Pagos;
import beans.Productosapartados;
import beans.mesConMasVentas;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mensajes.mensajeAdvertencia;
import modelo.daoProductosApartados;

/**
 *
 * @author famsa
 */
public class controlReportes {
//recibe  tiporeporte,fechai,fecha-f,fechaA,txtmostraVenta,tabla, default,label

    public void reportes(String tipoReporte, JDateChooser fechaInicial, JDateChooser fechaFinal, JYearChooser fechaAño,
            JTextField mostrarVenta, JTable tablaReportes, DefaultTableModel defaultTablaReportes,
            JLabel labeltextoVenta, JLabel labelFechaInicial, JLabel labelFechaAño, JLabel labelfechaFinal) {
        if (tipoReporte.equals("Selecciona...")) {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Selecciona algun tipo de consulta");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
            //JOptionPane.showMessageDialog(null, "Selecciona algun tipo de consulta", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (tipoReporte.equals("Dia")) {
            //desactivamos yactivamos los campos dependiendo cuanl se utilizara

            if (fechaInicial.getDate() != null) {

              //  System.out.println("fecha de  hoy " + obtenerFecha(fechaInicial));
                //                System.out.println("Fechaaa " + fechaDia);
                //obtenemro la lista y el tottal de la venta vendido
                //recibe una fecha,txtmostrarVenta,labelventa,tabla,defaultabla
                obtenerProductosVendidosPorDia(obtenerFecha(fechaInicial), mostrarVenta, labeltextoVenta, tablaReportes, defaultTablaReportes);
                //llenamos la tabla reportes

            } else {
                mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ingresa la fecha");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
               // JOptionPane.showMessageDialog(null, "Ingresa la fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            //buscamos tipo de reporte semanal
        } else if (tipoReporte.equals("Semana")) {
            //desactivamos yactivamos los campos dependiendo cuanl se utilizara
            //desactivamos yactivamos los campos dependiendo cuanl se utilizara

            if (fechaInicial != null) {
                if (fechaFinal != null) {
                   //System.out.println("entro aqui perro");
                    obtenerProductosVendidosPorSemana(obtenerFecha(fechaInicial), obtenerFecha(fechaFinal), mostrarVenta, labeltextoVenta, tablaReportes, defaultTablaReportes);
                } else {
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ingresa la fecha");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
                    
                   // JOptionPane.showMessageDialog(null, "Ingresa la fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                   mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ingresa la fecha");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ingresa la fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } else if (tipoReporte.equals("Año")) {
            Calendar fecha = Calendar.getInstance();
            int añoActual = fecha.get(Calendar.YEAR);
            int feañoIngresado = fechaAño.getYear();
            if (feañoIngresado <= añoActual) {
                obtenerProductosVendidosPorAño(tipoReporte, mostrarVenta, labeltextoVenta, tablaReportes, defaultTablaReportes);
            } else {
                   mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Ingresa un año valido");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Ingresa un año valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } else if (tipoReporte.equalsIgnoreCase("Mes con mas ventas")) {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("En construcción :)");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
             // JOptionPane.showMessageDialog(null, "En construcción :)", "Advertencia", JOptionPane.WARNING_MESSAGE);
            /*Calendar fecha = Calendar.getInstance();
            int añoActual = fecha.get(Calendar.YEAR);
            int feañoIngresado = fechaAño.getYear();
            if (feañoIngresado <= añoActual) {
                obtenerProductosVendidosPorAño(tipoReporte, mostrarVenta, labeltextoVenta, tablaReportes, defaultTablaReportes);
            } else {
                JOptionPane.showMessageDialog(null, "Ingresa un año valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }*/

        } else {
            mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
            mensajeAdvertencia.labelMensaje.setText("Aun no funcionan");
          menAdvertencia.setVisible(true);
            menAdvertencia .setAlwaysOnTop(true);
           // JOptionPane.showMessageDialog(null, "Aun no funcionan", "Advertencia", JOptionPane.WARNING_MESSAGE);
            vaciarTabla(tablaReportes, defaultTablaReportes);
        }
        //Selecciona..., Dia, Semana, Año, Mes con mas ventas

    }

    //recibe una fecha,txtmostrarVenta,labelventa,tabla,defaultabla
    public void obtenerProductosVendidosPorDia(String fecha, JTextField mostrarVenta, JLabel labeltextoVenta,
            JTable tablaReportes, DefaultTableModel defaultTablaReportes) {
        List<Pagos> listaPagos = new daoProductosApartados().consultarTodosPorDia(fecha);
           List<Productosapartados> listaProApartados2=new ArrayList<Productosapartados>();
        int sumaPrecio = 0;
        //System.out.println("listaProApar.size() " + listaPagos.size());
        if (listaPagos.size() > 0) {
           
          //  System.out.println("entro lista pro apa");
            for (int i = 0; i < listaPagos.size(); i++) {

               // System.out.println("entro 3333333 " + listaPagos.get(i).getAbono());
                sumaPrecio = sumaPrecio +  listaPagos.get(i).getAbono();
                //List<Deudatotal> listaDeuda=new daoProductosApartados().consultarTodosProApartdosPorId(listaPagos.get(i).getDeudatotal().getIddeudatotal()+"");
                //System.out.println("cliente " + listaDeuda.get(i).getClientes().getNombrecompleto());
               // Set<Productosapartados>listaProAp=listaDeuda.get(i).getClientes().getProductosapartadoses();
               // System.out.println("tama apa " +listaProAp.size());
                //if (listaProAp.size()>0) {
                  //  for (Productosapartados productosapartados : listaProAp) {
                  //  System.out.println("pro "+ productosapartados.getIdproductosapartados());
                   // listaProApartados2.add(productosapartados);
                //}
               // }else{
                    
                //}
                
            }
            //recibe tabla,defaultTabla,fecha,listaProapartados
            llenarTablaReportes(tablaReportes, defaultTablaReportes, listaProApartados2);

        }
        labeltextoVenta.setText("La venta del dia " + fecha);
        mostrarVenta.setText(sumaPrecio + "");

    }

    public void obtenerProductosVendidosPorSemana(String fechaInicial, String fechaFinal, JTextField mostrarVenta, JLabel labeltextoVenta,
            JTable tablaReportes, DefaultTableModel defaultTablaReportes) {
         List<Pagos> listaPagos = new daoProductosApartados().consultarTodosPorSemana(fechaInicial, fechaFinal);
        List<Productosapartados> listaProApartados2 = new ArrayList<Productosapartados>();
        int sumaPrecio = 0;
        System.out.println("listaProApar.size()semanal " + listaPagos.size());
        if (listaPagos.size() > 0) {

            System.out.println("entro lista pro apa semanal");
            for (int i = 0; i < listaPagos.size(); i++) {

                // System.out.println("entro 3333333 " + listaPagos.get(i).getAbono());
                sumaPrecio = sumaPrecio + listaPagos.get(i).getAbono();
                List<Deudatotal> listaDeuda = new daoProductosApartados().consultarTodosProApartdosPorId(listaPagos.get(i).getDeudatotal().getIddeudatotal() + "");
                //System.out.println("cliente " + listaDeuda.get(i).getClientes().getNombrecompleto());
                Set<Productosapartados> listaProAp = listaDeuda.get(i).getClientes().getProductosapartadoses();
                // System.out.println("tama apa " +listaProAp.size());
                if (listaProAp.size() > 0) {
                    for (Productosapartados productosapartados : listaProAp) {
                        System.out.println("pro semanal" + productosapartados.getIdproductosapartados());
                        listaProApartados2.add(productosapartados);
                    }
                } else {

                }

            }
            //recibe tabla,defaultTabla,fecha,listaProapartados
            llenarTablaReportes(tablaReportes, defaultTablaReportes, listaProApartados2);

        }
        labeltextoVenta.setText("La venta semanal del  " + fechaInicial + " \n al " + fechaFinal + " es:");
        mostrarVenta.setText(sumaPrecio + "");

    }

    public void obtenerProductosVendidosPorAño(String año, JTextField mostrarVenta, JLabel labeltextoVenta,
            JTable tablaReportes, DefaultTableModel defaultTablaReportes) {
    String fechaInicial = "01-01-" + año;
        String fechaFinal = "31-12-" + año;
        List<Pagos> listaPagos = new daoProductosApartados().consultarTodosPorAño(fechaInicial, fechaFinal);

        List<Productosapartados> listaProApartados2 = new ArrayList<Productosapartados>();
       // System.out.println("vamo a ver  " +listaProApartados2.get(0).getIdproductosapartados());
        int sumaPrecio = 0;
        System.out.println("listaProApar.size()año " + listaPagos.size());
        if (listaPagos.size() > 0) {

            
            for (int i = 0; i < listaPagos.size(); i++) {
               
                // System.out.println("entro 3333333 " + listaPagos.get(i).getAbono());
                sumaPrecio = sumaPrecio + listaPagos.get(i).getAbono();
              
            }//termina for
            //recibe tabla,defaultTabla,fecha,listaProapartados
            llenarTablaReportes(tablaReportes, defaultTablaReportes, listaProApartados2);

        } else {
            System.out.println("lista pagos 0");
        }
        labeltextoVenta.setText("La venta del año  " + año + " es:");
        mostrarVenta.setText(sumaPrecio + "");
    }

   
        //recibe tabla,defaultTabla,fecha,listaProapartados

    public void llenarTablaReportes(JTable tablaReportes, DefaultTableModel defaultTablaReportes, List<Productosapartados> listaProApar) {

        if (listaProApar.size() > 0) {
           // System.out.println("entro 10000 " + listaProApar.size());
            vaciarTabla(tablaReportes, defaultTablaReportes);
            for (int i = 0; i < listaProApar.size(); i++) {
                //System.out.println("entro 100001 " + listaProApar.get(i).getProductos().getPrecio());
                int Subtotal = listaProApar.get(i).getProductos().getPrecio() * listaProApar.get(i).getCantidadVenta();
                defaultTablaReportes.addRow(new Object[]{listaProApar.get(i).getProductos().getIdproductos(),listaProApar.get(i).getClientes().getNombrecompleto() ,listaProApar.get(i).getProductos().getClave(), listaProApar.get(i).getProductos().getNombre(),
                    listaProApar.get(i).getCantidadVenta(), listaProApar.get(i).getProductos().getPrecio(), Subtotal});

            }

        }

    }
public void llenarTablaReportes2(JTable tablaReportes, DefaultTableModel defaultTablaReportes, List<Productosapartados> listaProApar) {

        if (listaProApar.size() > 0) {
            System.out.println("entro 10000 " + listaProApar.size());
            vaciarTabla(tablaReportes, defaultTablaReportes);
            for (int i = 0; i < listaProApar.size(); i++) {
                System.out.println("entro 100001 " + listaProApar.get(i).getProductos().getPrecio());
                int Subtotal = listaProApar.get(i).getProductos().getPrecio() * listaProApar.get(i).getCantidadVenta();
                defaultTablaReportes.addRow(new Object[]{listaProApar.get(i).getProductos().getIdproductos(), listaProApar.get(i).getProductos().getClave(), listaProApar.get(i).getProductos().getNombre(),
                    listaProApar.get(i).getCantidadVenta(), listaProApar.get(i).getProductos().getPrecio(), Subtotal});

            }

        }

    }
    public void vaciarTabla(JTable tabla, DefaultTableModel defaultTabla) {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            defaultTabla.removeRow(i);

            i -= 1;

        }

    }

    public String obtenerFecha(JDateChooser fecha) {
      int dia = fecha.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = fecha.getCalendar().get(Calendar.MONTH) + 1;
        int año = fecha.getCalendar().get(Calendar.YEAR);
        String mes1 = mes + "";
        String dia1 = dia + "";
            //prueba
        String diaMesSeleccionado = "";
       
        
        try {
            String formato = fecha.getDateFormatString();
            Date date = fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String fechaDelDia = String.valueOf(sdf.format(date));
            diaMesSeleccionado = fechaDelDia.substring(0, 1);
            System.out.println("fecha prueba " + fechaDelDia);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Al menos elija una FECHA DE NACIMIENTO VALIDA ", "Error..!!", JOptionPane.ERROR_MESSAGE);

        }
        if (Integer.parseInt(diaMesSeleccionado)<= 9) {
            diaMesSeleccionado = "0" + diaMesSeleccionado;
        }
        if (mes <= 9) {
            mes1 = "0" + mes;
        }

    

        String fechaDia = diaMesSeleccionado + "-" + mes1 + "-" + año;
        System.out.println("fecha preubva buena " +fechaDia );

        return fechaDia;
    }

}
