/**
 * The main class which is the entrypoint to the backend
 * Mostly pseudocode right now
 */
public class Main {
    public static void main(String[] args){
        CompilerFactory compilerFactory = new CompilerFactory();
        ICompiler compiler = compilerFactory.getCompiler(type);
        compiler.compile();
    }
}