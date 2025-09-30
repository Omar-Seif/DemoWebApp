package com.backend.demoWebApp.controller;
import com.backend.demoWebApp.model.Instructor;
import com.backend.demoWebApp.model.Student;
import com.backend.demoWebApp.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    @Autowired
    private InstructorService Service;
    @GetMapping("/list")
    public List<Instructor> getInstructors(){
        return Service.getAllInstructors();
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable int employeeId){
        Instructor inst=Service.getInstructor(employeeId);
        if (inst!=null)
            return new ResponseEntity<>(inst,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{employeeId}/update")
    public ResponseEntity<String> updateInstructorById(@PathVariable int employeeId,@RequestPart Instructor inst){
        Instructor inst1 = Service.updateInstructor(employeeId,inst);
        if(inst1!=null)
            return new ResponseEntity<>("Successfully updated instructor",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update instructor",HttpStatus.BAD_REQUEST);
}
@GetMapping("/students")
    public List<Student> InstructorsStudents(){
        return Service.getInstructorsStudents();
    }
@GetMapping("/{employeeId}/department")
        public String instructorDepartment(@PathVariable int employeeId){
    return Service.getInstructorsDepartment(employeeId);
}


}
