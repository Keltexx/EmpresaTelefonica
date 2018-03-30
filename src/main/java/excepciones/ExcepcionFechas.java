package excepciones;

public class ExcepcionFechas extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6493546682060910141L;

	public ExcepcionFechas(){
		super("La fecha de inicio debe ser anterior a la fecha de fin");
	}
}
