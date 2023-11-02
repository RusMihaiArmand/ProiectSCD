package ro.mihai.exceptions;

/**
 * @author Radu Miron
 * @version 1
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
