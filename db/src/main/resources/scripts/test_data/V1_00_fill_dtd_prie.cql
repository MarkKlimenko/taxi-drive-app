
// INSERT price grk -> grd
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3284712, 'grk', 'grd', 110)
IF NOT EXISTS;

// INSERT price grk -> mtf
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3290752, 'grk', 'mtf', 110)
IF NOT EXISTS;

// INSERT price grk -> grk
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3284929, 'grk', 'grk', 110)
IF NOT EXISTS;

// INSERT price grk -> slk
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3296275, 'grk', 'slk', 110)
IF NOT EXISTS;

// INSERT price grk -> vkzl
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (6804360, 'grk', 'vkzl', 110)
IF NOT EXISTS;

// INSERT price grk -> dosa
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (95876008, 'grk', 'dosa', 130)
IF NOT EXISTS;

// INSERT price mtf -> mtf
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3471265, 'mtf', 'mtf', 100)
IF NOT EXISTS;

// INSERT price mtf -> grd
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3290535, 'mtf', 'grd', 120)
IF NOT EXISTS;

// INSERT price mtf -> slk
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (3476788, 'mtf', 'slk', 120)
IF NOT EXISTS;

// INSERT price mtf -> vkzl
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (6984873, 'mtf', 'vkzl', 110)
IF NOT EXISTS;

// INSERT price mtf -> dosa
INSERT INTO %keyspace%.price_dtd (id, distfrom, distto, price)
VALUES (95881831, 'mtf', 'dosa', 140)
IF NOT EXISTS;