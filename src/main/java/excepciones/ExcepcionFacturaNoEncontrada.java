package excepciones;

public class ExcepcionFacturaNoEncontrada extends Exception{
	public ExcepcionFacturaNoEncontrada(){
		super("La factura con el código solicitado no existe");
	}
}
