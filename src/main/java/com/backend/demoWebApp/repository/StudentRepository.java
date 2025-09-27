package com.backend.demoWebApp.repository;

import com.backend.demoWebApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Finds a student by their associated user ID
    Optional<Student> findByUserId(Long userId);
}