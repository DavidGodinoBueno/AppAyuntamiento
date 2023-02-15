package entidades;

import java.sql.Timestamp;

public class Comentarios {

	private int idcomentario;
	private String descripcion;
	private String nombreusuario;
	private Timestamp fechacomentario;
	
	public Comentarios() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdcomentario() {
		return idcomentario;
	}
	public void setIdcomentario(int idcomentario) {
		this.idcomentario = idcomentario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreusuario() {
		return nombreusuario;
	}
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	public Timestamp getFechacomentario() {
		return fechacomentario;
	}
	public void setFechacomentario(Timestamp fechacomentario) {
		this.fechacomentario = fechacomentario;
	}
	@Override
	public String toString() {
		return "Comentarios [idcomentario=" + idcomentario + ", descripcion=" + descripcion + ", nombreusuario="
				+ nombreusuario + ", fechacomentario=" + fechacomentario + "]";
	}
	
}
