package compile_io.docker;

import java.io.*;

/**
 * Used to run the backend independent of the server; eliminates the need to have website up and running
 * Bypasses end-to-end functionality to solely manually test the backend
 */
public class Main {
    public static void main(String[] args){
        BuilderFactory builderFactory = new BuilderFactory();
        // File file = new File("/SCHOOL/DockerTest/RevEngDrunnable.jar");
        File file = new File("/SCHOOL/DockerTest/PythonStuff/Main.py");

        try {
            AbstractBuilder builder = builderFactory.getBuilder("python", file);
            // AbstractBuilder builder = compilerFactory.getCompiler("java", file);
            IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
            builder.createDockerfile(builder.getDockerfileData());
            builder.buildContainer();
            String result = runner.run(60);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}