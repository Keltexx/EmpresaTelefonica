package interfazusuario.controlador;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cliente.Cliente;
import cliente.ClienteFactory;
import cliente.Direccion;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import excepciones.ExcepcionFacturaNoEncontrada;
import excepciones.ExcepcionFechas;
import excepciones.ExcepcionListaClientesVacia;
import excepciones.ExcepcionListaFacturasVacia;
import excepciones.ExcepcionListaLlamadasVacia;
import factura.Factura;
import factura.Llamada;
import factura.Tarifa;
import interfazusuario.modelo.Modelo;



public class ImplementacionControlador implements Controlador{

	private Modelo modelo;

	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
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
	
	public Collection<Cliente> recuperaListadoClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaClientesVacia, ExcepcionFechas{
		return modelo.mostrarListadoClientesFechas(fechaInicio, fechaFin);
	}
	
	public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ExcepcionClienteNoEncontrado{
		return modelo.darDeAltaLlamada(nif, llamada);
	}
	
	public List<Llamada> listarLlamadasCliente(String nif) throws ExcepcionClienteNoEncontrado{
		return modelo.listarLlamadasCliente(nif);
	}

	public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado{
		return modelo.mostrarListadoLlamadasFechas(nif, fechaInicio, fechaFin);
	}

	public Factura emitirFactura(String nif, Calendar fechaFacturacion) throws ExcepcionClienteNoEncontrado{
		return modelo.emitirFactura(nif, fechaFacturacion);
	}
	
	public Factura recuperarDatosFacturaCodigo(Integer codigo) throws ExcepcionFacturaNoEncontrada{
		return modelo.recuperarDatosFacturaCodigo(codigo);
	}

	public List<Factura> recuperarFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia{
		return modelo.recuperarFacturas(nif);
	}

	public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas{
		return modelo.mostrarListadoFacturasFechas(nif, fechaInicio, fechaFin);
	}


}
