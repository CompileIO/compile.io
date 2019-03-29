package compile_io.mongo.models;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Student") 
public class Student {

	@Id
	private String id;
	
	@DBRef
	private List<Code> codes;
	private List<String> sectionIds;
	private String name;
	private String userName;

    public Student() {
    	super();
    	this.codes = new ArrayList<Code>();
    	this.sectionIds = new ArrayList<String>();
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
		for(int i = 0; i < this.codes.size(); i++) {
			Code code = this.codes.get(i);
			if(newCode == code) {
				this.codes.remove(code);
				i--;
			}
		}
	}
	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}
	
	public void addSectionId(String newSectionId) {
		this.sectionIds.add(newSectionId);
	}
	
	public void deleteSectionId (String newSectionId) {
		for(int i = 0; i < this.sectionIds.size(); i++) {
			String sectionId = this.sectionIds.get(i);
			if(newSectionId == sectionId) {
				this.sectionIds.remove(sectionId);
				i--;
			}
		}
	}

	public List<String> getSectionIds() {
		return sectionIds;
	}

	public void setSectionIds(List<String> sectionIds) {
		this.sectionIds = sectionIds;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Student [id=" + id + ", codes="
				+ (codes != null ? codes.subList(0, Math.min(codes.size(), maxLen)) : null) + ", sectionIds="
				+ (sectionIds != null ? sectionIds.subList(0, Math.min(sectionIds.size(), maxLen)) : null) + ", name="
				+ name + ", userName=" + userName + "]";
	}
   
}

