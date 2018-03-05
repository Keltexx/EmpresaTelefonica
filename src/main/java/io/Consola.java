package io;

import java.util.Scanner;

public class Consola {
	private Scanner scanner;
	
	public Consola() {
		scanner = new Scanner(System.in);
	}
	
	public String insertData(Object data) {
		System.out.println("Introduce "+ data +": ");
		
		return scanner.nextLine();
	}
	
	public void showData(Object data) {
		System.out.println(data);
	}
}
