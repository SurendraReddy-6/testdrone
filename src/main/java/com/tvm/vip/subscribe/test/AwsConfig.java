package com.tvm.vip.subscribe.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.AWSIot;
import com.amazonaws.services.iot.AWSIotClientBuilder;

@Configuration
public class AwsConfig {

	@Bean
	public AWSIot getIotClient(AppConfig appconfig) {
		return AWSIotClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(appconfig.getAccessKeyId(), appconfig.getSecretKeyId())))
				.withRegion(Regions.US_EAST_1).build();
	}
}
