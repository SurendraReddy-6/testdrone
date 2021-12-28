package com.spxts.tdms.server.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class TenantConfiguration {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FlywayRunner flywayRunner;

	@Autowired
	DataSourceProps dataSourceProps;

	@Bean
	public DataSource dataSource() {

		Map<Object, Object> resolvedDataSources = new HashMap<>();
		TenantDataSource multiDataSource = null;
		try {
			flywayRunner.migrate();

			DataSource dataSource = DataSourceBuilder.create().username(dataSourceProps.getUsername())
					.password(dataSourceProps.getPassword()).url(dataSourceProps.getUrl())
					.driverClassName("com.mysql.jdbc.Driver").build();

			resolvedDataSources.put("server", dataSource);

			multiDataSource = new TenantDataSource();
			multiDataSource.setDefaultTargetDataSource(dataSource);
			multiDataSource.setTargetDataSources(resolvedDataSources);

			multiDataSource.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw e;
		}
		return multiDataSource;
	}
}