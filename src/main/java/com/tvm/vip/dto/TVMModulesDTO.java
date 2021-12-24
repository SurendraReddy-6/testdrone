package com.tvm.vip.dto;

public class TVMModulesDTO {

	private Long id;
	private String name	;
	private Integer status_code;
	private String module_serial;
	private Long tvmid ;
	private String value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModule_serial() {
		return module_serial;
	}
	public void setModule_serial(String module_serial) {
		this.module_serial = module_serial;
	}
	public Long getTvmid() {
		return tvmid;
	}
	public void setTvmid(Long tvmid) {
		this.tvmid = tvmid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
}
