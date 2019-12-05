package fiuba.algo3.algochess.Modelo.unidades;

import fiuba.algo3.algochess.Modelo.excepciones.*;
import fiuba.algo3.algochess.Modelo.juego.Puntos;
import fiuba.algo3.algochess.Modelo.juego.Posicion;

import java.util.ArrayList;
import java.util.HashMap;

//Clase donde se implementa la fiuba.algo3.algochess.Modelo.unidades.Unidad fiuba.algo3.algochess.Modelo.unidades.Catapulta

public class Catapulta implements Unidad{
    private static int costoUnidad = 5;
    private double vidaUnidad = 50;
    private static double danioDistancia = 20;
    private Batallon batallon = new Batallon();
    private Posicion posicion;
    private Emisario emisario;
    private double danioExtra = 0;


    public Catapulta(Puntos puntosJugador, Posicion posicion, Emisario emisario) {
        this.posicion = posicion;
        this.emisario = emisario;
        puntosJugador.alcanzanPuntos(costoUnidad);
    }

    public double getVidaUnidad(){
        return vidaUnidad;
    }

    @Override
    public Posicion getPosicion() {
        return posicion;
    }

    @Override
    public void modificarPosicion(Posicion posicion) {
        this.posicion = posicion;
        this.emisario.notificar(this);
    }

    @Override
    public void recibirNotificacion() {

    }

    @Override
    public void atacarDistanciaCerca(Unidad atacado, boolean esUnidadAliada, HashMap tablero) {
        throw new NoPuedeAtacarException("La catapulta solo ataca a distancia");
    }

    @Override
    public void atacarDistanciaMediana(Unidad atacado, boolean esUnidadAliada, HashMap tablero) {
        throw new NoPuedeAtacarException("La catapulta solo ataca a distancia");
    }

    @Override
    public void atacarDistanciaLejana(Unidad atacado, boolean esUnidadAliada, HashMap tablero) {
        UnidadesCercanas unidadesCercanas = new UnidadesCercanas();
        ArrayList unidadesAAtacar = unidadesCercanas.unidadesCercanas(tablero, atacado);
        for (Object unidad : unidadesAAtacar){
            ( (Unidad) unidad).recibirDanio(danioDistancia);
        }
    }

    @Override
    public void recibirDanio(double danioRecibido) {
        vidaUnidad -= danioRecibido + (danioRecibido * this.danioExtra);
    }

    @Override
    public int cuantoCuesta() {
        return costoUnidad;
    }

    @Override
    public void curarse(int vidaACurar) {
        throw new CurarException("La catapulta no puede ser curada");
    }

    @Override
    public ArrayList habilidadMoverse(Unidad unidadAMover, HashMap tablero, ArrayList unidadesAliadas) {
        throw new MovimientoInvalidoException("La catapulta no se puede mover");
    }

    @Override
    public void setDanioPorCasillero(double danioExtra) {
        this.danioExtra = danioExtra;
    }

    @Override
    public void agregarSoldadoAListaDeSoldados(ArrayList<Soldado> listaDeSoldados){

    }
    @Override
    public void agregarUnidadCercana(ArrayList batallonUnidades, ArrayList listaUnidades) {
        if(!batallonUnidades.contains(this)){
            batallonUnidades.add(this);
            listaUnidades.add(this);
        }
    }
    @Override
    public void agregarUnidadADistancia(ArrayList unidadesADistanciaCercana) {
        unidadesADistanciaCercana.add(this);
    }
}