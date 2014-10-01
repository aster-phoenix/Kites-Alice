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
	
	private static final long serialVersionUID = -3872113327996333849L;

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
