package compile_io.docker;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Class used to execute Terminal commands
 */
public class CommandExecuter implements ICommandExecuter {

    public CommandExecuter() {}

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