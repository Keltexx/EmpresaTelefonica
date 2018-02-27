package gestion;

import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import factura.Factura;
import factura.Llamada;
import factura.Tarifa;

public class Gestion {
	private HashMap<Cliente, List<Factura>> facturas;
	private HashMap<Cliente, List<Llamada>> llamadas;
	
	
	public Gestion(){
		facturas = new HashMap<Cliente, List<Factura>>();
		llamadas = new HashMap<Cliente, List<Llamada>>();
	}
	
	//OPERACIONES DEL CLIENTE
	
	public void darDeAltaCliente(Cliente cliente){
		
	}
	
	public void borrarCliente(Cliente cliente){
		
	}
	
	public void cambiarTarifa(Cliente cliente, Tarifa tarifa){
		
	}
	
	public void recuperarDatosNIF(String NIF){
		
	}
	
	public void recuperarListadoClientes(){
		
	}
	
	
	//OPERACIONES DE LAS LLAMADAS
	
	public void darDeAltaLlamada(Cliente cliente, Llamada llamada){
		
	}
	
	public void listarLlamadasCliente(Cliente cliente){
		
	}
	
	
	//OPERACIONES DE LAS FACTURAS
	
	public void emitirFactura(Cliente cliente){
		
	}
	
	public void recuperarDatosFacturaCodigo(String codigo){
		
	}
	
	public void recuperarFacturas(Cliente cliente){
		
	}
	
}