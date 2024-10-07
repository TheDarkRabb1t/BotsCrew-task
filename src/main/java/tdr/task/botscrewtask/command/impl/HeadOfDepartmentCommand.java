package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeadOfDepartmentCommand extends AbstractCommand<CommandResult, String, DepartmentRepository> {

    private static final String TEMPLATE = "(?i)Who\\s+is\\s+head\\s+of\\s+department\\s+(.+)\\s*";
    private static final String RESPONSE_TEMPLATE = "Head of %s department is %s";

    public HeadOfDepartmentCommand(DepartmentRepository repository) {
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
        Matcher matcher = Pattern.compile(TEMPLATE).matcher(value.trim());
        if (matcher.find()) {
            String departmentName = matcher.group(1).trim();

            String headOfDepartment = repository.getDepartmentByName(departmentName)
                    .map(department -> department.getHead().getFullName())
                    .orElse(null);

            if (headOfDepartment != null) {
                return new DefaultCommandResult(String.format(RESPONSE_TEMPLATE, departmentName, headOfDepartment));
            } else {
                return new DefaultCommandResult("Department " + departmentName + " not found.");
            }
        } else {
            return new DefaultCommandResult("Invalid command format.");
        }
    }

}
