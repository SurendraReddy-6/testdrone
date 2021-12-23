package com.spxts.tdms.model;

import java.util.List;

public class ExcitingTestResults {
	//private ExcitationIwcData iwcData;
	private List<ExcitationIwcData> iwcData;
	private List<ExcitationLinefreqVoltages> linefreqTestVoltages;
	private ExcitationExcitingCurrentFields excitingCurrentFields;
	private ExcitationTcReferences excitationTcReferences;
	private String detc;
	private String ltc;
	private String useIwcForLinefreqTest;
	private String lineFrequency;
	private String includeInPlot;
	private String detcId;
	private String ltcId;

	public ExcitationTcReferences getExcitationTcReferences() {
		return excitationTcReferences;
	}

	public void setExcitationTcReferences(ExcitationTcReferences excitationTcReferences) {
		this.excitationTcReferences = excitationTcReferences;
	}

	public List<ExcitationLinefreqVoltages> getLinefreqTestVoltages() {
		return linefreqTestVoltages;
	}

	public void setLinefreqTestVoltages(List<ExcitationLinefreqVoltages> linefreqTestVoltages) {
		this.linefreqTestVoltages = linefreqTestVoltages;
	}

	public ExcitationExcitingCurrentFields getExcitingCurrentFields() {
		return excitingCurrentFields;
	}

	public void setExcitingCurrentFields(ExcitationExcitingCurrentFields excitingCurrentFields) {
		this.excitingCurrentFields = excitingCurrentFields;
	}

	public List<ExcitationIwcData> getIwcData() {
		return iwcData;
	}

	public void setIwcData(List<ExcitationIwcData> iwcData) {
		this.iwcData = iwcData;
	}

//	public List<LinefreqTestVoltages> getLinefreqTestVoltages() {
//		return linefreqTestVoltages;
//	}
//	public void setLinefreqTestVoltages(List<LinefreqTestVoltages> linefreqTestVoltages) {
//		this.linefreqTestVoltages = linefreqTestVoltages;
//	}
//	public ExcitingCurrentFields getExcitingCurrentFields() {
//		return excitingCurrentFields;
//	}
//	public void setExcitingCurrentFields(ExcitingCurrentFields excitingCurrentFields) {
//		this.excitingCurrentFields = excitingCurrentFields;
//	}
	public String getDetc() {
		return detc;
	}

	public void setDetc(String detc) {
		this.detc = detc;
	}

	public String getLtc() {
		return ltc;
	}

	public void setLtc(String ltc) {
		this.ltc = ltc;
	}

	public String getUseIwcForLinefreqTest() {
		return useIwcForLinefreqTest;
	}

	public void setUseIwcForLinefreqTest(String useIwcForLinefreqTest) {
		this.useIwcForLinefreqTest = useIwcForLinefreqTest;
	}

	public String getLineFrequency() {
		return lineFrequency;
	}

	public void setLineFrequency(String lineFrequency) {
		this.lineFrequency = lineFrequency;
	}

	public String getIncludeInPlot() {
		return includeInPlot;
	}

	public void setIncludeInPlot(String includeInPlot) {
		this.includeInPlot = includeInPlot;
	}

	public String getDetcId() {
		return detcId;
	}

	public void setDetcId(String detcId) {
		this.detcId = detcId;
	}

	public String getLtcId() {
		return ltcId;
	}

	public void setLtcId(String ltcId) {
		this.ltcId = ltcId;
	}

}
