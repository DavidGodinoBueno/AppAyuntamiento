package entidades;

import java.io.InputStream;

public class CategoriasServicios {

	private int idcategoria;
	private String descripcion;
	private InputStream fotocategoria;
	public CategoriasServicios() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public InputStream getFotocategoria() {
		return fotocategoria;
	}
	public void setFotocategoria(InputStream fotocategoria) {
		this.fotocategoria = fotocategoria;
	}
	
}
