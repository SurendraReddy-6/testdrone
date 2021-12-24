package com.tvm.vip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMStatus;

@Repository
public interface TVMModulesRepository extends JpaRepository<TVMModules, Long> {

	Optional<TVMModules> findByname(String name);

	@Query(value="SELECT * FROM tvm_modules where tvmid=:tvmid ",nativeQuery = true)
	List<TVMModules> findByTVMId(@Param("tvmid") Long tvmid);

}
