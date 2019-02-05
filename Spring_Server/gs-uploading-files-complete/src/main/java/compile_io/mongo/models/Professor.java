package compile_io.mongo.models;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Professor {

	@Id
	private String id;
	
	private String name;
	private String userName;
    private List<String> assignmentIds;
    private List<String> testIds;

    public Professor() {}

    public Professor(String name, String userName) {
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
	
	

	public List<String> getAssignmentIds() {
		return assignmentIds;
	}

	public void setAssignmentIds(List<String> assignmentIds) {
		this.assignmentIds = assignmentIds;
	}

	public List<String> getTestIds() {
		return testIds;
	}

	public void setTestIds(List<String> testIds) {
		this.testIds = testIds;
	}

	@Override
	public String toString() {
		return "Professor [name=" + name + ", userName=" + userName + "]";
	}

}


