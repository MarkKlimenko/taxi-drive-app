-- Create sequences -- TODO: decrease VARCHAR(255)
CREATE SEQUENCE "${schema}".seq_global;

-- Create geo tables
CREATE TABLE "${schema}".countries (
  id   VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE INDEX countries_name ON "${schema}".countries (name);

CREATE TABLE "${schema}".states (
  id      VARCHAR(255) PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  country VARCHAR(255) REFERENCES "${schema}".countries (id) NOT NULL
);
CREATE INDEX states_name ON "${schema}".states (name);
CREATE INDEX states_country ON "${schema}".states (country);

CREATE TABLE "${schema}".cities (
  id    VARCHAR(255) PRIMARY KEY,
  name  VARCHAR(255) NOT NULL,
  state VARCHAR(255) REFERENCES "${schema}".states (id) NOT NULL
);
CREATE INDEX cities_name ON "${schema}".cities (name);
CREATE INDEX cities_state ON "${schema}".cities (state);

CREATE TABLE "${schema}".streets (
  id   VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  city VARCHAR(255) REFERENCES "${schema}".cities (id) NOT NULL
);
CREATE INDEX streets_name ON "${schema}".streets (name);
CREATE INDEX streets_city ON "${schema}".streets (city);

CREATE TABLE "${schema}".districts (
  id   VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  city VARCHAR(255) REFERENCES "${schema}".cities (id) NOT NULL
);
CREATE INDEX district_name ON "${schema}".districts (name);

CREATE TABLE "${schema}".street_district_mapper (
  id          BIGINT PRIMARY KEY,
  district_id VARCHAR(255) REFERENCES "${schema}".districts (id) NOT NULL,
  street_id   VARCHAR(255) REFERENCES "${schema}".streets (id) NOT NULL,
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
  type  VARCHAR(255)
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

CREATE TABLE "${schema}".cars (
  id     BIGINT PRIMARY KEY,
  call   VARCHAR(255) UNIQUE,
  number VARCHAR(255) NOT NULL,
  model  VARCHAR(255) NOT NULL
);
CREATE INDEX cars_call ON "${schema}".cars (call);

CREATE TABLE "${schema}".rides (
id              BIGINT PRIMARY KEY,
client          VARCHAR(255) REFERENCES "${schema}".clients (login) NOT NULL,
from_address    INTEGER REFERENCES "${schema}".addresses (id) NOT NULL,
to_address      INTEGER REFERENCES "${schema}".addresses (id) NOT NULL,
date_in         TIMESTAMP,
ride_in         TIMESTAMP,
ride_out        TIMESTAMP,
car_id          BIGINT REFERENCES "${schema}".cars (id) NOT NULL,
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

CREATE TABLE "${schema}".app_users (
  id         VARCHAR(255) PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255),
  date_in    TIMESTAMP    NOT NULL,
  last_login_time TIMESTAMP    NOT NULL
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
  id        BIGINT PRIMARY KEY,
  dist_from VARCHAR(255) REFERENCES "${schema}".districts (id),
  dist_to   VARCHAR(255) REFERENCES "${schema}".districts (id),
  price     INTEGER NOT NULL
);

CREATE TABLE "${schema}".prices_ctc (
  id       BIGINT PRIMARY KEY,
  city_from VARCHAR(255) REFERENCES "${schema}".cities (id),
  city_to   VARCHAR(255) REFERENCES "${schema}".cities (id),
  price    INTEGER NOT NULL
);
