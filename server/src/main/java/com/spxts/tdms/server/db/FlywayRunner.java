package com.spxts.tdms.server.db;

import java.sql.SQLException;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlywayRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlywayRunner.class);
    
    @Autowired
	private DataSourceProps dataSourceProps;
    
    public void migrate() {
  	  LOGGER.info(dataSourceProps.getUrl()+" Migrating schema {} tx");
        Flyway flyway = new Flyway();
        flyway.setLocations("db/migration/server" );
        flyway.setDataSource(dataSourceProps.getUrl(),dataSourceProps.getUsername(),dataSourceProps.getPassword());
        flyway.migrate();
        try {
            flyway.getDataSource().getConnection().close();
        } catch (SQLException e) {
            LOGGER.warn("Failed to close flyway datasource connection", e);
        }
		
	}
}
