package com.nextgear.manager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextgear.manager.model.Student;
import com.nextgear.manager.service.StudentService;

public class ApiControllerTest {

    private StudentService service = mock(StudentService.class);
    ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    ApiController controller = new ApiController();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        controller.setService(service);
        this.mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(new Student("Join"), new Student("Tim"));
        Mockito.when(service.getAllStudent()).thenReturn(students);
        MvcResult result = mockMvc.perform(get("/api/student")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();
        assertEquals(mapper.writeValueAsString(students), result.getResponse().getContentAsString());
    }
    
    @Test
    public void testGetStudentById() throws Exception {
        int id = 1;
        Student student = new Student("John");
        Mockito.when(service.getStudent(id)).thenReturn(student);
        MvcResult result = mockMvc.perform(get("/api/student/"+id)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();
        assertEquals(mapper.writeValueAsString(student), result.getResponse().getContentAsString());
    }
    
    @Test
    public void testAddStudent() throws Exception {
        Student student = new Student("John");
        String json = mapper.writeValueAsString(student);
       
        //mock service.save(student) return passed in parameter
        when(service.save(any(Student.class))).thenAnswer(new Answer<Student>() {
            @Override
            public Student answer(InvocationOnMock invocation) throws Throwable {
              Object[] args = invocation.getArguments();
              return (Student) args[0];
            }
          });
        
        MvcResult result = mockMvc.perform(post("/api/student").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();
        assertEquals(mapper.writeValueAsString(student), result.getResponse().getContentAsString());
    }
    
    @Test
    public void testModifyStudentById() throws Exception {
        int id = 1;
        Mockito.when(service.getStudent(id)).thenReturn(new Student(id, "Tim"));
        Student newStudent = new Student(id, "John");
        String json = mapper.writeValueAsString(newStudent);
       
        //mock service.save(student) return passed in parameter
        when(service.save(any(Student.class))).thenAnswer(new Answer<Student>() {
            @Override
            public Student answer(InvocationOnMock invocation) throws Throwable {
              Object[] args = invocation.getArguments();
              return (Student) args[0];
            }
          });
        
        MvcResult result = mockMvc.perform(put("/api/student/"+id).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
        assertEquals(mapper.writeValueAsString(newStudent), result.getResponse().getContentAsString());
    }
    
    @Test
    public void testModifyStudentWithInconsistentId() throws Exception {
        int id = 1;
        Student student = new Student(id+1, "John");
        String json = mapper.writeValueAsString(student);
        mockMvc.perform(put("/api/student/"+id).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
    }
    
    @Test
    public void testDeleteStudent() throws Exception {
        int id = 1;
        Student student = new Student(id, "John");
        Mockito.when(service.getStudent(id)).thenReturn(student);
        mockMvc.perform(delete("/api/student/"+id)).andExpect(status().isNoContent());
    }

}
