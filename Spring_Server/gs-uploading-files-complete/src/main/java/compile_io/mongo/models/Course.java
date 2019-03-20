package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Course")
public class Course {
	@Id
	private String id;
	private String courseName; 
	private List<Professor> professors;
	private List<Section> sections;
	

	private List<Assignment> assignments;
	
	public Course() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void addProfessor(Professor newprofessor) {
		this.professors.add(newprofessor);
	}
	
	public void deleteProfessor (Professor newprofessor) {
		for(Professor professor : this.professors) {
			if(newprofessor == professor) {
				this.professors.remove(professor);
			}
		}
	}
	
	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
	
	public void addSection(Section newSection) {
		this.sections.add(newSection);
	}
	
	public void deleteSection (Section newSection) {
		for(Section section : this.sections) {
			if(newSection == section) {
				this.sections.remove(section);
			}
		}
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public void addAssignment(Assignment newAssignment) {
		this.assignments.add(newAssignment);
	}
	
	public void deleteAssignment (Assignment newAssignment) {
		for(Assignment professor : this.assignments) {
			if(newAssignment == professor) {
				this.assignments.remove(professor);
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
		return "Course [id=" + id + ", courseName=" + courseName + ", professors=" + professors + ", sections="
				+ sections + ", assignments=" + assignments + "]";
	}

	
	
	
	
	
	
	
	
}
