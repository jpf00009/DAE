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
    
    public List<Evento> BuscarEventosPorTipo(String opcion, String clave);
    
    public void ListarEventosCreadosPorUsuario(Usuario usuario, String token);
    
}
