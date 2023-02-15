package entidades;

import java.io.InputStream;

public class Usuarios {
	
	private int idusuario;
    private String dni;
    private String nombre;
    private String clave;
    private String correo;
    private String direccion;
    private InputStream fotoperfil;
    private String rol;
    private String baja;
	public Usuarios() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public InputStream getFotoperfil() {
		return fotoperfil;
	}
	public void setFotoperfil(InputStream fotoperfil) {
		this.fotoperfil = fotoperfil;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getBaja() {
		return baja;
	}
	public void setBaja(String baja) {
		this.baja = baja;
	}
	@Override
	public String toString() {
		return "Usuarios [idusuario=" + idusuario + ", dni=" + dni + ", nombre=" + nombre + ", clave=" + clave
				+ ", correo=" + correo + ", direccion=" + direccion + ", fotoperfil=" + fotoperfil + ", rol=" + rol
				+ ", baja=" + baja + "]";
	}
}
