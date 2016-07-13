package com.nextgear.manager.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgear.manager.dao.StudentRepo;
import com.nextgear.manager.model.Student;

@Service
public class StudentService {
    private Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepo repo;

    public Student getStudent(int id) {
        return repo.findOne(id);
    }
    
    public List<Student> getAllStudent() {
        LinkedList<Student> result = new LinkedList<>();
        for(Student student : repo.findAll()) {
            result.add(student);
        }
        return result;
    }
    
    public Student save(Student student) {
        return repo.save(student);
    }
    
    public void delete(int id) {
        Student student = getStudent(id);
        if(student==null) throw new RuntimeException("No student exists");
        LOGGER.warn("Delete student {}", student);
        repo.delete(student);
    }



}
