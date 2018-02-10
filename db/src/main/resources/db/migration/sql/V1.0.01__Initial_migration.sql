-- Create sequences -- TODO: decrease VARCHAR(255)
CREATE SEQUENCE "${schema}".sq_entity;
CREATE SEQUENCE "${schema}".sq_ride;


-- Create geo tables
CREATE TABLE "${schema}".countries (
  id   VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE INDEX countries_name ON "${schema}".countries (name);

CREATE TABLE "${schema}".states (
  id      VARCHAR(255) PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  country VARCHAR(255) REFERENCES "${schema}".countries (id)
);
CREATE INDEX states_name ON "${schema}".states (name);
CREATE INDEX states_country ON "${schema}".states (country);

CREATE TABLE "${schema}".cities (
  id    VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL,
  state VARCHAR(255) REFERENCES "${schema}".states (id)
);
CREATE INDEX cities_name ON "${schema}".cities (name);
CREATE INDEX cities_state ON "${schema}".cities (state);

CREATE TABLE "${schema}".street (
  id   VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  city VARCHAR(255) REFERENCES "${schema}".cities (id)
);
CREATE INDEX streets_name ON "${schema}".street (name);
CREATE INDEX streets_city ON "${schema}".street (city);

CREATE TABLE "${schema}".districts (
  id   VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE INDEX district_name ON "${schema}".districts (name);

CREATE TABLE "${schema}".street_district_mapper (
  id          INTEGER PRIMARY KEY,
  district_id VARCHAR(255) NOT NULL,
  street_id   VARCHAR(255) NOT NULL,
  building    VARCHAR(255) NOT NULL
);
CREATE INDEX sdm_districtId ON "${schema}".street_district_mapper (district_id);
CREATE INDEX sdm_streetId ON "${schema}".street_district_mapper (street_id);


-- Create common tables
CREATE TABLE "${schema}".clients (
  login        VARCHAR(255) PRIMARY KEY,
  first_name   VARCHAR(255) NOT NULL,
  last_name    VARCHAR(255),
  rides_amount INTEGER,
  client_type  VARCHAR(255)
);

CREATE TABLE "${schema}".addresses (
  id       INTEGER PRIMARY KEY,
  country  VARCHAR(255),
  state    VARCHAR(255),
  city     VARCHAR(255),
  street   VARCHAR(255),
  building VARCHAR(255),
  district VARCHAR(255)
);

CREATE TABLE "${schema}".rides (
  id              INTEGER PRIMARY KEY,
  client          VARCHAR(255) REFERENCES "${schema}".clients (login),
  from_address    INTEGER REFERENCES "${schema}".addresses (id),
  to_address      INTEGER REFERENCES "${schema}".addresses (id),
  date_in         TIMESTAMP,
  ride_in         TIMESTAMP,
  ride_out        TIMESTAMP,
  car_id          INTEGER REFERENCES "${schema}".cars (id),
  adult_in_car    INTEGER,
  children_in_car INTEGER,
  price           INTEGER,
  state           VARCHAR(255),
  prepaid         VARCHAR(255),
  comment         VARCHAR(255)
);
CREATE INDEX ride_client_login ON "${schema}".rides (client);
CREATE INDEX ride_date_in ON "${schema}".rides (date_in);
CREATE INDEX ride_state ON "${schema}".rides (state);

CREATE TABLE "${schema}".cars (
  id     INTEGER PRIMARY KEY,
  call   VARCHAR(255) UNIQUE,
  number VARCHAR(255) NOT NULL,
  model  VARCHAR(255) NOT NULL
);
CREATE INDEX cars_call ON "${schema}".cars (call);

CREATE TABLE "${schema}".app_users (
  id         VARCHAR(255) PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255),
  date_in    TIMESTAMP    NOT NULL,
  login_time TIMESTAMP    NOT NULL
);
CREATE INDEX app_users_name ON "${schema}".app_users (name);

CREATE TABLE "${schema}".settings (
  setting VARCHAR(255) PRIMARY KEY,
  value   VARCHAR(255) NOT NULL
);

CREATE TABLE "${schema}".system_properties (
  property VARCHAR(255) PRIMARY KEY,
  value    VARCHAR(255) NOT NULL
);


-- Create price tables
CREATE TABLE "${schema}".prices_dtd (
  id        INTEGER PRIMARY KEY,
  dist_from VARCHAR(255) REFERENCES "${schema}".districts (id),
  dist_to   VARCHAR(255) REFERENCES "${schema}".districts (id),
  price     INTEGER NOT NULL
);

CREATE TABLE "${schema}".prices_ctc (
  id       INTEGER PRIMARY KEY,
  cityFrom VARCHAR(255) REFERENCES "${schema}".cities (id),
  cityTo   VARCHAR(255) REFERENCES "${schema}".cities (id),
  price    INTEGER NOT NULL
);