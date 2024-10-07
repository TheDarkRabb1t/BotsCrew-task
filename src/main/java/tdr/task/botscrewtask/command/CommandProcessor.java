package tdr.task.botscrewtask.command;

import tdr.task.botscrewtask.exception.AmbiguousCommandException;
import tdr.task.botscrewtask.exception.CommandNotRecognisedException;

public interface CommandProcessor {
    CommandResult process(String input) throws CommandNotRecognisedException, AmbiguousCommandException;
}
