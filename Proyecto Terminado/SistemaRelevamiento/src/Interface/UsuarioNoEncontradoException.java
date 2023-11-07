package Interface;

public class UsuarioNoEncontradoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8716235420081893373L;

	public UsuarioNoEncontradoException() {
        super("Usuario no encontrado.");
    }
}

