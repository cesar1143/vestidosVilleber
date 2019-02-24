/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import beans.Clientes;
import beans.Deudatotal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.daoClientes;
import modelo.daoDeudaTotal;

/**
 *
 * @author famsa
 */
public class pruebas {

    public String obtenerFechaActual() {
        String fecha = "";
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);
        int hora = calendario.get(Calendar.HOUR);
        int minuto = calendario.get(Calendar.MINUTE);
        String dia1=dia+"";
        mes=mes+1;
        String mes1=mes+"";
        if (dia<=9 ||  mes<9) {
            dia1="0"+dia;
            mes1="0"+mes;
        }
        fecha = año+ "-" + mes1  + "-" + dia1 ;

        return fecha;
    }
    
    
     public Date obtenerFechaActualTipoDate() {
        String fecha = "";
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);
        int hora = calendario.get(Calendar.HOUR);
        int minuto = calendario.get(Calendar.MINUTE);
        String dia1=dia+"";
        mes=mes+1;
        String mes1=mes+"";
        if (dia<=9 ||  mes<9) {
            dia1="0"+dia;
            mes1="0"+mes;
        }
        fecha = año+ "-" + mes1  + "-" + dia1 ;

        return null;
    }

    public static void main(String[] args) {
        String fecha="10-08-17";
        String fecha2="20-08-17";
        
     
    }

}
