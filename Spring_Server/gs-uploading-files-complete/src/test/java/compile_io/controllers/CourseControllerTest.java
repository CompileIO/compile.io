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
import compile_io.mongo.models.Course;
import compile_io.mongo.models.Professor;
import compile_io.mongo.models.Section;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllCoursesAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Courses")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
	}

	@Test
	public void createCourseAPI() throws Exception {
		List<Section> sectionList = new ArrayList<Section>();

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

		// Test the Get assignment by ID function
		mockMvc.perform(MockMvcRequestBuilders.get("/Course/{id}", 1)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("courseName"));

		this.deleteCourseAPI();

	}

	@Test
	public void updateCourseAPI() throws Exception {
		List<Section> sectionList = new ArrayList<Section>();

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
		
		newCourse.setCourseName("updatedCourseName");
//		Professor newnewProfessor = new Professor("1", "newTestProfessorUserName");
//		professorList.add(newnewProfessor.getUserName());
//		newCourse.setProfessors(professorList);
		
//		mockMvc.perform(MockMvcRequestBuilders.post("/Professor/Create").content(asJsonString(newnewProfessor))
//				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
//				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
//				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
		mockMvc.perform(MockMvcRequestBuilders.put("/Course/Update/{id}", "1").content(asJsonString(newCourse))
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		// Test the Get assignment by ID function
				mockMvc.perform(MockMvcRequestBuilders.get("/Course/{id}", 1)
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
						.andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("updatedCourseName"));

				this.deleteCourseAPI();
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	 @Test
	public void deleteCourseAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/Course/Delete/{id}", 1)).andExpect(status().isOk());
	}

}
