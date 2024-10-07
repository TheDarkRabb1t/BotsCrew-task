package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.model.enums.Degree;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowStatisticsCommand extends AbstractCommand<CommandResult, String, DepartmentRepository> {
    private static final String TEMPLATE = "Show (.+) statistics\\.";

    public ShowStatisticsCommand(DepartmentRepository repository) {
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
        StringBuilder resultMessage = new StringBuilder();

        resultMessage.append("assistants - ")
                .append(repository.countLecturesByDepartmentName(departmentName, Degree.ASSISTANT.ordinal()))
                .append("\n")
                .append("associate professors - ")
                .append(repository.countLecturesByDepartmentName(departmentName, Degree.ASSOCIATE_PROFESSOR.ordinal()))
                .append("\n")
                .append("professors - ")
                .append(repository.countLecturesByDepartmentName(departmentName, Degree.PROFESSOR.ordinal()));

        return new DefaultCommandResult(resultMessage.toString());
    }
}
