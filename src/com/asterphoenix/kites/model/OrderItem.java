package com.asterphoenix.kites.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class OrderItem {

	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long singleOrderID;
	private Product product;
	private int qty;
	private int price;
	@ManyToOne
	private Order order;
	
	public long getSingleOrderID() {
		return singleOrderID;
	}
	public void setSingleOrderID(long singleOrderID) {
		this.singleOrderID = singleOrderID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
