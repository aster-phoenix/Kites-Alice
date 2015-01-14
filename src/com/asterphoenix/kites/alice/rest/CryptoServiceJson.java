package com.asterphoenix.kites.alice.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.asterphoenix.kites.alice.helper.CryptoHelper;
import com.asterphoenix.kites.alice.helper.HandShakeData;

@Path("/json/crypto")
@Stateless
public class CryptoServiceJson {
	
	@PersistenceContext
	EntityManager em;
	@EJB
	CryptoHelper crypto;
	
	@POST
	@Path("handshake")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handShake(HandShakeData data) {
		String secretKey  = crypto.getSecretKeyAsString(crypto.getPublicKeyFromString(data.getClientPublicKey()));
		data.setRemotePublicKey(crypto.getPublicAsString());
		data.setIv(crypto.getIvAsString());
		data.setSecretKey(secretKey);
		return Response.ok(data).build();
	}
//	
//	@POST
//	@Path("getsecret")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response getSecret(String publicKeyString) {
//		PublicKey pk = crypto.getPublicKeyFromString(publicKeyString);
//		String encodedKey =
//				crypto.encodeRSAPublic(crypto.encodeRSAPrivate(crypto.getSecretKeyAsString()), pk);
//		return Response.ok(encodedKey).build();
//	}
//	
//	@GET
//	@Path("getpublic")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getPuplic() {
//		return Response.ok(crypto.getPublicAsString()).build();
//	}
//	
//	@GET
//	@Path("getiv")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response getIv() {
//		return Response.ok(crypto.getIvAsString()).build();
//	}
}
