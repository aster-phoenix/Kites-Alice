package com.asterphoenix.kites.alice.helper;

import java.util.List;

public class EncryptedOrderData {
	
	private String customerID;
	private String totalPrice;
	private String orderType;
	private String shippingAddress;
	private List<EncryptedOrderItem> orders;
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public List<EncryptedOrderItem> getOrders() {
		return orders;
	}
	public void setOrders(List<EncryptedOrderItem> orders) {
		this.orders = orders;
	}
}
