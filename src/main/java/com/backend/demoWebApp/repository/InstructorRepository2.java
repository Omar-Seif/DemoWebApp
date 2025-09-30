package com.backend.demoWebApp.repository;

import com.backend.demoWebApp.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository2 extends JpaRepository<Instructor,Integer> {

}
