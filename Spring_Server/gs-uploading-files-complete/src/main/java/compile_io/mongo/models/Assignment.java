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
}
