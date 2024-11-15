ALTER TABLE units DROP COLUMN unit_system;

-- this deleted all duplicated rows
WITH dupe_units AS (
  SELECT unit_name, unit_abbreviation, unit_type
  FROM units
  GROUP BY unit_name, unit_abbreviation, unit_type
  HAVING COUNT(*) > 1
) DELETE FROM units
WHERE (unit_name, unit_abbreviation, unit_type) IN (SELECT * FROM dupe_units);