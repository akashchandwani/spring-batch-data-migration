-- liquibase formatted sql

-- changeset akashchandwani:1
create table person (
    id serial primary key,
    first_name varchar(225) not null,
    last_name varchar(225) not null,
    email varchar(225) unique not null
);

-- changeset akashchandwani:2
CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    industry VARCHAR(50),
    address VARCHAR(100)
);

-- changeset akashchandwani:3
create table job_history (
    id serial primary key,
    person_id int references person(id) on delete cascade,
    company_id int references company(id) on delete set null,
    start_date date not null,
    end_date date not null,
    job_title varchar(225) not null
);

-- changeset akashchandwani:4
INSERT INTO person (id, first_name, last_name, email)
VALUES
    (1, 'John', 'Doe', 'john.doe@example.com'),
    (2, 'Jane', 'Smith', 'jane.smith@example.com'),
    (3, 'Mike', 'Johnson', 'mike.johnson@example.com'),
    (4, 'Emily', 'Brown', 'emily.brown@example.com'),
    (5, 'David', 'Miller', 'david.miller@example.com');

-- changeset akashchandwani:5
INSERT INTO company(id, name, industry, address)
VALUES
    (1, 'Google', 'IT', '1600 Amphitheatre Parkway'),
    (2, 'Facebook', 'IT', '1 Hacker Way, Menlo Park, CA 94205'),
    (3, 'Amazon', 'IT', '410 Terry Ave N, Seattle 98109, WA');


-- changeset akashchandwani:6
INSERT INTO job_history (person_id, company_id, start_date, end_date, job_title)
VALUES
    (1, 1, '2010-01-01', '2011-01-01', 'SDE 1'),
    (1, 2, '2011-01-01', '2012-01-01', 'SDE 2'),
    (2, 3, '2013-01-01', '2014-01-01', 'SDE 1'),
    (2, 2, '2014-01-01', '2015-01-01', 'SDE 2'),
    (3, 1, '2010-01-01', '2011-01-01', 'SDE 1'),
    (3, 2, '2011-01-01', '2012-01-01', 'SDE 2'),
    (4, 3, '2010-01-01', '2011-01-01', 'SDE 1'),
    (4, 2, '2011-01-01', '2012-01-01', 'SDE 2'),
    (5, 1, '2010-01-01', '2011-01-01', 'SDE 1'),
    (5, 3, '2011-01-01', '2012-01-01', 'SDE 2');


-- changeset akashchandwani:7
CREATE TABLE BATCH_JOB_INSTANCE  (
  JOB_INSTANCE_ID BIGINT  PRIMARY KEY ,
  VERSION BIGINT,
  JOB_NAME VARCHAR(100) NOT NULL ,
  JOB_KEY VARCHAR(32) NOT NULL
);


-- changeset akashchandwani:8
CREATE TABLE BATCH_JOB_EXECUTION  (
  JOB_EXECUTION_ID BIGINT  PRIMARY KEY ,
  VERSION BIGINT,
  JOB_INSTANCE_ID BIGINT NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  START_TIME TIMESTAMP DEFAULT NULL,
  END_TIME TIMESTAMP DEFAULT NULL,
  STATUS VARCHAR(10),
  EXIT_CODE VARCHAR(20),
  EXIT_MESSAGE VARCHAR(2500),
  LAST_UPDATED TIMESTAMP,
  constraint JOB_INSTANCE_EXECUTION_FK foreign key (JOB_INSTANCE_ID)
  references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ;

-- changeset akashchandwani:9
CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	PARAMETER_NAME VARCHAR(100) NOT NULL ,
	PARAMETER_TYPE VARCHAR(100) NOT NULL ,
	PARAMETER_VALUE VARCHAR(2500) ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);

-- changeset akashchandwani:10
CREATE TABLE BATCH_STEP_EXECUTION  (
  STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY ,
  VERSION BIGINT NOT NULL,
  STEP_NAME VARCHAR(100) NOT NULL,
  JOB_EXECUTION_ID BIGINT NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  START_TIME TIMESTAMP DEFAULT NULL ,
  END_TIME TIMESTAMP DEFAULT NULL,
  STATUS VARCHAR(10),
  COMMIT_COUNT BIGINT ,
  READ_COUNT BIGINT ,
  FILTER_COUNT BIGINT ,
  WRITE_COUNT BIGINT ,
  READ_SKIP_COUNT BIGINT ,
  WRITE_SKIP_COUNT BIGINT ,
  PROCESS_SKIP_COUNT BIGINT ,
  ROLLBACK_COUNT BIGINT ,
  EXIT_CODE VARCHAR(20) ,
  EXIT_MESSAGE VARCHAR(2500) ,
  LAST_UPDATED TIMESTAMP,
  constraint JOB_EXECUTION_STEP_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

-- changeset akashchandwani:11
CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
  JOB_EXECUTION_ID BIGINT PRIMARY KEY,
  SHORT_CONTEXT VARCHAR(2500) NOT NULL,
  SERIALIZED_CONTEXT TEXT,
  constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

-- changeset akashchandwani:12
CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
  STEP_EXECUTION_ID BIGINT PRIMARY KEY,
  SHORT_CONTEXT VARCHAR(2500) NOT NULL,
  SERIALIZED_CONTEXT TEXT,
  constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
  references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ;

-- changeset akashchandwani:13
CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_JOB_SEQ;