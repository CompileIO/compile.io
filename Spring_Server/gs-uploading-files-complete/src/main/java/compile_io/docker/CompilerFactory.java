package compile_io.docker;
/**
 * Factory class that chooses the correct compiler to use
 */
import java.io.*;

public class CompilerFactory {

    /**
     * Factory method that retrieves the correct compiler, based on the given type
     * @param Object type The type that represents the needed type of compiler
     * @return ICompiler A compiler capable of running docker containers in the specified language
     * @return null If the given type does not correspond to a supported compiler type
     */
    public ICompiler getCompiler(Object type, File file) {
        if (type == "java") { // something along these lines
            return new JavaCompiler(file);
            //return new JavaCompiler();
        } else {
            return null;
        }
    }

}