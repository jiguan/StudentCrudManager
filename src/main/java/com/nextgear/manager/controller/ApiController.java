package com.nextgear.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nextgear.manager.model.Student;
import com.nextgear.manager.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/student")
public class ApiController {
    private Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    
    @Autowired
    private StudentService service;
    
    public void setService(StudentService service) {
        this.service = service;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    public Student insertNewStudent(@RequestBody Student studnet) {
        LOGGER.info("Save student {}", studnet);
        return service.save(studnet);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents() {
        return service.getAllStudent();
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable("id") int id) {
        return service.getStudent(id);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes="application/json")
    public Student modifyStudent(@PathVariable("id") int id, @RequestBody Student studnet) {
        if(id!=studnet.getId()) throw new RuntimeException("Ids don't match");
        Student exist = service.getStudent(id);
        exist.setName(studnet.getName());
        return service.save(exist);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        service.delete(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }   
    

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeExceptionException(RuntimeException ex) {
        return ex.getMessage();
    }
    
}
