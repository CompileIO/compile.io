package compile_io.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
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
import compile_io.mongo.models.Assignment;
import compile_io.mongo.models.Code;
import compile_io.mongo.repositories.AssignmentRepository;
import compile_io.mongo.repositories.CodeRepository;
import compile_io.storage.StorageFileNotFoundException;
import compile_io.storage.StorageService;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class CodeController {

	@Autowired
	public CodeRepository codeRepository;

	@Autowired
	public AssignmentRepository assignmentRepository;

	private final StorageService storageService;
	private String codePath;

	@Autowired
	public CodeController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	 @GetMapping("/Code")
	    public List<Code> getAllCodes() {
	        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	        return codeRepository.findAll(sortByCreatedAtDesc);
	    }
	    
	    @GetMapping("/Code/getAssignment/{assignmentId}")
	    public ResponseEntity<List<Code>> getAllCodesForAssignment(@PathVariable("assignmentId") String assignmentId) {
	        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	        return ResponseEntity.ok().body(codeRepository.findByassignmentId(assignmentId, sortByCreatedAtDesc));
	    }
	    
	    @GetMapping(value="/Code/{id}")
	    public ResponseEntity<Code> getCodeById(@PathVariable("id") String id) {
	        return codeRepository.findById(id)
	                .map(assignment -> ResponseEntity.ok().body(assignment))
	                .orElse(ResponseEntity.notFound().build());
	    }

	@PostMapping("/Code/uploadFile")
	public ResponseEntity<String> uploadFile(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("file");
		String courseName = request.getParameter("courseName");
		String assignmentName = request.getParameter("assignmentName");
		String userName = request.getParameter("userName");
		storageService.storeAddPath(file, "student", courseName, assignmentName, userName);
		return ResponseEntity.ok().body("uploaded " + file.getOriginalFilename());
	}

	@PostMapping("/Code/uploadCode")
	public ResponseEntity<List<String>> inputCodeforUser(MultipartHttpServletRequest request) {
		String userName = request.getParameter("username");
		String type = request.getParameter("type");
		String runTime = request.getParameter("runTime");
		String givenAssignmentId = request.getParameter("assignmentID");
		Optional<Assignment> assignmentToRun = assignmentRepository.findById(givenAssignmentId);
		String assignmentFilepath = assignmentToRun.get().getFilePath();
		Path studentDir = Paths
				.get("upload-dir\\" + assignmentToRun.get().getCourseName().replaceAll(" ", "_").toLowerCase() + "\\"
						+ assignmentToRun.get().getassignmentName().replaceAll(" ", "_").toLowerCase()
						+ "\\student-files\\" + userName.replaceAll(" ", "_").toLowerCase());
		
		this.codePath = studentDir.toString();
		System.out.println("\n\n\n\n\n" + this.codePath + "\n\n\n\n\n");
		int runTimeNum = Integer.parseInt(runTime);
		LocalTime submissionTime = LocalTime.now();
		Code newCode = new Code(type, runTimeNum, this.codePath, submissionTime, givenAssignmentId, userName);
		// Docker stuff
		newCode.addTestResponse(runCompiler(type, runTimeNum, assignmentFilepath));
		codeRepository.save(newCode);
		return ResponseEntity.ok().body(newCode.getTestResponse());
	}

	public String runCompiler(String language, int timeLimit, String assignmentFilepath) {
		List<File> studentFiles = new ArrayList<>();
		List<File> ProfessorFiles = new ArrayList<>();
		File studentDirLocation = Paths.get(this.codePath).toFile();
		File professorDirLocation = Paths.get(assignmentFilepath).toFile();
		for (File file : studentDirLocation.listFiles()) {
			studentFiles.add(file);
		}
		for (File file : professorDirLocation.listFiles()) {
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
		return "Failed Running Docker";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
