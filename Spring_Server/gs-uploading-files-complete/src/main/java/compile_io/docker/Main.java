package compile_io.docker;

import java.io.*;
import java.util.*;

/**
 * Used to run the backend independent of the server; eliminates the need to have website up and running
 * Bypasses end-to-end functionality to solely manually test the backend
 */
public class Main {
    public static void main(String[] args){
        BuilderFactory builderFactory = new BuilderFactory();
        File file3 = new File("/SCHOOL/DockerTest/mock-upload-dir/Simple.java");
        File file4 = new File("/SCHOOL/DockerTest/mock-upload-dir/SimpleTest.java");

        List<File> studentFiles = new ArrayList<>();
        List<File> professorFiles = new ArrayList<>();
        studentFiles.add(file3);
        professorFiles.add(file4);

        try {
            AbstractBuilder builder = builderFactory.getBuilder("java", studentFiles, professorFiles);
            IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
            builder.createDockerfile(builder.getDockerfileData());
            builder.buildContainer();
            String result = runner.run(60000);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}