package gestion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import factura.Factura;
import factura.Llamada;
import factura.Tarifa;
import fecha.FechaGenerico;

public class Gestion {
	private HashMap<String, Cliente> clientes;
	private HashMap<String, List<Factura>> facturas;
	private HashMap<Integer, Factura> facturasCodigo;
	private HashMap<String, List<Llamada>> llamadas;
	
	
	public Gestion(){
		clientes = new HashMap<String, Cliente>();
		facturas = new HashMap<String, List<Factura>>();
		facturasCodigo = new HashMap<Integer, Factura>();
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
	
	
	public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin){
		Collection<Cliente> clientes = this.clientes.values();
		clientes = FechaGenerico.getConjuntoPorFecha(clientes, fechaInicio, fechaFin);
		return clientes;
	}
	
	
	
	//OPERACIONES DE LAS LLAMADAS
	
	public boolean darDeAltaLlamada(String nif, Llamada llamada){
		if(!this.llamadas.containsKey(nif))
			this.llamadas.put(nif, new ArrayList<Llamada>());
		this.llamadas.get(nif).add(llamada);
		return true;
	}
	
	public List<Llamada> listarLlamadasCliente(String nif){
		if(this.llamadas.containsKey(nif)){
			return this.llamadas.get(nif);
		}
		return null;
	}
	
	
	public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin){
		Collection<Llamada> llamadas = this.llamadas.get(nif);
		llamadas = FechaGenerico.getConjuntoPorFecha(llamadas, fechaInicio, fechaFin);
		return llamadas;
	}
	
	//OPERACIONES DE LAS FACTURAS
	
	public Factura emitirFactura(String nif, Calendar fechaFacturacion){
		int codigo = this.facturasCodigo.size();
		Tarifa tarifa = this.clientes.get(nif).getTarifa();
		Calendar fechaEmision = Calendar.getInstance();
		ArrayList<Calendar> periodoFacturacion = new ArrayList<Calendar>();
		periodoFacturacion.add(fechaFacturacion);
		periodoFacturacion.add(fechaEmision);
		
		int duracionLlamadas = 0;
		List<Llamada> listaLlamadas = this.llamadas.get(nif);
		for(Llamada llamada : listaLlamadas) {
			if(llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
				duracionLlamadas += llamada.getDuracion();
		}
		
		double importe = (tarifa.getImporte() / 60) * duracionLlamadas;		
		
		Factura factura = new Factura(codigo, tarifa, fechaEmision, periodoFacturacion, importe);
		
		if(this.facturas.get(nif) == null)
			this.facturas.put(nif, new ArrayList<Factura>());
		
		this.facturas.get(nif).add(factura);
		this.facturasCodigo.put(codigo, factura);
		
		return factura;
		
	}
	
	public Factura recuperarDatosFacturaCodigo(Integer codigo){
		if(this.facturasCodigo.containsKey(codigo))
			return this.facturasCodigo.get(codigo);
		
		return null;
	}
	
	public List<Factura> recuperarFacturas(String nif){
		if(this.facturas.containsKey(nif))
			return this.facturas.get(nif);
		
		return null;
	}
	
	
	public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin){
		Collection<Factura> facturas = this.facturas.get(nif);
		facturas = FechaGenerico.getConjuntoPorFecha(facturas, fechaInicio, fechaFin);
		return facturas;
	}
	
}