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

import edu.restful.entities.Company;

@Local
@Path("company")
public interface CompanyServiceLocal {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response add(Company company);

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response update(Company company);

	@DELETE
	@Path("/{idCompany:[0-9][0-9]*}")
	Response remove(@PathParam("idCompany") int idCompany);

	@GET
	@Path("/{idCompany:[0-9][0-9]*}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Company findById(@PathParam("idCompany") int idCompany);

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Company> findAll(@QueryParam("startElement") int startElement,
			@QueryParam("max") int maxElements);

}
