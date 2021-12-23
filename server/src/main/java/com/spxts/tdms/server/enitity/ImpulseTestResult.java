package com.spxts.tdms.server.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "impulse_test_result")
public class ImpulseTestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serialNumber;
	private int locked;
	private long lastModifiedDate;
	private String lastModifiedBy;
	private long lastCreatedDate;
	private String lastCreatedBy;
	private String jsonTest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public long getLastCreatedDate() {
		return lastCreatedDate;
	}

	public void setLastCreatedDate(long lastCreatedDate) {
		this.lastCreatedDate = lastCreatedDate;
	}

	public String getLastCreatedBy() {
		return lastCreatedBy;
	}

	public void setLastCreatedBy(String lastCreatedBy) {
		this.lastCreatedBy = lastCreatedBy;
	}

	public String getJsonTest() {
		return jsonTest;
	}

	public void setJsonTest(String jsonTest) {
		this.jsonTest = jsonTest;
	}
}
