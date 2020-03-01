package com.opowerhomework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;


public class MarvelUrlGenerator {

	public static final String BASEURL = "gateway.marvel.com/v1/public/";
	public final String publicKey;
	public final String privateKey;
	public final Long timeStamp;

	public MarvelUrlGenerator(String publicKey, String privateKey) {
		this.privateKey = privateKey;
		this.publicKey  = publicKey;
		this.timeStamp  = System.currentTimeMillis();
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	// This method generates the end point URL for the marvel service
	public String URLGenerator(String name){

		final String url = BASEURL + "characters";
		String timestamp  = getTimeStamp().toString();
		String apikey     = getPublicKey();
		String privateKey = getPrivateKey();
		URI uri = null;


		URIBuilder builder = new URIBuilder()
				.setScheme("http")
				.setHost(url)
				.setParameter("name", name)
				.setParameter("ts", timestamp)
				.setParameter("apikey", apikey)
				.setParameter("hash", createHash(timestamp + privateKey + apikey));

		try {
			uri = builder.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return uri.toString();
	}

	// This method generates the hash value based on the public key, private key and timestamp values
	private String createHash(String digest) {
		String stringToHash = timeStamp + privateKey + publicKey;
		return DigestUtils.md5Hex(stringToHash);
	}


}
