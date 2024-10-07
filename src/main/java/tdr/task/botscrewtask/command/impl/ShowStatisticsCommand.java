package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.model.enums.Degree;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShowStatisticsCommand extends AbstractCommand<CommandResult, String, DepartmentRepository> {
    private static final String TEMPLATE = "(?i)Show\\s+(.+)\\s+statistics";
    private final Pattern pattern;

    public ShowStatisticsCommand(DepartmentRepository repository) {
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
        if (!matcher.find()) {
            return new DefaultCommandResult("Invalid command format.");
        }

        String departmentName = matcher.group(1).trim();

        if (repository.getDepartmentByName(departmentName).isEmpty()) {
            return new DefaultCommandResult("Department '" + departmentName + "' not found.");
        }

        String resultMessage = Arrays.stream(Degree.values())
                .map(degree -> String.format("%s - %d", degree.toString().toLowerCase().replace("_", " "),
                        repository.countLecturesByDepartmentName(departmentName, degree.ordinal())))
                .collect(Collectors.joining("\n"));

        return new DefaultCommandResult(resultMessage);
    }
}
