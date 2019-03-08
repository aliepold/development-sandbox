package de.aliepold.vo;

import java.math.BigDecimal;

public class CustomerVO {

	
	private Integer id;
	
	private BigDecimal customerNumber;

	public CustomerVO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(BigDecimal customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String fullName;
	
	private String title;
}
