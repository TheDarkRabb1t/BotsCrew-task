package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.command.DefaultCommandResult;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.repository.LectorRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectorGlobalSearchCommand extends AbstractCommand<CommandResult, String, LectorRepository> {
    private static final String TEMPLATE = "(?i)Global\\s+search\\s+by\\s+(.+)";
    private final Pattern pattern;

    public LectorGlobalSearchCommand(LectorRepository repository) {
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
            String searchTemplate = matcher.group(1).trim();
            List<String> lecturers = repository.globalSearchByValue(searchTemplate).stream()
                    .map(Lector::getFullName)
                    .toList();

            String resultMessage = lecturers.isEmpty()
                    ? "No lecturers found for the search term: " + searchTemplate
                    : String.join(", ", lecturers);

            return new DefaultCommandResult("Global search results: " + resultMessage);
        } else {
            return new DefaultCommandResult("Invalid search format.");
        }
    }
}