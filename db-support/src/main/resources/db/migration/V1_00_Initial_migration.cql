// Create keyspace
CREATE KEYSPACE IF NOT EXISTS %keyspace% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};


CREATE TABLE IF NOT EXISTS %keyspace%.contextMessages (
                              id UUID,
                              operationName text,
                              owner text,
                              dateIn timestamp,
                              state text,
                              direction text,
                              requestBody text,
                              context text,
PRIMARY KEY (id, operationName, owner, dateIn));
