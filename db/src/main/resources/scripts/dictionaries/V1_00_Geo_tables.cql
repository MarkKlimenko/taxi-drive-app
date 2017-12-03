// COUNTRY
CREATE TABLE %keyspace%.country (id text PRIMARY KEY,
                                 name text);
CREATE INDEX county_name ON %keyspace%.country (name);

// STATE
CREATE TABLE %keyspace%.state (id text PRIMARY KEY,
                               name text,
                               country text);
CREATE INDEX state_name ON %keyspace%.state (name);
CREATE INDEX state_country ON %keyspace%.state (country);

// CITY
CREATE TABLE %keyspace%.city (id text PRIMARY KEY,
                              name text,
                              state text);
CREATE INDEX city_name ON %keyspace%.city (name);
CREATE INDEX city_state ON %keyspace%.city (state);

// STREET
CREATE TABLE %keyspace%.street (id text PRIMARY KEY,
                                name text,
                                city text);
CREATE INDEX street_name ON %keyspace%.street (name);
CREATE INDEX street_city ON %keyspace%.street (city);

// DISTRICT
CREATE TABLE %keyspace%.district (id text PRIMARY KEY,
                                  name text);
CREATE INDEX district_name ON %keyspace%.district (name);

// STREET_DISTRICT_MAPPER
CREATE TABLE %keyspace%.street_district_mapper (id int PRIMARY KEY,
                                                districtId text,
                                                streetId text,
                                                building text);
CREATE INDEX sdm_districtId ON %keyspace%.street_district_mapper (districtId);
CREATE INDEX sdm_streetId ON %keyspace%.street_district_mapper (streetId);

