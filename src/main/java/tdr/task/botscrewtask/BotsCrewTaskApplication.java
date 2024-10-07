package tdr.task.botscrewtask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tdr.task.botscrewtask.command.CommandProcessor;
import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.exception.AmbiguousCommandException;
import tdr.task.botscrewtask.exception.CommandNotRecognisedException;

import java.util.Scanner;

@SpringBootApplication
public class BotsCrewTaskApplication implements CommandLineRunner {

    private final CommandProcessor commandProcessor;

    public BotsCrewTaskApplication(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (type 'exit' to quit):");

        while (true) {
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                CommandResult result = commandProcessor.process(input);
                System.out.println(result.asString());
            } catch (CommandNotRecognisedException | AmbiguousCommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
