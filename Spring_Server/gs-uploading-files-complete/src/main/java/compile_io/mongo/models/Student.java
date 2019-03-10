package compile_io.mongo.models;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Student {

	@Id
	private String id;
	
	@DBRef
	private List<Code> codes;
	@DBRef
	private List<Course> courses;
	private String name;
	private String userName;

    public Student() {}

	public Student(String name, String userName) {
		super();
		this.name = name;
		this.userName = userName;
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
	
	public void addCode(Code newCode) {
		this.codes.add(newCode);
	}
	
	public void deleteCode (Code newCode) {
		for(Code code : this.codes) {
			if(newCode == code) {
				this.codes.remove(code);
			}
		}
	}
	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
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
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", userName=" + userName + "]";
	}
   
}

