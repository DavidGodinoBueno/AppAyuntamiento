package entidades;

public class Interfaz {

	private int idinterfaz;
	private String colorfondo;
	private String colornavegacion;
	private String colorforms;
	private String colortablas;
	public Interfaz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdinterfaz() {
		return idinterfaz;
	}
	public void setIdinterfaz(int idinterfaz) {
		this.idinterfaz = idinterfaz;
	}
	public String getColorfondo() {
		return colorfondo;
	}
	public void setColorfondo(String colorfondo) {
		this.colorfondo = colorfondo;
	}
	public String getColornavegacion() {
		return colornavegacion;
	}
	public void setColornavegacion(String colornavegacion) {
		this.colornavegacion = colornavegacion;
	}
	public String getColorforms() {
		return colorforms;
	}
	public void setColorforms(String colorforms) {
		this.colorforms = colorforms;
	}
	public String getColortablas() {
		return colortablas;
	}
	public void setColortablas(String colortablas) {
		this.colortablas = colortablas;
	}
	@Override
	public String toString() {
		return "Interfaz [idinterfaz=" + idinterfaz + ", colorfondo=" + colorfondo + ", colornavegacion="
				+ colornavegacion + ", colorforms=" + colorforms + ", colortablas=" + colortablas + "]";
	}
	
}
