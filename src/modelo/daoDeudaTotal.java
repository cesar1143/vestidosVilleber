/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import almacenamientoDeListas.almacenamientoDeListas;
import beans.Clientes;
import beans.Deudatotal;
import beans.Medidas;
import interfaces.metodosDao;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author famsa
 */
public class daoDeudaTotal implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    @Override
    public boolean registrar(Object obj) {
        boolean ban = false;
        Deudatotal bean = (Deudatotal) obj;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            almacenamientoDeListas.ultimoRegistroDeuda= (int) session.save(bean);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar medidas ", "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            session.close();
        }
        return ban;

    }

    @Override
    public Object consultaEspecifica(String id) {
        Deudatotal  bean= new Deudatotal();
        try {
            
            session=sessionFactory.openSession();
           transaction=session.beginTransaction();
           bean=(Deudatotal) session.createQuery("from Deudatotal where iddeudatotal='"+id+"'").uniqueResult();
           transaction.commit();
            
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            
            
        }finally{
            session.close();
        }
    
    return bean;
    }

    public Object consultaEspecificaPorDeudaTotalAndEstado(String status, String totalApagar) {

        Deudatotal bean = new Deudatotal();

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean
                    = bean = (Deudatotal) session.createQuery("from Deudatotal where status='" + status + "' and deudatotal='" + totalApagar + "'").uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al obtener el ultimo registro de Productosapartados " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
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
        Deudatotal beanDeuda = (Deudatotal) bean;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(bean);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al modificar deuda total " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            session.close();
        }
        return ban;

    }

    @Override
    public boolean eliminar(Object bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
