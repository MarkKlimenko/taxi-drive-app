// Create keyspace
CREATE KEYSPACE %schema% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};

// Create table 'person'
CREATE TABLE %schema%.person (id UUID PRIMARY KEY,
                              login text,
                              firstName text,
                              lastName text);
CREATE INDEX person_login ON %schema%.person (login);