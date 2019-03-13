/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Productos;
import beans.Productosapartados;
import beans.Usuarios;
import interfaces.metodosDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mensajes.mensajeAdvertencia;
import mensajes.mensajeExito;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author famsa
 */
public class daocontrolProductosApartados implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    Usuarios beanUsuarios = null;

    boolean ban = false;

    @Override
    public boolean registrar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultaEspecifica(String id) {
        Productosapartados bean = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // String hql="select  from Productosapartados  ;
            //Query query = session.createQuery(hql);
            //bean=(Productosapartados) query.uniqueResult();
            //bean=(Productosapartados) session.createQuery("from Productosapartados  where Productos.producto_id='"+id+"'").uniqueResult();
            transaction.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error consulta especifica " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return bean;
    }

    @Override
    public List<Object> consultarTodos() {
        List<Object> lista = new ArrayList<Object>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            lista = session.createQuery("from Productosapartados").list();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public boolean editar(Object bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Object bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean editarExistencias2019(Productos bean) {
        boolean ban = false;
        try {
            Productos beanPro = (Productos) bean;
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("update Productos as p set p.cantidad='" + bean.getCantidad() + "' where p.idproductos='" + bean.getIdproductos() + "'");
            hql.executeUpdate();
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error daoControlprosuctosApartados editarExistencias2019" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {

            session.close();
        }
        return ban;

    }

    public boolean elimianrProducto2019(String id) {
        boolean ban = false;
        // System.out.println("dao "+ id);
        Productos bean = new Productos();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query hql = session.createQuery("select proA.idproductosapartados,proA.status from Productosapartados as proA inner  join proA.productos as p where p.idproductos='" + id + "'");

            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                boolean banChechar = false;//para ver si todos los pro apartados son pagado entregado
                for (int i = 0; i < listaRes.size(); i++) {
                    String idProApartados = listaRes.get(i)[0] + "";
                    String estatus = listaRes.get(i)[1] + "";
                    if (estatus.equalsIgnoreCase("pagado entregado")) {
                        ban = true;
                    } else {
                        //si llega encontrar un status pagado no entregado o apartdo
                        //mustra mensaje no se puede eliminar
                        ban = false;
                        break;
                    }
                }
                if (ban) {
                    //eliminamos todos medidas,fechas y proApartados
                    //primero  eliminamos las medidas y fechas
                    for (int i = 0; i < listaRes.size(); i++) {
                        Query hqlPro = session.createQuery("delete from Productos as p where p.idproductos='" + id + "'");
                        hqlPro.executeUpdate();
                        String idProApartados = listaRes.get(i)[0] + "";
                        Query hqlM = session.createQuery("delete from Medidas as m where m.productosapartados.idproductosapartados='" + idProApartados + "'");
                        hqlM.executeUpdate();
                        Query hqlFechas = session.createQuery("delete from Fechaspruebas as p where p.productosapartados.idproductosapartados='" + idProApartados + "'");
                        hqlFechas.executeUpdate();
                        Query hqlProApartados = session.createQuery("delete from Productosapartados as proA where proA.idproductosapartados='" + idProApartados + "'");

                        hqlProApartados.executeUpdate();
                    }
                    mensajeExito menExito = new mensajeExito();
                    mensajeExito.labelMensaje.setText("Se elimino correctamente");
                    menExito.setVisible(true);
                    menExito.setAlwaysOnTop(true);

                } else {
                    mensajeAdvertencia menAdvertencia = new mensajeAdvertencia();
                    mensajeAdvertencia.labelMensaje.setText("Algún cliente lo tiene apartado");
                    menAdvertencia.setVisible(true);
                    menAdvertencia.setAlwaysOnTop(true);
                }

            } else {

                Query hqlPro = session.createQuery("delete from Productos as p where p.idproductos='" + id + "'");
                hqlPro.executeUpdate();
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("Se elimino correctamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                ban = true;
            }
            transaction.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        //System.out.println("bean dao " + bean.getClave());
        return ban;
    }

}
