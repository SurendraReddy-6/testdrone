package com.tvm.vip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvm.vip.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query(value = "SELECT * FROM user where email=:email and password=:password", nativeQuery = true)
	public Users verifyLoginCredentails(@Param("email") String email, @Param("password") String password);

}
