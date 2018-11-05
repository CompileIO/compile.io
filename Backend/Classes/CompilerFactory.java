/**
 * Factory class that chooses the correct compiler to use
 */
public class CompilerFactory {

    /**
     * Factory method that retrieves the correct compiler, based on the given type
     * @param Object type The type that represents the needed type of compiler
     * @return ICompiler A compiler capable of running docker containers
     * @return null If the given type does not correspond to a supported compiler type
     */
    public ICompiler getCompiler(Object type){
        if (type == "java") { // something along these lines
            //return new JavaCompiler(executableFileBytes);
            return new JavaCompiler();
        } else {
            return null;
        }
    }

}