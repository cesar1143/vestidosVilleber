/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import beans.Clientes;
import beans.Fechaspruebas;
import beans.Productosapartados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import servicios.conexion;
import util.NewHibernateUtil;

/**
 *
 * @author Usuario
 */
public class pruebas {
     
    public static void main(String[] args) {
        pruebas p = new pruebas();
        //p.editarConsultarPagos();
        //p.editarConsultarFechas();
    p.obtenerFechaActual()  ;
        
    }
    
   public void editarConsultarPagos(){
       Connection con=null;
       con=conexion.getConnection();
       try {
           String sql="select idpagos,fecharegistro from pagos ";
           PreparedStatement ps=con.prepareStatement(sql);
           ResultSet rs =ps.executeQuery();
           while(rs.next()){
               String id=rs.getString("idpagos");
               String arreDatos[]=rs.getString("fecharegistro").split("-");
               String dia=arreDatos[0];
               String mes=arreDatos[1];
               String año=arreDatos[2];
               String  nuevaFecha=año+"-"+mes+"-"+dia;
               sql="update pagos set fecharegistro2='"+nuevaFecha+"' where idpagos='"+id+"'";
               PreparedStatement ps1=con.prepareStatement(sql);
               ps1.executeUpdate();
           }
       } catch (Exception e) {
           System.out.println("faloo "   + e.getMessage());
       }
       
   }
   
     public void editarConsultarFechas(){
       Connection con=null;
       con=conexion.getConnection();
       try {
           String sql="select idfechaspruebas,fechaprueba,fechaevento,fechaprueba2,fechaevento2 from fechaspruebas; ";
           PreparedStatement ps=con.prepareStatement(sql);
           ResultSet rs =ps.executeQuery();
           while(rs.next()){
               String id=rs.getString("idfechaspruebas");
               String arreDatos[]=rs.getString("fechaprueba").split("-");
               String dia=arreDatos[0];
               String mes=arreDatos[1];
               String año=arreDatos[2];
               String  nuevaFechaprueba=año+"-"+mes+"-"+dia;
               ///----------------------
                String arreDatos2[]=rs.getString("fechaevento").split("-");
               String dia1=arreDatos2[0];
               String mes1=arreDatos2[1];
               String año1=arreDatos2[2];
               String  nuevaFechaevento=año1+"-"+mes1+"-"+dia1;
               sql="update fechaspruebas set fechaprueba2='"+nuevaFechaprueba+"',fechaevento2='"+nuevaFechaevento+"' where idfechaspruebas='"+id+"'";
               System.out.println("sql "+sql);
               PreparedStatement ps1=con.prepareStatement(sql);
               ps1.executeUpdate();
           }
       } catch (Exception e) {
           System.out.println("faloo "   + e.getMessage());
       }
       
   }
     
     
      public String obtenerFechaActual() {
        String fecha = "";
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);
        int hora= calendario.get(Calendar.HOUR);
        int minuto= calendario.get(Calendar.MINUTE);
       mes=mes+1;
        String dia1=dia+"";
        String mes1=mes+"";
        if (dia<=9 ) {
            dia1="0"+dia;
           
        }
        if ( mes<9) {
           
            mes1="0"+mes;
        }
        
        
        
        fecha = dia1+ "-" + mes1  + "-" + año ;
          System.out.println("fecha " + fecha);
          
        return fecha;
    }
}
