CREATE DATABASE "Mockup";

CREATE TABLE IF NOT EXISTS latest_education (
	id numeric(20) NOT NULL,
	last_education varchar(36) NULL,
	name_institution varchar(255) NULL,
	major varchar(255) NULL,
	graduation_year varchar(10) NULL,
	gpa varchar (10) NULL
	CONSTRAINT latest_education_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS training_history (
	id numeric(20) NOT NULL,
	name_training varchar(36) NULL,
	is_any_certificate boolean DEFAULT FALSE,
	year varchar(10) NULL,
	CONSTRAINT training_history_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS job_experiences (
	id numeric(20) NOT NULL,
	company_name varchar(36) NULL,
	last_position varchar(255) NULL,
	last_income numeric(19,2) NULL,
	year varchar(10) NULL,
	CONSTRAINT job_experiences_pk PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS user_authentication (
	id numeric(20) NOT NULL,
	email varchar(255) NULL,
	password varchar(255) NULL,
	CONSTRAINT user_authentication_id PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS biodata (
	id numeric(20) NOT NULL,
	created_date timestamp NOT NULL,
	positon varchar(36) NOT NULL,
	"name" varchar(255) NOT NULL,
	id_card varchar(255) NOT NULL,
	birth_date_and_place varchar(255) NOT NULL,
	gender varchar(3) NOT NULL,
	blood_group varchar(3) NOT NULL,
	status varchar(36) NOT NULL,
	address_by_id_card varchar(255) NOT NULL,
	address_now varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	mobile_phone varchar(15) NOT NULL,
	closest_person varchar(255) NOT NULL,
	latest_education_id numeric(20) NULL,
	training_history_id numeric(20) NULL,
	job_experiences_id numeric(20) NULL,
	skill varchar (255) NULL,
	willing_to_be_placed_in_all_offices boolean DEFAULT false,
	expected_income numeric(19,2) NULL,
	CONSTRAINT biodata_pk PRIMARY KEY (id)
);