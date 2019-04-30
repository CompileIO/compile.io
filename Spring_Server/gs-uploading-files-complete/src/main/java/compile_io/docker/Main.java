package compile_io.docker;

import java.io.*;
import java.util.*;
import java.nio.file.Paths;

/**
 * Used to run the backend independent of the server; eliminates the need to have website up and running
 * Bypasses end-to-end functionality to solely manually test the backend
 */
public class Main {
    public static void main(String[] args){
        BuilderFactory builderFactory = new BuilderFactory();
        String home = System.getProperty("user.home");
        System.out.println(home);
        File studentDir = Paths.get(home+"/Downloads").toFile();
        List<File> studentFiles = new ArrayList<>();
        for (File file: studentDir.listFiles()) {
            if(file.getName().equals("centipede.zip")) {
                System.out.println("Adding student File " +file.getName());
                studentFiles.add(file);
            } 
        }

        // File profDir = Paths.get(home+"/Downloads").toFile();
        List<File> professorFiles = new ArrayList<>();
        // for (File file: profDir.listFiles()) {
        //     if(file.getName().equals("SimpleTest.java")) {
        //         System.out.println("Adding Professor File " + file.getName());
        //         professorFiles.add(file);
        //     } 
        // }

        try {
            String codePath = "/2019/4/csse120/3/homework_1/student-files/palamujg";
            AbstractBuilder builder = builderFactory.getBuilder("java", studentFiles, professorFiles, codePath);
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