/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.servidor;

import com.dae.entidad.Usuario;

/**
 *
 * @author jesus
 */
public interface ServiceUsuarioInterfaz {

    public void crearUsuario(Usuario usuario);
    
    public Usuario registraUsuario(String nombreUsuario, String password);

    public void listarUsuarios();

    public Usuario BuscarUsuario(String nombreUsuario);

    public boolean identificarUsuario(String nombreUsuario, String password);
    
    public String loginUsuario(String nombreUsuario, String password);
    
    boolean buscarToken (String token);
    
}
