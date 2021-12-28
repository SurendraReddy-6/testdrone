CREATE TABLE user(
	 id 			BIGINT(8)  PRIMARY KEY NOT NULL,
	email 			VARCHAR(255) NOT NULL UNIQUE,
	active 			INT DEFAULT 1,
	name    	    VARCHAR(255) NOT NULL,
	password        VARCHAR(128) NOT NULL,
	contactnumber   BIGINT(10) DEFAULT NULL,
	createdon 		BIGINT(8) NOT NULL,
  	CONSTRAINT unique_user_details UNIQUE (email,contactnumber)
);

INSERT INTO user (`id`, `email`, `active`, `name`, `password`, `contactnumber`, `createdon`) VALUES ('1', 'medari@gmail.com', '1', 'medari', 'medari', '123456789', '987654321');

CREATE TABLE agent_version_data(
	 id 			BIGINT(8)  PRIMARY KEY NOT NULL,
	agentname 			VARCHAR(512) NOT NULL,
	version   			VARCHAR(512) NOT NULL,
	agent_software_path VARCHAR(512) NOT NULL,
	manditory_upgrade   INT DEFAULT 0,
	active 				INT DEFAULT 1,
	createdon 		BIGINT(8) NOT NULL,
	updatedon 		BIGINT(8) NOT NULL
);

CREATE TABLE transformer_details(
	 id 			BIGINT(8)  PRIMARY KEY NOT NULL,
	filepath 			VARCHAR(512) NOT NULL,
	downloadedtime 		BIGINT(8) NOT NULL,
	updatedtime 		BIGINT(8) NOT NULL,
	active 				INT DEFAULT 1,
	createdon 		BIGINT(8) NOT NULL
);
