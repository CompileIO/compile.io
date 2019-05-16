package compile_io.controllers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import compile_io.Application;
import compile_io.mongo.models.Assignment;
import compile_io.mongo.models.Code;
import compile_io.mongo.models.Course;
import compile_io.mongo.models.Professor;
import compile_io.mongo.models.Section;
import compile_io.mongo.models.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class CodeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	//Run Compiler is not tested
	//Run Code is not tested
	//serveFile is not tested
	//get all code for assignments and students are not tested as well

	@Test
	public void getAllCodesAPI() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/Code").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
	}

	@Test
	public void createCodeAPI() throws Exception {
		this.createAssignmentAPI();
		this.createStudentAPI();
		Code newCode = new Code();
		newCode.setId("1");
		newCode.setAssignmentId("1");
		newCode.setUserName("studentName");

		mockMvc.perform(MockMvcRequestBuilders.post("/Code/Create").content(asJsonString(newCode))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		// Test the Get Code by ID function
		mockMvc.perform(MockMvcRequestBuilders.get("/Code/{id}", 1)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
		this.deleteCodeAPI();
	}
	
	@Test
	public void updateCodeAPI() throws Exception {
		this.createAssignmentAPI();
		this.createStudentAPI();
		Code newCode = new Code();
		newCode.setId("1");
		newCode.setAssignmentId("1");
		newCode.setUserName("studentName");

		mockMvc.perform(MockMvcRequestBuilders.post("/Code/Create").content(asJsonString(newCode))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
		newCode.setGrade("1/1");
		mockMvc.perform(MockMvcRequestBuilders.put("/Code/Update/{id}", "1").content(asJsonString(newCode))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		// Test the Get Code by ID function
		mockMvc.perform(MockMvcRequestBuilders.get("/Code/{id}", 1)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.grade").value("1/1"));
		this.deleteCodeAPI();
	}


	public void createAssignmentAPI() throws Exception {
		List<String> sectionIdList = new ArrayList<String>();
		sectionIdList.add("1");
		Assignment newAssignment = new Assignment("1", "AssignmentName", "filepath", sectionIdList, "userName");
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		assignmentList.add(newAssignment);

		Code newCode = new Code();
		ArrayList<Code> codeList = new ArrayList<Code>();
		codeList.add(newCode);

		ArrayList<String> sectionIds = new ArrayList<String>();
		sectionIds.add("1");
		Student newStudent = new Student("1", codeList, sectionIds, "studentUsername");
		ArrayList<String> studentUsernameList = new ArrayList<String>();
		studentUsernameList.add(newStudent.getUserName());

		Section newSection = new Section("1", "1", assignmentList, studentUsernameList, 1, 2019, 2);
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.add(newSection);

		Professor newProfessor = new Professor("1", "TestProfessorUserName");
		List<String> professorList = new ArrayList<String>();
		professorList.add(newProfessor.getUserName());

		Course newCourse = new Course("1", sectionList, professorList, "courseName");

		mockMvc.perform(MockMvcRequestBuilders.post("/Professor/Create").content(asJsonString(newProfessor))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		mockMvc.perform(MockMvcRequestBuilders.post("/Course/Create").content(asJsonString(newCourse))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		mockMvc.perform(MockMvcRequestBuilders.post("/Section/Create").content(asJsonString(newSection))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		mockMvc.perform(MockMvcRequestBuilders.post("/Assignment/Create").content(asJsonString(newAssignment))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.*.id").exists());

		// Test the Get assignment by ID function
		mockMvc.perform(MockMvcRequestBuilders.get("/Assignment/{id}", 1)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
	}


	public void createStudentAPI() throws Exception {
		ArrayList<Code> codeList = new ArrayList<Code>();
		ArrayList<String> sectionIds = new ArrayList<String>();

		Student newStudent = new Student("1", codeList, sectionIds, "studentName");

		mockMvc.perform(MockMvcRequestBuilders.post("/Student/Create").content(asJsonString(newStudent))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	public void deleteCodeAPI() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.delete("/Student/Delete/{id}", 1)).andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders.delete("/Code/Delete/{id}", 1)).andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders.delete("/Assignment/Delete/{id}", 1)).andExpect(status().isOk());
	}

}
