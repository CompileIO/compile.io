package compile_io.docker;

/**
 * Interface for classes that will run unit tests
 */
public interface IDockerRunner {

    /**
     * Runs the "compile-io-image" Docker image.
     * Removes the container created from the image after execution.
     * @param long timeLimit A time limit for the process. Process terminates if runtime exceeds given timeLimit.
     * @return String 
     * @throws Exception e If the image has not been built
     */
    public String run (long timeLimit);

}