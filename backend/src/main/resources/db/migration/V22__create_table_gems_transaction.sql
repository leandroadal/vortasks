CREATE TABLE IF NOT EXISTS gems_transaction (
    id VARCHAR(36) PRIMARY KEY,
    status VARCHAR(50),
    price NUMERIC(38, 2),
    abstract_transaction_id VARCHAR(36), --extends
    currency_sell_id BIGINT,
    FOREIGN KEY (id) REFERENCES abstract_transaction(id),
    FOREIGN KEY (currency_sell_id) REFERENCES gems_package(id)
);