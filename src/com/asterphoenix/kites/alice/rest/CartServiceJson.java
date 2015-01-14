package com.asterphoenix.kites.alice.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.asterphoenix.kites.alice.helper.CryptoHelper;
import com.asterphoenix.kites.alice.helper.EncryptedOrderData;
import com.asterphoenix.kites.alice.helper.EncryptedOrderItem;
import com.asterphoenix.kites.model.Order;
import com.asterphoenix.kites.model.Order.OrderStatus;
import com.asterphoenix.kites.model.Order.OrderType;
import com.asterphoenix.kites.model.OrderItem;
import com.asterphoenix.kites.model.Product;

@Path("/json/cart")
@Stateless
public class CartServiceJson {
	
	@PersistenceContext
	EntityManager em;
	@EJB
	CryptoHelper crypto;
	
	@POST
	@Path("addorder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addOrder(EncryptedOrderData eOrder) {
		return Response.ok(decryptOrder(eOrder)).build();
	}
	
	@GET
	@Path("checkorder/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response checkOrder(@PathParam("id") long id) {
		return Response.ok(em.find(Order.class, id).getOrderStatus().toString()).build();
	}
	
	public long decryptOrder(EncryptedOrderData eOrder) {
		Order order = new Order();
		order.setTotalPrice(Float.valueOf(crypto.decodeAES(eOrder.getTotalPrice())));
		order.setCustomerID(Long.valueOf(crypto.decodeAES(eOrder.getCustomerID())));
		order.setOrderType(OrderType.valueOf(crypto.decodeAES(eOrder.getOrderType())));
		order.setShippingAddress(crypto.decodeAES(eOrder.getShippingAddress()));
		List<OrderItem> list = new ArrayList<OrderItem>();
		for (EncryptedOrderItem ei : eOrder.getOrders()) {
			OrderItem i = new OrderItem();
			i.setProductID(Long.valueOf(crypto.decodeAES(ei.getProductID())));
			i.setQty(Float.valueOf(crypto.decodeAES(ei.getQty())));
			i.setProductName(crypto.decodeAES(ei.getProductName()));
			list.add(i);
			Product p = em.find(Product.class, i.getProductID());
			p.setProductQTY(p.getProductQTY() - i.getQty());
		}
		order.setOrders(list);
		order.setOrderDate(LocalDate.now().toString());
		order.setOrderStatus(OrderStatus.Pendding);
		em.persist(order);
		em.flush();
		return order.getOrderID();
	}

}
