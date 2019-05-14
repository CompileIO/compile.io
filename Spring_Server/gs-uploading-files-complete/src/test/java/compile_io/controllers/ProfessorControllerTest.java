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
import compile_io.mongo.models.Professor;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class ProfessorControllerTest {

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
	public void createProfessorAPI() throws Exception {

		Professor newProfessor = new Professor("1", "TestProfessorUserName");
		List<String> professorList = new ArrayList<String>();
		professorList.add(newProfessor.getUserName());

		mockMvc.perform(MockMvcRequestBuilders.post("/Professor/Create").content(asJsonString(newProfessor))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		//Test the Get Professor by ID function
    	mockMvc.perform( MockMvcRequestBuilders
    	          .get("/Professor/{id}", 1)
    	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	          .andDo(print())
    	          .andExpect(MockMvcResultMatchers.status().isOk())
    	          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    	mockMvc.perform( MockMvcRequestBuilders
  	          .get("/Professor/Username/{username}", "TestProfessorUserName")
  	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
  	          .andDo(print())
  	          .andExpect(MockMvcResultMatchers.status().isOk())
  	          .andExpect(MockMvcResultMatchers.jsonPath("$.*.userName").value("TestProfessorUserName"));

	}
	
	@Test
	public void updateProfessorAPI() throws Exception {
		
		Professor newProfessorCreate = new Professor("1", "TestProfessorUserName");

		mockMvc.perform(MockMvcRequestBuilders.post("/Professor/Create").content(asJsonString(newProfessorCreate))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		Professor newProfessor = new Professor("1", "UpdatedTestProfessorUserName");

		mockMvc.perform(MockMvcRequestBuilders.put("/Professor/Update/{id}", "1").content(asJsonString(newProfessor))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		//Test the Get Professor by ID function
    	mockMvc.perform( MockMvcRequestBuilders
    	          .get("/Professor/{id}", 1)
    	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	          .andDo(print())
    	          .andExpect(MockMvcResultMatchers.status().isOk())
    	          .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("UpdatedTestProfessorUserName"));
    	mockMvc.perform( MockMvcRequestBuilders
  	          .get("/Professor/Username/{username}", "UpdatedTestProfessorUserName")
  	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
  	          .andDo(print())
  	          .andExpect(MockMvcResultMatchers.status().isOk())
  	          .andExpect(MockMvcResultMatchers.jsonPath("$.*.userName").value("UpdatedTestProfessorUserName"));
    	this.deleteProfessorAPI();

	}
	
	@Test
    public void getAllProfessorsAPI() throws Exception
    {
    	mockMvc.perform( MockMvcRequestBuilders
          .get("/Professors")
          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
          .andDo(print())
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
    }
	
	@Test
    public void deleteProfessorAPI() throws Exception
    {
    mockMvc.perform( MockMvcRequestBuilders.delete("/Professor/Delete/{id}", 1) )
            .andExpect(status().isOk());
    }
}
