package tarifas;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factura.ConTarifaDomingo;
import factura.ConTarifaTardes;
import factura.Llamada;
import factura.TarifaBasica;

public class TarifasTest {

		private Llamada llamada;
		private Llamada llamada2;
		private Llamada llamada3;
		private TarifaBasica tarifa;
		
		@Before
		public void init() {
			Calendar fecha = Calendar.getInstance();
			fecha.set(Calendar.DAY_OF_WEEK, 7);
			llamada = new Llamada(666666666,fecha,500);
			Calendar fecha2 = Calendar.getInstance();
			fecha2.set(Calendar.HOUR_OF_DAY,17);
			llamada2 = new Llamada(666777888,fecha2,600);
			Calendar fecha3 = Calendar.getInstance();
			fecha3.set(Calendar.DAY_OF_WEEK, 7);
			fecha3.set(Calendar.HOUR_OF_DAY, 18);
			llamada3 = new Llamada(655666666, fecha3, 300);
			tarifa = new TarifaBasica(10);
		}
		
		@After
		public void finish() {
			llamada = null;
			llamada2 = null;
			llamada3 = null;
			tarifa=null;
		}

		@Test
		public void testCalcularImporte() {
	        tarifa = new ConTarifaDomingo(tarifa, 0);
	        assertEquals(0,tarifa.calcularImporte(llamada), -1);
	        
	        tarifa = new ConTarifaTardes(tarifa,5);
	        assertEquals(3000,tarifa.calcularImporte(llamada2),-1);
	        
	        assertEquals(0, tarifa.calcularImporte(llamada3), -1);

		}
}
