package edu.restful.services.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.restful.entities.Employee;

@Local
@Path("employee")
public interface EmployeeServiceLocal {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response add(Employee employee);

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response update(Employee employee);

	@DELETE
	@Path("/{idEmployee:[0-9][0-9]*}")
	Response remove(@PathParam("idEmployee") int idEmployee);

	@GET
	@Path("/{idEmployee:[0-9][0-9]*}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Employee findById(@PathParam("idEmployee") int idEmployee);

	@GET
	@Path("findByCompany/{idCompany:[0-9][0-9]*}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Employee> findByCompany(@PathParam("idCompany") int idCompany,
			@QueryParam("startElement") int startElement,
			@QueryParam("max") int max);

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Employee> findAll(@QueryParam("startElement") int startElement,
			@QueryParam("max") int max);
}
