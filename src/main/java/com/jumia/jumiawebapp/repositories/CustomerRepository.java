package com.jumia.jumiawebapp.repositories;

import com.jumia.jumiawebapp.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
