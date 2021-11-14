package com.jumia.jumiawebapp;

import com.jumia.jumiawebapp.model.domain.CountryPhoneNumberPatterns;
import com.jumia.jumiawebapp.model.domain.Customer;
import com.jumia.jumiawebapp.model.domain.CustomerDTO;
import com.jumia.jumiawebapp.model.services.CustomerService;
import com.jumia.jumiawebapp.repositories.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void getAllCustomers_EmptyList() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        List<CustomerDTO>customers= customerService.getAllCustomers();
        Assertions.assertThat(customers.isEmpty()).isTrue();
    }

    @Test
    void getAllCustomers_WithValidNumbers() {
        Customer valid1 = createCustomer(111,"Nada Sofie","(212) 698054317");
        Customer valid2 = createCustomer(222, "Tanvi Sachdeva", "(258) 847602609");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(valid1,valid2));
        List<CustomerDTO>customers= customerService.getAllCustomers();
        Assertions.assertThat(customers.isEmpty()).isFalse();
        Assertions.assertThat(customers.get(0).getId()).isEqualTo(valid1.getId());
        Assertions.assertThat(customers.get(0).getName()).isEqualTo(valid1.getName());
        Assertions.assertThat(customers.get(0).getNumber()).isEqualTo("698054317");
        Assertions.assertThat(customers.get(0).getCountry_code()).isEqualTo(CountryPhoneNumberPatterns.Morocco.getCountry_code());
        Assertions.assertThat(customers.get(0).getCountry()).isEqualTo(CountryPhoneNumberPatterns.Morocco.name());
        Assertions.assertThat(customers.get(0).getValid()).isTrue();
        Assertions.assertThat(customers.get(1).getId()).isEqualTo(valid2.getId());
        Assertions.assertThat(customers.get(1).getName()).isEqualTo(valid2.getName());
        Assertions.assertThat(customers.get(1).getNumber()).isEqualTo("847602609");
        Assertions.assertThat(customers.get(1).getCountry_code()).isEqualTo(CountryPhoneNumberPatterns.Mozambique.getCountry_code());
        Assertions.assertThat(customers.get(1).getCountry()).isEqualTo(CountryPhoneNumberPatterns.Mozambique.name());
        Assertions.assertThat(customers.get(1).getValid()).isTrue();
    }

    @Test
    void getAllCustomers_WithInValidNumbers() {
        Customer invalid1 = createCustomer(111,"ARREYMANYOR ROLAND TABOT","(237) 6A0311634");
        Customer invalid2 = createCustomer(222, "WILLIAM KEMFANG", "(237) 6622284920");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(invalid1,invalid2));
        List<CustomerDTO>customers= customerService.getAllCustomers();
        Assertions.assertThat(customers.isEmpty()).isFalse();
        Assertions.assertThat(customers.get(0).getId()).isEqualTo(invalid1.getId());
        Assertions.assertThat(customers.get(0).getName()).isEqualTo(invalid1.getName());
        Assertions.assertThat(customers.get(0).getNumber()).isEqualTo("(237) 6A0311634");
        Assertions.assertThat(customers.get(0).getCountry_code()).isEqualTo(null);
        Assertions.assertThat(customers.get(0).getCountry()).isEqualTo(null);
        Assertions.assertThat(customers.get(0).getValid()).isFalse();
        Assertions.assertThat(customers.get(1).getId()).isEqualTo(invalid2.getId());
        Assertions.assertThat(customers.get(1).getName()).isEqualTo(invalid2.getName());
        Assertions.assertThat(customers.get(1).getNumber()).isEqualTo("(237) 6622284920");
        Assertions.assertThat(customers.get(1).getCountry_code()).isEqualTo(null);
        Assertions.assertThat(customers.get(1).getCountry()).isEqualTo(null);
        Assertions.assertThat(customers.get(1).getValid()).isFalse();
    }

    @Test
    void getAllCustomers_WithValidAndInvalidNumbers() {
        Customer valid1 = createCustomer(111,"Yosaf Karrouch","(212) 698054317");
        Customer invalid2 = createCustomer(222, "WILLIAM KEMFANG", "(237) 6622284920");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(valid1,invalid2));
        List<CustomerDTO>customers= customerService.getAllCustomers();
        Assertions.assertThat(customers.isEmpty()).isFalse();
        Assertions.assertThat(customers.get(0).getId()).isEqualTo(valid1.getId());
        Assertions.assertThat(customers.get(0).getName()).isEqualTo(valid1.getName());
        Assertions.assertThat(customers.get(0).getNumber()).isEqualTo("698054317");
        Assertions.assertThat(customers.get(0).getCountry_code()).isEqualTo(CountryPhoneNumberPatterns.Morocco.getCountry_code());
        Assertions.assertThat(customers.get(0).getCountry()).isEqualTo(CountryPhoneNumberPatterns.Morocco.name());
        Assertions.assertThat(customers.get(0).getValid()).isTrue();
        Assertions.assertThat(customers.get(1).getId()).isEqualTo(invalid2.getId());
        Assertions.assertThat(customers.get(1).getName()).isEqualTo(invalid2.getName());
        Assertions.assertThat(customers.get(1).getNumber()).isEqualTo("(237) 6622284920");
        Assertions.assertThat(customers.get(1).getCountry_code()).isEqualTo(null);
        Assertions.assertThat(customers.get(1).getCountry()).isEqualTo(null);
        Assertions.assertThat(customers.get(1).getValid()).isFalse();
    }


    private Customer createCustomer(int id,String name,String number) {
        return new Customer(id, name, number);
    }
}
