package compile_io.docker;

/**
 * Interface for classes that will run unit tests
 */
public interface IDockerRunner {

    public String run (long timeLimit);

}