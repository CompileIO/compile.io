package compile_io.mongo;
import org.springframework.data.annotation.Id;

public class User {

	@Id
	public String id;
	
    public String firstName;
    public String lastName;
    public String userName;
    public String type;
    public int runTime;

    public User() {}

    public User(String firstName, String lastName) {
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

