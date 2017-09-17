// COUNTRY
CREATE TABLE %keyspace%.country (name text PRIMARY KEY);

// STATE
CREATE TABLE %keyspace%.state (name text PRIMARY KEY,
                               country text);
CREATE INDEX state_country ON %keyspace%.state (country);

// CITY
CREATE TABLE %keyspace%.city (name text PRIMARY KEY,
                              state text);
CREATE INDEX city_state ON %keyspace%.city (state);

// STREET
CREATE TABLE %keyspace%.street (name text PRIMARY KEY,
                                city text,
                                buildings set<text>);
CREATE INDEX street_city ON %keyspace%.street (city);