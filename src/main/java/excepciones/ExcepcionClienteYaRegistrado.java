package excepciones;

public class ExcepcionClienteYaRegistrado extends Exception{
	public ExcepcionClienteYaRegistrado(){
		super("El cliente introducido ya está registrado");
	}
}
