// Create table 'person'
CREATE TABLE %schema%.person (id UUID PRIMARY KEY,
                              login text,
                              firstName text,
                              lastName text);
CREATE INDEX person_login ON %schema%.person (login);