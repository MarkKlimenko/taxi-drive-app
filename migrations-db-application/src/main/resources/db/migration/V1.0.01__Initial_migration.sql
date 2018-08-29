-- Create sequences
CREATE SEQUENCE "${schema}".seq_global;

-- Create geo tables
CREATE TABLE "${schema}".countries (
  id   VARCHAR(10) PRIMARY KEY,
  name VARCHAR(30) UNIQUE NOT NULL
);
CREATE INDEX countries_name ON "${schema}".countries (name);

CREATE TABLE "${schema}".states (
  id      VARCHAR(10) PRIMARY KEY,
  name    VARCHAR(30) UNIQUE                                NOT NULL,
  country VARCHAR(10) REFERENCES "${schema}".countries (id) NOT NULL
);
CREATE INDEX states_name ON "${schema}".states (name);
CREATE INDEX states_country ON "${schema}".states (country);

CREATE TABLE "${schema}".cities (
  id    VARCHAR(10) PRIMARY KEY,
  name  VARCHAR(30) UNIQUE                             NOT NULL,
  state VARCHAR(10) REFERENCES "${schema}".states (id) NOT NULL
);
CREATE INDEX cities_name ON "${schema}".cities (name);
CREATE INDEX cities_state ON "${schema}".cities (state);

CREATE TABLE "${schema}".streets (
  id   VARCHAR(10) PRIMARY KEY,
  name VARCHAR(30) UNIQUE                             NOT NULL,
  city VARCHAR(10) REFERENCES "${schema}".cities (id) NOT NULL
);
CREATE INDEX streets_name ON "${schema}".streets (name);
CREATE INDEX streets_city ON "${schema}".streets (city);

CREATE TABLE "${schema}".districts (
  id   VARCHAR(10) PRIMARY KEY,
  name VARCHAR(30) UNIQUE                             NOT NULL,
  city VARCHAR(10) REFERENCES "${schema}".cities (id) NOT NULL
);
CREATE INDEX district_name ON "${schema}".districts (name);

CREATE TABLE "${schema}".street_district_mapper (
  id          BIGINT PRIMARY KEY,
  district_id VARCHAR(10) REFERENCES "${schema}".districts (id) NOT NULL,
  street_id   VARCHAR(10) REFERENCES "${schema}".streets (id)   NOT NULL,
  building    VARCHAR(30)
);
CREATE INDEX sdm_districtId ON "${schema}".street_district_mapper (district_id);
CREATE INDEX sdm_streetId ON "${schema}".street_district_mapper (street_id);


-- Create common tables
CREATE TABLE "${schema}".clients (
  login        VARCHAR(30) PRIMARY KEY,
  first_name   VARCHAR(30) NOT NULL,
  last_name    VARCHAR(30),
  rides_amount INTEGER,
  type         VARCHAR(30)
);

CREATE TABLE "${schema}".addresses (
  id       INTEGER PRIMARY KEY,
  country  VARCHAR(30),
  state    VARCHAR(30),
  city     VARCHAR(30),
  street   VARCHAR(30),
  building VARCHAR(30),
  district VARCHAR(30)
);

CREATE TABLE "${schema}".cars (
  id     BIGINT PRIMARY KEY,
  call   VARCHAR(10) UNIQUE NOT NULL,
  number VARCHAR(10)        NOT NULL,
  model  VARCHAR(60)        NOT NULL
);
CREATE INDEX cars_call ON "${schema}".cars (call);

CREATE TABLE "${schema}".rides (
  id              BIGINT PRIMARY KEY,
  client          VARCHAR(30) REFERENCES "${schema}".clients (login)  NOT NULL,
  from_address    INTEGER REFERENCES "${schema}".addresses (id)       NOT NULL,
  to_address      INTEGER REFERENCES "${schema}".addresses (id)       NOT NULL,
  date_in         TIMESTAMP                                           NOT NULL,
  ride_in         TIMESTAMP                                           NOT NULL,
  ride_out        TIMESTAMP,
  car_id          BIGINT REFERENCES "${schema}".cars (id),
  adult_in_car    INTEGER,
  children_in_car INTEGER,
  price           INTEGER,
  state           VARCHAR(30),
  prepaid         BOOLEAN,
  comment         VARCHAR(255)
);
CREATE INDEX ride_client_login ON "${schema}".rides (client);
CREATE INDEX ride_date_in ON "${schema}".rides (date_in);
CREATE INDEX ride_state ON "${schema}".rides (state);

CREATE TABLE "${schema}".app_users (
  id              VARCHAR(30) PRIMARY KEY,
  name            VARCHAR(30) NOT NULL,
  email           VARCHAR(30),
  date_in         TIMESTAMP   NOT NULL,
  last_login_time TIMESTAMP   NOT NULL
);
CREATE INDEX app_users_name ON "${schema}".app_users (name);

CREATE TABLE "${schema}".settings (
  setting VARCHAR(30) PRIMARY KEY,
  value   VARCHAR(255) NOT NULL
);

CREATE TABLE "${schema}".system_properties (
  property VARCHAR(30) PRIMARY KEY,
  value    VARCHAR(255) NOT NULL
);


-- Create price tables
CREATE TABLE "${schema}".prices_dtd (
  id        INTEGER PRIMARY KEY,
  dist_from VARCHAR(10) NOT NULL REFERENCES "${schema}".districts (id),
  dist_to   VARCHAR(10) NOT NULL REFERENCES "${schema}".districts (id),
  price     INTEGER     NOT NULL
);

CREATE TABLE "${schema}".prices_ctc (
  id        INTEGER PRIMARY KEY,
  city_from VARCHAR(10) NOT NULL REFERENCES "${schema}".cities (id),
  city_to   VARCHAR(10) NOT NULL REFERENCES "${schema}".cities (id),
  price     INTEGER     NOT NULL
);
