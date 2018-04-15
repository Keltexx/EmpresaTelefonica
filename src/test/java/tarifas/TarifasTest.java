package tarifas;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factura.ConTarifaFinDeSemana;
import factura.Llamada;
import factura.TarifaBasica;

public class TarifasTest {

		private Llamada llamada;
		private TarifaBasica tarifa;
		
		@Before
		public void init() {
			Calendar fecha = Calendar.getInstance();
			fecha.set(Calendar.DAY_OF_WEEK, 6);
			llamada = new Llamada(666666666,fecha,500);
			
		}
		
		@After
		public void finish() {
			llamada = null;
			tarifa=null;
		}

		@Test
		public void testCalcularImporte() {
	        tarifa = new ConTarifaFinDeSemana(tarifa, 0);
	        assertEquals(0,tarifa.calcularImporte(llamada), -1);

		}
}
