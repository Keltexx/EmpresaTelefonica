package excepciones;

public class ExcepcionListaLlamadasVacia extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1478271360492602437L;

	public ExcepcionListaLlamadasVacia(){
		super("No hay llamadas guardadas");	
	}	
}
