// DIST_TO_DIST_PRICE
CREATE TABLE %keyspace%.price_dtd (id int PRIMARY KEY,
                                   distFrom text,
                                   distTo text,
                                   price int);

