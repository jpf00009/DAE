/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.servidor;

import com.dae.entidad.Evento;
import com.dae.entidad.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceEventoImp implements ServiceEventoInterfaz {

    List<Evento> eventos;

    @Autowired
    ServiceUsuarioImp gestorUsuario;

    public ServiceEventoImp() {

        eventos = new ArrayList<>();

    }

    @Override
    public Evento BuscarEventos(int id) {

        Evento eventaco = new Evento();
        Evento defecto = new Evento();

        Iterator<Evento> it = eventos.iterator();
        while (it.hasNext()) {
            eventaco = it.next();
            if (eventaco.getId() == id) {
                return eventaco;
            }
        }
        return defecto;
    }

    @Override
    public void obtenerEvento(int id) {

    }

    @Override
    public void crearEvento(Evento evento, String token) {

        if (gestorUsuario.buscarToken(token)) {

            eventos.add(evento);

        }

    }

    @Override
    public void borrarEvento(int id) {
        eventos.remove(BuscarEventos(id));
    }

    @Override
    public void listarEventos() {
        Evento evento = new Evento();
        Iterator<Evento> it = eventos.iterator();
        System.out.println("LISTA DE EVENTOS: ");
        while (it.hasNext()) {
            System.out.println("------------ ");
            evento = it.next();
            System.out.println("Id evento: " + evento.getId());
            System.out.println("Nombre evento: " + evento.getNombreEvento());

        }
    }

    @Override
    public void BuscarEventosPorTipo(String opcion) {
        Evento eventaco = new Evento();

        Iterator<Evento> it = eventos.iterator();
        while (it.hasNext()) {
            eventaco = it.next();
            System.out.println("Nombre Evento del tipo: " + opcion);
            if (new String(eventaco.getTipo()).equals(opcion)) {
                System.out.println("Nombre del evento: " + eventaco.getNombreEvento() + " Id del evento: " + eventaco.getId());
            }
        }

    }

    @Override
    public void crearEventoSimple(Evento evento) {

        eventos.add(evento);

    }

    @Override
    public void ListarEventosCreadosPorUsuario(Usuario usuario, String token) {

        if (gestorUsuario.buscarToken(token)) {

            Evento evento = new Evento();
            Iterator<Evento> it = eventos.iterator();
            System.out.println("LISTA DE EVENTOS CREADOS POR USUARIO: " + usuario.getNombreUsuario());
            while (it.hasNext()) {
                evento = it.next();
                Integer i = evento.getPropietario().getId();
                Integer i2 = usuario.getId();
                if (i.equals(i2)) {
                    System.out.println("------------ ");
                    System.out.println("Id evento: " + evento.getId());
                    System.out.println("Nombre evento: " + evento.getNombreEvento());
                }

            }
        }

    }

    @Override
    public void apuntarseEvento(Evento evento, Usuario usuario) {
        evento.añadirUsuarioInscrito(usuario);
    }
    
    @Override
    public void borrarseEvento(Evento evento, Usuario usuario) {
        evento.borrarUsuarioInscrito(usuario);
    }

    @Override
    public void listarUsuariosEvento(Evento evento) {
        List<Usuario> usuariosInscritos = evento.getUsuariosInscritos();
        Usuario usuario = new Usuario();
        Iterator<Usuario> it = usuariosInscritos.iterator();
        System.out.println("LISTA DE USUARIOS INSCRITOS: ");
        while (it.hasNext()) {
            System.out.println("------------ ");
            usuario = it.next();
            System.out.println("Nombre usuario: " + usuario.getNombreUsuario());

        }
    }

}
