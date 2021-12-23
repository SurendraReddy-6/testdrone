package com.spxts.tdms.server.ImpulseModel;

public class BILinformationDTO {
	
	private HVLineDTO hvLine;
	private LVLineDTO lvLine;
	private TVLineDTO tvLine;
	private NeutralDTO neutral;
	
	public HVLineDTO getHvLine() {
		return hvLine;
	}
	public void setHvLine(HVLineDTO hvLine) {
		this.hvLine = hvLine;
	}
	public LVLineDTO getLvLine() {
		return lvLine;
	}
	public void setLvLine(LVLineDTO lvLine) {
		this.lvLine = lvLine;
	}
	public TVLineDTO getTvLine() {
		return tvLine;
	}
	public void setTvLine(TVLineDTO tvLine) {
		this.tvLine = tvLine;
	}
	public NeutralDTO getNeutral() {
		return neutral;
	}
	public void setNeutral(NeutralDTO neutral) {
		this.neutral = neutral;
	}

}
