CREATE TABLE skill_task (
  task_id VARCHAR(36) NOT NULL,
  skill_id VARCHAR(36) NOT NULL,
  PRIMARY KEY (task_id, skill_id),
  FOREIGN KEY (task_id) REFERENCES abstract_task(id),
  FOREIGN KEY (skill_id) REFERENCES skill(id)
);