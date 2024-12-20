package com.repository;

import com.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;


@Transactional
@Repository
public class CustomerRepository implements ICustomerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveWithStoredProcedure(Customer customer) {
		String sql = "CALL Insert_Customer(:firstName, :lastName)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("firstName", customer.getFirstName());
		query.setParameter("lastName", customer.getLastName());
		int rowAffected = query.executeUpdate();
		return rowAffected != 0;
	}
}
