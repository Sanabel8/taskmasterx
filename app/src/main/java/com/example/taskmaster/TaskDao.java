package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("Select * from tasks")
    List<Task> getAll();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);
}
