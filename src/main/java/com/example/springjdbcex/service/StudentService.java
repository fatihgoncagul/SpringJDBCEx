package com.example.springjdbcex.service;

import com.example.springjdbcex.model.Student;
import com.example.springjdbcex.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepo repo;

    public StudentRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public void addStudent(Student s) {
        System.out.println("addStudent called in StudentService");
        repo.save(s);
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }
}
