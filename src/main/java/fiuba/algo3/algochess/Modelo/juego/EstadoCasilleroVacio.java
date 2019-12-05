package fiuba.algo3.algochess.Modelo.juego;

import fiuba.algo3.algochess.Modelo.excepciones.CasilleroOcupadoException;
import fiuba.algo3.algochess.Modelo.excepciones.CasilleroVacioExcepcion;
import fiuba.algo3.algochess.Modelo.unidades.Unidad;
import fiuba.algo3.algochess.Modelo.unidades.UnidadNula;

public class EstadoCasilleroVacio implements EstadoCasillero {



    @Override
    public EstadoCasillero guardarUnidad(Unidad unidad){
        return new EstadoCasilleroOcupado(unidad);
    }

    @Override
    public EstadoCasillero eliminarUnidad(){
        throw new CasilleroVacioExcepcion("El casillero esta vacio");
    }

    @Override
    public Unidad obtenerUnidad(){
        throw new CasilleroVacioExcepcion("El casillero esta vacio");
    }

    @Override

    public Unidad obtenerUnidadCercana() {
        return new UnidadNula();
    }

    @Override
    public int guardarUnidadBatallon(Unidad unidad, Jugador jugador, Casillero casilleroInicio, Casillero casilleroFin) {
        jugador.unidadModificarPosicionCasillero(unidad,casilleroFin);
        casilleroInicio.eliminarUnidad();
        unidad.modificarPosicion(casilleroFin.getPosicionCasillero());
        casilleroFin.modificarEstado(new EstadoCasilleroOcupado(unidad));
        return 1;
    }
}
