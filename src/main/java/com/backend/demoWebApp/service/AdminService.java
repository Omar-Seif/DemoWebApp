package com.backend.demoWebApp.service;

import com.backend.demoWebApp.exception.ConflictException;
import com.backend.demoWebApp.exception.ResourceNotFoundException;
import com.backend.demoWebApp.model.Instructor2;
import com.backend.demoWebApp.model.Student2;
import com.backend.demoWebApp.repository.InstructorRepository;
import com.backend.demoWebApp.repository.Student2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {


    //Student Management Functions

    @Autowired
    private Student2Repository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;


    public List<Student2> getStudents(){
        return studentRepository.findAll();
    }

    public Student2 getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student2 addStudent(Student2 student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    public Student2 updateStudent(Student2 student){
        Long id = student.getId();
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }


    // Instructor Management Functions


    public List<Instructor2> getInstructors(){
        return instructorRepository.findAll();
    }

    public Instructor2 getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));
    }

    public Instructor2 addInstructor(Instructor2 instructor) {
        if (instructorRepository.existsByEmail(instructor.getEmail())) {
            throw new ConflictException("Email already exists: " + instructor.getEmail());
        }
        return instructorRepository.save(instructor);
    }

    public Instructor2 updateInstructor(Instructor2 instructor){
        Long id = instructor.getId();
        if (!instructorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instructor not found with id: " + id);
        }
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id){
        if (!instructorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instructor not found with id: " + id);
        }
        instructorRepository.deleteById(id);
    }

}