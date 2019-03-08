package de.aliepold.jpa.entities;

import java.io.Serializable;

public class Developer implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_DEVELOPER = "GET_DEVELOPER";

	private Integer id;

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
		return obj instanceof Developer && this.id.equals(((Developer) obj).id);
	}


	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}
