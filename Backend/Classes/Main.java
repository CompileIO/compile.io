/**
 * The main class which is the entrypoint to the backend
 */
import java.io.*;
 
public class Main {
    public static void main(String[] args){
        // File file = new File(args[0]);
        File file = new File("C:\\SCHOOL\\RevEngDrunnable.jar");
        CompilerFactory compilerFactory = new CompilerFactory();
        // ICompiler compiler = compilerFactory.getCompiler(args[1], file);
        ICompiler compiler = compilerFactory.getCompiler("java", file);
        compiler.run();
    }
}