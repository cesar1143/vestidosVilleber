package beans;
// Generated 08-jun-2018 21:10:54 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Deudatotal generated by hbm2java
 */
public class Deudatotal  implements java.io.Serializable {


     private Integer iddeudatotal;
     private Clientes clientes;
     private Integer deudatotal;
     private String status;
     private String fecharegistro;
     private Set pagoses = new HashSet(0);

    public Deudatotal() {
    }

    public Deudatotal(Clientes clientes, Integer deudatotal, String status, String fecharegistro, Set pagoses) {
       this.clientes = clientes;
       this.deudatotal = deudatotal;
       this.status = status;
       this.fecharegistro = fecharegistro;
       this.pagoses = pagoses;
    }
   
    public Integer getIddeudatotal() {
        return this.iddeudatotal;
    }
    
    public void setIddeudatotal(Integer iddeudatotal) {
        this.iddeudatotal = iddeudatotal;
    }
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public Integer getDeudatotal() {
        return this.deudatotal;
    }
    
    public void setDeudatotal(Integer deudatotal) {
        this.deudatotal = deudatotal;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFecharegistro() {
        return this.fecharegistro;
    }
    
    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
    public Set getPagoses() {
        return this.pagoses;
    }
    
    public void setPagoses(Set pagoses) {
        this.pagoses = pagoses;
    }




}

