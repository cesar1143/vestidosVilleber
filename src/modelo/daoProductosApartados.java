/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import NuevasPantallas.principal;
import almacenamientoDeListas.almacenamientoDeListas;
import beans.Clientes;
import beans.Deudatotal;
import beans.Medidas;
import beans.Pagos;
import beans.Productos;
import beans.Productosapartados;
import beans.Usuarios;
import interfaces.metodosDao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.BinaryStream;

import servicios.conexion;
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
        boolean ban = false;
        Productosapartados beanPA = (Productosapartados) bean;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(beanPA);
            transaction.commit();
            ban = true;

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar medidas " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
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
    //consultar productos  pendientes  apartados y pagados no entregados

    public List<Productosapartados> llenarTablaPendientesNew(String idCliente) {
        List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select proA.idproductosapartados,proA.fecharegistro,proA.status,proA.cantidadVenta,proA.detallesproducto,usu.idusuarios,pro.clave,usu.nombre from  Productosapartados as proA inner join   proA.clientes as cli inner join proA.usuarios as usu  inner join proA.productos as pro   where proA.status!='Pagado entregado' and cli.idclientes='" + idCliente + "'");

            List<Object[]> lista = hql.list();

            for (int i = 0; i < lista.size(); i++) {

                String idProApa = lista.get(i)[0] + "";
                Query hqlFechas = session.createQuery("select f.idfechaspruebas,f.fechaprueba,f.fechaevento from Fechaspruebas as f inner join f.productosapartados as proA where proA.idproductosapartados='" + idProApa + "'");
                List<Object[]> listaFechas = hqlFechas.list();
                Productosapartados bean = new Productosapartados();
                if (listaFechas.size() > 0) {
                    for (int j = 0; j < listaFechas.size(); j++) {
                        String idFecha = listaFechas.get(j)[0] + "";
                        String fechaPrueba = listaFechas.get(j)[1] + "";
                        String fechaEvento = listaFechas.get(j)[2] + "";
                        if (fechaPrueba.isEmpty()) {
                            bean.setFechaPrueba("Sin Fecha");
                        } else {
                            bean.setFechaPrueba(fechaPrueba);
                        }
                        if (fechaEvento.isEmpty()) {
                            bean.setFechaEvento("Sin fecha");
                        } else {
                            bean.setFechaEvento(fechaEvento);
                        }
                        bean.setIdFechas(idFecha);

                    }
                } else {
                    bean.setIdFechas("0");
                    bean.setFechaPrueba("Sin Fecha");
                    bean.setFechaEvento("Sin fecha");
                }
                /*String producto_id = lista.get(i)[1];
                 String cliente_id = lista.get(i)[2];*/
                String fechaRegistro = lista.get(i)[1] + "";
                String status = lista.get(i)[2] + "";
                String cantidadVenta = lista.get(i)[3] + "";
                String detallesproducto = lista.get(i)[4] + "";
                String usuario_id = lista.get(i)[5] + "";
                String clave = lista.get(i)[6] + "";
                String nombrecompleto = lista.get(i)[7] + "";

                bean.setIdproductosapartados(Integer.parseInt(idProApa));
                bean.setFecharegistro(fechaRegistro);
                bean.setStatus(status);
                bean.setCantidadVenta(Integer.parseInt(cantidadVenta));
                bean.setDetallesproducto(detallesproducto);
                bean.setClave(clave);
                bean.setNombrecompleto(nombrecompleto);

                listaObj.add(bean);

            }

            transaction.commit();

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error en daoProductosApartados llenarTablaPendientesNew " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            listaObj = session.createQuery("from Pagos where  fecharegistro='" + fecha + "'").list();

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

    public List<Pagos> consultarTodosPorSemana(String fechaInicio, String fechafinal) {
        List<Pagos> listaObj = new ArrayList<Pagos>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Pagos where  fecharegistro>='" + fechaInicio + "' and fecharegistro<='" + fechafinal + "'").list();

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

    public List<Pagos> consultarTodosPorAño(String fechaInicio, String fechafinal) {
        List<Pagos> listaObj = new ArrayList<Pagos>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Pagos where fecharegistro>='" + fechaInicio + "' and fecharegistro<='" + fechafinal + "'").list();

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

    public List<Productosapartados> consultarMesConMasVentas(String fechaInicio, String fechafinal) {
        List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listaObj = session.createQuery("from Productosapartados where status='Pagado entregado' and fecharegistro>='" + fechaInicio + "' and fecharegistro<='" + fechafinal + "'").list();

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
            listaObj = session.createQuery("from Deudatotal where  iddeudatotal='" + id + "'").list();

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

    public List<Clientes> consultarClienteConDeuda() {
        List<Clientes> lista = new ArrayList<Clientes>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select distinct cli.idclientes,cli.nombrecompleto,cli.telefono from Productosapartados as c  inner join c.clientes as cli where c.status!='Pagado entregado' order by cli.nombrecompleto");

            List<Object[]> listaRes = hql.list();
            for (int i = 0; i < listaRes.size(); i++) {
                String id = listaRes.get(i)[0] + "";
                String nombre = listaRes.get(i)[1] + "";
                String telefono = listaRes.get(i)[2] + "";

                Clientes bean = new Clientes();
                bean.setIdclientes(Integer.parseInt(id));
                bean.setNombrecompleto(nombre);
                bean.setTelefono(telefono);

                lista.add(bean);

            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error en daoproductosapartados consultarClienteConDeuda" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return lista;

    }

    public List<Clientes> buscarClienteFiltrado(String txtNombre) {
        List<Clientes> lista = new ArrayList<Clientes>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select distinct cli.idclientes,cli.nombrecompleto,cli.telefono from Productosapartados as c  inner join c.clientes as cli where c.status!='Pagado entregado' and cli.nombrecompleto like '" + txtNombre + "%' order by cli.nombrecompleto");

            List<Object[]> listaRes = hql.list();
            for (int i = 0; i < listaRes.size(); i++) {
                String id = listaRes.get(i)[0] + "";
                String nombre = listaRes.get(i)[1] + "";
                String telefono = listaRes.get(i)[2] + "";

                Clientes bean = new Clientes();
                bean.setIdclientes(Integer.parseInt(id));
                bean.setNombrecompleto(nombre);
                bean.setTelefono(telefono);

                lista.add(bean);

            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error en daoproductosapartados buscarClienteFiltrado" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return lista;

    }

    //nuevo metodos cesar 2019
    public boolean editarSoloEstatusProApartadpos(String estadoPro, String idPro) {
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("update Productosapartados as p set p.status='" + estadoPro + "'  where p.idproductosapartados='" + idPro + "'");
            int r = hql.executeUpdate();
            ban = true;
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  daoproductosapartados editarSoloEstatusProApartadpos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return ban;

    }

    public boolean btnCancelarProdcutosUnoPorUno2019(String idProApartados) {
        boolean ban = false;
        try {
            System.out.println("llego men  " + idProApartados);
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //primero  eliminamos las medidas y fechas
            Query hql = session.createQuery("delete from Medidas as m where m.productosapartados.idproductosapartados='" + idProApartados + "'");
            hql.executeUpdate();
            Query hqlFechas = session.createQuery("delete from Fechaspruebas as p where p.productosapartados.idproductosapartados='" + idProApartados + "'");
            hqlFechas.executeUpdate();
            Query hqlProApartados = session.createQuery("delete from Productosapartados as proA where proA.idproductosapartados='" + idProApartados + "'");

            hqlProApartados.executeUpdate();
            System.out.println("simon1");
            //descontamos el costo del precio del producto sobre la deuda total
            transaction.commit();
            ban = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  daoproductosapartados btnCancelarProdcutosUnoPorUno2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return ban;
    }

    public boolean editarDeudaTotal2019(String idProApartados, int cantidad) {
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //necesitamos el precio del producto
            //necesitamos traer la deuda total del cliente y restarle el precio del producto
            //regresamos el producto 
            Query hql = session.createQuery("select c.idclientes,p.precio,p.idproductos,p.cantidad from Productosapartados as proA  inner join proA.productos as p inner join proA.clientes as c  where proA.idproductosapartados='" + idProApartados + "'");
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    String idCliente = listaRes.get(i)[0] + "";
                    String precio = listaRes.get(i)[1] + "";
                    String idPro = listaRes.get(i)[2] + "";
                    int cantidadPro = Integer.parseInt(listaRes.get(i)[3] + "");
                    //devolvemo el producto a tbproducts
                    cantidadPro = cantidadPro + cantidad;
                    Query hqlPro = session.createQuery("update Productos as p set p.cantidad='" + cantidadPro + "'  where p.idproductos='" + idPro + "'");
                    hqlPro.executeUpdate();
                    System.out.println("editamos el pro " + idPro);
//obtenemos el id de  la deuda total
                    Query hqlDeuda = session.createQuery("select d.deudatotal,d.iddeudatotal from Deudatotal as d inner join d.clientes as c where c.idclientes='" + idCliente + "'  ");
                    List<Object[]> listaDeuda = hqlDeuda.list();
                    if (listaDeuda.size() > 0) {
                        for (int j = 0; j < 10; j++) {
                            int deudaTotal = Integer.parseInt(listaDeuda.get(i)[0] + "");
                            deudaTotal = deudaTotal - (Integer.parseInt(precio) * cantidad);
                            String idDeuda = listaDeuda.get(i)[1] + "";
                            //editamos la deuda total
                            Query hqlD = session.createQuery("update Deudatotal as d set d.deudatotal='" + deudaTotal + "' where d.iddeudatotal='" + idDeuda + "'");
                            hqlD.executeUpdate();
                            ban = true;
                        }
                    } else {

                    }

                }
            } else {

            }
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  daoproductosapartados editarDeudaTotal2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return ban;
    }

    public boolean editarDeudaTotaYEstatusl2019(String idProApartados, int cantidad) {
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //necesitamos el precio del producto
            //necesitamos traer la deuda total del cliente y restarle el precio del producto
            Query hql = session.createQuery("select c.idclientes,p.precio,p.idproductos,p.cantidad  from Productosapartados as proA  inner join proA.productos as p inner join proA.clientes as c  where proA.idproductosapartados='" + idProApartados + "'");
            System.out.println("hql " + hql);
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {

                    String idCliente = listaRes.get(i)[0] + "";
                    String precio = listaRes.get(i)[1] + "";
                    String idPro = listaRes.get(i)[2] + "";
                    int cantidadPro = Integer.parseInt(listaRes.get(i)[3] + "");
                    //devolvemo el producto a tbproducts

                    cantidadPro = cantidadPro + cantidad;
                    Query hqlPro = session.createQuery("update Productos as p set p.cantidad='" + cantidadPro + "'  where p.idproductos='" + idPro + "'");
                    hqlPro.executeUpdate();

                    //obtenemos el id de  la deuda total
                    Query hqlDeuda = session.createQuery("select d.deudatotal,d.iddeudatotal from Deudatotal as d inner join d.clientes as c where c.idclientes='" + idCliente + "'  ");

                    List<Object[]> listaDeuda = hqlDeuda.list();
                    if (listaDeuda.size() > 0) {
                        for (int j = 0; j < 10; j++) {
                            int deudaTotal = Integer.parseInt(listaDeuda.get(i)[0] + "");
                            deudaTotal = deudaTotal - (Integer.parseInt(precio) * cantidad);
                            String idDeuda = listaDeuda.get(i)[1] + "";
                            //editamos la deuda total
                            Query hqlD = session.createQuery("update Deudatotal as d set d.deudatotal='" + deudaTotal + "',d.status='Pagado' where d.iddeudatotal='" + idDeuda + "'");
                            hqlD.executeUpdate();
                            ban = true;
                        }
                    } else {

                    }

                }
            } else {
                System.out.println("no trae nada");
            }
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  daoproductosapartados editarDeudaTotaYEstatusl2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return ban;
    }

    public Productos consultaParaEditarPro2019(String valorClave) {
        List<Productos> lista = new ArrayList<Productos>();
        Productos bean = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // Query hql = session.createQuery("select p.idproductos,p.clave,p.precio,p.color,p.tipo,p.fecharegistro,p.foto,p.descripcion,p.cantidad,p.nombre  from Productos as p where p.clave='" + valorClave + "'");
            Query hql = session.createQuery("select p.idproductos,p.foto,proA.detallesproducto  from Productosapartados as proA inner join proA.productos as p where proA.idproductosapartados='" + valorClave + "'");

            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    String idpro = listaRes.get(i)[0] + "";
                    /*String clave = listaRes.get(i)[1] + "";
                    String precio = listaRes.get(i)[2] + "";
                    String color = listaRes.get(i)[3] + "";
                    String tipo = listaRes.get(i)[4] + "";
                    String fechaRegistro = listaRes.get(i)[5] + "";*/
                    byte[] foto = (byte[]) listaRes.get(i)[1];
                    String descripcion = listaRes.get(i)[2] + "";
                    /*String cantidad = listaRes.get(i)[8] + "";
                    String nombre = listaRes.get(i)[9] + "";*/
                    bean = new Productos();
                    bean.setIdproductos(Integer.parseInt(idpro));
                    /*bean.setClave(clave);
                    bean.setPrecio(Integer.parseInt(precio));
                    bean.setColor(color);
                    bean.setTipo(tipo);
                    bean.setFotoString(fechaRegistro);*/
                    bean.setFoto(foto);
                    bean.setDescripcion(descripcion);
                    /* bean.setCantidad(Integer.parseInt(cantidad));
                    bean.setNombre(nombre);*/
                    lista.add(bean);
                }
            } else {

            }

            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  daoproductosapartados consultaParaEditarPro2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return bean;
    }

    public BufferedImage getImg(byte[] foto, boolean isThumbnail) throws IOException {

        BufferedImage imagenes = ImageIO.read(new ByteArrayInputStream(foto));

        return imagenes;

    }

    //============== Modificar Productos foto actual =================================
    public boolean modificarProductoFotoActual2019(Productos bean, String idProA) {
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //primero en productos
            //Query hql=session.createQuery("")
            System.out.println("bean " + bean.getDescripcion());
            //slolo modifica la des despues en tb  proapartados solo la descripcion
            Query hql = session.createQuery("update Productosapartados as proA set proA.detallesproducto='" + bean.getDescripcion() + "' where proA.idproductosapartados='" + idProA + "'");
            hql.executeUpdate();
            ban = true;
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductosApartados modificarProductoFotoActual2019 " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return ban;

    }

    public boolean modificarProductoFotoNueva2019(Productos bean, String idProA) {
        boolean ban = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //primero en productos
            //Query hql=session.createQuery("")

            System.out.println("bean foto " + bean.getFotoString());
            FileInputStream archivoFoto = new FileInputStream(bean.getFotoString());

            //ps.setBinaryStream(5, archivoFoto);
            /* Query hqlPro = session.createQuery("update Productos as p set   p.foto='" + b.getInputStream() + "'  where p.idproductos='" + bean.getIdproductos() + "'");
            hqlPro.executeUpdate();*/
            modificarProductoConFoto(bean);
            System.out.println("modificmaos foto " + bean.getIdproductos());
            //slolo modifica la des despues en tb  proapartados solo la descripcion
            Query hql = session.createQuery("update Productosapartados as proA set   proA.detallesproducto='" + bean.getDescripcion() + "' where proA.idproductosapartados='" + idProA + "'");
            hql.executeUpdate();
            ban = true;
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductosApartados modificarProductoFotoNueva2019 " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return ban;

    }

    public boolean modificarProductoConFoto(Productos bean) {
        boolean ban = false;
        String sql = "update productos set foto=? where idproductos=?";
        Connection con = null;
        try {
            con = conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            FileInputStream archivoFoto = new FileInputStream(bean.getFotoString());
            System.out.println("archivoFoto " + archivoFoto);
            ps.setBinaryStream(1, archivoFoto);

            ps.setInt(2, bean.getIdproductos());
            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje daoproductosApartados modificarProductoConFoto " + e.getMessage());
        }
        return ban;

    }

    public Productosapartados consultaEspecifica2019(String id) {
        Productosapartados bean = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select proA.idproductosapartados,proA.fecharegistro,proA.status,proA.cantidadVenta,proA.detallesproducto from Productosapartados as proA where  proA.idproductosapartados='" + id + "'");
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {
                    bean = new Productosapartados();
                    String idProA = listaRes.get(i)[0] + "";
                    String fecha = listaRes.get(i)[1] + "";
                    String status = listaRes.get(i)[2] + "";
                    String cantida = listaRes.get(i)[3] + "";
                    String detalles = listaRes.get(i)[4] + "";
                    bean.setIdproductosapartados(Integer.parseInt(idProA));
                    bean.setFecharegistro(fecha);
                    bean.setCantidadVenta(Integer.parseInt(cantida));
                    bean.setDetallesproducto(detalles);

                }
            } else {

            }
            transaction.commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error daoproductoapartados consultaEspecifica2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return bean;
    }

    public List<Pagos> consultarTodosPorDia2019(String fecha) {
        // List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
        List<Pagos> lista = new ArrayList<Pagos>();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select p.idpagos,p.abono,p.fecharegistro,p.deudatotal.iddeudatotal from Pagos as p where p.fecharegistro2='" + fecha + "' order by p.fecharegistro2");
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {

                    String idPagos = listaRes.get(i)[0] + "";

                    String abono = listaRes.get(i)[1] + "";
                    String fechaRe = listaRes.get(i)[2] + "";
                    String idDeuda = listaRes.get(i)[3] + "";
                    String nombre = "";
                    Query hqlCliente = session.createQuery("select c.nombrecompleto,c.telefono from Deudatotal  as d inner join d.clientes as c where d.iddeudatotal='" + idDeuda + "' ");
                    List<Object[]> listaResCli = hqlCliente.list();
                   
                    if (listaResCli.size()>0) {
                       for (int j = 0; j < listaResCli.size(); j++) {
                        nombre=listaResCli.get(j)[0]+"";
                    } 
                    }else{
                        
                    }
                    
                    Pagos p = new Pagos();
                    p.setIdpagos(Integer.parseInt(idPagos));
                    p.setAbono(Integer.parseInt(abono));
                    p.setFecharegistro(fechaRe + "," + idDeuda + "," + nombre);
                    lista.add(p);
                }
            } else {

            }
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error daoproductosapartados  consultarTodosPorDia2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return lista;
    }
    
    public List<Pagos> consultarTodosPorSemana2019(String fecha,String fechaFinal) {
        // List<Productosapartados> listaObj = new ArrayList<Productosapartados>();
        List<Pagos> lista = new ArrayList<Pagos>();
        try {
            
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query hql = session.createQuery("select p.idpagos,p.abono,p.fecharegistro,p.deudatotal.iddeudatotal,p.fecharegistro2 from Pagos as p where p.fecharegistro2>='" + fecha + "' and p.fecharegistro2<='" + fechaFinal + "' order by  p.fecharegistro2 ");
            
            List<Object[]> listaRes = hql.list();
            if (listaRes.size() > 0) {
                for (int i = 0; i < listaRes.size(); i++) {

                    String idPagos = listaRes.get(i)[0] + "";

                    String abono = listaRes.get(i)[1] + "";
                    String fechaRe = listaRes.get(i)[2] + "";
                    String idDeuda = listaRes.get(i)[3] + "";
                    String nombre = "";
                    Query hqlCliente = session.createQuery("select c.nombrecompleto,c.telefono from Deudatotal  as d inner join d.clientes as c where d.iddeudatotal='" + idDeuda + "' ");
                    List<Object[]> listaResCli = hqlCliente.list();
                   
                    if (listaResCli.size()>0) {
                       for (int j = 0; j < listaResCli.size(); j++) {
                        nombre=listaResCli.get(j)[0]+"";
                    } 
                    }else{
                        
                    }
                    
                    Pagos p = new Pagos();
                    p.setIdpagos(Integer.parseInt(idPagos));
                    p.setAbono(Integer.parseInt(abono));
                    p.setFecharegistro(fechaRe + "," + idDeuda + "," + nombre);
                    lista.add(p);
                }
            } else {

            }
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error daoproductosapartados  consultarTodosPorDia2019 " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }

        return lista;
    }
    
    public int consultarCaja(String fecha){
            int dinero=0;
         Connection con=null;
         conexion c= new conexion();
         con=c.getConnection();
         
         try {
             String  sqlConsulta="select cantidad from caja where fecharegistro=NOW()";
             PreparedStatement ps= con.prepareStatement(sqlConsulta);
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                dinero=rs.getInt("cantidad");
             }
             
             
         } catch (Exception e) {
             System.out.println("fallo registro dinero en caja " + e.getMessage());
         }
         return dinero;
    }
}
