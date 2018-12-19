package compile_io.docker;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Abstract class for the compilers that builds and runs docker images.
 */
public abstract class AbstractCompiler {

    private String fileName;
    private String fileDirectory;

    /**
     * Constructor that builds a docker image with the given name
     * @param File file File uploaded by the student
     */
    public AbstractCompiler(File file) {
        this.fileName  = file.getName();
        this.fileDirectory = file.getParent(); 
        if (this.fileDirectory == null) {
            this.fileDirectory = "/";
        }
        System.out.println("Given file: " + this.fileName + ". Target directory: " + this.fileDirectory);
    }

    /**
     * Creates and runs the container with the image name given to the constructor and prints the output to the console.
     * Removes the container created from the image after execution.
     * @param long timeLimit A time limit for the process. Process terminates if runtime exceeds given timeLimit.
     * @return void
     * @throws Exception e
     */
    public void run(long timeLimit){
        System.out.println("Attempting to run docker container...");
        System.out.println();
        String[] command = {"docker", "run", "--rm", "compile-io-image"};
        executeCommandWithTimeout(command, timeLimit);
        System.out.println();
        System.out.println("Container has finished execution.");
        this.teardownDockerImage();
        this.teardownDockerfile();
    }

    /**
     * Builds a docker image with the image name given to the constructor
     * @return void
     * @throws IOException e 
     * @throws InterruptedException e
     */
    public void buildContainer() {
        System.out.println("Attempting to build docker container...");
        String[] command = {"docker", "build", "-t", "compile-io-image", this.fileDirectory};
        executeCommand(command);
    }

    /**
     * Tears down the created image
     * @return void
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public void teardownDockerImage() {
        System.out.println("Beginning teardown of Docker image...");
        System.out.println();
        String[] command = {"docker", "rmi", "--force", "compile-io-image"};
        executeCommand(command);
        System.out.println();
        System.out.println("Successfully removed the Docker image.");
    }

    /**
     * Removes the Dockerfile used to create the image
     * @return void
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public void teardownDockerfile() {
        System.out.println("Beginning teardown of Dockerfile...");
        File dockerfile = new File(this.fileDirectory + "/Dockerfile");
        dockerfile.delete();
        System.out.println();
        System.out.println("Successfully removed the Dockerfile from " + this.fileDirectory);
    }

    /**
     * Executes the given command line argument. Output is displayed on the console.
     * For details on the format of the parameter, see Java Docs on the ProcessBuilder object.
     * @param String[] command An array of strings representing a command line instruction
     * @return void
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public void executeCommand(String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Executes the given command line argument. Output is displayed on the console.
     * Times out the process after surpassing the given time.
     * For details on the format of the parameter, see Java Docs on the ProcessBuilder object.
     * @param String[] command An array of strings representing a command line instruction
     * @param long timeLimit 
     * @return void
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public void executeCommandWithTimeout(String[] command, long timeLimit) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();
            boolean procFinished = proc.waitFor(timeLimit, TimeUnit.SECONDS);
            if (!procFinished) {
                System.out.println("ERROR: Alotted execution time has elapsed. Process timed out.\n");
                System.out.println("Terminating process and exiting gracefully...");
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Getter method for the field fileName
     * @return String the value of the field fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Getter method for the field fileDirectory
     * @return String the value of the field fileDirectory
     */
    public String getFileDirectory() {
        return this.fileDirectory;
    }

    /**
     * Setter method for the field fileName
     * @param String fileName 
     * @return void
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Setter method for the field fileDirectory
     * @param String fileDirectory 
     * @return void
     */
    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Creates the Dockerfile in the file directory specified
     * @return void
     * @throws FileNotFoundException e If the the given file does not exist or cannot be found
     * @throws IOException e If the java IO encounters an error
     */
    public void createDockerfile(String dockerfileData) {
        FileOutputStream fos = null;
        File file;

        try {
            System.out.println("Making Dockerfile in directory: " + this.getFileDirectory());
            file = new File(this.getFileDirectory() + "/Dockerfile");
            fos = new FileOutputStream(file);
      
            if (!file.exists()) {
               file.createNewFile();
            }

            fos.write(dockerfileData.getBytes());
            fos.flush();
            System.out.println("Dockerfile successfully written: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error in closing the Stream");
            }
        }
    }

    /**
     * Creates a string that contains the contents needed for a Dockerfile
     * IMPORTANT: Dockerfile MUST be in the directory of the source files it intends to run
     * @return String The text corresponding to the contents of the Dockerfile
     */
    public abstract String getDockerfileData();

}