CREATE TABLE IF NOT EXISTS goals (
    id VARCHAR(36) PRIMARY KEY,
    daily FLOAT,
    monthly FLOAT,
    backup_id VARCHAR(36),
    FOREIGN KEY (backup_id) REFERENCES backup(id)
);