/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.servidor;

import com.dae.entidad.Evento;
import com.dae.entidad.Usuario;
import com.dae.excepciones.FalloAcceso;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jesus
 */
@Component
public class ServiceUsuarioImp implements ServiceUsuarioInterfaz {

    List<Usuario> usuarios;

    List<Evento> eventos;

    public ServiceUsuarioImp() {

        usuarios = new ArrayList<>();

        eventos = new ArrayList<>();

    }

    @Override
    public void crearUsuario(Usuario usuario) {

        usuarios.add(usuario);

    }

    @Override
    public void listarUsuarios() {
        Usuario usuario = new Usuario();
        Iterator<Usuario> it = usuarios.iterator();
        System.out.println("LISTA DE EVENTOS: ");
        while (it.hasNext()) {
            System.out.println("------------ ");
            usuario = it.next();
            System.out.println("Nombre usuario: " + usuario.getNombreUsuario());

        }
    }

    @Override
    public Usuario BuscarUsuario(String nombreUsuario) {
        Usuario usuario = new Usuario();
        Usuario defecto = new Usuario();

        Iterator<Usuario> it = usuarios.iterator();
        while (it.hasNext()) {
            usuario = it.next();
            if (new String(usuario.getNombreUsuario()).equals(nombreUsuario)) {
                return usuario;
            }
        }
        return defecto;
    }

    @Override
    public boolean identificarUsuario(String nombreUsuario, String password) {
        Usuario usuario = new Usuario();
        usuario = BuscarUsuario(nombreUsuario);
        if (usuario.idenfifPassword(password)) {
            return true;
        }
        return false;
    }

    @Override
    public String loginUsuario(String nombreUsuario, String password) {

        Usuario logueado = BuscarUsuario(nombreUsuario);
        if (logueado == null) {
            throw new FalloAcceso();
        }
        if (!password.equals(logueado.getPassword())) {
            throw new FalloAcceso();
        } else {
            logueado.setToken(UUID.randomUUID().toString());
                        System.out.println("El token dentro del servicio es: " + logueado.getToken());

        }

        return logueado.getToken();

    }

    @Override
    public boolean buscarToken(String token) {

        Usuario usuario = new Usuario();

        Iterator<Usuario> it = usuarios.iterator();
        while (it.hasNext()) {
            usuario = it.next();
            if (new String(usuario.getToken()).equals(token)) {
                return true;
            }
        }
        return false;

    }

}
