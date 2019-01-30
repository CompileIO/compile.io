package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Section {
	@Id
	private String id;
	private int SectionNumber;
	private Professor Instructor;
	private List<String> assignmentIds;
}
