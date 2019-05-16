package compile_io.mongo.models;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.FileSystemUtils;

import compile_io.mongo.repositories.AssignmentRepository;
import compile_io.mongo.repositories.SectionRepository;
import compile_io.mongo.repositories.StudentRepository;

@Document(collection="Section") 
public class Section {
	@Id
	private String id;
	private int year; //2019
	private int term; //1
	private int sectionNumber;
	private List<String> studentUsernames;
	private boolean useCourseDescription;
	private String description;
	private String courseId;
	@DBRef
	private List<Assignment> assignments;
	
	@Autowired 
	private StudentRepository studentRepository;
	@Autowired
	private AssignmentRepository assignmentRepository;
	@Autowired
	private SectionRepository sectionRepository;
	
	public Section() {
    	super();
    	studentUsernames = new ArrayList<String>();
    	assignments = new ArrayList<Assignment>();
    }
	
	public Section(String id, String courseId, List<Assignment> assignments, List<String> studentUsernames, int sectionNumber, int year, int term) {
		this.id = id;
		this.courseId = courseId;
		this.assignments = assignments;
		this.studentUsernames = studentUsernames;
		this.sectionNumber = sectionNumber;
		this.year = year;
		this.term = term;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	public void addStudentUsername(String newStudentUsername) {
		this.studentUsernames.add(newStudentUsername);
	}
	
	public void deleteStudentUsername (String newStudentUsername) {
		for(int i = 0; i < this.studentUsernames.size(); i++) {
			String studentUsername = this.studentUsernames.get(i);
			if(newStudentUsername == studentUsername) {
				this.studentUsernames.remove(studentUsername);
				i--;				
			}
		}
	}
	
	public List<String> getStudentUsernames() {
		return studentUsernames;
	}

	public void setStudentUsernames(List<String> studentUsernames) {
		this.studentUsernames = studentUsernames;
	}



	public boolean isUseCourseDescription() {
		return useCourseDescription;
	}
	public void setUseCourseDescription(boolean useClassDescription) {
		this.useCourseDescription = useClassDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addAssignment(Assignment newAssignment) {
		this.assignments.add(newAssignment);
	}
	
	public void deleteAssignment (Assignment newAssignment) {
		for(int i = 0; i < this.assignments.size(); i++) {
			Assignment assignment = this.assignments.get(i);
			if(newAssignment == assignment) {
				this.assignments.remove(assignment);
				i--;
			}
		}
	}
	
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	
	public String getCourseId() {
		return courseId;
	}



	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}



	@Override
	public String toString() {
		return "Section [id=" + id + ", Year=" + year + ", Term=" + term + ", sectionNumber=" + sectionNumber
				+ ", studentUsernames=" + studentUsernames + ", useCourseDescription=" + useCourseDescription
				+ ", description=" + description + ", courseId=" + courseId + ", assignments=" + assignments + "]";
	}
	
	public void deleteSection (String id) {
		
		for(Assignment assignment : this.getAssignments()) {
    		String filepath = assignment.getFilePath();
    		Path filePath = Paths.get(filepath);
    		FileSystemUtils.deleteRecursively(filePath.toFile());
    		this.assignmentRepository.deleteById(assignment.getId());
    	}
    	for(String studentUsername : this.getStudentUsernames()) {
    		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
    		List<Student> student = this.studentRepository.findByUserName(studentUsername, sortByCreatedAtDesc);
    		student.get(0).deleteSectionId(id);
    		this.studentRepository.save(student.get(0));
    	}
//        sectionRepository.deleteById(id);
	}
	
}
