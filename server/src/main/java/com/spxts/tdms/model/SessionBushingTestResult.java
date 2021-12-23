package com.spxts.tdms.model;


public class SessionBushingTestResult {
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
    private String bushingInsulation;
    
    private M7IwcData m7iwcData;
    
	public M7IwcData getM7iwcData() {
		return m7iwcData;
	}
	public void setM7iwcData(M7IwcData m7iwcData) {
		this.m7iwcData = m7iwcData;
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
	public String getBushingInsulation() {
		return bushingInsulation;
	}
	public void setBushingInsulation(String bushingInsulation) {
		this.bushingInsulation = bushingInsulation;
	}
}
