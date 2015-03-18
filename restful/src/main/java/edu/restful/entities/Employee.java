package edu.restful.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity
@XmlRootElement
@Table(name = "t_employee")
public class Employee implements Serializable {

	private int idEmployee;
	private String givenName;
	private String lastName;
	private String imagePath;

	private Address address;
	private Company company;

	private static final long serialVersionUID = 1L;

	public Employee() {
		super();
	}

	public Employee(String givenName, String lastName, Address address) {
		super();
		this.givenName = givenName;
		this.lastName = lastName;
		this.address = address;
	}

	public Employee(String givenName, String lastName, String imagePath,
			Address address) {
		super();
		this.givenName = givenName;
		this.lastName = lastName;
		this.imagePath = imagePath;
		this.address = address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_employee")
	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	@Column(name = "given_name")
	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "image_path")
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_fk", unique = true)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn(name = "company_fk")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Employee [givenName=" + givenName + ", lastName=" + lastName
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmployee;
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
		Employee other = (Employee) obj;
		if (idEmployee != other.idEmployee)
			return false;
		return true;
	}

}
