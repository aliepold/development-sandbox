package de.aliepold.jpa.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

@NamedQueries({
	@NamedQuery(name = Customer.GET_CUSTOMER, query = "SELECT DISTINCT record FROM Customer record ORDER BY record.name")
})
@GenericGenerator(name = "customer_sequence_id", strategy = "aries.jpa.generator.CustomSequenceIdGenerator")
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_CUSTOMER = "GET_CUSTOMER";

	public Customer(){
		super();
	}

	public Customer(Integer id, String customerId, String name, String title) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.name = name;
		this.title = title;
	}

	@EmbeddedId
	@SequenceGenerator(name = "seq_id", sequenceName = "customer_sequence_id" )
	@GeneratedValue(generator = "seq_id")
	private CustomerPK cid;

	private Integer id;
	
	private String customerId;

	@Field(store = Store.YES)
	private String name;
	
	private String title;
	
	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	private Developer developer;

	public CustomerPK getCid() {
		return this.cid;
	}


	public void setCid(CustomerPK cid) {
		this.cid = cid;
	}


	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public boolean equals(Object obj) {
		return obj instanceof Customer && this.id.equals(((Customer) obj).id);
	}


	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}
