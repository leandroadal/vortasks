ALTER TABLE IF EXISTS goals

ADD COLUMN daily INTEGER,
ADD COLUMN weekly INTEGER,
ADD COLUMN monthly INTEGER,

ADD COLUMN daily_goal_progress INTEGER,
ADD COLUMN monthly_goal_progress INTEGER;