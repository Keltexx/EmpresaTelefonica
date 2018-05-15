package interfazusuario.modelo;

import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import factura.Factura;
import factura.Llamada;
import vista.Vista;

public class ImplementacionModelo implements Modelo {
	private HashMap<String, Cliente> clientes;
	private HashMap<String, List<Factura>> facturas;
	private HashMap<Integer, Factura> facturasCodigo;
	private HashMap<String, List<Llamada>> llamadas;
	
	private Vista vista;

	public void setVista(InformaVista vista) {
		this.vista = vista;
	}
	
	
}
