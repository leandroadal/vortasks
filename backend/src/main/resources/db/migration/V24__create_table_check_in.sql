CREATE TABLE IF NOT EXISTS check_in (
    id BIGSERIAL PRIMARY KEY,
    coins INTEGER NOT NULL,
    gems INTEGER NOT NULL,
    day INTEGER NOT NULL
);