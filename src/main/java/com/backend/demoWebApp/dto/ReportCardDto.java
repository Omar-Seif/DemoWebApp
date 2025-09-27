package com.backend.demoWebApp.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReportCardDto {
    // Student Information
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String studentIdNumber;
    private BigDecimal overallGpa;

    // Enrolled Courses
    private List<CourseGradeDto> courses;

    // Constructors
    public ReportCardDto() {
        // No-argument constructor is needed for frameworks like Jackson
    }

    public ReportCardDto(Long userId, String firstName, String lastName, String email, String studentIdNumber, BigDecimal overallGpa, List<CourseGradeDto> courses) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentIdNumber = studentIdNumber;
        this.overallGpa = overallGpa;
        this.courses = courses;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public BigDecimal getOverallGpa() {
        return overallGpa;
    }

    public void setOverallGpa(BigDecimal overallGpa) {
        this.overallGpa = overallGpa;
    }

    public List<CourseGradeDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseGradeDto> courses) {
        this.courses = courses;
    }
}