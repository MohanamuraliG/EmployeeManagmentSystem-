package com.example.gorai.technology.solution.customerRepo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.gorai.technology.solution.dao.Customer;

@Repository
public interface CustomerRepo extends MongoRepository<Customer, String> {
    List<Customer> findByEmployeeNameContainingIgnoreCase(String employeeName);
}
