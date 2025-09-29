package com.backend.demoWebApp.repository;

import com.backend.demoWebApp.model.Instructor2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor2, Long> {

    boolean existsByEmail(String email);


}