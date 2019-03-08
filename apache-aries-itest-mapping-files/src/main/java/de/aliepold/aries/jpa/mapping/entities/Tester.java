package de.aliepold.aries.jpa.mapping.entities;

import java.io.Serializable;

public class Tester implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_TESTER = "GET_TESTER";

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
		return obj instanceof Tester && this.id.equals(((Tester) obj).id);
	}


	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}
