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

    public void inputTest (String username, String type, int runtime  ) {
        this.userName = username;
        this.type = type;
        this.runTime = runtime;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}


