package com.tvm.vip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvm.vip.entity.TVMSales;

@Repository
public interface TVMSalesRepository extends JpaRepository<TVMSales, Long> {

	@Query(value = "SELECT * FROM vip.tvm_sales where tvmid=:tvmid order by createdon DESC; ", nativeQuery = true)
	List<TVMSales> findBytvmid(@Param("tvmid") Long tvmid);

	@Query(value = "SELECT * FROM tvm_sales order by createdon DESC ", nativeQuery = true)
	List<TVMSales> getResultByDesc();
}
