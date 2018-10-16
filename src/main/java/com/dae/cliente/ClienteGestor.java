/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dae.cliente;

import com.dae.entidad.Evento;
import com.dae.entidad.Usuario;
import com.dae.excepciones.FalloAcceso;
import com.dae.servidor.ServiceEventoImp;
//import com.dae.servidor.ServiceEventoInterfaz;

import org.springframework.context.ApplicationContext;
import com.dae.servidor.ServiceEventoInterfaz;
import com.dae.servidor.ServiceUsuarioImp;
import com.dae.servidor.ServiceUsuarioInterfaz;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteGestor {

    ApplicationContext context;

    public ClienteGestor(ApplicationContext context) {

        this.context = context;

    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Ya tenemos el "lector"

//        Evento evento1 = new Evento();
//        evento1.setId(1);
//        evento1.setNombreEvento("Partido");
        ServiceEventoInterfaz GestorEvento = (ServiceEventoInterfaz) context.getBean("gestorEvento");
        ServiceUsuarioInterfaz GestorUsuario = (ServiceUsuarioInterfaz) context.getBean("gestorUsuario");

//        ServiceEventoImp GestorEvento = (ServiceEventoImp) context.getBean("gestorEvento");
//        GestorEvento.añadirEvento(evento1);
//        Evento evento2 = new Evento();
//        evento2.setId(2);
//        evento2.setNombreEvento("Atletismo");
//        GestorEvento.añadirEvento(evento2);
//        Evento evento = GestorEvento.BuscarEventos(1);
//        System.out.println(evento.getNombreEvento());
//        String nombre = br.readLine(); //Se lee el nombre con readLine() que retorna un String con el dato
        Scanner sn = new Scanner(System.in);
        boolean salirMenuPrincipal = false;
        int opcion; //Guardaremos la opcion del usuario
        String texto, texto2;
        String token = "";
        boolean salirCreaEvento = false;

        Usuario usu1 = new Usuario("aaaa@gmail.com", "1234", null);
        GestorUsuario.crearUsuario(usu1);

        Evento e1 = new Evento("Volley", "Jaén", "01/05/2018", "Actividad deportiva", "Partido amistoso", 12, usu1);
//        Evento e2 = new Evento("Basket");
        GestorEvento.crearEventoSimple(e1);
//        GestorEvento.crearEventoSimple(e2);

        while (!salirMenuPrincipal) {
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Buscar Eventos por tipo");
            System.out.println("4. Salir");
            System.out.println("5. Logear Usuario");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {

                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        System.out.println("Introduce un nombre de usuario");
                        texto = br.readLine();
                        System.out.println("Introduce una contraseña");
                        texto2 = br.readLine();
                        GestorUsuario.crearUsuario(new Usuario(texto, texto2, token));
                        System.out.println("Se ha registrado al usuario: '" + texto + "' - '" + texto2 + "'");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        GestorUsuario.listarUsuarios();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        System.out.println("Selecciona el eventos de un tipo a mostrar");

                        System.out.println("1. Charla");
                        System.out.println("2. Curso");
                        System.out.println("3. Actividad deportiva");
                        System.out.println("4. Visita cultural");
                        String tipoEve = br.readLine();
                        boolean salirTipoEvento = false;
                        while (!salirTipoEvento) {
                            switch (tipoEve) {
                                case "1":
                                    tipoEve = "Charla";
                                    salirTipoEvento = true;
                                    GestorEvento.BuscarEventosPorTipo(tipoEve);
                                    break;
                                case "2":
                                    tipoEve = "Curso";
                                    salirTipoEvento = true;
                                    GestorEvento.BuscarEventosPorTipo(tipoEve);
                                    break;
                                case "3":
                                    tipoEve = "Actividad deportiva";
                                    salirTipoEvento = true;
                                    GestorEvento.BuscarEventosPorTipo(tipoEve);
                                    break;
                                case "4":
                                    tipoEve = "Visita cultural";
                                    salirTipoEvento = true;
                                    GestorEvento.BuscarEventosPorTipo(tipoEve);
                                    break;

                                default:
                                    System.out.println("Debe elegir una opcion entre las anteriores");
                            }
                        }
                        break;
                    case 4:
                        salirMenuPrincipal = true;
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion 5");
                        Usuario logueado = null;
                        boolean acceso = false;

                        while (!acceso) {
                            System.out.println("Introduce un nombre de usuario (aaaa@gmail.com) :");
                            texto = br.readLine();

                            System.out.println("Introduce una contraseña :");
                            texto2 = br.readLine();

                            try {
                                token = GestorUsuario.loginUsuario(texto, texto2);
                                acceso = true;
                                logueado = GestorUsuario.BuscarUsuario(texto);
                            } catch (FalloAcceso a) {
                                System.out.println("Error de autentificación (Vuelva a introducir email y password)");
                            }
                        }

                        System.out.println("\n********************************************************************");
                        System.out.println("***** Bienvenido " + logueado.getNombreUsuario() + "' *****");
                        System.out.println("********************************************************************");

                        boolean salirLogueo = false;

                        while (!salirLogueo) {
                            //MENU DEL USUARIO LOGEADO
                            System.out.println("\nElija una opción '" + logueado.getNombreUsuario() + "' :\n");

                            System.out.println("\n1. Crear Evento");
                            System.out.println("2. Buscar Evento");
                            System.out.println("3. Borrar Evento");
                            System.out.println("4. Inscribirse en Evento");
                            System.out.println("5. Listar Eventos creados por Usuario");
                            System.out.println("6. Salir");

                            boolean salirOpciones = false;

                            while (!salirOpciones) {
                                try {
                                    opcion = sn.nextInt();
                                    switch (opcion) {
                                        case 1:
                                            System.out.println("Has seleccionado la opcion 1");

                                            System.out.println("Introduce un nombre");
                                            String nombre = br.readLine();

                                            System.out.println("Introduce un lugar");
                                            String lugar = br.readLine();

                                            System.out.println("Introduce la fecha [formato: 00/00/00]");
                                            String fecha = br.readLine();

                                            System.out.println("Introduce el tipo de Evento");
                                            System.out.println("1. Charla");
                                            System.out.println("2. Curso");
                                            System.out.println("3. Actividad deportiva");
                                            System.out.println("4. Visita cultural");
                                            String tipo = br.readLine();

                                            while (!salirCreaEvento) {
                                                switch (tipo) {
                                                    case "1":
                                                        tipo = "Charla";
                                                        salirCreaEvento = true;
                                                        break;
                                                    case "2":
                                                        tipo = "Curso";
                                                        salirCreaEvento = true;
                                                        break;
                                                    case "3":
                                                        tipo = "Actividad deportiva";
                                                        salirCreaEvento = true;
                                                        break;
                                                    case "4":
                                                        tipo = "Visita cultural";
                                                        salirCreaEvento = true;
                                                        break;

                                                    default:
                                                        System.out.println("Debe elegir una opcion entre las anteriores");
                                                        tipo = sn.next();

                                                }
                                            }

                                            System.out.println("Introduce la descripcion del evento");
                                            String descripcion = br.readLine();

                                            System.out.println("Introduce el aforo maximo del evento");
                                            int aforo = sn.nextInt();

                                            Evento nuevoEvento = new Evento(nombre, lugar, fecha, tipo, descripcion, aforo, logueado);

                                            GestorEvento.crearEvento(nuevoEvento, logueado.getToken());

                                            System.out.println("Se ha registrado el Evento: '" + nombre + "' - '" + lugar + "' - '" + fecha + "' - '" + tipo + "' - '" + descripcion + "' - '" + aforo + "'");
                                            salirOpciones = true;
                                            break;
                                        case 2:
                                            System.out.println("Has seleccionado la opcion 2");
                                            System.out.println("Introduce el id del evento a mostrar");
                                            opcion = sn.nextInt();
                                            Evento evento = GestorEvento.BuscarEventos(opcion);
                                            System.out.println(evento.getNombreEvento());
                                            salirOpciones = true;

                                            break;
                                        case 3:
                                            System.out.println("Has seleccionado la opcion 3");
                                            System.out.println("Introduce el id del evento a borrar");
                                            opcion = sn.nextInt();
                                            GestorEvento.borrarEvento(opcion);
                                            salirOpciones = true;
                                            break;
                                        case 4:
                                            System.out.println("Has seleccionado la opcion 4");
//                                            GestorEvento.listarEventos();
                                            System.out.println("Introduce el id del evento al que quieras inscribirte");
                                            opcion = sn.nextInt();
                                            evento = GestorEvento.BuscarEventos(opcion);
                                            GestorEvento.apuntarseEvento(evento, logueado);
                                            GestorEvento.listarUsuariosEvento(evento);
                                            salirOpciones = true;
                                            break;
                                        case 5:
                                            System.out.println("Has seleccionado la opción 5");
                                            GestorEvento.ListarEventosCreadosPorUsuario(logueado, token);
                                            salirOpciones = true;
                                            break;
                                        case 6:
                                            salirOpciones = true;
                                            salirLogueo = true;
                                            break;
                                        case 7:
                                            System.out.println("Has seleccionado la opcion 7");
                                            System.out.println("Introduce el id del evento al que quieras desinscribirte");
                                            opcion = sn.nextInt();
                                            evento = GestorEvento.BuscarEventos(opcion);
                                            GestorEvento.borrarseEvento(evento, logueado);
                                            GestorEvento.listarUsuariosEvento(evento);
                                            salirOpciones = true;
                                            break;
                                        default:
                                            System.out.println("Elija una de las opciones anteriores entre 1 y 5");
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Debes insertar un número");
                                    sn.next();
                                }
                            }

                        }
                    default:
                        System.out.println("Solo números entre 1 y 10");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

}
