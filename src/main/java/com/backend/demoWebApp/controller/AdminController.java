package com.backend.demoWebApp.controller;

import com.backend.demoWebApp.model.Course;
import com.backend.demoWebApp.model.Instructor2;
import com.backend.demoWebApp.model.Student2;
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

    // Student Management Functions

    @GetMapping("students")
    public List<Student2> getStudents(){
        return service.getStudents();
    }

    @GetMapping("students/{id}")
    public Student2 getStudentById(@PathVariable Long id){
        return service.getStudentById(id);
    }

    @PostMapping("students")
    public ResponseEntity<Student2> addStudent(@RequestBody Student2 student){
        Student2 savedStudent = service.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("students/{id}")
    public Student2 updateStudent(@PathVariable Long id, @RequestBody Student2 student){

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


    // Instructor Management Functions

    @GetMapping("instructors")
    public List<Instructor2> getInstructors(){
        return service.getInstructors();
    }

    @GetMapping("instructors/{id}")
    public Instructor2 getInstructorById(@PathVariable Long id){
        return service.getInstructorById(id);
    }

    @PostMapping("instructors")
    public ResponseEntity<Instructor2> addInstructor(@RequestBody Instructor2 instructor){
        Instructor2 savedInstructor = service.addInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructor);
    }

    @PutMapping("instructors/{id}")
    public Instructor2 updateInstructor(@PathVariable Long id, @RequestBody Instructor2 instructor){
        if (!id.equals(instructor.getId())) {
            throw new IllegalArgumentException("ID in path must match ID in request body");
        }
        return service.updateInstructor(instructor);
    }

    @DeleteMapping("instructors/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id){
        service.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }


    // Course Management Functions

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return service.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = service.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if (!id.equals(course.getId())) {
            throw new IllegalArgumentException("ID in path must match ID in request body");
        }
        return service.updateCourse(course);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }


}