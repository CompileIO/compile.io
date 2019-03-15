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

import compile_io.mongo.models.Course;
import compile_io.mongo.models.Professor;
import compile_io.mongo.repositories.CourseRepository;
import compile_io.mongo.repositories.ProfessorRepository;

@RestController
public class CourseController {
	
	@Autowired
    CourseRepository courseRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	//As soon as we don't need this we can get rid of this api call
	@GetMapping("/courses")
	public String[] getClasses() {
		String[] temp = { "CSSE120", "CSSE220", "CSSE230", "CSSE241" };
		return temp;
	}
	
	@GetMapping("/Courses")
	public List<Course> getCourses() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return courseRepository.findAll(sortByCreatedAtDesc);
	}
    
    @GetMapping(value="/Course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }
	
    @GetMapping("/Course/getInstructorCourses/{instructorName}")
    public ResponseEntity<List<Course>> getAllCoursesForProfessor(@PathVariable("instructorName") String instructorName) {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(courseRepository.findByInstructor(instructorName, sortByCreatedAtDesc));
    }
	
	@PostMapping("/Course/Create")
    public ResponseEntity<String> createCourse(@Valid @RequestBody Course course) {  
    	courseRepository.save(course);
    	System.out.println("\n\n\n\n\n Course Created: " + course.toString() + "\n\n\n\n\n");
        return ResponseEntity.ok().body("uploaded Course: " + course.toString());
    } 
    

    @PutMapping(value="/Course/Update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") String id,
                                           @Valid @RequestBody Course course) {
    	System.out.println(course.toString());
    	return courseRepository.findById(id)
                .map(courseData -> {
                	courseData.setCrn(course.getCrn());
                	courseData.setCourseName(course.getCourseName());
                	courseData.setInstructor(course.getInstructor());
                	courseData.setSectionNumber(course.getSectionNumber());
                	courseData.setStudents(course.getStudents());
                    Course updatedCourse = courseRepository.save(courseData);
                    System.out.println("\n\n\n\n\n Course Updated: " + updatedCourse.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Course/Delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") String id) {
        return courseRepository.findById(id)
                .map(course -> {
                	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
                	List<Professor> newProfessors = professorRepository.findByuserName(course.getInstructor().getUserName(), sortByCreatedAtDesc);
                	Professor newProf = newProfessors.get(0);
                	newProf.deleteCourse(course);
                	professorRepository.save(newProf);
                    courseRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a course");
                }).orElse(ResponseEntity.notFound().build());
    }

}
