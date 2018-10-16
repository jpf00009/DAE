package com.dae.servidor;

import com.dae.entidad.Evento;



public interface ServiceEventos {
        
    public Evento BuscarEventos(int id);
    public void obtenerEvento(int id);
    public void añadirEvento(Evento evento);

}
