package de.aliepold.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "code1", nullable = false)
	private String code1;

	@Column(name = "code2", nullable = false)
	private String code2;

	public String getCode1() {
		return this.code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return this.code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.code1 == null ? 0 : this.code1.hashCode());
		result = prime * result + (this.code2 == null ? 0 : this.code2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CustomerPK)) {
			return false;
		}
		CustomerPK other = (CustomerPK) obj;
		if (this.code1 == null) {
			if (other.code1 != null) {
				return false;
			}
		} else if (!this.code1.equals(other.code1)) {
			return false;
		}
		if (this.code2 == null) {
			if (other.code2 != null) {
				return false;
			}
		} else if (!this.code2.equals(other.code2)) {
			return false;
		}
		return true;
	}

}
