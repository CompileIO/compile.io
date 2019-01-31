package compile_io.docker;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

/**
 * Class used to execute Terminal commands
 */
public class CommandExecuter implements ICommandExecuter {

    public CommandExecuter() {}

    /**
     * Executes the given command line argument. Output is displayed on the console.
     * For details on the format of the parameter, see Java Docs on the ProcessBuilder object.
     * @param String[] command An array of strings representing a command line instruction
     * @return String 
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public String executeCommand(String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process proc = pb.start();
        
            proc.waitFor();
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                result.append(line + '\n');
                line = reader.readLine();
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Executes the given command line argument. Output is displayed on the console.
     * Times out the process after surpassing the given time.
     * For details on the format of the parameter, see Java Docs on the ProcessBuilder object.
     * @param String[] command An array of strings representing a command line instruction
     * @param long timeLimit 
     * @return String  
     * @throws IOException e
     * @throws InterruptedException e 
     */
    public String executeCommandWithTimeout(String[] command, long timeLimit) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process proc = pb.start();
            boolean procFinished = proc.waitFor(timeLimit, TimeUnit.SECONDS);
            if (!procFinished) {
                System.out.println("ERROR: Alotted execution time has elapsed. Process timed out.\n");
                System.out.println("Terminating process gracefully and beginning teardown...");
                return null;
            }
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                result.append(line + '\n');
                line = reader.readLine();
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


}