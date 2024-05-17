ALTER TABLE abstract_mission ALTER COLUMN reminder TYPE TIMESTAMP WITH TIME ZONE USING reminder::timestamp with time zone;

ALTER TABLE abstract_mission ALTER COLUMN repetition TYPE INTEGER USING repetition::INTEGER;
