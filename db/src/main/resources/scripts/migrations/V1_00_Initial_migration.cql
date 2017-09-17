// Create keyspace
CREATE KEYSPACE %keyspace% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};

// Create table 'person'
CREATE TABLE %keyspace%.person (id UUID PRIMARY KEY,
                                login text,
                                firstName text,
                                lastName text);
CREATE INDEX person_login ON %keyspace%.person (login);