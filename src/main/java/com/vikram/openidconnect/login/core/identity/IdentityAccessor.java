package com.vikram.openidconnect.login.core.identity;

import org.apache.http.HttpException;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public interface IdentityAccessor {
	
	public Identity getIdentity(String accessToken, OAuthProvider provider) throws HttpException;

}
