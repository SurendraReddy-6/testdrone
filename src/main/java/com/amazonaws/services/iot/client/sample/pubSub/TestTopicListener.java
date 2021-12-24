/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.services.iot.client.sample.pubSub;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.google.gson.Gson;
import com.tvm.vip.dto.TVMModulesDTO;
import com.tvm.vip.dto.TVMSalesDTO;
import com.tvm.vip.dto.TVMStatusDTO;
import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMSales;
import com.tvm.vip.entity.TVMStatus;
import com.tvm.vip.service.TVMServerService;
import com.tvm.vip.ts.SalesTimestream;
import com.tvm.vip.ts.StatusTimestream;

/**
 * This class extends {@link AWSIotTopic} to receive messages from a subscribed
 * topic.
 */

public class TestTopicListener extends AWSIotTopic {

	private TVMServerService tvmServerService;

//	public static List<TVMStatusDTO> statusListDTO = new ArrayList<TVMStatusDTO>();
//	public static List<TVMSalesDTO> salesListDTO = new ArrayList<TVMSalesDTO>();

	/*
	 * public TestTopicListener(String topic, AWSIotQos qos) throws
	 * FileNotFoundException, AWSIotException { super(topic, qos); }
	 */

	public TestTopicListener(String topic, AWSIotQos qos) throws FileNotFoundException, AWSIotException {
		super(topic, qos);
	}

	public TestTopicListener(String topic, AWSIotQos qos, TVMServerService tvmServerService)
			throws FileNotFoundException, AWSIotException {
		super(topic, qos);
		this.tvmServerService = tvmServerService;
	}

	@Override
	public void onMessage(AWSIotMessage message) {
		try {
//			TimeUnit.SECONDS.sleep(3);
			Gson gsonObj = new Gson();
			String jsonString = message.getStringPayload();
			if (jsonString.contains("transaction_id")) {
				TVMSalesDTO salesDTO = gsonObj.fromJson(jsonString, TVMSalesDTO.class);

				TVMSales sales = convertSalesDTOToSalesEntity(salesDTO);

				tvmServerService.addTVMSales(sales);
				SalesTimestream.connectTS(sales);
			} else {
				TVMStatusDTO statusDTO = gsonObj.fromJson(jsonString, TVMStatusDTO.class);
				List<TVMModules> moduleList = new ArrayList<TVMModules>();
				for (int i = 0; i < statusDTO.getModulesListDTO().size(); i++) {
					TVMModules module = moduleDTOToModuleEntity(statusDTO.getModulesListDTO().get(i));
					tvmServerService.addTVMModules(module);
					moduleList.add(module);
				}

				TVMStatus status = statusDTOToStatusEntity(statusDTO);
				tvmServerService.addTVMStatus(status);
				StatusTimestream.connectTS(status,moduleList);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(System.currentTimeMillis() + ": <<< " + message.getStringPayload());
	}

	private TVMSales convertSalesDTOToSalesEntity(TVMSalesDTO salesDTO) {
		ModelMapper modelMapper = new ModelMapper();
		TVMSales sales = modelMapper.map(salesDTO, TVMSales.class);
		return sales;
	}

	private TVMModules moduleDTOToModuleEntity(TVMModulesDTO tvmModulesDTO) {
		ModelMapper modelMapper = new ModelMapper();
		TVMModules module = modelMapper.map(tvmModulesDTO, TVMModules.class);
		return module;
	}

	private TVMStatus statusDTOToStatusEntity(TVMStatusDTO statusDTO) {
		ModelMapper modelMapper = new ModelMapper();
		TVMStatus status = modelMapper.map(statusDTO, TVMStatus.class);
		return status;
	}
}
