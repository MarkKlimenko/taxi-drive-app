
// INSERT price Churkin -> Tikhaya
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (1, 'Churkin', 'Tikhaya', 250)
IF NOT EXISTS;