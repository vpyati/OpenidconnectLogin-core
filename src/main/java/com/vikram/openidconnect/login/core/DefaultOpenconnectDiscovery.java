package com.vikram.openidconnect.login.core;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vikram.openidconnect.login.core.util.HttpClientUtil;

public class DefaultOpenconnectDiscovery implements IOpenconnectDiscovery {

	private JSONObject jsonResponse;
	
	private String discoveryUrl;
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	

	protected DefaultOpenconnectDiscovery(String url){	
		this.discoveryUrl = url;		
	}
	
	
	private void init(String Url) throws JsonParseException, JsonMappingException, IOException, HttpException {
		String jsonString = httpClientUtil.getJSONResponse(Url);
		jsonResponse = (JSONObject) JSONValue.parse(jsonString);		
	}
 
	
	public String getIssuer() {
		return (String) jsonResponse.get("issuer");
	}


	public String getAuthorization_endpoint() {
		return (String) jsonResponse.get("authorization_endpoint");
	}


	public String getToken_endpoint() {
		return (String) jsonResponse.get("token_endpoint");
	}


	public String getUserinfo_endpoint() {
		return (String) jsonResponse.get("userinfo_endpoint");
	}


	public String getRevocation_endpoint() {
		return (String) jsonResponse.get("revocation_endpoint");
	}


	public String getJwks_uri() {
		return (String) jsonResponse.get("jwks_uri");
	}


	public String[] getResponse_types_supported() {
		return (String[]) jsonResponse.get("response_types_supported");
	}


	public String[] getSubject_types_supported() {
		return (String[]) jsonResponse.get("subject_types_supported");
	}


	public String[] getId_token_alg_values_supported() {
		return (String[]) jsonResponse.get("id_token_alg_values_supported");
	}


	@PostConstruct
	public void init() {
		try {
			init(discoveryUrl);
		} catch (IOException | HttpException e) {
			throw new RuntimeException("Unable to get GoogleDiscovery",e);
		}	
	}

}
