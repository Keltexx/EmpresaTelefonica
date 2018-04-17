package cliente;

public class ClienteFactory {
	public static Cliente crearCliente(int tipo) {
		Cliente cliente = null;
		
		switch(tipo) {
		case 0:
			cliente = new Empresa();
			break;
		case 1:
			cliente = new Particular();
			break;
		}
		return cliente;
	}
}
