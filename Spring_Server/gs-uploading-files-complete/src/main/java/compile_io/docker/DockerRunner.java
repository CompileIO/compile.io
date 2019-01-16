package compile_io.docker;

public class DockerRunner implements IDockerRunner {

    private ICommandExecuter executer;
    private AbstractBuilder builder;

    /**
     * Constructor for the DockerRunner class
     * @param AbstractBuilder builder Builder object which is used for teardown in this class
     */
    public DockerRunner(AbstractBuilder builder) {
        this.executer = new CommandExecuter();
        this.builder = builder;
    }

    /**
     * Runs the "compile-io-image" Docker image.
     * Removes the container created from the image after execution.
     * @param long timeLimit A time limit for the process. Process terminates if runtime exceeds given timeLimit.
     * @return String 
     * @throws Exception e If the image has not been built
     */
    public String run(long timeLimit) {
        System.out.println("Attempting to run docker container...");
        System.out.println();
        String[] command = {"docker", "run", "--rm", "compile-io-image"};
        String result = this.executer.executeCommandWithTimeout(command, timeLimit);
        this.builder.teardownDockerImage();
        this.builder.teardownDockerfile();
        if (result == null) {   
            System.exit(0);
        }
        System.out.println();
        System.out.println("Container has finished execution.");

        return result;
    }
}