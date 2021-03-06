package compile_io.controllers;

import java.util.List;
import java.util.Optional;

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
import compile_io.mongo.models.Section;
import compile_io.mongo.repositories.CourseRepository;
import compile_io.mongo.repositories.ProfessorRepository;
import compile_io.mongo.repositories.SectionRepository;

@RestController
public class CourseController {
	
	@Autowired
	
    CourseRepository courseRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@GetMapping("/Courses")
	public ResponseEntity<List<Course>> getCourses() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(courseRepository.findAll(sortByCreatedAtDesc));
	}
    
    @GetMapping(value="/Course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }
	
	@PostMapping("/Course/Create")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {  
		if(course.getId() == "-1") {
			course.setId(null);
		}
    	Course addedCourse = courseRepository.save(course);
    	System.out.println("\n\n\n\n\n Course Created: " + addedCourse.toString() + "\n\n\n\n\n");
    	List<String> professorUsernamesInCourse = course.getProfessors();
    	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
    	for(int i = 0; i < professorUsernamesInCourse.size(); i++) {
    			List<Professor> profs = this.professorRepository.findByuserName(professorUsernamesInCourse.get(i), sortByCreatedAtDesc);
    			if(!profs.isEmpty()) {
    				profs.get(0).addCourse(addedCourse);
        			Professor prof = this.professorRepository.save(profs.get(0));
        			System.out.println("\n\n\n\n\n professor updated in Course Creation: " + prof.toString() + "\n\n\n\n\n");
    			} else {
    				Professor professorToAdd = new Professor();
    				professorToAdd.setUserName(professorUsernamesInCourse.get(i));
    				professorToAdd.addCourse(addedCourse);
    				Professor prof = this.professorRepository.save(professorToAdd);
        			System.out.println("\n\n\n\n\n professor created in Course Creation: " + prof.toString() + "\n\n\n\n\n");
    			}
    	}
    	
        return ResponseEntity.ok().body(addedCourse);
    } 
    

    @PutMapping(value="/Course/Update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") String id,
                                           @Valid @RequestBody Course course) {
    	Optional<Course> Potentialcourse = courseRepository.findById(id);
    	Course courseToUpdate = Potentialcourse.get();
    	
    	return courseRepository.findById(id)
                .map(courseData -> {
                	courseData.setProfessors(course.getProfessors());
                	courseData.setCourseName(course.getCourseName());
                	courseData.setDescription(course.getDescription());
                	courseData.setSections(course.getSections());
                	Course updatedcourse = courseRepository.save(courseData);
                    System.out.println("\n\n\n\n\n Course Updated: " + updatedcourse.toString() + "\n\n\n\n\n");
                    
                    List<String> professorUsernamesInCourse = courseToUpdate.getProfessors();
                	Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
                	for(int i = 0; i < professorUsernamesInCourse.size(); i++) {
            			List<Professor> profs = this.professorRepository.findByuserName(professorUsernamesInCourse.get(i), sortByCreatedAtDesc);

                		if (course.getProfessors().contains(professorUsernamesInCourse.get(i))) {
                			if(!profs.isEmpty()) {	
//                				updatedcourse.updateProfessorUserName(profs.get(0).getUserName(),updatedcourse,courseToUpdate);
                			}
                		} else {
                			if(!profs.isEmpty()) {
                				updatedcourse.deleteProfessorUsername(profs.get(0).getUserName());
                			}
                		}
                	}
                	List<String> professorUsernamesInNewCourse = course.getProfessors();
                	for(int i = 0; i < professorUsernamesInNewCourse.size(); i++) {
                		if (!professorUsernamesInCourse.contains(professorUsernamesInNewCourse.get(i))) {
                			//add this course in this prof
                			List<Professor> profs = this.professorRepository.findByuserName(professorUsernamesInNewCourse.get(i), sortByCreatedAtDesc);
                			if(!profs.isEmpty()) {
                				updatedcourse.addProfessorUsername(professorUsernamesInNewCourse.get(i));
                			}
                		}
                	}
                	Course updatedCourse = this.courseRepository.save(updatedcourse);
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Course/Delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") String id) {
        return courseRepository.findById(id)
                .map(course -> {
                	for(String professorUsername : course.getProfessors()) {
                		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
                		List<Professor> professors = this.professorRepository.findByuserName(professorUsername, sortByCreatedAtDesc);
                		for(Course courseForProf : professors.get(0).getCourses()) {
                			if(courseForProf.equals(course)) {
                				professors.get(0).deleteCourse(course);
                				this.professorRepository.save(professors.get(0));
                			}
                		}
                	}
                	for(Section section : course.getSections()) {
                		section.deleteSection(section.getId());
                		this.sectionRepository.save(section);
                	}
                	courseRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a course");
                }).orElse(ResponseEntity.notFound().build());
    }

}
