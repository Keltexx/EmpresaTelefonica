package interfazusuario.controlador;

import java.util.Calendar;
import java.util.HashMap;

import cliente.Cliente;
import cliente.ClienteFactory;
import cliente.Direccion;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import excepciones.ExcepcionListaClientesVacia;
import factura.Tarifa;
import interfazusuario.modelo.Modelo;
import interfazusuario.vista.Vista;


public class ImplementacionControlador implements Controlador{

	private Modelo modelo;
	private Vista vista;
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public boolean creaCliente(int tipo,String nombre,String apellidos,String nif,Direccion dir,String correo,Calendar fecha,Tarifa tarifa) throws ExcepcionClienteYaRegistrado {
		Cliente cliente = ClienteFactory.crearCliente(tipo,nombre,apellidos,nif,dir,correo,fecha,tarifa);
		return modelo.darDeAltaCliente(cliente);
		
	}
	
	public boolean borraCliente(String nif) throws ExcepcionClienteNoEncontrado {
		return modelo.borrarCliente(nif);
	}
	
	public boolean cambiaTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado {
		return modelo.cambiarTarifa(nif, tarifa);
	}
	
	public Cliente recuperarDatosCliente(String nif) throws ExcepcionClienteNoEncontrado {
		return modelo.recuperarDatosNIF(nif);

	}
	
	public HashMap<String, Cliente> recuperaListadoClientes() throws ExcepcionListaClientesVacia {
		return modelo.recuperarListadoClientes();
		
	}
}
