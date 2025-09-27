package com.backend.demoWebApp.repository;

import com.backend.demoWebApp.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Finds all enrollments for a given student's user ID
    List<Enrollment> findByStudentUserId(Long userId);
}