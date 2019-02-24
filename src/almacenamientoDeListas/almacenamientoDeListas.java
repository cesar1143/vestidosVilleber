/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamientoDeListas;

import beans.Clientes;
import beans.Fechaspruebas;
import beans.Medidas;
import beans.Productos;
import beans.Productosapartados;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author famsa
 */
public class almacenamientoDeListas {
    //================= ARRAYlIST  QUE CONTENDRAN NUESTRAS MEDIDAS O FECHAS DE LOS PRODUCTOS QUE TENEMOS EN LA TABLA VENTAS===
   public static List<Medidas> listaMedidas = new ArrayList<Medidas>();
   public static List<Fechaspruebas> listaFechas = new ArrayList<Fechaspruebas>();
   public static List<Clientes> listaClientes =new ArrayList<Clientes>();
     public static List<Productosapartados> listaDescripcion =new ArrayList<Productosapartados>();
     public static List<Productos> listaProductos =new ArrayList<Productos>();
     public static boolean  prsionoBoton=false;
     public static String estadoProductoAPartadoGlobal;
   public static String estadoDeudaGlobal;
    public static int abonoPagoGlobal ;
    public static int idProductoApartadoRegistrado;
    public static int ultimoRegistroDeuda;
 
}
