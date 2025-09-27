package com.backend.demoWebApp.model;

import java.io.Serializable;
import java.util.Objects;

// This class represents the composite primary key for Enrollment
public class EnrollmentId implements Serializable {
    private Long student; // Must match the field name in the Enrollment entity
    private Long course;  // Must match the field name in the Enrollment entity

    // Default constructor, equals, and hashCode are required
    public EnrollmentId() {}

    public EnrollmentId(Long student, Long course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}