package compile_io.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import compile_io.mongo.models.Assignment;
import compile_io.mongo.repositories.AssignmentRepository;
import compile_io.storage.StorageService;

@RestController
//@RequestMapping("/Assignment")
public class AssignmentController {
	
	@Autowired
    AssignmentRepository assignmentRepository;
	
	private final StorageService storageService;
    //Windows
	private Path professorDir = Paths.get("upload-dir\\professor-files");
	//Ubuntu
//	private Path professorDir = Paths.get("upload-dir/professor-files");
	
	private final int MAX_FILE_SIZE = 50000000;

	@Autowired
	public AssignmentController(StorageService storageService) {
		this.storageService = storageService;
	}
	

    @GetMapping("/Assignment")
    public List<Assignment> getAllAssignments() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return assignmentRepository.findAll(sortByCreatedAtDesc);
    }
    
    @GetMapping("/Assignment/getCourse/{courseName}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsForCourse(@PathVariable("courseName") String courseName) {
//        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
    	System.out.println("\n\n\n\n\n\n INSIDE GET ALL ASSIGNMENTS FOR COURSE  \n\n\n\n\n\n ");
        return ResponseEntity.ok().body(assignmentRepository.findBycourseName(courseName));
    }
    
    @GetMapping(value="/Assignment/{id}")
    public ResponseEntity<Assignment> getassignmentById(@PathVariable("id") String id) {
        return assignmentRepository.findById(id)
                .map(assignment -> ResponseEntity.ok().body(assignment))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/Assignmnet/uploadFile")
	public ResponseEntity<String> uploadFile(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("file");
		storageService.storeAddPath(file, "professor");
		
		//If absolute path is necessary then this commented out code is needed
/*
		//Windows
		String workingDir = System.getProperty("user.dir") + "\\upload-dir\\professor-files\\" + this.file.getOriginalFilename();
		//Ubuntu
//		String workingDir = System.getProperty("user.dir") + "/upload-dir/professor-files/" + this.file.getOriginalFilename();
		workingDir = workingDir.substring(2);
		System.out.println("\n\n\n\n\nWorking Directory = " + workingDir + "\n\n\n\n\n");
//		File fileToUpload = new File(workingDir);
//		this.filepath = workingDir;
 * 
 */
		//Windows
		professorDir = Paths.get("upload-dir\\professor-files\\" + file.getOriginalFilename());
		//Ubuntu
//		professorDir = Paths.get("upload-dir/professor-files/" + file.getOriginalFilename());
		return ResponseEntity.ok().body("uploaded " + file.getOriginalFilename() );
    }
    
    @PostMapping("/Assignment")
    public ResponseEntity<String> createassignment(@Valid @RequestBody Assignment assignment) {    	
    	assignment.setFilePath(professorDir.toString());
    	System.out.println("\n\n\n\n\n" + assignment.toString() + "\n\n\n\n\n");
    	assignmentRepository.save(assignment);
        return ResponseEntity.ok().body("uploaded assignment: " + assignment.toString());
    } 
    

    @PutMapping(value="/Assignment/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable("id") String id,
                                           @Valid @RequestBody Assignment assignment) {
    	return assignmentRepository.findById(id)
                .map(assignmentData -> {
                    assignmentData.setAssignmentName(assignment.getassignmentName());
                    assignmentData.setTimeout(assignment.getTimeout());
                    assignmentData.setLanguage(assignment.getLanguage());
                    assignmentData.setSize(assignment.getSize());
                    assignmentData.setTries(assignment.getTries());
                    assignmentData.setStartDate(assignment.getStartDate());
                    assignmentData.setStartTime(assignment.getStartTime());
                    assignmentData.setEndDate(assignment.getEndDate());
                    assignmentData.setEndTime(assignment.getEndTime());  
                    assignmentData.setFilePath(professorDir.toString());
                    assignmentData.setCourseName(assignment.getCourseName());
                    Assignment updatedAssignment = assignmentRepository.save(assignmentData);
                    return ResponseEntity.ok().body(updatedAssignment);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Assignment/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable("id") String id) {
        return assignmentRepository.findById(id)
                .map(assignment -> {
                    assignmentRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
	
	
	
	
	

//	private StorageService storageService;
//	private String fileName;
//
//	@Autowired
//	AssignmentRepository assignmentRepo;
//
//	@Autowired
//	public AssignmentController(StorageService storageService) {
//		this.storageService = storageService;
//	}
//
////	
////	@GetMapping("/{className}")
////	public String[] getAssignments(@PathVariable String className) {
////		String[] temp = { "Hwk1", "Hwk2", "Hwk3", "Hwk4" };
////		return temp;
////	}
////	
//	@GetMapping("/Assignment")
//	public List<Assignment> getAllAssignments() {
//
//		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
//		return AssignmentRepository.findAll(sortByCreatedAtDesc);
//	}
//
//	@GetMapping(value="/Assignment/{Course}/{oldAssignmentName}")
//	    public ResponseEntity<Assignment> getassignmentById(String name) {
//	        return AssignmentRepository.findByName(name)
//	                .map(assignment -> ResponseEntity.ok().body(assignment))
//	                .orElse(ResponseEntity.notFound().build());
//	    }}}
//}
//
//@PostMapping("Assignment/{AssignmentName}/addAssignment")
//	public Assignment addAssigment(@RequestBody Assignment assignment) {
//		return assignment;}
//		
//		
//		assignment.file = RequestBody.getFile("file");
//		assignment.newAssignmentName = request.getParameter("oldAssignmentName");
//		assignment.timeout = request.getParameter("timeout");
//		assignment.language = request.getParameter("language");
//		assignment.size = request.getParameter("size");
//		assignment.tries = request.getParameter("tries");
//		assignment.coursename = request.getParameter("coursename");
//		
//		
//		storageService.store(assignment.file);                
//		this.fileName = assignment.file.getName();
//		this.fileName = assignment.file.getName();
//		
//		String workingDir = System.getProperty("user.dir") + "/upload-dir/" + fileName;
//		workingDir = workingDir.substring(2);
//		System.out.println("Working Directory = " + workingDir);
//		
//		
//		
//		
//		
//		
//		Assignment newAssignment = new Assignment();
//		
//		
//		
//		
//		
//		assignmentRepo.save(newAssignment);
//
//		return true;
//	}
//	
//	@PutMapping("Assignment/{AssignmentName}/updateAssignment")
//	public boolean updateAssigment(MultipartHttpServletRequest request) {
//		MultipartFile file = request.getFile("file");
//		String oldAssignmentName = request.getParameter("oldAssignmentName");
//		String newAssignmentName = request.getParameter("newAssignmentName");
//		String timeout = request.getParameter("timeout");
//		String language = request.getParameter("language");
//		String size = request.getParameter("size");
//		String tries = request.getParameter("tries");
//		String coursename = request.getParameter("coursename");
//		
//		
//		
//		return false;
//	}
//	
//
//	@ExceptionHandler(StorageFileNotFoundException.class)
//	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//		return ResponseEntity.notFound().build();
//	}
//	
//	public class AddAssignmentJson {
//		protected MultipartFile file;
//		protected String newAssignmentName;
//		protected String timeout; 
//		protected String language; 
//		protected String size; 
//		protected String tries; 
//		protected String coursename;
//	}
}
