package com.backend.demoWebApp.service;
import com.backend.demoWebApp.model.Student;
import com.backend.demoWebApp.repository.InstructorRepository2;
import com.backend.demoWebApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.demoWebApp.model.Instructor;


import java.util.List;
import java.util.stream.Collectors;

@Service
    public class InstructorService {
        @Autowired
        private InstructorRepository2 repo;

        @Autowired
        private StudentRepository studentRepo;

        public Instructor getInstructor(int employeeIdNumber) {
            return repo.findById(employeeIdNumber).orElse(null);

        }
        public List<Instructor> getAllInstructors() {
            return repo.findAll();

        }
        public Instructor updateInstructor(int employeeIdNumber, Instructor inst) {
            return repo.save(inst);
        }


        public List<Student> getInstructorsStudents() { //used lambda expression with Big Decimal and stream
        return studentRepo.findAll()        //and collectors class to turn the stream back
                .stream()                   //into a list (found it on the web and thought it was useful)
                .sorted((s1, s2) -> s2.getGpa().compareTo(s1.getGpa())) // lambda with BigDecimal
                .collect(Collectors.toList());
        }

    public String getInstructorsDepartment(int employeeIdNumber) {
        return repo.findById(employeeIdNumber)
                .map(instructor -> instructor.getDepartment()) // Map the entity to its department String
                .orElse(null); // Return null if the Optional is empty
    }
}

