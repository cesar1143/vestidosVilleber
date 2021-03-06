package beans;
// Generated 08-jun-2018 21:10:54 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Productosapartados generated by hbm2java
 */
public class Productosapartados  implements java.io.Serializable {

     private int idProducto;
     private Integer idproductosapartados;
     private Clientes clientes;
     private Productos productos;
     private Usuarios usuarios;
     private String fecharegistro;
     private Date fecharegistro2;
     private String status;
     private int cantidadVenta;
     private String detallesproducto;
     private Set productosvendidoses = new HashSet(0);
     private Set medidases = new HashSet(0);
     private Set fechaspruebases = new HashSet(0);
     private Medidas medidas;

     
     //datos extras para llenar la tabla pendientes
     private String clave;
     private String nombrecompleto;
     private String idFechas;
     private String fechaPrueba;
     private String fechaEvento;
     private byte[] foto;

    public Productosapartados() {
    }

	
    public Productosapartados(String fecharegistro, int cantidadVenta) {
        this.fecharegistro = fecharegistro;
        this.cantidadVenta = cantidadVenta;
    }
    public Productosapartados(Clientes clientes, Productos productos, Usuarios usuarios, String fecharegistro, String status, int cantidadVenta, String detallesproducto, Set productosvendidoses, Set medidases, Set fechaspruebases) {
       this.clientes = clientes;
       this.productos = productos;
       this.usuarios = usuarios;
       this.fecharegistro = fecharegistro;
       this.status = status;
       this.cantidadVenta = cantidadVenta;
       this.detallesproducto = detallesproducto;
       this.productosvendidoses = productosvendidoses;
       this.medidases = medidases;
       this.fechaspruebases = fechaspruebases;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
   
    
    
    public Integer getIdproductosapartados() {
        return this.idproductosapartados;
    }
    
    public void setIdproductosapartados(Integer idproductosapartados) {
        this.idproductosapartados = idproductosapartados;
    }
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public Productos getProductos() {
        return this.productos;
    }
    
    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public String getFecharegistro() {
        return this.fecharegistro;
    }
    
    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public int getCantidadVenta() {
        return this.cantidadVenta;
    }
    
    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
    public String getDetallesproducto() {
        return this.detallesproducto;
    }
    
    public void setDetallesproducto(String detallesproducto) {
        this.detallesproducto = detallesproducto;
    }
    public Set getProductosvendidoses() {
        return this.productosvendidoses;
    }
    
    public void setProductosvendidoses(Set productosvendidoses) {
        this.productosvendidoses = productosvendidoses;
    }
    public Set getMedidases() {
        return this.medidases;
    }
    
    public void setMedidases(Set medidases) {
        this.medidases = medidases;
    }
    public Set getFechaspruebases() {
        return this.fechaspruebases;
    }
    
    public void setFechaspruebases(Set fechaspruebases) {
        this.fechaspruebases = fechaspruebases;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getIdFechas() {
        return idFechas;
    }

    public void setIdFechas(String idFechas) {
        this.idFechas = idFechas;
    }

    public String getFechaPrueba() {
        return fechaPrueba;
    }

    public void setFechaPrueba(String fechaPrueba) {
        this.fechaPrueba = fechaPrueba;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Medidas getMedidas() {
        return medidas;
    }

    public void setMedidas(Medidas medidas) {
        this.medidas = medidas;
    }




}


