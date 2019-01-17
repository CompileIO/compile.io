package compile_io.docker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class DockerRunnerTest {

    @Test
    public void testRun() {
        AbstractBuilder builder = mock(PythonBuilder.class);
        ICommandExecuter executer = mock(CommandExecuter.class);
        IDockerRunner runner = new DockerRunner(builder, executer);
        String[] command = {"docker", "run", "--rm", "compile-io-image"};
        when(executer.executeCommandWithTimeout(command, 60)).thenReturn("[success]");
        assertEquals("[success]", runner.run(60));
        verify(builder, times(1)).teardownDockerImage();
        verify(builder, times(1)).teardownDockerfile();
    }

}