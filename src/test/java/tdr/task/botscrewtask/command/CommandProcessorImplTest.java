package tdr.task.botscrewtask.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.exception.AmbiguousCommandException;
import tdr.task.botscrewtask.exception.CommandNotRecognisedException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommandProcessorImplTest {

    private CommandProcessor processor;

    @Mock
    private CommandResult commandResult;

    @Mock
    private Command<CommandResult, String> mockCommand1;

    @Mock
    private Command<CommandResult, String> mockCommand2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new CommandProcessorImpl(List.of(mockCommand1, mockCommand2));
    }

    @Test
    void testProcess_RecognizedCommand() {
        String input = "head of department Physics";

        when(mockCommand1.supports(input)).thenReturn(true);
        when(mockCommand1.execute(input)).thenReturn(commandResult);

        CommandResult result = processor.process(input);

        verify(mockCommand1).execute(input);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(commandResult);
    }

    @Test
    void testProcess_CommandNotRecognized() {
        String input = "unknown command";

        when(mockCommand1.supports(input)).thenReturn(false);
        when(mockCommand2.supports(input)).thenReturn(false);

        assertThatThrownBy(() -> processor.process(input))
                .isInstanceOf(CommandNotRecognisedException.class)
                .hasMessageContaining("Command wasn't recognised");
    }

    @Test
    void testProcess_MultipleCommandsMatch() {
        String input = "head of department Physics";

        when(mockCommand1.supports(input)).thenReturn(true);
        when(mockCommand2.supports(input)).thenReturn(true);

        assertThatThrownBy(() -> processor.process(input))
                .isInstanceOf(AmbiguousCommandException.class)
                .hasMessageContaining("Found more then 1 command");
    }
}
