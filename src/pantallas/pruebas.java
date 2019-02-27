/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import beans.Clientes;
import beans.Fechaspruebas;
import beans.Productosapartados;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Usuario
 */
public class pruebas {
      SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;
    public static void main(String[] args) {
        pruebas p=new pruebas();
        p.mostrarPagosyabonos(12+"");
        
    }
     //*******       metoodos nuevos cesar 2019 ***********************************
    public List<Clientes> mostrarPagosyabonos(String idCliente){
        List<Clientes> lista= new ArrayList<Clientes>();
        try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
            Query hql=session.createQuery("select u.nombre,u.apaterno from Clientes as c inner join c.usuarios as u where c.idclientes='"+idCliente+"'");
            List<Object[]>listaRes=hql.list();
            if (listaRes.size()>0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    String nombre=listaRes.get(i)[0]+"";
                    System.out.println("nombre " + nombre);
                }
            }else{
                
            }
            transaction.commit();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Error en daoPAgos mostrarPagosyabonos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return lista;
    }
    //consultar productos  pendientes  apartados y pagados no entregados
    public List<Productosapartados> llenarTablaPendientesNew(String idCliente) {
        List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select proA.idproductosapartados,proA.fecharegistro,proA.status,proA.cantidadVenta,proA.detallesproducto,usu.idusuarios,pro.clave,usu.nombre from  Productosapartados as proA inner join   proA.clientes as cli inner join proA.usuarios as usu  inner join proA.productos as pro   where proA.status!='Pagado entregado' and cli.idclientes='" + idCliente + "'");
           
            List<Object[]> lista = hql.list();
           
            System.out.println("simon1");
            for (int i = 0; i < lista.size(); i++) {
                
                
                String idProApa = lista.get(i)[0]+"";
                 Query hqlFechas=session.createQuery("select f.idfechaspruebas,f.fechaprueba,f.fechaevento from Fechaspruebas as f inner join f.productosapartados as proA where proA.idproductosapartados='"+idProApa+"'");
                 List<Object[]>listaFechas=hqlFechas.list();
                   Productosapartados bean = new Productosapartados();
                 if (listaFechas.size()>0) {
                     for (int j = 0; j < listaFechas.size(); j++) {
                         String idFecha=listaFechas.get(j)[0]+"";
                         String fechaPrueba=listaFechas.get(j)[1]+"";
                         String fechaEvento=listaFechas.get(j)[2]+"";
                         if (fechaPrueba.isEmpty()) {
                               bean.setFechaPrueba("Sin Fecha");
                         }else{
                               bean.setFechaPrueba(fechaPrueba);
                         }
                         if (fechaEvento.isEmpty()) {
                              bean.setFechaEvento("Sin fecha");
                         }else{
                              bean.setFechaEvento(fechaEvento);
                         }
                         bean.setIdFechas(idFecha);
                       
                        
                       
                     }
                }else{
                      bean.setIdFechas("0");
                      bean.setFechaPrueba("Sin Fecha");
                      bean.setFechaEvento("Sin fecha");
                 }
                /*String producto_id = lista.get(i)[1];
                 String cliente_id = lista.get(i)[2];*/
                String fechaRegistro = lista.get(i)[1]+"";
                String status = lista.get(i)[2]+"";
                String cantidadVenta = lista.get(i)[3]+"";
                String detallesproducto = lista.get(i)[4]+"";
                String usuario_id = lista.get(i)[5]+"";
                String clave = lista.get(i)[6]+"";
                String nombrecompleto = lista.get(i)[7]+"";
              
                bean.setIdproductosapartados(Integer.parseInt(idProApa));
                bean.setFecharegistro(fechaRegistro);
                bean.setStatus(status);
                bean.setCantidadVenta(Integer.parseInt(cantidadVenta));
                bean.setDetallesproducto(detallesproducto);
                bean.setClave(clave);
                bean.setNombrecompleto(nombrecompleto);
                System.out.println("traigo valores " + nombrecompleto +" "+ bean.getFechaPrueba());
                listaObj.add(bean);

            }
            System.out.println("simon2");
            transaction.commit();
            System.out.println("simon 2ç3");
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error en daoProductosApartados llenarTablaPendientesNew " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
}
