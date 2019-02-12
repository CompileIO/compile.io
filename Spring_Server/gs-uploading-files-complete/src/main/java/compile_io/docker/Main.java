package compile_io.docker;

import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Used to run the backend independent of the server; eliminates the need to have website up and running
 * Bypasses end-to-end functionality to solely manually test the backend
 */
public class Main {
    public static void main(String[] args){
        BuilderFactory builderFactory = new BuilderFactory();
        File file3 = new File("/SCHOOL/DockerTest/mock-upload-dir/student-files/Simple.java");
        File file4 = new File("/SCHOOL/DockerTest/mock-upload-dir/professor-files/SimpleTest.java");

        File studentDir = Paths.get("mock-upload-dir/student-files").toFile();
        List<File> studentFiles = new ArrayList<>();
        for (File file: studentDir.listFiles()) {
            if(file.getName().equals("Simple.java")) {
                System.out.println(file.getName());
                studentFiles.add(file);
            } 
        }

        File profDir = Paths.get("mock-upload-dir/professor-files").toFile();
        List<File> professorFiles = new ArrayList<>();
        for (File file: profDir.listFiles()) {
            if(file.getName().equals("SimpleTest.java")) {
                System.out.println(file.getName());
                professorFiles.add(file);
            } 
        }

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