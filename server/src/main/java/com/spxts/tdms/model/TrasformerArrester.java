package com.spxts.tdms.model;

public class TrasformerArrester {
	private  String id;
	   
    private  String saSerialNum;
    
    private  String saLocation;
    
    private  String saStackorder;
    
    private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSaSerialNum() {
		return saSerialNum;
	}

	public void setSaSerialNum(String saSerialNum) {
		this.saSerialNum = saSerialNum;
	}

	public String getSaLocation() {
		return saLocation;
	}

	public void setSaLocation(String saLocation) {
		this.saLocation = saLocation;
	}

	public String getSaStackorder() {
		return saStackorder;
	}

	public void setSaStackorder(String saStackorder) {
		this.saStackorder = saStackorder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
