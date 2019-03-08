package de.aliepold.aries.jpa.mapping.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

@NamedQueries({
	@NamedQuery(name = Customer.GET_CUSTOMER, query = "SELECT DISTINCT record FROM Customer record ORDER BY record.name")
})
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_CUSTOMER = "GET_CUSTOMER";

	public Customer(){

	}

	@Id
	@GeneratedValue
	private Integer id;

	@Field(store = Store.YES)
	private String name;

	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
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
