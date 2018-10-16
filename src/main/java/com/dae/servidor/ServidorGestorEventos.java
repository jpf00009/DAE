package com.dae.servidor;


import com.dae.cliente.ClienteGestor;
import com.dae.entidad.Evento;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@SpringBootApplication
public class ServidorGestorEventos {
    
    //Crear bean Evento
    @Bean
    ServiceEventoImp gestorEvento(){
        
        ServiceEventoImp gestorEvento = new ServiceEventoImp();
        
        return gestorEvento;
        
    }
    
    //Crear bean Usuario
    @Bean
    ServiceUsuarioImp gestorUsuario(){
        
        ServiceUsuarioImp gestorUsuario = new ServiceUsuarioImp();
        
        return gestorUsuario;
        
    }
    
    public static void main(String[] args) throws Exception{
        
        SpringApplication servidor = new SpringApplication(ServidorGestorEventos.class);
        ApplicationContext context = servidor.run(args);
                
        ClienteGestor Cliente = new ClienteGestor(context);
        Cliente.run();
        
    }
    
    
}
