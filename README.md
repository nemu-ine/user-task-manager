# user-task-manager
Simple terminal-based local task manager, where you can add, remove, find tasks by id, mark as complete and build on top of it.

The purpose of this is to have a local database that you can access and tinker with directly.

This app will crash if you have not set up your database correctly or aren't running the instance.

### Set up
Make sure to modify pom.xml and persistence.xml to your dependencies as necessary

### SQL table
```
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    isCompleted BOOLEAN NOT NULL
);
```
