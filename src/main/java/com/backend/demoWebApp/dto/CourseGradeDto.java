package com.backend.demoWebApp.dto;

import java.math.BigDecimal;

public class CourseGradeDto {
    private String courseName;
    private String courseCode;
    private BigDecimal grade;

    // Constructors
    public CourseGradeDto() {
        // No-argument constructor is good practice for DTOs
    }

    public CourseGradeDto(String courseName, String courseCode, BigDecimal grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
}