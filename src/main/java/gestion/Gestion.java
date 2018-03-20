package gestion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import excepciones.*;

public class Gestion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2108257942382069823L;
	private HashMap<String, Cliente> clientes;
	private HashMap<String, List<Factura>> facturas;
	private HashMap<Integer, Factura> facturasCodigo;
	private HashMap<String, List<Llamada>> llamadas;

	public Gestion() {
		clientes = new HashMap<String, Cliente>();
		facturas = new HashMap<String, List<Factura>>();
		facturasCodigo = new HashMap<Integer, Factura>();
		llamadas = new HashMap<String, List<Llamada>>();
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

	public HashMap<String, Cliente> recuperarListadoClientes() {
		return this.clientes;
	}

	public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) {
		Collection<Cliente> clientes = this.clientes.values();
		clientes = FechaGenerico.getConjuntoPorFecha(clientes, fechaInicio, fechaFin);
		return clientes;
	}

	// OPERACIONES DE LAS LLAMADAS

	public boolean darDeAltaLlamada(String nif, Llamada llamada) {
		if (!this.llamadas.containsKey(nif))
			this.llamadas.put(nif, new ArrayList<Llamada>());
		this.llamadas.get(nif).add(llamada);
		return true;
	}

	public List<Llamada> listarLlamadasCliente(String nif) {
		if (this.llamadas.containsKey(nif)) {
			return this.llamadas.get(nif);
		}
		return null;
	}

	public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) {
		Collection<Llamada> llamadas = this.llamadas.get(nif);
		llamadas = FechaGenerico.getConjuntoPorFecha(llamadas, fechaInicio, fechaFin);
		return llamadas;
	}

	// OPERACIONES DE LAS FACTURAS

	public Factura emitirFactura(String nif, Calendar fechaFacturacion) {
		int codigo = this.facturasCodigo.size();
		Tarifa tarifa = this.clientes.get(nif).getTarifa();
		Calendar fechaEmision = Calendar.getInstance();
		ArrayList<Calendar> periodoFacturacion = new ArrayList<Calendar>();
		periodoFacturacion.add(fechaFacturacion);
		periodoFacturacion.add(fechaEmision);

		int duracionLlamadas = 0;
		List<Llamada> listaLlamadas = this.llamadas.get(nif);
		for (Llamada llamada : listaLlamadas) {
			if (llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
				duracionLlamadas += llamada.getDuracion();
		}

		double importe = (tarifa.getImporte() / 60) * duracionLlamadas;

		Factura factura = new Factura(codigo, tarifa, fechaEmision, periodoFacturacion, importe);

		if (this.facturas.get(nif) == null)
			this.facturas.put(nif, new ArrayList<Factura>());

		this.facturas.get(nif).add(factura);
		this.facturasCodigo.put(codigo, factura);

		return factura;

	}

	public Factura recuperarDatosFacturaCodigo(Integer codigo) {
		if (this.facturasCodigo.containsKey(codigo))
			return this.facturasCodigo.get(codigo);

		return null;
	}

	public List<Factura> recuperarFacturas(String nif) {
		if (this.facturas.containsKey(nif))
			return this.facturas.get(nif);

		return null;
	}

	public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) {
		Collection<Factura> facturas = this.facturas.get(nif);
		facturas = FechaGenerico.getConjuntoPorFecha(facturas, fechaInicio, fechaFin);
		return facturas;
	}

	// I/O

	public void guardarDatos() throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("clientes.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(clientes);
			fos = new FileOutputStream("facturas.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(facturas);
			fos = new FileOutputStream("facturasCodigo.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(facturasCodigo);
			fos = new FileOutputStream("llamadas.bin");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(llamadas);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fos.close();
			oos.close();
		}
	}

	public void cargarDatos() throws IOException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("clientes.bin");
			ois = new ObjectInputStream(fis);
			clientes = (HashMap<String, Cliente>) ois.readObject();
			fis = new FileInputStream("facturas.bin");
			ois = new ObjectInputStream(fis);
			facturas = (HashMap<String, List<Factura>>) ois.readObject();
			fis = new FileInputStream("facturasCodigo.bin");
			ois = new ObjectInputStream(fis);
			facturasCodigo= (HashMap<Integer, Factura>) ois.readObject();
			fis = new FileInputStream("llamadas.bin");
			ois = new ObjectInputStream(fis);
			llamadas = (HashMap<String, List<Llamada>>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			fis.close();
			ois.close();
		}
	}

}