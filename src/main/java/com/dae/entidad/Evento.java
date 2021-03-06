/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.entidad;

import java.util.ArrayList;
import java.util.List;

public class Evento {

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    private static int contador=1;
    private int id;
    private String nombreEvento;
    private final String lugar;
    private final String fecha;
    private final String tipo;
    private final String descripcion;
    private final int aforo;
    private Usuario propietario;
    
    List<Usuario> usuariosInscritos;
    List<Usuario> usuariosEspera;
    
    public Evento(){
        this.id=id++;
        nombreEvento="";
        lugar="";
        fecha="";
        tipo="";
        descripcion="";
        aforo=0;
        propietario=null;
        usuariosInscritos = new ArrayList<>();
        usuariosEspera = new ArrayList<>();
    }
    
    public Evento(String nombreEvento){
        this.id=contador++;
        this.nombreEvento=nombreEvento;
        lugar="";
        fecha="";
        tipo="";
        descripcion="";
        aforo=0;
        propietario=null;        
    }
    
        public Evento(String nombreEvento, String lugar, String fecha, String tipo, String descripcion, int aforo, Usuario propietario){
        this.id=contador++;
        this.nombreEvento=nombreEvento;
        this.lugar = lugar;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.aforo = aforo;
        this.propietario = propietario;
    }
    
    public Evento(Evento e){
        id=e.getId();
        nombreEvento="";
        lugar="";
        fecha="";
        tipo="";
        descripcion="";
        aforo=0;
        propietario = null;
    }
    
        /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombreEvento
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * @param nombreEvento the nombreEvento to set
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    
        /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the propietario
     */
    public Usuario getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }
    
}
