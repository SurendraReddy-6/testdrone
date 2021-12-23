package com.spxts.tdms.model;

	import java.util.List;

	import com.spxts.tdms.model.ExcitingTestResults;
	//import com.spxts.tdms.server.dobleUtils.DataModelR2.DtaSessions.DtaSession.TwoWindingTransformer.OverallTestSet.OverallTest;
	public class TwoWindingTransformer {
//	     private ExcitingCurrentTestSet excitingCurrentTestSet;
	//
//	     private OverallTestSet overallTestSet;
		private ExcitingTestResults excitingTestResults;
		private List<OverallTestResults> overallTest;
		//private int transformerType = TransformerConstants.TWO_WINDING_TRANSFORMER;
		private String transformerType = TransformerConstants.TWO_WINDING_TRANSFORMER;
		
		public String getTransformerType() {
			return transformerType;
		}
		public void setTransformerType(String transformerType) {
			this.transformerType = transformerType;
		}
		//		public int getTransformerType() {
//			return transformerType;
//		}
//		public void setTransformerType(int transformerType) {
//			this.transformerType = transformerType;
//		}
		public ExcitingTestResults getExcitingTestResults() {
			return excitingTestResults;
		}
		public void setExcitingTestResults(ExcitingTestResults excitingTestResults) {
			this.excitingTestResults = excitingTestResults;
		}
		public List<OverallTestResults> getOverallTest() {
			return overallTest;
		}
		public void setOverallTest(List<OverallTestResults> overallTest) {
			this.overallTest = overallTest;
		}
		
	}
