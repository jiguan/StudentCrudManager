package com.nextgear.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Student() {}

    public Student(String name) {
        this.name = name;
    }

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int prime = 7;
        int hashcode = 1;
        hashcode = hashCode() * prime + (id==null ? 0 : id.hashCode());
        hashcode = hashCode() * prime + (name==null ? 0 :name.hashCode());
        return hashcode;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Student student = (Student) object;
            if (this.id == student.id && this.name == student.name) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Student(" + id + ", " + name + ")";
    }
}
