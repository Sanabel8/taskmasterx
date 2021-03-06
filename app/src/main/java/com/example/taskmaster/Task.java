package com.example.taskmaster;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "titleTask")
    public String title;
    @ColumnInfo(name = "bodyTask")
    public String body;

    @ColumnInfo(name = "stateTask")
    public String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
