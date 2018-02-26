CREATE SCHEMA tda_klimenko;
CREATE USER tda_klimenko;
ALTER ROLE tda_klimenko PASSWORD '12345';
GRANT  ALL  ON SCHEMA tda_klimenko TO tda_klimenko;

CREATE SCHEMA tda_kolesnikov;
CREATE USER tda_kolesnikov;
ALTER ROLE tda_kolesnikov PASSWORD '12345';
GRANT  ALL  ON SCHEMA tda_kolesnikov TO tda_kolesnikov;