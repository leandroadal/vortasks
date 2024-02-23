CREATE TABLE IF NOT EXISTS online_mission (
    id VARCHAR(36) PRIMARY KEY,
    likes INTEGER,
    user_id VARCHAR(36),
    abstract_mission_id VARCHAR(36),
    FOREIGN KEY (id) REFERENCES abstract_mission(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);