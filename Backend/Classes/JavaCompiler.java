import docker;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * This class builds a docker image, then runs that docker image
 */
public class JavaCompiler implements ICompiler {

    private String imageName;

    /**
     * Default Constructor, no image name given
     */
    public JavaCompiler() {
        this.imageName = null;
    }

    /**
     * Constructor that builds a docker image with the given name
     * @param String imageName The desired name of the docker image
     */
    public JavaCompiler(String imageName) {
        this.imageName = imageName;
        this.buildContainer();
    }

    /**
     * Runs the container with the image name given to the constructor and prints the output to the console
     * @return void
     * @exception e
     */
    public void compile(){
        // now need to figure out how to build docker image
        try {
            // String[] command = {"docker", "run", this.imageName};
            String[] command = {"docker", "run", "hello-world"};
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();
    
            InputStream is = proc.getInputStream();
    
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(is));

            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
    
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds a docker image with the image name given to the constructor
     * @return void
     * @exception e
     */
    public void buildContainer() {
        try {
            String[] command = {"docker", "build", "-t", this.imageName, "."};
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates the Dockerfile used for creating the docker container
     * @param 
     * @return void
     */
    public void createDockerfile(String jarName) {
        // FROM openjdk
        // ADD Main.class Main.class
        // ENTRYPOINT ["java", "Main"]
    }
}