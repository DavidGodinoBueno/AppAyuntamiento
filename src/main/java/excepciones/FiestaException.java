package excepciones;

public class FiestaException extends Exception {

	private static final long serialVersionUID = 1L;

	public FiestaException() {
		
	}
	
	public FiestaException(String mensaje) {
		super(mensaje);
	}
}
