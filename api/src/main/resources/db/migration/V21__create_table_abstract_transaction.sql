CREATE TABLE IF NOT EXISTS abstract_transaction (
    id VARCHAR(36) PRIMARY KEY,
    purchase_date TIMESTAMP WITH TIME ZONE,
    error_message VARCHAR(255),
    user_id VARCHAR(36),
    FOREIGN KEY (user_id) REFERENCES users(id)
);