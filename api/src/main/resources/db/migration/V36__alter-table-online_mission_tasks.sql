ALTER TABLE IF EXISTS online_mission_tasks DROP CONSTRAINT online_mission_tasks_id_fkey,
ADD CONSTRAINT online_mission_tasks_id_fkey FOREIGN KEY (id) REFERENCES abstract_task(id);