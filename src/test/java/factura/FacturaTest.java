package factura;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uji.www.GeneradorDatosINE;
import factura.*;
import gestion.Gestion;


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

	@Before
	public void init() {
		gestion=new Gestion();
		generador = new GeneradorDatosINE();
		cliente= new Cliente(generador.getNombre(), generador.getNIF(),
				new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()),
				"empresa@empresa.com",Calendar.getInstance(),new Tarifa(5));
		llamada1= new Llamada(666777888,Calendar.getInstance(),Calendar.getInstance(),2);
		llamada2= new Llamada(666777888,Calendar.getInstance(),Calendar.getInstance(),6);
		llamada1= new Llamada(777888999,Calendar.getInstance(),Calendar.getInstance(),3);
		gestion.darDeAltaCliente(cliente);
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

}
