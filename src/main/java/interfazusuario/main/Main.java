package interfazusuario.main;

import interfazusuario.controlador.ImplementacionControlador;
import interfazusuario.modelo.ImplementacionModelo;
import interfazusuario.vista.ImplementacionVista;

public class Main {
	
    public static void main(String args[]) {
	ImplementacionControlador controlador = new ImplementacionControlador();
	ImplementacionVista vista = new ImplementacionVista();
	ImplementacionModelo modelo = new ImplementacionModelo();

	modelo.setVista(vista);
	controlador.setVista(vista);
	controlador.setModelo(modelo);
	vista.setModelo(modelo);
	vista.setControlador(controlador);
	modelo.cargarDatos();
	vista.creaGUI();
    }
    
}
