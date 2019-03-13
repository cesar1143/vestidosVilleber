/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloProductos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JOptionPane;
import servicios.conexion;

/** 
 *
 * @author Usuario
 */
public class DaoProductos {

    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    productos bean = new productos();

    public boolean registrarProducto(productos bean) {
        boolean ban = false;
        String sql = "insert into productos (clave,precio,color,tipo,fecharegistro,foto,descripcion,cantidad,estado,nombre,usuario_id) values(?,?,?,?,NOW(),?,?,?,?,?,'" + bean.getUsuario_id() + "')";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());

            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            FileInputStream archivoFoto = new FileInputStream(bean.getFotoStrin());
            ps.setBinaryStream(5, archivoFoto);
            ps.setString(6, bean.getDescripcion());
            ps.setInt(7, bean.getCantidad());
            ps.setString(8, bean.getEstado());
            ps.setString(9, bean.getNombre());

            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos RegistrarProductos " + e);
        }
        return ban;

    }
//Consultar si el producto existe

    public productos consultaExiste(String clave) {

        String sql = "select * from productos where clave=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                bean.setIdProductos(rs.getInt(1));
                bean.setClave(rs.getString(2));
                bean.setPrecio(rs.getInt(3));
                bean.setColor(rs.getString(4));
                bean.setTipo(rs.getString(5));
                bean.setCantidad(rs.getInt("cantidad"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos consultaExiste " + e);
        }
        return bean;
    }
// ==================================== CONSULTA  TODOS ====================================

    public ResultSet consultaTodos() {

        String sql = "select * from productos ";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultaTodos " + e);
        }
        return rs;
    }

// ==================================== CONSULTA  TODOS DONDE CANTIDAD SEA MAYOR A 0 ====================================
    public ResultSet consultaTodosDondeCantidadSeaMayorACero() {

        String sql = "select * from productos where cantidad>0 ";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultaTodos " + e);
        }
        return rs;
    }

// ==================================== CONSULTAR  imagenes ====================================
    public productos consultarImage(int idpro) {
        productos bean = new productos();
        String sql = "select foto,clave,precio from productos where idproductos=? ";

        try {
            con = conexion.getConnection();

            ps = con.prepareStatement(sql);
//            System.out.println("soy id pro " + idpro);
            ps.setInt(1, idpro);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);
                bean.setClave(rs.getString("clave"));
                bean.setPrecio(rs.getInt("precio"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImage " + e);
        }
        return bean;
    }

    // ==================================== CONSULTA  imagenes Para el catalogo====================================
    public List<productos> consultarImageParaCatalogo() {
        List<productos> listaProCatalogo = new ArrayList<>();
        String estado = "Novendido";
        productos bean;
        String sql = "select foto,idproductos from productos where cantidad>0";

        try {
            con = conexion.getConnection();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                bean = new productos();
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);
                bean.setIdProductos(rs.getInt("idproductos"));
                listaProCatalogo.add(bean);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImageParaCatalogo() " + e);
        }
        return listaProCatalogo;
    }

    // ==================================== CONSULTA  imagenes Para el catalogo====================================
    public List<productos> consultarImageParaCatalogoTipoyEstado(String tipo, String estado) {
        List<productos> listaProCatalogo = new ArrayList<>();

        productos bean;
        String sql = "select foto,idproductos from productos where tipo=? and cantidad>0";

        try {
            con = conexion.getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            //ps.setString(2, estado);
//            System.out.println("sql daooo " + sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                bean = new productos();
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);
                bean.setIdProductos(rs.getInt("idproductos"));
                listaProCatalogo.add(bean);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImageParaCatalogo() " + e);
        }
        return listaProCatalogo;
    }

    // ==================================== CONSULTA  imagenes CON CLAVE ====================================
    public productos consultarImageConClave(int idpro) {
        productos bean = new productos();
        String sql = "select foto from productos where clave=? ";

        try {
            con = conexion.getConnection();

            ps = con.prepareStatement(sql);
            System.out.println("soy id pro " + idpro);
            ps.setInt(1, idpro);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje en daoProductos,  consultarImage " + e);
        }
        return bean;
    }

////=================================== OBTENER IMAGEN ================================================
//    public Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {
//        
//        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//        
//        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
//        ImageReader reader = (ImageReader) readers.next();
//        Object source = bis; // File or InputStream
//        ImageInputStream iis = ImageIO.createImageInputStream(source);
//        reader.setInput(iis, true);
//        ImageReadParam param = reader.getDefaultReadParam();
//        if (isThumbnail) {
//
//            param.setSourceSubsampling(4, 4, 0, 0);
//
//        }
//        return reader.read(0, param);
//
//    }
//=================================== OBTENER IMAGEN TODO TIPO DE IMAGEN ================================================
    public BufferedImage getImage(productos bean, boolean isThumbnail) throws IOException {
        BufferedImage imagenes = null;
        if (bean.getFoto() == null) {
            System.out.println("soy  null al convertir imagen edn dao prodcutos catalogo " + bean.getFoto());
        } else {

            imagenes = ImageIO.read(new ByteArrayInputStream(bean.getFoto()));
        }

        return imagenes;

    }

//=============== Consulta Especifica para modificar =================================
    public productos consultaEspecifica(int idPro) {

        String sql = "select * from productos where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPro);
            rs = ps.executeQuery();
            while (rs.next()) {
                bean.setIdProductos(rs.getInt(1));
                bean.setClave(rs.getString(2));
                bean.setPrecio(rs.getInt(3));
                bean.setColor(rs.getString(4));
                bean.setTipo(rs.getString(5));
                bean.setDescripcion(rs.getString("descripcion"));
                bean.setCantidad(rs.getInt("cantidad"));
                byte[] img = rs.getBytes("foto");
                bean.setFoto(img);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos consultaEspecifica " + e);
        }
        return bean;
    }

    //=============== Consulta Especifica para validar producto por el codigo =================================
    public productos consultaEspecificaCodigo(int codigo) {

        String sql = "select * from productos where clave=" + codigo + "";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {

                bean.setIdProductos(rs.getInt(1));
                bean.setClave(rs.getString(2));
                bean.setPrecio(rs.getInt(3));
                bean.setColor(rs.getString(4));
                bean.setTipo(rs.getString(5));
                bean.setDescripcion(rs.getString("descripcion"));
                bean.setCantidad(rs.getInt("cantidad"));
//                byte[] img = rs.getBytes("foto");
//                bean.setFoto(img);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos consultaEspecifica " + e);
        }
        return bean;
    }
//============== Modificar productos con nueva foto =================================

    public boolean modificarProductoConFoto(productos bean) {
        boolean ban = false;
        String sql = "update productos set clave=?,precio=?,color=?,tipo=?,foto=?,descripcion=?,cantidad=?,estado=? where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());
            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            FileInputStream archivoFoto = new FileInputStream(bean.getFotoStrin());
            ps.setBinaryStream(5, archivoFoto);
            ps.setString(6, bean.getDescripcion());
            ps.setInt(7, bean.getCantidad());
            ps.setString(8, bean.estado);
            ps.setInt(9, bean.getIdProductos());
            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos ModificarProductos " + e);
        }
        return ban;

    }
//============== Modificar productos catntidad productos =================================

    public boolean modificarCantidadPRoductoYEstado(int nuevaCantidad, int idpro) {
        boolean ban = false;
        String estado = "Novendido";
        String sql = "update productos set cantidad=?,estado='" + estado + "' where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, idpro);

            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos  modificarCantidadPRoducto " + e);
        }
        return ban;

    }

//============== Modificar productos Estado productos cuando cantidad = 0 osease que ya no alla existencias=================================
    public boolean modificarCantidadYEstadoProducto2(int idpro, int nuevaCantidad) {
        boolean ban = false;

        String sql = "update productos set  cantidad=? where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, idpro);

            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos  modificarCantidadYEstadoProducto " + e.getMessage());
        }
        return ban;

    }
//============== Modificar productos Estado productos cuando cantidad = 0 osease que ya no alla existencias=================================

    public boolean modificarEstadoPRoducto(int idpro) {
        boolean ban = false;
        String estado = "Vendido";
        String sql = "update productos set estado='" + estado + "' where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idpro);

            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos  modificarCantidadPRoducto " + e);
        }
        return ban;

    }
//============== Modificar productos foto actual =================================

    public boolean modificarProductoFotoActual(productos bean) {
        boolean ban = false;
        String sql = "update productos set clave=?,precio=?,color=?,tipo=?,descripcion=?,cantidad=?,estado=? where idproductos=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getClave());
            ps.setInt(2, bean.getPrecio());
            ps.setString(3, bean.getColor());
            ps.setString(4, bean.getTipo());
            ps.setString(5, bean.getDescripcion());
            ps.setInt(6, bean.getCantidad());
            ps.setString(7, bean.estado);

            ps.setInt(8, bean.getIdProductos());
            ban = ps.executeUpdate() == 1;
            ban = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje DaoProductos ModificarProductos " + e);
        }
        return ban;

    }

    //Obtener el ultimo registro en apartados para registrar 
    public int obtener_id() {
        int ultimo_id = 0;
        String sql = "SELECT *\n"
                + "FROM productos\n"
                + "ORDER by idProductos DESC\n"
                + "LIMIT 1 ";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ultimo_id = rs.getInt("idproductosapartados");
            }
        } catch (Exception e) {
        }
        return ultimo_id;
    }
}
