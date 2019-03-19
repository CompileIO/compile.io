package compile_io.mongo.models;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Student {

	@Id
	private String id;
	
	@DBRef
	private List<Code> codes;
	@DBRef
	private List<Section> sections;
	private String name;
	private String userName;

    public Student() {
    	super();
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void addCode(Code newCode) {
		this.codes.add(newCode);
	}
	
	public void deleteCode (Code newCode) {
		for(Code code : this.codes) {
			if(newCode == code) {
				this.codes.remove(code);
			}
		}
	}
	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}
	
	public void addSection(Section newSection) {
		this.sections.add(newSection);
	}
	
	public void deleteSection (Section newSection) {
		for(Section section : this.sections) {
			if(newSection == section) {
				this.sections.remove(section);
			}
		}
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", userName=" + userName + "]";
	}
   
}

