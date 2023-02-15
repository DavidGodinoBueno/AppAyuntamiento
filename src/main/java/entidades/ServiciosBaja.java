package entidades;

import java.io.InputStream;

public class ServiciosBaja {

	private int idservicio;
    private String nombre;
    private String informacion;
    private InputStream fotoservicio;
    private int idcategoria;
	public ServiciosBaja() {
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
    
}
