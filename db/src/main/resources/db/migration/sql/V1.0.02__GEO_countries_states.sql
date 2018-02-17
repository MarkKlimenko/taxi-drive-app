-- COUNTRIES
INSERT INTO "${schema}".countries (id, name) values ('ru', 'Россия');

-- STATES
INSERT INTO "${schema}".states (id, name, country) values ('pk', 'Приморский край', 'ru');
INSERT INTO "${schema}".states (id, name, country) values ('hk', 'Хабаровский край', 'ru');