package compile_io.controllers;

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
import compile_io.mongo.repositories.ProfessorRepository;
import compile_io.storage.StorageService;

@RestController
//@RequestMapping("/Assignment")
public class AssignmentController {
	
	@Autowired
    AssignmentRepository assignmentRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	private final StorageService storageService;
    private String fileName;

	@Autowired
	public AssignmentController(StorageService storageService) {
		this.storageService = storageService;
	}
	

    @GetMapping("/Assignment")
    public  ResponseEntity<List<Assignment>> getAllAssignments() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(assignmentRepository.findAll(sortByCreatedAtDesc));
    }
    
    @GetMapping("/Assignment/getCourse/{courseName}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsForCourse(@PathVariable("courseName") String courseName) {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(assignmentRepository.findBycourseName(courseName, sortByCreatedAtDesc));
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
		String courseName = request.getParameter("courseName");
		String assignmentName = request.getParameter("assignmentName");
		String userName = request.getParameter("userName");
		storageService.storeAddPath(file, "professor", courseName, assignmentName, userName);
		this.fileName = file.getOriginalFilename();
		
		return ResponseEntity.ok().body("uploaded " + file.getOriginalFilename() );
    }
    
    @PostMapping("/Assignment/Create")
    public ResponseEntity<Assignment> createassignment(@Valid @RequestBody Assignment assignment) {   
    	Path professorDir = Paths.get("upload-dir/" + 
				assignment.getCourseName().replaceAll(" ", "_").toLowerCase() + "/" +
				assignment.getAssignmentName().replaceAll(" ", "_").toLowerCase() + 
				"/professor-files/" +
				assignment.getCreatedByUsername().replaceAll(" ", "_").toLowerCase());
    	assignment.setFilePath(professorDir.toString());
    	Assignment addedAssignment = assignmentRepository.save(assignment);
    	System.out.println("\n\n\n\n\n Assignment Created: " +  addedAssignment.toString() + "\n\n\n\n\n");
        return ResponseEntity.ok().body(addedAssignment);
    } 
    

    @PutMapping(value="/Assignment/Update/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable("id") String id,
                                           @Valid @RequestBody Assignment assignment) {
    	System.out.println(assignment.toString());
    	return assignmentRepository.findById(id)
                .map(assignmentData -> {
                    assignmentData.setAssignmentName(assignment.getAssignmentName());
                    assignmentData.setTimeout(assignment.getTimeout());
                    assignmentData.setLanguage(assignment.getLanguage());
                    assignmentData.setSize(assignment.getSize());
                    assignmentData.setTries(assignment.getTries());
                    assignmentData.setStartDate(assignment.getStartDate());
                    assignmentData.setStartTime(assignment.getStartTime());
                    assignmentData.setEndDate(assignment.getEndDate());
                    assignmentData.setEndTime(assignment.getEndTime());  
//                    assignmentData.setFilePath(professorDir.toString());
                    assignmentData.setCourseName(assignment.getCourseName());
                    Assignment updatedAssignment = assignmentRepository.save(assignmentData);
                    System.out.println("\n\n\n\n\n Assignment Updated: " + updatedAssignment.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedAssignment);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Assignment/Delete/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable("id") String id) {
    	return assignmentRepository.findById(id)
                .map(assignment -> {
                	assignmentRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a course");
                }).orElse(ResponseEntity.notFound().build());
    }
}
