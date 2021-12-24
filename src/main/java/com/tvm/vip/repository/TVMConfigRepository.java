package com.tvm.vip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvm.vip.entity.TVMConfig;

@Repository
public interface TVMConfigRepository extends JpaRepository<TVMConfig, Long> {

}
