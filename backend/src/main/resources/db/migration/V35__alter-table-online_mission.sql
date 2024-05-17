ALTER TABLE IF EXISTS online_mission DROP CONSTRAINT online_mission_id_fkey,
ADD CONSTRAINT online_mission_id_fkey FOREIGN KEY (id) REFERENCES abstract_task(id);