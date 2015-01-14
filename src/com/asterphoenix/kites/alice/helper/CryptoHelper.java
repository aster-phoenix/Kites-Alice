package com.asterphoenix.kites.alice.helper;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.codec.binary.Base64;

import com.asterphoenix.roxy.RoxyAsymmetric;
import com.asterphoenix.roxy.RoxySymmetric;

@Singleton
@Startup
public class CryptoHelper {

	@PostConstruct
	public void cryptoInit() {
		try {
			rsa = new RoxyAsymmetric();
			keyPair = rsa.generateKeyPair();
			keyFactory = KeyFactory.getInstance("RSA");

			aes = new RoxySymmetric();
			sercetKey = aes.generateSecretKey();
			iv = aes.generateIv();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public String getPublicAsString() {
		try {
			return new String(keyPair.getPublic().getEncoded(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PublicKey getPublicKeyFromString(String keyString) {
		try {
			byte[] keyBytes = Base64.decodeBase64(keyString);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			return keyFactory.generatePublic(spec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SecretKey getSecretKey() {
		return sercetKey;
	}

	public String getSecretKeyAsString(PublicKey pk) {
//		return new String(Base64.encodeBase64(sercetKey.getEncoded()));
		try {
			byte[] wrapedKey = rsa.wrapSecretKey(sercetKey, pk);
			return new String(wrapedKey, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SecretKey getSecretKeyFromString(String keyString) {
		byte[] keyBytes = Base64.decodeBase64(keyString);
		return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
	}
	
	public String getIvAsString() {
		try {
			return new String(iv.getIV(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String encodeRSAPublic(String msg, PublicKey pk) {
		return new String(rsa.encryptWithPublic(msg.getBytes(), pk));
	}

	public String encodeRSAPrivate(String msg) {
		System.err.println(msg.getBytes().length);
		return new String(rsa.encryptWithPrivate(msg.getBytes(), keyPair.getPrivate()));
	}

	public String decodeRSAPublic(String msg, PublicKey pk) {
		return new String(rsa.decryptWithPublic(msg.getBytes(), pk));
	}

	public String decodeRSAPrivate(String msg) {
		return new String(rsa.decryptWithPrivate(msg.getBytes(), keyPair.getPrivate()));
	}

	public String encodeAES(String msg) {
		return new String(aes.encrypt(msg, sercetKey, iv));
	}

	public String decodeAES(String msg) {
		try {
			return new String(aes.decrypt(msg.getBytes("ISO-8859-1"), sercetKey, iv));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	private RoxyAsymmetric rsa;
	private KeyPair keyPair;
	private KeyFactory keyFactory;

	private RoxySymmetric aes;
	private SecretKey sercetKey;
	private IvParameterSpec iv;
}
