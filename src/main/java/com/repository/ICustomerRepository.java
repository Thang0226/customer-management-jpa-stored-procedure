package com.repository;

import com.model.Customer;

public interface ICustomerRepository {
	boolean saveWithStoredProcedure(Customer customer);
}
