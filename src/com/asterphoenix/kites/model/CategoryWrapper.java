package com.asterphoenix.kites.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Category.class)
public class CategoryWrapper extends ArrayList<Category> {
	
	public CategoryWrapper() {
		super();
	}
	
	public CategoryWrapper(Collection<? extends Category> c) {
		super(c);
	}
	
	@XmlElement(name="category")
	public List<Category> getCategoy() {
		return this;
	}

	public void setCategoy(List<Category> c) {
		this.addAll(c);
	}

}
