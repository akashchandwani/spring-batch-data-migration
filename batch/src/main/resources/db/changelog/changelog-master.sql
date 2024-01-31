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

