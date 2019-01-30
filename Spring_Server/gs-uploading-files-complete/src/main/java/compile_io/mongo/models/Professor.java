package compile_io.mongo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Professor {

	@Id
	public String id;
	
    public String firstName;
    public String lastName;
    public String userName;
    public String type;
    public int runTime;

    public Professor() {}

    public Professor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

//const facultySchema = new mongoose.Schema({
//    username: { type: String, required: true },
//    name: { type: String, required: true },
//    department: { type: String },

