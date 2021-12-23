package com.spxts.tdms.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spxts.tdms.server.enitity.Users;

@Repository
public interface WaukeshaTransformerRepository extends JpaRepository<Users, Long> {

}
