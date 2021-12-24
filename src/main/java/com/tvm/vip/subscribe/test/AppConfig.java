package com.tvm.vip.subscribe.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "aws")
@Configuration
public class AppConfig {

	private String accessKeyId;
	private String secretKeyId;
	private String clientEndpoint;
	
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getSecretKeyId() {
		return secretKeyId;
	}
	public void setSecretKeyId(String secretKeyId) {
		this.secretKeyId = secretKeyId;
	}
	public String getClientEndpoint() {
		return clientEndpoint;
	}
	public void setClientEndpoint(String clientEndpoint) {
		this.clientEndpoint = clientEndpoint;
	}
}
