package com.spxts.tdms.agent.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transformer_details")
public class TransformerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int active;
	private long createdon;
	private String filepath;
	private long downloadedtime;
	private long updatedtime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public long getCreatedon() {
		return createdon;
	}
	public void setCreatedon(long createdon) {
		this.createdon = createdon;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public long getDownloadedtime() {
		return downloadedtime;
	}
	public void setDownloadedtime(long downloadedtime) {
		this.downloadedtime = downloadedtime;
	}
	public long getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(long updatedtime) {
		this.updatedtime = updatedtime;
	}
}
