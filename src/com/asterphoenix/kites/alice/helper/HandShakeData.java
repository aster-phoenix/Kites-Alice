package com.asterphoenix.kites.alice.helper;

public class HandShakeData {
	
	private String clientPublicKey;
	private String remotePublicKey;
	private String secretKey;
	private String iv;
	
	
	public String getClientPublicKey() {
		return clientPublicKey;
	}
	public void setClientPublicKey(String clientPublicKey) {
		this.clientPublicKey = clientPublicKey;
	}
	public String getRemotePublicKey() {
		return remotePublicKey;
	}
	public void setRemotePublicKey(String remotePublicKey) {
		this.remotePublicKey = remotePublicKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}

}
