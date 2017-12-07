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
INSERT INTO %keyspace%.street (id, name, city) values ('kre', 'Крестьянский', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('len', 'Ленинская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('len', 'Ленинская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('sovh per', 'Совхозный пер', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('spas', 'Спасская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('spas per', 'Спасский пер', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('stah per', 'Стахановский пер', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('suh', 'Сухановская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('has', 'Хасанская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('hank', 'Ханкайская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('hresh', 'Хрещатинская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('cheh', 'Чеховская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('volod', 'Володарского', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('gorov', 'Горовая', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('karer', 'Карьерная', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('lozov', 'Лозовая', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('mih', 'Михайловская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('ozer', 'Озерная', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('shors', 'Щорса', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('krasn', 'Красноармейская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('boris', 'Борисова', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('50letP', '50 лет Приморья', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('prtz per', 'Партизанский пер', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('gagar', 'Гагарина', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('sch per', 'Школный пер', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('kommun', 'Коммунальная', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('vkzl', 'Вокзальная', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('ersh', 'Ершова', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('dep', 'Деповская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('zarech', 'Заречная', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('prim', 'Приморская', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('of per', 'Офицерский пер', 'spa');
INSERT INTO %keyspace%.street (id, name, city) values ('svet', 'Светланская', 'spa');

// ----------------------------------- DISTRICT MATCHING ---------------------------------------

INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('1', 'grk', 'Горка', 'sovh per', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('2', 'grk', 'Горка', 'spas', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('3', 'grk', 'Горка', 'spas per', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('4', 'grk', 'Горка', 'stah per', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('5', 'grk', 'Горка', 'suh', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('6', 'grk', 'Горка', 'has', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('7', 'grk', 'Горка', 'hank', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('8', 'grk', 'Горка', 'hresh', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('9', 'grk', 'Горка', 'cheh', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('10', 'grk', 'Горка', 'volod', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('11', 'grk', 'Горка', 'gorov', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('12', 'grk', 'Горка', 'karer', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('13', 'grk', 'Горка', 'lozov', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('14', 'grk', 'Горка', 'mih', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('15', 'grk', 'Горка', 'ozer', '1000');

INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('16', 'grd', 'Город', 'par', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('17', 'grd', 'Город', 'shors', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('18', 'grd', 'Город', 'sov', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('19', 'grd', 'Город', 'kre', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('20', 'grd', 'Город', 'krasn', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('21', 'grd', 'Город', 'boris', '1000');

INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('22', 'mtf', 'МТФ', 'spas', '78');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('23', 'mtf', 'МТФ', 'hresh', '61');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('24', 'mtf', 'МТФ', '50letP', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('25', 'mtf', 'МТФ', 'ozer', '20');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('26', 'mtf', 'МТФ', 'prtz per', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('27', 'mtf', 'МТФ', 'gagar', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('28', 'mtf', 'МТФ', 'sch per', '1000');

INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('29', 'slk', 'Силикатный', 'kommun', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('30', 'slk', 'Силикатный', 'vkzl', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('31', 'slk', 'Силикатный', 'ersh', '1000');

INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('32', 'vkzl', 'Вокзал', 'dep', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('33', 'vkzl', 'Вокзал', 'zarech', '1000');

INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('35', 'dosa', 'ДОСА', 'prim', '11');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('36', 'dosa', 'ДОСА', 'of per', '1000');
INSERT INTO %keyspace%.district (id, shortName, name, street, building) values ('37', 'dosa', 'ДОСА', 'boris', '13');