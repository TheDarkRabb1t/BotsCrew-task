package tdr.task.botscrewtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tdr.task.botscrewtask.command.CommandProcessor;
import tdr.task.botscrewtask.command.CommandProcessorImpl;
import tdr.task.botscrewtask.command.impl.*;
import tdr.task.botscrewtask.repository.DepartmentRepository;
import tdr.task.botscrewtask.repository.LectorRepository;

import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public CommandProcessor commandProcessor(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
        return new CommandProcessorImpl(List.of(
                new HeadOfDepartmentCommand(departmentRepository),
                new LectorGlobalSearchCommand(lectorRepository),
                new ShowAverageSalaryForDepartmentCommand(departmentRepository),
                new ShowEmployeeCountForDepartmentCommand(departmentRepository),
                new ShowStatisticsCommand(departmentRepository)
        ));
    }
}
