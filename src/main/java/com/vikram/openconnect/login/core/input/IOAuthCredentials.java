package com.vikram.openconnect.login.core.input;

import com.vikram.openconnect.login.core.providers.OAuthProvider;

public interface IOAuthCredentials {
	
	public ICredentialInput getCredentialByProvider(OAuthProvider provider);

}
