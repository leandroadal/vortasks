CREATE TABLE IF NOT EXISTS user_purchased_products (
    user_id VARCHAR(36),
    product_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);