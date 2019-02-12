package compile_io.docker;

import java.io.*;
import java.util.*;

/**
 * This class builds a Dockerfile that the superclass uses to create a Docker image.
 */
public class JavaBuilder extends AbstractBuilder {

    public JavaBuilder(List<File> studentFiles, List<File> professorFiles) {
        super(studentFiles, professorFiles);
    } 

    public String getDockerfileData() {
        StringBuilder dockerfileData = new StringBuilder();
        List<File> studentFiles = super.getStudentFiles();
        List<File> professorFiles = super.getProfessorFiles();

        dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
        dockerfileData.append("USER root\n");
        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("RUN mkdir -p src/main/java\n");
        dockerfileData.append("RUN mkdir -p src/test/java\n");
        dockerfileData.append("COPY build.gradle build.gradle\n");
        for (int i = 0; i < super.getNumStudentFiles(); i++) {
            dockerfileData.append("COPY student-files/" + studentFiles.get(i).getName() + " " + studentFiles.get(i).getName() +  "\n");
            dockerfileData.append("RUN mv " + studentFiles.get(i).getName() + " " + "src/main/java/\n");
        }
        for (int i = 0; i < super.getNumProfessorFiles(); i++) {
            dockerfileData.append("COPY professor-files/" + professorFiles.get(i).getName() + " " + professorFiles.get(i).getName() +  "\n");
            dockerfileData.append("RUN mv " + professorFiles.get(i).getName() + " " + "src/test/java/\n");
        }
        dockerfileData.append("CMD export GRADLE_USER_HOME=\"" + super.getWorkingDirectory() + "\" && gradle test\n");

        return dockerfileData.toString();
    }

}