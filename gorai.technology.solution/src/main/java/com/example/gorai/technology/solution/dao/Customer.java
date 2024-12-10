package com.example.gorai.technology.solution.dao;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document
public class Customer {

    @Id
    private String id;

    @NotBlank(message = "Employee name is mandatory.")
    private String employeeName;

    @NotBlank(message = "Role is mandatory.")
    private String role;

    @NotNull(message = "Start date is mandatory.")
    private String startDate;

    @NotNull(message = "End date is mandatory.")
    private String endDate;

    @NotNull(message = "Salary is mandatory.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero.")
    private Double salary;

    @NotBlank(message = "Term is mandatory.")
    private String term;

    private String otherDetail;
    private String department;
    private String manager;

    private LocalDate createdDate;
    private LocalDate updatedDate;

    // Default Constructor
    public Customer() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getOtherDetail() {
        return otherDetail;
    }

    public void setOtherDetail(String otherDetail) {
        this.otherDetail = otherDetail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
