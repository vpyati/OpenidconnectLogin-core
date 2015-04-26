package com.vikram.openidconnect.login.core.input;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public interface ICredentialInput {
	
	public String getClientId();
	
	public String getClientSecret();
	
	public String getRedirectUri();
	
	public OAuthProvider getProvider();
}
