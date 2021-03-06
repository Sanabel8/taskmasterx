package com.example.taskmaster;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class},exportSchema = false,version = 1)
public abstract class TaskDb extends RoomDatabase{
    public abstract TaskDao taskDao();
}
