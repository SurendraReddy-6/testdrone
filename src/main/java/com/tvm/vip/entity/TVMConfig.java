package com.tvm.vip.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tvm_config")
public class TVMConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer active;
	private Integer configType;
	private Long moduleid;
	private Long tvmid;
	private Integer module_status_code;

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getConfigType() {
		return configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public Long getModuleid() {
		return moduleid;
	}

	public void setModuleid(Long moduleid) {
		this.moduleid = moduleid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTvmid() {
		return tvmid;
	}

	public void setTvmid(Long tvmid) {
		this.tvmid = tvmid;
	}

	public Integer getModule_status_code() {
		return module_status_code;
	}

	public void setModule_status_code(Integer module_status_code) {
		this.module_status_code = module_status_code;
	}
}
