INSERT INTO %keyspace%.country (id, name) values ('rus', 'Россия');

INSERT INTO %keyspace%.state (id, name, country) values ('pk', 'Приморский край', 'rus');
INSERT INTO %keyspace%.state (id, name, country) values ('hk', 'Хабаровский край', 'rus');

INSERT INTO %keyspace%.city (id, name, state) values ('ars', 'Арсеньев', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('abr', 'Абражеевка', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('anu', 'Анучино', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('ari', 'Ариадное', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('art', 'Артем', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('arta', 'Артем(аэропорт)', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('arc', 'Архангеловка', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('bel', 'Бельцово', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('bar', 'Барабаш', 'pk');
INSERT INTO %keyspace%.city (id, name, state) values ('spa', 'Спасск-Дальний', 'pk');

INSERT INTO %keyspace%.street (id, name, city) values ('par', 'Парковая', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('sov', 'Советская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('kre', 'Крестьянский переулок', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('len', 'Ленинская', 'spa');
