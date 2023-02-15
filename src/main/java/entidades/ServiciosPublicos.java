package entidades;

import java.io.InputStream;

public class ServiciosPublicos {
	
      private int idservicio;
      private String nombre;
      private String informacion;
      private InputStream fotoservicio;
      private int idcategoria;
      private String baja;
      private long likes;
      private long dislikes;
      private String descripcioncategoria;
	public ServiciosPublicos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdservicio() {
		return idservicio;
	}
	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
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
	public InputStream getFotoservicio() {
		return fotoservicio;
	}
	public void setFotoservicio(InputStream fotoservicio) {
		this.fotoservicio = fotoservicio;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getBaja() {
		return baja;
	}
	public void setBaja(String baja) {
		this.baja = baja;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public long getDislikes() {
		return dislikes;
	}
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	public String getDescripcioncategoria() {
		return descripcioncategoria;
	}
	public void setDescripcioncategoria(String descripcioncategoria) {
		this.descripcioncategoria = descripcioncategoria;
	}
	@Override
	public String toString() {
		return "ServiciosPublicos [idservicio=" + idservicio + ", nombre=" + nombre + ", informacion=" + informacion
				+ ", fotoservicio=" + fotoservicio + ", idcategoria=" + idcategoria + ", baja=" + baja + ", likes="
				+ likes + ", dislikes=" + dislikes + ", descripcioncategoria=" + descripcioncategoria + "]";
	}
	  
      
}
