package compile_io.docker;

/**
 * Class used to execute Terminal commands
 */
public interface ICommandExecuter {

    public String executeCommand(String[] command);

    public String executeCommandWithTimeout(String[] command, long timeLimit);

}