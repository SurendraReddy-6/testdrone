package com.spxts.tdms.model;

import java.util.List;


public class TransformerTestModel {
	private List<ExcitingTestResults> excitingTestResults;
	private List<OverallTestResults> overallTest;
	private SessionTestConditions sessionTestConditions;
	private String transformerType = TransformerConstants.AUTO_TRANSFORMER_WITH_TERTIARY;
	private SessionAdminDataDetails sessionAdminDataDetails;
	private TransformerDtasessionBasicDetails transformerDtasessionBasicDetails ;
	private SessionM7BushingDetails M7BushingDetails;
	private BushingDesignationDetails bushingDesignationDetails;
	private SessionLvttRatioTestResults sessionLvttRatioTestResults;
	
	private TransformerTestAdminDetails transformerTestAdminDetails;
	
	public TransformerTestAdminDetails getTransformerTestAdminDetails() {
		return transformerTestAdminDetails;
	}

	public void setTransformerTestAdminDetails(TransformerTestAdminDetails transformerTestAdminDetails) {
		this.transformerTestAdminDetails = transformerTestAdminDetails;
	}

	private TrasformerArresters trasformerArresters;
	private TrasformerSurgeArresterTestSet trasformerSurgeArresterTestSet;
	
	
	public TrasformerSurgeArresterTestSet getTrasformerSurgeArresterTestSet() {
		return trasformerSurgeArresterTestSet;
	}

	public void setTrasformerSurgeArresterTestSet(TrasformerSurgeArresterTestSet trasformerSurgeArresterTestSet) {
		this.trasformerSurgeArresterTestSet = trasformerSurgeArresterTestSet;
	}

	public TrasformerArresters getTrasformerArresters() {
		return trasformerArresters;
	}

	public void setTrasformerArresters(TrasformerArresters trasformerArresters) {
		this.trasformerArresters = trasformerArresters;
	}

	public SessionLvttRatioTestResults getSessionLvttRatioTestResults() {
		return sessionLvttRatioTestResults;
	}

	public SessionTestConditions getSessionTestConditions() {
		return sessionTestConditions;
	}

	public void setSessionTestConditions(SessionTestConditions sessionTestConditions) {
		this.sessionTestConditions = sessionTestConditions;
	}

	public void setSessionLvttRatioTestResults(SessionLvttRatioTestResults sessionLvttRatioTestResults) {
		this.sessionLvttRatioTestResults = sessionLvttRatioTestResults;
	}

	public BushingDesignationDetails getBushingDesignationDetails() {
		return bushingDesignationDetails;
	}

	public SessionM7BushingDetails getM7BushingDetails() {
		return M7BushingDetails;
	}

	public void setM7BushingDetails(SessionM7BushingDetails m7BushingDetails) {
		M7BushingDetails = m7BushingDetails;
	}

	public void setBushingDesignationDetails(BushingDesignationDetails bushingDesignationDetails) {
		this.bushingDesignationDetails = bushingDesignationDetails;
	}

	public SessionAdminDataDetails getSessionAdminDataDetails() {
		return sessionAdminDataDetails;
	}

	public void setSessionAdminDataDetails(SessionAdminDataDetails sessionAdminDataDetails) {
		this.sessionAdminDataDetails = sessionAdminDataDetails;
	}

	public String getTransformerType() {
		return transformerType;
	}

	public TransformerDtasessionBasicDetails getTransformerDtasessionBasicDetails() {
		return transformerDtasessionBasicDetails;
	}

	public void setTransformerDtasessionBasicDetails(TransformerDtasessionBasicDetails transformerDtasessionBasicDetails) {
		this.transformerDtasessionBasicDetails = transformerDtasessionBasicDetails;
	}

	public void setTransformerType(String transformerType) {
		this.transformerType = transformerType;
	}

	public List<OverallTestResults> getOverallTest() {
		return overallTest;
	}

	public void setOverallTest(List<OverallTestResults> overallTest) {
		this.overallTest = overallTest;
	}

	public List<ExcitingTestResults> getExcitingTestResults() {
		return excitingTestResults;
	}

	public void setExcitingTestResults(List<ExcitingTestResults> excitingTestResults) {
		this.excitingTestResults = excitingTestResults;
	}
}
