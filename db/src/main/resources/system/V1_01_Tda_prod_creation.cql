CREATE ROLE tda_prod
WITH PASSWORD = 'password'
     AND LOGIN = true;

CREATE ROLE tda_prod_admin;

GRANT ALL PERMISSIONS on KEYSPACE tda_prod to tda_prod_admin;

GRANT tda_prod_admin TO tda_prod;

