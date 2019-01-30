package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Test {
	
	@Id
	private String id;
	
	private String testPath;
	private List<UnitTest> unitTests;
	
	public void inputTest (String username, String type, int runtime  ) {
        this.userName = username;
        this.type = type;
        this.runTime = runtime;
    }

}
