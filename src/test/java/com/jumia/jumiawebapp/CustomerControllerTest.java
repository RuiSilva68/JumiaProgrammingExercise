package com.jumia.jumiawebapp;

import com.jumia.jumiawebapp.controllers.CustomerController;
import com.jumia.jumiawebapp.model.domain.Customer;
import com.jumia.jumiawebapp.model.domain.CustomerDTO;
import com.jumia.jumiawebapp.model.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Test
    void getAllCustomers_EmptyList() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(new CustomerController(customerService))
            .build();

        when(customerService.getAllCustomers()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("customers"));

        ResultActions result = mockMvc.perform(get("/"));
        MvcResult mvcResult = result.andExpect(status().isOk()).andReturn();
    }

    @Test
    void getAllCustomers_WithValidNumber() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(new CustomerController(customerService))
                .build();

        CustomerDTO cDTO = createCustomer(1,"Nada Sofie","(251) 698054317");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(cDTO));

        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("customers"));

        ResultActions result = mockMvc.perform(get("/"));
        MvcResult mvcResult = result.andExpect(status().isOk()).andReturn();

    }

    @Test
    void getAllCustomers_InvalidUrl() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(new CustomerController(customerService))
                .build();

        ResultActions result = mockMvc.perform(get("/new"));
        MvcResult mvcResult = result.andExpect(status().isNotFound()).andReturn();

    }

    private CustomerDTO createCustomer(int id, String name, String number ) {
        return new CustomerDTO(id,name,number,"+251","Morroco",true);
    }
}
