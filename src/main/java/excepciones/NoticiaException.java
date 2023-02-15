package excepciones;

public class NoticiaException extends Exception {

	private static final long serialVersionUID = 1L;
    
	public NoticiaException() {
		
	}
	
	public NoticiaException(String mensaje) {
		super(mensaje);
	}
}
