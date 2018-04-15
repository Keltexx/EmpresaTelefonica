package factura;

public class TarifaBasica extends Tarifa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7493440740613869440L;

	public TarifaBasica(){
		super();
	}
	
	public TarifaBasica(double importe) {
		super(importe);
	}
	
	public TarifaBasica(TarifaBasica tarifaBasica) {
		super(tarifaBasica);
	}

	@Override
	public double calcularImporte(Llamada llamada) {
		return llamada.getDuracion()*getImporte();
	}
	
}
