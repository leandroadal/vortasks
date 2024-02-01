CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    type VARCHAR(50),
    icon VARCHAR(255),
    coins INT,
    gems INT,
    total_sales INT DEFAULT 0
);
