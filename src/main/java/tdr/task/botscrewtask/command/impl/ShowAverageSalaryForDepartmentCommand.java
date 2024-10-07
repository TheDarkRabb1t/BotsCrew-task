package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.repository.DepartmentRepository;

public class ShowAverageSalaryForDepartmentCommand extends AbstractCommand<String, String, DepartmentRepository>{
    public ShowAverageSalaryForDepartmentCommand(DepartmentRepository repository) {
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
