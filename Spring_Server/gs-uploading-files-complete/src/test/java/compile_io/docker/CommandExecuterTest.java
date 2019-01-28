package compile_io.docker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import java.io.IOException;

import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class CommandExecuterTest {

    @Test
    public void testExecuteCommand() {
        ICommandExecuter testExecuter = mock(CommandExecuter.class);
        String[] command = {"echo", "ExecuteCommandOutput"};
        when(testExecuter.executeCommand(command)).thenReturn("success");
        assertEquals("success", testExecuter.executeCommand(command));
    }

    @Test
    public void testExecuteCommandOutput() {
        ICommandExecuter testExecuter = new CommandExecuter();
        String[] command = {"echo", "foo"};
        assertEquals("[foo]", testExecuter.executeCommand(command));
    }

    @Test
    public void testExecuteCommandTimeout() {
        ICommandExecuter testExecuter = mock(CommandExecuter.class);
        String[] command = {"echo", "ExecuteCommandOutput"};
        when(testExecuter.executeCommandWithTimeout(command, 60)).thenReturn(null);
        assertEquals(null, testExecuter.executeCommand(command));
    }

    @Test
    public void testExecuteCommandIOException() {
        ICommandExecuter testExecuter = mock(CommandExecuter.class);
        String[] command = {"echo", "ExecuteCommandOutput"};
        when(testExecuter.executeCommand(command)).thenThrow(IOException.class);
    }

    @Test
    public void testExecuteCommandInterruptedException() {
        ICommandExecuter testExecuter = mock(CommandExecuter.class);
        String[] command = {"echo", "ExecuteCommandOutput"};
        when(testExecuter.executeCommand(command)).thenThrow(InterruptedException.class);
    }

}