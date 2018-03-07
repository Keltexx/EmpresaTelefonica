package cliente;

import es.uji.www.GeneradorDatosINE;
import factura.*;
import gestion.Gestion;
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import cliente.Cliente;

public class ClienteTest {
	private static Gestion gestion;
	private static GeneradorDatosINE generador;
	private static Cliente empresa;
	private static Cliente particular;
	private static Cliente hombre;
	private static Cliente mujer;

	@Before
	public static void init() {
		generador = new GeneradorDatosINE();
		empresa = new Empresa(generador.getNombre(), generador.getNIF(),
				new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"empresa@empresa.com",Calendar.getInstance(),new Tarifa(5));
		particular = new Particular();
		hombre = new Particular();
		mujer = new Particular();
	}

	@Test
	public void testConstructor() {

	}

}
