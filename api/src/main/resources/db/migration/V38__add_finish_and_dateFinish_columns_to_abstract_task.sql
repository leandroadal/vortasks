ALTER TABLE IF EXISTS abstract_task
ADD COLUMN finish BOOLEAN DEFAULT FALSE,
ADD COLUMN dateFinish TIMESTAMP WITH TIME ZONE;