package compile_io.docker;

public class UnsupportedBuilderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedBuilderException() {}

	public UnsupportedBuilderException(String message) {
		super(message);
	}
}