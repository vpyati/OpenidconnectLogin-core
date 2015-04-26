package com.vikram.openconnect.login.core.input;

import com.vikram.openconnect.login.core.providers.OAuthProvider;

public interface ICredentialInput {
	
	public String getClientId();
	
	public String getClientSecret();
	
	public String getRedirectUri();
	
	public OAuthProvider getProvider();
}
