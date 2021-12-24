package com.tvm.vip.subscribe;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.amazonaws.services.iot.client.sample.pubSub.TestTopicListener;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil.KeyStorePasswordPair;
import com.tvm.vip.service.TVMServerService;
import com.tvm.vip.utils.AWSUtils;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	private TVMServerService tvmServerService;

	private static final String TestTopic = "salesandstatus";
	private static final AWSIotQos TestTopicQos = AWSIotQos.QOS0;

	private static AWSIotMqttClient awsIotClient = null;

	public static void setClient(AWSIotMqttClient client) {
		awsIotClient = client;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=====>>>> START  ");
		if (awsIotClient == null) {
			initClient();
			awsIotClient.connect();
		}

		AWSIotTopic topic = new TestTopicListener(TestTopic, TestTopicQos, tvmServerService);
		awsIotClient.subscribe(topic, true);
	}

	private static void initClient() throws IOException {

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