CREATE TABLE users
(
    id  SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL
);

CREATE TABLE organisations
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255)             NOT NULL,
    owner_id          BIGINT                   NOT NULL,
    cord_x            INT                      NOT NULL,
    cord_y            DOUBLE PRECISION         NOT NULL,
    creation_date     TIMESTAMP WITH TIME ZONE NOT NULL,
    annual_turnover   DOUBLE PRECISION         NOT NULL,
    employees_count   BIGINT                   NOT NULL,
    organisation_type VARCHAR(50)              NOT NULL,
    street            VARCHAR(255),
    town_x            INT,
    town_y            FLOAT,
    town_z            INT
);