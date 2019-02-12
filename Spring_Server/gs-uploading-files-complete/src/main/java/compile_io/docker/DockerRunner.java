package compile_io.docker;

public class DockerRunner implements IDockerRunner {

    private ICommandExecuter executer;
    private AbstractBuilder builder;

    public DockerRunner(AbstractBuilder builder, ICommandExecuter executer) {
        this.executer = executer;
        this.builder = builder;
    }

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
        System.out.println("Container has finished execution.");

        return result;
    }
}