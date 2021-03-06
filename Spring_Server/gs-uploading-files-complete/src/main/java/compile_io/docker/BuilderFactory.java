package compile_io.docker;

import java.io.*;
import java.util.*;

/**
 * Factory class that chooses the correct builder to use
 */
public class BuilderFactory {
    public AbstractBuilder getBuilder(String type, List<File> studentFiles, List<File> professorFiles, String codePath) {
        if (type.equals("java")) {
            return new JavaBuilder(studentFiles, professorFiles, codePath);
        } else if (type.equals("python")) {
            return new PythonBuilder(studentFiles, professorFiles, codePath);
        } else if (type.equals("java-multifile")) {
            return new JavaMultifileBuilder(studentFiles, professorFiles, codePath);
        }
        throw new UnsupportedBuilderException();
    }

}