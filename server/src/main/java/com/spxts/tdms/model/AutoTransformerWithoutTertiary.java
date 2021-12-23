package com.spxts.tdms.model;

import java.util.List;


public class AutoTransformerWithoutTertiary {
	private List<ExcitingTestResults> excitingTestResults;
	private List<OverallTestResults> overallTest;
	//private int transformerType = TransformerConstants.AUTO_TRANSFORMER_WITH_TERTIARY;
	private String transformerType = TransformerConstants.AUTO_TRANSFORMER_WITHOUT_TERTIARY;
	
	
//	public int getTransformerType() {
//		return transformerType;
//	}
//
//	public void setTransformerType(int transformerType) {
//		this.transformerType = transformerType;
//	}

	public String getTransformerType() {
		return transformerType;
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
