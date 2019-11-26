package fiuba.algo3.algochess.unidades;

import fiuba.algo3.algochess.excepciones.NoPuedeAtacarException;
import fiuba.algo3.algochess.excepciones.UnidadNulaException;
import fiuba.algo3.algochess.juego.Casillero;

public interface EstadoJinete {
    public EstadoJinete cambiarEstadoJinete(String estado);

    public void atacarDistanciaCerca(Unidad atacado, double danioExtra) throws NoPuedeAtacarException, UnidadNulaException;

    public void atacarDistanciaMediana(Unidad atacado, double danioExtra) throws NoPuedeAtacarException, UnidadNulaException;

    public void atacarDistanciaLejana(Unidad atacado, double danioExtra, Casillero[][] arrayCasillero) throws NoPuedeAtacarException;
}
