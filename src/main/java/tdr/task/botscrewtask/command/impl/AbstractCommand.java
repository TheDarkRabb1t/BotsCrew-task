package tdr.task.botscrewtask.command.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import tdr.task.botscrewtask.command.Command;
import tdr.task.botscrewtask.command.CommandResult;

public abstract class AbstractCommand<R extends CommandResult, T, Repo extends JpaRepository<?, ?>> implements Command<R, T> {
    protected final Repo repository;

    public AbstractCommand(Repo repository) {
        this.repository = repository;
    }
}
