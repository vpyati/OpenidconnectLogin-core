package com.vikram.openidconnect.login.core.input;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public interface IOAuthCredentials {
	
	public ICredentialInput getCredentialByProvider(OAuthProvider provider);

}
