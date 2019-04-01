package compile_io.controllers;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.core.io.Resource;

import compile_io.mongo.models.Assignment;
import compile_io.mongo.models.Course;
import compile_io.mongo.models.Section;
import compile_io.mongo.repositories.AssignmentRepository;
import compile_io.mongo.repositories.CourseRepository;
import compile_io.mongo.repositories.SectionRepository;
import compile_io.storage.StorageService;

@RestController
//@RequestMapping("/Assignment")
public class AssignmentController {

	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	CourseRepository courseRepository;

	private final StorageService storageService;

	@Autowired
	public AssignmentController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/Assignment")
	public ResponseEntity<List<Assignment>> getAllAssignments() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return ResponseEntity.ok().body(assignmentRepository.findAll(sortByCreatedAtDesc));
	}

	@GetMapping(value = "/Assignment/{id}")
	public ResponseEntity<Assignment> getassignmentById(@PathVariable("id") String id) {
		return assignmentRepository.findById(id).map(assignment -> ResponseEntity.ok().body(assignment))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/Assignmnet/uploadFile")
	public ResponseEntity<String> uploadFile(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("file");
		String filePath = request.getParameter("filePath");
		storageService.storeAddPath(file, filePath);

		return ResponseEntity.ok().body("Assignment uploaded " + file.getOriginalFilename());
	}
	
	@GetMapping("/Assignment/getFile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, MultipartHttpServletRequest request) {
		String filepath = request.getParameter("filepath");
        Resource file = storageService.loadAsResource(filepath, filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

	@PostMapping("/Assignment/Create")
	public ResponseEntity<List<Assignment>> createassignment(@Valid @RequestBody Assignment assignment) {
		List<Assignment> addedAssignments = new ArrayList<>();
		
		for (String sectionId : assignment.getSectionIds()) {
			String directory = createFilePathInServer(sectionId, assignment.getAssignmentName(),
					assignment.getCreatedByUsername());
			assignment.setFilePath(directory);
			Assignment addedAssignment = assignmentRepository.save(assignment);
			addedAssignments.add(addedAssignment);
			System.out.println("\n\n\n\n\n Assignment Created: " + addedAssignment.toString() + "\n\n\n\n\n");
			Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
			Section section = sectionToFind.get();
			section.addAssignment(addedAssignment);
			Section addedSection = this.sectionRepository.save(section);
			System.out.println(
					"\n\n\n\n\n Section Updated in Assignment Create: " + addedSection.toString() + "\n\n\n\n\n");
		}
		return ResponseEntity.ok().body(addedAssignments);
	}

	public String createFilePathInServer(String sectionId, String AssignmentName, String createdByUser) {
		String directory = "upload-dir/";
		Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
		Section section = sectionToFind.get();
		Optional<Course> courseToFind = this.courseRepository.findById(section.getCourseId());
		Course course = courseToFind.get();
		
		String year = Integer.toString(section.getYear());
		String term = Integer.toString(section.getTerm());
		String courseName = course.getCourseName().replaceAll(" ", "_").toLowerCase();
		String sectionNumber = Integer.toString(section.getSectionNumber());
		String assignmentName = AssignmentName.replaceAll(" ", "_").toLowerCase();
		String user = createdByUser.replaceAll(" ", "_").toLowerCase();
		
		directory = "upload-dir/" + year + "/" + term
				+ "/" + courseName + "/"
				+ sectionNumber + "/" + assignmentName
				+ "/professor-files/" + user;
		
		System.out.println("\n\n\n\n\n" + directory + "\n\n\n\n");

		return directory;
	}

	@PutMapping(value="/Assignment/Update/{id}")
    public ResponseEntity<List<Assignment>> updateAssignment(@PathVariable("id") String id,
                                           @Valid @RequestBody Assignment assignment) {
		List<Assignment> updatedAssignments = new ArrayList<>();
    	if(assignment.isAvailableToOtherSections()) {
    		for(String sectionId : assignment.getSectionIds()) {
    			Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
       		 	Section section = sectionToFind.get();
       		 	for(Assignment assignmentToUpdate: section.getAssignments()) {
       		 		Assignment updatedAssignment = this.updateOneAssignment(assignmentToUpdate,sectionId).getBody();
       		 		updatedAssignments.add(updatedAssignment);
       		 	}
    		}
//    		Optional <Course> courseToFind = this.courseRepository.findById(assignment.getCourseId());
//    		Course course = courseToFind.get();
//    		List<Section> sections = course.getSections();
//    		for(Section section : sections) {
//       		 	for(Assignment assignmentToUpdate: section.getAssignments()) {
//       		 		updatedAssignment = this.updateOneAssignment(assignmentToUpdate,section.getId()).getBody();
//       		 	}
//    		}
    	} else {
    		String sectionId = assignment.getSectionIds().get(0);
    		assignment.setSectionIds(new ArrayList<>());
    		assignment.getSectionIds().add(sectionId);
    		Assignment updatedAssignment = this.updateOneAssignment(assignment, sectionId).getBody();
    		updatedAssignments.add(updatedAssignment);
    	}
    	return ResponseEntity.ok().body(updatedAssignments);

	}

	public ResponseEntity<Assignment> updateOneAssignment(Assignment assignment, String givenSectionId) {
    	Optional<Assignment> assignmentToFind = this.assignmentRepository.findById(assignment.getId());
    	Assignment assignmentToUpdate = assignmentToFind.get();
    	System.out.println(assignment.toString());
    	return assignmentRepository.findById(assignment.getId())
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
                    assignmentData.setCreatedByUsername(assignment.getCreatedByUsername());
                    assignmentData.setSectionIds(assignment.getSectionIds());
                    assignmentData.setCourseId(assignment.getCourseId());
                    assignmentData.setAvailableToOtherSections(assignment.isAvailableToOtherSections());
                    String directory = createFilePathInServer(givenSectionId, assignment.getAssignmentName(),
        					assignment.getCreatedByUsername());
        			assignmentData.setFilePath(directory);
                    
                    Assignment updatedAssignment = assignmentRepository.save(assignmentData);
                    System.out.println("\n\n\n\n\n Assignment Updated: " + updatedAssignment.toString() + "\n\n\n\n\n");
                    
                    for(String sectionId : assignmentToUpdate.getSectionIds()) {
                    	 Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
                		 Section section = sectionToFind.get();
                		 if(section.getAssignments().contains(assignmentToUpdate)) {
                			 section.deleteAssignment(assignmentToUpdate);
                			 section.addAssignment(updatedAssignment);
                			 Section updatedSection = this.sectionRepository.save(section);
                			 System.out.println("\n\n\n\n\n Section Updated in assignment controller: " + updatedSection.toString());
                		 } else {
                			 section.deleteAssignment(assignmentToUpdate);
                			 Section updatedSection = this.sectionRepository.save(section);
                			 System.out.println("\n\n\n\n\n Section Updated removed: " + updatedSection.toString() + "\n\n\n\n\n");
                		 }
                    }
                    
                    for(String sectionId : updatedAssignment.getSectionIds() ) {
                    	if(!updatedAssignment.getSectionIds().contains(sectionId)) {
                    		Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
                   		 	Section section = sectionToFind.get();
                   		 	section.addAssignment(updatedAssignment);
                   		 	Section updatedSection = this.sectionRepository.save(section);
                   		 System.out.println("\n\n\n\n\n Section Updated second for loop: " + updatedSection.toString() + "\n\n\n\n\n");
                    	}
                    }
                    return ResponseEntity.ok().body(updatedAssignment);
        		}).orElse(ResponseEntity.notFound().build()); 
    }

	@DeleteMapping(value = "/Assignment/Delete/{id}")
	public ResponseEntity<String> deleteAssignment(@PathVariable("id") String id) {
		return assignmentRepository.findById(id).map(assignment -> {
			File assignmentFile = Paths.get(assignment.getFilePath()).toFile();
			FileSystemUtils.deleteRecursively(assignmentFile);
			
			//might not have to delete assignments in sections because of DBRef
			
			for(String sectionIds : assignment.getSectionIds()) {
				//delete assignments from section
				Optional<Section> sectionToFind = this.sectionRepository.findById(sectionIds);
				Section section = sectionToFind.get();
				section.deleteAssignment(assignment);
				this.sectionRepository.save(section);
			}
			
			assignmentRepository.deleteById(id);
			return ResponseEntity.ok().body("Deleted a Assignment");
			
		}).orElse(ResponseEntity.notFound().build());
	}
}
