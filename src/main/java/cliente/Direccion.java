package cliente;

public class Direccion {
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
	
}
