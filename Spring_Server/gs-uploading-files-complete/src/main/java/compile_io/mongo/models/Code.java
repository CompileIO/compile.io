package compile_io.mongo.models;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class Code {
	@Id
	private String id;

	private String language;
	private int runTime;
    private List<String> testResponses;
    private String codePath;
    private Assignment assignment;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalTime submissionTime;
    private String grade;
    
    public Code() {}
    
    public Code(String language, int runTime, String codePath, LocalTime submissionTime) {
    	super();
		this.language = language;
		this.runTime = runTime;
		this.codePath = codePath;
		this.submissionTime = submissionTime;
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
	public List<String> getTestResponse() {
		return testResponses;
	}
	public void addTestResponse(String testResponse) {
		this.testResponses.add(testResponse);
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
	
	
	
	
	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	@Override
	public String toString() {
		return "Code [language=" + language + ", runTime=" + runTime + ", testResponses=" + testResponses
				+ ", assignment=" + assignment + ", submissionTime=" + submissionTime + ", grade=" + grade + "]";
	}
    
    
}

