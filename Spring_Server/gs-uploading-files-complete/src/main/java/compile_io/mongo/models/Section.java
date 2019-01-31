package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Section {
	@Id
	private String id;
	private int SectionNumber;
	@DBRef
	private Professor Instructor;
	private List<String> assignmentIds;
	public Section(int sectionNumber, Professor instructor) {
		super();
		SectionNumber = sectionNumber;
		Instructor = instructor;
	}
	public int getSectionNumber() {
		return SectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		SectionNumber = sectionNumber;
	}
	public Professor getInstructor() {
		return Instructor;
	}
	public void setInstructor(Professor instructor) {
		Instructor = instructor;
	}
	
	
	public List<String> getAssignmentIds() {
		return assignmentIds;
	}
	public void setAssignmentIds(List<String> assignmentIds) {
		this.assignmentIds = assignmentIds;
	}
	@Override
	public String toString() {
		return "Section [SectionNumber=" + SectionNumber + ", Instructor=" + Instructor + "]";
	}
	
	
}
