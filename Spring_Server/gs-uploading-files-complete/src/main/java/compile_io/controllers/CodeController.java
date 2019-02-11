package compile_io.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import compile_io.docker.*;
import compile_io.mongo.models.Code;
import compile_io.mongo.repositories.CodeRepository;
import compile_io.storage.StorageFileNotFoundException;
import compile_io.storage.StorageService;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CodeController{
	
	@Autowired 
	public CodeRepository codeRepository;

	private final StorageService storageService;
	private String fileName;
  private final int MAX_FILE_SIZE = 50000000;

	@Autowired
	public CodeController(StorageService storageService) {
		this.storageService = storageService;
	}
		
	@PostMapping("/{courseName}/{homeworkName}/uploadTest")
	public ResponseEntity<List<String>> inputCodeforUser(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("file");
		String fileString = request.getParameter("file");
		String userName = request.getParameter("username");
		String type = request.getParameter("type");
		String runTime = request.getParameter("runTime");
		String givenCourse = request.getParameter("class");
		//Save file to correct path
		storageService.storeAddPath(file, "student");                
		fileName = file.getName();		
		int runTimeNum = Integer.parseInt(runTime);
		LocalTime submissionTime = LocalTime.now();
		Code newCode = new Code(type, runTimeNum, fileName, submissionTime);
    	// Docker stuff
		String result = runCompiler(type, runTimeNum);
		String[] temp2 = {result};
		newCode.addTestResponse(result);
		codeRepository.save(newCode);
		return ResponseEntity.ok().body(newCode.getTestResponse());
	}
	
	public String runCompiler(String language, int timeLimit) {
		List<File> studentFiles = new ArrayList<>();
		List<File> ProfessorFiles = new ArrayList<>();
		File studentDirLocation = Paths.get("\\upload-dir\\student-files").toFile();
		File professorDirLocation = Paths.get("\\upload-dir\\professor-files").toFile();
		for (File file: studentDirLocation.listFiles()) {
			studentFiles.add(file);
		}
		for (File file: professorDirLocation.listFiles()) {
			ProfessorFiles.add(file);
		}
		try {
			BuilderFactory builderFactory = new BuilderFactory();
			AbstractBuilder builder = builderFactory.getBuilder(language, studentFiles, ProfessorFiles);
			IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
			builder.createDockerfile(builder.getDockerfileData());
			builder.buildContainer();
			return runner.run(timeLimit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
