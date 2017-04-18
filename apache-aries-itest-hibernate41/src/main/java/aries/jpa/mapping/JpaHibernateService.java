package aries.jpa.mapping;

import java.util.List;

import aries.jpa.mapping.entities.Customer;
import aries.jpa.mapping.entities.Developer;
import aries.jpa.mapping.entities.Tester;

public interface JpaHibernateService {

	public List<Developer> listAllDeveloper() throws Exception;

	public List<Tester> listAllTester() throws Exception;

	public List<Customer> listAllCustomer() throws Exception;

}
