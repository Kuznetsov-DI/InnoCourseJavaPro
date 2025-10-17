package spring.web.lesson.exception;

public class NoHaveProductsException extends RuntimeException {


    public NoHaveProductsException(String message) {
        super(message);
    }
}
