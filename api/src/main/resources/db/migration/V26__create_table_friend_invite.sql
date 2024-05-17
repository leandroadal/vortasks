CREATE TABLE IF NOT EXISTS friend_invite (
    sender_user_id VARCHAR(36),
    receiver_user_id VARCHAR(36),
    request_date TIMESTAMP WITH TIME ZONE,
    status VARCHAR(50),
    PRIMARY KEY (sender_user_id, receiver_user_id),
    FOREIGN KEY (sender_user_id) REFERENCES users(id),
    FOREIGN KEY (receiver_user_id) REFERENCES users(id)
);