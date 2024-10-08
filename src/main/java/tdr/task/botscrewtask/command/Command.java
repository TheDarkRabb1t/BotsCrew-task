package tdr.task.botscrewtask.command;

public interface Command<R extends CommandResult, T> {
    String getTemplate();

    boolean supports(T value);

    R execute(T value);
}
