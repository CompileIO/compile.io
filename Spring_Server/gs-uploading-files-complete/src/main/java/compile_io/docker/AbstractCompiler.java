package compile_io.docker;

import java.io.*;

/**
 * Abstract class for the compilers that compile.io will utilize.
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
        System.out.println("Given file: " + this.fileName + ". Attempted target directory: " + this.fileDirectory);
        this.createDockerfile();
        this.buildContainer();
    }

    /**
     * Runs the container with the image name given to the constructor and prints the output to the console.
     * Removes the created docker image after the execution of said image.
     * @return void
     * @throws Exception e
     */
    public void run(){
        try {
            System.out.println("Attempting to run docker container...");
            String[] command = {"docker", "run", "--rm", "compile-io-image"};
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
            System.out.println("ERROR: Failed to run the Docker container!\n");
            System.exit(1);
        }
    }

    /**
     * Builds a docker image with the image name given to the constructor
     * @return void
     * @throws IOException e 
     * @throws InterruptedException e
     */
    public void buildContainer() {
        try {
            System.out.println("Attempting to build docker container...");
            String[] command = {"docker", "build", "-t", "compile-io-image", this.fileDirectory};
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Docker build failed!\n");
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("ERROR: Docker build failed!\n");
            System.exit(1);
        }
    }

    /**
     * Removes the Dockerfile used to create the image
     * @return void
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public void teardownDockerfile() {
        try {
            String[] command = {"rm", this.fileDirectory + "Dockerfile"};
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
     * Creates the Dockerfile used for creating the docker container
     * IMPORTANT: Dockerfile MUST be in the directory of the source files it intends to run
     * @return void
     * @throws FileNotFoundException e If the the given file does not exist or cannot be found
     * @throws IOException e If the java IO encounters an error
     */
    public abstract void createDockerfile();

}