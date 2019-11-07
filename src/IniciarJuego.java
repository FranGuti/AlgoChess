import Excepciones.*;
import excepciones.UnidadInvalidaException;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IniciarJuego {

    Scanner input = new Scanner(System.in);

    public void iniciarJuego() throws excepciones.UnidadInvalidaException, NoAlcanzanLosPuntosException, NoPuedeAtacarException, CurarException, CasilleroOcupadoExcenption {
        System.out.println("Bienvenidos a AlgoChess\n\n");
        Jugador jugador1 = crearJugador("Ingrese el nombre del primer Jugador: ");
        Jugador jugador2 = crearJugador("Ingrese el nombre del segundo Jugador: ");
        Tablero tablero = new Tablero(jugador1,jugador2);
        inicializacionTurnos(jugador1,jugador2,tablero);
        System.out.println("Comienzan los turnos, arranca: " + jugador1.getNombre());
        accionesJuego(jugador1,jugador2,tablero);
    }
        //Crea los jugadores del juego.
    public Jugador crearJugador(String msg) {
        // Pido al usuario el nombre y devuelvo el Jugador.


        System.out.println(msg);
        String nombreJugador = input.nextLine();
        System.out.println("Hola " + nombreJugador + "\n");
        return new Jugador(nombreJugador);
    }

        // Decide quien arranca el juego.
    public void inicializacionTurnos(Jugador jugador1, Jugador jugador2,Tablero tablero) throws excepciones.UnidadInvalidaException, NoAlcanzanLosPuntosException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);

        Random random = new Random();

        //Este jugador comienza colocando las fichas
        Jugador jugadorQueArranca = listaJugadores.get(random.nextInt(2));
        System.out.println("Comienza colocando las fichas: " + jugadorQueArranca.getNombre()+"\n");

        // Inicia la colocacion de fichas
        listaJugadores.remove(jugadorQueArranca);
        colocarFichas(jugadorQueArranca,tablero);
        System.out.println("Turno de " + listaJugadores.get(0).getNombre()+ "\n");
        colocarFichas(listaJugadores.get(0),tablero);
    }

    public void colocarFichas(Jugador jugador,Tablero tablero) throws excepciones.UnidadInvalidaException, NoAlcanzanLosPuntosException {

        do{
            System.out.println("Que unidad quiere crear ?.\n");
            String nombreJugador = input.next();
            System.out.println("En que posicion X");
            int posX = input.nextInt();
            System.out.println("En que posicion Y");
            int posY = input.nextInt();
            Casillero casillero =new Casillero();
            try {
                tablero.crearUnidad(jugador,nombreJugador,posX,posY);
            }catch (NoAlcanzanLosPuntosException e){
                System.out.println(e.getMessage());
                System.out.println("Dispone de " + jugador.getPuntos() + "puntos");
            }catch (UnidadInvalidaException | CasilleroEnemigoException | CasilleroOcupadoExcenption e){
                System.out.println(e.getMessage()+ ",eliga una unidad correcta.");
            }
        } while (jugador.getPuntos() != 0);
    }

    private void accionesJuego(Jugador jugador1 ,Jugador jugador2,Tablero tablero) throws NoPuedeAtacarException, CurarException, CasilleroOcupadoExcenption {
        while (jugador1.puedeSeguirJugando() && jugador2.puedeSeguirJugando()){
            realizarJugadas(jugador1,jugador2,tablero);
            realizarJugadas(jugador2,jugador1,tablero);
        }

    }

    private void realizarJugadas(Jugador jugador1, Jugador jugador2, Tablero tablero) throws NoPuedeAtacarException, CurarException, CasilleroOcupadoExcenption {
        System.out.println("Turno de " + jugador1.getNombre() + " desea atacar, mover o curar una unidad");
        String accionJugador = input.next();
        if (accionJugador.equalsIgnoreCase("atacar")){
            jugadaAtacar(jugador1,jugador2,tablero);
        }
        if (accionJugador.equalsIgnoreCase("mover")){
            jugadaMover(jugador1,jugador2,tablero);
        }
        else if (accionJugador.equalsIgnoreCase("curar")){
            jugadaCurar(jugador1);
        }
    }

    private void jugadaAtacar(Jugador jugador1, Jugador jugador2, Tablero tablero) throws NoPuedeAtacarException, CurarException, CasilleroOcupadoExcenption {
        System.out.println(jugador1.getNombre() + " elija una unidad para atacar");
        System.out.println(jugador1.unidadesDisponibles());
        Unidades unidadAtacante = (Unidades) jugador1.unidadesDisponibles().get(input.nextInt());
        System.out.println("Elija una unidad a atacar");
        System.out.println(jugador2.unidadesDisponibles());
        Unidades unidadAAtacar = (Unidades) jugador2.unidadesDisponibles().get(input.nextInt());
        try {
            jugador1.atacar(unidadAtacante, unidadAAtacar);
        }catch (NoPuedeAtacarException | CurarException e){
            System.out.println(e.getMessage());
            realizarJugadas(jugador1,jugador2,tablero);
        }
    }

    private void jugadaMover(Jugador jugador1,Jugador jugador2,Tablero tablero) throws CasilleroOcupadoExcenption, NoPuedeAtacarException, CurarException {
        System.out.println(jugador1.getNombre() + "elija una unidad para mover");
        System.out.println(jugador1.unidadesDisponibles());
        Unidades unidadAMover = (Unidades) jugador1.unidadesDisponibles().get(input.nextInt());
        System.out.println("A que posicion X desea moverla");
        int movimientoX = input.nextInt();
        System.out.println("A que posicion en Y desea moverla");
        int movimientoY = input.nextInt();
        try {
            tablero.moverUnidad(unidadAMover.posicionEnX(), unidadAMover.posicionEnY(), movimientoX, movimientoY);
        }catch (CasilleroOcupadoExcenption e){
            System.out.println(e.getMessage());
            realizarJugadas(jugador1,jugador2,tablero);
        }
    }

    private void jugadaCurar(Jugador jugador){
        System.out.println("Seleccione curandero");
        System.out.println(jugador.unidadesDisponibles());
        Unidades unidadCurandero = (Unidades) jugador.unidadesDisponibles().get(input.nextInt());
        System.out.println("Seleccione unidad a curar");
        Unidades unidadACurar = (Unidades) jugador.unidadesDisponibles().get(input.nextInt());
        try {
            jugador.atacar(unidadCurandero,unidadACurar);
        } catch (CurarException | NoPuedeAtacarException e) {
            System.out.println(e.getMessage());
        }
    }
}

