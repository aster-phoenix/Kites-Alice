package com.asterphoenix.kites.alice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.asterphoenix.kites.alice.rest.CatalogServiceJson;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
	
	private Set<Class<?>> classes;
	
	public ApplicationConfig() {
		HashSet<Class<?>> c = new HashSet<>();
		c.add(CatalogServiceJson.class);
		c.add(JacksonFeature.class);
		
		classes = Collections.unmodifiableSet(c);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}
