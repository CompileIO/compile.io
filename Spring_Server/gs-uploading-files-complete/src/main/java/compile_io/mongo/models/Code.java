package compile_io.mongo.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="Code") 
public class Code {
	@Id
	private String id;

	private String language;
	private int runTime;
	private int submissionAttempts;//change
    private List<String> testResponses;
    private List<List<String>> unitResponses;
    private String codePath;
    private String assignmentId;
    private String userName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalTime submissionTime;
    private String grade;
    private String fileName;
    
    public Code() {
    	super();
    	testResponses = new ArrayList<>();
    	submissionAttempts = 0;
    	unitResponses = new ArrayList<>();
    }
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getTestResponses() {
		return testResponses;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(String assignmentName) {
		this.assignmentId = assignmentName;
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
	
	public List<List<String>> getUnitResponses() {
		return unitResponses;
	}

	public void setUnitResponses(List<List<String>> unitResponses) {
		this.unitResponses = unitResponses;
	}

	public void addTestResponse(String testResponse) {
		this.testResponses.add(testResponse);
	}
	
	public List<String> getTestResponse() {
		return testResponses;
	}
	public void setTestResponses(List<String> testResponses) {
		this.testResponses = testResponses;
	}
	
	public int getSubmissionAttempts() {
		return submissionAttempts;
	}

	public void setSubmissionAttempts(int submissionAttempts) {
		this.submissionAttempts = submissionAttempts;
	}

	
	public LocalTime getSubmissionTime() {
		return submissionTime;
	}
	
	
	public void setSubmissionTime(LocalTime submissionTime) {
		this.submissionTime = submissionTime;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	@Override
	public String toString() {
		return "Code [id=" + id + ", language=" + language + ", runTime=" + runTime + ", submissionAttempts="
				+ submissionAttempts + ", testResponses=" + testResponses + ", unitResponses=" + unitResponses
				+ ", codePath=" + codePath + ", assignmentId=" + assignmentId + ", userName=" + userName
				+ ", submissionTime=" + submissionTime + ", grade=" + grade + ", fileName=" + fileName + "]";
	}



	
    
    
}

