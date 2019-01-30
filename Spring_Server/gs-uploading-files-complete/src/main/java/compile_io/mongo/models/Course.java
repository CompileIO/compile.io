package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Course {
	@Id
	private String id;
	private String name; 
	private String crn;
	private List<Section> section;
	
	
	
}

//const courseSchema = new mongoose.Schema({
//    name: { type: String, required: true },
//    section: { type: String, required: true },
//    crn: { type: String, required: true },
//    description: String,
//    creditHours: { type: Number, required: true },
//    meetTimes: String,
//    instructor: String,
//    term: { type: String, required: true }
//});
//
//courseSchema.index({ name: 1, section: 1, term: 1 }, { unique: true });
