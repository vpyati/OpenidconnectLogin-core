package com.vikram.openconnect.login.core;

import com.vikram.openconnect.login.core.providers.OAuthProvider;

public interface IOpenconnectDiscoveryFactory {
	
	IOpenconnectDiscovery get(OAuthProvider oauthProvider);
}
