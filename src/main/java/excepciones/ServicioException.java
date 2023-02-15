package excepciones;

public class ServicioException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServicioException() {
	}
	
	public ServicioException(String mensaje) {
		super(mensaje);
	}
}
