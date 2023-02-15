package entidades;

import java.io.InputStream;
import java.sql.Date;

public class Fiestas {

	private int idfiesta;
	private String nombre;
	private String informacion;
	private InputStream fotofiesta;
	private Date fechainicio;
	private Date fechafin;
	private int rangodias;
	public Fiestas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdfiesta() {
		return idfiesta;
	}
	public void setIdfiesta(int idfiesta) {
		this.idfiesta = idfiesta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	public InputStream getFotofiesta() {
		return fotofiesta;
	}
	public void setFotofiesta(InputStream fotofiesta) {
		this.fotofiesta = fotofiesta;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public int getRangodias() {
		return rangodias;
	}
	public void setRangodias(int rangodias) {
		this.rangodias = rangodias;
	}
	@Override
	public String toString() {
		return "Fiestas [idfiesta=" + idfiesta + ", nombre=" + nombre + ", informacion=" + informacion + ", fotofiesta="
				+ fotofiesta + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", rangodias=" + rangodias
				+ "]";
	}
	
	
}
