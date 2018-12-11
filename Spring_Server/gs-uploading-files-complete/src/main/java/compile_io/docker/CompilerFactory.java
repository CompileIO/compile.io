package compile_io.docker;

import java.io.*;

/**
 * Factory class that chooses the correct compiler to use
 */
public class CompilerFactory {

    /**
     * Factory method that retrieves the correct compiler, based on the given type
     * @param String type The type that represents the needed type of compiler
     * @return Compiler A compiler capable of running docker containers in the specified language
     * @return null If the given type does not correspond to a supported compiler type
     */
    public AbstractCompiler getCompiler(String type, File file) {
        if (type == "java") {
            return new JavaCompiler(file);
        } else {
            return null; // make this an exception in the future
        }
    }

}