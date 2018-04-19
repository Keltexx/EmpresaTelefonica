package tarifas;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cliente.ClienteFactory;
import cliente.Direccion;
import cliente.Empresa;
import cliente.Particular;
import factura.ConTarifaDomingo;
import factura.ConTarifaTardes;
import factura.TarifaBasica;
import factura.TarifaFactory;


public class FactoryTest {

	private Empresa empresa;
	private Particular particular;
	private TarifaBasica tarifa = new TarifaBasica(5);
	private String nombre = "Pepe";
	private String nif = "0001";
	private int cod = 1;
	private String prov = "Castellon";
	private String pob = "cs";
	private Direccion dir = new Direccion(cod, prov, pob);
	private String correo = "jaja";
	private Calendar fecha = Calendar.getInstance();
	private String apellidos = "Tenas";

	
	@Before
	public void init() {
		empresa = new Empresa(nombre, nif, dir, correo, fecha, tarifa);
		particular = new Particular(nombre, apellidos, nif, dir, correo, fecha, tarifa);
		
	}
	
	@After
	public void finish() {
		empresa = null;
		particular = null; 
		tarifa=null;
	}

	@Test
	public void testCliente() {
        Empresa empresa2 = (Empresa) ClienteFactory.crearCliente(0, nombre, apellidos, nif, dir, correo, fecha, tarifa);
        assertEquals(empresa.getNombre(),empresa2.getNombre());
        assertEquals(empresa.getCorreo(),empresa2.getCorreo());
        assertEquals(empresa.getNIF(),empresa2.getNIF());
        
        Particular particular2 = (Particular) ClienteFactory.crearCliente(1, nombre, apellidos, nif, dir, correo, fecha, tarifa);
        assertEquals(particular.getNombre(),particular2.getNombre());
        assertEquals(particular.getApellidos(),particular2.getApellidos());
        assertEquals(particular.getCorreo(),particular2.getCorreo());

	}

	
	@Test
	public void testTarifa() {
		TarifaBasica tarifaB = null;
        tarifaB= (TarifaBasica) TarifaFactory.crearTarifa(0, tarifaB, 5);
        assertEquals(tarifa.getImporte(),tarifaB.getImporte(), -1);
        
        ConTarifaDomingo tarifaD = (ConTarifaDomingo) TarifaFactory.crearTarifa(1, tarifaB, 2);
        tarifa = new ConTarifaDomingo(tarifa, 2);
        assertEquals(tarifa.getImporte(),tarifaD.getImporte(), -1);
        
        ConTarifaTardes tarifaT = (ConTarifaTardes) TarifaFactory.crearTarifa(2, tarifaB, 3);
        tarifa = new ConTarifaTardes(tarifa, 3);
        assertEquals(tarifa.getImporte(),tarifaT.getImporte(), -1);
        
	}
	
	
}
