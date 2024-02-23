CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    icon VARCHAR(255),
    coins INTEGER NOT NULL,
    gems INTEGER NOT NULL,
    total_sales INT DEFAULT 0,
    active BOOLEAN
);