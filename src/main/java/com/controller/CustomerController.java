package com.controller;

import com.model.Customer;
import com.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping
	public String listCustomers(Model model) {
		model.addAttribute("customer", new Customer());
		return "create";
	}

	@PostMapping("/create")
	public String createCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		boolean success = customerService.saveWithStoredProcedure(customer);
		if (success) {
			model.addAttribute("message", "Customer added successfully");
		} else {
			model.addAttribute("message", "Customer not added!");
		}
		return "create";
	}
}
