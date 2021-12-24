package com.tvm.vip.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

import javax.annotation.security.PermitAll;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.sample.pubSub.PublishSubscribeSample.BlockingPublisher;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil.KeyStorePasswordPair;
import com.google.gson.Gson;
import com.tvm.vip.dto.CommandPublish;
import com.tvm.vip.utils.AWSUtils;

@RestController
public class PublishController {

	private static AWSIotMqttClient awsIotClient = null;

	public static void setClient(AWSIotMqttClient client) {
		awsIotClient = client;
	}

	@PermitAll
	@PostMapping("/publish/command")
	public String publishSalesToAWS(@RequestBody CommandPublish command)
			throws InterruptedException, AWSIotException, FileNotFoundException {
		String payload = new Gson().toJson(command);
		connectToAWS(payload);
		return "success";
	}

	public void connectToAWS(String payload) throws InterruptedException, AWSIotException, FileNotFoundException {

		if (awsIotClient == null) {
			initClient();

			awsIotClient.connect();
		}
		// AWSIotTopic topic = new TestTopicListener(TestTopic, TestTopicQos);
		// awsIotClient.subscribe(topic, true);

		BlockingPublisher blockingPublishThread = new BlockingPublisher(awsIotClient);
		blockingPublishThread.execute(payload);
	}

	private static void initClient() throws FileNotFoundException {

		File cert = new File("src/main/resources/awsfiles/VipNew.cert.pem");
		File pk = new File("src/main/resources/awsfiles/VipNew.private.key");

		String certificateFile = cert.getAbsolutePath();
		String privateKeyFile = pk.getAbsolutePath();

		if (awsIotClient == null && certificateFile != null && privateKeyFile != null) {
			String algorithm = null;

			KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile, algorithm);

			awsIotClient = new AWSIotMqttClient(AWSUtils.clientEndpoint, AWSUtils.clientId, pair.keyStore,
					pair.keyPassword);
		}

		if (awsIotClient == null) {
			throw new IllegalArgumentException("Failed to construct client due to missing certificate or credentials.");
		}
	}
}
