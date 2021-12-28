package com.spxts.tdms.server.enitity;

public class TransformerDTO {
	private String id;
	private String transformerName;
	private long manufacturingDate;
	private String process;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransformerName() {
		return transformerName;
	}
	public void setTransformerName(String transformerName) {
		this.transformerName = transformerName;
	}
	public long getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(long manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
}
