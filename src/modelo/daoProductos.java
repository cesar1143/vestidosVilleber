/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import ModeloProductos.productos;
import NuevasPantallas.principal;
import beans.Productos;
import beans.Usuarios;
import interfaces.metodosDao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import NuevasPantallas.principal;
import beans.Medidas;
import beans.Productosapartados;
import org.hibernate.Query;
import pantallas.principalUsuarios;
import servicios.conexion;
import util.NewHibernateUtil;

/**
 *
 * @author famsa
 */
public class daoProductos implements metodosDao {
    
    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;
    
    Productos bean = null;
    
    boolean ban = false;

    //SQL
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean registrar(Object obj) {
        try {
            Productos bean = (Productos) obj;
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(bean);
            transaction.commit();
            ban = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el producto" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
                
            }
        }
        return ban;
    }
    
    public boolean registrarSQL(Productos bean) {
        
        boolean ban = false;
        int prueba = 1;
        String sql = "insert into productos (clave,precio,color,tipo,fecharegistro,foto,descripcion,cantidad,nombre,usuario_id) values(?,?,?,?,NOW(),?,?,?,?,'" + principal.idUsuario + "')";
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());
            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            if (bean.getFotoString() == null || bean.getFotoString().equalsIgnoreCase("null")) {
                ps.setBinaryStream(5, null);
                
            } else {
                FileInputStream archivoFoto = new FileInputStream(bean.getFotoString());
                ps.setBinaryStream(5, archivoFoto);
            }
            ps.setString(6, bean.getDescripcion());
            ps.setInt(7, bean.getCantidad());
            ps.setString(8, bean.getNombre());
            
            ban = ps.executeUpdate() == 1;
            ban = true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos RegistrarProductos " + e.getMessage());
        }
        return ban;
    }
    
    public boolean registrarSQL2(Productos bean) {
        
        boolean ban = false;
        int prueba = 1;
        String sql = "insert into productos (clave,precio,color,tipo,fecharegistro,foto,descripcion,cantidad,nombre,usuario_id) values(?,?,?,?,NOW(),?,?,?,?,'" + principalUsuarios.idUSuario + "')";
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());
            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            if (bean.getFotoString() == null || bean.getFotoString().equalsIgnoreCase("null")) {
                ps.setBinaryStream(5, null);
                
            } else {
                FileInputStream archivoFoto = new FileInputStream(bean.getFotoString());
                ps.setBinaryStream(5, archivoFoto);
            }
            ps.setString(6, bean.getDescripcion());
            ps.setInt(7, bean.getCantidad());
            ps.setString(8, bean.getNombre());
            
            ban = ps.executeUpdate() == 1;
            ban = true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos RegistrarProductos " + e.getMessage());
        }
        return ban;
    }
    
    public Productos consultaEspecifica12(String id) {
        Productos bean = new Productos();
        String sql = "select foto,nombre,descripcion,clave,precio from productos where idproductos=? ";
        
        try {
            con = conexion.getConnection();
            
            ps = con.prepareStatement(sql);
//            System.out.println("soy id pro " + idpro);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);
                bean.setClave(rs.getString("clave"));
                bean.setPrecio(rs.getInt("precio"));
                bean.setNombre(rs.getString("nombre"));
                bean.setDescripcion(rs.getString("descripcion"));
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImage " + e);
        }
        return bean;
    }
    
    public Productos consultaEspecifica12PorClave(String clave) {
        Productos bean = new Productos();
        String sql = "select foto,nombre,descripcion,clave,precio from productos where clave=? ";
        
        try {
            con = conexion.getConnection();
            
            ps = con.prepareStatement(sql);
//            System.out.println("soy id pro " + idpro);
            ps.setString(1, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);
                bean.setClave(rs.getString("clave"));
                bean.setPrecio(rs.getInt("precio"));
                bean.setNombre(rs.getString("nombre"));
                bean.setDescripcion(rs.getString("descripcion"));
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImage " + e);
        }
        return bean;
    }
    
    @Override
    public Object consultaEspecifica(String id) {
        // System.out.println("dao "+ id);
        Productos bean = new Productos();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            bean = (Productos) session.createQuery("from  Productos  where idproductos='" + id + "'").uniqueResult();
            transaction.commit();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error consulta especifica " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
                
            }
        } finally {
            session.close();
        }
        //System.out.println("bean dao " + bean.getClave());
        return bean;
    }
    
    public Object consultaEspecificaPorClave(String clave) {
        Productos bean = new Productos();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            bean = (Productos) session.createQuery("from  Productos  where clave='" + clave + "'").uniqueResult();
            transaction.commit();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error consulta especifica por clave " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            listaObj = session.createQuery("from Productos where cantidad>0").list();
            
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
                
            }
        } finally {
            session.close();
        }
        
        return listaObj;
    }
    //metodo consultar todos donde cantidad sea igual a 0

    public List<Object> consultarTodosProAgotados() {
        List<Object> listaObj = new ArrayList<Object>();
        try {
            
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Productos where cantidad=0").list();
            
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
                
            }
        } finally {
            session.close();
        }
        
        return listaObj;
    }

    //metodo para buscar por nombre con like
    public List<Productos> consultarTodosProAgotadosLike(String clave, String nombre, String descripcion) {
        List<Productos> listaPro = new ArrayList<Productos>();
        try {
            
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaPro = session.createQuery("from Productos where  clave like '" + clave + "%" + "' and cantidad=0 or nombre like '" + nombre + "%" + "' and cantidad=0 or descripcion like '" + descripcion + "%" + "' and cantidad=0").list();
            
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar todos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
                
            }
        } finally {
            session.close();
        }
        
        return listaPro;
    }
    //============== Modificar Productos con nueva foto =================================

    public boolean modificarProductoConFoto(Productos bean) {
        boolean ban = false;
        String sql = "update productos set clave=?,precio=?,color=?,tipo=?,foto=?,descripcion=?,cantidad=?,nombre=?,usuario_id='" + principal.idUsuario + "' where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());
            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            FileInputStream archivoFoto = new FileInputStream(bean.getFotoString());
            ps.setBinaryStream(5, archivoFoto);
            ps.setString(6, bean.getDescripcion());
            ps.setInt(7, bean.getCantidad());
            ps.setString(8, bean.getNombre());
            
            ps.setInt(9, bean.getIdproductos());
            ban = ps.executeUpdate() == 1;
            ban = true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos modificarProductoConFoto " + e.getMessage());
        }
        return ban;
        
    }

//============== Modificar Productos foto actual =================================
    public boolean modificarProductoFotoActual(Productos bean) {
        boolean ban = false;
        String sql = "update productos set clave=?,precio=?,color=?,tipo=?,descripcion=?,cantidad=?,nombre=?,usuario_id='" + principal.idUsuario + "' where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());
            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            ps.setString(5, bean.getDescripcion());
            ps.setInt(6, bean.getCantidad());
            ps.setString(7, bean.getNombre());
            
            ps.setInt(8, bean.getIdproductos());
            ban = ps.executeUpdate() == 1;
            ban = true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos modificarProductoFotoActual " + e.getMessage());
        }
        return ban;
        
    }
    
    @Override
    public boolean editar(Object bean) {
        boolean ban = false;
        try {
            Productos beanPro = (Productos) bean;
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(beanPro);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            
            session.close();
        }
        return ban;
        
    }
    
    @Override
    public boolean eliminar(Object bean) {
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(bean);
            transaction.commit();
            ban = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            
            session.close();
        }
        return ban;
    }

    //=================================== OBTENER IMAGEN TODO TIPO DE IMAGEN ================================================
    public BufferedImage getImage(Productos bean, boolean isThumbnail) throws IOException {
        
        BufferedImage imagenes = ImageIO.read(new ByteArrayInputStream(bean.getFoto()));
        
        return imagenes;
        
    }

    //Obtener el ultimo registro en apartados para registrar 
    public int obtener_id() {
        int ultimo_id = 0;
        String sql = "SELECT *\n"
                + "FROM productos\n"
                + "ORDER by idproductos DESC\n"
                + "LIMIT 1 ";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ultimo_id = rs.getInt("idproductos");
            }
        } catch (Exception e) {
        }
        return ultimo_id;
    }

    //========================================== METODOS PARA PRODUCTOS  PENDIENTES ==========================================
    public List<productos> consultarTodosPricipal() {
        List<productos> listaProCatalogo = new ArrayList<>();
        
        productos bean;
        String sql = "select idproductos,clave,nombre,descripcion,cantidad,precio from productos where cantidad>0";
        
        try {
            
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                bean = new productos();
                
                bean.setIdProductos(rs.getInt("idproductos"));
                bean.setClave(rs.getString("clave"));
                bean.setNombre(rs.getString("nombre"));
                bean.setCantidad(rs.getInt("cantidad"));
                bean.setPrecio(rs.getInt("precio"));
                bean.setDescripcion(rs.getString("descripcion"));
                listaProCatalogo.add(bean);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImageParaCatalogo()11 " + e.getMessage());
        }
        return listaProCatalogo;
    }
    
    public List<productos> consultarPorNombreClaveDescripcionLike(String dato) {
        List<productos> listaProCatalogo = new ArrayList<>();
        
        productos bean;
        String sql = "select idproductos,clave,nombre,descripcion,cantidad,precio from productos where  clave like '" + dato + "%" + "' and cantidad>0 or nombre like '" + dato + "%" + "' and cantidad>0 or descripcion like '" + dato + "%" + "' and  cantidad>0";
        
        try {
            
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                bean = new productos();
                
                bean.setIdProductos(rs.getInt("idproductos"));
                bean.setClave(rs.getString("clave"));
                bean.setNombre(rs.getString("nombre"));
                bean.setCantidad(rs.getInt("cantidad"));
                bean.setPrecio(rs.getInt("precio"));
                bean.setDescripcion(rs.getString("descripcion"));
                listaProCatalogo.add(bean);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarPorNombreClaveDescripcionLike " + e.getMessage());
        }
        return listaProCatalogo;
    }
    
    public List<productos> consultarPorNombreClaveDescripcionLikeAgotados(String dato) {
        List<productos> listaProCatalogo = new ArrayList<>();
        
        productos bean;
        String sql = "select idproductos,clave,nombre,descripcion,cantidad,precio from productos where  clave like '" + dato + "%" + "' and cantidad=0 or nombre like '" + dato + "%" + "' and cantidad=0 or descripcion like '" + dato + "%" + "' and  cantidad=0";
        // System.out.println("sql " + sql);
        try {
            
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                bean = new productos();
                
                bean.setIdProductos(rs.getInt("idproductos"));
                bean.setClave(rs.getString("clave"));
                bean.setNombre(rs.getString("nombre"));
                bean.setCantidad(rs.getInt("cantidad"));
                bean.setPrecio(rs.getInt("precio"));
                bean.setDescripcion(rs.getString("descripcion"));
                listaProCatalogo.add(bean);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarPorNombreClaveDescripcionLike " + e.getMessage());
        }
        return listaProCatalogo;
    }
    
    public List<productos> consultarTodosPricipalAgotados() {
        List<productos> listaProCatalogo = new ArrayList<>();
        
        productos bean;
        String sql = "select idproductos,clave,nombre,descripcion,cantidad,precio from productos where cantidad=0";
        
        try {
            
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                bean = new productos();
                
                bean.setIdProductos(rs.getInt("idproductos"));
                bean.setClave(rs.getString("clave"));
                bean.setNombre(rs.getString("nombre"));
                bean.setCantidad(rs.getInt("cantidad"));
                bean.setPrecio(rs.getInt("precio"));
                bean.setDescripcion(rs.getString("descripcion"));
                listaProCatalogo.add(bean);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImageParaCatalogo()11 " + e.getMessage());
        }
        return listaProCatalogo;
    }

    //************************ NUEVOS METODOS CESAR 2019 ///////////////////////////***********
    public List<Productosapartados> consultarImgYmedidas(String idproApartados) {
        List<Productosapartados> lista = new ArrayList<Productosapartados>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select p.foto,proA.detallesproducto from Productosapartados as proA inner join proA.productos as p where proA.idproductosapartados='" + idproApartados + "'");
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                
                for (int i = 0; i < listaRes.size(); i++) {
                    byte[] foto = (byte[]) listaRes.get(i)[0];
                    String detalles = listaRes.get(i)[1] + "";
                    Productosapartados bean = new Productosapartados();
                    bean.setFoto(foto);
                    bean.setDetallesproducto(detalles);

                    //y ahora buscamos las medidas
                    Query hqlMedidas = session.createQuery("select m.idmedidas,m.talle,m.sise,m.hombros,m.busto,m.largoFalda,m.anchoPuno,m.cintura,m.cadera from Medidas as m inner join m.productosapartados as proA where proA.idproductosapartados='" + idproApartados + "'");
                    List<Object[]> listaM = hqlMedidas.list();
                    Medidas m = new Medidas();
                    if (listaM.size() > 0) {
                        for (int j = 0; j < listaM.size(); j++) {
                            m.setIdmedidas(Integer.parseInt(listaM.get(j)[0] + ""));
                            m.setTalle(Float.parseFloat(listaM.get(j)[1] + ""));
                            m.setSise(Float.parseFloat(listaM.get(j)[2] + ""));
                            m.setHombros(Float.parseFloat(listaM.get(j)[3] + ""));
                            m.setBusto(Float.parseFloat(listaM.get(j)[4] + ""));
                            m.setLargoFalda(Float.parseFloat(listaM.get(j)[5] + ""));
                            m.setAnchoPuno(Float.parseFloat(listaM.get(j)[6] + ""));
                            m.setCintura(Float.parseFloat(listaM.get(j)[7] + ""));
                            m.setCadera(Float.parseFloat(listaM.get(j)[8] + ""));
                            
                        }
                        
                    } else {
                        m.setIdmedidas(0);
                        m.setTalle(Float.parseFloat("0"));
                        m.setSise(Float.parseFloat("0"));
                        m.setHombros(Float.parseFloat("0"));
                        m.setBusto(Float.parseFloat("0"));
                        m.setLargoFalda(Float.parseFloat("0"));
                        m.setAnchoPuno(Float.parseFloat("0"));
                        m.setCintura(Float.parseFloat("0"));
                        m.setCadera(Float.parseFloat("0"));
                    }
                    bean.setMedidas(m);
                    lista.add(bean);
                }
            } else {
                
            }
            transaction.commit();
            
        } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImgYmedidas " + e.getMessage());
            if (transaction!=null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return lista;
    }
    
    
      public BufferedImage getImg(byte[] foto, boolean isThumbnail) throws IOException {
        
        BufferedImage imagenes = ImageIO.read(new ByteArrayInputStream(foto));
        
        return imagenes;
        
    }
}
