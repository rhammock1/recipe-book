CREATE TABLE db_versions (
  db_version INT PRIMARY KEY,
  created TIMESTAMP DEFAULT NOW()
);
