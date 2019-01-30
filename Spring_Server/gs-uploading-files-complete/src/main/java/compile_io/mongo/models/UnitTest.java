package compile_io.mongo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UnitTest {

	@Id
	private String id;
	
	private int weight;
}
