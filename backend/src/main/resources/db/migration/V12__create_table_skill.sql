CREATE TABLE IF NOT EXISTS skill (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    xp FLOAT,
    level INTEGER,
    backup_id VARCHAR(36),
    FOREIGN KEY (backup_id) REFERENCES backup(id)
);