ALTER TABLE IF EXISTS mission_tasks DROP CONSTRAINT mission_tasks_id_fkey,
ADD CONSTRAINT mission_tasks_id_fkey FOREIGN KEY (id) REFERENCES abstract_task(id);