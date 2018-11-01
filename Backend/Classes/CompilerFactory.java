/**
 * Factory class that chooses the correct compiler to use
 */
public class CompilerFactory {

    public ICompiler getCompiler(Object type){
        if (type == "java") { // something along these lines
            return new JavaCompiler();
        } else {
            return null;
        }
    }

}