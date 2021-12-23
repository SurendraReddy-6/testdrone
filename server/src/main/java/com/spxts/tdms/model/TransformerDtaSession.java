package com.spxts.tdms.model;



public class TransformerDtaSession {
	
	
     private  String currentTimezone;
    
     private  String utcOffsetMinutes;
    
     private  String location;
     
     private  String division;
    
     private  String company;
     
      private String cctDesignation;
   
     private  String sessionNote;
    
     private  String sessionCreatedDateUtc;
    
    private  String firstTestDateUtc;
   
     private  String lastTestDateUtc;

	public String getCurrentTimezone() {
		return currentTimezone;
	}

	public void setCurrentTimezone(String currentTimezone) {
		this.currentTimezone = currentTimezone;
	}

	public String getUtcOffsetMinutes() {
		return utcOffsetMinutes;
	}

	public void setUtcOffsetMinutes(String utcOffsetMinutes) {
		this.utcOffsetMinutes = utcOffsetMinutes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCctDesignation() {
		return cctDesignation;
	}

	public void setCctDesignation(String cctDesignation) {
		this.cctDesignation = cctDesignation;
	}

	public String getSessionNote() {
		return sessionNote;
	}

	public void setSessionNote(String sessionNote) {
		this.sessionNote = sessionNote;
	}

	public String getSessionCreatedDateUtc() {
		return sessionCreatedDateUtc;
	}

	public void setSessionCreatedDateUtc(String sessionCreatedDateUtc) {
		this.sessionCreatedDateUtc = sessionCreatedDateUtc;
	}

	public String getFirstTestDateUtc() {
		return firstTestDateUtc;
	}

	public void setFirstTestDateUtc(String firstTestDateUtc) {
		this.firstTestDateUtc = firstTestDateUtc;
	}

	public String getLastTestDateUtc() {
		return lastTestDateUtc;
	}

	public void setLastTestDateUtc(String lastTestDateUtc) {
		this.lastTestDateUtc = lastTestDateUtc;
	}

	

}
