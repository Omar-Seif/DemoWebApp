package com.backend.demoWebApp.service;

import com.backend.demoWebApp.exception.ConflictException;
import com.backend.demoWebApp.exception.ResourceNotFoundException;
import com.backend.demoWebApp.model.Course;
import com.backend.demoWebApp.model.Instructor2;
import com.backend.demoWebApp.model.Student2;
import com.backend.demoWebApp.repository.CourseRepository;
import com.backend.demoWebApp.repository.InstructorRepository;
import com.backend.demoWebApp.repository.Student2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {


    //Student Management Functions

    @Autowired
    private Student2Repository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;


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


    // Course Management Functions


    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public Course addCourse(Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new ConflictException("Course code already exists: " + course.getCourseCode());
        }
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        Long id = course.getId();
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }


    // Instructor Course Assignment

    public Course assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        Instructor2 instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));

        // Set the bidirectional relationship
        course.setInstructor(instructor);
        instructor.addCourse(course);

        return courseRepository.save(course);
    }

    public Course removeInstructorFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        if (course.getInstructor() == null) {
            throw new ResourceNotFoundException("No instructor assigned to this course");
        }

        // Remove the bidirectional relationship
        Instructor2 instructor = course.getInstructor();
        instructor.removeCourse(course);
        course.setInstructor(null);

        return courseRepository.save(course);
    }

    public List<Course> getCoursesByInstructor(Long instructorId) {
        Instructor2 instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));

        return new ArrayList<>(instructor.getCourses());
    }


}