package main;
import java.util.Scanner;

import cliente.Cliente;
import gestion.Gestion;

public class Main {

	public static void main(String[] args) {
		Gestion gestion = new Gestion();
		Cliente cliente = Cliente.pedirDatosCliente();
		System.out.println(Menu.getMenu());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Elige una opción:");
		byte opcion = scanner.nextByte();
		Menu opcionMenu = Menu.getOpcion(opcion);
		switch(opcionMenu) {
			//completar switch menú
		}

	
	}
}
