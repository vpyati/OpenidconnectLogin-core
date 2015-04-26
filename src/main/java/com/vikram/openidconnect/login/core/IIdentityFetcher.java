package com.vikram.openidconnect.login.core;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public interface IIdentityFetcher {
	
	TokenResponse getTokenResponse(String authCode,OAuthProvider provider);
	
	JSONObject getPropertiesByAccessToken(String accessToken, OAuthProvider provider) throws HttpException;
}
