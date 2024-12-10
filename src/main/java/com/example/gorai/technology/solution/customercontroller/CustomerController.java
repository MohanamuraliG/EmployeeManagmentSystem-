package com.example.gorai.technology.solution.customercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gorai.technology.solution.customerservice.CustomerService;
import com.example.gorai.technology.solution.dao.Customer;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/employment-agreements")
@Validated
public class CustomerController {

    @Autowired
    private  CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createEmploymentAgreement(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.addEmploymentAgreement(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getEmploymentAgreement(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmploymentAgreement(@PathVariable String id, @Valid @RequestBody Customer customerUpdateDTO) {
        customerUpdateDTO.setId(id);
        String responseMessage = customerService.updateEmploymentAgreement(customerUpdateDTO);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmploymentAgreement(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Employment agreement deleted successfully for ID: " + id);
    }

    @GetMapping
    public List<Customer> listAllEmploymentAgreements() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/search")
    public List<Customer> searchEmploymentAgreements(@RequestParam String employeeName) {
        return customerService.searchAgreements(employeeName);
    }
}


