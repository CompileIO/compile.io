package compile_io.mongo.models;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class Code {
	@Id
	private String id;

	private String language;
	private int runTime;
    private String testResponse;
    private String codePath;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date submissionTime;
    private String grade;
}


//const gradeSchema = new mongoose.Schema({
//    student: { type: String, required: true },
//    course: { type: String, required: true },
//    grade: String