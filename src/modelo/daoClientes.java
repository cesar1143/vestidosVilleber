/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Clientes;
import beans.Deudatotal;
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
    
    public Clientes consultaEspecificaPorNombreBean2019(String nombre2) {
        
        Clientes bean = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select c.idclientes,c.nombrecompleto,c.telefono,c.fecharegistro from Clientes as c where c.nombrecompleto='" + nombre2 + "'");
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    bean = new Clientes();
                    String id = listaRes.get(i)[0] + "";
                    String nombre = listaRes.get(i)[1] + "";
                    String telefono = listaRes.get(i)[2] + "";
                    String fechaRegistro = listaRes.get(i)[3] + "";
                    
                    bean.setIdclientes(Integer.parseInt(id));
                    bean.setNombrecompleto(nombre);
                    bean.setTelefono(telefono);
                    bean.setFecharegistro(fechaRegistro);
                }
                
            } else {
                
            }
            
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error adaoClientes consultaEspecificaPorNombreBean2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return bean;
        
    }
    
    public Deudatotal obtnerDeudaXIdCliente(String idCliente) {
        Deudatotal bean = null;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            Query hqlDeuda = session.createQuery("select d.iddeudatotal,d.deudatotal,d.status,d.fecharegistro from Deudatotal as d inner join d.clientes as c where c.idclientes='" + idCliente + "' and d.status='No pagado' ");
            List<Object[]> listaDeuda = hqlDeuda.list();
            if (listaDeuda.size() > 0) {
                for (int j = 0; j < listaDeuda.size(); j++) {
                    bean = new Deudatotal();
                    String iddeuda = listaDeuda.get(j)[0] + "";
                    String deuda = listaDeuda.get(j)[1] + "";
                    String status = listaDeuda.get(j)[2] + "";
                    String fechaRegistro = listaDeuda.get(j)[3] + "";
                    bean.setIddeudatotal(Integer.parseInt(iddeuda));
                    bean.setDeudatotal(Integer.parseInt(deuda));
                    bean.setStatus(status);
                    bean.setFecharegistro(fechaRegistro);
                    
                }
            } else {
                
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error daoClientes obtnerDeudaXIdCliente " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return bean;
    }
    
    public List<Pagos> obtnerPagosXidDeuda(String idDeuda) {
        List<Pagos>  lista=new ArrayList<Pagos>();
        Pagos bean = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select p.idpagos,p.abono,p.fecharegistro from Pagos as p inner join p.deudatotal as d where d.iddeudatotal='" + idDeuda + "'");
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    bean=new Pagos();
                    String idPagos = listaRes.get(i)[0] + "";
                    String abono = listaRes.get(i)[1] + "";
                    String fechaRegistro = listaRes.get(i)[2] + "";
                    bean.setIdpagos(Integer.parseInt(idPagos));
                    bean.setAbono(Integer.parseInt(abono));
                    bean.setFecharegistro(fechaRegistro);
                    lista.add(bean);
                }
            } else {
                
            }
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error daoClientes obtnerDeudaXIdCliente " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return lista;
    }
}
