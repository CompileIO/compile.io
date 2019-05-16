package compile_io.mongo.models;



import java.util.Date;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.FileSystemUtils;

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
    private String fileName;
    private String createdByUsername;
    private List<String> sectionIds;
    private String courseId;
    private boolean availableToOtherSections;
    
	
	public Assignment() {
		super();
	}

	public Assignment(String id, String assignmentName, String filepath, List<String> sectionIdList, String createdByUser) {
		super();
		this.id = id;
		this.assignmentName = assignmentName;
		this.filepath = filepath;
		this.sectionIds = sectionIdList;
		this.createdByUsername = createdByUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssignmentName() {
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
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void deleteFilePathFromServer() {
		File assignmentFile = Paths.get(this.filepath).toFile();
		FileSystemUtils.deleteRecursively(assignmentFile);
	}
	
	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}

	public List<String> getSectionIds() {
		return sectionIds;
	}

	public void setSectionIds(List<String> sectionIds) {
		this.sectionIds = sectionIds;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public boolean isAvailableToOtherSections() {
		return availableToOtherSections;
	}

	public void setAvailableToOtherSections(boolean availableToOtherSections) {
		this.availableToOtherSections = availableToOtherSections;
	}


	@Override
	public String toString() {
		final int maxLen = 10;
		return "Assignment [id=" + id + ", assignmentName=" + assignmentName + ", timeout=" + timeout + ", language="
				+ language + ", size=" + size + ", tries=" + tries + ", startDate=" + startDate + ", startTime="
				+ startTime + ", endDate=" + endDate + ", endTime=" + endTime + ", filepath=" + filepath + ", fileName="
				+ fileName + ", createdByUsername=" + createdByUsername + ", sectionIds="
				+ (sectionIds != null ? sectionIds.subList(0, Math.min(sectionIds.size(), maxLen)) : null)
				+ ", courseId=" + courseId + ", availableToOtherSections=" + availableToOtherSections + "]";
	}

	
	
	

	
	
	
}
