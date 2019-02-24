/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Usuarios;
import interfaces.metodosDao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import servicios.conexion;
import util.NewHibernateUtil;

/**
 *
 * @author Usuario
 */
public class daoUsuarios implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    Usuarios beanUsuarios = null;

    boolean ban = false;

    @Override
    public boolean registrar(Object obj) {

        try {
            beanUsuarios = (Usuarios) obj;
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            int idnuevo = (int) session.save(beanUsuarios);
            System.out.println("id nuevo " + idnuevo);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return ban;
    }

    @Override
    public Object consultaEspecifica(String id) {
        Usuarios bean = new Usuarios();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            bean = (Usuarios) session.createQuery("from Usuarios where idusuarios='" + id + "'").uniqueResult();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar el usuario " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        List<Object> listaObj = new ArrayList<Object>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Usuarios").list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos los usuarios" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }

    @Override
    public boolean editar(Object bean) {
        boolean ban2 = false;
        Usuarios beanUsu = (Usuarios) bean;
        try {
            beanUsuarios = (Usuarios) bean;
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            System.out.println("id bean usuario " + beanUsu.getIdusuarios());
            System.out.println("id bean usuario dao" + beanUsu.getApaterno());
           
            session.update(beanUsu);
            transaction.commit();

            ban2 = true;
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al editar el usuario" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return ban2;
    }

    @Override
    public boolean eliminar(Object bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public Object consultaEspecificaSession(String usuario,String contra) {
        Usuarios bean = new Usuarios();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            bean = (Usuarios) session.createQuery("from Usuarios where usuario='" + usuario + "' and contra='" + contra + "'").uniqueResult();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar el usuario " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return bean;
    }
     
       public Object consultaEspecificaPorUsuario(String id) {
        Usuarios bean = new Usuarios();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            bean = (Usuarios) session.createQuery("from Usuarios where idusuarios='" + id + "'").uniqueResult();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar el usuario " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return bean;
    }

}