// Create keyspace
CREATE KEYSPACE %keyspace% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};
