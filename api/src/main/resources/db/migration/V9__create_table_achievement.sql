CREATE TABLE IF NOT EXISTS achievement (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    xp INTEGER NOT NULL,
    backup_id VARCHAR(36),
    FOREIGN KEY (backup_id) REFERENCES backup(id)
);