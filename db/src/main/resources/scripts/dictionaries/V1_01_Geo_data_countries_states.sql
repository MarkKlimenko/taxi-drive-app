// COUNTRIES
INSERT INTO %keyspace%.country (id, name) values ('rus', 'Россия');

// STATES
INSERT INTO %keyspace%.state (id, name, country) values ('pk', 'Приморский край', 'rus');
INSERT INTO %keyspace%.state (id, name, country) values ('hk', 'Хабаровский край', 'rus');