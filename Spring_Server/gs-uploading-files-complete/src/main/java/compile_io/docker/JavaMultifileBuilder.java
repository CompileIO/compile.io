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
//        String studentFileName = studentFiles.get(0).getName().replace(".zip", "");
////        File studentworkDir = Paths.get(studentFiles.get(0).getParent()).toFile();
////        String studentworkingDirectory = studentFiles.get(0).getAbsolutePath();
////        String newStudentWD = studentworkingDirectory.replace(".zip", "");
////        System.out.println("Student working directory path: " + studentworkingDirectory);
//
//        dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
//        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
//        dockerfileData.append("EXPOSE 8000\n");
////        dockerfileData.append("COPY build.gradle /" + studentFileName + "/build.gradle\n");
//        dockerfileData.append("COPY build.gradle build.gradle\n");
//        dockerfileData.append("WORKDIR " + super.getCodePath() + "\n");
//        dockerfileData.append("COPY " +  studentFileName + " " + studentFileName +"\n");
//        dockerfileData.append("RUN unzip "  + studentFiles.get(0).getName());
//        dockerfileData.append("RUN rm " +  studentFiles.get(0).getName());
////        dockerfileData.append("CMD unzip " + studentFiles.get(0) + "\n");
//        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\\" + studentFileName  + "\n");
////        dockerfileData.append("COPY " + super.getCodePath() + "\\" + studentFileName + " " + studentFileName +  "\n");
////        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
//        
////        dockerfileData.append("WORKDIR " + studentworkDir + "\n");
//        // for (int i = 0; i < super.getNumProfessorFiles(); i++) {
//        // 	String professorFilePath = super.getCodePath().replaceFirst("student-files", "professor-files");
//        //     dockerfileData.append("COPY " + professorFilePath + "/" + professorFiles.get(i).getName() + " " + professorFiles.get(i).getName() +  "\n");
//        // }
//        dockerfileData.append("CMD export GRADLE_USER_HOME=\"" + super.getWorkingDirectory() + "\" && ls\n");
//
//        return dockerfileData.toString();
        
        
        String studentFileName = studentFiles.get(0).getName();//.replace(".zip", "");
        String studentFileNameNoZip = studentFiles.get(0).getName().replace(".zip", "");
        dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("COPY " +  super.getCodePath() + "/" + studentFileName + " " + studentFileName +"\n");
        dockerfileData.append("RUN unzip "  + studentFileName+ "\n");
        dockerfileData.append("RUN rm " +  studentFileName + "\n");
//        dockerfileData.append("COPY " + super.getCodePath() + "/" + studentFileName + " " + studentFileName +  "\n");
        dockerfileData.append("COPY build.gradle /" + studentFileName + "/build.gradle\n");
        dockerfileData.append("CMD export GRADLE_USER_HOME=\"" + super.getWorkingDirectory() + "\" && ls");

        return dockerfileData.toString();
    } 
}