/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.servidor;

import com.dae.entidad.Evento;
import com.dae.entidad.Usuario;
import com.dae.excepciones.eventoNoEncontrado;
import com.dae.excepciones.palabraClaveNoEncontrada;
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
    public List<Evento> BuscarEventosPorTipo(String opcion, String clave) {
        Evento eventaco = new Evento();
        List<Evento> eventosTipo = new ArrayList<>();

        Iterator<Evento> it = eventos.iterator();
        while (it.hasNext()) {
            eventaco = it.next();
            if (new String(eventaco.getTipo()).equals(opcion)) {
                if (eventaco == null) {
                    throw new eventoNoEncontrado();
                } else {
                    eventosTipo.add(eventaco);
                }
            }
        }
        if (eventosTipo == null) {
            throw new eventoNoEncontrado();
        }
        //OPCIÓN CON PALABRA CLAVE VACIA
        if (clave.isEmpty()) {
            return eventosTipo;
        }
        //OPCIÓN CON PALABRA CLAVE
        Evento eventoClave = new Evento();
        List<Evento> listEventosClave = new ArrayList<>();
        Iterator<Evento> it2 = eventosTipo.iterator();
        while (it2.hasNext()) {
            eventoClave = it2.next();
            if (new String(eventoClave.getDescripcion()).contains(clave)) {
                listEventosClave.add(eventoClave);
            }
        }

        if (listEventosClave == null) {
            throw new palabraClaveNoEncontrada();
        }

        return listEventosClave;

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

}
