package compile_io.controllers;

import java.util.ArrayList;
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

import compile_io.mongo.models.Course;
import compile_io.mongo.models.Professor;
import compile_io.mongo.repositories.CourseRepository;
import compile_io.mongo.repositories.ProfessorRepository;

@RestController
public class ProfessorController{
	@Autowired 
	public ProfessorRepository professorRepository;
	
	@Autowired 
	public CourseRepository courseRepository;
	
	@GetMapping("/Professors")
	public ResponseEntity<List<Professor>> getProfessors() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		List<Professor> Professors = professorRepository.findAll(sortByCreatedAtDesc);
        return ResponseEntity.ok().body(Professors);
	}
    
    @GetMapping(value="/Professor/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable("id") String id) {
        return professorRepository.findById(id)
                .map(professor -> ResponseEntity.ok().body(professor))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping(value="/Professor/Username/{username}")
    public ResponseEntity<List<Professor>> getProfessorByUsername(@PathVariable("username") String username) {
    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
    	List<Professor> professors = new ArrayList<Professor>();
        professors = professorRepository.findByuserName(username, sortByCreatedAtDesc);
        return ResponseEntity.ok().body(professors);
    }
	
	@PostMapping("/Professor/Create")
    public ResponseEntity<Professor> createProfessor(@Valid @RequestBody Professor professor) {   
    	Professor createdProf = professorRepository.save(professor);
    	System.out.println("\n\n\n\n\n professor Created: " + createdProf.toString() + "\n\n\n\n\n");
        return ResponseEntity.ok().body(createdProf);
    } 
    

    @PutMapping(value="/Professor/Update/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") String id,
                                           @Valid @RequestBody Professor professor) {
    	System.out.println("\n\n\n\n\n Updating this professor: " +professor.toString()+ "\n\n\n\n\n");
    	return professorRepository.findById(id)
                .map(professorData -> {
                	professorData.setCourses(professor.getCourses());
                	professorData.setName(professor.getName());
                	professorData.setUserName(professor.getUserName());
                    Professor updatedProfessor = professorRepository.save(professorData);
                    System.out.println("\n\n\n\n\n Professor Updated: " + updatedProfessor.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedProfessor);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Professor/Delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable("id") String id) {
        return professorRepository.findById(id)
                .map(professor -> {
                	for(Course course : professor.getCourses()) {
                		course.deleteProfessorUsername(professor.getUserName());
                		this.courseRepository.save(course);
                	}
                    professorRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a professor");
                }).orElse(ResponseEntity.notFound().build());
    }

}
