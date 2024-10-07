package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.repository.LectorRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectorGlobalSearchCommand extends AbstractCommand<CommandResult, String, LectorRepository> {
    private static final String TEMPLATE = "Global search by (.+)";
    private static final String RESPONSE_TEMPLATE = "%s"; // For direct listing of lecturers

    public LectorGlobalSearchCommand(LectorRepository repository) {
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
            return new DefaultCommandResult("Invalid search format.");
        }

        String searchTemplate = matcher.group(1).trim();
        List<String> lecturers = repository.globalSearchByValue(searchTemplate).stream()
                .map(Lector::getFullName)
                .toList();

        String resultMessage;
        if (lecturers.isEmpty()) {
            resultMessage = "No lecturers found for the search term: " + searchTemplate;
        } else {
            resultMessage = String.join(", ", lecturers);
        }

        return new DefaultCommandResult("Global search results: " + resultMessage);
    }
}
