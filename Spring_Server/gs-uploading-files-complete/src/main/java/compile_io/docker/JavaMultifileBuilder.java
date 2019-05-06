package compile_io.docker;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class build a Dockerfile that the superclass uses to create a Docker image
 */
public class JavaMultifileBuilder extends AbstractBuilder {

    public JavaMultifileBuilder(List<File> studentFiles, List<File> professorFiles, String codePath) {
        super(studentFiles, professorFiles, codePath);
    }

    public String getDockerfileData() {
        StringBuilder dockerfileData = new StringBuilder();
        List<File> studentFiles = super.getStudentFiles();
        
        
        // List<File> professorFiles = super.getProfessorFiles();

        if (studentFiles.size() != 1) {
            throw new InvalidFileException("Expected single file (a zipped project), but received " + studentFiles.size() + " files.");
        }        
        
        String studentFileName = studentFiles.get(0).getName();//.replace(".zip", "");
        String studentFileNameNoZip = studentFiles.get(0).getName().replace(".zip", "");
        dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("USER root\n");
//        dockerfileData.append("COPY build.gradle build.gradle \n");
        dockerfileData.append("COPY " +  super.getCodePath() + "/" + studentFileName + " " + studentFileName +"\n");
        dockerfileData.append("RUN unzip "  + studentFileName+ "\n");
        dockerfileData.append("RUN rm " +  studentFileName + "\n");
        
        dockerfileData.append("RUN rm " +  studentFileNameNoZip + "/build.gradle"  + "\n");
//        dockerfileData.append("RUN rm -r " +  studentFileNameNoZip + "/gradle"  + "\n");
//        dockerfileData.append("RUN rm " +  studentFileNameNoZip + "/gradlew"  + "\n");
//        dockerfileData.append("RUN rm " +  studentFileNameNoZip + "/gradlew.bat"  + "\n");
        
//        dockerfileData.append("RUN mv build.gradle /"  + studentFileNameNoZip + " \n");//
        
        dockerfileData.append("COPY build.gradle " + studentFileNameNoZip + "/build.gradle \n");
        
//        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "/" + studentFileNameNoZip + "\n");//
        dockerfileData.append("CMD export GRADLE_USER_HOME=\"" + super.getWorkingDirectory() + "\" && cd " + studentFileNameNoZip + " && vi build.gradle");

        return dockerfileData.toString();
    } 
}