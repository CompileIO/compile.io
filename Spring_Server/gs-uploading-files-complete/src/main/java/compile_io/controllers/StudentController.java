package compile_io.controllers;

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

import compile_io.mongo.models.Professor;
import compile_io.mongo.models.Student;
import compile_io.mongo.repositories.StudentRepository;

@RestController
public class StudentController{
	@Autowired 
	public StudentRepository studentRepository;
	
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
    public ResponseEntity<String> createStudent(@Valid @RequestBody Student student) {   
    	System.out.println("\n\n\n\n\n Student Created: " + student.toString() + "\n\n\n\n\n");
    	studentRepository.save(student);
        return ResponseEntity.ok().body("uploaded Student: " + student.toString());
    } 
    

    @PutMapping(value="/Student/Update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id,
                                           @Valid @RequestBody Student student) {
    	System.out.println(student.toString());
    	return studentRepository.findById(id)
                .map(studentData -> {
                	studentData.setName(student.getName());
                	studentData.setCodes(student.getCodes());
                	studentData.setSections(student.getSections());
                	student.setUserName(student.getUserName());
                	Student updatedStudent = studentRepository.save(studentData);
                    System.out.println("\n\n\n\n\n Student Updated: " + updatedStudent.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedStudent);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Student/Delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id) {
        return studentRepository.findById(id)
                .map(student -> {
                	studentRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a Student");
                }).orElse(ResponseEntity.notFound().build());
    }

}
