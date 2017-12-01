list all permissions of tda_dev_admin;

CREATE ROLE tda_dev
WITH PASSWORD = 'password'
     AND LOGIN = true;

CREATE ROLE tda_dev_admin;

GRANT ALL PERMISSIONS on KEYSPACE tda_dev to tda_dev_admin;

GRANT tda_dev_admin TO tda_dev;

