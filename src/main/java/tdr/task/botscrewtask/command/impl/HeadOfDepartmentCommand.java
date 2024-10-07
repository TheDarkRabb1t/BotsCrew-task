package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.repository.DepartmentRepository;

public class HeadOfDepartmentCommand extends AbstractCommand<String, String, DepartmentRepository> {

    public HeadOfDepartmentCommand(DepartmentRepository repository) {
        super(repository);
    }

    @Override
    public String getTemplate() {
        return null;
    }

    @Override
    public boolean supports() {
        return false;
    }

    @Override
    public String execute(String value) {
        return null;
    }
}
