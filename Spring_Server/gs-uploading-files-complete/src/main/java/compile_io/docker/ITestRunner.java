package compile_io.docker;

import java.io.*;

/**
 * Interface for classes that will run unit tests
 */
public interface ITestRunner {

    /**
     * Runs the given file (fileToRun) against the provided tests from the test
     * file (testFile)
     * NOTE: Can probably use executeCommand method in AbstractCompiler
     * @param testFile File containing the tests to run
     * @param fileToRun File the tests will run against; file to be tested
     * @return String The output of the tests
     */
    public String runTests(File testFile, File fileToRun);

    /**
     * Returns the score of the unit tests
     * ?: Do professors assign point values to unit tests?
     * @return int The integer score of the unit tests
     */
    public int getGrade();

    

}