package com.spxts.tdms.server.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.spxts.tdms.server.ImpulseModel.ImpulseSurgeLogDTO;

public interface DBTAndDXMReadService {

	public Map<Long, List<Object>> uploadDBTData(MultipartFile file);
	
public String createDBTJSON(ImpulseSurgeLogDTO impulseSurgelog);
	
	public String getDBTJSON(String transformerId) throws FileNotFoundException, IOException;

}
