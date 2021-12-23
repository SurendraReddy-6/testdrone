CREATE TABLE user(
	 id 			bigint(20) NOT NULL AUTO_INCREMENT,
	email 			varchar(255) NOT NULL UNIQUE,
	active 			INT DEFAULT 1,
	name    	    varchar(255) NOT NULL,
	password        varchar(128) NOT NULL,
	contactnumber   BIGINT(10) DEFAULT NULL,
	createdon 		BIGINT(8) NOT NULL,
  	PRIMARY KEY (id),
  	UNIQUE KEY unique_user_details (email,contactnumber)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` (`id`, `email`, `active`, `name`, `password`, `contactnumber`, `createdon`) VALUES ('1', 'medari@gmail.com', '1', 'medari', 'medari', '123456789', '987654321');
