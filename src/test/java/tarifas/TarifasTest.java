package tarifas;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factura.ConTarifaFinDeSemana;
import factura.ConTarifaMañanas;
import factura.Llamada;
import factura.TarifaBasica;

public class TarifasTest {

		private Llamada llamada;
		private Llamada llamada2;
		private TarifaBasica tarifa;
		
		@Before
		public void init() {
			Calendar fecha = Calendar.getInstance();
			fecha.set(Calendar.DAY_OF_WEEK, 6);
			llamada = new Llamada(666666666,fecha,500);
			Calendar fecha2 = Calendar.getInstance();
			fecha.set(Calendar.HOUR,11);
			llamada2 = new Llamada(666777888,fecha2,600);
			tarifa = new TarifaBasica(10);
		}
		
		@After
		public void finish() {
			llamada = null;
			llamada2 = null;
			tarifa=null;
		}

		@Test
		public void testCalcularImporte() {
	        tarifa = new ConTarifaFinDeSemana(tarifa, 0);
	        assertEquals(0,tarifa.calcularImporte(llamada), -1);
	        
	        tarifa = new ConTarifaMañanas(tarifa,5);
	        assertEquals(3000,tarifa.calcularImporte(llamada2),-1);

		}
}
