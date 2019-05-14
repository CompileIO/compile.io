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
import compile_io.mongo.models.Assignment;
import compile_io.mongo.models.Code;
import compile_io.mongo.models.Course;
import compile_io.mongo.models.Professor;
import compile_io.mongo.models.Section;
import compile_io.mongo.models.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    private String AssignmentId;
    
//    @Test
//    public void getAllAssignmentsAPI() throws Exception
//    {
//    	mockMvc.perform( MockMvcRequestBuilders
//          .get("/Assignment")
//          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//          .andDo(print())
//          .andExpect(MockMvcResultMatchers.status().isOk())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
//    }
     
    @Test
    public void getAssignmentByIdAPI() throws Exception
    {
//    	mockMvc.perform( MockMvcRequestBuilders
//          .get("/Assignment/{id}", AssignmentId)
//          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//          .andDo(print())
//          .andExpect(MockMvcResultMatchers.status().isOk())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.*.id").value(AssignmentId));
    }
    
    @Test
    public void createAssignmentAPI() throws Exception
    {
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
    	Student newStudent = new Student("1",codeList, sectionIds, "studentUsername");
    	ArrayList<String> studentUsernameList = new ArrayList<String>();
    	studentUsernameList.add(newStudent.getUserName());
    	
    	
    	
    	Section newSection = new Section("1","1", assignmentList, studentUsernameList, 1,2019,2);
    	List<Section> sectionList = new ArrayList<Section>();
    	sectionList.add(newSection);
    	
    	Professor newProfessor = new Professor("1", "TestProfessorUserName");
    	List<String> professorList = new ArrayList<String>();
    	professorList.add(newProfessor.getUserName());
    	
    	Course newCourse = new Course("1", sectionList, professorList, "courseName");
    	
    	mockMvc.perform( MockMvcRequestBuilders
    	          .post("/Professor/Create")
    	          .content(asJsonString(newProfessor))
    	          .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
    	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	          .andExpect(status().isOk())
    	          .andDo(print())
    	          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    	
    	mockMvc.perform( MockMvcRequestBuilders
  	          .post("/Course/Create")
  	          .content(asJsonString(newCourse))
  	          .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
  	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
  	          .andExpect(status().isOk())
  	          .andDo(print())
  	          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    	
    	
    	mockMvc.perform( MockMvcRequestBuilders
    	          .post("/Section/Create")
    	          .content(asJsonString(newSection))
    	          .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
    	          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    	          .andExpect(status().isOk())
    	          .andDo(print())
    	          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    	
    	mockMvc.perform( MockMvcRequestBuilders
          .post("/Assignment/Create")
          .content(asJsonString(newAssignment))
          .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
          .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
          .andExpect(status().isOk())
          .andDo(print())
          .andExpect(MockMvcResultMatchers.jsonPath("$.*.id").exists());
    }
     
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void deleteAssignmentAPI() throws Exception
    {
    mockMvc.perform( MockMvcRequestBuilders.delete("/Assignment/Delete/{id}", 1) )
            .andExpect(status().isOk());
    }
    
    
}
