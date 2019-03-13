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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import servicios.conexion;
import util.NewHibernateUtil;
import validaciones.validarCampos;

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

    public Object consultaEspecificaSession(String usuario, String contra) {
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
    //nuevos meotodos 2019 cesar

    public Usuarios consultaEspecificaSession2019(String usuario, String contra) {
        Usuarios bean = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query hql = session.createQuery("select u.idusuarios,u.nombre,u.apaterno,u.amaterno,u.usuario,u.contra from Usuarios as u where u.usuario='" + usuario + "' and u.contra='" + contra + "'");

            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    bean = new Usuarios();
                    String id = listaRes.get(i)[0] + "";
                    String nombre = listaRes.get(i)[1] + "";
                    String apaterno = listaRes.get(i)[2] + "";
                    String amaterno = listaRes.get(i)[3] + "";
                    String usuario2 = listaRes.get(i)[4] + "";
                    String contra2 = listaRes.get(i)[5] + "";

                    bean.setIdusuarios(Integer.parseInt(id));
                    bean.setNombre(nombre);
                    bean.setApaterno(apaterno);
                    bean.setAmaterno(amaterno);
                    bean.setUsuario(usuario2);
                    bean.setContra(contra2);

                }
            } else {

            }

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error daoUsuario consultaEspecificaSession2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return bean;
    }

    public Usuarios consultaEspecifica2019(String id) {
        Usuarios bean = new Usuarios();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query hql = session.createQuery("select u.idusuarios,u.nombre,u.apaterno,u.amaterno,u.usuario,u.contra from Usuarios as u where u.idusuarios='" + id + "'");

            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    bean = new Usuarios();
                    String idPro = listaRes.get(i)[0] + "";
                    String nombre = listaRes.get(i)[1] + "";
                    String apaterno = listaRes.get(i)[2] + "";
                    String amaterno = listaRes.get(i)[3] + "";
                    String usuario2 = listaRes.get(i)[4] + "";
                    String contra2 = listaRes.get(i)[5] + "";

                    bean.setIdusuarios(Integer.parseInt(idPro));
                    bean.setNombre(nombre);
                    bean.setApaterno(apaterno);
                    bean.setAmaterno(amaterno);
                    bean.setUsuario(usuario2);
                    bean.setContra(contra2);

                }
            } else {

            }
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

    public List<Usuarios> consultarTodos2019() {
        List<Usuarios> lista = new ArrayList<Usuarios>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select u.idusuarios,u.nombre,u.apaterno,u.amaterno,u.usuario,u.contra from Usuarios as u");

            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    Usuarios bean = new Usuarios();
                    String idPro = listaRes.get(i)[0] + "";
                    String nombre = listaRes.get(i)[1] + "";
                    String apaterno = listaRes.get(i)[2] + "";
                    String amaterno = listaRes.get(i)[3] + "";
                    String usuario2 = listaRes.get(i)[4] + "";
                    String contra2 = listaRes.get(i)[5] + "";

                    bean.setIdusuarios(Integer.parseInt(idPro));
                    bean.setNombre(nombre);
                    bean.setApaterno(apaterno);
                    bean.setAmaterno(amaterno);
                    bean.setUsuario(usuario2);
                    bean.setContra(contra2);
                    lista.add(bean);

                }
            }

            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos los usuarios" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return lista;
    }
    
    public Usuarios consultaEspecificaPorUsuario2019(String usuario) {
        Usuarios bean = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

           Query  hql=session.createQuery("select u.usuario,u.nombre from  Usuarios as u where u.usuario='"+usuario+"'");
            
           List<Object[]>listaRes=hql.list();
            if (listaRes.size()>0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    bean = new Usuarios();
                    String usu=listaRes.get(i)[0]+"";
                    String nombre=listaRes.get(i)[1]+"";
                    bean.setUsuario(usuario);
                    bean.setNombre(nombre);
                }
            }else{
                
            }
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error daousuarios consultaEspecificaPorUsuario2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return bean;
    }
    
    public List<String> consultarPruebas2019(String fechaActual){
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        boolean ban=false;
        String respuesta="";
         List<String>lista=new ArrayList<String>();
        try {
            Query hql=session.createQuery("select f.idfechaspruebas,c.nombrecompleto from  Fechaspruebas as f inner  join  f.productosapartados as proA inner  join proA.clientes as c where f.fechaprueba2='"+fechaActual+"' and proA.status='Apartado' ");
            List<Object[]>listaRes1=hql.list();
            if (listaRes1.size()>0) {
               for (int i = 0; i < listaRes1.size(); i++) {
                  String nombre=listaRes1.get(i)[1]+"";
                  lista.add(nombre);
                }
            }else{
                
            }
            
           
            
            
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error daousuarios consultarPruebas2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        
        return lista;
    }
    
    
     public  List<String> consultarEventos2019(String mes,String año){
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        boolean ban=false;
        String respuesta="";
        List<String>lista=new ArrayList<String>();
        try {
            
            
            Query hql1=session.createQuery("select f.idfechaspruebas,c.nombrecompleto from  Fechaspruebas as f inner  join  f.productosapartados as proA inner join proA.clientes as c where  month(fechaevento2)='"+mes+"' and year(fechaevento2)='"+año+"'  and  proA.status='Pagado no entregado' or  month(fechaevento2)='"+mes+"' and year(fechaevento2)='"+año+"'  and  proA.status='Apartado'");
            List<Object[]>listaRes1=hql1.list();
            if (listaRes1.size()>0) {
                for (int i = 0; i < listaRes1.size(); i++) {
                  String nombre=listaRes1.get(i)[1]+"";
                  lista.add(nombre);
                }
                
            }else{
                
            }
            
            
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error daousuarios consultarEventos2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
       return lista; 
    }
     
      public boolean  consultarDineroEnCaja(   ){
          String arreDatos[] = new validarCampos().obtenerFechaActual().split("-");
        String dia = arreDatos[0];
        String mes = arreDatos[1];
        String año = arreDatos[2];
        String fecha = año + "-" + mes + "-" + dia;
          boolean ban=false;
         Connection con=null;
         conexion c= new conexion();
         con=c.getConnection();
         
         try {
             String  sqlConsulta="select cantidad from caja where fecharegistro='"+fecha+"'";
             System.out.println("sql  consulta "  + sqlConsulta);
             PreparedStatement ps= con.prepareStatement(sqlConsulta);
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 ban=true;
             }
             
             
         } catch (Exception e) {
             System.out.println("fallo registro dinero en caja " + e.getMessage());
         }
         return ban;
     }
     
     public void  registrarDineroEnCaja(int cantidad,String nombre    ){
         Connection con=null;
         conexion c= new conexion();
         con=c.getConnection();
         
         try {
             
             String sql="insert into caja(cantidad,fecharegistro,usuario)"
                     + "values('"+cantidad+"',now(),'"+nombre+"')";
             System.out.println("sql "+sql);
             PreparedStatement  ps=con.prepareStatement(sql);
             
             ps.executeUpdate();
             System.out.println("registro exitoso dinero caja");
             
         } catch (Exception e) {
             System.out.println("fallo registro dinero en caja " + e.getMessage());
         }
     }
}
