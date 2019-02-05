package compile_io.mongo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Course {
	@Id
	private String id;
	private String name; 
	private String crn;
	@DBRef
	private List<Section> section;
	
	
	public Course(String name, String crn, List<Section> section) {
		super();
		this.name = name;
		this.crn = crn;
		this.section = section;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	public List<Section> getSection() {
		return section;
	}
	public void setSection(List<Section> section) {
		this.section = section;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Course [name=" + name + ", crn=" + crn + ", section=" + section + "]";
	}
	
	
	
	
	
}
