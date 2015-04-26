package com.vikram.openidconnect.login.core.google;

import com.vikram.openidconnect.login.core.DefaultOpenconnectDiscovery;

public class GoogleOpenconnectDiscovery extends DefaultOpenconnectDiscovery {

	private static String URL = "https://accounts.google.com/.well-known/openid-configuration";

	public GoogleOpenconnectDiscovery() {
		super(URL);
	}	
}