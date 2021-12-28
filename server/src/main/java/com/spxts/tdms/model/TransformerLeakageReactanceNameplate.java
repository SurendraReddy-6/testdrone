package com.spxts.tdms.model;


public class TransformerLeakageReactanceNameplate {
	
	 protected String id;
     
     protected String detcId;
    
     public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	protected String ltcId;

	private TransformerLrFields transformerLrFields;
	
	private LeakageTcReferences leakageTcReferences;

	public LeakageTcReferences getLeakageTcReferences() {
		return leakageTcReferences;
	}

	public void setLeakageTcReferences(LeakageTcReferences leakageTcReferences) {
		this.leakageTcReferences = leakageTcReferences;
	}

	public TransformerLrFields getTransformerLrFields() {
		return transformerLrFields;
	}

	public void setTransformerLrFields(TransformerLrFields transformerLrFields) {
		this.transformerLrFields = transformerLrFields;
	}
	
}
