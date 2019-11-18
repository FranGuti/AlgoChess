import Excepciones.MovimientoInvalidoException;

public class Posicion {

    private int posicionX, posicionY;
    public void posicionNueva(int posicionX, int posicionY){

        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public void movimientoNuevo(int posicionX, int posicionY) throws MovimientoInvalidoException {
        int posicionFinalX = this.posicionX - posicionX;
        int posicionFinalY = this.posicionY - posicionY;

        //Verifico que no se mueva mas de un lugar.
        if (posicionFinalX < -1 || posicionFinalX > 1 || posicionFinalY < -1 || posicionFinalY > 1){
            throw new MovimientoInvalidoException("La unidad solo se mueve de a un casillero");
        }
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public int distanciaXHasta(Posicion posicion){
        return (Math.abs(this.posicionX - posicion.getPosicionX()));
    }

    public int distanciaYHasta(Posicion posicion){
        return (Math.abs(this.posicionY - posicion.getPosicionY()));
    }

   /* creo que tener estos métodos rompe el encapsulamiento que se trata de tener con
   esta clase */

   public int getPosicionX(){
        return posicionX;
    }
    public int getPosicionY(){
        return posicionY;
    }

}
