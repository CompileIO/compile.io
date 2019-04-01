package compile_io.docker;

import java.io.*;
import java.util.List;

/**
 * This class builds a Dockerfile that the superclass uses to create a Docker image.
 */
public class PythonBuilder extends AbstractBuilder {

    public PythonBuilder(List<File> studentFiles, List<File> professorFiles) {
        super(studentFiles, professorFiles);
    }


	// Still only works for a single file.
    public String getDockerfileData() { 
        StringBuilder dockerfileData = new StringBuilder();
        List<File> studentFiles = super.getStudentFiles();
        // List<File> professorFiles = super.getProfessorFiles();

        dockerfileData.append("FROM python:latest\n");
        dockerfileData.append("WORKDIR " + super.getWorkingDirectory() + "\n");
        dockerfileData.append("ADD " + studentFiles.get(0).getName() + " " + studentFiles.get(0).getName() + "\n");
        dockerfileData.append("EXPOSE 8000\n");
        dockerfileData.append("CMD python " + studentFiles.get(0).getName() + "\n");
        
        return dockerfileData.toString();
    }

}