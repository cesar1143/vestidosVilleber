/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Clientes;
import beans.Medidas;
import beans.Pagos;
import interfaces.metodosDao;
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
 * @author famsa
 */
public class daoPagos implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    @Override
    public boolean registrar(Object obj) {
        boolean ban = false;
        Pagos bean = (Pagos) obj;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(bean);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar medidas ", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return ban;

    }

    @Override
    public Object consultaEspecifica(String id) {
        Pagos bean = new Pagos();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean = (Pagos) session.createQuery("from Pagos  where idpagos='"+id+"'").uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar pago "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return bean;
    }

    @Override
    public List<Object> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(Object bean) {
        boolean ban = false;
        Pagos beanP = (Pagos) bean;
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(beanP);
            transaction.commit();
            ban = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar medidas ", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return ban;
    }

    @Override
    public boolean eliminar(Object bean) {
        boolean ban = false;
        Pagos beanP = (Pagos) bean;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(beanP);
            transaction.commit();
            ban = true;

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar pagos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return ban;

    }
    
    //*******       metoodos nuevos cesar 2019 ***********************************
    public List<Clientes> mostrarPagosyabonos(String idCliente){
        List<Clientes> lista= new ArrayList<Clientes>();
        try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
            Query hql=session.createQuery("select u.nombre from Clientes as c inner join c.usuarios where c.idclientes='"+idCliente+"'");
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
}
