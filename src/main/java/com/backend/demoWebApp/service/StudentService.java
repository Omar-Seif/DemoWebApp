package com.backend.demoWebApp.service;

import com.backend.demoWebApp.dto.CourseGradeDto;
import com.backend.demoWebApp.dto.ReportCardDto;
import com.backend.demoWebApp.dto.StudentUpdateDto;
import com.backend.demoWebApp.exception.ProductNotFoundException; // Assuming you'll rename this to ResourceNotFoundException
import com.backend.demoWebApp.model.Course;
import com.backend.demoWebApp.model.Enrollment;
import com.backend.demoWebApp.model.Student;
import com.backend.demoWebApp.model.User;
import com.backend.demoWebApp.repository.CourseRepository;
import com.backend.demoWebApp.repository.EnrollmentRepository;
import com.backend.demoWebApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;


    /**
     * Generates a report card for a specific student.
     */
    @Transactional(readOnly = true)
    public ReportCardDto getReportCard(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ProductNotFoundException("Student not found with user ID: " + userId));

        List<Enrollment> enrollments = enrollmentRepository.findByStudentUserId(userId);

        // Use Streams (as per requirements) to map enrollments to DTOs
        List<CourseGradeDto> courseGrades = enrollments.stream()
                .map(enrollment -> new CourseGradeDto(
                        enrollment.getCourse().getCourseName(),
                        enrollment.getCourse().getCourseCode(),
                        enrollment.getGrade()
                ))
                .collect(Collectors.toList());

        return buildReportCardDto(student, courseGrades);
    }

    /**
     * Updates a student's personal information.
     */
    @Transactional
    public User updateStudentInfo(Long userId, StudentUpdateDto updateDto) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ProductNotFoundException("Student not found with user ID: " + userId));

        User user = student.getUser();
        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        user.setEmail(updateDto.getEmail());
        // Add password logic if needed (with hashing)

        // The student entity doesn't need to be saved separately if the User is the owner of the relationship
        return student.getUser();
    }


    // Helper method to build the final DTO
    private ReportCardDto buildReportCardDto(Student student, List<CourseGradeDto> courseGrades) {
        ReportCardDto dto = new ReportCardDto();
        dto.setUserId(student.getUser().getId());
        dto.setFirstName(student.getUser().getFirstName());
        dto.setLastName(student.getUser().getLastName());
        dto.setEmail(student.getUser().getEmail());
        dto.setStudentIdNumber(student.getStudentIdNumber());
        dto.setOverallGpa(student.getGpa()); // GPA is taken directly from the students table
        dto.setCourses(courseGrades);
        return dto;
    }
    //course register
    @Transactional
    public String registerCourse(Long userId, Long courseId) {
        // Find student by userId
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new ProductNotFoundException("Student not found with user ID: " + userId));

        // Find course by courseId
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ProductNotFoundException("Course not found with ID: " + courseId));

        // Check if already enrolled
        boolean alreadyEnrolled = enrollmentRepository.findByStudentUserId(userId).stream()
                .anyMatch(enrollment -> enrollment.getCourse().getId().equals(courseId));

        if (alreadyEnrolled) {
            return "Student is already enrolled in this course.";
        }

        // Create new enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(null); // grade can be null initially

        enrollmentRepository.save(enrollment);

        return "Student enrolled in course successfully!";
    }
    @Transactional(readOnly = true)
    public List<String> getStudentCourses(Long userId) {
        return enrollmentRepository.findByStudentUserId(userId).stream()
                .map(enrollment -> enrollment.getCourse().getCourseName()) // you can return whole Course objects if needed
                .collect(Collectors.toList());
    }
    //unregister a course
    public String unregisterCourse(Long userId, Long courseId) {
        // Find student
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + userId));

        // Find course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        // Find the enrollment
        Enrollment enrollment = enrollmentRepository.findByStudentUserId(userId).stream()
                .filter(e -> e.getCourse().getId().equals(courseId))
                .findFirst()
                .orElse(null);

        if (enrollment == null) {
            return "Student is not enrolled in this course.";
        }

        // Delete enrollment
        enrollmentRepository.delete(enrollment);

        return "Student unregistered from course successfully!";
    }




}