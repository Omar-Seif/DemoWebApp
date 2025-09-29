package com.backend.demoWebApp.service;

import com.backend.demoWebApp.exception.ConflictException;
import com.backend.demoWebApp.exception.ResourceNotFoundException;
import com.backend.demoWebApp.model.Student;
import com.backend.demoWebApp.model.Student2;
import com.backend.demoWebApp.repository.Student2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {


    //Student Management Functions

    @Autowired
    private Student2Repository studentRepository;

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

}