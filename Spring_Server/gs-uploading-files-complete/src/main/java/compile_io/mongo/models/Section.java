package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Section") 
public class Section {
	@Id
	private String id;
	private int Year; //2019
	private int Term; //1
	private int sectionNumber;
	@DBRef
	private List<Student> students;
	private boolean useClassDescription;
	private String description;
	@DBRef
	private List<Assignment> assignments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int Year) {
		Year = Year;
	}
	public int getTerm() {
		return Term;
	}
	public void setTerm(int Term) {
		Term = Term;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	public void addStudent(Student newStudent) {
		this.students.add(newStudent);
	}
	
	public void deleteStudent (Student newStudent) {
		for(Student student : this.students) {
			if(newStudent == student) {
				this.students.remove(student);
			}
		}
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
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
		for(Assignment assignment : this.assignments) {
			if(newAssignment == assignment) {
				this.assignments.remove(assignment);
			}
		}
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	@Override
	public String toString() {
		return "Section [id=" + id + ", Year=" + Year + ", Term=" + Term + ", sectionNumber=" + sectionNumber
				+ ", students=" + students + ", useClassDescription=" + useClassDescription + ", description="
				+ description + ", assignments=" + assignments + "]";
	}
	
}
