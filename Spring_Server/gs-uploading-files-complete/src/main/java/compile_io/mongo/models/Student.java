package compile_io.mongo.models;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Student {

	@Id
	private String id;
	
	private List<String> codeIds;
	private List<String> courseIds;
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
	
	

	public List<String> getCodeIds() {
		return codeIds;
	}

	public void setCodeIds(List<String> codeIds) {
		this.codeIds = codeIds;
	}

	public List<String> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<String> courseIds) {
		this.courseIds = courseIds;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", userName=" + userName + "]";
	}
   
}

