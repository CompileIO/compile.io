package compile_io.docker;

import java.io.*;
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

        dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("CMD unzip " + studentFiles.get(0));
        dockerfileData.append("COPY build.gradle /" + studentFiles.get(0).getName() + "/build.gradle\n");
        dockerfileData.append("COPY " + super.getCodePath() + "/" + studentFiles.get(0).getName() + " " + studentFiles.get(0).getName() +  "\n");
        // for (int i = 0; i < super.getNumProfessorFiles(); i++) {
        // 	String professorFilePath = super.getCodePath().replaceFirst("student-files", "professor-files");
        //     dockerfileData.append("COPY " + professorFilePath + "/" + professorFiles.get(i).getName() + " " + professorFiles.get(i).getName() +  "\n");
        // }
        dockerfileData.append("CMD unzip " + studentFiles.get(0));
        dockerfileData.append("CMD export GRADLE_USER_HOME=\"" + super.getWorkingDirectory() + "\" && cd " + studentFiles.get(0).getName() + " && gradle test\n");

        return dockerfileData.toString();
    }
}