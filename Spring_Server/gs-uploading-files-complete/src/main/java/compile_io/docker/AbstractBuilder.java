package compile_io.docker;

import java.io.*;
import java.util.*;
import java.nio.file.Paths;

/**
 * Abstract class for the compilers that builds and runs docker images.
 */
public abstract class AbstractBuilder {

    private String workingDirectory;
    private List<File> studentFiles;
    private List<File> professorFiles;
    private String codePath;
    private ICommandExecuter executer;
    private int numStudentFiles;
    private int numProfessorFiles;

    public AbstractBuilder(List<File> studentFiles, List<File> professorFiles, String codePath) {
        File workDir = Paths.get("upload-dir/").toFile();
        this.studentFiles = studentFiles;
        this.professorFiles = professorFiles;
        this.codePath = codePath;
        this.numStudentFiles = this.studentFiles.size();
        this.numProfessorFiles = this.professorFiles.size();
        this.executer = new CommandExecuter();
        this.workingDirectory = workDir.getPath();
        if (this.workingDirectory == null) {
            this.workingDirectory = "/";
        }
        System.out.println("Files received. Target directory: " + this.workingDirectory);
    }


	public void buildContainer() {
        System.out.print("Attempting to build docker container...");
        String[] command = {"docker", "build", "-t", "compile-io-image", this.workingDirectory};
        System.out.println(this.executer.executeCommand(command));
        System.out.print("DONE\n");
    }

    public void teardownDockerImage() {
        System.out.println("Beginning teardown of Docker image...");
        String[] command = {"docker", "rmi", "--force", "compile-io-image"};
        this.executer.executeCommand(command);
        System.out.println("Successfully removed the Docker image.");
    }

    public void teardownDockerfile() {
        System.out.println("Beginning teardown of Dockerfile...");
        File dockerfile = new File(this.workingDirectory + "/Dockerfile");
        dockerfile.delete();
        System.out.println("Successfully removed the Dockerfile from " + this.workingDirectory);
    }

    public void createDockerfile(String dockerfileData) {
        FileOutputStream fos = null;
        File file;

        try {
            System.out.println("Making Dockerfile in directory: " + this.getWorkingDirectory());
            file = new File(this.getWorkingDirectory() + "/Dockerfile");
            fos = new FileOutputStream(file);
      
            if (!file.exists()) {
               file.createNewFile();
            }

            fos.write(dockerfileData.getBytes());
            fos.flush();
            System.out.println("Dockerfile successfully written: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error in closing the Stream");
            }
        }
    }

    public abstract String getDockerfileData();

    public String getWorkingDirectory() {
        return this.workingDirectory;
    }

    public ICommandExecuter getExecuter() {
        return this.executer;
    }

    public void setExecuter(ICommandExecuter executer) {
        this.executer = executer;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public List<File> getStudentFiles() {
        return this.studentFiles;
    }

    public List<File> getProfessorFiles() {
        return this.professorFiles;
    }

    public int getNumStudentFiles() {
        return this.numStudentFiles;
    }

    public int getNumProfessorFiles() {
        return this.numProfessorFiles;
    }
    
    public String getCodePath() {
    	return this.codePath;
    }

}
