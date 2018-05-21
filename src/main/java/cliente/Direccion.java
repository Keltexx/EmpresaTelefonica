package cliente;

import java.io.Serializable;

public class Direccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8041694659183858247L;
	private int codPostal;
	private String provincia;
	private String poblacion;

	public Direccion(int cod, String prov, String pob) {
		this.setCodPostal(cod);
		this.setProvincia(prov);
		this.setPoblacion(pob);
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	public String toString() {
		return " Direccion [ \n" +
				"\t codigo postal = " + codPostal + "\n"+
				"\t provincia = " + provincia + "\n"+
				"\t poblacion = " + poblacion + "]";
	}
	
}
