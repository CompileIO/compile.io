package compile_io.mongo.models;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Professor")
public class Professor {

	@Id
	private String id;
	private String name;
	private String userName;
	@DBRef
    private List<Course> courses;

    public Professor() {
    	super();
    	courses = new ArrayList<Course>();
    }
    
    public Professor(String id, String userName) {
    	this.id = id;
    	this.userName = userName;
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		for(int i = 0; i < this.courses.size(); i ++) {
			Course course = this.courses.get(i);
			if(newCourse.getId().equals(course.getId())) {
				this.courses.remove(course);
				i--;
			}
		}
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", userName=" + userName + ", courses=" + courses + "]";
	}

}


