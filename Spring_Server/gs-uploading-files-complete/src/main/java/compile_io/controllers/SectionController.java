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


import compile_io.mongo.models.Section;
import compile_io.mongo.repositories.SectionRepository;

@RestController
public class SectionController {
	
	@Autowired 
	public SectionRepository sectionRepository;
	
	
	@GetMapping("/Section/getInstructorSections/{instructorName}")
    public ResponseEntity<List<Section>> getAllSectionsForProfessor(@PathVariable("instructorName") String instructorName) {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ResponseEntity.ok().body(sectionRepository.findByInstructor(instructorName, sortByCreatedAtDesc));
    }
    
    @GetMapping(value="/Section/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable("id") String id) {
        return sectionRepository.findById(id)
                .map(section -> ResponseEntity.ok().body(section))
                .orElse(ResponseEntity.notFound().build());
    }
	
	
	@PostMapping("/Section/Create")
    public ResponseEntity<String> createSection(@Valid @RequestBody Section section) {   
    	System.out.println("\n\n\n\n\n Section Created: " + section.toString() + "\n\n\n\n\n");
    	sectionRepository.save(section);
        return ResponseEntity.ok().body("uploaded Section: " + section.toString());
    } 
    

    @PutMapping(value="/Section/Update/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable("id") String id,
                                           @Valid @RequestBody Section section) {
    	System.out.println(section.toString());
    	return sectionRepository.findById(id)
                .map(sectionData -> {
                	sectionData.setCourseName(section.getCourseName());
                	sectionData.setInstructor(section.getInstructor());
                	sectionData.setSectionNumber(section.getSectionNumber());
                    Section updatedSection = sectionRepository.save(sectionData);
                    System.out.println("\n\n\n\n\n Section Updated: " + updatedSection.toString() + "\n\n\n\n\n");
                    return ResponseEntity.ok().body(updatedSection);
                }).orElse(ResponseEntity.notFound().build());							
     
    }

    @DeleteMapping(value="/Section/Delete/{id}")
    public ResponseEntity<?> deleteSection(@PathVariable("id") String id) {
        return sectionRepository.findById(id)
                .map(assignment -> {
                    sectionRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
	
	
	
	
}
