package com.backend.demoWebApp.service;

import com.backend.demoWebApp.exception.ConflictException;
import com.backend.demoWebApp.exception.ResourceNotFoundException;
import com.backend.demoWebApp.model.Student;
import com.backend.demoWebApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {


    //Student Management Functions

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student addStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student){
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
