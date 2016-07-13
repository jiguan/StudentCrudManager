package com.nextgear.manager.component;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nextgear.manager.model.Student;
import com.nextgear.manager.service.StudentService;

@Component
public class SampleData implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(SampleData.class);

    @Autowired
    private StudentService service;

    @Override
    public void run(String... args) {
        LOGGER.info("Load sample data");
        List<Student> students = new LinkedList<>();
        students.add(new Student("John"));
        students.add(new Student("Rob"));
        students.add(new Student("Tim"));
        students.add(new Student("Mike"));
        for (Student student : students) {
            service.save(student);
        }
    }
}
