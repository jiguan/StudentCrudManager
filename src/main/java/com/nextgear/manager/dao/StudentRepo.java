package com.nextgear.manager.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgear.manager.model.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {

}
