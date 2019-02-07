package compile_io.mongo.models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Document(collection="Assignment")
public class Assignment {
	
	@Id
	private String id;
    private String assignmentName;
    private int timeout;
    private String language;
    private int size;
    private int tries;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startDate;
    
    private Time startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endDate;

	private Time endTime;
    private MultipartFile file;
    private String courseName;
    
//    @DBRef
//	private List<UnitTest> unitTests;
	
    
    
    
    //NOT NECESSARY
//	private String name;
//	
//	@DBRef
//	private Test test;
//	
//	@DBRef
//	private List<Code> codes;
//	
//    private Date dueDate;
	
	public Assignment() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getassignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String newAssignmentName) {
		this.assignmentName = newAssignmentName;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@Override
	public String toString() {
		return "Assignment [id=" + id + ", assignmentName=" + assignmentName + ", timeout=" + timeout + ", language="
				+ language + ", size=" + size + ", tries=" + tries + ", startDate=" + startDate + ", startTime="
				+ startTime + ", endDate=" + endDate + ", endTime=" + endTime + ", File=" + this.file + ", courseName="
				+ courseName + "]";
	}

	
	
	
}
