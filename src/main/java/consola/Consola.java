package consola;

import java.util.Scanner;

public class Consola {
	
	private Scanner scanner = new Scanner(System.in);
	
	
	public void mostrarDato(Object dato){
		System.out.println(dato);
	}
	
	public String pedirDato(String dato){
		this.mostrarDato(dato);
		
		String cadena = "";
		while(cadena.isEmpty())
			cadena = scanner.nextLine();
		
		return cadena;
	}
}
