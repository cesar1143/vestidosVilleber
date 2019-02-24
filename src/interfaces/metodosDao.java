package interfaces;


import java.util.List;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author famsa
 */
public interface metodosDao {
    public boolean registrar(Object obj);
    public Object  consultaEspecifica(String id);
    public List<Object> consultarTodos();
    public boolean editar(Object  bean);
    public boolean eliminar(Object bean);
}
