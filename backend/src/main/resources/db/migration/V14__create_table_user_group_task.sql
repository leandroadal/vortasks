CREATE TABLE IF NOT EXISTS user_group_task (
    group_task_id VARCHAR(36),
    user_id VARCHAR(36),
    PRIMARY KEY (group_task_id, user_id),
    FOREIGN KEY (group_task_id) REFERENCES group_task(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);