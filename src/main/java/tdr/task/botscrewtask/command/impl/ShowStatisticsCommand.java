package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.repository.DepartmentRepository;

public class ShowStatisticsCommand extends AbstractCommand<String, String, DepartmentRepository>{
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
    public String execute(String value) {
        return null;
    }
}
