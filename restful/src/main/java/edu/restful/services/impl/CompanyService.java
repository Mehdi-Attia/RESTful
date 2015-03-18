package edu.restful.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.restful.entities.Company;
import edu.restful.services.interfaces.CompanyServiceLocal;

/**
 * Session Bean implementation class CompanyService
 */
@Stateless
public class CompanyService implements CompanyServiceLocal {

	@PersistenceContext
	EntityManager em;

	public CompanyService() {
	}

	@Override
	public Response add(Company company) {
		em.persist(company);
		return Response.ok("Company " + company + " created successfully")
				.build();
	}

	@Override
	public Response update(Company company) {
		em.merge(company);
		return Response.ok("Company " + company + " created successfully")
				.build();
	}

	@Override
	public Response remove(int idCompany) {
		Company company = em.find(Company.class, idCompany);
		if (company == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(company);
		return Response.noContent().build();
	}

	@Override
	public Company findById(int idCompany) {
		return em.find(Company.class, idCompany);
	}

	@Override
	public List<Company> findAll(int startElement, int maxElements) {
		return em
				.createQuery("SELECT DISTINCT c FROM Company c", Company.class)
				.setFirstResult(startElement).setMaxResults(maxElements)
				.getResultList();
	}

}
