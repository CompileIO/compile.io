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
        //File file1 = new File("/SCHOOL/DockerTest/mock-upload-dir/BinaryTree.java");
        //File file2 = new File("/SCHOOL/DockerTest/mock-upload-dir/Testing.java");
        File file3 = new File("/SCHOOL/DockerTest/mock-upload-dir/Simple.java");
        File file4 = new File("/SCHOOL/DockerTest/mock-upload-dir/SimpleTest.java");

        List<File> studentFiles = new ArrayList<>();
        // studentFiles.add(file1);
        // studentFiles.add(file2);
        List<File> professorFiles = new ArrayList<>();
        professorFiles.add(file3);
        professorFiles.add(file4);
        // File file = new File("/SCHOOL/DockerTest/PythonStuff/Main.py");

        try {
            //AbstractBuilder builder = builderFactory.getBuilder("python", file);
            //AbstractBuilder builder = builderFactory.getBuilder("java", file);
            AbstractBuilder builder = builderFactory.getBuilderManyFiles("java", studentFiles, professorFiles);
            IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
            builder.createDockerfile(builder.getDockerfileDataFiles());
            builder.buildContainer();
            String result = runner.run(60000);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}