/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import beans.Clientes;
import beans.Usuarios;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mensajes.mensajeError;
import mensajes.mensajeExito;
import modelo.daoClientes;
import modelo.daoUsuarios;
import validaciones.validarCampos;

/**
 *
 * @author famsa
 */
public class controlClientes {

    //=========================== INSTANCIAMOS LA CLASES QUE UTILIZAREMOS ======================================
    validarCampos validar = new validarCampos();
    daoClientes daoClien = new daoClientes();

    public boolean registrar(JTextField txtNombre, JTextField txtTelefono, JFrame frame, String idUsuario) {
        boolean ban = false;
        if (validar.validarCampos(txtNombre)) {

            String telefono = txtNombre.getText();
            if (telefono.isEmpty()) {
                telefono = "Sin teléfono";

            } else {

            }
            //llenamos el bean cliente para enviarlo al dao
            Clientes beanClientes = new Clientes();
            beanClientes.setNombrecompleto(txtNombre.getText());
            beanClientes.setTelefono(telefono);
            //debemos enviar un bean usuarios para registrar un cliente entonces consultamos para obtenerlo
            //hacemos un metodo anonimo para traer el bean usuario
            beanClientes.setUsuarios((Usuarios) new daoUsuarios().consultaEspecifica(idUsuario));
            if (daoClien.registrar(beanClientes)) {
                frame.dispose();
                mensajeExito menExito = new mensajeExito();
                mensajeExito.labelMensaje.setText("El cliente se registro correctamente");
                menExito.setVisible(true);
                menExito.setAlwaysOnTop(true);
                // JOptionPane.showMessageDialog(null, "El cliente se registro correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                ban = true;
            } else {
                mensajeError  menError = new mensajeError ();
                mensajeError .labelMensaje.setText("Error al registrar el cliente");
                menError.setVisible(true);
                menError.setAlwaysOnTop(true);
                //JOptionPane.showMessageDialog(null, "Error al registrar el cliente", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }
        return ban;
    }

    public void consultaEspecifica(String idCliente) {

    }

    public Clientes consultaEspecificaPorNombre(String nombre) {

        return (Clientes) daoClien.consultaEspecificaPorNombreBean(nombre);

    }
}
