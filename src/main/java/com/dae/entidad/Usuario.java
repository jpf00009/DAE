/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.entidad;

public class Usuario {



    private static int contador=1;
    private int id;
    private String nombreUsuario;
    private String password;
    private String token;

    public Usuario() {
        this.id=id++;
        nombreUsuario = "";
        password = "";
    }

    public Usuario(String nombreUsuario, String password, String token) {
        this.id=contador++;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.token = token;
    }
    
    public Usuario(Usuario u){
        this.id=u.getId();
        nombreUsuario="";
        password="";
        
    }
    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @return the password
     */
    public boolean idenfifPassword(String password) {

        return this.getPassword().equals(password);
    }
    
        /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    public void inscribirUsuarioEnEvento(){
        
        
        
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
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
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
