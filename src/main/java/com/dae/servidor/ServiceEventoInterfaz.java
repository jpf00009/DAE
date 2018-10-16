package com.dae.servidor;

import com.dae.entidad.Evento;
import com.dae.entidad.Usuario;
import java.util.List;

public interface ServiceEventoInterfaz {

    public Evento BuscarEventos(int id);

    public void obtenerEvento(int id);

    public void crearEventoSimple(Evento evento);

    public void crearEvento(Evento evento, String token);

    public void borrarEvento(int id);

    public void listarEventos();

    public void BuscarEventosPorTipo(String opcion);

    public void ListarEventosCreadosPorUsuario(Usuario usuario, String token);

    public void apuntarseEvento(Evento evento, Usuario usuario);

    public void listarUsuariosEvento(Evento evento);

    public void borrarseEvento(Evento evento, Usuario usuario);

}
