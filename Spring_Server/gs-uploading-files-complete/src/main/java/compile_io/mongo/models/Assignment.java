package compile_io.mongo.models;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class Assignment {
	
	@Id
	private String id;
	
	private List<String> testIds;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dueDate;
	
	public Assignment(Date dueDate) {
		super();
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
	public List<String> getTestIds() {
		return testIds;
	}

	public void setTestIds(List<String> testIds) {
		this.testIds = testIds;
	}

	@Override
	public String toString() {
		return "Assignment [dueDate=" + dueDate + "]";
	}
	
	
	
	
}
