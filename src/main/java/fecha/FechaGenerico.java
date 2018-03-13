package fecha;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import interfaces.Fecha;

public class FechaGenerico {
	public static <T extends Fecha> Collection<T> getConjuntoPorFecha(Collection<T> conjuntoObjetos,Calendar fechaInicio, Calendar fechaFinal){
		Collection<T> conjuntoFinal = new LinkedList<T>();
		for(T col : conjuntoObjetos){
			if(col.getFecha().after(fechaInicio) && col.getFecha().before(fechaFinal))
				conjuntoFinal.add(col);
		}
		return conjuntoFinal;
	}
}
