# Spring batch data migration

A ETL project which migrates data from postgresql (relational) database to mongodb (document) database for faster reads.

## Setup Instructions

1. Run the docker-compose command to start up the postgresql and mongoDB server: `docker-compose up -d`
2. Go to the batch project `cd batch` and run the spring boot project to create tables and populate it with seed data in the postgresql database and also migrate the data from mongodb to postgres using spring batch `./mvnw spring-boot:run`.
