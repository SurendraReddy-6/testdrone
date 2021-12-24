package com.tvm.vip.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvm.vip.dto.CompareTVMDTO;
import com.tvm.vip.entity.TVMConfig;
import com.tvm.vip.entity.TVMInfo;
import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMSales;
import com.tvm.vip.entity.TVMStatus;
import com.tvm.vip.repository.TVMConfigRepository;
import com.tvm.vip.repository.TVMInfoRepository;
import com.tvm.vip.repository.TVMModulesRepository;
import com.tvm.vip.repository.TVMSalesRepository;
import com.tvm.vip.repository.TVMStatusRepository;
import com.tvm.vip.service.TVMServerService;

@Service
public class TVMServerServiceImpl implements TVMServerService {

	@Autowired
	private TVMConfigRepository tvmConfigRepository;

	@Autowired
	private TVMInfoRepository tvmInfoRepository;

	@Autowired
	private TVMModulesRepository tvmModulesRepository;

	@Autowired
	private TVMSalesRepository tvmSalesRepository;

	@Autowired
	private TVMStatusRepository tvmStatusRepository;

	@Override
	public Map<Object, Object> addTVMInfo(TVMInfo tvmInfo) {
		Map<Object, Object> response = new HashMap<>();
		try {
			Optional<TVMInfo> uniqueVerify = tvmInfoRepository.findByname(tvmInfo.getName());
			if (!uniqueVerify.isPresent()) {
				tvmInfo.setCreatedon(new DateTime().getMillis());
				TVMInfo result = new TVMInfo();
				result = tvmInfoRepository.save(tvmInfo);
				response.put("result", result);
			} else {
				response.put("error", "duplicate entry");
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMInfo() {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMInfo> result = tvmInfoRepository.findAll();
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMInfoByid(Long id) {
		Map<Object, Object> response = new HashMap<>();
		try {
			Optional<TVMInfo> result = tvmInfoRepository.findById(id);
			if(result.isPresent()) {
				response.put("result", result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}
	@Override
	public Map<Object, Object> updateTVMInfo(TVMInfo tvmInfo) {
		Map<Object, Object> response = new HashMap<>();
		try {
			Optional<TVMInfo> uniqueVerify = tvmInfoRepository.findById(tvmInfo.getId());
			if (uniqueVerify.isPresent()) {
				tvmInfo.setCreatedon(new DateTime().getMillis());

				TVMInfo result = new TVMInfo();
				result = uniqueVerify.get();
				result.setActive(tvmInfo.getActive());

				tvmInfoRepository.save(result);
				response.put("result", result);
			} else {
				response.put("error", "record not available for id : " + tvmInfo.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> addTVMModules(TVMModules tvmModules) {
		Map<Object, Object> response = new HashMap<>();
		try {
			TVMModules result = new TVMModules();
			Optional<TVMModules> uniqueVerify = tvmModulesRepository.findByname(tvmModules.getName());
			if (!uniqueVerify.isPresent()) {
				result = tvmModulesRepository.save(tvmModules);
				response.put("result", result);
			} else {
				tvmModules.setId(uniqueVerify.get().getId());
				result = tvmModulesRepository.save(tvmModules);
				response.put("result", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMModules() {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMModules> result = tvmModulesRepository.findAll();
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;

	}

	@Override
	public Map<Object, Object> getModulesByTVMId(Long tvmId) {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMModules> result = tvmModulesRepository.findByTVMId(tvmId);
				response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;

	}
	
	@Override
	public Map<Object, Object> addTVMStatus(TVMStatus tvmtStatus) {
		Map<Object, Object> response = new HashMap<>();
		try {
			Optional<TVMStatus> uniqueVerify = tvmStatusRepository.findByname(tvmtStatus.getName());
			TVMStatus result = new TVMStatus();
			if (!uniqueVerify.isPresent()) {
				result = tvmStatusRepository.save(tvmtStatus);
				response.put("result", result);
			} else {
				tvmtStatus.setId(uniqueVerify.get().getId());
				result = tvmStatusRepository.save(tvmtStatus);
				response.put("result", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMStatus() {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMStatus> result = tvmStatusRepository.findAll();
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}
	
	@Override
	public Map<Object, Object> getStatusByTVMId(Long tvmId) {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMStatus> result = tvmStatusRepository.findByTVMId(tvmId);
				response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> addTVMSales(TVMSales TVMSales) {
		Map<Object, Object> response = new HashMap<>();
		try {
			TVMSales result = new TVMSales();
			TVMSales.setCreatedon(new DateTime().getMillis());
			result = tvmSalesRepository.save(TVMSales);
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMSales() {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMSales> result = tvmSalesRepository.getResultByDesc();
//			List<TVMSales> result = tvmSalesRepository.findAll();
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> addTVMConfig(TVMConfig tvmConfig) {
		Map<Object, Object> response = new HashMap<>();
		try {
			TVMConfig result = new TVMConfig();
			result = tvmConfigRepository.save(tvmConfig);
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMConfig() {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMConfig> result = tvmConfigRepository.findAll();
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> getTVMSalesById(Long tvmId) {
		Map<Object, Object> response = new HashMap<>();
		try {
			List<TVMSales> result = tvmSalesRepository.findBytvmid(tvmId);
			response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}

	@Override
	public Map<Object, Object> compareTVM(CompareTVMDTO compareTVM) {
		Map<Object, Object> response = new HashMap<>();
		try {
			CompareTVMDTO result = new CompareTVMDTO();
			long totalReocrd = 0;
			List<String> paymentMethod = new ArrayList<String>();
			for(int i=0;i<compareTVM.getTvmIds().size();i++) {
				List<TVMSales> tvmSales = tvmSalesRepository.findBytvmid(compareTVM.getTvmIds().get(i));
				totalReocrd = totalReocrd+tvmSales.size();
				for(int j=0;j<tvmSales.size();j++) {
					paymentMethod.add(tvmSales.get(j).getPayment_method());
					
					result.setTotalAmount((result.getTotalAmount()+tvmSales.get(j).getAmount()));
					result.setTotalCashAmount((result.getTotalCashAmount()+tvmSales.get(j).getCashAmount()));
					result.setTotalChangeAmount((result.getTotalChangeAmount()+tvmSales.get(j).getChangeAmount()));
					result.setTotalTicketAmount((result.getTotalTicketAmount()+tvmSales.get(j).getTicketAmount()));
				}
			}
			result.setPaymentMethod(paymentMethod.stream().distinct().collect(Collectors.toList()));
			result.setAvgAmount((result.getTotalAmount()/totalReocrd));
			result.setAvgCashAmount((result.getTotalCashAmount()/totalReocrd));
			result.setAvgChangeAmount((result.getTotalChangeAmount()/totalReocrd));
			result.setAvgTicketAmount((result.getTotalTicketAmount()/totalReocrd));
		response.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			response.put("error", errors);
		}
		return response;
	}
}
