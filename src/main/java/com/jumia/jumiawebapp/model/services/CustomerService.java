package com.jumia.jumiawebapp.model.services;

import com.jumia.jumiawebapp.model.domain.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
}
