/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Medidas;
import beans.Productosapartados;
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
public class daoMedidas implements metodosDao{
    SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
    Session session=null;
    Transaction  transaction=null;

    @Override
    public boolean registrar(Object obj) {
        boolean ban=false;
        Medidas bean=(Medidas) obj;
         try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.save(bean);
            transaction.commit();
            ban=true;
        } catch (HibernateException e) {
            if (transaction!=null) {
                transaction.rollback();
            }
             JOptionPane.showMessageDialog(null,"Error alregistrar medidas " +  e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
       
        }finally{
             session.close();
         }
        return ban;
    
    }

    @Override
    public Object consultaEspecifica(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        boolean ban=false;
        Medidas beanM= (Medidas) bean;
        try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.delete(beanM);
            transaction.commit();
            ban=true;
            
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar medidas " +  e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
       
            if (transaction!=null) {
                transaction.rollback();
            }
        }finally{
        session.close();
    }
    
    return ban;
    }
    
}
