package com.tvm.vip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvm.vip.dto.CompareTVMDTO;
import com.tvm.vip.entity.TVMConfig;
import com.tvm.vip.entity.TVMInfo;
import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMSales;
import com.tvm.vip.entity.TVMStatus;
import com.tvm.vip.service.TVMServerService;
import com.tvm.vip.utils.JSONResultEntity;

@SuppressWarnings("rawtypes")
@RestController
public class TVMServerController {

	@Autowired
	private TVMServerService tvmServerService;

	@PermitAll
	@PostMapping("/api/tvm/info")
	public ResponseEntity<?> addTVMInfo(@RequestBody TVMInfo tvmInfo) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.addTVMInfo(tvmInfo);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/info")
	public ResponseEntity<?> getTVMInfo() {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMInfo();

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/{tvmId}/info")
	public ResponseEntity<?> getTVMInfoByid(@PathVariable Long tvmId) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMInfoByid(tvmId);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@PostMapping("/api/tvm-info/update")
	public ResponseEntity<?> updateTVMInfo(@RequestBody TVMInfo tvmInfo) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.updateTVMInfo(tvmInfo);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@PostMapping("/api/tvm/modules")
	public ResponseEntity<?> addTVMModules(@RequestBody TVMModules tvmModules) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.addTVMModules(tvmModules);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/modules")
	public ResponseEntity<?> getTVMModules() {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMModules();

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/{tvmId}/modules")
	public ResponseEntity<?> getModulesByTVMId(@PathVariable Long tvmId) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getModulesByTVMId(tvmId);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@PostMapping("/api/tvm/status")
	public ResponseEntity<?> addTVMStatus(@RequestBody TVMStatus tvmtStatus) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.addTVMStatus(tvmtStatus);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/status")
	public ResponseEntity<?> getTVMStatus() {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMStatus();

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/{tvmId}/status")
	public ResponseEntity<?> getStatusByTVMId(@PathVariable Long tvmId) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getStatusByTVMId(tvmId);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@PostMapping("/api/tvm/sales")
	public ResponseEntity<?> addTVMSales(@RequestBody TVMSales tvmSales) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.addTVMSales(tvmSales);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/sales")
	public ResponseEntity<?> getTVMSales() {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMSales();

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PermitAll
	@GetMapping("/api/tvm/{tvmid}/sales")
	public ResponseEntity<?> getTVMSalesById(@PathVariable Long tvmid) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMSalesById(tvmid);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PermitAll
	@PostMapping("/api/tvm/config")
	public ResponseEntity<?> addTVMConfig(@RequestBody TVMConfig tvmConfig) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.addTVMConfig(tvmConfig);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PermitAll
	@GetMapping("/api/tvm/config")
	public ResponseEntity<?> getTVMConfig() {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.getTVMConfig();

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PermitAll
	@PostMapping("/api/sales/compare")
	public ResponseEntity<?> compareTVM(@RequestBody CompareTVMDTO compareTVM) {
		Map<Object, Object> responseData = new HashMap<>();

		responseData = tvmServerService.compareTVM(compareTVM);

		JSONResultEntity<HashMap> response = null;
		List<Object> keys = new ArrayList<>(responseData.keySet());
		if (keys.get(0).toString().toLowerCase().equals("result")) {
			response = new JSONResultEntity<HashMap>(true, "Success", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
		} else {
			response = new JSONResultEntity<HashMap>(false, "Failed", null, responseData);
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
