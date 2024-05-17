ALTER TABLE IF EXISTS users
ADD CONSTRAINT fk_progress_data_id FOREIGN KEY (progress_data_id) REFERENCES progress_data(id),
ADD CONSTRAINT fk_backup_id FOREIGN KEY (backup_id) REFERENCES backup(id);