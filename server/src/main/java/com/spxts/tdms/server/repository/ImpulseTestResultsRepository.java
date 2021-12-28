package com.spxts.tdms.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spxts.tdms.server.enitity.ImpulseTestResult;
import com.spxts.tdms.server.enitity.Users;


@Repository
public interface ImpulseTestResultsRepository extends JpaRepository<ImpulseTestResult, Long>{


}
