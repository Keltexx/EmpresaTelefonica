package excepciones;

public class ExcepcionFechas extends Exception{
	public ExcepcionFechas(){
		super("La fecha de inicio debe ser anterior a la fecha de fin");
	}
}
