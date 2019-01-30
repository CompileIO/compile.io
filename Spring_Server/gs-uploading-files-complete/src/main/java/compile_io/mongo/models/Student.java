package compile_io.mongo.models;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Student {

	@Id
	public String id;
	
	private List<String> codeIds;
	private List<String> courseIds;
	private String name;
	private String userName;

    public Student() {}

    public Student(String firstName, String lastName) {
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

//const studentSchema = new mongoose.Schema({
//    username: { type: String, unique: true },
//    name: { type: String },
//    year: { type: String },
//    majors: [ String ],
//    minors: [ String ],
//    graduationDate: String,
//    termsEnrolled: [ String ] // TODO: term.name must exist (business rule?)
//    //degreeId: Add degree id if we go down this route see ./degree for further discussion
//}, { usePushEach: true });
//
//const Student = mongoose.model('Student', studentSchema);
//module.exports = Student;

