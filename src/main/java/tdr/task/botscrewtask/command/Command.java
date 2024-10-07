package tdr.task.botscrewtask.command;

public interface Command<R, T> {
    String getTemplate();

    boolean supports();

    R execute(T value);
}
