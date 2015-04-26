package com.vikram.openidconnect.login.core;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public interface IOpenconnectDiscoveryFactory {
	
	IOpenconnectDiscovery get(OAuthProvider oauthProvider);
}
