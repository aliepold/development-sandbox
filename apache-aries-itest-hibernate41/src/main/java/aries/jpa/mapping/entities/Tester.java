package aries.jpa.mapping.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

@NamedQueries({
	@NamedQuery(name = Tester.GET_TESTER, query = "SELECT DISTINCT record FROM Tester record ORDER BY record.name")
})
@GenericGenerator(name = "custom_tester_sequence_id", strategy = "aries.jpa.generator.CustomSequenceIdGenerator")
@Entity
public class Tester implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_TESTER = "GET_TESTER";

	@Id
	@GeneratedValue(generator = "custom_sequence_id")
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
