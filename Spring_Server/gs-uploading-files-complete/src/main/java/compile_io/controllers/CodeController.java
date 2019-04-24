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
import org.springframework.web.bind.annotation.RequestParam;
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
	    public ResponseEntity<Code> getAllCodesForAssignment(@PathVariable("assignmentId") String assignmentId, @PathVariable("studentUsername") String studentUsername) {
	        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	        List<Student> students = this.studentRepository.findByUserName(studentUsername, sortByCreatedAtDesc);
	        Code codeToReturn = null;
	        if(!students.isEmpty()) {
	        	Student student = students.get(0);
	 	        for(Code code : student.getCodes()) {
	 	        	if(assignmentId.equals(code.getAssignmentId())) {
	 	        		codeToReturn = this.updateCode(code.getId(), code).getBody();
	 	        		return ResponseEntity.ok().body(codeToReturn);
	 	        	}
	 	        }
	        } 
	       
	        
	        return ResponseEntity.ok().body(codeToReturn);
	    }
	    
	    @GetMapping("/Code/getStudent/{studentUsername}") 
	    ResponseEntity<List<Code>> getAllCodesForStudent(@PathVariable("studentUsername") String studentUsername) {
	    	List<Code> codesToReturn = new ArrayList<>();
	    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
	    	List<Student> students = this.studentRepository.findByUserName(studentUsername, sortByCreatedAtDesc);
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

	@PostMapping("/Code/runCode")
	public ResponseEntity<Code> runCode(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("file");
		String codeId = request.getParameter("codeId");
		Optional<Code> codeToFind = this.codeRepository.findById(codeId);
		Code code;
		if(!codeToFind.isPresent()) {
			return ResponseEntity.notFound().build();
			
		} 
		code = codeToFind.get();
		code.setFileName(file.getOriginalFilename());
		int timeLimit = code.getRunTime();
//		String language = code.getLanguage();
		String language = "java"; //NEED TO CHANGE TO THIS ^^^^^^^ BEFORE DEPLOYING, FOR TESTING PURPOSES ONLY
		String codeFilePath = code.getCodePath();
		LocalTime submissionTime = LocalTime.now();
		code.setSubmissionTime(submissionTime);
		storageService.storeAddPath(file, codeFilePath);
		String assignmentFilepath = codeFilePath.replaceFirst("professor-files", "student-files");
		code.addTestResponse(this.runCompiler(language, timeLimit, assignmentFilepath, codeFilePath));
		code.setSubmissionAttempts(code.getSubmissionAttempts()+1);
		Code addedCode = codeRepository.save(code);
    	System.out.println("\n\n\n\n\n Code Submitted: " + addedCode.toString() + "\n\n\n\n\n");
		return ResponseEntity.ok().body(code);
	}
	
	@PostMapping("/Code/getFile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, @RequestParam(value = "filepath") String filepath) {
		Resource file = storageService.loadAsResource(filepath, filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
	
	@PostMapping("/Code/Create")
    public ResponseEntity<Code> createCode(@Valid @RequestBody Code code) {  
		Optional<Assignment> assignmentToFind = assignmentRepository.findById(code.getAssignmentId());
		Assignment assignment = assignmentToFind.get();
		String assignmentFilepath = assignment.getFilePath();
		String codePath = assignmentFilepath.replaceFirst("professor-files", "student-files");
		code.setCodePath(codePath);
    	Code addedCode = codeRepository.save(code);
    	System.out.println("\n\n\n\n\n Code Created: " + addedCode.toString() + "\n\n\n\n\n");
    	
    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		List<Student> students = this.studentRepository.findByUserName(code.getUserName(), sortByCreatedAtDesc);
		Student student;
		if(!students.isEmpty()) {
			student = students.get(0);
		} else {
			student = new Student();
			student.setUserName(code.getUserName());
			student.setSectionIds(assignment.getSectionIds());
		}
		student.addCode(addedCode);
		Student addedStudent = this.studentRepository.save(student);
		System.out.println("\n\n\n\n\n Student added or updated in CodeController: " + addedStudent.toString() + "\n\n\n\n\n");
		
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
			AbstractBuilder builder = builderFactory.getBuilder(language, studentFiles, ProfessorFiles, codePath);
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
            		Optional<Assignment> assignmentToFind = assignmentRepository.findById(code.getAssignmentId());
            		Assignment assignment = new Assignment();
            		Code updatedCode = new Code();
            		if(assignmentToFind.isPresent()) {
            			assignment = assignmentToFind.get();
            			String assignmentFilepath = assignment.getFilePath();
                		String codePath = assignmentFilepath.replaceFirst("professor-files", "student-files");
                		codeData.setCodePath(codePath);
//                		codeData.setSubmissionAttempts(code.getSubmissionAttempts()+1);
//                		code.addTestResponse(runCompiler(code.getLanguage(), code.getRunTime(), assignmentFilepath, codePath));
                    	//might have to delete test responses later
                    	codeData.setAssignmentId(assignment.getId());
                    	codeData.setGrade(code.getGrade());
                    	codeData.setLanguage(assignment.getLanguage());
                    	codeData.setRunTime(assignment.getTimeout());
//                    	codeData.setSubmissionAttempts(code.getSubmissionAttempts());
                    	codeData.setUserName(code.getUserName());
//                    	codeData.setFileName(code.getFileName());
                        updatedCode = codeRepository.save(codeData);
                        System.out.println("\n\n\n\n\n Code Updated: " + updatedCode.toString() + "\n\n\n\n\n");
            		} else {
            			ResponseEntity.notFound().build();
            		}
            		
                    return ResponseEntity.ok().body(updatedCode);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Code/Delete/{id}")
    public ResponseEntity<String> deleteCode(@PathVariable("id") String id) {
        return codeRepository.findById(id)
                .map(code -> {
                	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
                	//don't need to delete anything in Assignment
                	List<Student> students = this.studentRepository.findByUserName(code.getUserName(),sortByCreatedAtDesc);
                	students.get(0).deleteCode(code);
                	this.studentRepository.save(students.get(0));
                	
                	File codeFile = Paths.get(code.getCodePath()).toFile();
        			FileSystemUtils.deleteRecursively(codeFile);
                	
                    codeRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a code");
                }).orElse(ResponseEntity.notFound().build());
    }

}
