package com.asterphoenix.kites.alice.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.asterphoenix.kites.model.Order;

@Path("/json/cart")
@Stateless
public class CartServiceJson {
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Path("addorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addOrder(Order order) {
		em.persist(order);
		return Response.ok(String.valueOf(order.getOrderID())).build();
	}

}
