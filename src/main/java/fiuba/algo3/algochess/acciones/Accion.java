package fiuba.algo3.algochess.acciones;

import fiuba.algo3.algochess.excepciones.CurarException;
import fiuba.algo3.algochess.excepciones.NoPuedeAtacarException;
import fiuba.algo3.algochess.excepciones.UnidadNulaException;
import fiuba.algo3.algochess.unidades.Unidad;

public interface Accion{
    public void atacar(Unidad atacante, Unidad atacado) throws NoPuedeAtacarException, CurarException, UnidadNulaException;
}