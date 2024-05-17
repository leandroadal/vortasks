CREATE TABLE IF NOT EXISTS group_task (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    author VARCHAR(255) NOT NULL,
    editor VARCHAR(255),
    FOREIGN KEY (id) REFERENCES abstract_task(id)
);