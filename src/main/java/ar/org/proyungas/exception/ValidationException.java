package ar.org.proyungas.exception;

public class ValidationException extends GenericException{
	public ValidationException(ErrorCode errorCode) {
		super(errorCode);
	}
}
