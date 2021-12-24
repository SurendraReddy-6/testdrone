package com.tvm.vip.service;

import java.util.Map;

import com.tvm.vip.dto.CompareTVMDTO;
import com.tvm.vip.entity.TVMConfig;
import com.tvm.vip.entity.TVMInfo;
import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMSales;
import com.tvm.vip.entity.TVMStatus;

public interface TVMServerService {

	public Map<Object, Object> addTVMInfo(TVMInfo tvmInfo);

	public Map<Object, Object> addTVMModules(TVMModules tvmModules);

	public Map<Object, Object> addTVMStatus(TVMStatus tvmtStatus);

	public Map<Object, Object> addTVMSales(TVMSales tvmSales);

	public Map<Object, Object> addTVMConfig(TVMConfig tvmConfig);

	public Map<Object, Object> getTVMConfig();

	public Map<Object, Object> getTVMInfo();

	public Map<Object, Object> getTVMStatus();

	public Map<Object, Object> getTVMModules();

	public Map<Object, Object> getTVMSales();

	public Map<Object, Object> updateTVMInfo(TVMInfo tvmInfo);

	public Map<Object, Object> getTVMSalesById(Long tvmid);

	public Map<Object, Object> getTVMInfoByid(Long tvmId);

	public Map<Object, Object> getModulesByTVMId(Long tvmId);

	public Map<Object, Object> getStatusByTVMId(Long tvmId);

	public Map<Object, Object> compareTVM(CompareTVMDTO compareTVM);
}
