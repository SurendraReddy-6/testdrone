package com.spxts.tdms.server.ImpulseModel;

import java.util.List;

public class ImpulseSurgeLogDTO {
	
	private String unit;
	private String test;
	private String tech;
	private String date;
	private String test_floor;
	private String customername;
	
	private int base_mva;
	private float namePlateDesignImpedance;
	
	private BILinformationDTO bilDto;
	private	List<ImpulseGeneratorSetupDTO> impulseGenSetupDTO;
	
	private List<ShotDTO> shotInfo;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTest_floor() {
		return test_floor;
	}

	public void setTest_floor(String test_floor) {
		this.test_floor = test_floor;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public BILinformationDTO getBilDto() {
		return bilDto;
	}

	public void setBilDto(BILinformationDTO bilDto) {
		this.bilDto = bilDto;
	}

	public List<ImpulseGeneratorSetupDTO> getImpulseGenSetupDTO() {
		return impulseGenSetupDTO;
	}

	public void setImpulseGenSetupDTO(List<ImpulseGeneratorSetupDTO> impulseGenSetupDTO) {
		this.impulseGenSetupDTO = impulseGenSetupDTO;
	}

	public List<ShotDTO> getShotInfo() {
		return shotInfo;
	}

	public void setShotInfo(List<ShotDTO> shotInfo) {
		this.shotInfo = shotInfo;
	}

	public int getBase_mva() {
		return base_mva;
	}

	public void setBase_mva(int base_mva) {
		this.base_mva = base_mva;
	}

	public float getNamePlateDesignImpedance() {
		return namePlateDesignImpedance;
	}

	public void setNamePlateDesignImpedance(float d) {
		this.namePlateDesignImpedance = d;
	}

}
