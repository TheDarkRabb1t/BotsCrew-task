package tdr.task.botscrewtask.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultCommandResult implements CommandResult {
    private final String result;

    @Override
    public String asString() {
        return result;
    }
}
