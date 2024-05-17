CREATE TABLE IF NOT EXISTS check_in_days (
    id VARCHAR(36) PRIMARY KEY,
    days INTEGER,
    month INTEGER,
    backup_id VARCHAR(36),
    FOREIGN KEY (backup_id) REFERENCES backup(id)
);