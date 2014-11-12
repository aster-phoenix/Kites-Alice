package com.asterphoenix.kites.alice.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.asterphoenix.kites.model.Category;
import com.asterphoenix.kites.model.CategoryWrapper;
import com.asterphoenix.kites.model.Product;
import com.asterphoenix.kites.model.ProductWrapper;

@Path("/json/catalog")
@Stateless
public class CatalogServiceJson {
	
	@PersistenceContext
	EntityManager em;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCatalogs() {
		TypedQuery<Category> q = em.createQuery("select c from Category c", Category.class);
		CategoryWrapper w = new CategoryWrapper(q.getResultList());
		return Response.ok(w).build();	
	}
	
	@GET
	@Path("products")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		TypedQuery<Product> q =
				em.createQuery("select p from Product p where p.productQTY > 0", Product.class);
		ProductWrapper w = new ProductWrapper(q.getResultList());
		return Response.ok(w).build();
	}
	
	@GET
	@Path("products/bycatalog/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsByCategory(@PathParam("category") String categoryName){
		TypedQuery<Product> q =
				em.createQuery("select p from Product p where p.category.categoryName = :name"
						+ " and p.productQTY > 0", Product.class);
		q.setParameter("name", categoryName);
		ProductWrapper w = new ProductWrapper(q.getResultList());
		return Response.ok(w).build();
	}
	
	@GET
	@Path("products/byname/{product}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsByName(@PathParam("product") String productName){
		TypedQuery<Product> q =
				em.createQuery("select p from Product p where p.productQTY > 0", Product.class);
		List<Product> list = new ArrayList<Product>();
		for (Product p : q.getResultList()) {
			if (p.getProductName().toLowerCase().contains(productName)) {
				list.add(p);
			}
		}
		ProductWrapper w = new ProductWrapper(list);
		return Response.ok(w).build();
	}
	
	@GET
	@Path("products/byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsById(@PathParam("id") int productID){
		return Response.ok(em.find(Product.class, productID)).build();
	}
	
}
