package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowAverageSalaryForDepartmentCommand extends AbstractCommand<CommandResult, String, DepartmentRepository> {
    private static final String TEMPLATE = "(?i)Show\\s+the\\s+average\\s+salary\\s+for\\s+the\\s+department\\s+(.+)";
    private final Pattern pattern;

    public ShowAverageSalaryForDepartmentCommand(DepartmentRepository repository) {
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
        Matcher matcher = Pattern.compile(TEMPLATE).matcher(value.trim());
        if (matcher.find()) {
            String departmentName = matcher.group(1).trim();
            Double averageSalary = repository.getAverageSalary(departmentName);
            if (averageSalary == null) {
                return new DefaultCommandResult("Department " + departmentName + " not found.");
            }

            return new DefaultCommandResult(String.format("The average salary of %s is %.2f", departmentName, averageSalary));
        } else {
            return new DefaultCommandResult("Invalid command format.");
        }
    }
}
