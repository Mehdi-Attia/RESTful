package edu.restful.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@XmlRootElement
@Table(name = "t_address")
public class Address implements Serializable{

	private int id_address;
	private String country;
	private String city;
	private String street;

	private Company company;
	private Employee employee;

	private static final long serialVersionUID = 1L;

	public Address() {
		super();
	}

	public Address(String country, String city, String street) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId_address() {
		return this.id_address;
	}

	public void setId_address(int id_address) {
		this.id_address = id_address;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@OneToOne(mappedBy = "address")
	@XmlTransient
	@JsonIgnore
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@OneToOne(mappedBy = "address")
	@XmlTransient
	@JsonIgnore
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Adress [country=" + country + ", city=" + city + ", street="
				+ street + "]";
	}

}
