package com.asterphoenix.kites.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Order {

	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderID;
	private float totalPrice;
	private OrderStatus orderStatus;
	private Set<OrderItem> orders;

	public enum OrderStatus {
		New, Completed, Rejected
	}
	
	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Set<OrderItem> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderItem> orders) {
		this.orders = orders;
	}
	
	//add one Single order method

}

