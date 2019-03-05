package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Section {
	@Id
	private String id;
	private int sectionNumber;
	@DBRef
	private Professor instructor;
	@DBRef
	private List<Student> students;
	private String courseName;
	
	public Section(int sectionNumber, Professor instructor) {
		super();
		this.sectionNumber = sectionNumber;
		this.instructor = instructor;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public Professor getInstructor() {
		return instructor;
	}
	public void setInstructor(Professor instructor) {
		this.instructor = instructor;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "Section [id=" + id + ", sectionNumber=" + sectionNumber + ", instructor=" + instructor + ", courseName="
				+ courseName + "]";
	}
	
	
	
	
	
	
	
	
}
