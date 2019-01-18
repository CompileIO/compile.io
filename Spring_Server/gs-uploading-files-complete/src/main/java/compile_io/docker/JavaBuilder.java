package compile_io.docker;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

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

    // Hard coded manual test function to test different ways to run professor test code against student code
    public String getDockerfileDataFiles() {
        List<File> files = new ArrayList<File>();
        File studentCode = new File("/EclipseWorkspaces/csse230/PreorderBuildTree/src/buildtree/BinaryTree.java");
        File professorTestCode = new File("/EclipseWorkspaces/csse230/PreorderBuildTree/src/buildtree/Testing.java");
        files.add(studentCode);
        files.add(professorTestCode);
        StringBuilder dockerfileData = new StringBuilder();
        
        dockerfileData.append("FROM openjdk\n");
        dockerfileData.append("WORKDIR /SCHOOL/DockerTest/MultipleFilesTest\n");
        String toAdd;
        for (int i = 0; i < files.size(); i++) {
            toAdd = files.get(i).getName();
            dockerfileData.append("ADD " + toAdd + " " + toAdd + "\n");
        }
        dockerfileData.append("EXPOSE 8000\n");
        // dockerfileData.append("CMD java -jar " + super.getFileName() + "\n");
        dockerfileData.append("CMD java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore Testing.java\n");

        return dockerfileData.toString();
    }
}