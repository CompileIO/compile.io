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

import compile_io.mongo.models.Section;
import compile_io.mongo.models.Student;
import compile_io.mongo.models.Course;
import compile_io.mongo.models.Professor;
import compile_io.mongo.repositories.SectionRepository;
import compile_io.mongo.repositories.StudentRepository;
import compile_io.mongo.repositories.AssignmentRepository;
import compile_io.mongo.repositories.CourseRepository;

@RestController
public class SectionController {

@Autowired
    SectionRepository sectionRepository;
@Autowired
    CourseRepository courseRepository;
@Autowired
    AssignmentRepository assignmentRepository;
@Autowired
    StudentRepository studentRepository;
  
@GetMapping("/Sections")
public ResponseEntity<List<Section>> getSections() {
 Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(sectionRepository.findAll(sortByCreatedAtDesc));
}
    
    @GetMapping(value="/Section/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable("id") String id) {
        return sectionRepository.findById(id)
                .map(section -> ResponseEntity.ok().body(section))
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/section/getInstructorsections/{instructorName}")
//    public ResponseEntity<List<Section>> getAllsectionsForProfessor(@PathVariable("instructorName") String instructorName) {
//        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
//        return ResponseEntity.ok().body(sectionRepository.findByInstructor(instructorName, sortByCreatedAtDesc));
//    }

@PostMapping("/Section/Create")
    public ResponseEntity<Section> createSection(@Valid @RequestBody Section section) {  
 if(section.getId() == "-1") {
  section.setId(null);
 }
     Section sectionAdded = sectionRepository.save(section);
     System.out.println("\n\n\n\n\n section Created: " + sectionAdded.toString() + "\n\n\n\n\n");
     Optional<Course> courseToFind = this.courseRepository.findById(section.getCourseId());
     Course course = courseToFind.get();
     course.addSection(sectionAdded);
     this.courseRepository.save(course);
     
     List<String> studentUsernamesInSection = section.getStudentUsernames();
     for(int i = 0; i < studentUsernamesInSection.size(); i++) {
//      sectionAdded.addStudentUsername(studentUsernamesInSection.get(i));
      this.addStudent(studentUsernamesInSection.get(i), sectionAdded.getId());
     }
        return ResponseEntity.ok().body(sectionAdded);
    } 

public void addStudent(String newStudentUsername, String sectionId) {
 Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
 List<Student> students = this.studentRepository.findByUserName(newStudentUsername, sortByCreatedAtDesc);
 if(!students.isEmpty()) {
  students.get(0).addSectionId(sectionId);
  Student updatedStudent = this.studentRepository.save(students.get(0));
  System.out.println("\n\n\n\n\n Student Added in SectionController AddStudentUsername: " + updatedStudent.toString() + "\n\n\n\n\n");
 } else {
  Student newStudent = new Student();
  newStudent.setUserName(newStudentUsername);
  newStudent.addSectionId(sectionId);
  Student updatedStudent = this.studentRepository.save(newStudent);
  System.out.println("\n\n\n\n\n Student Added in Else SectionController AddStudentUsername: " + updatedStudent.toString() + "\n\n\n\n\n");
 }
}

public void updateStudentUserName(String newStudentUsername, String sectionToUpdateId, String sectionToDeleteId) {
   Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
   System.out.println("\n\n\n\n\n\n" + newStudentUsername);
   List<Student> students = this.studentRepository.findByUserName(newStudentUsername, sortByCreatedAtDesc);
   if(!students.isEmpty()) {
    Student student = students.get(0);
    //might have to use id's
    student.deleteSectionId(sectionToDeleteId);
    student.addSectionId(sectionToUpdateId);
    Student updatedStudent = this.studentRepository.save(student);
           System.out.println("\n\n\n\n\n Student Updated in section updateProfessorUserName: " + updatedStudent.toString() + "\n\n\n\n\n");
   }
}

public void deleteStudentUsername (String newStudentUsername, String sectionId) {
   Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
   List<Student> student = this.studentRepository.findByUserName(newStudentUsername, sortByCreatedAtDesc);
   if(!student.isEmpty()) {
    //might have to use id's
    student.get(0).deleteSectionId(sectionId);
    Student updatedStudent = this.studentRepository.save(student.get(0));
           System.out.println("\n\n\n\n\n Student Updated removed in Section deleteStudentUserName: " + updatedStudent.toString() + "\n\n\n\n\n");
   }
   
 }
    

    @PutMapping(value="/Section/Update/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable("id") String id,
                                           @Valid @RequestBody Section section) {
     Optional<Section> sectionToFind = this.sectionRepository.findById(id);
     Section sectionToUpdate = sectionToFind.get();
     System.out.println(section.toString());
     return sectionRepository.findById(id)
                .map(sectionData -> {
                 sectionData.setAssignments(section.getAssignments());
                 sectionData.setDescription(section.getDescription());
                 sectionData.setSectionNumber(section.getSectionNumber());
                 sectionData.setTerm(section.getTerm());
                 sectionData.setYear(section.getYear());
                 sectionData.setUseCourseDescription(section.isUseCourseDescription());
                 sectionData.setCourseId(section.getCourseId());
                 
                 List<String> oldStudentUsernamesInSection = sectionToUpdate.getStudentUsernames();
                 Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
                 for(int i = 0; i < oldStudentUsernamesInSection.size(); i++) {
               List<Student> students = this.studentRepository.findByUserName(oldStudentUsernamesInSection.get(i), sortByCreatedAtDesc);
               if(!students.isEmpty()) {
                if (!section.getStudentUsernames().contains(oldStudentUsernamesInSection.get(i))) {
                 //remove Section Id from this student
//                       sectionToUpdate.deleteStudentUsername(students.get(0).getUserName());
                          this.deleteStudentUsername(students.get(0).getUserName(), section.getId());
                       
                      }
               }
                  
                 }
                 List<String> studentUsernamesInNewSection = section.getStudentUsernames(); //infinite loop here
                 for(int i = 0; i < studentUsernamesInNewSection.size(); i++) {
                  if (!oldStudentUsernamesInSection.contains(studentUsernamesInNewSection.get(i))) {
                   this.addStudent(studentUsernamesInNewSection.get(i), section.getId());
                  }
                 }
                 
                 sectionData.setStudentUsernames(section.getStudentUsernames());// might need to deal with this in a better way
                 
                    Section updatedsection = sectionRepository.save(sectionData);
                    System.out.println("\n\n\n\n\n section Updated: " + updatedsection.toString() + "\n\n\n\n\n");
                    //updating courses
                    Optional<Course> courseToAddASectionFind = this.courseRepository.findById(section.getCourseId());
                 Course courseToAddASection = courseToAddASectionFind.get();
                 if(!section.getCourseId().equals(sectionToUpdate.getCourseId())) {
                  Optional<Course> courseToDeleteASectionFind = this.courseRepository.findById(sectionToUpdate.getCourseId());
                     Course courseToDeleteASection = courseToDeleteASectionFind.get();
                     courseToDeleteASection.deleteSection(sectionToUpdate);
                     courseToAddASection.addSection(updatedsection);
                     Course courseDelete = this.courseRepository.save(courseToDeleteASection);
                     Course courseAdd = this.courseRepository.save(courseToAddASection);
                     System.out.println("\n\n\nCourse Updated in Section Controller If deleted: " + courseDelete.toString());
                     System.out.println("\n\n\nCourse Updated in Section Controller If Added: " + courseAdd.toString());
                 }
                    return ResponseEntity.ok().body(updatedsection);
                }).orElse(ResponseEntity.notFound().build());       
     
    }

    @DeleteMapping(value="/Section/Delete/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable("id") String id) {
        return sectionRepository.findById(id)
                .map(section -> {
                 //put the delete in the section class itself
                    section.deleteSection();
                    return ResponseEntity.ok().body("Deleted a section");
                }).orElse(ResponseEntity.notFound().build());
    }

}