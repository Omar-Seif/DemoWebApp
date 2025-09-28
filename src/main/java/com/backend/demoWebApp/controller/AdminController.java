package com.backend.demoWebApp.controller;

import com.backend.demoWebApp.model.Student;
import com.backend.demoWebApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping("students")
    public List<Student> getStudents(){
        return service.getStudents();
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable Long id){
        return service.getStudentById(id);
    }

    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student savedStudent = service.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){

        if (!id.equals(student.getId())) {
            throw new IllegalArgumentException("ID in path must match ID in request body");
        }
        return service.updateStudent(student);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
