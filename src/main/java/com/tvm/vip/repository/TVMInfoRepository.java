package com.tvm.vip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvm.vip.entity.TVMInfo;

@Repository
public interface TVMInfoRepository extends JpaRepository<TVMInfo, Long> {

	Optional<TVMInfo> findByname(String name);

}
