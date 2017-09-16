DROP KEYSPACE %schema%;
CREATE KEYSPACE %schema% WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};
