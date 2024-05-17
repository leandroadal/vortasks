CREATE TABLE IF NOT EXISTS progress_data (
    id VARCHAR(36) PRIMARY KEY,
    coins INT DEFAULT 0,
    gems INT DEFAULT 0,
    level INT DEFAULT 1,
    xp FLOAT DEFAULT 0,
    user_id VARCHAR(36),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);