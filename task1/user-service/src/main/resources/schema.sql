CREATE TABLE IF NOT EXISTS user_info (
      id      INTEGER PRIMARY KEY,
      name    VARCHAR(64) NOT NULL,
      posts   INTEGER NOT NULL
  );

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;