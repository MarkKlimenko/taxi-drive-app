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

// PERSON TABLE
CREATE TABLE %keyspace%.person (personLogin text PRIMARY KEY,
                                firstName text,
                                lastName text,
                                dateOfBirth date,
                                ridesAmount int);

// USER TABLE
CREATE TABLE %keyspace%.user (userId text PRIMARY KEY,
                              firstName text,
                              lastName text,
                              email text,
                              dateIn timestamp,
                              loginTime timestamp);

// RIDE TABLE
CREATE TABLE %keyspace%.ride (id UUID PRIMARY KEY,
                              personLogin text,
                              fromAddress frozen <address>,
                              toAddress frozen <address>,
                              time timestamp,
                              rideDuration int,
                              carId int,
                              menInCar int);
CREATE INDEX person_personLogin ON %keyspace%.ride (personLogin);
CREATE INDEX person_time ON %keyspace%.ride (time);

// CAR TABLE
CREATE TABLE %keyspace%.car (carId int PRIMARY KEY,
                             carNumber text,
                             model text);