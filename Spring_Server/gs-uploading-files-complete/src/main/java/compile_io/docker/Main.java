package compile_io.docker;

import java.io.*;

/**
 * Used to run the backend independent of the server; eliminates the need to have website up and running
 * Bypasses end-to-end functionality to solely manually test the backend
 */
public class Main {
    public static void main(String[] args){
        CompilerFactory compilerFactory = new CompilerFactory();
        File file = new File("C:\\SCHOOL\\DockerTest\\SimplePrint.jar");
        AbstractCompiler compiler = compilerFactory.getCompiler("java", file);
        compiler.createDockerfile();
        compiler.buildContainer();
        compiler.run();
    }
}