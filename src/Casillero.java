import Excepciones.CasilleroOcupadoException;

public class Casillero {
    private Unidad unidad_actual;
    private UnidadNula unidadNula = new UnidadNula(0,0);

    public void guardarUnidad(Unidad unidadNueva) {
        unidad_actual = unidadNueva;
   }

    public void modificarUnidad(Unidad unidadNueva) throws CasilleroOcupadoException {
        //Si la unidad almacenada no es una unidad nula lanza error
        if(!unidad_actual.getClass().equals(unidadNula.getClass())){
            throw new CasilleroOcupadoException("El casillero esta ocupado");
        }
        unidad_actual = unidadNueva;
   }


   public Unidad getUnidad(){
       return unidad_actual;
   }

   public void eliminarUnidad(){
        unidad_actual = unidadNula;
   }
}