package compile_io.controllers;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import compile_io.docker.*;
import compile_io.mongo.models.Assignment;
import compile_io.mongo.models.Code;
import compile_io.mongo.models.Student;
import compile_io.mongo.repositories.AssignmentRepository;
import compile_io.mongo.repositories.StudentRepository;
import compile_io.mongo.repositories.CodeRepository;
import compile_io.storage.StorageFileNotFoundException;
import compile_io.storage.StorageService;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class CodeController {

	@Autowired
	public CodeRepository codeRepository;

	@Autowired
	public AssignmentRepository assignmentRepository;
	
	@Autowired
	public StudentRepository studentRepository;

	private final StorageService storageService;

	@Autowired
	public CodeController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	 @GetMapping("/Code")
	    public ResponseEntity<List<Code>> getAllCodes() {
	        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	        return ResponseEntity.ok().body(codeRepository.findAll(sortByCreatedAtDesc));
	    }
	    
	    @GetMapping("/Code/getAssignment/{assignmentId}/{studentUsername}")
	    public ResponseEntity<List<Code>> getAllCodesForAssignment(@PathVariable("assignmentId") String assignmentId, @PathVariable("studentUsername") String studentUsername) {
	        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	        List<Student> students = this.studentRepository.findByuserName(studentUsername, sortByCreatedAtDesc);
	        Student student = students.get(0);
	        List<Code> codesToReturn = new ArrayList<>();
	        for(Code code : student.getCodes()) {
	        	if(assignmentId.equals(code.getAssignmentId())) {
	        		codesToReturn.add(code);
	        	}
	        }
	        
	        return ResponseEntity.ok().body(codesToReturn);
	    }
	    
	    @GetMapping("/Code/getStudent/{studentUsername}") 
	    ResponseEntity<List<Code>> getAllCodesForStudent(@PathVariable("studentUsername") String studentUsername) {
	    	List<Code> codesToReturn = new ArrayList<>();
	    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	    	List<Student> students = this.studentRepository.findByuserName(studentUsername, sortByCreatedAtDesc);
	        Student student = students.get(0);
	        codesToReturn = student.getCodes();
	    	 return ResponseEntity.ok().body(codesToReturn);
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
		String filePath = request.getParameter("filePath");
		storageService.storeAddPath(file, filePath);
		return ResponseEntity.ok().body("Code uploaded " + file.getOriginalFilename());
	}
	
	@GetMapping("/Code/getFile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, MultipartHttpServletRequest request) {
		String filepath = request.getParameter("filepath");
        Resource file = storageService.loadAsResource(filepath, filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

	@PostMapping("/Code/uploadCode")
	public ResponseEntity<Code> inputCodeforUser(MultipartHttpServletRequest request) {
		String userName = request.getParameter("username");
		String type = request.getParameter("type");
		String runTime = request.getParameter("runTime");
		String givenAssignmentId = request.getParameter("assignmentID");
		Optional<Assignment> assignmentToRun = assignmentRepository.findById(givenAssignmentId);
		String assignmentFilepath = assignmentToRun.get().getFilePath();
		String codePath = assignmentFilepath.replaceFirst("professor-files", "student-files");
		System.out.println("\n\n\n\n\n" + codePath + "\n\n\n\n\n");
		int runTimeNum = Integer.parseInt(runTime);
		LocalTime submissionTime = LocalTime.now();
		Code newCode = new Code();
		newCode.setLanguage(type);
		newCode.setRunTime(runTimeNum);
		newCode.setCodePath(codePath);
		newCode.setSubmissionTime(submissionTime);
		newCode.setAssignmentId(givenAssignmentId);
		newCode.setUserName(userName);
		newCode.setSubmissionAttempts(1);
		// Docker stuff
		newCode.addTestResponse(runCompiler(type, runTimeNum, assignmentFilepath, codePath));
		Code addedCode = codeRepository.save(newCode);
		
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		List<Student> students = this.studentRepository.findByuserName(userName, sortByCreatedAtDesc);
		Student student = students.get(0);
		student.addCode(addedCode);
		this.studentRepository.save(student);
		
		return ResponseEntity.ok().body(addedCode);
	}
	
	@PostMapping("/Code/Create")
    public ResponseEntity<Code> createCode(@Valid @RequestBody Code code) {  
		LocalTime submissionTime = LocalTime.now();
		code.setSubmissionTime(submissionTime);
		Optional<Assignment> assignmentToRun = assignmentRepository.findById(code.getAssignmentId());
		String assignmentFilepath = assignmentToRun.get().getFilePath();
		String codePath = assignmentFilepath.replaceFirst("professor-files", "student-files");
		code.setCodePath(codePath);
		code.setSubmissionAttempts(1);
		code.addTestResponse(runCompiler(code.getLanguage(), code.getRunTime(), assignmentFilepath, codePath));
    	Code addedCode = codeRepository.save(code);
    	System.out.println("\n\n\n\n\n Code Created: " + addedCode.toString() + "\n\n\n\n\n");
    	
    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		List<Student> students = this.studentRepository.findByuserName(code.getUserName(), sortByCreatedAtDesc);
		Student student = students.get(0);
		student.addCode(addedCode);
		this.studentRepository.save(student);
		
        return ResponseEntity.ok().body(addedCode);
    }

	public String runCompiler(String language, int timeLimit, String assignmentFilepath, String codePath) {
		List<File> studentFiles = new ArrayList<>();
		List<File> ProfessorFiles = new ArrayList<>();
		File studentDirLocation = Paths.get(codePath).toFile();
		File professorDirLocation = Paths.get(assignmentFilepath).toFile();
		for (File file : studentDirLocation.listFiles()) {
			System.out.println(studentDirLocation);
			studentFiles.add(file);
			System.out.println(file.getName());
		}
		for (File file : professorDirLocation.listFiles()) {
			System.out.println(professorDirLocation.toString());
			ProfessorFiles.add(file);
			System.out.println(file.getName());
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

    @PutMapping(value="/Code/Update/{id}")
    public ResponseEntity<Code> updateCode(@PathVariable("id") String id,
                                           @Valid @RequestBody Code code) {
    	System.out.println(code.toString());
    	return codeRepository.findById(id)
                .map(codeData -> {
                	
                	LocalTime submissionTime = LocalTime.now();
            		codeData.setSubmissionTime(submissionTime);
            		Optional<Assignment> assignmentToRun = assignmentRepository.findById(code.getAssignmentId());
            		String assignmentFilepath = assignmentToRun.get().getFilePath();
            		String codePath = assignmentFilepath.replaceFirst("professor-files", "student-files");
            		codeData.setCodePath(codePath);
            		codeData.setSubmissionAttempts(code.getSubmissionAttempts()+1);
            		code.addTestResponse(runCompiler(code.getLanguage(), code.getRunTime(), assignmentFilepath, codePath));
                	//might have to delete test responses later
                	codeData.setAssignmentId(code.getAssignmentId());
                	codeData.setGrade(code.getGrade());
                	codeData.setLanguage(code.getLanguage());
                	codeData.setRunTime(code.getRunTime());
                	codeData.setUserName(code.getUserName());
                    Code updatedCode = codeRepository.save(codeData);
                    System.out.println("\n\n\n\n\n Code Updated: " + updatedCode.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedCode);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Code/Delete/{id}")
    public ResponseEntity<String> deleteCode(@PathVariable("id") String id) {
        return codeRepository.findById(id)
                .map(code -> {
                	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
                	//don't need to delete anything in Assignment
                	List<Student> students = this.studentRepository.findByuserName(code.getUserName(),sortByCreatedAtDesc);
                	students.get(0).deleteCode(code);
                	this.studentRepository.save(students.get(0));
                	
                	File codeFile = Paths.get(code.getCodePath()).toFile();
        			FileSystemUtils.deleteRecursively(codeFile);
                	
                    codeRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a code");
                }).orElse(ResponseEntity.notFound().build());
    }

}
