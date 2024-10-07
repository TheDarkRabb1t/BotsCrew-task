package tdr.task.botscrewtask.exception;

public class AmbiguousCommandException extends RuntimeException {

    public AmbiguousCommandException(String message) {
        super(message);
    }
}
