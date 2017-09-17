// Create keyspace
CREATE KEYSPACE %keyspace% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};

// PERSON
CREATE TABLE %keyspace%.person (personLogin text PRIMARY KEY,
                                firstName text,
                                lastName text,
                                dateOfBirth date,
                                ridesAmount int);
CREATE INDEX person_login ON %keyspace%.person (login);

// RIDE
CREATE TABLE %keyspace%.ride (id UUID PRIMARY KEY,
                              personLogin text,
                              fromAddress blob,
                              toAddress blob,
                              time timestamp,
                              rideDuration int,
                              carId int,
                              menInCar int);
CREATE INDEX person_personLogin ON %keyspace%.ride (personLogin);
CREATE INDEX person_time ON %keyspace%.ride (time);

// CAR
CREATE TABLE %keyspace%.car (carId int PRIMARY KEY,
                             carNumber text,
                             model text);