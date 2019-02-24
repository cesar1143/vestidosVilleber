/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author famsa
 */
public class validarCampos {

    /*Mensajes
     JOptionPane.showMessageDialog(null,"Completa el campo", "Advertencia",JOptionPane.WARNING_MESSAGE);
    JOptionPane.showMessageDialog(null,"Error al registrar el usuario", "Error",JOptionPane.ERROR_MESSAGE);
    JOptionPane.showMessageDialog(null,"Completa el campo", "Exito",JOptionPane.INFORMATION_MESSAGE);
     */

    public boolean validarCampos(JTextField texto) {
        boolean ban = false;
        if (texto.getText().toString().isEmpty()) {
            texto.requestFocus();
            JOptionPane.showMessageDialog(null, "Completa el campo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            

        } else {

            ban = true;
        }
        return ban;
    }

    public void soloLetras(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                int caracterAsci = (int) e.getKeyChar();//validacion condigo asccii obtenemos el valor de laletra o nuemro en codigo ascci

                if (Character.isDigit(caracter)
                        || caracterAsci >= 33 && caracterAsci <= 47
                        || caracterAsci >= 58 && caracterAsci <= 64
                        || caracterAsci >= 91 && caracterAsci <= 96
                        || caracterAsci >= 123 && caracterAsci <= 163
                        || caracterAsci >= 166 && caracterAsci <= 208//241--> es la ñ minuscular Y ÑMAYUSCULA ES 209 ESTO EN CODIGO ASCII
                        || caracterAsci >= 210 && caracterAsci <= 240
                        || caracterAsci >= 242 && caracterAsci <= 255) {
                    // getToolkit().beep();
                    e.consume();

                } else {

                }
            }

        });

    }
    

    public void soloNumeros(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                int caracterAsci = (int) e.getKeyChar();
                if (Character.isLetter(caracter)|| caracterAsci >= 33 && caracterAsci <= 47
                        || caracterAsci >= 58 && caracterAsci <= 64
                        || caracterAsci >= 91 && caracterAsci <= 96
                        || caracterAsci >= 123 && caracterAsci <= 163
                        || caracterAsci >= 166 && caracterAsci <= 208//241--> es la ñ minuscular Y ÑMAYUSCULA ES 209 ESTO EN CODIGO ASCII
                        || caracterAsci >= 210 && caracterAsci <= 240
                        || caracterAsci >= 242 && caracterAsci <= 255) {
                    //getToolkit().beep();
                    e.consume();

                }
            }

        });

    }
     public void soloNumerosYLetras(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                int caracterAsci = (int) e.getKeyChar();
                if ( caracterAsci >= 33 && caracterAsci <= 47
                        || caracterAsci >= 58 && caracterAsci <= 64
                        || caracterAsci >= 91 && caracterAsci <= 96
                        || caracterAsci >= 123 && caracterAsci <= 163
                        || caracterAsci >= 166 && caracterAsci <= 208//241--> es la ñ minuscular Y ÑMAYUSCULA ES 209 ESTO EN CODIGO ASCII
                        || caracterAsci >= 210 && caracterAsci <= 240
                        || caracterAsci >= 242 && caracterAsci <= 255) {
                    //getToolkit().beep();
                    e.consume();

                }
            }

        });

    }
     public void soloNumerosYLetras(JTextArea texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                int caracterAsci = (int) e.getKeyChar();
                if ( caracterAsci >= 33 && caracterAsci <= 47
                        || caracterAsci >= 58 && caracterAsci <= 64
                        || caracterAsci >= 91 && caracterAsci <= 96
                        || caracterAsci >= 123 && caracterAsci <= 163
                        || caracterAsci >= 166 && caracterAsci <= 208//241--> es la ñ minuscular Y ÑMAYUSCULA ES 209 ESTO EN CODIGO ASCII
                        || caracterAsci >= 210 && caracterAsci <= 240
                        || caracterAsci >= 242 && caracterAsci <= 255) {
                    //getToolkit().beep();
                    e.consume();

                }
            }

        });

    }
     
      public void soloNumerosConPunto(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                int caracterAsci = (int) e.getKeyChar();
                if (Character.isLetter(caracter)|| caracterAsci >= 33 && caracterAsci <= 45
                        ||caracterAsci == 47
                        || caracterAsci >= 58 && caracterAsci <= 64
                        || caracterAsci >= 91 && caracterAsci <= 96
                        || caracterAsci >= 123 && caracterAsci <= 163
                        || caracterAsci >= 166 && caracterAsci <= 208//241--> es la ñ minuscular Y ÑMAYUSCULA ES 209 ESTO EN CODIGO ASCII
                        || caracterAsci >= 210 && caracterAsci <= 240
                        || caracterAsci >= 242 && caracterAsci <= 255) {
                    //getToolkit().beep();
                    e.consume();

                }
            }

        });

    }
    public void limpiarCampos(JTextField texto){
        texto.setText("");
        
    }
    
    public String obtenerFechaActual() {
        String fecha = "";
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);
        int hora= calendario.get(Calendar.HOUR);
        int minuto= calendario.get(Calendar.MINUTE);
       mes=mes+1;
        String dia1=dia+"";
        String mes1=mes+"";
        if (dia<=9 ) {
            dia1="0"+dia;
           
        }
        if ( mes<9) {
           
            mes1="0"+mes;
        }
        
        
        
        fecha = dia1+ "-" + mes1  + "-" + año ;

          
        return fecha;
    }

}
