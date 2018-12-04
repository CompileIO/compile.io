package compile_io.docker;
/**
 * Interface for compilers
 */
public interface ICompiler {

    /**
     * Runs the container with the image name given to the constructor and prints the output to the console
     * @return void
     * @throws Exception e
     */
    public void run();

    /**
     * Builds a docker image with the image name given to the constructor
     * @return void
     * @throws IOException e 
     * @throws InterruptedException e
     */
    public void buildContainer();

    /**
     * Creates the Dockerfile used for creating the docker container
     * @return void
     * @throws FileNotFoundException e If the the given file does not exist or cannot be found
     * @throws IOException e If the java IO encounters an error
     */
    public void createDockerfile();

    /**
     * Tears down the created image
     * @return void
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public void teardownDockerImage();

}