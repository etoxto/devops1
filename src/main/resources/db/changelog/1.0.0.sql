--liquibase formatted sql
--changeset yourname:etoxto

CREATE TABLE note (
                      id SERIAL PRIMARY KEY,
                      owner VARCHAR(255) NOT NULL,
                      created_date TIMESTAMP NOT NULL,
                      description TEXT NOT NULL
);