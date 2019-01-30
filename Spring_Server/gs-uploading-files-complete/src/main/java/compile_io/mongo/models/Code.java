package compile_io.mongo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Code {
	@Id
	public String id;
	
	public Student student;
}


//const gradeSchema = new mongoose.Schema({
//    student: { type: String, required: true },
//    course: { type: String, required: true },
//    grade: String