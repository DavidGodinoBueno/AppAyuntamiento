package entidades;

public class Visitas {

	private int idvisita;
	private long contador;
	private String usuario;
	public Visitas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdvisita() {
		return idvisita;
	}
	public void setIdvisita(int idvisita) {
		this.idvisita = idvisita;
	}
	public long getContador() {
		return contador;
	}
	public void setContador(long contador) {
		this.contador = contador;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Visitas [idvisita=" + idvisita + ", contador=" + contador + ", usuario=" + usuario + "]";
	}
	
}
