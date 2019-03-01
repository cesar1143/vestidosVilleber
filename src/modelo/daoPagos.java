/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import NuevasPantallas.principal;
import beans.Clientes;
import beans.Deudatotal;
import beans.Medidas;
import beans.Pagos;
import beans.Usuarios;
import interfaces.metodosDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
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
public class daoPagos implements metodosDao {

    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    @Override
    public boolean registrar(Object obj) {
        boolean ban = false;
        Pagos bean = (Pagos) obj;
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
            JOptionPane.showMessageDialog(null, "Error alregistrar medidas ", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return ban;

    }

    @Override
    public Object consultaEspecifica(String id) {
        Pagos bean = new Pagos();
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            bean = (Pagos) session.createQuery("from Pagos  where idpagos='" + id + "'").uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar pago " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        Pagos beanP = (Pagos) bean;
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(beanP);
            transaction.commit();
            ban = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Error alregistrar medidas ", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
        return ban;
    }

    @Override
    public boolean eliminar(Object bean) {
        boolean ban = false;
        Pagos beanP = (Pagos) bean;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(beanP);
            transaction.commit();
            ban = true;

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar pagos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return ban;

    }

    //*******       metoodos nuevos cesar 2019 ***********************************
    public List<Clientes> mostrarPagosyabonos(String idCliente) {
        List<Clientes> lista = new ArrayList<Clientes>();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //obtenemos la deuda total por medio del idCliente
            Query hqlDeuda = session.createQuery("select d.iddeudatotal,d.deudatotal,d.status,d.fecharegistro from Deudatotal as d inner join d.clientes as cli where cli.idclientes='" + idCliente + "' and d.status!='pagado'");
            List<Object[]> listaRes2 = hqlDeuda.list();
            if (listaRes2.size() > 0) {
                for (int j = 0; j < listaRes2.size(); j++) {

                    String idDeuda = listaRes2.get(j)[0] + "";
                    String deuda = listaRes2.get(j)[1] + "";
                    String status = listaRes2.get(j)[2] + "";
                    String fechaRegistro = listaRes2.get(j)[3] + "";

                    //obtenemos los pagos
                    Query hqlPagos = session.createQuery("select p.idpagos,p.abono,p.fecharegistro,u.nombre from Pagos as p inner join p.deudatotal as d inner join p.usuarios  as u where d.iddeudatotal='" + idDeuda + "'");
                    List<Object[]> listaPagos = hqlPagos.list();

                    for (int k = 0; k < listaPagos.size(); k++) {
                        Clientes bean = new Clientes();
                        String idPagos = listaPagos.get(k)[0] + "";

                        String abono = listaPagos.get(k)[1] + "";
                        String fecha = listaPagos.get(k)[2] + "";
                        String nombreusu = listaPagos.get(k)[3] + "";
                        Pagos p = new Pagos();
                        p.setIdpagos(Integer.parseInt(idPagos));
                        p.setAbono(Integer.parseInt(abono));
                        p.setFecharegistro(fecha);
                        bean.setPagos(p);
                        Usuarios u = new Usuarios();
                        u.setNombre(nombreusu);
                        bean.setUsuarios(u);
                        //para la deuda
                        Deudatotal deu = new Deudatotal();
                        deu.setIddeudatotal(Integer.parseInt(idDeuda));
                        deu.setDeudatotal(Integer.parseInt(deuda));
                        deu.setStatus(status);
                        deu.setFecharegistro(fechaRegistro);
                        bean.setDeudatotal(deu);
                        lista.add(bean);

                    }

                }
            } else {

            }

            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en daoPAgos mostrarPagosyabonos " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return lista;
    }

    public boolean registrarSoloAbono(String idCliente, int abono) {
        boolean ban = false;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            //Deudatotal benaDeuda=(Deudatotal)session.createQuery("from Deudatotal as d where d.clientes as c c.idclientes='"+idCliente+"' and d.status='No pagado'");
//obtenemos lo que necesitamos para registrar el pago 
//bean deuda y usuarios
            System.out.println("idusu " + principal.idUsuario);
            Usuarios beanUsuario = (Usuarios) session.createQuery("from Usuarios as u where u.idusuarios='" + principal.idUsuario + "'").uniqueResult();
            System.out.println("usuario " + beanUsuario.getNombre() + " idclie " + idCliente);
            Clientes beanCliente = (Clientes) session.createQuery("from Clientes as c where c.idclientes='" + idCliente + "'").uniqueResult();
            System.out.println("cliente " + beanCliente.getNombrecompleto());
            Set<Deudatotal> listaDeuda = beanCliente.getDeudatotals();
            if (listaDeuda.size() > 0) {
                for (Deudatotal deudatotal : listaDeuda) {
                    if (deudatotal.getStatus().equalsIgnoreCase("no pagado")) {
                        //ahora registrmos el pago
                        Pagos beanPagos = new Pagos();
                        beanPagos.setDeudatotal(deudatotal);
                        beanPagos.setUsuarios(beanUsuario);
                        beanPagos.setAbono(abono);
                        beanPagos.setFecharegistro(fechaActual());
                        session.save(beanPagos);
                        break;
                    } else {

                    }
                }

            } else {

            }

            ban = true;
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en daoPAgos registrarSoloAbono " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return ban;
    }

    public String fechaActual() {
        //Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fechaActual = dia + "-" + mes + "-" + año;
        System.out.println("fechaActutla " + fechaActual);
        return fechaActual;
    }

    public boolean registrarSoloAbono2(String idCliente, int abono) {
        boolean ban = false;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            //Deudatotal benaDeuda=(Deudatotal)session.createQuery("from Deudatotal as d where d.clientes as c c.idclientes='"+idCliente+"' and d.status='No pagado'");
//obtenemos lo que necesitamos para registrar el pago 
//bean deuda y usuarios
            //usuarios
            Query hqlUsuarios = session.createQuery("select u.idusuarios,u.nombre,u.apaterno,u.amaterno,u.usuario,u.contra,cl.idclientes,cl.nombrecompleto,cl.telefono,cl.fecharegistro from Clientes as cl inner join cl.usuarios  as u where cl.idclientes='" + idCliente + "'");
            List<Object[]> listaUsuarios = hqlUsuarios.list();
            if (listaUsuarios.size() > 0) {
                System.out.println("tama " + listaUsuarios.size());
            } else {
                System.out.println("tama 22 " + listaUsuarios.size());
            }
            Usuarios beanUsuario = new Usuarios();
            System.out.println("idDeuda " + listaUsuarios.get(0)[0] + "");
            beanUsuario.setIdusuarios(Integer.parseInt(listaUsuarios.get(0)[0] + ""));
            beanUsuario.setNombre(listaUsuarios.get(0)[1] + "");
            beanUsuario.setApaterno(listaUsuarios.get(0)[2] + "");
            beanUsuario.setAmaterno(listaUsuarios.get(0)[3] + "");
            beanUsuario.setUsuario(listaUsuarios.get(0)[4] + "");
            beanUsuario.setContra(listaUsuarios.get(0)[5] + "");

            //cliente
            Clientes beanCliente = new Clientes();
          
            beanCliente.setIdclientes(Integer.parseInt(listaUsuarios.get(0)[6] + ""));
            beanCliente.setNombrecompleto(listaUsuarios.get(0)[7] + "");
            beanCliente.setTelefono(listaUsuarios.get(0)[8] + "");
            beanCliente.setFecharegistro(listaUsuarios.get(0)[9] + "");
            beanCliente.setUsuarios(beanUsuario);

            Query hqlDeuda = session.createQuery("select d.iddeudatotal,d.deudatotal,d.status,d.fecharegistro,c.idclientes "
                    + "from Deudatotal as d inner join d.clientes as c  where c.idclientes='" + idCliente + "'");
            List<Object[]> listaDeuda = hqlDeuda.list();
            Deudatotal beanDeduda = new Deudatotal();
            beanDeduda.setIddeudatotal(Integer.parseInt(listaDeuda.get(0)[0] + ""));
            beanDeduda.setDeudatotal(Integer.parseInt(listaDeuda.get(0)[1] + ""));
            beanDeduda.setStatus(listaDeuda.get(0)[2] + "");
            beanDeduda.setFecharegistro(listaDeuda.get(0)[3] + "");
            beanDeduda.setClientes(beanCliente);

            Pagos beanPagos = new Pagos();
            beanPagos.setDeudatotal(beanDeduda);
            beanPagos.setUsuarios(beanUsuario);
            beanPagos.setAbono(abono);
            beanPagos.setFecharegistro(fechaActual());
            session.save(beanPagos);

            ban = true;
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en daoPAgos registrarSoloAbono2  " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return ban;
    }
}
