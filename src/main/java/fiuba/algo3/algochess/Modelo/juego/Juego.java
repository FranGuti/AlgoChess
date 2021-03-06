package fiuba.algo3.algochess.Modelo.juego;


import java.util.concurrent.atomic.AtomicBoolean;

public class Juego {

    public Jugador jugadorAliado;
    public Jugador jugadorEnemigo;
    public Tablero tablero;
    public EstadoJuego estadoJuego;


    public Juego(String jugador1, String jugador2) {
        jugadorAliado = new Jugador(jugador1);
        jugadorEnemigo = new Jugador(jugador2);
        tablero = new Tablero(jugadorAliado, jugadorEnemigo);
        estadoJuego = new EstadoJuegoAliado(jugadorAliado,jugadorEnemigo);

    }

    public void crearUnidad(String nombreUnidad, Posicion posicion){
        estadoJuego = estadoJuego.crearUnidad(tablero,nombreUnidad,posicion);
    }

    public void mover(Posicion posicionInicial,Posicion posicionFinal){
        Posicion posicion = new Posicion(posicionInicial.posicionX,posicionInicial.posicionY);
        Posicion posicion1 = new Posicion(posicionFinal.posicionX,posicionFinal.posicionY);
        estadoJuego = estadoJuego.mover(posicion,posicion1,tablero);

    }

    public void atacar(Posicion posicionAtancate,Posicion posicionAtacado){
        estadoJuego = estadoJuego.atacar(posicionAtancate,posicionAtacado,tablero);
    }

    public Jugador jugadorActual(){
        return estadoJuego.jugadorActual();
    }


}