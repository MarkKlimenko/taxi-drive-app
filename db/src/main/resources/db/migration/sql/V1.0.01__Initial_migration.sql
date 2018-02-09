// Create keyspace
CREATE KEYSPACE %keyspace% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};

// ADDRESS DATATYPE
CREATE TYPE "${schema}".address (country text,
                                state text,
                                city text,
                                street text,
                                building text,
                                district text);

// CLIENT TABLE
CREATE TABLE "${schema}".client (clientLogin text PRIMARY KEY,
                                firstName text,
                                lastName text,
                                ridesAmount int,
                                clientType text);

// RIDE TABLE
CREATE TABLE "${schema}".ride (id UUID PRIMARY KEY,
                              clientLogin text,
                              fromAddress frozen <address>,
                              toAddress frozen <address>,
                              dateIn timestamp,
                              rideIn timestamp,
                              rideOut timestamp,
                              carId text,
                              adultInCar int,
                              childrenInCar int,
                              price int,
                              state text,
                              prepaid text,
                              comment text);
CREATE INDEX ride_clientLogin ON "${schema}".ride (clientLogin);
CREATE INDEX ride_dateIn ON "${schema}".ride (dateIn);
CREATE INDEX ride_state ON "${schema}".ride (state);

// CAR TABLE
CREATE TABLE "${schema}".car (carId int PRIMARY KEY,
                             carNumber text,
                             carModel text);

// USER TABLE
CREATE TABLE "${schema}".user (userId text PRIMARY KEY,
                              firstName text,
                              lastName text,
                              email text,
                              dateIn timestamp,
                              loginTime timestamp);

// DIST_TO_DIST_PRICE
CREATE TABLE "${schema}".price_dtd (id int PRIMARY KEY,
                                   distFrom text,
                                   distTo text,
                                   price int);

// CITY_TO_CITY_PRICE
CREATE TABLE "${schema}".price_ctc (id int PRIMARY KEY,
                                   cityFrom text,
                                   cityTo text,
                                   price int);
// SETTINGS
CREATE TABLE "${schema}".setting (setting text PRIMARY KEY,
                                 value text);

// SYSTEM_PROPERTIES
CREATE TABLE "${schema}".system_property (property text PRIMARY KEY,
                                         value text);


-- COUNTRY
CREATE TABLE %keyspace%.country (id text PRIMARY KEY,
name text);
CREATE INDEX county_name ON %keyspace%.country (name);

-- STATE
CREATE TABLE %keyspace%.state (id text PRIMARY KEY,
name text,
  country text);
CREATE INDEX state_name ON %keyspace%.state (name);
CREATE INDEX state_country ON %keyspace%.state (country);

-- CITY
CREATE TABLE %keyspace%.city (id text PRIMARY KEY,
name text,
  state text);
CREATE INDEX city_name ON %keyspace%.city (name);
CREATE INDEX city_state ON %keyspace%.city (state);

-- STREET
CREATE TABLE %keyspace%.street (id text PRIMARY KEY,
name text,
  city text);
CREATE INDEX street_name ON %keyspace%.street (name);
CREATE INDEX street_city ON %keyspace%.street (city);

-- DISTRICT
CREATE TABLE %keyspace%.district (id text PRIMARY KEY,
name text);
CREATE INDEX district_name ON %keyspace%.district (name);

-- STREET_DISTRICT_MAPPER
CREATE TABLE %keyspace%.street_district_mapper (id UUID PRIMARY KEY,
  districtId text,
  streetId text,
  building text);
CREATE INDEX sdm_districtId ON %keyspace%.street_district_mapper (districtId);
CREATE INDEX sdm_streetId ON %keyspace%.street_district_mapper (streetId);

