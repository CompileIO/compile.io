package compile_io.docker;

import java.io.*;

/**
 * Used to run the backend independent of the server; eliminates the need to have website up and running
 * Bypasses end-to-end functionality to solely manually test the backend
 */
public class Main {
    public static void main(String[] args){
        CompilerFactory compilerFactory = new CompilerFactory();
        File file = new File("C:\\SCHOOL\\DockerTest\\PythonStuff\\Main.py");
        AbstractCompiler compiler = compilerFactory.getCompiler("python", file);
        compiler.createDockerfile(compiler.getDockerfileData());
        compiler.buildContainer();
        compiler.run(60);
    }
}