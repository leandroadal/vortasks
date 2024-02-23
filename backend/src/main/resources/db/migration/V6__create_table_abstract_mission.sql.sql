CREATE TABLE IF NOT EXISTS abstract_mission (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(50),
    xp INTEGER NOT NULL,
    coins INTEGER NOT NULL,
    type VARCHAR(50),
    repetition VARCHAR(255),
    reminder VARCHAR(255),
    skill_increase INTEGER,
    skill_decrease INTEGER,
    start_date TIMESTAMP WITH TIME ZONE,
    end_date TIMESTAMP WITH TIME ZONE,
    theme VARCHAR(50),
    difficulty VARCHAR(50)
);
