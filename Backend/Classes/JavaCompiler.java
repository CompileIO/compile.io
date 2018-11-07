import java.io.*;

/**
 * This class builds a docker image, then runs that docker image
 */
public class JavaCompiler implements ICompiler {

    private File file;
    private String fileName;
    private String fileDirectory;

    /**
     * Default Constructor, no bytes given, for testing purposes at the moment
     */
    public JavaCompiler() {}

    /**
     * Constructor that builds a docker image with the given name
     * @param byte[] executableFileBytes The bytecode of the student-submitted executable
     */
    public JavaCompiler(File file) {
        this.file = file;
        this.fileName  = file.getName();
        this.fileDirectory = file.getParent(); 
        if (this.fileDirectory == null) {
            this.fileDirectory = "/";
        }
        System.out.println("Found file: " + this.fileName + " in directory: " + this.fileDirectory);
        this.createDockerfile();
        this.buildContainer();
    }

    /**
     * Runs the container with the image name given to the constructor and prints the output to the console
     * @return void
     * @throws Exception e
     */
    public void run(){
        try {
            System.out.println("Attempting to run docker container...");
            String[] command = {"docker", "run", "java-image"};
            //String[] command = {"docker", "run", "hello-world"};
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
     * @throws IOException e 
     * @throws InterruptedException e
     */
    public void buildContainer() {
        try {
            System.out.println("Attempting to build docker container...");
            String[] command = {"docker", "build", "-t", "java-image", "."};
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
     * Creates the Dockerfile used for creating the docker container
     * @return void
     * @throws FileNotFoundException e If the the given file does not exist or cannot be found
     * @throws IOException e If the java IO encounters an error
     */
    public void createDockerfile() {
        System.out.println("Making Dockerfile...");
        String dockerfileData = "FROM openjdk\nWORKDIR " + this.fileDirectory + "\nADD " + this.fileName + " " + this.fileName + "\nEXPOSE 8000\nCMD java -jar " + this.fileName + "\n";
        try {
            FileOutputStream dockerfileFos = new FileOutputStream("Dockerfile");
            dockerfileFos.write(dockerfileData.getBytes());
            dockerfileFos.flush();
            dockerfileFos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}