package de.aliepold.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.aliepold.jpa.entities.Customer;
import de.aliepold.vo.CustomerVO;

/**
 * Testklasse zur Demonstration des Mappings.
 * 
 * @author aliepold
 *
 */
public class CustomerMapperAntTest {

	/**
	 * Testfall soll ein Objekt Customer.class nach CustomerVO.class mappen.
	 */
	@Test
	public void testShouldMapCustomerToCustomerVO() {
		
		
	    Customer customer = new Customer(1, "K100", "Liepold, Alexander", "Consultant");
	    
	    //wenn
	    CustomerVO customerVO = CustomerMapper.INSTANCE.customer2VO( customer );
	 
	    //dann
	    assertNotNull( customerVO );
	    assertEquals( customerVO.getId(), 1, 0 );
	    assertEquals( customerVO.getFullName(), "Liepold, Alexander" );
	    assertEquals( customerVO.getTitle(), "Consultant" );
	}
	
	@Test
	public void testShouldMapCustomerVOToCustomer() {
		
		
	    CustomerVO customerVO = new CustomerVO(1, "K100", "Liepold, Alexander", "Consultant");
	    
	    //wenn
	    Customer customer = CustomerMapper.INSTANCE.customerVO2Domain( customerVO );
	 
	    //dann
	    assertNotNull( customer );
	    assertEquals( customer.getId(), 1, 0 );
	    assertEquals( customer.getName(), "Liepold, Alexander" );
	    assertEquals( customer.getTitle(), "Consultant" );
	}

}
