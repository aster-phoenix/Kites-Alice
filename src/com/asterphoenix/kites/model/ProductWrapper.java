package com.asterphoenix.kites.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Product.class)
public class ProductWrapper extends ArrayList<Product> {
	
	public ProductWrapper() {
		super();
	}
	
	public ProductWrapper(Collection<? extends Product> p) {
		super(p);
	}
	
	@XmlElement(name="product")
	public List<Product> getProduct() {
		return this;
	}
	
	public void setProduct(List<Product> p) {
		this.addAll(p);
	}

}
