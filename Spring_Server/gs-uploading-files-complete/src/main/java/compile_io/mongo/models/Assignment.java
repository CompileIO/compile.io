package compile_io.mongo.models;



import java.util.Date;
import java.time.LocalTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime endTime;
    private String filepath;
    private String courseName;
    private String createdByUsername;
    
	
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
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	public LocalTime getStartTime() {
		return startTime;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	public LocalTime getEndTime() {
		return endTime;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getFilePath() {
		return this.filepath;
	}

	public void setFilePath(String file) {
		this.filepath = file;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}

	@Override
	public String toString() {
		return "Assignment [Id= " +id +", assignmentName=" + assignmentName + ", timeout=" + timeout + ", language="
				+ language + ", size=" + size + ", tries=" + tries + ", startDate=" + startDate + ", startTime="
				+ startTime + ", endDate=" + endDate + ", endTime=" + endTime + ", File=" + filepath + ", courseName="
				+ courseName + "]";
	}

	
	
	
}
