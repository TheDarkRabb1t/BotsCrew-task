package tdr.task.botscrewtask.command;

import lombok.AllArgsConstructor;
import tdr.task.botscrewtask.exception.AmbiguousCommandException;
import tdr.task.botscrewtask.exception.CommandNotRecognisedException;

import java.util.List;

@AllArgsConstructor
public class CommandProcessorImpl implements CommandProcessor {
    private final List<Command<? extends CommandResult, String>> commands;

    @Override
    public CommandResult process(String input) throws CommandNotRecognisedException, AmbiguousCommandException {
        List<Command<? extends CommandResult, String>> supportedCommands = commands.stream()
                .filter(command -> command.supports(input))
                .toList();
        if (supportedCommands.size() > 1) {
            throw new AmbiguousCommandException("Found more then 1 command");
        }
        if (supportedCommands.isEmpty()) {
            throw new CommandNotRecognisedException("Command wasn't recognised");
        }
        return supportedCommands.getFirst().execute(input);
    }
}
