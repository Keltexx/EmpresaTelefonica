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
				"empresa@empresa.com",Calendar.getInstance(),new Tarifa(5));
		llamada1= new Llamada(666777888,Calendar.getInstance(),Calendar.getInstance(),20);
		llamada2= new Llamada(666777888,Calendar.getInstance(),Calendar.getInstance(),10);
		llamada3= new Llamada(777888999,Calendar.getInstance(),Calendar.getInstance(),18);
		gestion.darDeAltaCliente(cliente);
		
		ArrayList<Calendar> periodo = new ArrayList<Calendar>();
		periodo.add(Calendar.getInstance());
		periodo.add(Calendar.getInstance());
		periodo.get(0).set(Calendar.MONTH, periodo.get(0).get(Calendar.MONTH) - 1);
		
		factura = new Factura(0,new Tarifa(10),Calendar.getInstance(),periodo,8);
		
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
		Factura aux = gestion.emitirFactura(cliente.getNIF(), Calendar.getInstance());
		
		assertEquals(factura,aux);
	}
	
}
