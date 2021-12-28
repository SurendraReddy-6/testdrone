package com.spxts.tdms.server.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.spxts.tdms.model.WaukeshaTransformer;
import com.spxts.tdms.server.enitity.TransformerDTO;

public interface TransformerService {

	public TransformerDTO getTransformerDetails(TransformerDTO transformerDTO);

	public HashMap<String,WaukeshaTransformer>  getDataModel(TransformerDTO transformerDTO) throws JAXBException, FileNotFoundException;

}
