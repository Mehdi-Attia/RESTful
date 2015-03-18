package edu.restful.services.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import edu.restful.entities.Address;
import edu.restful.entities.Company;
import edu.restful.entities.Employee;
import edu.restful.services.interfaces.EmployeeServiceLocal;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless
public class EmployeeService implements EmployeeServiceLocal {

	@PersistenceContext
	EntityManager em;

	public EmployeeService() {

	}

	@Override
	public Response add(Employee employee) {
		em.persist(employee);
		return Response.ok("Employee " + employee + " created successfully")
				.build();
	}

	@PUT
	@Path("create_3.ws")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addStudentWithoutPicture(MultipartFormDataInput input)
			throws IOException {
		String imagePath = "/usr/local/share/jboss/welcome-content/restful/";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		String givenName = uploadForm.get("givenName").get(0).getBodyAsString();
		String lastName = uploadForm.get("lastName").get(0).getBodyAsString();
		String country = uploadForm.get("country").get(0).getBodyAsString();
		String city = uploadForm.get("city").get(0).getBodyAsString();
		String street = uploadForm.get("street").get(0).getBodyAsString();

		List<InputPart> inputParts = uploadForm.get("picture");
		SecureRandom random = new SecureRandom();
		String randomPath = new BigInteger(130, random).toString(32);
		for (InputPart inputPart : inputParts) {
			try {
				InputStream inputStream = inputPart.getBody(InputStream.class,
						null);
				imagePath += randomPath;
				copyFileUsingFileStreams(inputStream, imagePath + ".jpg");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Address address = new Address(country, city, street);
		Employee employee = new Employee(givenName, lastName, randomPath
				+ "jpg", address);
		em.persist(employee);
		return Response.status(200)
				.entity("Employee " + employee + " created successfully")
				.build();
	}

	@Override
	public Response update(Employee employee) {
		em.merge(employee);
		return Response.ok("Employee " + employee + " created successfully")
				.build();
	}

	@Override
	public Response remove(int idEmployee) {
		Employee employee = em.find(Employee.class, idEmployee);
		if (employee == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(employee);
		return Response.noContent().build();
	}

	@Override
	public Employee findById(int idEmployee) {
		return em.find(Employee.class, idEmployee);
	}

	@Override
	public List<Employee> findAll(int startElement, int maxElements) {
		return em
				.createQuery("SELECT DISTINCT e FROM Employee e",
						Employee.class).setFirstResult(startElement)
				.setMaxResults(maxElements).getResultList();
	}

	@Override
	public List<Employee> findByCompany(int idCompany, int startElement,
			int maxElements) {
		Company company = em.find(Company.class, idCompany);
		return em
				.createQuery(
						"SELECT DISTINCT e FROM Employee e WHERE e.company =: c",
						Employee.class).setFirstResult(startElement)
				.setMaxResults(maxElements).setParameter("c", company)
				.getResultList();
	}

	private void copyFileUsingFileStreams(InputStream input, String filename)
			throws IOException {
		OutputStream output = null;
		try {

			output = new FileOutputStream(filename);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}
}
