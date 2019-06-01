package Model;

import javax.persistence.*;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String city;

	@Column
	private String street;

	@Column
	private Integer apartment;

	public Address() {
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getApartment() {
		return apartment;
	}

	public void setApartment(Integer apartment) {
		this.apartment = apartment;
	}

	public Address(String city, String street, Integer apartment) {
		this.city = city;
		this.street = street;
		this.apartment = apartment;
	}

}
