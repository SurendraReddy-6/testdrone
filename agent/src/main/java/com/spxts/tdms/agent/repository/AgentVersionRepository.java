package com.spxts.tdms.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spxts.tdms.agent.enitity.AgentVersionData;

public interface AgentVersionRepository  extends JpaRepository<AgentVersionData, Long>{

	
}
