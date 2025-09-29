package com.backend.demoWebApp.repository;

import com.backend.demoWebApp.model.Student2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student2Repository extends JpaRepository<Student2, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

}