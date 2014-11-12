package com.asterphoenix.kites.model;

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
public class OrderItem {

	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long singleOrderID;
	private long productID;
	private float qty;
	
	public long getSingleOrderID() {
		return singleOrderID;
	}
	public void setSingleOrderID(long singleOrderID) {
		this.singleOrderID = singleOrderID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProduct(long productID) {
		this.productID = productID;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	
}
