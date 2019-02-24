/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author famsa
 */
public class mesConMasVentas {
    
  private  int venteMes;
  private String mes;

    public mesConMasVentas(int venteMes, String mes) {
        this.venteMes = venteMes;
        this.mes = mes;
    }

    public int getVenteMes() {
        return venteMes;
    }

    public void setVenteMes(int venteMes) {
        this.venteMes = venteMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
  
  
}
