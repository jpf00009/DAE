/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.entidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Evento {

    private static int contador = 1;
    private int id;
    private String nombreEvento;
    private final String lugar;
    private final String fecha;
    private final String tipo;
    private final String descripcion;
    private final int aforo;
    private int espacio;

    private Usuario propietario;

    private List<Usuario> usuariosInscritos;
    private List<Usuario> usuariosEspera;

    public Evento() {
        this.id = id++;
        nombreEvento = "";
        lugar = "";
        fecha = "";
        tipo = "";
        descripcion = "";
        aforo = 0;
        propietario = null;
        espacio = 0;
        usuariosInscritos = new ArrayList<>();
        usuariosEspera = new ArrayList<>();
    }

    public Evento(String nombreEvento) {
        this.id = contador++;
        this.nombreEvento = nombreEvento;
        lugar = "";
        fecha = "";
        tipo = "";
        descripcion = "";
        aforo = 0;
        espacio = 0;
        propietario = null;
        usuariosInscritos = new ArrayList<>();
        usuariosEspera = new ArrayList<>();
    }

    public Evento(String nombreEvento, String lugar, String fecha, String tipo, String descripcion, int aforo, Usuario propietario) {
        this.id = contador++;
        this.nombreEvento = nombreEvento;
        this.lugar = lugar;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.aforo = aforo;
        this.espacio = aforo;
        this.propietario = propietario;
        usuariosInscritos = new ArrayList<>();
        usuariosEspera = new ArrayList<>();
    }

    public Evento(Evento e) {
        id = e.getId();
        nombreEvento = "";
        lugar = "";
        fecha = "";
        tipo = "";
        descripcion = "";
        aforo = 0;
        espacio = 0;
        propietario = null;
        usuariosInscritos = e.usuariosInscritos;
        usuariosEspera = e.usuariosEspera;
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

    /**
     * @param usuario the propietario to set
     */
    public void añadirUsuarioInscrito(Usuario usuario) {
        if (buscarUsuarioInscrito(usuario)) {
            System.out.println("USUARIO YA INSCRITO");
            return;
        }
        if (this.espacio > 0) {
            this.usuariosInscritos.add(usuario);
            this.espacio--;
        } else {
            this.usuariosEspera.add(usuario);
        }
    }

    /**
     * @param usuario the propietario to set
     */
    public void borrarUsuarioInscrito(Usuario usuario) {
        if (!buscarUsuarioInscrito(usuario)) {
            System.out.println("USUARIO NO INSCRITO");
            return;
        }
        usuariosInscritos.remove(usuario);
        if(!usuariosEspera.isEmpty()){
            Iterator<Usuario> it =usuariosEspera.iterator();
            Usuario usu=it.next();
            usuariosInscritos.add(usu);
            usuariosEspera.remove(usu);
        }
    }

    /**
     * @param usuario the propietario to set
     */
    public boolean buscarUsuarioInscrito(Usuario usuario) {
        Usuario usuario1 = new Usuario();
        Iterator<Usuario> it = usuariosInscritos.iterator();
        while (it.hasNext()) {
            usuario1 = it.next();
            if (usuario1.getId() == usuario.getId()) {
                return true;
            }

        }
        return false;
    }

    /**
     * @return the usuariosInscritos
     */
    public List<Usuario> getUsuariosInscritos() {
        return usuariosInscritos;
    }

    /**
     * @return the usuariosEspera
     */
    public List<Usuario> getUsuariosEspera() {
        return usuariosEspera;
    }
}
