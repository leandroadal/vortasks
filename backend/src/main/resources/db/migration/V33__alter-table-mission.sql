ALTER TABLE IF EXISTS mission DROP CONSTRAINT mission_id_fkey,
ADD CONSTRAINT mission_id_fkey FOREIGN KEY (id) REFERENCES abstract_task(id);