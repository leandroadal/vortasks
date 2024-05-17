CREATE TABLE IF NOT EXISTS mission (
    id VARCHAR(36) PRIMARY KEY,
    backup_id VARCHAR(36),
    FOREIGN KEY (id) REFERENCES abstract_mission(id),
    FOREIGN KEY (backup_id) REFERENCES backup(id)
);