package com.spxts.tdms.agent.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agent_version_data")
public class AgentVersionData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String agentname;
	private String version;
	private String agent_software_path;
	private int manditory_upgrade;
	private int active;
	private long createdon;
	private long updatedon;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAgentname() {
		return agentname;
	}
	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAgent_software_path() {
		return agent_software_path;
	}
	public void setAgent_software_path(String agent_software_path) {
		this.agent_software_path = agent_software_path;
	}
	public int getManditory_upgrade() {
		return manditory_upgrade;
	}
	public void setManditory_upgrade(int manditory_upgrade) {
		this.manditory_upgrade = manditory_upgrade;
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
	public long getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(long updatedon) {
		this.updatedon = updatedon;
	}
	
}
