package com.tvm.vip.ts;

import java.time.Duration;
import java.util.List;

import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMStatus;
import com.tvm.vip.utils.AWSTimestreamUtils;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.timestreamwrite.TimestreamWriteClient;

public class StatusTimestream {

	public static void connectTS(TVMStatus status, List<TVMModules> moduleList) {
		TimestreamWriteClient writeClient = buildWriteClient();

		TimestreamService crudIngest = new TimestreamService(writeClient);
		crudIngest.createDatabase(AWSTimestreamUtils.DATABASE_NAME);
		crudIngest.listDatabases();
		crudIngest.createTable(AWSTimestreamUtils.DATABASE_NAME, AWSTimestreamUtils.STATUS_TABLE);
		crudIngest.createTable(AWSTimestreamUtils.DATABASE_NAME, AWSTimestreamUtils.MODULE_TABLE);
		crudIngest.writeRecordsStatus(status, moduleList);
	}

	private static TimestreamWriteClient buildWriteClient() {
		ApacheHttpClient.Builder httpClientBuilder = ApacheHttpClient.builder();
		httpClientBuilder.maxConnections(5000);

		RetryPolicy.Builder retryPolicy = RetryPolicy.builder();
		retryPolicy.numRetries(10);

		ClientOverrideConfiguration.Builder overrideConfig = ClientOverrideConfiguration.builder();
		overrideConfig.apiCallAttemptTimeout(Duration.ofSeconds(20));
		overrideConfig.retryPolicy(retryPolicy.build());

		return TimestreamWriteClient.builder().credentialsProvider(AWSTimestreamUtils.credentialsProvider)
				.httpClientBuilder(httpClientBuilder).overrideConfiguration(overrideConfig.build())
				.region(Region.US_EAST_1).build();
	}

}
