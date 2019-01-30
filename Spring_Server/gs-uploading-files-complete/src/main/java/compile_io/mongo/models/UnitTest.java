package compile_io.mongo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class UnitTest {

	@Id
	private String id;
	
	private int weight;

	public UnitTest(int weight) {
		super();
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "UnitTest [weight=" + weight + "]";
	}
	
	
}
