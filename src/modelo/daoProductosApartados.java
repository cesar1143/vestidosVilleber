/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import almacenamientoDeListas.almacenamientoDeListas;
import beans.Deudatotal;
import beans.Medidas;
import beans.Pagos;
import beans.Productosapartados;
import interfaces.metodosDao;
import java.util.ArrayList;
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
public class daoProductosApartados implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    @Override
    public boolean registrar(Object obj) {
        boolean ban = false;
        Productosapartados bean = (Productosapartados) obj;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            almacenamientoDeListas.idProductoApartadoRegistrado = (int) session.save(bean);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar medidas ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ban;

    }

    @Override
    public Object consultaEspecifica(String id) {
        Productosapartados bean = new Productosapartados();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            bean = (Productosapartados) session.createQuery("from  Productosapartados  where idproductosapartados='" + id + "'").uniqueResult();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(Object bean) {
        boolean ban = false;
        Productosapartados bean1 = (Productosapartados) bean;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(bean1);

            transaction.commit();
            ban = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error editar pro apartado " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return ban;

    }

    @Override
    public boolean eliminar(Object bean) {
            boolean ban=false;
        Productosapartados beanPA= (Productosapartados) bean;
        try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.delete(beanPA);
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

    public Productosapartados obtenerUltimoIdaverestewey(String id) {
        Productosapartados bean = new Productosapartados();
        String sql = "FROM Productosapartados ORDER by idproductosapartados DESC LIMIT 1";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean = (Productosapartados) session.createQuery(sql).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al obtener el ultimo registro de Productosapartados ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return bean;
    }

    //consultar productos  pendientes  apartados y pagados no entregados
    public List<Object> consultarTodosPendientes() {
        List<Object> listaObj = new ArrayList<Object>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Productosapartados where status!='Pagado entregado'").list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos pendientes " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
    
    public List<Pagos> consultarTodosPorDia(String fecha) {
           // List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
       List<Pagos> listaObj = new ArrayList<Pagos>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //listaObj = session.createQuery("from Productosapartados where status='Pagado entregado' and fecharegistro='"+fecha+"'" ).list();
            listaObj = session.createQuery("from Pagos where  fecharegistro='"+fecha+"'" ).list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos pendientes " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
    
    public List<Pagos> consultarTodosPorSemana(String fechaInicio,String fechafinal) {
         List<Pagos> listaObj = new ArrayList<Pagos>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Pagos where  fecharegistro>='"+fechaInicio+"' and fecharegistro<='"+fechafinal+"'" ).list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos pendientes " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
    public List<Pagos> consultarTodosPorAño(String fechaInicio,String fechafinal) {
      List<Pagos> listaObj = new ArrayList<Pagos>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Pagos where fecharegistro>='"+fechaInicio+"' and fecharegistro<='"+fechafinal+"'" ).list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos pendientes " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
     public List<Productosapartados> consultarMesConMasVentas(String fechaInicio,String fechafinal) {
        List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Productosapartados where status='Pagado entregado' and fecharegistro>='"+fechaInicio+"' and fecharegistro<='"+fechafinal+"'" ).list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos pendientes " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
    
          public List<Deudatotal> consultarTodosProApartdosPorId(String id) {
       // List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
       List<Deudatotal> listaObj = new ArrayList<Deudatotal>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //listaObj = session.createQuery("from Productosapartados where status='Pagado entregado' and fecharegistro='"+fecha+"'" ).list();
            listaObj = session.createQuery("from Deudatotal where  iddeudatotal='"+id+"'" ).list();

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos pendientes " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return listaObj;
    }
}
