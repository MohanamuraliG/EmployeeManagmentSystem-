package com.example.gorai.technology.solution.customerservice;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gorai.technology.solution.customerRepo.CustomerRepo;
import com.example.gorai.technology.solution.dao.Customer;

import jakarta.validation.ValidationException;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepo customerRepo;

    public Customer addEmploymentAgreement(Customer customer) {
        validateBusinessRules(customer);
        logger.info("Adding a new employment agreement: {}", customer);
        customer.setCreatedDate(LocalDate.now());
        customer.setUpdatedDate(LocalDate.now());
        return customerRepo.save(customer);
    }

    public Customer getCustomerById(String id) {
        logger.debug("Fetching employment agreement with ID: {}", id);
        return customerRepo.findById(id)
                .orElseThrow(() -> new ValidationException("Agreement not found for ID: " + id));
    }

    public List<Customer> getAllCustomer() {
        logger.info("Fetching all employment agreements.");
        return customerRepo.findAll();
    }

    public List<Customer> searchAgreements(String employeeName) {
        logger.debug("Searching employment agreements for employee name: {}", employeeName);
        return customerRepo.findByEmployeeNameContainingIgnoreCase(employeeName);
    }

    public String updateEmploymentAgreement(Customer customerUpdateDTO) {
        validateBusinessRules(customerUpdateDTO);
        logger.info("Updating employment agreement with ID: {}", customerUpdateDTO.getId());
        if (customerRepo.existsById(customerUpdateDTO.getId())) {
            Customer existingCustomer = customerRepo.findById(customerUpdateDTO.getId()).orElse(null);
            if (existingCustomer != null) {
                updateCustomerDetails(existingCustomer, customerUpdateDTO);
                customerRepo.save(existingCustomer);
                logger.info("Successfully updated employment agreement with ID: {}", customerUpdateDTO.getId());
                return "Customer updated successfully!";
            }
        }
        logger.warn("Failed to update employment agreement with ID: {}", customerUpdateDTO.getId());
        throw new ValidationException("Agreement not found for ID: " + customerUpdateDTO.getId());
    }

    public boolean deleteCustomer(String id) {
        logger.info("Attempting to delete employment agreement with ID: {}", id);
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            logger.info("Successfully deleted employment agreement with ID: {}", id);
            return true;
        }
        logger.warn("Employment agreement with ID: {} not found.", id);
        throw new ValidationException("Agreement not found for ID: " + id);
    }

    private void validateBusinessRules(Customer customer) {
        validateDates(customer.getStartDate(), customer.getEndDate());
        if (customer.getSalary() <= 0) {
            throw new ValidationException("Salary must be greater than zero.");
        }
    }

    private void validateDates(String startDate, String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            if (end.isBefore(start)) {
                throw new ValidationException("End date must be after the start date.");
            }
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format. Use 'YYYY-MM-DD'.");
        }
    }

    private void updateCustomerDetails(Customer existingCustomer, Customer customerUpdateDTO) {
        existingCustomer.setEmployeeName(customerUpdateDTO.getEmployeeName());
        existingCustomer.setRole(customerUpdateDTO.getRole());
        existingCustomer.setStartDate(customerUpdateDTO.getStartDate());
        existingCustomer.setEndDate(customerUpdateDTO.getEndDate());
        existingCustomer.setSalary(customerUpdateDTO.getSalary());
        existingCustomer.setTerm(customerUpdateDTO.getTerm());
        existingCustomer.setOtherDetail(customerUpdateDTO.getOtherDetail());
        existingCustomer.setDepartment(customerUpdateDTO.getDepartment());
        existingCustomer.setManager(customerUpdateDTO.getManager());
        existingCustomer.setUpdatedDate(LocalDate.now());
    }
}

