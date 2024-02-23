CREATE TABLE IF NOT EXISTS product_category (
    product_id BIGSERIAL,
    category_id SERIAL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);