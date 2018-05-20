package interfazusuario.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import interfazusuario.vista.Vista;
import excepciones.*;

public class ImplementacionModelo implements Modelo{
	
	private HashMap<String, Cliente> clientes;
	private HashMap<String, List<Factura>> facturas;
	private HashMap<Integer, Factura> facturasCodigo;
	private HashMap<String, List<Llamada>> llamadas;
	private Vista vista;
	
	public ImplementacionModelo() {
		clientes = new HashMap<String, Cliente>();
		facturas = new HashMap<String, List<Factura>>();
		facturasCodigo = new HashMap<Integer, Factura>();
		llamadas = new HashMap<String, List<Llamada>>();
	}
	
	public void setVista(Vista vista) {
		this.vista = vista;
	}

	// OPERACIONES DEL CLIENTE

	public boolean darDeAltaCliente(Cliente cliente) throws ExcepcionClienteYaRegistrado {
		String nif = cliente.getNIF();
		if (!this.clientes.containsKey(nif)) {
			this.clientes.put(nif, cliente);
			return true;
		}
		throw new ExcepcionClienteYaRegistrado();
	}

	public boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado {
		if (this.clientes.containsKey(nif)) {
			this.clientes.remove(nif);
			return true;
		}
		throw new ExcepcionClienteNoEncontrado();
	}

	public boolean cambiarTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado {
		if (this.clientes.containsKey(nif)) {
			this.clientes.get(nif).setTarifa(tarifa);
			return true;
		}
		throw new ExcepcionClienteNoEncontrado();
	}

	public Cliente recuperarDatosNIF(String nif) throws ExcepcionClienteNoEncontrado {
		if (this.clientes.containsKey(nif)) {
			return this.clientes.get(nif);
		}
		throw new ExcepcionClienteNoEncontrado();
	}

	public HashMap<String, Cliente> recuperarListadoClientes() throws ExcepcionListaClientesVacia{
		if(this.clientes.isEmpty())
			throw new ExcepcionListaClientesVacia();
		
		return this.clientes;
	}

	public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia{
		if(fechaInicio.after(fechaFin))
			throw new ExcepcionFechas();
		
		Collection<Cliente> clientes = this.clientes.values();
		clientes = FechaGenerico.getConjuntoPorFecha(clientes, fechaInicio, fechaFin);
		
		if(clientes.isEmpty())
			throw new ExcepcionListaClientesVacia();
		
		return clientes;
	}

	// OPERACIONES DE LAS LLAMADAS

	public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ExcepcionClienteNoEncontrado {
		if(!this.clientes.containsKey(nif))
			throw new ExcepcionClienteNoEncontrado();		
		
		if (!this.llamadas.containsKey(nif))
			this.llamadas.put(nif, new ArrayList<Llamada>());
		this.llamadas.get(nif).add(llamada);
		return true;
	}

	public List<Llamada> listarLlamadasCliente(String nif) throws ExcepcionClienteNoEncontrado {
		if (this.llamadas.containsKey(nif)) {
			return this.llamadas.get(nif);
		}
		throw new ExcepcionClienteNoEncontrado();
	}

	public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado{
		if(fechaInicio.after(fechaFin))
			throw new ExcepcionFechas();
		
		if(!this.llamadas.containsKey(nif))
			throw new ExcepcionClienteNoEncontrado();
		
		Collection<Llamada> llamadas = this.llamadas.get(nif);
		llamadas = FechaGenerico.getConjuntoPorFecha(llamadas, fechaInicio, fechaFin);
		if(llamadas.isEmpty())
			throw new ExcepcionListaLlamadasVacia();
		
		return llamadas;
	}

	// OPERACIONES DE LAS FACTURAS

	public Factura emitirFactura(String nif, Calendar fechaFacturacion) throws ExcepcionClienteNoEncontrado{
		if(!this.clientes.containsKey(nif))
			throw new ExcepcionClienteNoEncontrado();
		
		int codigo = this.facturasCodigo.size();
		Tarifa tarifa = this.clientes.get(nif).getTarifa();
		Calendar fechaEmision = Calendar.getInstance();

		int duracionLlamadas = 0;
		List<Llamada> listaLlamadas = this.llamadas.get(nif);
		for (Llamada llamada : listaLlamadas) {
			if (llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
				duracionLlamadas += llamada.getDuracion();
		}

		double importe = (tarifa.getImporte() / 60) * duracionLlamadas;

		Factura factura = new Factura(codigo, tarifa, fechaEmision, importe);

		if (this.facturas.get(nif) == null)
			this.facturas.put(nif, new ArrayList<Factura>());

		this.facturas.get(nif).add(factura);
		this.facturasCodigo.put(codigo, factura);

		return factura;

	}

	public Factura recuperarDatosFacturaCodigo(Integer codigo) throws ExcepcionFacturaNoEncontrada {
		if (this.facturasCodigo.containsKey(codigo))
			return this.facturasCodigo.get(codigo);

		throw new ExcepcionFacturaNoEncontrada();
	}

	public List<Factura> recuperarFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia {
		if (!this.facturas.containsKey(nif))
			throw new ExcepcionClienteNoEncontrado();
		
		List<Factura> listaFacturas = this.facturas.get(nif);
		if(listaFacturas.isEmpty())
			throw new ExcepcionListaFacturasVacia();
		
		return listaFacturas;
	}

	public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas {
		if(fechaInicio.after(fechaFin))
			throw new ExcepcionFechas();
		
		if(!this.facturas.containsKey(nif))
			throw new ExcepcionClienteNoEncontrado();
		
		Collection<Factura> facturas = this.facturas.get(nif);
		facturas = FechaGenerico.getConjuntoPorFecha(facturas, fechaInicio, fechaFin);
		
		if(facturas.isEmpty())
			throw new ExcepcionListaFacturasVacia();
		
		return facturas;
	}

	// I/O

	public void guardarDatos() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("io/clientes.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(clientes);
			fos = new FileOutputStream("io/facturas.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(facturas);
			fos = new FileOutputStream("io/facturasCodigo.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(facturasCodigo);
			fos = new FileOutputStream("io/llamadas.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(llamadas);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarDatos() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("io/clientes.bin");
			ois = new ObjectInputStream(fis);
			clientes = (HashMap<String, Cliente>) ois.readObject();
			fis = new FileInputStream("io/facturas.bin");
			ois = new ObjectInputStream(fis);
			facturas = (HashMap<String, List<Factura>>) ois.readObject();
			fis = new FileInputStream("io/facturasCodigo.bin");
			ois = new ObjectInputStream(fis);
			facturasCodigo= (HashMap<Integer, Factura>) ois.readObject();
			fis = new FileInputStream("io/llamadas.bin");
			ois = new ObjectInputStream(fis);
			llamadas = (HashMap<String, List<Llamada>>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}