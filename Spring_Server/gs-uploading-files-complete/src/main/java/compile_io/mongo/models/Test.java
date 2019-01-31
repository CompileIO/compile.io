package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Test {
	
	@Id
	private String id;
	
	protected String language;
	protected int runTime;
	private String testPath;
	@DBRef
	private List<UnitTest> unitTests;
	
	
	public Test () {}
	
	public Test(String language, int runTime, String testPath) {
		this.language = language;
		this.runTime = runTime;
		this.testPath = testPath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public String getTestPath() {
		return testPath;
	}

	public void setTestPath(String testPath) {
		this.testPath = testPath;
	}

	public void inputTest (String language, int runtime  ) {
        this.language = language;
        this.runTime = runtime;
    }
	
	
	
	public List<UnitTest> getUnitTests() {
		return unitTests;
	}

	public void setUnitTests(List<UnitTest> unitTests) {
		this.unitTests = unitTests;
	}

	@Override
	public String toString() {
		return "Test [language=" + language + ", runTime=" + runTime + "]";
	}


}
