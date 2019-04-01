package compile_io.controllers;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import compile_io.mongo.models.Code;
import compile_io.mongo.models.Section;
import compile_io.mongo.models.Student;
import compile_io.mongo.repositories.StudentRepository;
import compile_io.mongo.repositories.CodeRepository;
import compile_io.mongo.repositories.SectionRepository;

@RestController
public class StudentController{
	@Autowired 
	public StudentRepository studentRepository;
	
	@Autowired 
	public SectionRepository sectionRepository;
	
	@Autowired 
	public CodeRepository codeRepository;
	
	@GetMapping("/Students")
	public ResponseEntity<List<Student>> getStudents() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(studentRepository.findAll(sortByCreatedAtDesc));
	}
    
    @GetMapping(value="/Student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping(value="/Student/Username/{username}")
    public ResponseEntity<Student> getProfessorByUsername(@PathVariable("username") String username) {
    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        List<Student> students = studentRepository.findByuserName(username, sortByCreatedAtDesc);
        if(students.isEmpty()) {
        	return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(students.get(0));
    }
	@PostMapping("/Student/Create")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {   
    	Student addedStudent = studentRepository.save(student);
    	System.out.println("\n\n\n\n\n Student Created: " + addedStudent.toString() + "\n\n\n\n\n");
    	
    	if(!addedStudent.getSectionIds().isEmpty()) {
    	for(String sectionId : addedStudent.getSectionIds()) {
    		Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
    		Section section = sectionToFind.get();
    		section.addStudentUsername(student.getUserName());
    	}
    	}
    	
        return ResponseEntity.ok().body(addedStudent);
    } 
    

    @PutMapping(value="/Student/Update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id,
                                           @Valid @RequestBody Student student) {
    	Optional<Student> studentToFind = this.studentRepository.findById(student.getId());
    	Student studentToUpdate = studentToFind.get();//change
    	System.out.println(student.toString());
    	return studentRepository.findById(id)
                .map(studentData -> {
                	studentData.setName(student.getName());
                	studentData.setCodes(student.getCodes());
                	studentData.setSectionIds(student.getSectionIds());
                	student.setUserName(student.getUserName());
                	Student updatedStudent = studentRepository.save(studentData);
                    System.out.println("\n\n\n\n\n Student Updated: " + updatedStudent.toString() + "\n\n\n\n\n");
                    for(String sectionId : studentToUpdate.getSectionIds()) {
                   	 Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
               		 Section section = sectionToFind.get();
               		 if(section.getStudentUsernames().contains(studentToUpdate.getUserName())) {
               			 section.deleteStudentUsername(studentToUpdate.getUserName());
               			section.addStudentUsername(studentToUpdate.getUserName());
               			 Section updatedSection = this.sectionRepository.save(section);
               			 System.out.println("\n\n\n\n\n Section Updated in Student controller: " + updatedSection.toString());
               		 } else {
               			section.deleteStudentUsername(studentToUpdate.getUserName());
               			 Section updatedSection = this.sectionRepository.save(section);
               			 System.out.println("\n\n\n\n\n Section Updated in Student controller removed: " + updatedSection.toString() + "\n\n\n\n\n");
               		 }
                   }
                   
                   for(String sectionId : updatedStudent.getSectionIds() ) {
                   	if(!updatedStudent.getSectionIds().contains(sectionId)) {
                   		Optional<Section> sectionToFind = this.sectionRepository.findById(sectionId);
                  		 	Section section = sectionToFind.get();
                  		 	section.addStudentUsername(updatedStudent.getUserName());
                  		 	Section updatedSection = this.sectionRepository.save(section);
                  		 System.out.println("\n\n\n\n\n Section Updated in Student controller second for loop: " + updatedSection.toString() + "\n\n\n\n\n");
                   	}
                   }
                    return ResponseEntity.ok().body(updatedStudent);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Student/Delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String id) {
        return studentRepository.findById(id)
                .map(student -> {
                	for(Code code : student.getCodes()) {
                		File codeFile = Paths.get(code.getCodePath()).toFile();
            			FileSystemUtils.deleteRecursively(codeFile);
                		codeRepository.deleteById(code.getId());
                	}
                	
                	for(String sectionIds : student.getSectionIds()) {
                		Optional<Section> sectionToFind = this.sectionRepository.findById(sectionIds);
                		Section section = sectionToFind.get();
                		section.deleteStudentUsername(student.getUserName());
                		this.sectionRepository.save(section);
                	}
                	studentRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a Student");
                }).orElse(ResponseEntity.notFound().build());
    }

}
