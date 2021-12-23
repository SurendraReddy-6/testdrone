package com.spxts.tdms.model;


import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.math.BigInteger;

public class ExcitationLinefreqVoltages {
	 private BigDecimal mdHighFreqkV;
	    private BigDecimal mdLowFreqkV;
		public BigDecimal getMdHighFreqkV() {
			return mdHighFreqkV;
		}
		public void setMdHighFreqkV(BigDecimal bigDecimal) {
			this.mdHighFreqkV = bigDecimal;
		}
		public BigDecimal getMdLowFreqkV() {
			return mdLowFreqkV;
		}
		public void setMdLowFreqkV(BigDecimal mdLowFreqkV) {
			this.mdLowFreqkV = mdLowFreqkV;
		}  
}
