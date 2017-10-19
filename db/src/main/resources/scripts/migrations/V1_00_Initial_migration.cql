// Create keyspace
CREATE KEYSPACE %keyspace% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};

// ADDRESS DATATYPE
CREATE TYPE %keyspace%.address (country text,
                                state text,
                                city text,
                                street text,
                                building text);

// CLIENT TABLE
CREATE TABLE %keyspace%.client (clientLogin text PRIMARY KEY,
                                firstName text,
                                lastName text,
                                dateOfBirth date,
                                ridesAmount int);

// RIDE TABLE
CREATE TABLE %keyspace%.ride (id UUID PRIMARY KEY,
                              clientLogin text,
                              fromAddress frozen <address>,
                              toAddress frozen <address>,
                              time timestamp,
                              rideDuration int,
                              carId int,
                              menInCar int);
CREATE INDEX ride_clientLogin ON %keyspace%.ride (clientLogin);
CREATE INDEX ride_time ON %keyspace%.ride (time);

// CAR TABLE
CREATE TABLE %keyspace%.car (carId int PRIMARY KEY,
                             carNumber text,
                             model text);

// USER TABLE
CREATE TABLE %keyspace%.user (userId text PRIMARY KEY,
                              firstName text,
                              lastName text,
                              email text,
                              dateIn timestamp,
                              loginTime timestamp);