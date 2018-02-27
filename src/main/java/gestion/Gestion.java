package gestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import factura.Factura;
import factura.Llamada;
import factura.Tarifa;

public class Gestion {
	private HashMap<String, Cliente> clientes;
	private HashMap<String, List<Factura>> facturas;
	private HashMap<String, Factura> facturasCodigo;
	private HashMap<String, List<Llamada>> llamadas;
	
	
	public Gestion(){
		clientes = new HashMap<String, Cliente>();
		facturas = new HashMap<String, List<Factura>>();
		facturasCodigo = new HashMap<String, Factura>();
		llamadas = new HashMap<String, List<Llamada>>();
	}
	
	//OPERACIONES DEL CLIENTE
	
	public boolean darDeAltaCliente(Cliente cliente){
		String nif = cliente.getNIF();
		if(!this.clientes.containsKey(nif)){
			this.clientes.put(nif, cliente);
			return true;
		}
		return false;
	}
	
	public boolean borrarCliente(Cliente cliente){
		String nif = cliente.getNIF();
		if(this.clientes.containsKey(nif)){
			this.clientes.remove(nif);
			return true;
		}
		return false;
	}
	
	public boolean cambiarTarifa(String nif, Tarifa tarifa){
		if(this.clientes.containsKey(nif)){
			this.clientes.get(nif).setTarifa(tarifa);
			return true;
		}
		return false;
	}
	
	public Cliente recuperarDatosNIF(String nif){
		if(this.clientes.containsKey(nif)){
			return this.clientes.get(nif);
		}
		return null;
	}
	
	public HashMap<String,Cliente> recuperarListadoClientes(){
		return this.clientes;
	}
	
	
	//OPERACIONES DE LAS LLAMADAS
	
	public boolean darDeAltaLlamada(String nif, Llamada llamada){
		if(this.llamadas.containsKey(nif)){
			this.llamadas.get(nif).add(llamada);
			return true;
		}
		return false;
	}
	
	public List<Llamada> listarLlamadasCliente(String nif){
		if(this.llamadas.containsKey(nif)){
			return this.llamadas.get(nif);
		}
		return null;
	}
	
	
	//OPERACIONES DE LAS FACTURAS
	
	public Factura emitirFactura(String nif){
		
	}
	
	public Factura recuperarDatosFacturaCodigo(String codigo){
		if(this.facturasCodigo.containsKey(codigo))
			return this.facturasCodigo.get(codigo);
		
		return null;
	}
	
	public List<Factura> recuperarFacturas(String nif){
		if(this.facturas.containsKey(nif))
			return this.facturas.get(nif);
		
		return null;
	}
	
}