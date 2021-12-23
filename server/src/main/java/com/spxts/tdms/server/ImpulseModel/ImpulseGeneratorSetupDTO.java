package com.spxts.tdms.server.ImpulseModel;

import java.util.List;

public class ImpulseGeneratorSetupDTO {

	private int number;
	private int StagesinSeries;
	private int StagesinParallel;
	private List<IGenSetupResDTO> ROneFront;
	private List<IGenSetupResDTO> ROneTail; 
	private int ROneFrontCount;
	private int ROneTailCount;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStagesinSeries() {
		return StagesinSeries;
	}
	public void setStagesinSeries(int stagesinSeries) {
		StagesinSeries = stagesinSeries;
	}
	public int getStagesinParallel() {
		return StagesinParallel;
	}
	public void setStagesinParallel(int stagesinParallel) {
		StagesinParallel = stagesinParallel;
	}
	public List<IGenSetupResDTO> getROneFront() {
		return ROneFront;
	}
	public void setROneFront(List<IGenSetupResDTO> rOneFront) {
		ROneFront = rOneFront;
	}
	
	public int getROneFrontCount() {
		return ROneFront.size();
	}
	public void setROneFrontCount(int value) {
		ROneFrontCount= value;
	}
	
	public List<IGenSetupResDTO> getROneTail() {
		return ROneTail;
	}
	public void setROneTail(List<IGenSetupResDTO> rOneTail) {
		ROneTail = rOneTail;
	}
	public int getROneTailCount() {
		return ROneTail.size();
	}
	
	public void setROneTailCount(int value) {
		ROneTailCount=value;
	}
	
}
