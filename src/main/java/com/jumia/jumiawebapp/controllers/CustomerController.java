package com.jumia.jumiawebapp.controllers;

import com.jumia.jumiawebapp.model.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping("/")
    public String getCustomers(Model model){

        model.addAttribute("customers",customerService.getAllCustomers());

        return "customers/list";
    }

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
