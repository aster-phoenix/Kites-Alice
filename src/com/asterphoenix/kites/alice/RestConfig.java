package com.asterphoenix.kites.alice;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestConfig extends ResourceConfig {
	
	public RestConfig() {
		packages("com.asterphoenix.kites.alice.rest");
	}

}
