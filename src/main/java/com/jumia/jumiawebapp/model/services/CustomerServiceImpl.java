package com.jumia.jumiawebapp.model.services;

import com.jumia.jumiawebapp.model.domain.CountryPhoneNumberPatterns;
import com.jumia.jumiawebapp.model.domain.Customer;
import com.jumia.jumiawebapp.model.domain.CustomerDTO;
import com.jumia.jumiawebapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return parseNumber(customers);
    }

    public List<CustomerDTO> parseNumber(List<Customer> customers){
        return customers.stream()
                .map(customer->validatePhoneNumber(customer))
                .collect(Collectors.toList());
    }

    private CustomerDTO validatePhoneNumber(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();

        Arrays.asList(CountryPhoneNumberPatterns.values()).forEach(
                pattern -> {
                    if (Pattern.matches(pattern.getRegex(), customer.getPhone())) {
                        customerDTO.setId(customer.getId());
                        customerDTO.setName(customer.getName());
                        customerDTO.setNumber(customer.getPhone().substring(6));
                        customerDTO.setCountry_code(pattern.getCountry_code());
                        customerDTO.setCountry(pattern.name());
                        customerDTO.setValid(true);
                    }
                });
        if (Objects.isNull(customerDTO.getValid())) {
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setNumber(customer.getPhone());
            customerDTO.setValid(false);
        }

        return customerDTO;
    }
}
