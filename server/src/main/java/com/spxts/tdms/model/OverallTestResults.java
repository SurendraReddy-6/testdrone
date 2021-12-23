package com.spxts.tdms.model;
//import com.spxts.tdms.server.service.impl.OverallIwcData;
//import com.spxts.tdms.server.dobleUtils.DataModelR2.DtaSessions.DtaSession.TwoWindingTransformer.OverallTestSet.OverallTest.IwcData;
//import com.spxts.tdms.server.dobleUtils.DataModelR2.DtaSessions.DtaSession.TwoWindingTransformer.OverallTestSet.OverallTest.IwcData;;
public class OverallTestResults {
	   private OverallIwcData iwcData;
	   private String dateTestedUtc;
	   private String ratingExpertSystem;
	   private String ratingTester;
	   private String messageExpertSystem;
	   private String requestedTestKV;
	   private String testKV;
	   private String testCircuit;
	   private String ma;
	   private String watts;
	   private String measuredCap;
	   private String pfm;
	   private String pfc;
	   private String correctionFactor;
	   private String lineId;
	   private String insulation;
	
	public OverallIwcData getIwcData() {
		return iwcData;
	}
	public void setIwcData(OverallIwcData iwcData) {
		this.iwcData = iwcData;
	}
	public String getDateTestedUtc() {
		return dateTestedUtc;
	}
	public void setDateTestedUtc(String dateTestedUtc) {
		this.dateTestedUtc = dateTestedUtc;
	}
	public String getRatingExpertSystem() {
		return ratingExpertSystem;
	}
	public void setRatingExpertSystem(String ratingExpertSystem) {
		this.ratingExpertSystem = ratingExpertSystem;
	}
	public String getRatingTester() {
		return ratingTester;
	}
	public void setRatingTester(String ratingTester) {
		this.ratingTester = ratingTester;
	}
	public String getMessageExpertSystem() {
		return messageExpertSystem;
	}
	public void setMessageExpertSystem(String messageExpertSystem) {
		this.messageExpertSystem = messageExpertSystem;
	}
	public String getRequestedTestKV() {
		return requestedTestKV;
	}
	public void setRequestedTestKV(String requestedTestKV) {
		this.requestedTestKV = requestedTestKV;
	}
	public String getTestKV() {
		return testKV;
	}
	public void setTestKV(String testKV) {
		this.testKV = testKV;
	}
	public String getTestCircuit() {
		return testCircuit;
	}
	public void setTestCircuit(String testCircuit) {
		this.testCircuit = testCircuit;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getWatts() {
		return watts;
	}
	public void setWatts(String watts) {
		this.watts = watts;
	}
	public String getMeasuredCap() {
		return measuredCap;
	}
	public void setMeasuredCap(String measuredCap) {
		this.measuredCap = measuredCap;
	}
	public String getPfm() {
		return pfm;
	}
	public void setPfm(String pfm) {
		this.pfm = pfm;
	}
	public String getPfc() {
		return pfc;
	}
	public void setPfc(String pfc) {
		this.pfc = pfc;
	}
	public String getCorrectionFactor() {
		return correctionFactor;
	}
	public void setCorrectionFactor(String correctionFactor) {
		this.correctionFactor = correctionFactor;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getInsulation() {
		return insulation;
	}
	public void setInsulation(String insulation) {
		this.insulation = insulation;
	}
}
