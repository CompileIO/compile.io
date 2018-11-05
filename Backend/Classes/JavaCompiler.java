import docker;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * This class builds a docker image, then runs that docker image
 */
public class JavaCompiler implements ICompiler {

    private byte[] executableFileBytes;

    /**
     * Default Constructor, no bytes given, for testing purposes at the moment
     */
    public JavaCompiler() {
        this.executableFileBytes = null;
    }

    /**
     * Constructor that builds a docker image with the given name
     * @param byte[] executableFileBytes The bytecode of the student-submitted executable
     */
    public JavaCompiler(byte[] executableFileBytes) {
        this.executableFileBytes = executableFileBytes;
        this.createRunnable();
        this.createDockerfile();
        this.buildContainer();
    }

    /**
     * Runs the container with the image name given to the constructor and prints the output to the console
     * @return void
     * @exception e
     */
    public void run(){
        try {
            // String[] command = {"docker", "run", "java-image"};
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
            String[] command = {"docker", "build", "-t", "java-image", "."};
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
     * @return void
     */
    public void createDockerfile() {
        String dockerfileData = "FROM openjdk\nWORKDIR /\nADD runnable.jar runnable.jar\nEXPOSE 8000\nCMD java -jar runnable.jar\n";
        FileOutputStream dockerfileFos = new FileOutputStream("Dockerfile");
        dockerfileFos.write(dockerfileData.getBytes());
        dockerfileFos.flush();
        dockerfileFos.close();
    }

    /**
     * Creates the runnable used for running the submitted student code
     * @return void
     */
    public void createRunnable() {
        FileOutputStream executableFos = new FileOutputStream("runnable.jar");
        executableFos.write(this.executableFileBytes);
        executableFos.flush();
        executableFos.close();
    }

}