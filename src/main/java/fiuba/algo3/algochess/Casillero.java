package fiuba.algo3.algochess;

import fiuba.algo3.algochess.excepciones.CasilleroOcupadoExcenption;

public class Casillero {
    private Unidad unidad_actual = null;

    public boolean esta_vacio(){
        return (this.unidad_actual == null);
    }

    // Mueve unidad a otro casillero, elimina la guardada en el atributo unidad_actual
    public void mover_unidad_a(Casillero destino, int posicionX,int posicionY) throws CasilleroOcupadoExcenption {
        unidad_actual.nuevaPosicion(posicionX,posicionY);
        destino.recibir_unidad(this.unidad_actual);
        this.unidad_actual = null;
    }
    // Recibe unidad de otro casillero
    public void recibir_unidad(Unidad unidad) throws CasilleroOcupadoExcenption {
        if (!this.esta_vacio()) {
            throw new CasilleroOcupadoExcenption("El casillero esta ocupado");
        }
        unidad_actual = unidad;
    }

    public Unidad getUnidad(){
        return unidad_actual;
    }
}