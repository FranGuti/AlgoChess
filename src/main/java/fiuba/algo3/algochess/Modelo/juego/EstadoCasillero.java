package fiuba.algo3.algochess.Modelo.juego;

import fiuba.algo3.algochess.Modelo.excepciones.CasilleroOcupadoException;
import fiuba.algo3.algochess.Modelo.excepciones.CasilleroVacioExcepcion;
import fiuba.algo3.algochess.Modelo.unidades.Unidad;

public interface EstadoCasillero {

    public EstadoCasillero guardarUnidad(Unidad unidad) throws CasilleroOcupadoException;
    public EstadoCasillero eliminarUnidad() throws CasilleroVacioExcepcion;
    public Unidad obtenerUnidad() throws CasilleroVacioExcepcion;
}