package com.tvm.vip.controller;

import javax.annotation.security.PermitAll;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.AWSIot;
import com.amazonaws.services.iot.AWSIotClientBuilder;
import com.amazonaws.services.iot.model.CreatePolicyRequest;
import com.amazonaws.services.iot.model.CreateThingResult;
import com.amazonaws.services.iot.model.DescribeThingRequest;
import com.amazonaws.services.iot.model.DescribeThingResult;
import com.amazonaws.services.iot.model.RegisterCertificateResult;
import com.amazonaws.services.iotdata.model.ResourceNotFoundException;
import com.tvm.vip.subscribe.test.AppConfig;
import com.tvm.vip.subscribe.test.AwsConfig;
import com.amazonaws.services.iot.model.AttachPolicyRequest;
import com.amazonaws.services.iot.model.AttachThingPrincipalRequest;
import com.amazonaws.services.iot.model.CreateThingRequest;
import com.amazonaws.services.iot.model.RegisterCertificateRequest;

@RestController
public class TVMCreateController {

	@Autowired
	private AwsConfig iotClient;
	
	@Autowired
	private AppConfig appconfig;
	
	private static final String CA_CERTIFICATE = "-----BEGIN CERTIFICATE-----\r\n" + 
			"MIIDazCCAlOgAwIBAgIUfKWJkIetQOADZTN4ovv63N576DEwDQYJKoZIhvcNAQEL\r\n" + 
			"BQAwRTELMAkGA1UEBhMCQVUxEzARBgNVBAgMClNvbWUtU3RhdGUxITAfBgNVBAoM\r\n" + 
			"GEludGVybmV0IFdpZGdpdHMgUHR5IEx0ZDAeFw0yMTEyMjAxNDI4MDZaFw0yMjEy\r\n" + 
			"MjAxNDI4MDZaMEUxCzAJBgNVBAYTAkFVMRMwEQYDVQQIDApTb21lLVN0YXRlMSEw\r\n" + 
			"HwYDVQQKDBhJbnRlcm5ldCBXaWRnaXRzIFB0eSBMdGQwggEiMA0GCSqGSIb3DQEB\r\n" + 
			"AQUAA4IBDwAwggEKAoIBAQCuVIfTpqfNsFx9fWHgzav0ignX8swCfC5g9DcrRQp/\r\n" + 
			"XbDD+USGfMzCiQtRszfsGssXeAGJJpmbDODm/i2n6wBC8+iPetQSweWojdmSVDt3\r\n" + 
			"UkGXjUNQcVrZanmbXtvEulreO2NvydsjT1VLNKioRP+7t3N17Sk6QzPkZz/30ror\r\n" + 
			"iC6CzqM1gk9XUgp/wd/Y5NqgYOR5hzOgVHiV5MnzQIAZ899Dcwp3b3ydtH5Me1My\r\n" + 
			"MWJe2w9YZ1dT3PIi2CLc0fvOcjLfNhHSe31GmW1YMNwTUnafKp5BY6WE/y1KbqAf\r\n" + 
			"wm2EkdHckvnvNT4FrD8WXYaf7O4CrOQhi/u79A1mnz9BAgMBAAGjUzBRMB0GA1Ud\r\n" + 
			"DgQWBBSJR9eDapY6gqrCgAf+xsaWlXa4SzAfBgNVHSMEGDAWgBSJR9eDapY6gqrC\r\n" + 
			"gAf+xsaWlXa4SzAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4IBAQAq\r\n" + 
			"ihLblnM6XcYf/2AYVnc/ksZkLyCOOosLl79pGQwE0O0mYdiWmtI5whGPoApgm6xd\r\n" + 
			"LOkBJId8wYOzEXDv1t8rTWvD8T1aNhXNiRq7CugFUrTZaKJg7Xu8jZFisFmjQzTU\r\n" + 
			"CTVOxXpvkKcZCVhIns311qGOr19GbjgV7ZFLWZi8+0DW8W+dUNCpkp4IytjI6mvq\r\n" + 
			"vpk6Yie+jrtLKECrTM1bS8HAXaIGReYSf5/5RxKSHJnVUZKKdb3EQQ7hw7T0PV8G\r\n" + 
			"D1kUa4puPJ74K/gW8n3W6sIvv7bY0Eqq2giGi0eHKBd1E9WpV0nPgwfg9o9ermOr\r\n" + 
			"3sVDJ/C7gfoxkqaU2F7a\r\n" + 
			"-----END CERTIFICATE-----\r\n" + 
			"";

	private static final String DEVICE_CERTIFICATE = "-----BEGIN CERTIFICATE-----\r\n"
			+ "MIIDIzCCAgsCFFI+Jixt5w2zMydXSTq0wGaaPsysMA0GCSqGSIb3DQEBCwUAMEUx\r\n"
			+ "CzAJBgNVBAYTAkFVMRMwEQYDVQQIDApTb21lLVN0YXRlMSEwHwYDVQQKDBhJbnRl\r\n"
			+ "cm5ldCBXaWRnaXRzIFB0eSBMdGQwHhcNMjExMjI0MDcyNDIxWhcNMjIxMjI0MDcy\r\n"
			+ "NDIxWjBXMQswCQYDVQQGEwJBVTETMBEGA1UECAwKU29tZS1TdGF0ZTEhMB8GA1UE\r\n"
			+ "CgwYSW50ZXJuZXQgV2lkZ2l0cyBQdHkgTHRkMRAwDgYDVQQDDAdEZXZpY2UxMIIB\r\n"
			+ "IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAumlc1zncoKugD74k9AowebWO\r\n"
			+ "jjtkr5qMibUAgyJdMLlt8IZ6310uFWAkqWunm+tSdMzH9x5DbwH+htJMmIhqnggV\r\n"
			+ "Vcw607k4rQVuxHyJv+m5ZePwPRCj+M1gYF/fTOTiF1Cs5v/eu/Q5s9rjRwnTj5p/\r\n"
			+ "tMQo6CGWCjbcfEUhm1fZuo7MTOi23B+/BUucQQAML/RORqfd9kfcvGVhNAsGu01u\r\n"
			+ "ZDjFn7cQ3evdysRHWSfbiwldWZ3YOMzvBgYUk8wixIWEuZRqDefHiFGOrdTMEErY\r\n"
			+ "q4ShSPlhiaytQI9I82ZtSaG2MPWOJeUJoqGrPZ/ZqsMGAp9+0RLLuoLTDR5dywID\r\n"
			+ "AQABMA0GCSqGSIb3DQEBCwUAA4IBAQAI/hx0m1THqOfwtpBjh7WEz8PdMvrgFnG/\r\n"
			+ "l6EqwMygZgcA3resFnkJYiUU4VYZ/1hzREFGCMHlpyQUA2TJZaCnHcnzNunSA5QP\r\n"
			+ "4Ncxrk/bXi8Kcsw7BxaX3mjhpcWY+TREYreSRKXys1xSGZAgHOv5T5hlOsbwJzh+\r\n"
			+ "BxXYOKYirbiPWwGVfepx1Q1EWkcvdRDaIkiaOhq28OA5+94U4QZUtqSxDlZboGjq\r\n"
			+ "d0/2gCP71epFBmy2MWtDBUYw5JeC3aNjvufVqbzZJlmsCYi8NFLV7tKhjITUQjTU\r\n"
			+ "hI+DRhziTmqRv01DSJrxzTdhaQ2NDEzSyP9aJQ4u4m5KOR1QjkSE\r\n"
			+ "-----END CERTIFICATE-----\r\n"
			+ "\r\n"
			+ "";

	String policy_documetn ="{\r\n" + 
			"    \"Version\": \"2012-10-17\",\r\n" + 
			"    \"Statement\": [\r\n" + 
			"        {\r\n" + 
			"            \"Effect\": \"Allow\",\r\n" + 
			"            \"Action\": [\r\n" + 
			"                \"iot:Publish\",\r\n" + 
			"                \"iot:Receive\"\r\n" + 
			"            ],\r\n" + 
			"            \"Resource\": [\r\n" + 
			"                \"arn:aws:iot:us-east-1:633025986259:topic/topic_1\",\r\n" + 
			"                \"arn:aws:iot:us-east-1:633025986259:topic/topic_2\"\r\n" + 
			"            ]\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"Effect\": \"Allow\",\r\n" + 
			"            \"Action\": [\r\n" + 
			"                \"iot:Subscribe\"\r\n" + 
			"            ],\r\n" + 
			"            \"Resource\": [\r\n" + 
			"                \"arn:aws:iot:us-east-1:633025986259:topicfilter/topic_1\",\r\n" + 
			"                \"arn:aws:iot:us-east-1:633025986259:topicfilter/topic_2\"\r\n" + 
			"            ]\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"Effect\": \"Allow\",\r\n" + 
			"            \"Action\": [\r\n" + 
			"                \"iot:Connect\"\r\n" + 
			"            ],\r\n" + 
			"            \"Resource\": [\r\n" + 
			"            \"arn:aws:iot:us-east-1:633025986259:client/basicPubSub\"\r\n" + 
			"            ]\r\n" + 
			"        }\r\n" + 
			"    ]\r\n" + 
			"}";
	
	@PermitAll
	@GetMapping("/tvm-create")
	public String createTVM( @PathParam("thingName") String thingName) {
		
		AWSIot aWSIotClient = AWSIotClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider
						(new BasicAWSCredentials(appconfig.getAccessKeyId(), appconfig.getSecretKeyId()))).withRegion(Regions.US_EAST_1).build();
		
		//check If thing already exists
		if(!describThing(thingName)) {
			// create thing
			CreateThingResult response = iotClient.getIotClient(appconfig).createThing(new CreateThingRequest().withThingName(thingName));

			RegisterCertificateRequest registerCertificateRequest = new RegisterCertificateRequest();
			registerCertificateRequest.setCaCertificatePem(CA_CERTIFICATE);
			registerCertificateRequest.setCertificatePem(DEVICE_CERTIFICATE);
			registerCertificateRequest.setSetAsActive(true);
			final RegisterCertificateResult registerCertificateResponse = aWSIotClient.registerCertificate(registerCertificateRequest);
			
			System.out.println("CertificateArn:"+registerCertificateResponse.getCertificateArn());
			
			//creating policy
			aWSIotClient.createPolicy(new CreatePolicyRequest().withPolicyDocument(policy_documetn).withPolicyName(thingName+"Policy"));
			aWSIotClient.attachPolicy(new AttachPolicyRequest().withPolicyName(thingName+"Policy").withTarget(registerCertificateResponse.getCertificateArn()));
			System.out.println("Policy is created....");
			
			//attach things
			AttachThingPrincipalRequest attachThingPrincipalRequest = new  AttachThingPrincipalRequest();
			attachThingPrincipalRequest.setThingName(thingName);
			attachThingPrincipalRequest.setPrincipal(registerCertificateResponse.getCertificateArn());
			aWSIotClient.attachThingPrincipal(attachThingPrincipalRequest);
			System.out.println("Attached thing and policy.");

			System.out.println("Thing Created succsfully...");
			return "Thing Created succsfully..."+response.getThingName();
		}
		// Thing exits
		return "Thing already exits on IOT console";
	}
	
private boolean describThing(String thingName) {
		
		if(thingName == null) {
			return false;
		}
		try {
			//DescribeThingResponse(thingName);
			return false;
		} catch (ResourceNotFoundException exception) {
			return false;
		}
	}
	private DescribeThingResult DescribeThingResponse(String thingName)  {
		DescribeThingRequest describeThingRequest = new DescribeThingRequest();
		describeThingRequest.setThingName(thingName);
		return iotClient.getIotClient(appconfig).describeThing(describeThingRequest);
		
	}
}
