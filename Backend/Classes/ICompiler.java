/**
 * Interface for compilers
 */
public interface ICompiler {

    /**
     * Runs the container with the image name given to the constructor and prints the output to the console
     * @return void
     * @exception e
     */
    public void run();

    /**
     * Builds a docker image with the image name given to the constructor
     * @return void
     * @exception e
     */
    public void buildContainer();

    /**
     * Creates the Dockerfile used for creating the docker container
     * @return void
     */
    public void createDockerfile();

}