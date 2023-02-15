package excepciones;

public class CategoriaException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoriaException() {
		
	}
	
    public CategoriaException(String mensaje) {
		super(mensaje);
	}
}
