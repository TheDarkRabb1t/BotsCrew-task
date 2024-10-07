package tdr.task.botscrewtask.command;

public interface CommandProcessor {
    <R> R process(String input);
}
