package com.tvm.vip.ts;

import java.time.Duration;

import com.tvm.vip.entity.TVMSales;
import com.tvm.vip.utils.AWSTimestreamUtils;

import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.timestreamwrite.TimestreamWriteClient;

public class SalesTimestream {

	public static void connectTS(TVMSales sales) {
		TimestreamWriteClient writeClient = buildWriteClient();

		TimestreamService crudIngest = new TimestreamService(writeClient);
		crudIngest.createDatabase(AWSTimestreamUtils.DATABASE_NAME);
		crudIngest.listDatabases();
		crudIngest.createTable(AWSTimestreamUtils.DATABASE_NAME, AWSTimestreamUtils.SALES_TABLE);
		crudIngest.writeRecordsSales(sales);
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
