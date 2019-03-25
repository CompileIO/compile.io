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

import compile_io.mongo.models.Section;
import compile_io.mongo.models.Professor;
import compile_io.mongo.repositories.SectionRepository;
import compile_io.mongo.repositories.ProfessorRepository;

@RestController
public class SectionController {
	
	@Autowired
    SectionRepository sectionRepository;
			
	@GetMapping("/Sections")
	public List<Section> getSections() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return sectionRepository.findAll(sortByCreatedAtDesc);
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
		System.out.println("Inside create section with: " + section.toString());
    	Section sectionAdded = sectionRepository.save(section);
    	System.out.println("\n\n\n\n\n section Created: " + sectionAdded.toString() + "\n\n\n\n\n");
        return ResponseEntity.ok().body(sectionAdded);
    } 
    

    @PutMapping(value="/Section/Update/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable("id") String id,
                                           @Valid @RequestBody Section section) {
    	System.out.println(section.toString());
    	return sectionRepository.findById(id)
                .map(sectionData -> {
                	sectionData.setAssignments(section.getAssignments());
                	sectionData.setDescription(section.getDescription());
                	sectionData.setSectionNumber(section.getSectionNumber());
                	sectionData.setStudents(section.getStudents());
                	sectionData.setTerm(section.getTerm());
                	sectionData.setYear(section.getYear());
                	sectionData.setUseClassDescription(section.isUseClassDescription());
                    Section updatedsection = sectionRepository.save(sectionData);
                    System.out.println("\n\n\n\n\n section Updated: " + updatedsection.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedsection);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Section/Delete/{id}")
    public ResponseEntity<?> deletesection(@PathVariable("id") String id) {
        return sectionRepository.findById(id)
                .map(section -> {
                    sectionRepository.deleteById(id);
                    return ResponseEntity.ok().body("Deleted a section");
                }).orElse(ResponseEntity.notFound().build());
    }

}
