package exception;

public class ParameterCountDoesNotMatchException extends RuntimeException {

    public ParameterCountDoesNotMatchException(String message) {

        super(message);
    }
}
