package compile_io.mongo.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import compile_io.mongo.repositories.StudentRepository;

@Document(collection="Section") 
public class Section {
	@Id
	private String id;
	private int year; //2019
	private int term; //1
	private int sectionNumber;
	private List<String> studentUsernames;
	private boolean useClassDescription;
	private String description;
	private String courseId;
	@DBRef
	private List<Assignment> assignments;
	
	@Autowired 
	public StudentRepository studentRepository;
	
	public Section() {
    	super();
    	studentUsernames = new ArrayList<String>();
    	assignments = new ArrayList<Assignment>();
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
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		List<Student> student = this.studentRepository.findByuserName(newStudentUsername, sortByCreatedAtDesc);
		if(!student.isEmpty()) {
			student.get(0).addSection(this);
			this.studentRepository.save(student.get(0));
		}
	}
	
	public void deleteStudentUsername (String newStudentUsername) {
		for(int i = 0; i < this.studentUsernames.size(); i++) {
			String studentUsername = this.studentUsernames.get(i);
			if(newStudentUsername == studentUsername) {
				this.studentUsernames.remove(studentUsername);
				i--;
				Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
				List<Student> student = this.studentRepository.findByuserName(newStudentUsername, sortByCreatedAtDesc);
				if(!student.isEmpty()) {
					//might have to use id's
					student.get(0).deleteSection(this);
					this.studentRepository.save(student.get(0));
				}
				
			}
		}
	}
	
	public List<String> getStudentUsernames() {
		return studentUsernames;
	}

	public void setStudentUsernames(List<String> studentUsernames) {
		this.studentUsernames = studentUsernames;
	}



	public boolean isUseClassDescription() {
		return useClassDescription;
	}
	public void setUseClassDescription(boolean useClassDescription) {
		this.useClassDescription = useClassDescription;
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
				+ ", studentUsernames=" + studentUsernames + ", useClassDescription=" + useClassDescription
				+ ", description=" + description + ", courseId=" + courseId + ", assignments=" + assignments + "]";
	}
	
}
