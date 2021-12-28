CREATE TABLE impulse_test_result(
	 id 			bigint(20) NOT NULL AUTO_INCREMENT,
	 serialNumber varchar(255) NOT NULL UNIQUE,
	 locked        int DEFAULT 0,
	 lastModifiedDate BIGINT(8) NOT NULL,
	 lastModifiedBy	 varchar(300) DEFAULT NULL ,
	 lastCreatedDate  BIGINT(8) NOT NULL,
	 lastCreatedBy   varchar(200) DEFAULT NULL,
	 jsonTest		longtext,
  	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
