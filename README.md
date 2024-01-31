# Spring batch data migration

A spring batch project which migrates data from postgresql database (relational database) to mongodb (document database).

## Setup Instructions

1. Run the docker-compose command to start up the postgresql and mongoDB server: `docker-compose up -d`
2. Go to the batch project `cd batch` and run the spring boot project to create tables and populate it with seed data in the postgresql database `./mvnw spring-boot:run`.
3. TODO: migrate the data from postgresql to mongodb using spring batch
