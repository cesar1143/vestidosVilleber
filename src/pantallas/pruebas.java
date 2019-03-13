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
import java.util.Date;
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
        p.editarConsultar();
        
    
        
    }
    
   public void editarConsultar(){
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
}
