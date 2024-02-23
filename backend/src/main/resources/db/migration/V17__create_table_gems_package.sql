CREATE TABLE IF NOT EXISTS gems_package (
    id BIGSERIAL PRIMARY KEY,
    name_of_package VARCHAR(255) UNIQUE NOT NULL,
    icon VARCHAR(255),
    gems INTEGER NOT NULL,
    money NUMERIC(38, 2) NOT NULL,
    discount_percentage FLOAT,
    total_sales INTEGER DEFAULT 0
);
