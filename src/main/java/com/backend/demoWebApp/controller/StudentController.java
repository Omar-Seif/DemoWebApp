package com.backend.demoWebApp.controller;

import com.backend.demoWebApp.dto.ReportCardDto;
import com.backend.demoWebApp.dto.StudentUpdateDto;
import com.backend.demoWebApp.model.User;
import com.backend.demoWebApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * GET endpoint to retrieve a student's report card.
     * Usage: GET http://localhost:8080/api/students/1/report-card
     */
    @GetMapping("/{userId}/report-card")
    public ResponseEntity<ReportCardDto> getStudentReportCard(@PathVariable Long userId) {
        ReportCardDto reportCard = studentService.getReportCard(userId);
        return ResponseEntity.ok(reportCard);
    }

    /**
     * PUT endpoint to update a student's information.
     * Usage: PUT http://localhost:8080/api/students/1
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateStudentInfo(@PathVariable Long userId, @RequestBody StudentUpdateDto studentUpdateDto) {
        User updatedUser = studentService.updateStudentInfo(userId, studentUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }
}