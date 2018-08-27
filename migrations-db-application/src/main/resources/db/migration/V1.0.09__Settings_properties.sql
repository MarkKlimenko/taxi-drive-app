-- SETTINGS
INSERT INTO "${schema}".settings (setting, value) VALUES ('ride_free', '10');
INSERT INTO "${schema}".settings (setting, value) VALUES ('free_discount', '1');
INSERT INTO "${schema}".settings (setting, value) VALUES ('vip_discount', '0.2');
INSERT INTO "${schema}".settings (setting, value) VALUES ('zero_discount', '0');

INSERT INTO "${schema}".settings (setting, value) VALUES ('default_city', 'Спасск-Дальний');
INSERT INTO "${schema}".settings (setting, value) VALUES ('default_city_id', 'spa');

-- SYSTEM PROPERTIES
INSERT INTO "${schema}".system_properties(property, value) VALUES ('geo_version', 'a3ed4431-1874-4cc8-bf1d-a3bd0ed8cd62');