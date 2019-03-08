package de.aliepold.aries.jpa.mapping;

import java.util.List;

import de.aliepold.aries.jpa.mapping.entities.Customer;
import de.aliepold.aries.jpa.mapping.entities.Developer;
import de.aliepold.aries.jpa.mapping.entities.Tester;

public interface MappingService {

	public List<Developer> listAllDeveloper() throws Exception;

	public List<Tester> listAllTester() throws Exception;

	public List<Customer> listAllCustomer() throws Exception;

}
