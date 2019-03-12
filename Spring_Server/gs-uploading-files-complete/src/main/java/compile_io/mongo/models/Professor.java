package compile_io.mongo.models;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Professor {

	@Id
	private String id;
	
	private String name;
	private String userName;
	@DBRef
    private List<Course> courses;
	@DBRef
    private List<Assignment> assignments;

    public Professor() {
    	super();
    }

//    public Professor(String name, String userName) {
//        this.name = name;
//        this.userName = userName;
//    }
    
//    public Professor(String userName) {
//        this.userName = userName;
//    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void addCourse(Course newCourse) {
		this.courses.add(newCourse);
	}
	
	public void deleteCourse (Course newCourse) {
		for(Course course : this.courses) {
			if(newCourse == course) {
				this.courses.remove(course);
			}
		}
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
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
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", userName=" + userName + ", courses=" + courses
				+ ", assignments=" + assignments + "]";
	}

}


