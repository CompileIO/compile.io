package compile_io.docker;

/**
 * Class used to execute Terminal commands
 */
public interface ICommandExecuter {

    /**
     * Executes the given command line argument. Output is displayed on the console.
     * For details on the format of the parameter, see Java Docs on the ProcessBuilder object.
     * @param String[] command An array of strings representing a command line instruction
     * @return String 
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public String executeCommand(String[] command);

    /**
     * Executes the given command line argument. Output is displayed on the console.
     * Times out the process after surpassing the given time.
     * For details on the format of the parameter, see Java Docs on the ProcessBuilder object.
     * @param String[] command An array of strings representing a command line instruction
     * @param long timeLimit 
     * @return String  
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public String executeCommandWithTimeout(String[] command, long timeLimit);

}