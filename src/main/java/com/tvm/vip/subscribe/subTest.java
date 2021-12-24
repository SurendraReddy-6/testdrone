package com.tvm.vip.subscribe;


import java.io.FileNotFoundException;

import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.amazonaws.services.iot.client.sample.pubSub.TestTopicListener;

import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil.KeyStorePasswordPair;


@RestController
public class subTest {

	
	   private static final String TestTopic = "salesandstatus";
	    private static final AWSIotQos TestTopicQos = AWSIotQos.QOS0;
	    
	    private static AWSIotMqttClient awsIotClient;

	    public static void setClient(AWSIotMqttClient client) {
	        awsIotClient = client;
	    }
	    
	
	public static void main(String args[]) throws AWSIotException, InterruptedException, FileNotFoundException {
		
		 initClient();

	        awsIotClient.connect();


	        AWSIotTopic topic = new TestTopicListener(TestTopic, TestTopicQos);
	        awsIotClient.subscribe(topic, true);

	        Thread blockingPublishThread = new Thread(new BlockingPublisher(awsIotClient));
	        //Thread nonBlockingPublishThread = new Thread(new NonBlockingPublisher(awsIotClient));

	        blockingPublishThread.start();
	       // nonBlockingPublishThread.start();

	        blockingPublishThread.join();
	        //nonBlockingPublishThread.join();
		
		
	}
	
	private static void initClient() {
		
		String vipCert ="C:\\Users\\Pushpa Kumar\\Desktop\\TVM\\New folder\\connect_device_package_VIpNew\\VipNew.cert.pem";
		String vipPrivateKey ="C:\\Users\\Pushpa Kumar\\Desktop\\TVM\\New folder\\connect_device_package_VIpNew\\VipNew.private.key";
		String vipPublicKey ="C:\\Users\\Pushpa Kumar\\Desktop\\TVM\\New folder\\connect_device_package_VIpNew\\VipNew.public.key";
		String viproot ="C:\\Users\\Pushpa Kumar\\Desktop\\TVM\\New folder\\connect_device_package_VIpNew\\root-CA";
		
		
		//mvn exec:java -pl aws-iot-device-sdk-java-samples --% -Dexec.mainClass="com.amazonaws.services.iot.client.sample.pubSub.PublishSubscribeSample" -Dexec.args="-clientEndpoint ak037zsv75o5k-ats.iot.us-east-1.amazonaws.com -clientId sdk-java -certificateFile ..\VipNew.cert.pem -privateKeyFile ..\VipNew.private.key"

        String clientEndpoint = "ak037zsv75o5k-ats.iot.us-east-1.amazonaws.com";
        String clientId = "vip";

        String certificateFile = vipCert;//arguments.get("certificateFile", SampleUtil.getConfig("certificateFile"));
        String privateKeyFile = vipPrivateKey;// arguments.get("privateKeyFile", SampleUtil.getConfig("privateKeyFile"));
        if (awsIotClient == null && certificateFile != null && privateKeyFile != null) {
            String algorithm = null;

            KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile, algorithm);

            awsIotClient = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
        }

        if (awsIotClient == null) {
//            String awsAccessKeyId = arguments.get("awsAccessKeyId", SampleUtil.getConfig("awsAccessKeyId"));
//            String awsSecretAccessKey = arguments.get("awsSecretAccessKey", SampleUtil.getConfig("awsSecretAccessKey"));
//            String sessionToken = arguments.get("sessionToken", SampleUtil.getConfig("sessionToken"));
//
//            if (awsAccessKeyId != null && awsSecretAccessKey != null) {
//                awsIotClient = new AWSIotMqttClient(clientEndpoint, clientId, awsAccessKeyId, awsSecretAccessKey,
//                        sessionToken);
//            }
        }

        if (awsIotClient == null) {
            throw new IllegalArgumentException("Failed to construct client due to missing certificate or credentials.");
        }
    }

	public static class BlockingPublisher implements Runnable {
        private final AWSIotMqttClient awsIotClient;

        public BlockingPublisher(AWSIotMqttClient awsIotClient) {
            this.awsIotClient = awsIotClient;
        }

        @Override
        public void run() {
            long counter = 1;

            while (counter <=2) {
                counter++;
                 String payload = "{\"name\":\"VipNew\", \"VipNewID\":31, \"Message\":\"Test\"}";
                try {
                    awsIotClient.publish(TestTopic, payload);
                } catch (AWSIotException e) {
                    System.out.println(System.currentTimeMillis() + ": publish failed for " + payload);
                }
                System.out.println(System.currentTimeMillis() + ": >>> " + payload);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(System.currentTimeMillis() + ": BlockingPublisher was interrupted");
                    return;
                }
            }
        }
    }


}


