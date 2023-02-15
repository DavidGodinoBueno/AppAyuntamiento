package entidades;

import java.io.InputStream;
import java.sql.Date;

public class Noticias {

	private int idnoticia;
	private String titulo;
	private String descripcion;
	private InputStream fotonoticia;
	private Date fechanoticia;
	private String autor;
	private String baja;
	public Noticias() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdnoticia() {
		return idnoticia;
	}
	public void setIdnoticia(int idnoticia) {
		this.idnoticia = idnoticia;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public InputStream getFotonoticia() {
		return fotonoticia;
	}
	public void setFotonoticia(InputStream fotonoticia) {
		this.fotonoticia = fotonoticia;
	}
	public Date getFechanoticia() {
		return fechanoticia;
	}
	public void setFechanoticia(Date fechanoticia) {
		this.fechanoticia = fechanoticia;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getBaja() {
		return baja;
	}
	public void setBaja(String baja) {
		this.baja = baja;
	}
	@Override
	public String toString() {
		return "Noticias [idnoticia=" + idnoticia + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", fotonoticia=" + fotonoticia + ", fechanoticia=" + fechanoticia + ", autor=" + autor + ", baja="
				+ baja + "]";
	}
	
}
