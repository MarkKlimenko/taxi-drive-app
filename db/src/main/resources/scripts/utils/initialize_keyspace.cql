DROP KEYSPACE %schema%;

// Create keyspace
CREATE KEYSPACE %schema% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};
