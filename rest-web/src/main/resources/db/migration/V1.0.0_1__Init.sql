-- Table: Phone Number

CREATE TABLE phone_number_db
(
  id              BIGSERIAL,
  phone_number    character varying(255)    NOT NULL,
  phone_type      character varying(255)    NOT NULL,
  occurrences     INTEGER                   NOT NULL,
  CONSTRAINT phone_number_db_pk PRIMARY KEY (id)
);