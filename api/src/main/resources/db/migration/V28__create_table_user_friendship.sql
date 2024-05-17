CREATE TABLE IF NOT EXISTS user_friendship (
    friendship_id VARCHAR(36),
    user_id VARCHAR(36),
    PRIMARY KEY (friendship_id, user_id),
    FOREIGN KEY (friendship_id) REFERENCES friendship(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);