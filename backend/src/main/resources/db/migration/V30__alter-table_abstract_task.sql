ALTER TABLE abstract_task ALTER COLUMN reminder TYPE TIMESTAMP WITH TIME ZONE USING reminder::timestamp with time zone;
