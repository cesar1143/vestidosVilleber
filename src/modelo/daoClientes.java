/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Clientes;
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
public class daoClientes implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    @Override
    public boolean registrar(Object obj) {

        boolean ban = false;
        Clientes bean = (Clientes) obj;
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
            JOptionPane.showMessageDialog(null, "Error al registrar el cliente " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return ban;

    }

    @Override
    public Object consultaEspecifica(String id) {

        Clientes bean = new Clientes();

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean
                    = bean = (Clientes) session.createQuery("from Clientes where idclientes='" + id + "'").uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al obtener el ultimo registro de Productosapartados " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return bean;

    }

    public List<Clientes> consultaEspecificaPorNombre(String nombre) {
        List<Clientes> listaCliente = new ArrayList<Clientes>();
        Clientes bean = new Clientes();

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // bean=(Clientes) session.createQuery("from Clientes where nombrecompleto='"+nombre+"'").uniqueResult();
            listaCliente = session.createQuery("from Clientes where nombrecompleto like'" + nombre + "%" + "'").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al obtener consultaEspecificaPorNombre con like Productosapartados " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return listaCliente;

    }

    public Object consultaEspecificaPorNombreBean(String nombre) {

        Clientes bean = new Clientes();

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean
                    = // bean=(Clientes) session.createQuery("from Clientes where nombrecompleto='"+nombre+"'").uniqueResult();
                    bean = (Clientes) session.createQuery("from Clientes where nombrecompleto = '" + nombre + "'").uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al obtener el ultimo registro de Productosapartados " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Object bean) {
        Clientes beanCliente = (Clientes) bean;
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(beanCliente);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return ban;
    }

    public Clientes obtenerUltimoId() {
        Clientes bean = new Clientes();
        String sql = "select  from Clientes ORDER by idclientes DESC LIMIT 1";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean
                    = bean = (Clientes) session.createQuery(sql).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al obtener el ultimo registro de Productosapartados " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return bean;
    }
}
