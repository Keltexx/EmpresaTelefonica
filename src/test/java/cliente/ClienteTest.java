package cliente;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uji.www.GeneradorDatosINE;
import factura.*;
import gestion.Gestion;


import java.util.Calendar;
import cliente.Cliente;


public class ClienteTest {
	private Gestion gestion;
	private GeneradorDatosINE generador;
	private Cliente empresa;
	private Cliente particular;
	private Cliente hombre;
	private Cliente mujer;

	@Before
	public void init() {
		gestion = new Gestion();
		generador = new GeneradorDatosINE();
		empresa = new Empresa(generador.getNombre(), generador.getNIF(),
				new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"empresa@empresa.com",Calendar.getInstance(),new Tarifa(5));
		particular = new Particular(generador.getNombre(),generador.getApellido(), generador.getNIF(),
				new Direccion(23456, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"particular@particular.com",Calendar.getInstance(),new Tarifa(10));
		hombre = new Particular(generador.getNombreHombre(),generador.getApellido(), generador.getNIF(),
				new Direccion(34567, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"hombre@hombre.com",Calendar.getInstance(),new Tarifa(7));
		mujer =new Particular(generador.getNombreMujer(),generador.getApellido(), generador.getNIF(),
				new Direccion(45678, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"mujer@mujer.com",Calendar.getInstance(),new Tarifa(8));
	}

	@After
	public void finish() {
		gestion=null;
		generador=null;
		empresa=null;
		particular=null;
		hombre=null;
		mujer=null;
	}
	
	@Test
	public void testDarDeAltaCliente() {
		assertTrue(gestion.darDeAltaCliente(empresa));
		assertTrue(gestion.darDeAltaCliente(particular));
		assertTrue(gestion.darDeAltaCliente(hombre));
		assertTrue(gestion.darDeAltaCliente(mujer));
	}
	
	@Test
	public void testBorrarCliente() {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertTrue(gestion.borrarCliente(empresa));
		assertTrue(gestion.borrarCliente(particular));
		assertTrue(gestion.borrarCliente(hombre));
		assertTrue(gestion.borrarCliente(mujer));
	}
	
	@Test
	public void testCambiarTarifa() {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertTrue(gestion.cambiarTarifa(empresa.getNIF(), new Tarifa(1)));
		assertTrue(gestion.cambiarTarifa(particular.getNIF(), new Tarifa(1)));
		assertTrue(gestion.cambiarTarifa(hombre.getNIF(), new Tarifa(1)));
		assertTrue(gestion.cambiarTarifa(mujer.getNIF(), new Tarifa(1)));
	}
	
	@Test 
	public void testRecuperarDatosNIF() {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertNotNull(gestion.recuperarDatosNIF(empresa.getNIF()));
		assertNotNull(gestion.recuperarDatosNIF(particular.getNIF()));
		assertNotNull(gestion.recuperarDatosNIF(hombre.getNIF()));
		assertNotNull(gestion.recuperarDatosNIF(mujer.getNIF()));
		
	}
	
	@Test
	public void testRecuperarListadoClientes() {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertNotNull(gestion.recuperarListadoClientes());
	}

}