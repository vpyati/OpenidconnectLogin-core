package com.vikram.openidconnect.login.core.identity;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vikram.openidconnect.login.core.IIdentityFetcher;
import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public class DefaultIdentityAccessor implements IdentityAccessor {

	@Autowired
	private IIdentityFetcher identityFetcher;
	
	@Override
	public Identity getIdentity(String accessToken, OAuthProvider provider) throws HttpException {
		return new AuthCodeIdentity(accessToken, identityFetcher, provider);
	}

}
