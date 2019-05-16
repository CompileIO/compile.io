package compile_io.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import compile_io.Application;
import compile_io.mongo.models.Code;
import compile_io.mongo.models.Student;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class StudentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void createStudentAPI() throws Exception {
    	ArrayList<Code> codeList = new ArrayList<Code>();
    	ArrayList<String> sectionIds = new ArrayList<String>();
    	
		Student newStudent = new Student("1",codeList, sectionIds, "studentName");
    	

		mockMvc.perform(MockMvcRequestBuilders.post("/Student/Create").content(asJsonString(newStudent))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		//Test the Get Student by ID function
    	mockMvc.perform( MockMvcRequestBuilders
    	          .get("/Student/{id}", 1)
    	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	          .andDo(print())
    	          .andExpect(MockMvcResultMatchers.status().isOk())
    	          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    	mockMvc.perform( MockMvcRequestBuilders
  	          .get("/Student/Username/{username}", "studentName")
  	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
  	          .andDo(print())
  	          .andExpect(MockMvcResultMatchers.status().isOk())
  	          .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("studentName"));
    	this.deleteStudentAPI();

	}
	
	@Test
	public void updateStudentAPI() throws Exception {
		ArrayList<Code> codeList = new ArrayList<Code>();
    	ArrayList<String> sectionIds = new ArrayList<String>();
    	Student newStudent = new Student("1",codeList, sectionIds, "studentName");
		mockMvc.perform(MockMvcRequestBuilders.post("/Student/Create").content(asJsonString(newStudent))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
		newStudent = new Student("1",codeList, sectionIds, "updatedStudentName");
		newStudent.setName("studentNameName");
		mockMvc.perform(MockMvcRequestBuilders.put("/Student/Update/{id}", "1").content(asJsonString(newStudent))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		//Test the Get Student by ID function
    	mockMvc.perform( MockMvcRequestBuilders
    	          .get("/Student/{id}", "1")
    	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	          .andDo(print())
    	          .andExpect(MockMvcResultMatchers.status().isOk())
    	          .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("updatedStudentName"));
    	mockMvc.perform( MockMvcRequestBuilders
  	          .get("/Student/Username/{username}", "updatedStudentName")
  	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
  	          .andDo(print())
  	          .andExpect(MockMvcResultMatchers.status().isOk())
  	          .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("studentNameName"));
    	this.deleteStudentAPI();
		
	}

	@Test
	public void getAllStudentsAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Students")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
	}
	
//	@Test
    public void deleteStudentAPI() throws Exception
    {
    mockMvc.perform( MockMvcRequestBuilders.delete("/Student/Delete/{id}", 1) )
            .andExpect(status().isOk());
    }

}
