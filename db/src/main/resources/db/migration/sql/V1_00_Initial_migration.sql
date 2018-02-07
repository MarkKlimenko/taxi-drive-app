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
