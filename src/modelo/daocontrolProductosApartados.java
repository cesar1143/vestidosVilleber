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
public class daocontrolProductosApartados implements metodosDao{
SessionFactory sessionFactory=NewHibernateUtil.getSessionFactory();
    Session session= null;
    Transaction transaction= null;
    
    
    Usuarios beanUsuarios=null;
    
    boolean ban=false;
    @Override
    public boolean registrar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultaEspecifica(String id) {
      Productosapartados  bean= null;
       
        try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
           // String hql="select  from Productosapartados  ;
            //Query query = session.createQuery(hql);
            //bean=(Productosapartados) query.uniqueResult();
            //bean=(Productosapartados) session.createQuery("from Productosapartados  where Productos.producto_id='"+id+"'").uniqueResult();
            transaction.commit();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error consulta especifica "+e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
         if (transaction!=null) {
                transaction.rollback();
                
            }
        }finally{
           session.close();
        }
        return bean;
    }

    @Override
    public List<Object> consultarTodos() {
        List<Object>lista= new ArrayList<Object>();
        try {
            session= sessionFactory.openSession();
            transaction=session.beginTransaction();
            lista=session.createQuery("from Productosapartados").list();
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        }finally{
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
    
}
