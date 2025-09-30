package com.backend.demoWebApp.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // This links the primary key of this entity to the User entity's key
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "employee_id_number", unique = true)
    private String employeeIdNumber;

    private String department;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

//    @OneToMany(mappedBy = "instructor")
//    private Set<Course> courses;

    // Constructors
    public Instructor() {
        // JPA requires a no-argument constructor
    }

    public Instructor(User user, String employeeIdNumber, String department, Date hireDate) {
        this.user = user;
        this.employeeIdNumber = employeeIdNumber;
        this.department = department;
        this.hireDate = hireDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    public void setEmployeeIdNumber(String employeeIdNumber) {
        this.employeeIdNumber = employeeIdNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
}