package tdr.task.botscrewtask.command.impl;

import tdr.task.botscrewtask.repository.LectorRepository;

public class LectorGlobalSearchCommand extends AbstractCommand<String, String, LectorRepository> {
    public LectorGlobalSearchCommand(LectorRepository repository) {
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
