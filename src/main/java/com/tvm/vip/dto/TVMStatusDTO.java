package com.tvm.vip.dto;

import java.util.List;

public class TVMStatusDTO {

	private Long id;
	private String name	;
	private Integer status_code;
	private String value;
	private Long tvmid;
	private List<TVMModulesDTO> modulesListDTO;
	
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
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getTvmid() {
		return tvmid;
	}
	public void setTvmid(Long tvmid) {
		this.tvmid = tvmid;
	}
	public List<TVMModulesDTO> getModulesListDTO() {
		return modulesListDTO;
	}
	public void setModulesListDTO(List<TVMModulesDTO> modulesListDTO) {
		this.modulesListDTO = modulesListDTO;
	}
}
