CREATE TABLE IF NOT EXISTS online_mission_tasks (
    id VARCHAR(36) PRIMARY KEY,
    online_mission_id VARCHAR(36),
    FOREIGN KEY (id) REFERENCES abstract_mission(id),
    FOREIGN KEY (online_mission_id) REFERENCES online_mission(id)
);