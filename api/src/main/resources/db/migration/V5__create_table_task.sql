CREATE TABLE IF NOT EXISTS task (
    id VARCHAR(36) PRIMARY KEY,
    backup_id VARCHAR(36),
    FOREIGN KEY (id) REFERENCES abstract_task(id),
    FOREIGN KEY (backup_id) REFERENCES backup(id)
);