package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowEmployeeCountForDepartmentCommand extends AbstractCommand<CommandResult, String, DepartmentRepository> {
    private static final String TEMPLATE = "Show count of employee for (.+)";

    public ShowEmployeeCountForDepartmentCommand(DepartmentRepository repository) {
        super(repository);
    }

    @Override
    public String getTemplate() {
        return TEMPLATE;
    }

    @Override
    public boolean supports(String value) {
        return Pattern.matches(TEMPLATE, value);
    }

    @Override
    public CommandResult execute(String value) {
        Matcher matcher = Pattern.compile(TEMPLATE).matcher(value);
        if (!matcher.find()) {
            return new DefaultCommandResult("Invalid command format.");
        }

        String departmentName = matcher.group(1).trim();
        int employeeCount = repository.countEmployeesByDepartmentName(departmentName);

        return new DefaultCommandResult("Count of employees for " + departmentName + " is " + employeeCount);
    }
}
