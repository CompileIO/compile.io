package compile_io.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import compile_io.docker.*;
import compile_io.mongo.repositories.TestRepository;

@RestController
public class TestController {
	
	@Autowired 
	public TestRepository repository;
	
	private String fileName;
	
	@GetMapping("/{courseName}/{assignment}/test")
	public String[] inputTestsforUser(@RequestParam("username") String userName,
									  @RequestParam("file") MultipartFile file, 
									  @RequestParam("type") String type,
									  @RequestParam("runTime") String runTime,
									  RedirectAttributes redirectAttributes) {

		int runTimeNum = Integer.parseInt(runTime);

		String workingDir = System.getProperty("user.dir") + "/upload-dir/" + file;
		workingDir = workingDir.substring(2);
		System.out.println("Working Directory = " + workingDir);

    	// Docker stuff
		File fileToUpload = new File(workingDir);
		String result = runCompiler(fileToUpload, type, runTimeNum);
		String[] temp2 = {result};
		return temp2;
	}
	
	@GetMapping("/{courseName}/{assignment}")
	public String[] getResults(@PathVariable String className, @PathVariable String homework) {
		String[] temp = {"done!"};
		return temp;
	}
	
	@GetMapping("/run")
	public String[] runDocker() {
		String workingDir = System.getProperty("user.dir") + "/upload-dir/" + fileName;
		workingDir = workingDir.substring(2);
		System.out.println("Working Directory = " + workingDir);

    	// Docker stuff
		File fileToUpload = new File(workingDir);
		String result = runCompiler(fileToUpload, "python", 60);
		String[] temp2 = {result};
		return temp2;
	}
	
//	@GetMapping("/test")
	public String runCompiler(File fileToUpload, String language, int timeLimit) {
		try {
			BuilderFactory builderFactory = new BuilderFactory();
			AbstractBuilder builder = builderFactory.getBuilder(language, fileToUpload);
			IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
			builder.createDockerfile(builder.getDockerfileData());
			builder.buildContainer();
			return runner.run(timeLimit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
