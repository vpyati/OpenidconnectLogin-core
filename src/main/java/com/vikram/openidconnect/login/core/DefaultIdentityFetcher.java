package com.vikram.openidconnect.login.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.openidconnect.login.core.input.ICredentialInput;
import com.vikram.openidconnect.login.core.input.IOAuthCredentials;
import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.openidconnect.login.core.util.HttpClientUtil;

public class DefaultIdentityFetcher implements IIdentityFetcher {

	@Autowired
	private IOpenconnectDiscoveryFactory discoveryFactory;
	
	@Autowired
	private HttpClientUtil httpUtil;
	
	@Autowired
	private IOAuthCredentials oauthCredentials;
	
	
	private static Logger logger = LoggerFactory.getLogger(DefaultIdentityFetcher.class);
	
	@Override
	public JSONObject getPropertiesByAccessToken(String accessToken, OAuthProvider provider) throws HttpException {

		IOpenconnectDiscovery discovery = discoveryFactory.get(provider);
		String userInfoEndpoint = discovery.getUserinfo_endpoint();
		
		logger.info("Invoking userinfo endpoint to get user details by providing accessToken");		
		String jsonResponse = httpUtil.getJSONResponse(userInfoEndpoint + "?access_token=" + accessToken);
		return (JSONObject) JSONValue.parse(jsonResponse);
	}


	@Override
	public TokenResponse getTokenResponse(String authCode, OAuthProvider provider) {
		ICredentialInput credentials = oauthCredentials.getCredentialByProvider(provider);
		IOpenconnectDiscovery discovery = discoveryFactory.get(provider);
		return getTokenResponseInternal(authCode, credentials, discovery);
	}
	
	private TokenResponse getTokenResponseInternal(String authCode, ICredentialInput credentials, IOpenconnectDiscovery discovery) {
		
		logger.info("Invoking tokenresponse endpoint to get the accesstoken");
		String tokenEndpoint = discovery.getToken_endpoint();
		
		Map<String, String> postParams = new HashMap<String, String>();
		postParams.put("code", authCode);
		postParams.put("client_id", credentials.getClientId());
		postParams.put("client_secret",credentials.getClientSecret());
		postParams.put("redirect_uri", credentials.getRedirectUri());
		postParams.put("grant_type", "authorization_code");
		postParams.put("access_type", "offline");
		
		String response = getHttpResponse(tokenEndpoint, postParams);
		if(response == null){
			return null;
		}
		
		return getTokenResponseInternal(response);
	}

	private String getHttpResponse(String tokenEndpoint,Map<String, String> postParams) {
		try {
			return httpUtil.getJSONResponse(tokenEndpoint, postParams);
		} catch (HttpException e) {
			logger.error("Error while getting token response", e);
			return null;
		}
	}


	private TokenResponse getTokenResponseInternal(String response)  {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(response,TokenResponse.class);
		} catch (IOException e) {
			logger.error("Error while parsing token response", e);
			return null;
		}
	}

	
}
