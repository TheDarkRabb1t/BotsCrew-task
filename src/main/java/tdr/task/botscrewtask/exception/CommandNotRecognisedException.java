package tdr.task.botscrewtask.exception;

public class CommandNotRecognisedException extends RuntimeException{
    public CommandNotRecognisedException(String message) {
        super(message);
    }
}
