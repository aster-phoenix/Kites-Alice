package com.asterphoenix.kites.alice.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.asterphoenix.kites.model.Customer;

@Path("/json/customer")
@Stateless
public class CustomerServiceJson {
	
	@PersistenceContext
	EntityManager em;
	
	@POST
	@Path("addcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCustomer(Customer customer) {
		em.persist(customer);
		em.flush();
		return Response.ok(String.valueOf(customer.getCustomerID())).build();
	}
	
	@POST
	@Path("logcustomerin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logCustomerIn(Customer customer) {
		TypedQuery<Customer> q = em.createQuery(
				"select c from Customer c where"
				+ " c.customerUsername = :username and c.customerPassword = :password", Customer.class);
		q.setParameter("username", customer.getCustomerUsername());
		q.setParameter("password", customer.getCustomerPassword());
		customer = q.getSingleResult();
		if (null != customer) {
			return Response.ok(customer).build();
		}
		return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response customerByID(@PathParam("id")long id) {
		Customer customer = em.find(Customer.class, id);
		return Response.ok(customer).build();
	}

}
