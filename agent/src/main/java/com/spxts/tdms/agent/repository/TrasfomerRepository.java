package com.spxts.tdms.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spxts.tdms.agent.enitity.TransformerDetails;

public interface TrasfomerRepository  extends JpaRepository<TransformerDetails, Long>{

	
}
