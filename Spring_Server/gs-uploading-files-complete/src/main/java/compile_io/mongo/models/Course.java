package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Course {
	@Id
	private String id;
	private String courseName; 
	private String crn;
	private int sectionNumber;
	@DBRef
	private Professor instructor;
	@DBRef
	private List<Student> students;
	
	
	public Course(String courseName, String crn, int sectionNumber, String instructorName) {
		super();
		this.courseName = courseName;
		this.crn = crn;
		this.sectionNumber = sectionNumber;
		this.instructor.setName(instructorName);
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCrn() {
		return crn;
	}


	public void setCrn(String crn) {
		this.crn = crn;
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


	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", crn=" + crn + ", sectionNumber=" + sectionNumber
				+ ", instructor=" + instructor + ", students=" + students + "]";
	}
	
	
	
	
	
}
