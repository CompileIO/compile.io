package compile_io.docker;

import java.io.*;

/**
 * Factory class that chooses the correct builder to use
 */
public class BuilderFactory {

    /**
     * Factory method that retrieves the correct builder, based on the given type
     * @param String type The type that represents the needed type of compbuilderiler
     * @return AbstractBuilder A builder capable of building docker containers for the specified language
     * @throws UnsupportedBuilderException If the given type does not correspond to a supported language type
     */
    public AbstractBuilder getBuilder(String type, File file) throws UnsupportedBuilderException {
        // change this from a nested if statement to...?
        if (type == "java") {
            return new JavaBuilder(file);
        } else if (type == "python") {
            return new PythonBuilder(file);
        } else {
            throw new UnsupportedBuilderException();
        }
    }

}