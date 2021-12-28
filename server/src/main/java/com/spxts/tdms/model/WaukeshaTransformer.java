package com.spxts.tdms.model;
import java.util.List;

public class WaukeshaTransformer {
	private List<TransformerTestModel> transformerSessions;
	private String transformerType = TransformerConstants.AUTO_TRANSFORMER_WITH_TERTIARY;
	private  TransformerNamePlateType  transformerDetails;

	public List<TransformerTestModel> getTransformerSessions() {
		return transformerSessions;
	}
	public void setTransformerSessions(List<TransformerTestModel> transformerSessions) {
		this.transformerSessions = transformerSessions;
	}
	public String getTransformerType() {
		return transformerType;
	}
	public void setTransformerType(String transformerType) {
		this.transformerType = transformerType;
	}
	public TransformerNamePlateType getTransformerDetails() {
		return transformerDetails;
	}
	public void setTransformerDetails(TransformerNamePlateType transformerDetails) {
		this.transformerDetails = transformerDetails;
	}
}


