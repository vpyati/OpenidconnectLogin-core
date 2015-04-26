package com.vikram.openconnect.login.core;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vikram.openconnect.login.core.google.GoogleOpenconnectDiscovery;
import com.vikram.openconnect.login.core.providers.OAuthProvider;

public class OpenconnectDiscoveryFactory implements IOpenconnectDiscoveryFactory {
	
	@Autowired
	private GoogleOpenconnectDiscovery googleDiscovery;
	
	private Map<OAuthProvider, IOpenconnectDiscovery> instanceMap = new HashMap<OAuthProvider, IOpenconnectDiscovery>();
	
	@PostConstruct
	public void init(){
		instanceMap.put(OAuthProvider.GOOGLE, googleDiscovery);
	}
		
	public IOpenconnectDiscovery get(OAuthProvider provider){
		return instanceMap.get(provider);		
	}
}
