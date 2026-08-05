package ar.org.proyungas.exception;




public class GenericException extends RuntimeException {
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private final ErrorCode errorCode;

    protected GenericException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    private static String buildMessage(String message, ErrorCode errorCode) {
        return errorCode.getReason() + COMMA + SPACE + message;
    }
}
