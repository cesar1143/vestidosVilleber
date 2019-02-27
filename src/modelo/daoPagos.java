/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import beans.Clientes;
import beans.Deudatotal;
import beans.Medidas;
import beans.Pagos;
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

}
