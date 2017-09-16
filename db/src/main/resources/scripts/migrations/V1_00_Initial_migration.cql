// Create table 'person'
CREATE TABLE tda.person (id UUID PRIMARY KEY,
                         login text,
                         firstName text,
                         lastName text);
CREATE INDEX person_login ON tda.person (login);