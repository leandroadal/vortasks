CREATE TABLE IF NOT EXISTS mission_tasks (
    id VARCHAR(36) PRIMARY KEY,
    mission_id VARCHAR(36),
    FOREIGN KEY (id) REFERENCES abstract_mission(id),
    FOREIGN KEY (mission_id) REFERENCES mission(id) ON DELETE CASCADE
);