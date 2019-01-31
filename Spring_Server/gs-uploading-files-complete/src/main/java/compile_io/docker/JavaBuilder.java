package compile_io.docker;

import java.io.*;
import java.util.*;

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

    public JavaBuilder(List<File> studentFiles, List<File> professorFiles) {
        super(studentFiles, professorFiles);
    }

    /**
     * Creates a string that contains the contents needed for a Dockerfile that runs a single jar file.
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

    
    /**
     * Creates a string that contains the contents needed for a Dockerfile that can run multiple java files against JUnit tests using gradle
     * @return String The text corresponding to the contents of the Dockerfile
     */
    public String getDockerfileDataFiles() {
        StringBuilder dockerfileData = new StringBuilder();
        List<File> studentFiles = super.getStudentFiles();
        List<File> professorFiles = super.getProfessorFiles();

        dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
        dockerfileData.append("WORKDIR /SCHOOL/DockerTest/mock-upload-dir\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("RUN mkdir -p src/main/java\n");
        dockerfileData.append("RUN mkdir -p src/test/java\n");
        dockerfileData.append("COPY build.gradle build.gradle\n");
        for (int i = 0; i < super.getNumStudentFiles(); i++) {
            dockerfileData.append("COPY " + studentFiles.get(i).getName() + " " + studentFiles.get(i).getName() +  "\n");
            dockerfileData.append("RUN mv " + studentFiles.get(i).getName() + " " + "src/main/java/\n");
        }
        for (int i = 0; i < super.getNumProfessorFiles(); i++) {
            dockerfileData.append("COPY " + professorFiles.get(i).getName() + " " + professorFiles.get(i).getName() +  "\n");
            dockerfileData.append("RUN mv " + professorFiles.get(i).getName() + " " + "src/test/java/\n");
        }
        dockerfileData.append("CMD export GRADLE_USER_HOME=\"/SCHOOL/DockerTest/mock-upload-dir\" && gradle test\n");

        System.out.println(dockerfileData.toString());

        return dockerfileData.toString();
    }

}