package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.repository.DepartmentRepository;

public class ShowStatisticsCommand extends AbstractCommand<CommandResult, String, DepartmentRepository>{
    public ShowStatisticsCommand(DepartmentRepository repository) {
        super(repository);
    }

    @Override
    public String getTemplate() {
        return null;
    }

    @Override
    public boolean supports(String value) {
        return false;
    }


    @Override
    public CommandResult execute(String value) {
        return null;
    }
}
