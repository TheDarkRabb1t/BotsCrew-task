package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowEmployeeCountForDepartmentCommand extends AbstractCommand<CommandResult, String, DepartmentRepository> {
    private static final String TEMPLATE = "(?i)Show\\s+count\\s+of\\s+employee\\s+for\\s+(.+)";
    private final Pattern pattern;

    public ShowEmployeeCountForDepartmentCommand(DepartmentRepository repository) {
        super(repository);
        this.pattern = Pattern.compile(TEMPLATE, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public String getTemplate() {
        return TEMPLATE;
    }

    @Override
    public boolean supports(String value) {
        Matcher matcher = pattern.matcher(value.trim());
        return matcher.matches();
    }

    @Override
    public CommandResult execute(String value) {
        Matcher matcher = pattern.matcher(value.trim());
        if (matcher.find()) {
            String departmentName = matcher.group(1).trim();
            int employeeCount = repository.countEmployeesByDepartmentName(departmentName);

            return new DefaultCommandResult(String.valueOf(employeeCount));
        } else {
            return new DefaultCommandResult("Invalid command format.");
        }
    }
}
