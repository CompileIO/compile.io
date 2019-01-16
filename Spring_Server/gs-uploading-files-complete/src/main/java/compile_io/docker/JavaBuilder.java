package compile_io.docker;

import java.io.*;

/**
 * This class builds a Dockerfile that the superclass uses to create a Docker image.
 */
public class JavaBuilder extends AbstractBuilder {

    /**
     * Constructor that builds a docker image with the given name
     * @param File file File uploaded by the student
     */
    public JavaBuilder(File file) {
        super(file);
    }

    /**
     * Creates a string that contains the contents needed for a Dockerfile
     * IMPORTANT: Dockerfile MUST be in the directory of the source files it intends to run
     * @return String The text corresponding to the contents of the Dockerfile
     */
    public String getDockerfileData() {  
        StringBuilder dockerfileData = new StringBuilder();
        
        dockerfileData.append("FROM openjdk\n");
        dockerfileData.append("WORKDIR " + super.getFileDirectory() + "\n");
        dockerfileData.append("ADD " + super.getFileName() + " " + super.getFileName() + "\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("CMD java -jar " + super.getFileName() + "\n");

        return dockerfileData.toString();
    }
}