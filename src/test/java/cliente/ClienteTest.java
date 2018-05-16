package cliente;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uji.www.GeneradorDatosINE;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import excepciones.ExcepcionListaClientesVacia;
import factura.*;
import interfazusuario.modelo.ImplementacionModelo;

import java.util.Calendar;
import cliente.Cliente;


public class ClienteTest {
	private ImplementacionModelo gestion;
	private GeneradorDatosINE generador;
	private Cliente empresa;
	private Cliente particular;
	private Cliente hombre;
	private Cliente mujer;

	@Before
	public void init() {
		gestion = new ImplementacionModelo();
		generador = new GeneradorDatosINE();
		empresa = new Empresa(generador.getNombre(), generador.getNIF(),
				new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"empresa@empresa.com",Calendar.getInstance(),new TarifaBasica(5));
		particular = new Particular(generador.getNombre(),generador.getApellido(), generador.getNIF(),
				new Direccion(23456, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"particular@particular.com",Calendar.getInstance(),new TarifaBasica(10));
		hombre = new Particular(generador.getNombreHombre(),generador.getApellido(), generador.getNIF(),
				new Direccion(34567, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"hombre@hombre.com",Calendar.getInstance(),new TarifaBasica(7));
		mujer =new Particular(generador.getNombreMujer(),generador.getApellido(), generador.getNIF(),
				new Direccion(45678, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"mujer@mujer.com",Calendar.getInstance(),new TarifaBasica(8));
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
	public void testDarDeAltaCliente() throws ExcepcionClienteYaRegistrado {
		assertTrue(gestion.darDeAltaCliente(empresa));
		assertTrue(gestion.darDeAltaCliente(particular));
		assertTrue(gestion.darDeAltaCliente(hombre));
		assertTrue(gestion.darDeAltaCliente(mujer));
	}
	
	@Test
	public void testBorrarCliente() throws ExcepcionClienteYaRegistrado, ExcepcionClienteNoEncontrado {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertTrue(gestion.borrarCliente(empresa.getNIF()));
		assertTrue(gestion.borrarCliente(particular.getNIF()));
		assertTrue(gestion.borrarCliente(hombre.getNIF()));
		assertTrue(gestion.borrarCliente(mujer.getNIF()));
	}
	
	@Test
	public void testCambiarTarifa() throws ExcepcionClienteYaRegistrado, ExcepcionClienteNoEncontrado {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertTrue(gestion.cambiarTarifa(empresa.getNIF(), new TarifaBasica(1)));
		assertTrue(gestion.cambiarTarifa(particular.getNIF(), new TarifaBasica(1)));
		assertTrue(gestion.cambiarTarifa(hombre.getNIF(), new TarifaBasica(1)));
		assertTrue(gestion.cambiarTarifa(mujer.getNIF(), new TarifaBasica(1)));
	}
	
	@Test 
	public void testRecuperarDatosNIF() throws ExcepcionClienteYaRegistrado, ExcepcionClienteNoEncontrado {
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
	public void testRecuperarListadoClientes() throws ExcepcionClienteYaRegistrado, ExcepcionListaClientesVacia {
		gestion.darDeAltaCliente(empresa);
		gestion.darDeAltaCliente(particular);
		gestion.darDeAltaCliente(hombre);
		gestion.darDeAltaCliente(mujer);
		
		assertNotNull(gestion.recuperarListadoClientes());
	}

}