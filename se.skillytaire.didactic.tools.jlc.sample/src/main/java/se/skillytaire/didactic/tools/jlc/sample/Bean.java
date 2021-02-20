package se.skillytaire.didactic.tools.jlc.sample;

import java.io.Serializable;

public class Bean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private boolean enabled;
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Bean() {}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean other = (Bean) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public boolean hasName() {
		return name != null;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public void setName(String name, String harry) {
//		this.name = name;
//	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return "Bean [name=" + name + ", surname=" + surname + "]";
	}

}
