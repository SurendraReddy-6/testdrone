CREATE TABLE tvm_info(
	id 			bigint(20) NOT NULL AUTO_INCREMENT,
	name			varchar(255) NOT NULL UNIQUE,
	location		varchar(255) NOT NULL,
	serial_no 		varchar(255) NOT NULL,
	active 			INT DEFAULT 1,
	createdon 		BIGINT(8) NOT NULL,
  	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tvm_modules(
	 id 			bigint(20) NOT NULL AUTO_INCREMENT,
	 name    	    varchar(255) NOT NULL UNIQUE,
	status_code 	INT DEFAULT 1,
	module_serial   varchar(255) NOT NULL,
	tvmid 			bigint(20) NOT NULL,
	value 			varchar(255) NOT NULL,
  	PRIMARY KEY (id),
  	FOREIGN KEY (tvmid) REFERENCES tvm_info (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tvm_status(
	 id 			bigint(20) NOT NULL AUTO_INCREMENT,
	name    	    varchar(255) NOT NULL UNIQUE,
	status_code     INT DEFAULT 1,
	value        	varchar(128) NOT NULL,
	tvmid 			bigint(20) NOT NULL,
  	PRIMARY KEY (id),
  	FOREIGN KEY (tvmid) REFERENCES tvm_info (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tvm_sales(
	id 			bigint(20) NOT NULL AUTO_INCREMENT,
	amount    	    BIGINT(8) NOT NULL,
	transaction_id  varchar(128) NOT NULL,
	productCode 	varchar(255) NOT NULL ,
	ticketid		varchar(128) NOT NULL, 
	productType		varchar(128) NOT NULL,
	cashAmount		 BIGINT(8) NOT NULL,
	changeAmount	 BIGINT(8) NOT NULL,
	ticketAmount	 BIGINT(8) NOT NULL,
	createdon 		BIGINT(8) NOT NULL,
  	tvmid 			BIGINT(20) NOT NULL,
  	payment_method varchar(128) NOT NULL,
  	PRIMARY KEY (id),
  	FOREIGN KEY (tvmid) REFERENCES tvm_info (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tvm_config(
	 id int NOT NULL AUTO_INCREMENT,  	 
  	 active 			INT DEFAULT 1,
  	 configType 		INT DEFAULT 1,
  	 moduleid 		BIGINT(20) NOT NULL,
 	 tvmid 			BIGINT(20) NOT NULL,
 	 module_status_code INT DEFAULT 1,
  	PRIMARY KEY (id),
  	FOREIGN KEY (moduleid) REFERENCES tvm_modules (id),
  	FOREIGN KEY (tvmid) REFERENCES tvm_info (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE tvm_module_status(
	 id int NOT NULL AUTO_INCREMENT,  	 
  	 moduleid 		BIGINT(20) NOT NULL,
 	 tvmid 			BIGINT(20) NOT NULL,
 	 module_status_code INT DEFAULT 1,
 	 active 			INT DEFAULT 1,
 	 createdon 		BIGINT(8) NOT NULL,
 	 module_status varchar(128) NOT NULL,
  	PRIMARY KEY (id),
  	FOREIGN KEY (moduleid) REFERENCES tvm_modules (id),
  	FOREIGN KEY (tvmid) REFERENCES tvm_info (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
