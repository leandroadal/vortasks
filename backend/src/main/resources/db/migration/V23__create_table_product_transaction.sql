CREATE TABLE IF NOT EXISTS product_transaction (
    id VARCHAR(36) PRIMARY KEY,
    price_in_coins INTEGER NOT NULL,
    price_in_gems INTEGER NOT NULL,
    product_id BIGINT,  
    FOREIGN KEY (id) REFERENCES abstract_transaction(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);