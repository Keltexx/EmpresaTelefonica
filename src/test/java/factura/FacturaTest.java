package factura;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uji.www.GeneradorDatosINE;
import factura.*;
import gestion.Gestion;

import java.util.ArrayList;
import java.util.Calendar;

import cliente.Cliente;
import cliente.Direccion;
import cliente.Empresa;

public class FacturaTest {
	private Gestion gestion;
	private GeneradorDatosINE generador;
	private Cliente cliente;
	private Llamada llamada1;
	private Llamada llamada2;
	private Llamada llamada3;
	private Factura factura;

	@Before
	public void init() {
		gestion=new Gestion();
		generador = new GeneradorDatosINE();
		cliente= new Cliente(generador.getNombre(), generador.getNIF(),
				new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"empresa@empresa.com",Calendar.getInstance(),new Tarifa(10));
		llamada1= new Llamada(666777888,Calendar.getInstance(),Calendar.getInstance(),20);
		llamada2= new Llamada(666777888,Calendar.getInstance(),Calendar.getInstance(),10);
		llamada3= new Llamada(777888999,Calendar.getInstance(),Calendar.getInstance(),18);
		gestion.darDeAltaCliente(cliente);
		
		ArrayList<Calendar> periodo = new ArrayList<Calendar>();
		periodo.add(Calendar.getInstance());
		periodo.add(Calendar.getInstance());
		periodo.get(0).set(Calendar.MONTH, periodo.get(0).get(Calendar.MONTH) - 1);
		
		Calendar fechaFacturacion = periodo.get(0);
		factura = new Factura(0,new Tarifa(10),fechaFacturacion,periodo,8);
		
		gestion.darDeAltaLlamada(cliente.getNIF(), llamada1);
		gestion.darDeAltaLlamada(cliente.getNIF(), llamada2);
		gestion.darDeAltaLlamada(cliente.getNIF(), llamada3);
		
	}
	
	@After
	public void finish() {
		gestion=null;
		generador=null;
		cliente=null;
		llamada1=null;
		llamada2=null;
		llamada3=null;
	}

	@Test
	public void testEmitirFactura() {
		Calendar fechaFacturacion = Calendar.getInstance();
		fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH)-1);
		Factura aux = gestion.emitirFactura(cliente.getNIF(), fechaFacturacion);
		
		assertEquals(factura.getCodigo(),aux.getCodigo());
		assertEquals(factura.getTarifa().getImporte(),aux.getTarifa().getImporte(),1);
		assertEquals(factura.getImporte(),aux.getImporte(),1);
	}
	
	@Test
	public void testRecuperarDatosFacturaCodigo() {
		assertNull(gestion.recuperarDatosFacturaCodigo(0));
		
		Calendar fechaFacturacion = Calendar.getInstance();
		fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH)-1);
		gestion.emitirFactura(cliente.getNIF(), fechaFacturacion);
		
		assertNotNull(gestion.recuperarDatosFacturaCodigo(0));
	}
	
	@Test
	public void testRecuperarFacturas() {
		assertNull(gestion.recuperarFacturas(cliente.getNIF()));
		
		Calendar fechaFacturacion = Calendar.getInstance();
		fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH)-1);
		gestion.emitirFactura(cliente.getNIF(), fechaFacturacion);
		
		assertNotNull(gestion.recuperarFacturas(cliente.getNIF()));
	}
	
}
